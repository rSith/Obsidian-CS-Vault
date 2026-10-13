/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.b;

/**
 *
 * @author Piyumi
 */
public class AB {

    /**
     * overflow
     */
    public static void main(String[] args) {
        // TODO code application logic here
        float a = 3.4e38f;   // very large float value
        float b = a * 10;    // exceeds float range

        System.out.println("Value after multiplication: " + b);
    }
    
    /**
     * underflow
//     */
//    public static void main(String[] args) {
//
//        float a = 1.0e-45f;   // very small number
//        float b = a / 10;     // makes it even smaller
//
//        System.out.println("Value after division: " + b);
//    }
}
