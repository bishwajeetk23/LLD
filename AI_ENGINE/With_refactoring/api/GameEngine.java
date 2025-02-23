package AI_ENGINE.With_refactoring.api;
// This contain all APIs which is function over here

import AI_ENGINE.With_refactoring.boards.TicTacToeBoard;
import AI_ENGINE.With_refactoring.game.Board;
import AI_ENGINE.With_refactoring.game.GameResult;
import AI_ENGINE.With_refactoring.game.Move;
import AI_ENGINE.With_refactoring.game.Player;

public class GameEngine {
    public Board start(){
        return new Board();
    }

    public void move(Board board, Player player, Move move){

    }

    public GameResult isCompleted(Board board){
        if (board instanceof TicTacToeBoard){
            // win row, col, diag, revdiag
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";
            //  row check
            boolean rowComplete = true;
            for (int i=0;i<3;i++){
                firstCharacter = board1.getCell(i,0);
                rowComplete = true;
                for (int j=1;j<3;j++){
                    if(!(board1.getCell(i,j).equals(firstCharacter))){
                        rowComplete = false;
                        break;
                    }
                }
                if(rowComplete)return new GameResult(true,firstCharacter);
            }
            if(rowComplete)return new GameResult(true,firstCharacter);
            // col check
            boolean colComplete = true;
            for (int i=0;i<3;i++){
                firstCharacter = board1.getCell(0,i);
                colComplete = true;
                for (int j=1;j<3;j++){
                    if(!(board1.getCell(j, i).equals(firstCharacter))){
                        colComplete = false;
                        break;
                    }
                }
                if(colComplete)return new GameResult(true,firstCharacter);
            }
            if(colComplete)return new GameResult(true,firstCharacter);
            // diag check
            boolean diagComplete = true;
            for (int i=0;i<3;i++){
                firstCharacter = board1.getCell(0, 0);
                diagComplete = true;
                if(!(board1.getCell(i,i).equals(firstCharacter))){
                    diagComplete = false;
                    break;
                }
            }
            if(diagComplete)return new GameResult(true,firstCharacter);
            // revdiag check
            boolean revDiagComplete = true;
            for (int i=0;i<3;i++){
                firstCharacter = board1.getCell(0, 2);
                revDiagComplete = true;
                if(!(board1.getCell(i, 2-i).equals(firstCharacter))){
                    revDiagComplete = false;
                    break;
                }
            }
            if(revDiagComplete)return new GameResult(true,firstCharacter);

            // count not null cells for determining the status of the Game
            int countOfFilledCells = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board1.getCell(i, j) != null){
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells==9){
                return new GameResult(true, "-");
            }else{
                return new GameResult(false,"-");
            }
            
        }else{
            return new GameResult(false,"-");
        }
    }
}
