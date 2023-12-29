package board;
import ChancesAndModifications.Chances;
import java.util.Random;

public class Chance extends Field{
    private final Chances [] listOfChances;
    public Chance(String name, Chances [] listOfChances){
        super(name);
        this.listOfChances = listOfChances;
    }
    public Chances [] getListOfChances(){
        return listOfChances;
    }
    public Chances getChance(int index){
        return listOfChances[index];
    }
    public void drawChance(){
        Random generator = new Random();
        listOfChances[generator.nextInt(listOfChances.length)].doChance();
    }
    public String toString(){
        return "Chance";
    }
}
