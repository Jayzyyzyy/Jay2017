package Nowcoder.dp;

import java.util.Scanner;

/**
 * 滑雪
 */
public class Poj1088 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxLength = 0;
        while(sc.hasNextInt()){
            int M = sc.nextInt();
            int N = sc.nextInt();
            int[][] height = new int[M + 2][N + 2];
            int[][] length = new int[M + 2][N + 2];
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    height[i][j] = sc.nextInt();
                }
            }

            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    length[i][j] = recursion(height, length, i, j, M, N);
                    if(maxLength < length[i][j]){
                        maxLength = length[i][j];
                    }
                }
            }
            System.out.println(maxLength);
            maxLength = 0;
        }
    }
    //i,j 的最长路径
    public static int recursion(int[][] height, int[][] length, int i, int j, int M, int N){
        if(height == null || height.length == 0 || height[0].length == 0){
            return 0;
        }
        if(i < 1 || j < 1 || i > M || j > N){
            return 0;
        }

        if(length[i][j] != 0){
            return length[i][j];
        }

        int temp = 0, max = 0;
        //left
        if(height[i][j] > height[i-1][j]){
            temp = recursion(height, length, i-1, j, M, N);
            max = Math.max(max, temp);
        }
        //right
        if(height[i][j] > height[i+1][j]){
            temp = recursion(height, length, i+1, j, M, N);
            max = Math.max(max, temp);
        }
        //up
        if(height[i][j] > height[i][j-1]){
            temp = recursion(height, length, i, j-1, M, N);
            max = Math.max(max, temp);
        }
        //down
        if(height[i][j] > height[i][j+1]){
            temp = recursion(height, length, i, j+1, M, N);
            max = Math.max(max, temp);
        }

        return max +1;
    }
}
