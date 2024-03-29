package board;
import ChancesAndModifications.Chances;
import monopolyGame.GamePanel;
import player.Player;

import java.util.Random;

public class Chance extends Field{
    private static Chances [] listOfChances = null;
    public Chance(String name, Chances [] listOfChances){
        super(name);
        this.listOfChances = listOfChances;
    }
    public static Chances [] getListOfChances(){
        return listOfChances;
    }
    public Chances getChance(int index){
        return listOfChances[index];
    }
    public void doChance(Board board){
        Random generator = new Random();
        int i = generator.nextInt(listOfChances.length);
        System.out.println(i);
        int [] moneyChange = listOfChances[i].getMoneyChange();
        int [] moneyGivenPerPlayer = listOfChances[i].getMoneyGivenPerPlayer();
        int positionChange = listOfChances[i].getPositionChange();
        int finalPosition = listOfChances[i].getFinalPosition();
        GamePanel.showChance(i);

        Player player = board.getCurrentPlayer();
        if (i==0 || i==1) {
            player.decreaseBalance(moneyChange);
        }
        if (i==2 || i==3){
            player.decreaseBalance(moneyGivenPerPlayer);
            board.getPlayers()[(board.getRound()+1)%board.getPlayers().length].increaseBalance(moneyGivenPerPlayer);
        }
        if (i==4 || i==5){
            player.setCanMoveAfterChance(true);
            board.changePlayerLocation(positionChange);
            player.changeStrategy();
            board.movePawn();
        }
        if (i==6){
            player.setFieldIndex(finalPosition);
            board.SetPlayerLocation(finalPosition);
            player.changeStrategy();
            board.movePawn();
        }
    }
    public String toString(){
        return "Chance";
    }
}
