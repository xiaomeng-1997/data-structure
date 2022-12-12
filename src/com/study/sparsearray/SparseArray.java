package com.study.sparsearray;

/**
 * @author meng
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个 11*11 的二维数组
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][4] = 2;

        for (int[] row : chessArray) {
            for (int date : row) {
                System.out.print("\t" + date);
            }
            System.out.println();
        }
        System.out.println(chessArray.length);
        // 转换为稀疏数组
        int sum = 0;
        // 获取非零数据个数
        for (int[] row : chessArray) {
            for (int date : row) {
                if (date != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum ==" + sum);
        int row = sum + 1;
        int colum = 3;
        int[][] sparseArray = new int[row][colum];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        for (int[] row1 : sparseArray) {
            for (int date : row1) {
                System.out.print("\t" + date);
            }
            System.out.println();
        }
    }
}
