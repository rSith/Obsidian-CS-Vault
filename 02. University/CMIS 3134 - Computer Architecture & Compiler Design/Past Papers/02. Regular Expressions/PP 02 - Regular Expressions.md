---
type: past-paper
course: CMIS 3134
lesson: L2
status: complete
tags: [cmis3134, past-paper, cmis3134/L2]
---
# PP 02 · Regular Expressions: Past-Paper Questions

> [!info] Lesson: [[02.00 Regular Expressions]] · Summary: [[02.99 Summary - Regular Expressions]] · [[CMIS 3134 Past Paper Mapping]]

| Paper | Q | Topic | Marks | Repeated? |
|---|---|---|---|---|
| 2019/20 | 2(c) | Example strings for 4 REs | 20 | |
| 2019/20 | 2(d) | Prove (0110 + 01)(10)\* = 01(10)\* | 40 | answered in [[PP 08 - Arden's Theorem and RE-FA Conversion]] |
| 2020/21 | 2(b) | Regular grammar vs regular language | 20 | ≈ 2021/22 Q3(a), 2022/23 Q3(a) |
| 2020/21 | 2(c) | Valid tokens x\*, x?, [0-5], (5-9) | 20 | same pattern 2021/22 Q3(b), 2022/23 Q3(b) |
| 2020/21 | 2(d) | Example strings + output, 4 REs | 40 | |
| 2020/21 | 6(a) | Is "1101" accepted by A, B or both? | 20 | |
| 2021/22 | 3(a) | RE; regular grammar & language | 20 | = 2022/23 Q3(a) |
| 2021/22 | 3(b) | Valid tokens l⁺, l\*, [a–g), (M–W) | 20 | |
| 2021/22 | 3(c) | Decimal, identifier, NIC, email | 20 | = 2022/23 Q3(c) |
| 2021/22 | 3(d) | Construct REs; strings for (a+ab)\*, b\*(aaa)\*b\* | 40 | ≈ 2022/23 Q3(d) |
| 2022/23 | 3(a)–(c) | (as 2021/22, tokens a⁺, s\*, [1–7), (A–T)) | 20 each | |
| 2022/23 | 3(d) | Construct REs; strings for (a+b)\*, b\*(aaa)\*b\* | 40 | |
| 2023/24 | 3(a) | RE and its importance for lexical analyzers | 20 | |
| 2023/24 | 3(b) | Union, concatenation, L\*, L⁺ | 20 | |
| 2023/24 | 3(c) | Binary number, mobile no., reg. no., password | 20 | |
| 2023/24 | 3(d) | (aa+ab+ba+bb)\*: language and simpler RE | 40 | |

---

## 2019/20 · Q2(c) (20 marks)
> **Question:** Provide example strings that can be generated from regular Expressions given below: i. (0 + 10\*) ii. (0+1)\*011 iii. (ε + x)(y + ε) iv. (aa)\*(bb)\*b

**Lesson:** [[02.06 Reading and Writing Regular Expressions]]

### Expected answer
| RE | Example strings | Language in words |
|---|---|---|
| (0 + 10\*) | 0, 1, 10, 100, 1000 | either the single symbol 0, or a 1 followed by any number of 0s (\* applies only to the 0) |
| (0+1)\*011 | 011, 0011, 1011, 00011, 11011 | all binary strings ending with 011 |
| (ε + x)(y + ε) | ε, x, y, xy | an optional x followed by an optional y (exactly these 4 strings) |
| (aa)\*(bb)\*b | b, aab, bbb, aabbb, aaaab | an even number of a's followed by an odd number of b's |

### Key points to include
- 4–5 strings each **and** a one-line description · mention ε where it is included.

### How to approach this question
Watch precedence: in `10*` the star belongs to 0 only.

---

## 2020/21 · Q2(b) (20 marks)
> **Question:** Differentiate between **Regular Grammar** and **Regular Language**.

**Lesson:** [[02.01 Regular Expressions and Lexical Analysis]]

### Expected answer
- **Regular grammar:** the grammar defined by regular expressions, i.e. a finite set of production rules (variables, terminals, start symbol, productions) that generate strings. It may be right linear (A → xB) or left linear (A → Bx).
- **Regular language:** the language defined by a regular grammar, i.e. the set of all strings the grammar generates.

| Regular grammar | Regular language |
|---|---|
| A specification / generator (rules) | The result: a set of strings |
| Always finite (finite number of rules) | Can be finite or infinite |
| e.g. S → aS \| b | L = {b, ab, aab, …} = a\*b |

Every regular language can also be described by a regular expression and recognised by a finite automaton.

### Key points to include
- Both definitions (lecture wording) · grammar = rules, language = strings · example.

### How to approach this question
Two definitions, a small table and one worked example.

---

## 2021/22 · Q3(a) and 2022/23 · Q3(a) (20 marks)
> **Question:** What is meant by a **Regular Expression**? How do **regular grammar** and **regular language** differ from a regular expression?

**Lesson:** [[02.01 Regular Expressions and Lexical Analysis]]

### Expected answer
**Regular expression:** an important notation for specifying **patterns**; each pattern matches a set of strings, so an RE is a **name for a set of strings**. It is defined recursively from ε, φ and symbols using union (+), concatenation (.) and Kleene closure (\*).

| Term | What it is | Example |
|---|---|---|
| Regular expression | The notation (pattern) | a\*b |
| Regular grammar | The grammar defined by regular expressions (production rules) | S → aS \| b |
| Regular language | The language (set of strings) defined by a regular grammar | {b, ab, aab, …} |

**Difference:** the RE is the *notation*; the regular grammar is the *rule set* it defines; the regular language is the *set of strings* generated. In compilers, token patterns are written as REs so the lexical analyzer can recognise the regular language of valid tokens.

### Key points to include
- RE definition · 3-way distinction · one example carried through.

### How to approach this question
Define the RE, then use a 3-row table.

---

## 2023/24 · Q3(a) (20 marks)
> **Question:** What is meant by a **Regular Expression**? Why are regular expressions important in the design of a **lexical analyzer**?

**Lesson:** [[02.01 Regular Expressions and Lexical Analysis]]

### Expected answer
**Definition:** a notation for specifying patterns; each RE denotes a set of strings; built recursively using union, concatenation and Kleene closure.

**Importance for a lexical analyzer:**
1. The lexical analyzer must identify a finite set of valid tokens/lexemes by searching for the **patterns** defined by the language rules; REs are how those patterns are written (e.g. `Identifier = (letter)(letter|digit)*`, `Decimal = (sign)?(digit)+`).
2. An RE describes an **infinite set** (all identifiers) with a short **finite** pattern.
3. REs give a **precise, unambiguous** specification of each token class.
4. REs can be converted to **finite automata**, which verify/recognise tokens efficiently; scanners can be generated automatically.
5. Regular languages are easy to understand and have **efficient implementation**.

### Key points to include
- Definition · at least 3–4 reasons with a token example.

### How to approach this question
Definition (≈5 marks) + reasons as bullets (≈15 marks).

---

## 2020/21 · Q2(c) · 2021/22 · Q3(b) · 2022/23 · Q3(b) (20 marks each)
> **Question:** Briefly explain the following **valid tokens** of a language in a regular expression.
> - 2020/21: i. x\* ii. x? iii. [0 - 5] iv. (5 - 9)
> - 2021/22: i. l⁺ ii. l\* iii. [a – g) iv. (M – W)
> - 2022/23: i. a⁺ ii. s\* iii. [1 – 7) iv. (A – T)

**Lesson:** [[02.04 Representing Valid Tokens - Repetition and Ranges]]

### Expected answer
**Rules:** `x*` = zero or more x {ε, x, xx, …}; `x⁺` = one or more {x, xx, …} = x.x\*; `x?` = at most one {ε, x}. In ranges, **square brackets include** the endpoint and **round brackets exclude** it (handwritten notes).

| Token | Meaning | Members / examples |
|---|---|---|
| x\* | zero or more x | ε, x, xx, xxx |
| x? | zero or one x | ε, x |
| [0 - 5] | 0 to 5 inclusive | 0,1,2,3,4,5 |
| (5 - 9) | between 5 and 9, both excluded | 6,7,8 |
| l⁺ | one or more l | l, ll, lll |
| l\* | zero or more l | ε, l, ll |
| [a – g) | a included, g excluded | a,b,c,d,e,f |
| (M – W) | both excluded | N,O,P,Q,R,S,T,U,V |
| a⁺ | one or more a | a, aa, aaa |
| s\* | zero or more s | ε, s, ss |
| [1 – 7) | 1 included, 7 excluded | 1,2,3,4,5,6 |
| (A – T) | both excluded | B,C,…,S |

> [!warning] Mixed brackets
> `[a – g)` and `[1 – 7)` mix brackets. Neither the lecture nor the handwritten notes cover this case. Applying the notes' rule to each end gives the half-open ranges above. If it is a typing error for `[a – g]` / `[1 – 7]`, the answers would be {a,…,g} / {1,…,7}. **State the rule and your reading in the exam.**

### Key points to include
- Meaning + set + example for each · state the bracket rule once · x\* vs x⁺ differ only in ε.

### How to approach this question
Four short entries (≈5 marks each).

---

## 2021/22 · Q3(c) and 2022/23 · Q3(c) (20 marks)
> **Question:** Represent the following language tokens using regular expressions. i. Any Decimal Value ii. Any Identifier iii. Any NIC number that contains x or v at the end. iv. Any Email address that ends with "@gmail.com".

**Lesson:** [[02.05 Regular Expressions for Real-World Tokens]]

### Expected answer
Building blocks: `letter = [a-z] | [A-Z]`, `digit = [0-9]`, `sign = [+ | -]`.

| Token | RE | Example |
|---|---|---|
| Decimal value | `(sign)?(digit)+` | −42, 7, +150 |
| Identifier | `(letter)(letter \| digit)*` (or with `_`) | value1, age |
| NIC (x or v at the end) | `(digit)(digit)(digit)(digit)(digit)(digit)(digit)(digit)(digit)(V \| v \| X \| x)` | 775772341V |
| Email ending @gmail.com | `(letter)(letter \| digit \| _ \| .)* @gmail.com` | ranshitha01@gmail.com |

Assumptions: "decimal value" = signed base-10 integer as in the lecture (a fractional version would be `(sign)?(digit)+(.(digit)+)?`); NIC = old 9-digit format.

### Key points to include
- Building blocks defined · one RE + example per part · assumptions stated.

### How to approach this question
Use the lecture's Decimal and Identifier patterns exactly; the NIC pattern is from the handwritten notes.

---

## 2023/24 · Q3(c) (20 marks)
> **Question:** Construct suitable regular expressions for the following tokens: i. A binary number (only 0s and 1s). ii. A valid Sri Lankan mobile phone number starting with 07 and containing 10 digits. iii. A valid university registration number in the format "YYYY/ABC/NNN". (Example: 2026/FAS/015). iv. A password that: starts with a letter, has at least 6 characters, contains only letters and digits.

**Lesson:** [[02.05 Regular Expressions for Real-World Tokens]]

### Expected answer
`letter = [a-z] | [A-Z]`, `digit = [0-9]`, `L = [A-Z]`

| Token | RE | Example |
|---|---|---|
| Binary number | `(0 + 1)+` | 1011 |
| Mobile number | `07(digit)(digit)(digit)(digit)(digit)(digit)(digit)(digit)` (07 + 8 digits = 10) | 0771234567 |
| Registration number | `(digit)(digit)(digit)(digit)/(L)(L)(L)/(digit)(digit)(digit)` | 2026/FAS/015 |
| Password | `(letter)(letter + digit)(letter + digit)(letter + digit)(letter + digit)(letter + digit)(letter + digit)*` | Abc123xyz |

- Binary uses ⁺ because an empty string is not a number.
- The password has 1 letter + **5 forced** letters/digits (minimum 6) + any more.

### Key points to include
- Exact lengths encoded correctly · literals (07, /) written as is · examples.

### How to approach this question
Break each format into fixed and variable parts; the password's "at least 6" is the part examiners check.

---

## 2020/21 · Q2(d) (40 marks)
> **Question:** Provide example strings and the final output in your words that can be generated below regular Expressions. i. (a+b)( (a+b)(a+b))\* ii. (aa + ab + ba + bb)\* iii. (aa)\* iv. (aa)\*(bb)\*b

**Lesson:** [[02.06 Reading and Writing Regular Expressions]]

### Expected answer
| RE | Example strings | Final output (language) |
|---|---|---|
| (a+b)((a+b)(a+b))\* | a, b, aab, bab, ababa | one symbol followed by any number of pairs: **all strings over {a,b} of odd length** |
| (aa+ab+ba+bb)\* | ε, aa, ab, abba, bbaa | any number of 2-symbol blocks: **all strings over {a,b} of even length**, including ε |
| (aa)\* | ε, aa, aaaa, aaaaaa | strings of a's with an **even number of a's** (including zero) |
| (aa)\*(bb)\*b | b, aab, bbb, aabbb, aaaabbb | **an even number of a's followed by an odd number of b's** |

Note: (i) and (ii) are complements: odd vs even length.

### Key points to include
- Examples + description + a short justification of the structure (≈10 marks each).

### How to approach this question
Explain *why*: "every alternative inside the star has length 2, so…".

---

## 2020/21 · Q6(a) (20 marks)
> **Question:** Consider the following two regular expressions; A. 110\*(0+1) B. 1(0+1)\*101. State whether the string "1101" is acceptable by A, B, or Both. Justify your answer.

**Lesson:** [[02.06 Reading and Writing Regular Expressions]]

### Expected answer
- **A = 1 · 1 · 0\* · (0+1):** 1101 = `1` `1` `0` `1`: the first two 1s match the literals, 0\* takes the single 0, (0+1) takes the final 1. ✅ **Accepted.**
- **B = 1 · (0+1)\* · 101:** 1101 = `1` + ε + `101`: the first 1 matches, (0+1)\* matches the empty string, the last three symbols are 101. ✅ **Accepted.**
- **Answer: Both A and B accept "1101".**
- (Strings that separate them: 1100 ∈ A but ∉ B; 10101 ∈ B but ∉ A.)

### Key points to include
- A symbol-by-symbol match for each RE · verdict.

### How to approach this question
Show the split of the string under each expression.

---

## 2021/22 · Q3(d) and 2022/23 · Q3(d) (40 marks)
> **Question:** i) Construct the regular expression for the following. i. language accepting all the strings that are starting with 1 and ending with 0, over Σ = {0, 1}. ii. language accepting all the strings that are starting and ending with "a" and having any combination of "b"s in between over Σ = {a, b}.
> ii) In your own words, provide example strings and the final output that can be generated by the regular expressions below. 2021/22: i. (a + ab)\* ii. b\* (aaa)\* b\* · 2022/23: i. (a + b)\* ii. b\* (aaa)\* b\*

**Lesson:** [[02.06 Reading and Writing Regular Expressions]]

### Expected answer
**Part (i)**
- Starts with 1, ends with 0: **`1(0 + 1)*0`** (shortest string: 10; examples: 10, 110, 1010).
- Starts and ends with a, any combination of b's in between: **`a b* a`** (shortest: aa; examples: aa, aba, abbba). *(If any mix of a and b were allowed in between: `a(a+b)*a`.)*

**Part (ii)**

| RE | Example strings | Final output |
|---|---|---|
| (a + ab)\* (2021/22) | ε, a, ab, aab, abab, aaba | strings over {a,b} in which every b comes immediately after an a: no string starts with b and there is no "bb" |
| (a + b)\* (2022/23) | ε, a, b, ab, ba, aab | every possible string over {a,b}, including ε |
| b\*(aaa)\*b\* (both) | ε, b, aaa, baaab, bbaaaaaab | any number of b's, then a block of a's whose length is a multiple of 3, then any number of b's |

### Key points to include
- Each RE justified piece by piece · shortest strings · descriptions in words.

### How to approach this question
For constructions, name the fixed start, the free middle and the fixed end.

---

## 2023/24 · Q3(b) (20 marks)
> **Question:** Explain the following operations on languages with examples: i. Union ii. Concatenation iii. Kleene Closure Star (L\*) iv. Kleene Closure Plus (L⁺)

**Lesson:** [[02.02 Operations on Languages]]

### Expected answer
Let L = {a, b}, M = {c}.
- **Union:** $L \cup M = \{s \mid s \in L \text{ or } s \in M\}$ → {a, b, c}.
- **Concatenation:** $LM = \{st \mid s \in L, t \in M\}$ → {ac, bc}.
- **Kleene star:** $L^*$ = zero or more occurrences of L = $L^0 \cup L^1 \cup L^2 \cup \dots$. With L = {a}: {ε, a, aa, aaa, …}. **Includes ε.**
- **Kleene plus:** $L^+$ = one or more occurrences = $L \cdot L^* = L^1 \cup L^2 \cup \dots$. With L = {a}: {a, aa, aaa, …}. **Excludes ε** (when ε ∉ L).

### Key points to include
- Notation + set definition + example for each · star vs plus contrast.

### How to approach this question
Use the same small languages in all four parts.

---

## 2023/24 · Q3(d) (40 marks)
> **Question:** Consider the following regular expression: (aa + ab + ba + bb)\*. i. Describe the language generated by this expression and state the common property shared by all strings in the language. ii. Can this language be described by a simpler expression? Explain with reasoning, and if yes, write and describe an equivalent regular expression.

**Lesson:** [[02.06 Reading and Writing Regular Expressions]] · [[08.01 Regular Expression Identities]]

### Expected answer
**(i)** Each alternative (aa, ab, ba, bb) has length 2, and \* repeats a choice of them zero or more times, so every string is a concatenation of 2-symbol blocks. Since aa, ab, ba, bb are **all** strings of length 2, any even-length string can be formed.
**Language:** all strings over {a, b} of **even length**, including ε. Examples: ε, ab, aabb, abba, bbbaab.
**Common property:** **even length** (|w| = 0, 2, 4, …).

**(ii) Yes.** Using the distributive identity $(P + Q)R = PR + QR$:
$$(a+b)(a+b) = a(a+b) + b(a+b) = aa + ab + ba + bb$$
So $(aa + ab + ba + bb)^* = ((a+b)(a+b))^*$, meaning "zero or more pairs of any two symbols". It is shorter, shows the even-length property directly, and generates the same language.

### Key points to include
- Length-2 blocks argument · ε included · identity named · equivalent RE described.

### How to approach this question
Spend most effort on clear reasoning; the algebraic step with the named identity earns the "explain with reasoning" marks.
