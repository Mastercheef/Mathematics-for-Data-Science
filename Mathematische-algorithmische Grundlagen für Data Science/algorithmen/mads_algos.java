import java.util.Arrays;

public class MADS {

    public static void forwardSubstitution(double[][] l, double[] y, double[] b){
        y[0]=b[0]/l[0][0];
        for(int i=0;i<y.length;i++){
            double sum=0;
            for(int j=0;j<i;j++){
                sum += y[j]*l[i][j];
            }
            y[i]=(b[i]-sum)/l[i][i];
        }
    }
    public static void backSubstitution(double[][] r, double[] x, double[] y){
        int len=r.length;
        x[len-1]=y[len-1]/r[len-1][len-1];

        for(int i=len-2;i>-1;i--){
            double sum=0;
            for(int j=i+1;j<len;j++){
                sum += x[j]*r[i][j];
            }
            x[i]=(y[i]-sum)/r[i][i];
        }
    }
    public static void lrFactorization(double[][] a, double[][] l,double[][] r) {
        int n= a.length;

        for (int i = 0; i < n; i++) {
            for (int k = i; k < n; k++) {
                int sum = 0;
                for (int j = 0; j < i; j++)
                    sum += (l[i][j] * r[j][k]);
                r[i][k] = a[i][k] - sum;
            }
            for (int k = i; k < n; k++) {
                if (i == k)
                    l[i][i] = 1;
                else {
                    int sum = 0;
                    for (int j = 0; j < i; j++)
                        sum += (l[k][j] * r[j][i]);

                    l[k][i] =
                            (a[k][i] - sum) / r[i][i];
                }
            }
        }
    }

    public static void solveLinearEquationSystem(double[][] a, double[] x, double[] b){
        int n = b.length;
        double[] y = new double[n];
        double[][] l = new double[n][n];
        double[][] r= new double[n][n];
        lrFactorization(a,l,r);
        forwardSubstitution(l,y,b);
        backSubstitution(r,x,y);
    }

    private static void printMatrix(double[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            System.out.print(Arrays.toString(matrix[r]) + "\t");
            System.out.println();
        }
    }


    public static void choleskyFactorization(double[][] a, double[][] l){
        int n = a.length;
        for (int i =0 ;i < n;i++ ){
            for(int j = 0; j <= i;j++){
                double sum = 0.0;
                if(i==j){
                    for (int k = 0; k < j; k++){
                        sum += Math.pow(l[i][k],2);
                    }
                    l[i][i] = Math.sqrt(a[i][i] - sum);

                }
                else{
                    for (int k = 0; k < j; k++){
                        sum += (l[j][k] *l[i][k] );
                    }
                    l[i][j] = (a[i][j] - sum) / l[j][j];
                }
            }
        }
    }

    public static void lrFactorization(int[] pi,double[][] a, double[][] l, double[][] r) {

        double[][] P = new double[a.length][a.length];

        for (int i = 0; i < l.length; i++) {
            l[i][i] = r[i][i] = P[i][i] = 1;
            pi[i] = i;
        }

        double[][] A_1 = a;

        for (int j = 0; j < A_1.length - 1; j++) {

            int pos_i = 0;
            double max = 0;
            for (int i = j; i < a.length; i++) {
                if (Math.abs(A_1[i][j]) > max) {
                    pos_i = i;
                    max = Math.abs(A_1[i][j]);
                }
            }
            int tmp = pi[pos_i];
            pi[pos_i] = pi[j];
            pi[j] = tmp;
            double[] p_tmp = P[j];
            P[j] = P[pos_i];
            P[pos_i] = p_tmp;
            p_tmp = A_1[pos_i];
            A_1[pos_i] = A_1[j];
            A_1[j] = p_tmp;
            //swap_sub(l, pos_i, j);

            for (int i = j + 1; i < a.length; i++) {
                l[i][j] = A_1[i][j] / A_1[j][j];
            }
            for(int i = j+1; i<a.length; i++){
                for(int k = 0; k<a.length; k++){
                    A_1[i][k] -= l[i][j] * A_1[j][k];
                }
            }

        }
    //    copy(r,A_1);
    }

    public static void main(String[]args){
        double[][] l={{1,0,0,0},
                     {-1,1,0,0},
                      {2,8,1,0},
                     {3,-9,-2,1}};
        double[][] r={{2,1,3,5},{0,-1,1,1},{0,0,-4,-10},{0,0,0,-12}};
        double[] b= {-6,6,14,-34};
        double[] y= new double[b.length];
        double[] xx= new double[y.length];
        double[][] acat=new double[1][1];

        forwardSubstitution(l,y,b);
        System.out.println(Arrays.toString(y));


        //System.out.println("_____________________");
    //  System.out.println(Arrays.toString(y));
    //  System.out.println("Ergebnis: _____________________");
    //  System.out.println(Arrays.toString(xx));

        double[][] a={{2,1,3,5},{-2,-2,-2,-4},{4,-6,10,8},{6,12,8,14}};
        double[][] a_beispiel={{1,6,1},
                                {2,3,2},
                                {4,2,1}};
        double[][] l_beispiel={{0,0,0},{0,0,0},{0,0,0}};
        double[][] r_beispiel={{0,0,0},{0,0,0},{0,0,0}};
        double[][] links={{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        double[][] rechts={{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        double[] x= new double[a.length];

        //lrFactorization(a_beispiel,l_beispiel,r_beispiel);
        solveLinearEquationSystem(a,x,b);
        printMatrix(acat);

        System.out.println("_____________________");
        System.out.println("Ergebnis: _____________________");
        System.out.println(Arrays.toString(x));

        double[][] mat ={
                {1.0,0.0,0.0,0.0,0.0,0.0,0.0},
                {1.0,1.0,0.0,0.0,0.0,0.0,0.0},
                {9.0,8.0,1.0,0.0,0.0,0.0,0.0},
                {0.0,5.0,3.0,1.0,0.0,0.0,0.0},
                {0.0,0.0,1.0,8.0,1.0,0.0,0.0},
                {5.0,2.0,7.0,11.0,0.0,1.0,0.0},
                {1.0,3.0,5.0,2.0,10.0,11.0,1.0}
        };



    }
}
