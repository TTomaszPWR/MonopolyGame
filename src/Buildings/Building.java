package Buildings;

public abstract class Building {
    private int priceOfUpgrade;
    private int revenuePerVisit;
    private int level;
    public Building(int priceOfUpgrade, int revenuePerVisit, int level) {
        this.priceOfUpgrade = priceOfUpgrade;
        this.revenuePerVisit = revenuePerVisit;
        this.level = level;
    }
    public Building() {
        this.priceOfUpgrade = 5000;
        this.revenuePerVisit = 3000;
        this.level = 1;
    }
    public void upgrade() {
        if (this.getLevel() < 5) {
            this.setRevenuePerVisit(2 * this.revenuePerVisit);
            this.level++;
            this.priceOfUpgrade = 2 * this.priceOfUpgrade;
        }
    }
    public int getPriceOfUpgrade() {
        return priceOfUpgrade;
    }
    public int getRevenuePerVisit() {
        return revenuePerVisit;
    }
    public void setPriceOfUpgrade(int priceOfUpgrade) {
        this.priceOfUpgrade = priceOfUpgrade;
    }
    public void setRevenuePerVisit(int revenuePerVisit) {
        this.revenuePerVisit = revenuePerVisit;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String toString() {
        if (this.level < 5) {
            return this.revenuePerVisit + "," + this.level + "," + this.priceOfUpgrade;
        }
        else {
            return this.revenuePerVisit + "," + this.level + ",null";
        }
    }
}
