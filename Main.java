import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Main {
    
    public static void main(String[] args) throws FileNotFoundException{
        PrintWriter file = new PrintWriter("JoseResult.txt");
        PrintWriter nums = new PrintWriter("RawNumbers.txt");
        Matrix test = new Matrix();
        int[][] sampleA = {
                        {2,0,-1,6},
                        {3,7,8,0},
                        {-5,1,6,-2},
                        {8,0,2,7}
        };
        int[][] sampleB = {
            {0,1,6,3},
            {-2,8,7,1},
            {2,0,-1,0},
            {9,1,6,-2}
        };
        test.displayMatrix(4, sampleA);
        test.displayMatrix(4, sampleB);
        long startTime = System.currentTimeMillis();
        int[][] brute = test.bruteForceSolve(sampleA, sampleB, 4);
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("Brute Force time: " + runTime + " for matrix size: " + 4);
        test.displayMatrix(4, brute);
        startTime = System.currentTimeMillis();
        int[][] divide = test.divideAndConquer(sampleA, sampleB, 4);
        runTime = System.currentTimeMillis() - startTime;
        System.out.println("Divide and Conquer time: " + runTime + " for matrix size: " + 4);
        test.displayMatrix(4, divide);
        startTime = System.currentTimeMillis();
        int[][] strassens = test.Strassens(sampleA, sampleB, 4);
        runTime = System.currentTimeMillis() - startTime;
        System.out.println("Strassens time: " + runTime + " for matrix size: " + 4);
        test.displayMatrix(4, strassens);
        for(int i = 2; i < 1025; i = i*2){
            for(int j = 1; j<=10; j++){
                int[][] a = test.generateMatrix(i);
                int[][] b = test.generateMatrix(i);
                startTime = System.currentTimeMillis();
                brute = test.bruteForceSolve(a, b, i);
                runTime = System.currentTimeMillis() - startTime;
                file.println(runTime + " for matrix size: " + i + " trial number: " + j);
                nums.println(runTime);
                startTime = System.currentTimeMillis();
                divide = test.divideAndConquer(a, b, i);
                runTime = System.currentTimeMillis() - startTime;
                file.println("Divide and Conquer time: " + runTime + " for matrix size: " + i + " trial number: " + j);
                nums.println(runTime);
                startTime = System.currentTimeMillis();
                strassens = test.Strassens(a, b, i);
                runTime = System.currentTimeMillis() - startTime;
                file.println("Strassens time: " + runTime + " for matrix size: " + i + " trial number: " + j);
                nums.println(runTime);
                file.println(" ");
            }
            System.out.println("Finised size" + i);
        }
        file.close();
        nums.close();
    }
}