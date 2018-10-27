package org.solidityj

open class ASTNode {

    val nodeName: String
        get() = this.javaClass.simpleName

    open val childNodes: List<ASTNode>
        get() = emptyList()

    override fun toString(): String {
        return toString(0)
    }

    fun toString(indentation: Int): String {
        val sb = StringBuilder()

        indent(indentation, sb)
        sb.append(nodeName + '\n')

        for (node in childNodes) {
            sb.append(node.toString(indentation + 1))
        }

        return sb.toString()
    }

    private fun indent(indentation: Int, sb: StringBuilder) {
        for (i in 1..indentation) { sb.append("  ") }
    }
}

enum class Visibility {
    Default, Private, Internal, Public, External
}

open class Declaration(
    val name: String?,
    val visibility: Visibility = Visibility.Default
) : ASTNode()

class ImportDirective(
        unitAlias: String?,
        val path: String
) : Declaration(unitAlias)

class InheritanceSpecifier(
        val baseName: UserDefinedTypeName,
        val arguments: List<Expression>
) : ASTNode() {
    override val childNodes: List<ASTNode>
        get() = listOf(baseName) + arguments
}

open class TypeName : ASTNode() {
    object Var : TypeName()
}

class UserDefinedTypeName(val namePath: String) : TypeName()


open class Expression : ASTNode()

open class PrimaryExpression : Expression()

class Identifier(val name: String) : PrimaryExpression()



