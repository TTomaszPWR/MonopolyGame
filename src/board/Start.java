package board;

public class Start extends Field {
    public int startMoney;
    public Start(String name, int startMoney) {
        super(name);
        this.startMoney = startMoney;
    }
    public Start() {
        this.startMoney = 100;
    }
    public int getStartMoney() {
        return startMoney;
    }
    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }
    public String toString() {
        return "Start";
    }

}
