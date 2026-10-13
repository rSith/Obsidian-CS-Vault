---
type: past-paper
course: CMIS 3142
lesson: L01
status: complete
tags: [cmis3142, past-paper, cmis3142/L01]
---
# PP 01 · Floating Point Numbers (IEEE 754): Past-Paper Questions

> [!info] Lesson: [[01.00 Floating Point Numbers]] · Summary: [[01.99 Summary - Floating Point Numbers]] · [[CMIS 3142 Past Paper Mapping]]
> **Q1 of every paper is on this lesson.** Priority 🔴.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023 | 1(a) | Convert 654, −456, 512, −128 to IEEE 754 single | 8 |
| 2023 | 1(b) | Difference between single and double precision | 4 |
| 2023 | 1(c) | Java: decimal → IEEE 754 double | 8 |
| 2023 | 1(d) | Java method: double → single | 5 |
| 2024 | 1(a) | Range of single and double precision | – |
| 2024 | 1(b) | Normalised vs denormalised numbers | – |
| 2024 | 1(c) | Java: decimal ↔ IEEE 754 double | – |
| 2024 | 1(d) | Java: single ↔ double, with assumptions | – |
| 2025 | 1(a) | Overflow and underflow | – |
| 2025 | 1(b) | Special feature of denormalised numbers | – |
| 2025 | 1(c) | Convert 256, 1023, 364 to IEEE 754 single | – |
| 2025 | 1(d) | Java: octal ↔ hexadecimal | – |
| 2026 | 1(a) | Biased exponent; why bias is used | 10 |
| 2026 | 1(b) | IEEE single bits → decimal (two patterns) | 10 |
| 2026 | 1(c) | −13.625 in IEEE 754 double | 5 |
| 2026 | 1(d) | Special values ±0, ±∞, NaN and their use | 10 |
| 2026 | 1(e) | Java: decimal → IEEE 754 double | 15 |

(The 2024 and 2025 papers print no marks.)

---

## 2023 · Q1(a) (8 marks)
> **Question:** Convert the following decimal numbers into IEEE 754 single precision format. i. 654 ii. −456 iii. 512 iv. −128

**Lesson:** [[01.03 IEEE 754 Single Precision]] · [[01.01 Number System Conversions]]

### Expected answer
Rule: $(-1)^S\times1.F\times2^{E-127}$; S = 1 bit, E = 8 bits, F = 23 bits.

**(i) 654**
- $S = 0$; $654 = 512 + 128 + 8 + 4 + 2 = 1010001110_2$
- $= 1.010001110\times2^9$ → $E = 9 + 127 = 136 = 10001000_2$
- $F = 010001110\,00000000000000$
- **`0 10001000 01000111000000000000000`** = `0x44238000`

**(ii) −456**
- $S = 1$; $456 = 256 + 128 + 64 + 8 = 111001000_2$
- $= 1.11001000\times2^8$ → $E = 8 + 127 = 135 = 10000111_2$
- $F = 11001000\,000000000000000$
- **`1 10000111 11001000000000000000000`** = `0xC3E40000`

**(iii) 512**
- $S = 0$; $512 = 2^9 = 1000000000_2 = 1.0\times2^9$ → $E = 136 = 10001000_2$; $F$ = all zeros
- **`0 10001000 00000000000000000000000`** = `0x44000000`

**(iv) −128**
- $S = 1$; $128 = 2^7 = 1.0\times2^7$ → $E = 7 + 127 = 134 = 10000110_2$; $F$ = all zeros
- **`1 10000110 00000000000000000000000`** = `0xC3000000`

### Key points to include
- Sign bit, binary conversion, normalised form, $E = e + 127$, fraction padded to 23 bits **on the right**.
- Powers of 2 have an all-zero fraction (hidden 1 only).

### How to approach this question
2 marks each: show the 5 labelled steps for each number. Learn the powers of 2 so the binary conversion is quick.

---

## 2023 · Q1(b) (4 marks)
> **Question:** Describe the difference between the IEEE 754 single precision and IEEE 754 double precision.

**Lesson:** [[01.05 IEEE 754 Double Precision]]

### Expected answer
| | Single | Double |
|---|---|---|
| Size | 32 bits | 64 bits |
| Sign / exponent / fraction | 1 / 8 / 23 | 1 / 11 / 52 |
| Bias | 127 | 1023 |
| Value | $(-1)^S\,1.F\,2^{E-127}$ | $(-1)^S\,1.F\,2^{E-1023}$ |
| Range (normalised) | $\approx1.18\times10^{-38}$ to $3.4\times10^{38}$ | $\approx2.2\times10^{-308}$ to $1.8\times10^{308}$ |
| Precision | 24 bits (≈ 7 decimal digits) | 53 bits (≈ 16 decimal digits) |

More exponent bits → greater **range**; more fraction bits → greater **accuracy**. Double uses twice the memory.

### Key points to include
- Bit layout + bias (essential), range and precision consequence, memory cost.

### How to approach this question
4 marks ≈ 4 differences. A table is the fastest clear answer.

---

## 2023 · Q1(c) (8 marks) · 2024 · Q1(c) · 2026 · Q1(e) (15 marks)
> **2023 Q1(c):** Write a Java program to convert a given decimal number into IEEE 754 double precision notation.
> **2024 Q1(c):** Write a Java program to convert a given decimal number into the IEEE 754 double precision format and vice versa.
> **2026 Q1(e):** Write a Java program to convert a given decimal number in IEEE 754 double-precision number.

**Lesson:** [[01.08 Java Programs for IEEE 754]] (full, tested program)

### Expected answer (structure)
1. Read the decimal number; **sign bit** = 1 if negative; work with $|x|$.
2. **Integer part → binary** by repeated division by 2.
3. **Fraction → binary** by repeated multiplication by 2 (stop at 0 or after enough bits).
4. **Normalise**: if integer part > 0, $e$ = (number of integer bits − 1); else $e = -(\text{position of first 1})$. Mantissa bits = bits after the leading 1.
5. **Exponent** $E = e + 1023$ as 11 bits.
6. **Fraction**: pad/cut the mantissa to 52 bits.
7. Print `S E F` (optionally check with `Double.doubleToLongBits`).
8. *Vice versa (2024):* split the 64 bits; $E$ → integer; fraction $= \sum F_i2^{-i}$; value $= (-1)^S(1 + \text{fraction})2^{E-1023}$ (handle $E = 0$ denormal, $E = 2047$ ∞/NaN).

Core code (see the linked note for the complete program):
```java
String sign = (x < 0) ? "1" : "0";  x = Math.abs(x);
long intPart = (long) x;  double frac = x - intPart;
String intBits = Long.toBinaryString(intPart);
StringBuilder fracBits = new StringBuilder();
while (frac > 0 && fracBits.length() < 1100) {
    frac *= 2;
    if (frac >= 1) { fracBits.append('1'); frac -= 1; } else fracBits.append('0');
}
int e; String m;
if (intPart > 0) { e = intBits.length() - 1; m = intBits.substring(1) + fracBits; }
else { int p = fracBits.indexOf("1"); e = -(p + 1); m = fracBits.substring(p + 1); }
String E = String.format("%11s", Integer.toBinaryString(e + 1023)).replace(' ', '0');
m = (m.length() < 52) ? m + "0".repeat(52 - m.length()) : m.substring(0, 52);
System.out.println(sign + " " + E + " " + m);
```

### Key points to include
- Comments naming each IEEE step; bias **1023**, **11** and **52** bits; zero handled separately.
- For 2024, a separate decode method.

### How to approach this question
Write the steps as comments first, then fill in code. 15 marks (2026) rewards a complete program with input/output and a check.

---

## 2023 · Q1(d) (5 marks) · 2024 · Q1(d)
> **2023 Q1(d):** Write a Java method to convert IEEE 754 double precision notation into IEEE 754 single precision notation.
> **2024 Q1(d):** Write a Java program to convert the IEEE 754 single precision format to IEEE 754 double precision format and vice versa. Mention the assumptions, if any.

**Lesson:** [[01.08 Java Programs for IEEE 754]] §2 · [[01.07 Ranges, Overflow and Underflow]]

### Expected answer
**Assumptions:** input is a valid bit string; if the true exponent $e > 127$ → **overflow** → ±∞; if $e < -126$ → **underflow** → ±0 (no denormals produced); the extra 29 fraction bits are **chopped**, not rounded (precision loss); ±0, ±∞, NaN map to the same special values; single → double is always exact.

```java
static String doubleToSingle(String d) {
    d = d.replace(" ", "");
    String S = d.substring(0, 1);
    int Ed = Integer.parseInt(d.substring(1, 12), 2);
    String Fd = d.substring(12);                                     // 52 bits
    if (Ed == 2047) return S + " 11111111 " + (Fd.contains("1") ? "1" + "0".repeat(22) : "0".repeat(23));
    if (Ed == 0)    return S + " 00000000 " + "0".repeat(23);
    int e = Ed - 1023;
    if (e > 127)  return S + " 11111111 " + "0".repeat(23);           // overflow
    if (e < -126) return S + " 00000000 " + "0".repeat(23);           // underflow
    String Es = String.format("%8s", Integer.toBinaryString(e + 127)).replace(' ', '0');
    return S + " " + Es + " " + Fd.substring(0, 23);                 // keep 23 bits
}

static String singleToDouble(String s) {
    s = s.replace(" ", "");
    String S = s.substring(0, 1);
    int Es = Integer.parseInt(s.substring(1, 9), 2);
    String Fs = s.substring(9);
    if (Es == 255) return S + " 11111111111 " + Fs + "0".repeat(29);
    if (Es == 0)   return S + " 00000000000 " + "0".repeat(52);
    String Ed = String.format("%11s", Integer.toBinaryString(Es - 127 + 1023)).replace(' ', '0');
    return S + " " + Ed + " " + Fs + "0".repeat(29);                 // exact
}
```
Example: `1 10000000010 101101 0…0` (−13.625) → `1 10000010 10110100000000000000000`.

### Key points to include
- Re-bias: $E_s = E_d - 1023 + 127$ (and reverse).
- Fraction 52 → 23 (chop/round) or 23 → 52 (append zeros).
- Overflow/underflow and special values as stated assumptions.

### How to approach this question
List assumptions first (2024 asks for them), then the two short methods.

---

## 2024 · Q1(a)
> **Question:** Summarize the range of numbers that can be represented by the IEEE 754 single precision and double precision.

**Lesson:** [[01.07 Ranges, Overflow and Underflow]]

### Expected answer
| | Denormalised | Normalised |
|---|---|---|
| Single | $\pm2^{-149}$ to $(1-2^{-23})\times2^{-126}$ | $\pm2^{-126}$ to $(2-2^{-23})\times2^{127}$ |
| Double | $\pm2^{-1074}$ to $(1-2^{-52})\times2^{-1022}$ | $\pm2^{-1022}$ to $(2-2^{-52})\times2^{1023}$ |

In decimal: single ≈ $1.4\times10^{-45}$ (smallest denormal), $1.18\times10^{-38}$ (smallest normal), $3.4\times10^{38}$ (largest); double ≈ $4.9\times10^{-324}$, $2.2\times10^{-308}$, $1.8\times10^{308}$. Also exactly representable: ±0, ±∞, NaN. Positive and negative ranges are symmetric. Values above the largest overflow to ∞; values below the smallest denormal underflow to 0.

### Key points to include
- The 2×2 table with derivation of one entry (e.g. largest single: $E = 254$, $F$ = all ones).

### How to approach this question
Calculators were **not allowed** in 2024: give the powers-of-2 forms; decimal approximations are a bonus.

---

## 2024 · Q1(b)
> **Question:** Describe the difference between normalized and denormalized numbers.

**Lesson:** [[01.06 Special Values and Denormalized Numbers]]

### Expected answer
| | Normalised | Denormalised |
|---|---|---|
| Exponent field | $1 \le E \le 254$ | $E = 0$ (all zeros), $F \ne 0$ |
| Leading bit | hidden **1** ($1.F$) | **0** ($0.F$), no hidden 1 |
| Value (single) | $(-1)^S\,1.F\,2^{E-127}$ | $(-1)^S\,0.F\,2^{-126}$ |
| Value (double) | $(-1)^S\,1.F\,2^{E-1023}$ | $(-1)^S\,0.F\,2^{-1022}$ |
| Magnitude | $2^{-126}$ … $\approx3.4\times10^{38}$ | $2^{-149}$ … just below $2^{-126}$ |
| Precision | full 24 bits | reduced (leading zeros in F) |
| Purpose | ordinary numbers | numbers very close to 0 (gradual underflow); zero follows this rule |

### How to approach this question
Table + one example of each (e.g. 0.75 normalised; smallest denormal `0 00000000 0…01` = $2^{-149}$).

---

## 2025 · Q1(a)
> **Question:** Describe overflow and underflow when representing a floating point number using IEEE 754 format.

**Lesson:** [[01.07 Ranges, Overflow and Underflow]]

### Expected answer
- **Overflow:** the absolute value of a result is too large for the format (above ≈ $3.4\times10^{38}$ single / $1.8\times10^{308}$ double). The result becomes **+∞ or −∞** (E = all 1s, F = 0). Seeing ∞ usually means a programming or algorithm error. Example: `float a = 3.4e38f; a * 10` → `Infinity`.
- **Underflow:** a non-zero result is smaller in magnitude than the smallest representable positive number; the result is set to **0** (via denormalised numbers first, so precision is lost gradually below $2^{-126}$). Usually a warning only and the calculation continues; dangerous if you later divide by it. Example: `1.0e-45f / 10` → `0.0`.

### Key points to include
Definition, resulting value, limits, example, practical effect, for **both**.

---

## 2025 · Q1(b)
> **Question:** What is the special feature in the Denormalized number in the IEEE 754 format?

**Lesson:** [[01.06 Special Values and Denormalized Numbers]]

### Expected answer
A denormalised number has exponent field $E = 0$ and **no hidden 1**: its value is $(-1)^S\times0.F\times2^{-126}$ (single) or $\times2^{-1022}$ (double), with the exponent **fixed** at −126/−1022 instead of $E - \text{bias}$. This lets IEEE 754 represent numbers **very close to zero**, filling the gap between 0 and the smallest normalised number $2^{-126}$ with evenly spaced values (smallest $2^{-149}\approx1.4\times10^{-45}$). Results that are too small lose precision **gradually** instead of jumping to 0: **gradual underflow**. Zero itself follows the denormalised rule ($F = 0$).

---

## 2025 · Q1(c)
> **Question:** Convert the following numbers into IEEE 754 single precision format: i) 256 ii) 1023 iii) 364

**Lesson:** [[01.03 IEEE 754 Single Precision]]

### Expected answer
**(i) 256** $= 2^8 = 1.0\times2^8$; $S = 0$; $E = 8 + 127 = 135 = 10000111$; $F = 0$
**`0 10000111 00000000000000000000000`** = `0x43800000`

**(ii) 1023** $= 1111111111_2$ (ten 1s) $= 1.111111111\times2^9$; $E = 136 = 10001000$; $F = 111111111$ + 14 zeros
**`0 10001000 11111111100000000000000`** = `0x447FC000`

**(iii) 364** $= 256 + 64 + 32 + 8 + 4 = 101101100_2 = 1.01101100\times2^8$; $E = 135 = 10000111$; $F = 01101100$ + 15 zeros
**`0 10000111 01101100000000000000000`** = `0x43B60000`

### How to approach this question
$1023 = 1024 - 1$ is all ones: a quick way to get its binary.

---

## 2025 · Q1(d)
> **Question:** Write a Java program to convert an octal number to hexa-decimal number and vice versa.

**Lesson:** [[01.01 Number System Conversions]] §3.5 and §4

### Expected answer
Convert via decimal (or via binary: 1 octal digit = 3 bits, 1 hex digit = 4 bits).
```java
import java.util.Scanner;

public class OctalHex {
    static final String DIGITS = "0123456789ABCDEF";

    static int toDecimal(String s, int base) {          // positional value (Horner)
        int value = 0;
        for (char c : s.toUpperCase().toCharArray()) value = value * base + DIGITS.indexOf(c);
        return value;
    }

    static String fromDecimal(int n, int base) {         // repeated division
        if (n == 0) return "0";
        String r = "";
        while (n > 0) { r = DIGITS.charAt(n % base) + r; n /= base; }
        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Octal number: ");
        String oct = sc.next();
        System.out.println("Hexadecimal = " + fromDecimal(toDecimal(oct, 8), 16));
        System.out.print("Hexadecimal number: ");
        String hex = sc.next();
        System.out.println("Octal = " + fromDecimal(toDecimal(hex, 16), 8));
    }
}
```
Example: $752_8 \to 490_{10} \to 1EA_{16}$; $1EA_{16} \to 752_8$.

### Key points to include
- Validation idea (digits 0–7 for octal, 0–9/A–F for hex) earns extra credit.

---

## 2026 · Q1(a) (10 marks)
> **Question:** What is meant by biased exponent in IEEE 754 format? Why is bias used?

**Lesson:** [[01.04 Biased Exponent]]

### Expected answer
- **Definition:** IEEE 754 stores the exponent as an unsigned number $E = e + \text{bias}$; the true exponent is $e = E - \text{bias}$. The bias is about midway in the exponent range: **127** for single (8 bits, $E = 0..255$), **1023** for double (11 bits).
- **Example:** $0.75 = 1.1\times2^{-1}$ → $E = -1 + 127 = 126 = 01111110$. Decoding $E = 10000010 = 130$ → $e = 3$.
- **Why:** (1) to represent **negative exponents** without a sign bit or two's complement ("unsigned interpretation minus bias"); (2) floats can be **compared/sorted like unsigned integers** (bigger exponent field = bigger number), simplifying hardware; (3) **zero** is all-zero bits and the extreme patterns (E = 0, E = all 1s) are free for zero/denormals and ∞/NaN.

### How to approach this question
10 marks: definition + both bias values + 2 examples + at least 2 reasons.

---

## 2026 · Q1(b) (10 marks)
> **Question:** Convert the IEEE 754 following single-precision numbers into their decimal equivalent: i) 0 10000010 10100000000000000000000 ii) 1 10011010 10100110000000000000000

**Lesson:** [[01.03 IEEE 754 Single Precision]]

### Expected answer
**(i)** $S = 0$; $E = 10000010_2 = 130$ → $e = 130 - 127 = 3$; $1.F = 1.101_2$
$1.101_2\times2^3 = 1101_2 = 8 + 4 + 1 =$ **13**

**(ii)** $S = 1$ (negative); $E = 10011010_2 = 128 + 16 + 8 + 2 = 154$ → $e = 154 - 127 = 27$; $1.F = 1.1010011_2$
$1.1010011_2\times2^{27} = 11010011_2\times2^{20} = 211\times1\,048\,576 = 221\,249\,536$
Value = **−221 249 536** ($\approx -2.2125\times10^{8}$)

Check: $1.1010011_2 = 1 + 0.5 + 0.125 + 0.015625 + 0.0078125 = 1.6484375$; $1.6484375\times2^{27} = 221\,249\,536$ ✓.

### How to approach this question
Split into 1 | 8 | 23 first; write $e = E - 127$; shift the point $e$ places (or multiply by $2^e$).

---

## 2026 · Q1(c) (5 marks)
> **Question:** Represent the decimal number −13.625 in IEEE 754 double-precision format.

**Lesson:** [[01.05 IEEE 754 Double Precision]]

### Expected answer
1. $S = 1$
2. $13 = 1101_2$, $0.625 = 0.101_2$ → $1101.101_2$
3. $= 1.101101\times2^3$
4. $E = 3 + 1023 = 1026 = 10000000010_2$
5. $F = 101101$ followed by 46 zeros
**`1 10000000010 1011010000000000000000000000000000000000000000000000`** = `0xC02B400000000000`

---

## 2026 · Q1(d) (10 marks)
> **Question:** Explain special values in IEEE 754 (±0, ±∞, NaN) and their use.

**Lesson:** [[01.06 Special Values and Denormalized Numbers]]

### Expected answer
Because the normalised rule always has a hidden 1, some values need **reserved exponent patterns** ($E$ = all 0s and $E$ = all 1s):

| Value | S | E | F | Use |
|---|---|---|---|---|
| +0 / −0 | 0 / 1 | 00…0 | 00…0 | Represent zero (impossible with hidden 1). Signed zero keeps the sign of a value that underflowed and gives $1/{+0} = +\infty$, $1/{-0} = -\infty$; $+0 = -0$ in comparisons. |
| +∞ / −∞ | 0 / 1 | 11…1 | 00…0 | Result of **overflow** or division of a non-zero number by 0; lets calculations continue ($\infty + 5 = \infty$, $1/\infty = 0$); signals a probable error. |
| NaN | 0 or 1 | 11…1 | ≠ 0 | Result of **invalid** operations: $0/0$, $\infty - \infty$, $0\times\infty$, $\sqrt{-1}$; **propagates** through later calculations so the error is visible; NaN ≠ NaN (test with `Double.isNaN`). |

### How to approach this question
For each value: bit pattern, when it arises, why it is useful. A table + one example per row.
