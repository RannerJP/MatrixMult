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
                matrix[rowNums][columnNums] = rand.nextInt(11);
            }
            for(int columnZeros = size; columnZeros < newSize; columnZeros++){
                matrix[rowNums][columnZeros] = 0;
            }
        }
        for(int rowZeros =0; rowZeros < size; rowZeros++){
            for(int columnNums = 0; columnNums < size; columnNums++){
                matrix[rowZeros][columnNums] = rand.nextInt(11);
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

    public int[][] divideAndConquer(int[][] matrixA, int[][] matrixB, int size){
        int[][] matrixC = divideAndConquerHelper(matrixA,matrixB, size);
        return matrixC;
    }


    private int[][] divideAndConquerHelper(int[][] matrixA, int[][] matrixB, int size){
        if(size == 1){
            int[][] base = new int[1][1];
            base[0][0] = matrixA[0][0] * matrixB[0][0];
            return base;
        }
        else{
            int halfSize = size/2;
            int[][] A11 = new int[halfSize][halfSize];
            int[][] A12 = new int[halfSize][halfSize];
            int[][] A21 = new int[halfSize][halfSize];
            int[][] A22 = new int[halfSize][halfSize];
            int[][] B11 = new int[halfSize][halfSize];
            int[][] B12 = new int[halfSize][halfSize];
            int[][] B21 = new int[halfSize][halfSize];
            int[][] B22 = new int[halfSize][halfSize];
            for(int i = 0; i<halfSize; i++){
                for(int j = 0; j<halfSize; j++){
                    A11[i][j] = matrixA[i][j];
                    A12[i][j] = matrixA[i][j+halfSize];
                    A21[i][j] = matrixA[i+halfSize][j];
                    A22[i][j] = matrixA[i+halfSize][j+halfSize];
                    B11[i][j] = matrixB[i][j];
                    B12[i][j] = matrixB[i][j+halfSize];
                    B21[i][j] = matrixB[i+halfSize][j];
                    B22[i][j] = matrixB[i+halfSize][j+halfSize];
                }
            }

            int[][] C11 = matrixAddition(divideAndConquerHelper(A11, B11, halfSize), divideAndConquerHelper(A12, B21, halfSize), halfSize);
            int[][] C12 = matrixAddition(divideAndConquerHelper(A11, B12, halfSize), divideAndConquerHelper(A12, B22, halfSize), halfSize);
            int[][] C21 = matrixAddition(divideAndConquerHelper(A21, B11, halfSize), divideAndConquerHelper(A22, B21, halfSize), halfSize);
            int[][] C22 = matrixAddition(divideAndConquerHelper(A21, B12, halfSize), divideAndConquerHelper(A22, B22, halfSize), halfSize);
            int[][] C = new int[size][size];
            for(int i =0; i<halfSize; i++){
                for(int j =0; j<halfSize; j++){
                    C[i][j] = C11[i][j];
                    C[i][j+halfSize] = C12[i][j];
                    C[i+halfSize][j] = C21[i][j];
                    C[i+halfSize][j+halfSize] = C22[i][j];
                }
            }
            return C;
        }
    }

    public int[][] Strassens(int[][] matrixA, int[][] matrixB, int size){
        int[][] matrixC = strassensHelper(matrixA, matrixB, size);
        return matrixC;
    }

    private int[][] strassensHelper(int[][] matrixA, int[][] matrixB, int size){
        if(size == 1){
            int[][] base = new int[1][1];
            base[0][0] = matrixA[0][0] * matrixB[0][0];
            return base;
        }
        else{
            int halfSize = size/2;
            int[][] A11 = new int[halfSize][halfSize];
            int[][] A12 = new int[halfSize][halfSize];
            int[][] A21 = new int[halfSize][halfSize];
            int[][] A22 = new int[halfSize][halfSize];
            int[][] B11 = new int[halfSize][halfSize];
            int[][] B12 = new int[halfSize][halfSize];
            int[][] B21 = new int[halfSize][halfSize];
            int[][] B22 = new int[halfSize][halfSize];
            for(int i = 0; i<halfSize; i++){
                for(int j = 0; j<halfSize; j++){
                    A11[i][j] = matrixA[i][j];
                    A12[i][j] = matrixA[i][j+halfSize];
                    A21[i][j] = matrixA[i+halfSize][j];
                    A22[i][j] = matrixA[i+halfSize][j+halfSize];
                    B11[i][j] = matrixB[i][j];
                    B12[i][j] = matrixB[i][j+halfSize];
                    B21[i][j] = matrixB[i+halfSize][j];
                    B22[i][j] = matrixB[i+halfSize][j+halfSize];
                }
            }
            int[][] product1 = strassensHelper(A11, matrixSubtraction(B12, B22, halfSize), halfSize);
            int[][] product2 = strassensHelper(matrixAddition(A11, A12, halfSize), B22, halfSize);
            int[][] product3 = strassensHelper(matrixAddition(A21, A22, halfSize), B11, halfSize);
            int[][] product4 = strassensHelper(A22, matrixSubtraction(B21, B11, halfSize), halfSize);
            int[][] product5 = strassensHelper(matrixAddition(A11, A22, halfSize), matrixAddition(B11, B22, halfSize), halfSize);
            int[][] product6 = strassensHelper(matrixSubtraction(A12, A22, halfSize), matrixAddition(B21, B22, halfSize), halfSize);
            int[][] product7 = strassensHelper(matrixSubtraction(A11, A21, halfSize), matrixAddition(B11, B12, halfSize), halfSize);
            int[][] C11 = matrixSubtraction(matrixAddition(matrixAddition(product4, product5, halfSize), product6, halfSize), product2, halfSize);
            int[][] C12 = matrixAddition(product1, product2, halfSize);
            int[][] C21 = matrixAddition(product3, product4, halfSize);
            int[][] C22 = matrixAddition(matrixSubtraction(product1, product3, halfSize), matrixSubtraction(product5, product7, halfSize), halfSize);
            int[][] C = new int[size][size];
            for(int i =0; i<halfSize; i++){
                for(int j =0; j<halfSize; j++){
                    C[i][j] = C11[i][j];
                    C[i][j+halfSize] = C12[i][j];
                    C[i+halfSize][j] = C21[i][j];
                    C[i+halfSize][j+halfSize] = C22[i][j];
                }
            }
            return C;
        }

    }

    private int[][] matrixAddition(int[][] matrixA, int[][] matrixB, int size){
        int[][] temp = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j <size; j++){
                temp[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return temp;
    }

    private int[][] matrixSubtraction(int[][] matrixA, int[][] matrixB, int size){
        int[][] temp = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j <size; j++){
                temp[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }
        return temp;
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
