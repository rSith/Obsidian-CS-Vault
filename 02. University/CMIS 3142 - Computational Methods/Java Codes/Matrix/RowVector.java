/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Piyumi
 */
public class RowVector {
    int size;
    int[] data;

    RowVector(int size) {
        this.size = size;
        data = new int[size];
    }

    void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
    
//    FullMatrix toFullMatrix(int r, int c) {
//    FullMatrix fm = new FullMatrix(r, c);
//    int k = 0;
//
//    for (int i = 0; i < r; i++) {
//        for (int j = 0; j < c; j++) {
//            fm.data[i][j] = data[k++];
//        }
//    }
//    return fm;
//}

}

