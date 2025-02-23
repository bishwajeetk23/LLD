package AI_ENGINE.Without_Principles;

import java.util.Scanner;

class AI_Engine{
    public static void main(String arg[]){
        Board board = start("TicTacToe");
        int row,col;
        Scanner sc = new Scanner(System.in);
        while(isCompleted(board).isOver()){
            System.out.println("Make your move");
            Player computer = new Player("O");
            Player opponent = new Player("X");
            row = sc.nextInt();
            col = sc.nextInt();
            // me makes move
            Move oppMove = new Move(new Cell(row, col));
            move(board, opponent, oppMove);

            // computer makes move
            if(!isCompleted(board).isOver()){
                Move computerMove = suggestMove(computer,board);
                move(board, computer, computerMove);
            }
        }
        System.out.println("GameResult:  "+ isCompleted(board));
        sc.close();
    }
    public static Move suggestMove(Player computer, Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if(board1.getCell(i, j).equals(null)){
                        return new Move(new Cell(i, j));
                    }
                }
            }
            throw new IllegalStateException();
        }else{
            throw new IllegalArgumentException();
        }
    }
    public static Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        }else{
            throw new IllegalArgumentException();
        }
    }

    public static void move(Board board, Player player, Move move){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            board1.setCell(move.getCell(),player.Symbol());
        }else{
            throw new IllegalArgumentException();
        }
    }

    public static GameResult isCompleted(Board board){
        if (board instanceof TicTacToeBoard){
            // win row, col, diag, revdiag
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";
            //  row check
            boolean rowComplete = true;
            for (int i=0;i<3;i++){
                firstCharacter = board1.cells[i][0];
                rowComplete = true;
                for (int j=1;j<3;j++){
                    if(!(board1.cells[i][j].equals(firstCharacter))){
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
                firstCharacter = board1.cells[0][i];
                colComplete = true;
                for (int j=1;j<3;j++){
                    if(!(board1.cells[j][i].equals(firstCharacter))){
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
                firstCharacter = board1.cells[0][0];
                diagComplete = true;
                if(!(board1.cells[i][i].equals(firstCharacter))){
                    diagComplete = false;
                    break;
                }
            }
            if(diagComplete)return new GameResult(true,firstCharacter);
            // revdiag check
            boolean revDiagComplete = true;
            for (int i=0;i<3;i++){
                firstCharacter = board1.cells[0][2];
                revDiagComplete = true;
                if(!(board1.cells[i][2-i].equals(firstCharacter))){
                    revDiagComplete = false;
                    break;
                }
            }
            if(revDiagComplete)return new GameResult(true,firstCharacter);

            // count not null cells for determining the status of the Game
            int countOfFilledCells = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board1.cells[i][j] != null){
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


class Board{

}

class TicTacToeBoard extends Board{
    String cells[][] = new String[3][3];
    public String getCell(int row,int col){
        return this.cells[row][col];
    } 
    public void setCell(Cell cell, String symbol){
        cells[cell.row][cell.col] = symbol;
    }
}

class Player{

    private String playerSymbol;

    public Player(String playerSymbol){
        this.playerSymbol = playerSymbol;
    }

    public String Symbol(){
        return this.playerSymbol;
    }
}

class Move{
    private Cell cell;
    public Move(Cell cell){
        this.cell = cell;
    }
    public Cell getCell(){
        return this.cell;
    }
}
class Cell{
    int row;
    int col;
    public Cell(int row, int col){
        this.row = row;
        this.col = col;
    }
}

class GameResult{
    private boolean isOver;
    private String  winner;

    public GameResult(boolean isOver, String winner){
        this.isOver = isOver;
        this.winner = winner;
    }
    public boolean isOver(){
        return isOver;
    }
    public String getWinner(){
        return winner;
    }
}

/*
APIs to be exposed :-
    1) play game/start a game       (Board start())
    2) make move                    (void move(Board board, Player player, Move move))
    3) get result                   (GameResult isComplete())
Creating required objects on the fly which happen in interview
Each Object has some idea encapsulated in them
    1) Board class - A place where pieces can move in turn based game
    2) Player is user who participated in game
    3) Move 
Start function that assign a board on which player will play
move does action on board but return nothing
isComplete return the gameresult in which bunch of if else condition will be written

So now using Tic Tac Toe as an example we design this game engine
    1) write the logic in isComplete() function for tic tac toe instance
        - win
        - loose
        - draw
    2) Thought process/getting sence of bad/redudant code which can be improves
        - writting same lines of code means need to improve (think where are the things can be improved on a high level)
        - problem - how should be GameResult object is constructed given its state is well known to isComplete() function which is a problem
        - all classes and object in same file which leads to tight coupling 
            -> (move all classes in different files which leads to flexible in changing different classes individually)
            -> also it is easy to find which code is where now
            ->(APIs are endpoints that other developers can use to interact with your system. There is no other way to connect with your system except using API)
                - by clearly defining rules for every interaction through API's, you want to restrict access to data structure.
                - using this api we restrict the amount of interactions that different systems can have with my internal system.
        Phase 2: more extensible code than previous 
            - seperate and decide which class have similar meaning and accoridng to that place them in same folder
            - Like here there are 4 folders:-
                -> api:  main contains all the function which is the way other systems can interact with my system-> so we can say it as GamesEngine
                -> boards: TicTacToe is a type of board its not specific to a game(telling status of each playable game it either can be tictactoe or chess or other). A board is like a blue-print for other boards like TicTacToe, Chess
                -> game: it store board and moves that are possible on the board, gamesresult also comes on this package so all the information about game will be here
                -> user: player can be here but it is int game (its not anti pattern) as we store statistics of the game or profile etc in there.

                Where should Cell be defined?
                    -> can every boards have cells -> i dont know or No (then think)
                    -> If boards can exist without cells then we should not put cells in boards. we should put it in specific TicTacToe board
        Phase 3: now creating logic for move and see what is problem when not using SOLID principles
            - 

        Debug phase
            - It will throw nullpointer exception as firstCharecter can be null and it not handled.
            - also handle diag and revdiag
            - print board state after every move
*/

// KerberosTicket