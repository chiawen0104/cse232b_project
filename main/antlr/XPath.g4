grammar Exp;

/* This will be the entry point of our parser. */
eval
    :    additionExp
    ;

/* Addition and subtraction have the lowest precedence. */
additionExp
    :    multiplyExp 
         ( '+' multiplyExp 
         | '-' multiplyExp
         )* 
    ;

/* Multiplication and division have a higher precedence. */
multiplyExp
    :    atomExp
         ( '*' atomExp 
         | '/' atomExp
         )* 
    ;

/* An expression atom is the smallest part of an expression: a number. Or 
   when we encounter parenthesis, we're making a recursive call back to the
   rule 'additionExp'. As you can see, an 'atomExp' has the highest precedence. */
atomExp
    :    Number
    |    '(' additionExp ')'
    ;

/* A number: can be an integer value, or a decimal value */
Number
    :    ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

/* We're going to ignore all white space characters */
WS  
    :   (' ' | '\t' | '\r'| '\n') -> skip
    ;

ap
    : 'doc' '(' fileName ')' '/' rp
    | 'doc' '(' fileName ')' '//' rp
    ;
    
fileName
    : STRING
    ;
STRING : [/a-zA-Z_0-9]+ ;

rp
    : TAGNAME
    | '*'
    | '.'
    | '..'
    | 'text' '(' ')'
    | '@' ATTRNAME
    | '(' rp ')'
    | rp '/' rp
    | rp '//' rp
    | rp '[' f ']'
    | rp ',' rp
    ;

f
    : rp
    | rp '=' rp
    | rp 'eq' rp
    | rp '==' rp
    | rp 'is' rp
    | rp '=' STRING
    | '(' f ')'
    | f 'and' f
    | f 'or' f
    | 'not' f
    ;

TAGNAME  : [a-zA-Z_] [A-Za-z0-9_]* ;
ATTRNAME : [a-zA-Z_] [A-Za-z0-9_]* ;
