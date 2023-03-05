/**Matrix solver which uses Gaussian reduction to solve matrices
 @author Caleb Bessit
 @date 25 February 2023
**/
import java.util.Scanner;

public class MatrixSolver{

   /**
      Method which "eliminates" a column by setting all non-pivot values to zero.
      
      @param matrix The matrix
      @param rows The number of rows in the matrix
      @param cols The number of columns in the matrix
      @param rowOfPivot The row in which the current pivot is
      @param colOfPivot The column in which the current pivot is
   **/
   
   public static void eliminate(float[][] matrix, int rows, int cols, int rowOfPivot, int colOfPivot){
   
   //For each row except the row of pivot, find the lambda, where lambda is the value in the column beneath row of pivot
   //Replace value in the row with : value = value +lambda*corresp value in row of pivot, same column
   
      float lambda;
     
      for (int i=0; i<rows;i++){
         if (!(i==rowOfPivot)){
            lambda = matrix[i][colOfPivot]; //Lambda is the value in the column of the pivot, in the current row
            
            for(int j=0; j<cols;j++){
               //Replace each value in the row with that value less lambda times the value in the row of the pivot, but the current column
               matrix[i][j] = matrix[i][j] - lambda*matrix[rowOfPivot][j] +Float.parseFloat("0.0");  
            
            }//end for over cols
         
         }   //end if not row of pivot
      }//end for over rows
   
   }

   /**
    Method which finds the pivot in a given column of the matrix.
    
    @param rows The number of rows in the matrix
    @param cols The number of columns in the matrix
    
    **/
   public static int pivotRow(float[][] matrix, int rows, int col){
      for (int i=col; i<rows; i++){
         if (!(matrix[i][col]==0)){ return i;};
      }
      return -1;
   }
   
   
   /**
    Method which swaps two rows in the matrix.
    
    @param row1 The first row in the matrix to be swapped
    @param row2 The second row in the matrix to be swapped
    @param cols The number of columns in the matrix
    
    **/
   public static void swapRows(float[][] matrix, int row1, int row2, int cols){
      float tempVal;
      
      for (int j=0; j<cols; j++){
            tempVal = matrix[row1][j];  
            matrix[row1][j] = matrix[row2][j];
            matrix[row2][j] = tempVal;
        } 
   
   
   }

   
    /** Method which prints out a formatted version of a given float valued matrix.
    
    @param rows The number of rows in the matrix
    @param cols The number of columns in the matrix
    
    **/
    public static void printMatrix(float[][] matrix, int rows, int cols){
      String output = "|";
      for (int i=0; i<rows; i++){
         output = "|";
        
         for (int j=0; j<cols-1; j++){
            output = output + String.format("%.2f |",matrix[i][j]);
         }  
         output = output+ String.format("| %.2f |",matrix[i][cols-1]);
         
         System.out.println(output);
        
        }  
    
    }

    public static void main(String[] args){
        int rows, cols;
        float[][] matrix;
        Scanner input = new Scanner(System.in);
        

        System.out.println("Enter the dimensions of the matrix <mxn>: ");
        rows = input.nextInt();
        cols = input.nextInt();
        
        //Create matrix
        matrix = new float[rows][cols];
         
        for (int i=0; i<rows; i++){
        
         System.out.printf("Enter the space separated values of row %d%n", i+1);
        
         for (int j=0; j<cols; j++){
            matrix[i][j] = input.nextFloat();
         }  
        
        }
        
        //Loop through columns
        for (int j=0; j<cols-1; j++){
         
         //Find the pivot in this column
         int rowOfPivot = pivotRow(matrix, rows, j);
         
         //Row j and rowOfPivot need to be swapped, but only if j and rowOfPivot are not equal
         if (!(j==rowOfPivot)){swapRows(matrix, j, rowOfPivot, cols);}
         
         //For simplification, have the pivot in row j be one
         float pivot = matrix[j][j] + Float.parseFloat("0.0");
         for (int m=0;m<cols;m++){ 
         
         matrix[j][m] = matrix[j][m]/pivot + Float.parseFloat("0.0");}
         
         //Eliminate all other values from column
         eliminate(matrix, rows, cols, rowOfPivot, j);
        
        }
        
       printMatrix(matrix, rows, cols);
        


    }//end main




}