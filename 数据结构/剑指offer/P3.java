package 剑指offer;

/**
 *  二维数组查找（从右上角开始寻找）
 */
public class P3 {
    /**
     *  返回结果
     * @param array Array
     * @param target 要查找的数
     * @return
     */
    public static boolean find(int[][] array, int target){
        if(array == null) return false;

        int row = 0; //第几行
        int col = array[0].length-1; //第几列

        while(row <= array.length-1 && col >= 0){ //查找
            if(array[row][col] == target){ //找到
                return true;
            }else if(array[row][col] > target){ //当前数值比要查找的数值target大，则向左寻找
                col --;
            }else {  //当前数值比要寻找的target小，则向下寻找
                row ++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = new int[4][4];
        array[0][0] = 1;
        array[0][1] = 2;
        array[0][2] = 8;
        array[0][3] = 9;
        array[1][0] = 2;
        array[1][1] = 4;
        array[1][2] = 9;
        array[1][3] = 12;
        array[2][0] = 4;
        array[2][1] = 7;
        array[2][2] = 10;
        array[2][3] = 13;
        array[3][0] = 6;
        array[3][1] = 8;
        array[3][2] = 11;
        array[3][3] = 15;

        System.out.println(find(array, 7));
        System.out.println(find(array, 5));
    }
}
