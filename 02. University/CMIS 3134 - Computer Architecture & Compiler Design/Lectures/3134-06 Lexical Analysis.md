---
type: lecture
course: CMIS 3134
lecture: 6
status: stub
priority: Tier 2
tags: [compilers]
aliases: [Lexical Analysis]
---
# Lexical Analysis
> [!info] CMIS 3134 · Lecture 6
> [[CMIS 3134 Course Home]] · Exam prep: [[3134 Topic Map & Past Papers|Topic map]] · Study priority: **Tier 2**
> ← [[3134-05 Moore and Mealy Machines]] | [[3134-07 Syntax Analysis]] →

> [!tip]- Exam focus (5 papers, 2019/20–2023/24)
> - **4 of 5 papers** — *Perform a lexical analyzer to generate tokens* for a small C program (the `void main(){int age; …}` program is reused verbatim).
> - Low priority: longest match rule, rule priority and the special-symbols table (never asked).

> [!question]- Past paper questions on this lecture (4)
> - **2019/20 Q1(b)** · 20 marks — Perform a **lexical analyzer** to generate tokens of the given C program (`int main(){int a=20,b=30; if(a<b)…}`)
> - **2020/21 Q1(b)** · 20 marks — Perform a **lexical analyzer** to generate tokens of the given C program
> - **2021/22 Q1(c)** · 20 marks — Perform a **lexical analyzer** to generate tokens of the given program (`void main(){int age; age=25; …}`)
> - **2022/23 Q1(d)** · 40 marks — Perform a **lexical analyzer** to generate tokens of the given program (`void main(){int age; age=25; …}`)

## Topic checklist
- [ ] **6.1 Lexical Analysis — role** — First phase; takes modified source from preprocessor; breaks syntaxes into a series of tokens; removes whitespace and comments; generates error on invalid token; works closely with syntax analyzer; passes data on demand
- [ ] **6.2 Tokens & Lexemes** — Lexeme = sequence of (alphanumeric) characters in a token; predefined rules per lexeme; rules defined by grammar as a **pattern**; patterns defined by regular expressions
- [ ] **6.3 Token categories** — Keywords, constants, identifiers, strings, numbers, operators, punctuation symbols
- [ ] **6.4 Worked example** — `int value = 100;` → `int` (keyword), `value` (identifier), `=` (operator), `100` (constant), `;` (symbol)
- [ ] **6.5 Specifications of Tokens**
    - [ ] 6.5.1 Alphabets — Binary {0,1}; Hexadecimal {0–9,A–F}; English {a-z, A-Z}
    - [ ] 6.5.2 Strings — Finite sequence of alphabets; length = occurrences; `│Lexical│ = 7`; empty string ε
    - [ ] 6.5.3 Special symbols — Arithmetic `+ - % * /`; Punctuation `, ; . ->`; Assignment `=`; Special assignment `+= /= *= -=`; Comparison `== != < <= > >=`; Preprocessor `#`; Location specifier `&`; Logical `& && │ ││ !`; Shift `>> >>> << <<<`
    - [ ] 6.5.4 Language — Finite set of strings over a finite set of alphabets; set operations apply; describable by regular expressions
- [ ] **6.6 Longest Match Rule** — Scans letter by letter; word completes at whitespace/operator/special symbol; lexeme determined by longest match among available tokens; e.g. `int intvalue;`
- [ ] **6.7 Rule Priority** — Reserved words/keywords take priority over user input

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
