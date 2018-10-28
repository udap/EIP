package org.solidityj

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

// bran: only used for collecting conImports
class ImportsBuilder() : SolidityBaseVisitor<ASTNode>() {

    public var contractName: String? = null
    public var imports: List<String> = arrayListOf<String>()
    public var baseContracts: List<String> ? = null;
    public var isLibrary: Boolean = false;

    override fun visitInheritanceSpecifier(ctx: SolidityParser.InheritanceSpecifierContext?): ASTNode {
        val baseName = visit(ctx!!.userDefinedTypeName()) as UserDefinedTypeName
        val arguments = ctx.expression().map { visit(it) as Expression }
        return InheritanceSpecifier(baseName, arguments)
    }

    override fun visitUserDefinedTypeName(ctx: SolidityParser.UserDefinedTypeNameContext?): ASTNode {
        return UserDefinedTypeName(ctx!!.text)
    }


    override fun visitContractDefinition(ctx: SolidityParser.ContractDefinitionContext?): ASTNode {
        val name = ctx!!.Identifier().text
        this.contractName = name
        this.baseContracts = ctx.inheritanceSpecifier().map {
            (visitInheritanceSpecifier(it) as InheritanceSpecifier).baseName.namePath }
        this.isLibrary = ctx.getChild(0).text == "library"

        // nullify all the content of the contract
        return ASTNode()
    }

    override fun visitImportDirective(ctx: SolidityParser.ImportDirectiveContext?): ASTNode {
        val pathString = ctx!!.StringLiteral().text
        (imports as ArrayList<String>).add(pathString.substring(1, pathString.length - 1))
        return ASTNode();
    }

    fun foo():String {
        return "hello"
    }

}

fun parseImports(code: String): ImportsBuilder {
    val parser = SolidityParser(CommonTokenStream(SolidityLexer(CharStreams.fromString(code))))
    val builder = ImportsBuilder()
    builder.visit(parser.sourceUnit())
    return builder
}

fun parseImports(file: File): ImportsBuilder {
    val builder = parseImports(file.readText())
    // fix the path

    val tmp : ArrayList<String> = arrayListOf();

    for (im in builder.imports) {
        var s = im;
        if (im.startsWith(".")) {
            s = File(file.parentFile, im).canonicalPath
        }
        tmp.add(s);
    }
    builder.imports = tmp

    return builder
}
