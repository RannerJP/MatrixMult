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

    
    public void bruteForceSolve(){

    }

    public void divideAndConquer(){
        divideAndConquerHelper();
    }

    public void divideAndConquerHelper(){
    
    }

    public void Strassens(){
        strassensHelper();
    }
    public void strassensHelper(){
    
    }

    /**
     * This method will display the matrix given it's size and 
     * @param size is the number of equations in the matrix
     * @param matrix is the array that holds our matrix
     */
    public static void displayMatrix(int size, int[][] matrix){
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
