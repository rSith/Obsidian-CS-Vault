---
type: past-paper
course: CMIS 3134
lesson: L6
status: complete
tags: [cmis3134, past-paper, cmis3134/L6]
---
# PP 06 · Lexical Analysis: Past-Paper Questions

> [!info] Lesson: [[06.05 Tokenizing a C Program]] · [[06.02 Tokens, Lexemes and Patterns]] · Summary: [[06.99 Summary - Lexical Analysis]]
> Related front-end theory questions (lexical vs syntax analysis, error detection) are in [[PP 01.2 - Phases of a Compiler]].

| Paper | Q | Program | Marks |
|---|---|---|---|
| 2019/20 | 1(b) | Program 1 (`int main()`, max of a and b) | 20 |
| 2020/21 | 1(b) | Program 1 (same) | 20 |
| 2021/22 | 1(c) | Program 2 (`void main()`, age) | 20 |
| 2022/23 | 1(d) | Program 2 (same) | 40 |

Not examined in the 5 papers: longest match rule, rule priority, special-symbols table.

---

## 2019/20 · Q1(b) and 2020/21 · Q1(b) (20 marks)
> **Question:** Perform a lexical analyzer to generate tokens of the following program.
> ```c
> #include <stdio.h>
>
> int main()
> {
>     /* (comment) */
>     int a = 20, b = 30;
>
>     if(a < b){
>         printf("Max is %d", b);
>         return (b);
>
>     }else{
>         printf("Max is %d", a);
>         return (b);
>     }
> }
> ```

### Expected answer
- The preprocessor line `#include <stdio.h>` is handled **before** lexical analysis; whitespace and the comment produce **no tokens**.
- Tokens in order, as `<token-name, attribute-value>`:

```text
<keyword,int> <identifier,main> <punctuator,(> <punctuator,)> <punctuator,{>
<keyword,int> <identifier,a> <operator,=> <constant,20> <punctuator,,> <identifier,b> <operator,=> <constant,30> <punctuator,;>
<keyword,if> <punctuator,(> <identifier,a> <operator,<> <identifier,b> <punctuator,)> <punctuator,{>
<identifier,printf> <punctuator,(> <literal,"Max is %d"> <punctuator,,> <identifier,b> <punctuator,)> <punctuator,;>
<keyword,return> <punctuator,(> <identifier,b> <punctuator,)> <punctuator,;>
<punctuator,}> <keyword,else> <punctuator,{>
<identifier,printf> <punctuator,(> <literal,"Max is %d"> <punctuator,,> <identifier,a> <punctuator,)> <punctuator,;>
<keyword,return> <punctuator,(> <identifier,b> <punctuator,)> <punctuator,;>
<punctuator,}>
<punctuator,}>
```

**Total: 50 tokens**: keywords 6 (int ×2, if, else, return ×2) · identifiers 11 · operators 3 (=, =, <) · constants 2 (20, 30) · string literals 2 · punctuators 26.
(A numbered Lexeme | Token table is in [[06.05 Tokenizing a C Program]].)

### Key points to include
- Every bracket, comma and semicolon · correct categories · whole string literal = one token · whitespace/comments removed · total.

### How to approach this question
Go line by line. Use a two-column table if it is faster for you.

---

## 2021/22 · Q1(c) (20 marks) and 2022/23 · Q1(d) (40 marks)
> **Question:** Perform a lexical analyzer to generate tokens of the following program.
> ```c
> #include <stdio.h>
> void main ()
> {
>     int age;
>     age= 25;
>
>     age = age + 5;  // age after 5 years
>     printf("Age after 5 years: %d\n",age);
>
>     age = age + 5;  // age after 10 years
>     printf("Age after 10 years: %d\n",age);
> }
> ```

### Expected answer
- `#include <stdio.h>` handled by the preprocessor; the `//` comments and all whitespace are removed and give no tokens.

```text
<keyword,void> <identifier,main> <punctuator,(> <punctuator,)> <punctuator,{>
<keyword,int> <identifier,age> <punctuator,;>
<identifier,age> <operator,=> <constant,25> <punctuator,;>
<identifier,age> <operator,=> <identifier,age> <operator,+> <constant,5> <punctuator,;>
<identifier,printf> <punctuator,(> <literal,"Age after 5 years: %d\n"> <punctuator,,> <identifier,age> <punctuator,)> <punctuator,;>
<identifier,age> <operator,=> <identifier,age> <operator,+> <constant,5> <punctuator,;>
<identifier,printf> <punctuator,(> <literal,"Age after 10 years: %d\n"> <punctuator,,> <identifier,age> <punctuator,)> <punctuator,;>
<punctuator,}>
```

**Total: 39 tokens**: keywords 2 (void, int) · identifiers 11 (main, age ×8, printf ×2) · operators 5 · constants 3 (25, 5, 5) · string literals 2 · punctuators 16.

**For the 40-mark version (2022/23), also explain briefly:**
- the lexical analyzer is the first phase; it reads the character stream, groups characters into **lexemes**, and matches them against **patterns** (regular expressions) to produce **tokens**;
- token categories used: keyword, identifier, constant, literal, operator, punctuator;
- whitespace and comments are discarded; `printf` is an identifier (library function), not a keyword; `\n` is part of the string literal;
- the longest match rule ensures e.g. `age` is read as one identifier.

### Key points to include
- All 39 tokens · categories · removed comments · totals · (40 marks) a short explanation of the process.
