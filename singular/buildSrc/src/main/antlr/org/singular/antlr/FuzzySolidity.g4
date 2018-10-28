grammar FuzzySolidity;

@header {
    package org.singular.antlr;

//    import java.util.HashMap;
}

file : .*? (importDirective .*?)+ ;

/*
 *
 * Faster alternate version (Gets an ANTLR tool warning about a subrule like .* in parser that you can ignore.)
 */

altfile : (importDirective | .)* ;

Identifier : [a-zA-Z_$] [a-zA-Z_$0-9]* ; // simplified


importDirective
    : 'import' StringLiteral ('as' Identifier)? ';'
         {/*System.out.println("import1: "+$StringLiteral.text);*/}
    | 'import' ('*' | Identifier) ('as' Identifier)? 'from' StringLiteral ';'
         {/*System.out.println("import2: "+$StringLiteral.text);*/}
    | 'import' '{' importDeclaration ( ',' importDeclaration )* '}' 'from' StringLiteral ';'
        {/*System.out.println("import3: "+$StringLiteral.text);*/}
    ;

importDeclaration : Identifier ('as' Identifier)? ;

StringLiteral
    : '"' DoubleQuotedStringCharacter* '"'
    | '\'' SingleQuotedStringCharacter* '\''
    ;

fragment
DoubleQuotedStringCharacter : ~["\r\n\\] | ('\\' .) ;

fragment
SingleQuotedStringCharacter : ~['\r\n\\] | ('\\' .) ;


COMMENT
    :   '/*' .*? '*/'    -> skip // match anything between /* and */
    ;
WS  :   [ \r\t\u000C\n]+ -> skip
    ;

LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> skip
    ;

OTHER : . -> skip ;
