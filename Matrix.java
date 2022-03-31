import java.util.Random;
public class Matrix {
    Random rand = new Random();

    public int[][] generateMatrix(int size){
        double logTwo;
        int newSize = size;
        do{
            logTwo = Math.log(newSize)/Math.log(2);
            newSize++;
        }while(Math.floor(logTwo) != logTwo);
        newSize--;
        int[][] matrix = new int[newSize][newSize];
        for(int rowNums =0; rowNums < size; rowNums++){
            for(int columnNums = 0; columnNums < size; columnNums++){
                matrix[rowNums][columnNums] = rand.nextInt(101);
            }
            for(int columnZeros = size; columnZeros < newSize; columnZeros++){
                matrix[rowNums][columnZeros] = 0;
            }
        }
        for(int rowZeros =0; rowZeros < size; rowZeros++){
            for(int columnNums = 0; columnNums < size; columnNums++){
                matrix[rowZeros][columnNums] = rand.nextInt(100);
            }
            for(int columnZeros = size; columnZeros < size; columnZeros++){
                matrix[rowZeros][columnZeros] = 0;
            }
        }
        return matrix;
    }

    
    public void bruteForceSolve(int[][] A, int[][] B, int size){
        int[][] multMatrix = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                multMatrix[i][j] = 0;
                for(int k = 0; k < size; k++){
                    multMatrix[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        displayMatrix(4, multMatrix);
    }

    public void divideAndConquer(){
        divideAndConquerHelper();
    }

    public void divideAndConquerHelper(int[][] A){
        int n = A.length;
        int[][] divConqMatrix = new int[][];
        int[][] A11 = new int[n/2][n/2];
        int[][] A12 = new int[n/2][n/2];
        int[][] A21 = new int[n/2][n/2];
        int[][] A22 = new int[n/2][n/2];
        int[][] B11 = new int[n/2][n/2];
        int[][] B12 = new int[n/2][n/2];
        int[][] B21 = new int[n/2][n/2];
        int[][] B22 = new int[n/2][n/2];

        int[][] C11 = (A11[][]*B11[][]) + (A12[][]*B21[][]);
        int[][] C12 = (A11[][]*B12[][]) + (A12[][]*B22[][]);
        int[][] C21 = (A21[][]*B11[][]) + (A22[][]*B21[][]);
        int[][] C22 = (A21[][]*B12[][]) + (A22[][]*B22[][]);





    }

    public int[][] Strassens(int[][] matrixA, int[][] matrixB, int rowStart, int rowEnd, int columnStart, int columnEnd){
        int[][] matrixC = new int[rowEnd][rowEnd];
        strassensHelper(matrixA, matrixB, matrixC, rowStart, rowEnd, columnStart, columnEnd);
        return matrixC;
    }

    public void strassensHelper(int[][] matrixA, int[][] matrixB, int[][] matrixC, int rowStart, int rowEnd, int columnStart, int columnEnd){
        if((rowEnd - rowStart) <= 1){
            int product1 = matrixA[rowStart-1][columnStart-1] * (matrixB[rowStart-1][columnEnd-1] - matrixB[rowEnd-1][columnEnd-1]);
            int product2 = matrixB[rowEnd-1][columnEnd-1] * (matrixA[rowStart-1][columnStart-1] + matrixA[rowStart-1][columnEnd-1]);
            int product3 = matrixB[rowStart-1][columnStart-1] * (matrixA[rowEnd-1][columnStart-1] + matrixA[rowEnd-1][columnEnd-1]);
            int product4 = matrixA[rowEnd-1][columnEnd-1] * (matrixB[rowEnd-1][columnStart-1] - matrixB[rowStart-1][columnStart-1]);
            int product5 = (matrixA[rowStart-1][columnStart-1]+matrixA[rowEnd-1][columnEnd-1])*(matrixB[rowStart-1][columnStart-1]+matrixB[rowEnd-1][columnEnd-1]);
            int product6 = (matrixA[rowStart-1][columnEnd-1] - matrixA[rowEnd-1][columnEnd-1])*(matrixB[rowEnd-1][columnStart-1]+matrixB[rowEnd-1][columnEnd-1]);
            int product7 = (matrixA[rowStart-1][columnStart-1] - matrixA[rowEnd-1][columnStart-1])*(matrixB[rowStart-1][columnStart-1]+matrixB[rowStart-1][columnEnd-1]);
            matrixC[rowStart-1][columnStart-1] = (-1*product2) + product4 + product5 + product6;
            matrixC[rowStart-1][columnEnd-1] = product1 + product2;
            matrixC[rowEnd-1][columnStart-1] = product3 + product4;
            matrixC[rowEnd-1][columnEnd-1] = product1 - product3 + product5 - product7;
        }
        else{
            int middleRow = (int)Math.floor((rowStart+rowEnd)/2);
            int middleColumn = (int)Math.floor((columnStart+columnEnd)/2);
            strassensHelper(matrixA, matrixB, matrixC, rowStart, middleRow, columnStart, middleColumn);
            strassensHelper(matrixA, matrixB, matrixC, middleRow+1, rowEnd, columnStart, middleColumn);
            strassensHelper(matrixA, matrixB, matrixC, rowStart, middleRow, middleColumn + 1, columnEnd);
            strassensHelper(matrixA, matrixB, matrixC, middleRow+1, rowEnd, middleColumn + 1, columnEnd);
        }

    }

    /**
     * This method will display the matrix given it's size and 
     * @param size is the number of equations in the matrix
     * @param matrix is the array that holds our matrix
     */
    public void displayMatrix(int size, int[][] matrix){
        for(int i = 0; i < size; i++){
            String row = "[ ";
            for(int j = 0; j < size; j++){
                if(j == size){
                    row = row + ": ";
                }
                row = row + matrix[i][j] + " ";
            }
            row = row + "]";
            System.out.println(row);
        }
    }
}
