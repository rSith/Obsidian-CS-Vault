import java.util.Scanner;

public class IEEE754Double {

    // ---------- Decimal  -->  IEEE 754 double (64-bit string) ----------
    public static String decimalToIEEE(double x) {
        // Special values
        if (Double.isNaN(x))      return "0" + "11111111111" + "1" + "0".repeat(51);
        if (Double.isInfinite(x)) return (x > 0 ? "0" : "1") + "11111111111" + "0".repeat(52);
        if (x == 0.0)             return (1 / x < 0 ? "1" : "0") + "0".repeat(63);   // +0 / -0

        // Step 1: sign bit
        String sign = (x < 0) ? "1" : "0";
        x = Math.abs(x);

        // Step 2: normalise to 1.xxxx * 2^e   (divide/multiply by 2 = move binary point)
        int e = 0;
        while (x >= 2.0) { x = x / 2; e++; }
        while (x < 1.0)  { x = x * 2; e--; }

        // Step 3: biased exponent (bias = 1023) as 11 bits
        int biased = e + 1023;
        if (biased >= 2047) return sign + "11111111111" + "0".repeat(52); // overflow -> infinity
        if (biased <= 0)    return sign + "0".repeat(63);                 // underflow (assume 0)
        String exponent = toBinary(biased, 11);

        // Step 4: mantissa = fraction part after the hidden 1 (52 bits, x2 method)
        double frac = x - 1.0;
        StringBuilder mantissa = new StringBuilder();
        for (int k = 0; k < 52; k++) {
            frac = frac * 2;
            if (frac >= 1.0) { mantissa.append('1'); frac = frac - 1.0; }
            else             { mantissa.append('0'); }
        }
        return sign + exponent + mantissa;
    }

    // integer -> binary string of fixed length (repeated division by 2)
    static String toBinary(int n, int bits) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < bits; k++) {
            sb.insert(0, n % 2);
            n = n / 2;
        }
        return sb.toString();
    }

    // ---------- IEEE 754 double (64-bit string)  -->  Decimal ----------
    public static double ieeeToDecimal(String bits) {
        bits = bits.replace(" ", "");
        if (bits.length() != 64 || !bits.matches("[01]+"))
            throw new IllegalArgumentException("Need exactly 64 bits (0/1)");

        int    s  = bits.charAt(0) - '0';
        String eS = bits.substring(1, 12);
        String mS = bits.substring(12);

        // exponent bits -> integer
        int E = 0;
        for (int k = 0; k < 11; k++) E = E * 2 + (eS.charAt(k) - '0');

        // mantissa bits -> fraction F = b1*2^-1 + b2*2^-2 + ...
        double F = 0, w = 0.5;
        for (int k = 0; k < 52; k++) {
            if (mS.charAt(k) == '1') F += w;
            w = w / 2;
        }

        double sign = (s == 0) ? 1 : -1;
        if (E == 2047)                                      // all 1s
            return (F == 0) ? sign * Double.POSITIVE_INFINITY : Double.NaN;
        if (E == 0)                                         // denormalised (or zero)
            return sign * F * Math.pow(2, -1022);
        return sign * (1 + F) * Math.pow(2, E - 1023);      // normalised
    }

    // print in S | E | M groups and in hexadecimal
    static void show(String b) {
        System.out.println("Sign     : " + b.charAt(0));
        System.out.println("Exponent : " + b.substring(1, 12));
        System.out.println("Mantissa : " + b.substring(12));
        StringBuilder hex = new StringBuilder();
        for (int k = 0; k < 64; k += 4)
            hex.append(Integer.toHexString(Integer.parseInt(b.substring(k, k + 4), 2)).toUpperCase());
        System.out.println("Hex      : 0x" + hex);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Decimal -> IEEE 754 double");
        System.out.println("2. IEEE 754 double -> Decimal");
        System.out.print("Choice: ");
        int ch = Integer.parseInt(sc.nextLine().trim());

        if (ch == 1) {
            System.out.print("Enter decimal number: ");
            double x = Double.parseDouble(sc.nextLine().trim());
            show(decimalToIEEE(x));
        } else if (ch == 2) {
            System.out.print("Enter 64 bits: ");
            System.out.println("Decimal  : " + ieeeToDecimal(sc.nextLine()));
        } else {
            System.out.println("Invalid choice");
        }
    }
}
