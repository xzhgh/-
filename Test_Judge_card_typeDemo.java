package DouDiZhu;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_Judge_card_typeDemo {
    public static void main(String[] args) {
        int pp = 0;
        while(pp<500) {
            Scanner sc = new Scanner(System.in);
            String x = sc.nextLine();
            player[] players = new player[1];
            players[0] = new player();
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < 54; i++) {
                arr.add(i);
            }
            players[0].setCard(arr);
            int[] a = new int[2];
            System.out.println("手牌序号:  "+deal.Judge_card_type(x,players,0,a));
            deal.PrintInt(a[0]);
            System.out.println("x:  "+a[1]);
            pp += 1;
        }
    }
}
