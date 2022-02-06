import java.util.Arrays;

public class matrix_calc {

    private static double[][] add(double[][] first, double[][] second) {
        int row = first.length;
        int col = first[0].length;
        double[][] sum = new double[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                sum[r][c] = first[r][c] + second[r][c];
            }
        }
        System.out.println("\nSum of Matrices:\n");
        printMatrix(sum);
        return sum;
    }

    private static double[][] mul(double[][] first, double[][] second) {
        int row = first.length;
        int col = second[0].length;
        double[][] m = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < col; k++) {
                    m[i][j] += first[i][k] * second[k][j];
                }
            }
        }
        System.out.println("\nMultiplication of Matrices:\n");
        printMatrix(m);
        return m;
    }

    public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static double[][] deepCopy(double[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    private static double[][] clone(double[][] matrix) {
        double[][] copy = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < copy.length; i++) {
            {
                for (int j = 0; j < copy[0].length; j++) {
                    copy[i][j] = matrix[i][j];
                }
            }
        }
        return copy;
    }

    private static void printMatrix(double[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            System.out.print(Arrays.toString(matrix[r]) + "\t");
            System.out.println();
        }
    }

    public static void main(String[]args){

        double[][] E={{1,0,0},
                      {0,1,0},
                      {0,0,1}};
        double[][] P={{0,0,1},
                      {0,1,0},
                       {1,0,0}};
        double[][] A={{3,2,4},
                      {7,1,3},
                       {1,0,6}};


        // von links Zeilenvertauschung
        // von rechts Spaltenvertauschung
        mul(P,A);








    }



}
