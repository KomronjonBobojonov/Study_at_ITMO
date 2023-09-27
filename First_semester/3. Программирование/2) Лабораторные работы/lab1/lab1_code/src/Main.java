import java.util.Random;

import static java.lang.Math.*;

public class Main{
    public static void main(String[] args){
        long[] c = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};

        float[] x = new float[15];
        for(int i = 0; i < 15; i++){
            x[i] = -10 + new Random().nextFloat() * (20);
        }

        double[][] C = new double[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(c[i] == 14){
                    C[i][j] = pow((1 - log10(pow(E,x[j]))),3);
                }
                else if(c[i] == 4 || c[i] == 5 || c[i] == 6 || c[i] == 9 || c[i] == 10 || c[i] == 11 || c[i] == 15){
                    C[i][j] = tan(tan(x[j]*(x[j] - 1)));
                }
                else{
                    C[i][j] = asin(1/pow(E,pow(pow(tan(asin((1/2 * x[j]/2*E+1))),2),1/2)));
                }
            }
        }

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                System.out.printf("%.4f ", C[i][j]);
            }
            System.out.println('\n');
        }
    }
}
