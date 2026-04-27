grammar XPath;

/* Entry point */
ap
    : 'doc' '(' fileName ')' '/' rp
    | 'doc' '(' fileName ')' '//' rp
    ;

fileName
    : StringConstant
    ;

/* Relative path — precedence from lowest to highest:
     comma (,) < slash (/ //) < filter ([]) < atom
*/
rp
    : rp ',' rp                         // rule 13
    | rp '/' rp                         // rule 10
    | rp '//' rp                        // rule 11
    | rp '[' f ']'                      // rule 12
    | NAME                              // rule 3
    | '*'                               // rule 4
    | '.'                               // rule 5
    | '..'                              // rule 6
    | 'text' '(' ')'                    // rule 7
    | '@' NAME                          // rule 8
    | '(' rp ')'                        // rule 9
    ;

/* Path filter — precedence from lowest to highest:
     or < and < not < atom
*/
f
    : f 'or' f                          // rule 20: lowest precedence
    | f 'and' f                         // rule 19
    | 'not' f                           // rule 21
    | rp '=' StringConstant             // rule 17
    | rp '=' rp                         // rule 15
    | rp 'eq' rp                        // rule 15
    | rp '==' rp                        // rule 16
    | rp 'is' rp                        // rule 16
    | rp                                // rule 14
    | '(' f ')'                         // rule 18
    ;

/* Tokens */
StringConstant : '"' (~["])* '"'
               | '\'' (~['])* '\''
               ;

NAME : [a-zA-Z_] [A-Za-z0-9_\-]* ;

WS : [ \t\r\n]+ -> skip ;
