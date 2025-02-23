package AI_ENGINE.With_refactoring.boards;

import AI_ENGINE.With_refactoring.game.Board;

public class TicTacToeBoard extends Board{
    String cells[][] = new String[3][3];
    
    public String getCell(int row,int col){
        return this.cells[row][col];
    } 
}
