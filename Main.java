public class Main {
    
    public static void main(String[] args){
        Matrix test = new Matrix();
    int[][] A = test.generateMatrix(4);
    int[][] B = test.generateMatrix(4);
    test.displayMatrix(4, A);
    test.displayMatrix(4, B);
    test.bruteForceSolve(A, B, 4);
    }
}