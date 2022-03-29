public class Matrix {
    
    public void generateMatrix(int size){
        double logTwo;
        int newSize = size;
        do{
            logTwo = Math.log(newSize)/Math.log(2);
            newSize++;
        }while(Math.floor(logTwo) != logTwo);
        newSize--;
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
}
