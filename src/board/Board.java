package board;

import ChancesAndModifications.Chances;
import observer.Subject;
import player.Pawn;
import player.Player;

import java.awt.*;

public class Board {
    Subject subjects = new Subject();
    private static Field[] fields ;
    private static Player[] players;
    private static Color[] playercolors={new Color(255, 16, 16), new Color(43,77, 255), Color.YELLOW, Color.GREEN};
    private static Pawn[] pawns = new Pawn[]{
        new Pawn(0),
        new Pawn(1),
        new Pawn(2),
        new Pawn(3)
    };
    private int moveCounter; //licznik ruchów
    private int round; //oblicza index gracza którego aktualnie jest kolej ruchu
    private Player currentPlayer;
    private static final Chances[] listOfChances = {
            new Chances("You are losing 7500 euro", new int[]{10000, 0}, new int[] {0, 0}, 0, 0, 0),
            new Chances("You are losing 15000 euro", new int[]{20000, 0}, new int[] {0, 0}, 0, 0, 0),
            new Chances("You must give 7000 dolars for next player",new int[] {0, 0}, new int[]{0,7000}, 0, 0, 0),
            new Chances("You must give 10000 dolars for next player",new int[] {0, 0}, new int[]{0,10000}, 0, 0, 0),
            new Chances("You go 6 fields ahead", new int[] {0, 0}, new int[]{0,0}, 6, 0, 0),
            new Chances("You go 1 field ahead", new int[] {0, 0}, new int[]{0,0}, 1, 0, 0),
            new Chances("Start again",new int[] {0, 0}, new int[]{0,0}, 0, 0, 0),
    };
    private double dollarRate;
    private double euroRate;

    public Board(){
        moveCounter = 0;
        round = 0;
        fields = new Field[36];
        euroRate = 1.0;
        dollarRate = 1.0;

    }
    public static void generatePlayers(int playersCount){
        players = new Player[playersCount];
        for(int i=0; i<players.length; i++){
            players[i]=new Player("Player %s".formatted(i+1), playercolors[i]);
            players[i].setLocation(new Start());
        }
    }

    public static Player[] GetPlayersArray(){
        return players;
    }


    public static void generateBoard(int boardNumber){
        fields[0] = new Start();
        fields[9] = new Exchange("Kantor1"); //kantory musza byc osobnymi obiektami aby dzialala strategia
        fields[18] = new Exchange("Kantor2");
        fields[27] = new Exchange("Kantor3");
        if(boardNumber==1) {
            fields[1] = new Village("Leirose", 20000, 0, null, 1);
            fields[2] = new Chance("Szansa1", listOfChances);
            fields[3] = new City("Lizbone", 30000, 0, null, 10, 8);
            fields[4] = new CarDealership("Euro");
            fields[5] = new City("Sevilla", 30000, 0, null, 10, 7);
            fields[6] = new Village("Opole", 20000, 0, null, 1);
            fields[7] = new Chance("Szansa2", listOfChances);
            fields[8] = new City("Madryt", 40000, 0, null, 10, 9);
            //fields[9] jest wspolne dla każdej mapy
            fields[10] = new City("Marsylie", 40000, 0, null, 10, 4);
            fields[11] = new CarDealership("Euro");
            fields[12] = new City("Lyon", 40000, 0, null, 10, 2);
            fields[13] = new Village("Donzy", 20000, 0, null, 1);
            fields[14] = new Chance("Szansa3", listOfChances);
            fields[15] = new City("Antwerp", 50000, 0, null, 10, 3);
            fields[16] = new Village("Farmville", 40000, 0, null, 2);
            fields[17] = new City("Brussels", 50000, 0, null, 10, 7);
            //fields[18] jest wspolne dla każdej mapy
            fields[19] = new Village("Quebec", 0, 20000, null, 2);
            fields[20] = new Village("Smalltown", 0, 20000, null, 3);
            fields[21] = new CarDealership("Dolar");
            fields[22] = new City("Seattle", 0, 45000, null, 10, 7);
            fields[23] = new Village("Ruralville", 0, 25000, null, 4);
            fields[24] = new City("Dallas", 0, 38000, null, 10, 4);
            fields[25] = new City("Miami", 0, 32000, null, 10, 5);
            fields[26] = new Village("Countryside", 0, 30000, null, 6);
            //fields[27] jest wspolne dla każdej mapy
            fields[28] = new City("Denver", 0, 42000, null, 10, 6);
            fields[29] = new Village("Hamlet", 0, 23000, null, 1);
            fields[30] = new City("Atlanta", 0,37000, null, 10, 5);
            fields[31] = new City("Minneapolis", 0, 35000, null, 10, 5);
            fields[32] = new Village("Villageton", 0, 25000, null, 2);
            fields[33] = new CarDealership("Dolar");
            fields[34] = new Chance("Szansa3", listOfChances);
            fields[35] = new Village("Hometown", 0, 18000, null, 3);

        }
        if(boardNumber==2) {
            fields[1] = new Village("Leirose", 20000, 0, null, 4);
            fields[2] = new Chance("Szansa1", listOfChances);
            fields[3] = new Chance("Szansa2", listOfChances);
            fields[4] = new CarDealership("Euro");
            fields[5] = new City("Sevilla", 30000, 0, null, 10, 7);
            fields[6] = new City("Barcelona", 40000, 0, null, 10, 9);
            fields[7] = new Chance("Szansa2", listOfChances);
            fields[8] = new City("Madryt", 40000, 0, null, 10, 9);
            //fields[9] jest wspolne dla każdej mapy
            fields[10] = new City("Marsylie", 40000, 0, null, 10, 4);
            fields[11] = new Chance("Szansa2", listOfChances);
            fields[12] = new City("Lyon", 40000, 0, null, 10, 2);
            fields[13] = new Village("Donzy", 20000, 0, null, 5);
            fields[14] = new Chance("Szansa3", listOfChances);
            fields[15] = new City("Antwerp", 50000, 0, null, 10, 3);
            fields[16] = new Village("Farmville", 400, 1500, null, 6);
            fields[17] = new City("Brussels", 50000, 0, null, 10, 7);
            //fields[18] jest wspolne dla każdej mapy
            fields[19] = new City("Seattle", 0, 45000, null, 10, 7);
            fields[20] = new Village("Smalltown", 0, 2000, null, 7);
            fields[21] = new Chance("Szansa2", listOfChances);
            fields[22] = new City("Seattle", 0, 45000, null, 10, 7);
            fields[23] = new Village("Ruralville", 0, 1000, null, 8);
            fields[24] = new City("Dallas", 0, 38000, null, 10, 4);
            fields[25] = new Chance("Szansa2", listOfChances);
            fields[26] = new Village("Countryside", 0, 3000, null, 8);
            //fields[27] jest wspolne dla każdej mapy
            fields[28] = new City("Denver", 0, 42000, null, 10, 6);
            fields[29] = new Village("Hamlet", 0, 800, null, 9);
            fields[30] = new City("Atlanta", 0,37000, null, 10, 5);
            fields[31] = new City("Minneapolis", 0, 35000, null, 10, 5);
            fields[32] = new Village("Villageton", 0, 2500, null, 9);
            fields[33] = new CarDealership("Dolar");
            fields[34] = new Chance("Szansa3", listOfChances);
            fields[35] = new City("Washington", 0, 35000, null, 10, 5);
        }
        if(boardNumber==3) {
            fields[1] = new Village("Leirose", 20000, 0, null, 1);
            fields[2] = new Village("Waldau", 20000, 0, null, 1);
            fields[3] = new City("Lizbone", 30000, 0, null, 10, 8);
            fields[4] = new Village("Flein", 20000, 0, null, 2);
            fields[5] = new City("Sevilla", 30000, 0, null, 10, 7);
            fields[6] = new Village("Los Cabezudos", 20000, 0, null, 2);
            fields[7] = new Chance("Szansa2", listOfChances);
            fields[8] = new City("Madryt", 40000, 0, null, 10, 9);
            //fields[9] jest wspolne dla każdej mapy
            fields[10] = new City("Marsylie", 40000, 0, null, 10, 4);
            fields[11] = new CarDealership("Euro");
            fields[12] = new Village("Talheim", 20000, 0, null, 3);
            fields[13] = new Village("Donzy", 20000, 0, null, 3);
            fields[14] = new Chance("Szansa3", listOfChances);
            fields[15] = new City("Antwerp", 50000, 0, null, 10, 3);
            fields[16] = new Village("Farmville", 400, 1500, null, 4);
            fields[17] = new City("Brussels", 50000, 0, null, 10, 7);
            //fields[18] jest wspolne dla każdej mapy
            fields[19] = new Village("Quebec", 0, 20000, null, 4);
            fields[20] = new Village("Smalltown", 0, 2000, null, 5);
            fields[21] = new CarDealership("Dolar");
            fields[22] = new City("Seattle", 0, 45000, null, 10, 7);
            fields[23] = new Village("Ruralville", 0, 1000, null, 5);
            fields[24] = new City("Dallas", 0, 38000, null, 10, 4);
            fields[25] = new Village("Farmside", 0, 1000, null, 6);
            fields[26] = new Village("Countryside", 0, 3000, null, 6);
            //fields[27] jest wspolne dla każdej mapy
            fields[28] = new City("Denver", 0, 42000, null, 10, 6);
            fields[29] = new Village("Hamlet", 0, 800, null, 1);
            fields[30] = new Village("Pursley", 0, 1000, null, 1);
            fields[31] = new City("Minneapolis", 0, 35000, null, 10, 5);
            fields[32] = new Village("Villageton", 0, 2500, null, 2);
            fields[33] = new Village("Purdon", 0, 1000, null, 2);
            fields[34] = new Chance("Szansa3", listOfChances);
            fields[35] = new Village("Hometown", 0, 1800, null, 3);
        }
        if(boardNumber==4) {
            fields[1] = new Village("Leirose", 20000, 0, null, 3);
            fields[2] = new City("Berlin", 30000, 0, null, 10, 7);
            fields[3] = new City("Amsterdam", 40000, 0, null, 10, 9);
            fields[4] = new CarDealership("Euro");
            fields[5] = new City("Sevilla", 30000, 0, null, 10, 7);
            fields[6] = new City("Barcelona", 40000, 0, null, 10, 9);
            fields[7] = new Chance("Szansa2", listOfChances);
            fields[8] = new City("Madryt", 40000, 0, null, 10, 9);
            //fields[9] jest wspolne dla każdej mapy
            fields[10] = new City("Marsylie", 40000, 0, null, 10, 4);
            fields[11] = new City("Frankfurt", 30000, 0, null, 10, 7);
            fields[12] = new City("Lyon", 40000, 0, null, 10, 2);
            fields[13] = new City("Wien", 30000, 0, null, 10, 7);
            fields[14] = new Chance("Szansa3", listOfChances);
            fields[15] = new City("Antwerp", 50000, 0, null, 10, 3);
            fields[16] = new Village("Farmville", 400, 1500, null, 4);
            fields[17] = new City("Brussels", 50000, 0, null, 10, 7);
            //fields[18] jest wspolne dla każdej mapy
            fields[19] = new City("Seattle", 0, 45000, null, 10, 7);
            fields[20] = new City("Los Agenles", 0, 45000, null, 10, 7);
            fields[21] = new Chance("Szansa2", listOfChances);
            fields[22] = new City("Seattle", 0, 45000, null, 10, 7);
            fields[23] = new City("Florida", 0, 45000, null, 10, 7);
            fields[24] = new City("Dallas", 0, 38000, null, 10, 4);
            fields[25] = new CarDealership("Dolar");
            fields[26] = new Village("Countryside", 0, 3000, null, 5);
            //fields[27] jest wspolne dla każdej mapy
            fields[28] = new City("Denver", 0, 42000, null, 10, 6);
            fields[29] = new Village("Hamlet", 0, 800, null, 6);
            fields[30] = new City("Atlanta", 0,37000, null, 10, 5);
            fields[31] = new City("Minneapolis", 0, 35000, null, 10, 5);
            fields[32] = new Village("Villageton", 0, 2500, null, 7);
            fields[33] = new City("Houston", 0, 45000, null, 10, 7);
            fields[34] = new Chance("Szansa3", listOfChances);
            fields[35] = new City("Washington", 0, 35000, null, 10, 5);
        }
    }
    public void movePawn(){
        if(round==0) pawns[0].placePawnOn(players[round].getFieldIndex());
        if(round==1) pawns[1].placePawnOn(players[round].getFieldIndex());
        if(round==2) pawns[2].placePawnOn(players[round].getFieldIndex());
        if(round==3) pawns[3].placePawnOn(players[round].getFieldIndex());
    }
    public void changePlayerLocation(int roll){
        int oldIndex = currentPlayer.getFieldIndex();
        int newIndex = (oldIndex+ roll) % 36;
        if (newIndex<oldIndex){
            currentPlayer.increaseBalance(currentPlayer.getCashPerLap());
        }
        currentPlayer.setFieldIndex(newIndex);
        currentPlayer.setLap(currentPlayer.getLap() + 1);

        int temp = 0;
        if (fields != null){
            for (int i = 0; i < fields.length; i++) {
                if (fields[i] != null){
                    if(fields[i] == currentPlayer.getLocation()){
                        temp = i;
                    }
                }
            }
            currentPlayer.setLocation(fields[(temp + roll)%36]);
        }
    }
    public void SetPlayerLocation(int finalPosition){
        if (fields != null){
            if (finalPosition < 36) {
                currentPlayer.setLocation(fields[(finalPosition)]);
            }
        }
    }
    public void calculateRound(){
        round = moveCounter%players.length;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public static Field[] getFieldsArray(){
        return fields;
    }
    public int getMoveCounter(){
        return moveCounter;
    }
    public void setMoveCounter(int moveCounter){
        this.moveCounter = moveCounter;
    }
    public void incrementMoveCounter(){
        moveCounter++;
    }
    public void decrementMoveCounter(){
        moveCounter--;
    }

    public int getRound(){
        return round;
    }
    public void setRound(int round){
        this.round=round;
    }
    public Player[] getPlayers() {
        return players;
    }

    public double getDollarRate() {
        return dollarRate;
    }

    public double getEuroRate() {
        return euroRate;
    }

    public void setDollarRate(double dollarRate) {
        this.dollarRate = dollarRate;
    }

    public void setEuroRate(double euroRate) {
        this.euroRate = euroRate;
    }

    public static Pawn[] getPawns() {
        return pawns;
    }

    public static void setPawns(Pawn[] pawns) {
        Board.pawns = pawns;
    }

    public int[] ExchangeEURtoUSD(int amount, int euro, Board board) {
        int[] tab = new int[2];
        int dolar = (int) Math.round(euro * dollarRate);
        if (euro <= amount) {
            tab[0] = -euro;
            tab[1] = dolar;
            subjects.notifyObserversDollar(board); // Aktualizujemy kurs waluty Dollara
        }
        return tab;
    }
    public int[] ExchangeUSDtoEUR(int amount, int dolar, Board board){
        int[] tab=new int[2];
        int euro=(int) Math.round(dolar*euroRate);
        if(dolar<=amount){
            tab[0] = euro;
            tab[1] = -dolar;
            subjects.notifyObserversEuro(board); // Aktualizujemy kurs waluty Euro
        }
        return tab;
    }
    public long[] Ranking(){
        long[] scoreboard = new long[players.length];
        for(int i = 0; i < players.length; i++){
            scoreboard[i] = players[i].totalNetWorth(this);
        }
        return scoreboard;
        // scoreboard[i] to laczny majatek Playeri
    }

    public static Chances[] getListOfChances() {
        return listOfChances;
    }
}
