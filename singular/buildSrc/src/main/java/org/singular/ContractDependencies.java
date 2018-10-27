package org.singular;

import org.jetbrains.annotations.NotNull;
import org.solidityj.ImportsBuilder;
import org.solidityj.ImportsBuilderKt;

import java.io.*;
import java.util.*;

//import static org.solidityj.ImportsBuilderKt.parseImports;

public class ContractDependencies implements Serializable {

    public Map<String, Set<String>> conImports = new HashMap<>();

    transient String persistFile;

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
            return depends;
        } catch (IOException i) {
            i.printStackTrace();
            return new ContractDependencies();
        } catch (ClassNotFoundException c) {
            System.out.println("ContractDependencies class not found");
            throw new RuntimeException(c);
        }
    }

    public void scan(File contract) throws IOException {
        ImportsBuilder builder = ImportsBuilderKt.parseImports(contract);
        List<String> imports = builder.getImports();
        String canonicalPath = contract.getCanonicalPath();
        Set<String> oldSet = this.conImports.put(canonicalPath, new HashSet<>(imports));
        if (oldSet != null && oldSet.removeAll(imports)) {
            for (String i : oldSet) {
                Set<String> strings = getDependents(i);
                strings.remove(canonicalPath);
            }
        }
        // update the conDirectDependents
        // need to find out the deleted dependencies
        for (String imp : imports) {
            Set<String> strings = getDependents(imp);
//            if(!canonicalPath.startsWith("/")) {
//                System.out.println("add: " + canonicalPath);
//            }
            strings.add(canonicalPath);
        }
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

        // now all files are processed
        Set<String> result = new HashSet<>(updatedFileNames);
        for (String f : updatedFileNames) {
            result.addAll(getDependents(f));
        }
        for(String s : result) {
            if (s.startsWith("/")) {

            }
            else {
                System.out.println("what: " + s);
            }
        }
        return result;
    }
}