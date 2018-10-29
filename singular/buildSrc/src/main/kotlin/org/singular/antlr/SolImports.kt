package org.singular.antlr

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

fun parseImports(code: String): Array<String> {
    val parser = FuzzySolidityParser(CommonTokenStream(FuzzySolidityLexer(CharStreams.fromString(code))));
    return parser.altfile().importDirective()
            .map {
                it.StringLiteral.text.trim('\'', '"')
            }
            .toTypedArray();
}

/**
 * return the imported files in the contract, as they are exactly appear in the contract. No
 * path mapping is applied.
 */
fun parseImports(contract: File): Array<String> {
    val code = contract.readText()
    val imports = parseImports(code)
    return imports
}
