package player;

import board.*;
import observer.Subject;
import strategy.*;

import java.io.File;
import java.util.ArrayList;

import static player.Dice.Roll;

public class Player {
    private int fieldIndex;
    private int lap;
    private int[] balance = new int[2];
    private boolean haveCar;
    private boolean canExchange;
    private ArrayList<Field> OwnedFields;
    private Field location;
    protected ActionStrategy actionStrategy;
    private String name;


    public Player() {
        balance[0] = 250000; //euro
        balance[1] = 250000; //dolary
        this.haveCar = false;
        this.canExchange = false;
        this.OwnedFields = new ArrayList<>();
        this.lap = 0;
        this.location = null;
        this.actionStrategy = null;
        this.name = "Player";
    }
    public Player(String name) {
        balance[0] = 250000; //euro
        balance[1] = 250000; //dolary
        this.haveCar = false;
        this.canExchange = false;
        this.OwnedFields = new ArrayList<>();
        this.lap = 0;
        this.location = null;
        this.actionStrategy = null;
        this.name = name;
    }

    public void setLocation(Field location) {
        this.location = location;
    }

    public Field getLocation() {
        return location;
    }

    public int getLap() {
        return lap;
    }

    public int[] getBalance() {
        return balance;
    }

    public void setBalance(int[] newbalance) {
        for (int i = 0; i < newbalance.length; i++) balance[i] = newbalance[i];
    }

    public void increaseBalance(int[] cost) {
        for (int i = 0; i < cost.length; i++) balance[i] += cost[i];
    }

    public void decreaseBalance(int[] cost) {
        for (int i = 0; i < cost.length; i++) balance[i] -= cost[i];
    }

    public ArrayList<Field> getOwnedFields() {
        return OwnedFields;
    }

    public void setOwnedFields(ArrayList<Field> ownedFields) {
        OwnedFields = ownedFields;
    }

    public void setHaveCar(boolean haveCar) {
        this.haveCar = haveCar;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }


    public boolean isHaveCar() {
        return haveCar;
    }

    public int getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public boolean isCanExchange() {
        return canExchange;
    }

    public void setCanExchange(boolean canExchange) {
        this.canExchange = canExchange;
    }

    public void movePlayer(int roll) {
        int currentIndex = fieldIndex;
        int newIndex = (currentIndex + roll) % 36;
        setFieldIndex(newIndex);
        setLap(getLap() + 1);
    }

    public void playerAction(Board board) {
        if (actionStrategy != null) {
            actionStrategy.action(board);
        }
    }

    public void setActionStrategy(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public void changeStrategy(){
        if(location instanceof ToBuy){
            if(((ToBuy) location).getOwner() == null){
                setActionStrategy(new BuyFieldStrategy());
            }
            else {
                setActionStrategy(null);
                if (OwnedFields.contains(location)){
                    setActionStrategy(new BuyBuildingStrategy());
                }

            }

        }
        if(location instanceof Chance){
            setActionStrategy(new ChanceStrategy());
        }
        if(location instanceof Exchange){
            setActionStrategy(new ChangeStrategy());
        }
        if(location instanceof CarDealership){
            setActionStrategy(new BuyCarStrategy());
        }
        if(location instanceof Start){
            setActionStrategy(null);
        }

    }
    public void useCar(Board board, int round, int sum){
        board.getPlayers()[round].movePlayer(sum);
        board.ChangePlayerLocation(sum);
        board.getCurrentPlayer().changeStrategy();
        board.getCurrentPlayer().setHaveCar(false);
    }
    public void exchangeMoney(Board board, int enteredValue, int typeOfTransaction){ //int typeOfTransaction (1- euro to usd), (2 - usd to euro)
        Player player = board.getCurrentPlayer();
        if (typeOfTransaction == 1) {
            if(enteredValue<player.balance[0]) {
                player.increaseBalance(board.ExchangeEURtoUSD(player.balance[0], enteredValue, board));
            }
        }else if(typeOfTransaction == 2){
            if (enteredValue<player.balance[1]) {
                player.increaseBalance(board.ExchangeUSDtoEUR(player.balance[1], enteredValue, board));
            }
        }
        player.setCanExchange(false);
    }


}