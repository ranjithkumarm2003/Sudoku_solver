public class SudokuSolver {
    private static final int grid_Size=9;
    public static void main(String[]args) {
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };
        if(solver(board)){
            System.out.println("Board was Successfully Solved");
        }
        else{
            System.out.println("Unsolvable Board!!!");
        }
        printBoard(board);
    }
    private static void printBoard(int [][]board){
         for(int i=0;i<grid_Size;i++){
             if(i%3==0 && i!=0){
                 System.out.println("---- ----- ----");
             }
             for(int j=0;j<grid_Size;j++){
                 if(j%3==0 && j!=0){
                     System.out.print(" | ");
                 }
                 System.out.print(board[i][j]);
             }
             System.out.println();
         }
    }
    private static boolean isNumInRow(int [][]board, int num, int row){
        for(int i=0;i<grid_Size;i++){
            if(board[row][i]==num){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumInCol(int [][]board, int num, int col){
        for(int i=0;i<grid_Size;i++){
            if(board[i][col]==num){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumInBox(int [][]board, int num, int row,int col){
        int localBoxRow=row-row%3;
        int localBoxCol=col-col%3;
        for(int i=localBoxRow;i<localBoxRow+3;i++){
            for(int j=localBoxCol;j<localBoxCol+3;j++){
                if(board[i][j]==num){
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isValidPlacement(int [][]board, int num, int row, int col){
        return !isNumInBox(board,num,row,col) && !isNumInRow(board,num,row) && !isNumInCol(board,num,col);

    }
    private static boolean solver(int [][]board){
        for(int i=0;i<grid_Size;i++){
            for(int j=0;j<grid_Size;j++){
                if(board[i][j]==0){
                    for(int numToTry=1;numToTry<=9;numToTry++){
                        if(isValidPlacement(board,numToTry,i,j)){
                            board[i][j]=numToTry;
                            if(solver(board)){
                                return true;
                            }
                            else{
                                board[i][j]=0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
