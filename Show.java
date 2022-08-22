package DouDiZhu;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Map;

public class Show {
    public static void ShowPoker(player[] players, ArrayList<String> buttom) {
        for(int i=0;i<3;i++) {
            System.out.print(players[i].getName() + ":" + players[i].getPoker());
            if(i==1 || i==2) {
                System.out.println("(咱先假装不知道)");
            }else {
                System.out.println();
            }
        }
        System.out.println("底牌:" + buttom);
    }

    public static void ShowPlayer(player[] players, int i, Map<Integer,String> map) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("姓名: " + players[i].getName());
//        System.out.println("手牌序号: " + players[i].getCard());
        System.out.println("手牌: " + players[i].getPoker());
        System.out.print("身份: ");
        if (players[i].getDiZhu() == true) {
            System.out.println("地主");
        } else {
            System.out.println("农民");
        }
        System.out.println("单牌的种类数: " + players[i].getOne_cards());
        System.out.println("单牌的手牌序号: " + players[i].getOne_card());
        System.out.print("单牌手牌: ");
        for (int j = 0; j < players[i].getOne_card().size(); j++) {
            if(players[i].getOne_card().get(j)<2) {
                System.out.print(map.get(players[i].getOne_card().get(j)) + " ");
            }
            else {
                for (int k = 1; k < map.get(players[i].getOne_card().get(j)).length(); k++) {
                    System.out.print((map.get(players[i].getOne_card().get(j))).charAt(k));
                }
                System.out.print(" ");
            }
        }
        System.out.print("\n--------\n");


        System.out.println("对牌的种类数: " + players[i].getTwo_cards());
        System.out.println("对牌手牌序号:　" + players[i].getTwo_card());
        System.out.print("对牌手牌: ");
        for (int j = 0; j < players[i].getTwo_card().size(); j++) {
            if (map.get(players[i].getTwo_card().get(j)).equals("大王") == false && map.get(players[i].getTwo_card().get(j)).equals("小王") == false) {
                for (int k = 1; k < map.get(players[i].getTwo_card().get(j)).length(); k++) {
                    System.out.print((map.get(players[i].getTwo_card().get(j))).charAt(k));
                }
            } else {
                System.out.print(map.get(players[i].getTwo_card().get(j)));
            }
            System.out.print(" ");
        }
        System.out.print("\n--------\n");


        System.out.println("三张牌的种类数: " + players[i].getThree_cards());
//        System.out.println("三张牌手牌序号: " + players[i].getThree_card());
        for (int j = 0; j < players[i].getThree_cards(); j++) {
            for (int k = 1; k < map.get(players[i].getThree_card().get(j)).length(); k++) {
                System.out.print((map.get(players[i].getThree_card().get(j))).charAt(k));
            }
            System.out.print(" ");
        }
        System.out.print("\n--------\n");


        System.out.println("炸弹(四张牌)种类数: " + players[i].getFour_cards());
//        System.out.println("炸弹手牌序号: " + players[i].getFour_card());
        for (int j = 0; j < players[i].getFour_cards(); j++) {
            for (int k = 1; k < map.get(players[i].getFour_card().get(j)).length(); k++) {
                System.out.print(map.get(players[i].getFour_card().get(j)).charAt(k));
            }
            System.out.print(" ");
        }
        System.out.print("\n--------\n");


        System.out.println("三带一的种类数: " + players[i].getThree_and_one_or_two_cards());
        System.out.println("三带一手牌: ");
//        for (int j = 0; j < 2; j++) {
//            for (int k = 0; k < players[i].getTwo_cards(); k++) {
//                for (int l = 0; l < players[i].getOne_cards(); l++) {
//                    if(players[i].getThree_and_one_or_two_card()[j][k][l].getBigs()!=0) {
//                        for (int m = 0; m < players[i].getThree_and_one_or_two_card()[j][k][l].getBigs(); m++) {
//                            System.out.print(players[i].getThree_and_one_or_two_card()[j][k][l].getBig() + " ");
//                        }System.out.print(" ");
//                        for (int m = 0; m < players[i].getThree_and_one_or_two_card()[j][k][l].getSmalls(); m++) {
//                            System.out.print(players[i].getThree_and_one_or_two_card()[j][k][l].getSmall() + " ");
//                        }System.out.println();
//                    }
//                }
//            }
//        }
//        for (int j = 0; j < 2; j++) {
//            for (int k = 0; k < players[i].getThree_cards(); k++) {
//                for (int l = 0; l < players[i].getTwo_cards(); l++) {
//                    for (int m = 0; m < players[i].getThree_and_one_or_two_card()[j][k][l].getBigs(); m++) {
//                           System.out.print(players[i].getThree_and_one_or_two_card()[j][k][l].getBig() + " ");
//                        }System.out.print(" ");
//                }
//                for (int l = 0; l < players[i].getOne_cards(); l++) {
//                    for (int m = 0; m < players[i].getThree_and_one_or_two_card()[j][k][l].getSmalls(); m++) {
//                            System.out.print(players[i].getThree_and_one_or_two_card()[j][k][l].getSmall() + " ");
//                        }System.out.println();
//                }
//            }
//        }
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < players[i].getThree_cards(); k++) {
                for (int l = 0; l < players[i].getOne_cards(); l++) {
                    if(players[i].getThree_and_one_or_two_card()[j][k][l].getBig()!=0) {
                        for (int m = 0; m < players[i].getThree_and_one_or_two_card()[j][k][l].getBigs(); m++) {
                            for (int n = 1; n < map.get(players[i].getThree_and_one_or_two_card()[j][k][l].getBig()).length(); n++) {
                                System.out.print(map.get(players[i].getThree_and_one_or_two_card()[j][k][l].getBig()).charAt(n));
                            }
                        }
                        System.out.print(" ");
                        for (int m = 0; m < players[i].getThree_and_one_or_two_card()[j][k][l].getSmalls(); m++) {
                            if(map.get(players[i].getThree_and_one_or_two_card()[j][k][l].getSmall())=="大王"||map.get(players[i].getThree_and_one_or_two_card()[j][k][l].getSmall())=="小王") {
                                System.out.print(map.get(players[i].getThree_and_one_or_two_card()[j][k][l].getSmall()));
                            }
                            else {
                                for (int n = 1; n < map.get(players[i].getThree_and_one_or_two_card()[j][k][l].getSmall()).length(); n++) {
                                    System.out.print(map.get(players[i].getThree_and_one_or_two_card()[j][k][l].getSmall()).charAt(n));
                                }
                            }
                        }
                        System.out.println();
                    }
                }
            }
        }
        System.out.print("--------\n");



        System.out.println("单顺的种类数: " + players[i].getOne_straight_five_cards());
        System.out.println("单顺手牌: ");
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                int x = players[i].getOne_straight_five_card()[j][k];
                if(x!=0) {
                    int y = 0;
                    while(y <= j+4) {
                        if(x==11) {
                            System.out.print("j ");
                        }
                        else if(x==12) {
                            System.out.print("Q ");
                        }
                        else if(x==13) {
                            System.out.print("K ");
                        }
                        else if(x==14) {
                            System.out.print("A ");
                        }
                        else {
                            System.out.print(x + " ");
                        }
                        x += 1;
                        y += 1;
                    }
                    System.out.println();
                }
            }
        }
//        for (int j = 0; j < 8; j++) {
//            for (int k = 0; k < 8; k++) {
//                System.out.print(players[i].getOne_straight_five_card()[j][k] + " ");
//            }
//            System.out.println();
//        }
        System.out.print("--------\n");



        System.out.println("双顺的种类数: " + players[i].getTwo_straight_three_cards());
        System.out.println("双顺手牌: ");
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 10; k++) {
                int x = players[i].getTwo_straight_three_card()[j][k];
                if(x!=0) {
                    int y = 0;
                    while(y <= j+2) {
                        if(x==11) {
                            System.out.print("JJ");
                        }
                        else if(x==12) {
                            System.out.print("QQ");
                        }
                        else if(x==13) {
                            System.out.print("KK");
                        }
                        else if(x==14) {
                            System.out.print("AA");
                        }
                        else {
                            System.out.print(x + "" + x);
                        }
                        x += 1;
                        y += 1;
                    }
                    System.out.println();
                }
            }
        }
        System.out.print("--------\n");



        //三顺
        System.out.println("三顺的种类数: " + players[i].getThree_straight_two_cards());
        System.out.println("三顺手牌: ");
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 11; k++) {
                int x = players[i].getThree_straight_two_card()[j][k];
                if(x!=0) {
                    int y = 0;
                    while(y < j+2) {
                        if(x==11) {
                            System.out.print("JJJ");
                        }
                        else if(x==12) {
                            System.out.print("QQQ");
                        }
                        else if(x==13) {
                            System.out.print("KKK");
                        }
                        else if(x==14) {
                            System.out.print("AAA");
                        }
                        else {
                            System.out.print(x + "" + x + "" + x);
                        }
                        x += 1;
                        y += 1;
                    }
                    System.out.println();
                }
            }
        }
        System.out.print("--------\n");



        //飞机带翅膀
        //三顺加同数量的单或对牌
        System.out.println("飞机带翅膀中三顺的种类数: " + players[i].getThree_straight_two_cards());
        System.out.println("飞机带翅膀手牌: ");
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 4; k++) {
                for (int l = 0; l < 11; l++) {
                    if(players[i].getThree_straight_two_and_one_or_two_card()[j][k][l]!=0) {
                        int x = players[i].getThree_straight_two_and_one_or_two_card()[j][k][l];
                        for (int m = 0; m < k+2; m++) {
                            for (int n = 0; n < 3; n++) {
                                if(x==11) {
                                    System.out.print("J");
                                }
                                else if(x==12) {
                                    System.out.print("Q");
                                }
                                else if(x==13) {
                                    System.out.print("K");
                                }
                                else if(x==14) {
                                    System.out.print("A");
                                }
                                else {
                                    System.out.print(x+"");
                                }
                            }
                            x += 1;
                        }
                        if(j==0) {
                            System.out.print("  加任意"+(k+2)+"张单牌");
                        }
                        else {
                            System.out.print("  加任意"+(k+2)+"组对牌");
                        }
                        System.out.println();
                    }
//                int x = players[i].getThree_staight_two_card()[j][k];
//                if(x!=0) {
//                    int y = 0;
//                    while(y < j+2) {
//                        if(x==11) {
//                            System.out.print("JJJ");
//                        }
//                        else if(x==12) {
//                            System.out.print("QQQ");
//                        }
//                        else if(x==13) {
//                            System.out.print("KKK");
//                        }
//                        else if(x==14) {
//                            System.out.print("AAA");
//                        }
//                        else {
//                            System.out.print(x + "" + x + "" + x);
//                        }
//                        x += 1;
//                        y += 1;
//                    }
//                    System.out.println();
                }
            }
        }
        System.out.print("--------\n");



        //火箭
        System.out.print("是否有火箭: ");
        if(players[i].getJudge()[9]==true) System.out.println("   是");
        else System.out.println("   否");



        //四带二
        System.out.print("四带二的种类数(不包括花色不同): ");
        System.out.println(players[i].getFour_and_two_cards());
    }

    public static void ShowMyPoker(player[] players,int i) {
        System.out.println(players[i].getName() + ":" + players[i].getPoker());
    }
}
