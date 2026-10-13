package javaapplication26;

import java.util.Scanner;

public class JavaApplication26 {

    // Convert integer part to binary
    static String intToBinary(int n) {
        if (n == 0) return "0";

        String binary = "";
        while (n > 0) {
            binary = (n % 2) + binary;
            n /= 2;
        }
        return binary;
    }

    // Convert fractional part to binary
    static String fractionToBinary(float fraction) {
        String binary = "";
        int count = 0;

        while (fraction > 0 && count < 25) {
            fraction *= 2;
            if (fraction >= 1) {
                binary += "1";
                fraction -= 1;
            } else {
                binary += "0";
            }
            count++;
        }
        return binary;
    }

    // Pad binary with zeros on the left
    static String padLeft(String s, int length) {
        while (s.length() < length)
            s = "0" + s;
        return s;
    }

    // Pad binary with zeros on the right
    static String padRight(String s, int length) {
        while (s.length() < length)
            s += "0";
        return s;
    }

    // Decimal → IEEE 754
    static String decimalToIEEE754(float num) {

        // Handle zero
        if (num == 0.0f) {
            return "00000000000000000000000000000000";
        }

        int sign = (num < 0) ? 1 : 0;
        num = Math.abs(num);

        int integerPart = (int) num;
        float fractionPart = num - integerPart;

        String intBinary = intToBinary(integerPart);
        String fracBinary = fractionToBinary(fractionPart);

        int exponent;
        String mantissa;

        if (integerPart != 0) {
            exponent = intBinary.length() - 1;
            mantissa = intBinary.substring(1) + fracBinary;
        } else {
            int index = fracBinary.indexOf('1');
            exponent = -(index + 1);
            mantissa = fracBinary.substring(index + 1);
        }

        int biasedExponent = exponent + 127;
        String exponentBits = padLeft(intToBinary(biasedExponent), 8);

        mantissa = padRight(mantissa, 23);
        mantissa = mantissa.substring(0, 23);

        return sign + exponentBits + mantissa;
    }

    // IEEE 754 → Decimal
    static float ieee754ToDecimal(String bits) {

        if (bits.length() != 32) {
            System.out.println("Invalid IEEE 754 input.");
            return 0;
        }

        int sign = bits.charAt(0) - '0';
        String exponentBits = bits.substring(1, 9);
        String mantissaBits = bits.substring(9);

        int exponentValue = 0;
        for (int i = 0; i < exponentBits.length(); i++) {
            exponentValue = exponentValue * 2 + (exponentBits.charAt(i) - '0');
        }

        int exponent = exponentValue - 127;

        float mantissa = 1.0f;
        float place = 0.5f;

        for (int i = 0; i < mantissaBits.length(); i++) {
            if (mantissaBits.charAt(i) == '1') {
                mantissa += place;
            }
            place /= 2;
        }

        float value = (float) (mantissa * Math.pow(2, exponent));
        return (sign == 1) ? -value : value;
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("A. Decimal into IEEE 754 (Single Precision)");
        System.out.println("B. IEEE 754 → Decimal");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Enter decimal number: ");
            float num = sc.nextFloat();
            System.out.println("IEEE 754 format: " + decimalToIEEE754(num));
        }
        else if (choice == 2 ) {
            System.out.print("Enter 32-bit IEEE 754 binary: ");
            String bits = sc.next();
            System.out.println("Decimal value: " + ieee754ToDecimal(bits));
        }

        sc.close();
    }
}



   
