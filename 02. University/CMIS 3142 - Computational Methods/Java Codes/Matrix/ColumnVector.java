/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Piyumi
 */
public class ColumnVector {
    int size;
    int[] data;

    ColumnVector(int size) {
        this.size = size;
        data = new int[size];
    }

    void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }
}

    

