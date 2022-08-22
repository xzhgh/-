package DouDiZhu;
/*
双顺数量有误
单顺数组长度有误
三顺数组长度有误
四带二的数量和数组长度
//三带一数量有误
 */
public class Dai {
    private int big;
    private int small;
    private int bigs;
    private int smalls;

    public Dai(int big, int small, int bigs, int smalls) {
        this.big = big;
        this.small = small;
        this.bigs = bigs;
        this.smalls = smalls;
    }

    public Dai() {
        big = small = bigs = smalls = 0;
    }

    public int getBig() {
        return big;
    }

    public void setBig(int big) {
        this.big = big;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public int getBigs() {
        return bigs;
    }

    public void setBigs(int bigs) {
        this.bigs = bigs;
    }

    public int getSmalls() {
        return smalls;
    }

    public void setSmalls(int smalls) {
        this.smalls = smalls;
    }
}
