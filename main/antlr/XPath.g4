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
    | 'text()'
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

TAGNAME  : [a-zA-Z_] [A-Za-z0-9_-]* ;
ATTRNAME : [a-zA-Z_] [A-Za-z0-9_]* ;
xq
    : VAR
    | STRING
    | ap
    | '(' xq ')'
    | xq ',' xq
    | xq '/' rp
    | xq '//' rp
    | '<' TAGNAME '>' '{' xq '}' '</' TAGNAME '>'
    | '<' TAGNAME '>' STRING '</' TAGNAME '>'
    | forClause letClause? whereClause? returnClause
    | letClause xq
    | joinExpr
    ;
joinExpr
    : 'join' '(' xq ',' xq ',' '[' joinAttrs ']' ',' '[' joinAttrs ']' ')'
    ;

joinAttrs
    : (TAGNAME ( ',' TAGNAME )*)?
    ;
forClause
    : 'for' varRepeat1 ( ',' varRepeat1 )*
    ;
varRepeat1
    :  VAR 'in' xq
    | VAR 'in' path
    ;
letClause
    :  'let' varRepeat2 (',' varRepeat2)*
    ;
varRepeat2
    : VAR ':=' xq
    | VAR '=' xq
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

xquery
    : 'for' varRepeat3 ( ',' varRepeat3 )* 'where' cond2 'return' returnexpr 
    ;
varRepeat3
    :  VAR 'in' path
    ;
path
    : 'doc' '(' fileName ')' ( sepRepeat )* ( '//' | '/' ) TAGNAME
    |  VAR ( sepRepeat )* ( '//' | '/' ) TAGNAME
    | 'doc' '(' fileName ')' ( sepRepeat )* ( '//' | '/' ) 'text()'
    | VAR ( sepRepeat )* ( '//' | '/' ) 'text()'
    ;
sepRepeat
    : ( '//' | '/' ) TAGNAME
    ;
returnexpr 
    : VAR
    | returnexpr ',' returnexpr
    | '<' TAGNAME '>' '{' returnexpr '}' '</' TAGNAME '>'
    | '<' TAGNAME '>' STRING '</' TAGNAME '>'
    | path
    ;
cond2
    : VAR 'eq' VAR
    | VAR 'eq' STRING
    | STRING 'eq' VAR
    | STRING 'eq' STRING
    | VAR '=' VAR
    | VAR '=' STRING
    | STRING '=' VAR
    | STRING '=' STRING
    | cond2 'and' cond2
    ;