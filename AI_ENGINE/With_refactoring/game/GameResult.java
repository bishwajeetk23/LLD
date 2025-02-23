package AI_ENGINE.With_refactoring.game;

public class GameResult {
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
