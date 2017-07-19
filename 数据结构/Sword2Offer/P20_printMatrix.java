package Sword2Offer;

import java.util.ArrayList;

public class P20_printMatrix {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(matrix == null) return result;

        int m = matrix.length-1, n = matrix[0].length-1;
        int x=0;

        while(x < m/2 && x < n/2){
            print(result, matrix, x, m, n);
            x ++;
        }

        return result;
    }

    private void print(ArrayList<Integer> result, int[][] matrix, int x, int m, int n){
        if(x <= n-x){
            for (int i = x; i <= n-x; i++) {
                result.add(matrix[x][i]);
            }
        }
        if(m-x > x && n-x >=x){
            for (int i = x+1; i <= n-x; i++) {
                result.add(matrix[i][n-x]);
            }
        }
        if(x < n-x && m-x >= x) {
            for (int i = n-x-1; i >= x; i--) {
                result.add(matrix[m-x][i]);
            }
        }
        if(x < m-x){
            for (int i = m-x-1; i >= x; i++) {
                result.add(matrix[i][x]);
            }
        }
    }
}
