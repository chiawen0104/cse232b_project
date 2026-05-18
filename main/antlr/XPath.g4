grammar XPath;

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
    | 'document' '(' fileName ')' '//' rp
    | 'document' '(' fileName ')' '/' rp
    ;
    
fileName
    : STRING
    ;
STRING  : '"' (~["])* '"'
        | '\'' (~['])* '\''
        ; 
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
xq
    : VAR
    | STRING
    | ap
    | '(' xq ')'
    | xq ',' xq
    | xq '/' rp
    | xq '//' rp
    | LT TAGNAME GT LBRACE xq RBRACE LT SLASH TAGNAME GT
    | forClause letClause? whereClause? returnClause
    | letClause xq
    ;
content
    : xq?
    ;
COMMA : ',';
LT    : '<';
GT    : '>';
SLASH : '/';
LBRACE : '{';
RBRACE : '}';
forClause
    : 'for' varRepeat1 ( ',' varRepeat1 )*
    ;
varRepeat1
    :  VAR 'in' xq
    ;
letClause
    :  'let' varRepeat2 (',' varRepeat2)*
    ;
varRepeat2
    : VAR ':=' xq
    ;

whereClause
    : 'where' cond
    ;
returnClause
    : 'return' xq
    ;
cond
    : xq '=' xq
    | xq 'eq' xq
    | xq '==' xq
    | xq 'is' xq
    | 'empty' '(' xq ')'
    | 'some' varRepeat1 (',' varRepeat1)* 'satisfies' cond
    | '(' cond ')'
    | cond 'and' cond
    | cond 'or' cond
    | 'not' cond
    ;
VAR : '$' [a-zA-Z_][a-zA-Z0-9_]* 
    ;
