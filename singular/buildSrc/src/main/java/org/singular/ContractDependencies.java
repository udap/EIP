package org.singular;

import org.jetbrains.annotations.NotNull;
import org.singular.antlr.SolImportsKt;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//import static org.solidityj.ImportsBuilderKt.parseImports;

public class ContractDependencies implements Serializable {

    public Map<String, Set<String>> conImports = new HashMap<>();

    transient String persistFile;

    public static final String DefaultDependencyFileName = "src/tmp/dependencies.data";
    /**
     * the files that depend on a file. If the file is changed, all the conDirectDependents
     * must be updated too.
     */
    public Map<String, Set<String>> conDirectDependents = new HashMap<>();

    public static ContractDependencies restoreFrom(String p) {
        File dataFile = new File(p);
        if (!dataFile.exists()) {
            ContractDependencies contractDependencies = new ContractDependencies();
            contractDependencies.persistFile = p;
            return contractDependencies;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(p))
        ) {
            ContractDependencies depends = (ContractDependencies) in.readObject();
            depends.persistFile = p;
            System.out.println("From drive - imports database size: " + depends.conImports.keySet().size());
            System.out.println("From drive - dependency database size: " + depends.conDirectDependents.keySet().size());
            return depends;
        } catch (IOException i) {
            i.printStackTrace();
            return new ContractDependencies();
        } catch (ClassNotFoundException c) {
            System.out.println("ContractDependencies class not found");
            throw new RuntimeException(c);
        }
    }

    public static ContractDependencies fresh(String p) {
        File dataFile = new File(p);
        if (dataFile.exists()) {
            dataFile.delete();
        }
        ContractDependencies contractDependencies = new ContractDependencies();
        contractDependencies.persistFile = p;
        return contractDependencies;
    }

    /**
     * scan the source code of a contract and update the dependency database
     * @param contract
     * @throws IOException
     */
    public void scan(File contract) throws IOException {
        List<String> imports = Arrays.asList(SolImportsKt.parseImports(contract));

        imports = makeCanonical(contract, imports);

        String thisContract = contract.getCanonicalPath();
        Set<String> oldSet = this.conImports.put(thisContract, new HashSet<>((imports)));
        if (oldSet != null && oldSet.removeAll(imports)) {
            // break the dependencies for reflect the latest imports
            for (String i : oldSet) {
                Set<String> strings = getDependents(i);
                strings.remove(thisContract);
            }
        }
        // update the conDirectDependents
        for (String imp : imports) {
            Set<String> strings = getDependents(imp);
            strings.add(thisContract);
        }
    }

    private List<String> makeCanonical(File contract, List<String> imports) {
        return imports.stream()
                .filter(it ->
                        // all relative paths to the current file
                        // absolute paths are ignored for now.
                        // we may consider path mapping later
                        it.startsWith(".")
                )
                .map(it -> {
                    try {
                        return new File(contract.getParentFile(), it).getCanonicalPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return it;
                    }
                })
                .collect(Collectors.toList());
    }

    @NotNull
    private Set<String> getDependents(String i) {
        Set<String> strings = conDirectDependents.get(i);
        if (strings == null) {
            strings = new HashSet<>();
            conDirectDependents.put(i, strings);
        }
        return strings;
    }

    /**
     * to deduce a set of files that must recompile based on the changed files
     *
     * @param updatedFileNames the changed files from last check
     * @return the files to be re-processed
     */
    public Set<String> update(Set<String> updatedFileNames) {
        for (String f : updatedFileNames) {
            if (!f.startsWith("/")) {
                System.out.println("check: " + f);
            }

            try {
                scan(new File(f));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // persist
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(this.persistFile))) {
            out.writeObject(this);
        } catch (IOException i) {
            throw new RuntimeException(i);
        }

        Set<String> sourceSet = new HashSet<>();

        // now all files are processed, let deduce the source set to be compiled
        Set<String> depends = new HashSet<>(updatedFileNames);

        while (depends.size() > 0) {
            sourceSet.addAll(depends);
            Set<String> tmps = new HashSet<>();
            for (String f : depends) {
                tmps.addAll(getDependents(f));
            }
            depends = tmps;
            depends.removeAll(sourceSet); // the new ones
        }

        System.out.println("compilation set size: " + sourceSet.size());
        sourceSet.forEach(System.out::println);
        return sourceSet;
    }
}
