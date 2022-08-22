package DouDiZhu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class player {
    private int num;
    private int winnum;
    private float winscore;

    public void Adddata(boolean iswin) {
        num += 1;
        if(iswin==true) winnum += 1;
        winscore = (float)winnum / num;
    }

    public int getNum() {
        return num;
    }

    public int getWinnum() {
        return winnum;
    }

    public float getWinscore() {
        return winscore;
    }

    public boolean isIsdizhu() {
        return Isdizhu;
    }

    private boolean whether;
    boolean[]  judge;
    private int number_card;
    private String name;
    private Boolean IsDiZhu;
    private ArrayList<Integer> card;
    private ArrayList<String> poker;
    private int one_cards;
    private ArrayList<Integer> one_card;//1-13 A-K    14小王    15大王
    private int two_cards;
    private ArrayList<Integer> two_card;
    private int three_cards;
    private ArrayList<Integer>   three_card;
    private int three_and_one_or_two_cards;
    private Dai[][][] three_and_one_or_two_card;
    private int one_straight_five_cards;
    private int[][] one_straight_five_card;//36
    private int two_straight_three_cards;
    private int[][] two_straight_three_card;//45
    private int three_straight_two_cards;
    private int[][] three_straight_two_card;//38
    private int three_straight_two_and_one_or_two_cards;
    private int[][][] three_straight_two_and_one_or_two_card;
    private int four_and_two_cards;
    private Dai[][][] four_and_two_card;

    public void setThree_straight_two_card(int[][] three_straight_two_card) {
        this.three_straight_two_card = three_straight_two_card;
    }

    public Dai[][][] getFour_and_two_card() {
        return four_and_two_card;
    }

    public void setFour_and_two_card(Dai[][][] four_and_two_card) {
        this.four_and_two_card = four_and_two_card;
    }

    public boolean isWhether() {
        return whether;
    }

    public void setWhether(boolean whether) {
        this.whether = whether;
    }

    public int getFour_and_two_cards() {
        return four_and_two_cards;
    }

    public void setFour_and_two_cards(int four_and_two_cards) {
        this.four_and_two_cards = four_and_two_cards;
    }

    public boolean[] getJudge() {
        return judge;
    }

    public void setJudge(boolean[] judge) {
        this.judge = judge;
    }

    public int[][][] getThree_straight_two_and_one_or_two_card() {
        return three_straight_two_and_one_or_two_card;
    }

    public int getThree_straight_two_and_one_or_two_cards() {
        return three_straight_two_and_one_or_two_cards;
    }

    public void setThree_straight_two_and_one_or_two_cards(int three_straight_two_and_one_or_two_cards) {
        this.three_straight_two_and_one_or_two_cards = three_straight_two_and_one_or_two_cards;
    }

    public void setThree_straight_two_and_one_or_two_card(int[][][] three_straight_two_and_one_or_two_card) {
        this.three_straight_two_and_one_or_two_card = three_straight_two_and_one_or_two_card;
    }

    private int four_cards;
    private ArrayList<Integer> four_card;//13
    private boolean rocket;

    public int getNumber_card() {
        return number_card;
    }

    public int getOne_cards() {
        return one_cards;
    }

    public void setOne_card(ArrayList<Integer> one_card) {
        this.one_card = one_card;
    }

    public ArrayList<Integer> getTwo_card() {
        return two_card;
    }

    public void setTwo_card(ArrayList<Integer> two_card) {
        this.two_card = two_card;
    }

    public ArrayList<Integer> getThree_card() {
        return three_card;
    }

    public void setThree_card(ArrayList<Integer> three_card) {
        this.three_card = three_card;
    }

    public void setOne_straight_five_cards(int one_straight_five_cards) {
        this.one_straight_five_cards = one_straight_five_cards;
    }

    public int[][] getOne_straight_five_card() {
        return one_straight_five_card;
    }

    public void setOne_straight_five_card(int[][] one_straight_five_card) {
        this.one_straight_five_card = one_straight_five_card;
    }

    public int[][] getTwo_straight_three_card() {
        return two_straight_three_card;
    }

    public void setTwo_straight_three_card(int[][] two_straight_three_card) {
        this.two_straight_three_card = two_straight_three_card;
    }

    public int[][] getThree_straight_two_card() {
        return three_straight_two_card;
    }

    public void setThree_staight_two_card(int[][] three_staight_two_card) {
        this.three_straight_two_card = three_staight_two_card;
    }

    public boolean getIsdizhu() {
        return Isdizhu;
    }

    public void setIsdizhu(boolean isdizhu) {
        Isdizhu = isdizhu;
    }

    public int getWinNumner() {
        return WinNumner;
    }

    public void setWinNumner(int winNumner) {
        WinNumner = winNumner;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setNumber_card(int number_card) {
        this.number_card = number_card;
    }

    public void setDiZhu(Boolean diZhu) {
        IsDiZhu = diZhu;
    }

    public ArrayList<Integer> getOne_card() {
        return one_card;
    }

    public void setOne_cards(int one_cards) {
        this.one_cards = one_cards;
    }

    public int getTwo_cards() {
        return two_cards;
    }

    public void setTwo_cards(int two_cards) {
        this.two_cards = two_cards;
    }

    public int getThree_cards() {
        return three_cards;
    }

    public void setThree_cards(int three_cards) {
        this.three_cards = three_cards;
    }

    public int getThree_and_one_or_two_cards() {
        return three_and_one_or_two_cards;
    }

    public void setThree_and_one_or_two_cards(int three_and_one_or_two_cards) {
        this.three_and_one_or_two_cards = three_and_one_or_two_cards;
    }

    public Dai[][][] getThree_and_one_or_two_card() {
        return three_and_one_or_two_card;
    }

    public void setThree_and_one_or_two_card(Dai[][][] three_and_one_or_two_card) {
        this.three_and_one_or_two_card = three_and_one_or_two_card;
    }

    public int getOne_straight_five_cards() {
        return one_straight_five_cards;
    }

    public void setOne_straight_five_card(int one_straight_five_cards) {
        this.one_straight_five_cards = one_straight_five_cards;
    }

    public int getTwo_straight_three_cards() {
        return two_straight_three_cards;
    }

    public void setTwo_straight_three_cards(int two_straight_three_cards) {
        this.two_straight_three_cards = two_straight_three_cards;
    }

    public int getThree_straight_two_cards() {
        return three_straight_two_cards;
    }

    public void setThree_straight_two_cards(int three_straight_two_cards) {
        this.three_straight_two_cards = three_straight_two_cards;
    }

    public ArrayList<Integer> getFour_card() {
        return four_card;
    }

    public void setFour_card(ArrayList<Integer> four_card) {
        this.four_card = four_card;
    }

    public int getFour_cards() {
        return four_cards;
    }

    public void setFour_cards(int four_cards) {
        this.four_cards = four_cards;
    }

    public boolean isRocket() {
        return rocket;
    }

    public void setRocket(boolean rocket) {
        this.rocket = rocket;
    }

    private boolean Isdizhu;
    private int WinNumner;
    private float score;

    public player() {
        name = "玩家";
        Isdizhu = false;

    }

    public player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDiZhu(Boolean diZhu) {
        IsDiZhu = diZhu;
    }

    public String getName() {
        return name;
    }

    public Boolean getDiZhu() {
        return IsDiZhu;
    }

    public player(ArrayList<Integer> card, ArrayList<String> poker) {
        this.card = card;
        this.poker = poker;
    }

    public ArrayList<String> getPoker() {
        return poker;
    }

    public void setPoker(ArrayList<String> poker) {
        this.poker = poker;
    }
    public void AddPoker_and_Card(ArrayList<Integer> buttom, Map<Integer,String> map) {
//        for (int i = 0; i < 3; i++) {
//            poker.add(map.get(buttom.get(i)));
//        }
        for (int i = 0; i < 3; i++) {
            card.add(buttom.get(i));
        }
        for (int i = 0; i < card.size()-1; i++) {
            for (int j = i+1; j < card.size(); j++) {
                if(card.get(i)>card.get(j)) {
                    Integer m = card.get(i);
                    card.set(i,card.get(j));
                    card.set(j,m);
                }
            }
        }
        ArrayList<String> mapp = new ArrayList<>();
        for (int i = 0; i < card.size(); i++) {
            mapp.add(map.get(card.get(i)));
        }
        setPoker(mapp);


//        ArrayList<String> mapp1 = new ArrayList<>();
//        ArrayList<Integer> ccccc = new ArrayList<>();
//        for (int i = 0; i < poker.size(); i++) {
//            ccccc.add(i);
//            mapp1.add(map.get(i));
//        }
//        setPoker(mapp1);
//        setCard(ccccc);
    }
    public ArrayList<Integer> getCard() {
        return card;
    }
    public Integer getcard(int i) {
        return card.get(i);
    }

    public void setCard(ArrayList<Integer> card) {
        this.card = card;
    }
    public void AddCard(ArrayList<Integer> buttom) {
        for (int i = 0; i < 3; i++) {
            card.add(buttom.get(i));
        }
    }
    public void AddCard(Integer i) {
        if(this.card==null) {
            card = new ArrayList<Integer>();
        }
        this.card.add(i);
    }
    public void sort() {
        Collections.sort(this.card);
    }
}
