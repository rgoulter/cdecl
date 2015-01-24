## CDecl

"Inspired by" [CDecl](cdecl.org)
[also](http://www.ibiblio.org/pub/linux/devel/lang/c/), since C's declaration
syntax is more compiler-friendly than human-friendly.

CDecl translates declarations in C from "gibberish" to English. e.g.:  
`int *(*x)[3];` becomes `x is pointer to array 3 of pointer to int`.  

### Implementation

The implementation is fairly straightforward, and involves constructing a
"gibberish to English" phrase by visiting nodes of an Abstract Syntax Tree,
which comes from a Parser generated with [Antlr 4](http://www.antlr.org/)
(using the C grammar from [here](https://github.com/antlr/grammars-v4)).

I can recommend [The Definitive ANTLR 4
Reference](https://pragprog.com/book/tpantlr2/the-definitive-antlr-4-reference)
if you're curious about ANTLR, or for a friendly introduction to building
Programming Language applications.

### Current Limitations

This doesn't do the "English to gibberish" declaration (yet), although the
"English" in the referenced CDecl programs is somewhat rigid at any rate.

Just like the original CDecl, certain declarations don't work. e.g.:  
`struct { int a; } x;`  

This program doesn't give a warning for (semantically) invalid declarations,
e.g.:  
`int foo(int)[3]`
