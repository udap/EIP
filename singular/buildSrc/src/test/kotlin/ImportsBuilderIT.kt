package org.solidityj

import org.junit.Test
import kotlin.test.assertEquals

class ImportsBuilderIT {

    @Test
    fun testParseToken() {
        val sourceCode = """
            import './ERC20.sol';
            import '../tools.sol';
            import 'lib/SafeMath.sol';
//          import 'somthing.sol'; // this one should not be included as part of the conImports

            contract StandardToken is ERC20, SafeMath {
                function allowance(address _owner, address _spender) constant returns (uint remaining) {
                    return allowed[_owner][_spender];
                }
            }
        """
        var builder = parseImports(sourceCode)
        assertEquals(3, builder.imports.size)
        assertEquals(2, builder.baseContracts!!.size)
        println(builder.imports)
        assertEquals("StandardToken", builder.contractName)
        println(builder.baseContracts)
    }
}
