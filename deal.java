package DouDiZhu;

import java.util.*;

public class deal {
    /*
     初始化扑克牌  deal.Create_card(Map<Integer,String> map)
     发牌        deal.Hand_out_card(ArrayList<Integer> card,Map<Integer,String> map,Map<Integer,String> buttom,player[] players)
     手牌展示     ShowPoker().show(player[] players, ArrayList<String> buttom)
     发牌        deal.Call_card(player[] players,int xnumber[])
     牌型类型     0.1.2.3.4.5.6.7.8.9
     出牌类型判断  从个数上找
     出牌函数
     出牌展示
     随机出牌类型
     */
    public static void Create_card(Map<Integer, String> map) {
        int index = 2;
        map.put(0, "大王");
        map.put(1, "小王");
        String[] colors = {"♥", "♦", "♣", "♠"};
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        for (String number : numbers) {
            for (String color : colors) {
                map.put(index, color + number);
                index++;
            }
        }
//        System.out.println(map);//控制云台输出的第一行，带=的那一行
    }

    public static void Hand_out_card(Map<Integer, String> map, Map<Integer, String> buttom02, player[] players,ArrayList<Integer> buttom) {
        ArrayList<Integer> card = new ArrayList<>();

        for (int i = 0; i < 54; i++) {
            card.add(i);
        }
        Collections.shuffle(card);//54张牌打乱完毕

        player[] players2 = new player[3];
        for (int i = 0; i < 3; i++) {
            players2[i] = new player();
        }
        //玩家手牌ArrayList集合
        for (int i = 0; i < 51; i++) {
            Integer w = card.get(i);
            if (i % 3 == 0) {
                players2[0].AddCard(w);
            } else if (i % 3 == 1) {
                players2[1].AddCard(w);
            } else {
                players2[2].AddCard(w);
            }
        }
        for (int i = 0; i < 3; i++) {
            players2[i].sort();
        }
        //底牌Map集合
        for (int i = 51; i < 54; i++) {
            Integer x = card.get(i);
            buttom02.put(i - 51, map.get(x));
            buttom.add(card.get(i));
        }


        for (int i = 0; i < 3; i++) {
            //玩家手牌序号排序，默认从小到大
            //第二行到第四行
            ArrayList<String> arr = new ArrayList<String>();
            for (int j = 0; j < 17; j++) {
                arr.add(map.get(players2[i].getcard(j)));
            }
            players2[i].setPoker(arr);
        }
        for (int i = 0; i < 3; i++) {
            players[i].setCard(players2[i].getCard());
            players[i].setPoker(players2[i].getPoker());
        }
    }

    //    public static void Call_card(player[] players, ArrayList<String> buttom) {
//        int person = new Random().nextInt(3);
//        Scanner sc = new Scanner(System.in);
//        switch(person) {
//            case 0:
//                System.out.println("1号玩家,你是否要抢地主(0:不抢,1:抢)");
//                int x = sc.nextInt();
//                if(x==1) {
//                    System.out.println("1号玩家抢到了地主");
//                    return;
//                }
//                else {
//
//                }
//        }
//    }
//    public static boolean  Judge() {
//        return new Random().nextBoolean();
//    }
    public static int Random_nextInt() {
        return new Random().nextInt(0, 3);
    }

    public static int Call_card(player[] players, int xnumber[],Map<Integer, String> map,ArrayList<Integer> buttom) {
        Scanner sc = new Scanner(System.in);
        if (xnumber[1] == 1) {
            xnumber[0] = deal.Random_nextInt();
        } else {
            xnumber[0] = (xnumber[0] + 1) % 3;
        }
        int xx = xnumber[0];
        int player = 0;
        int score_total = 0;
        if (xnumber[1] == 1) {
            System.out.println("系统选定" + players[xx].getName() + "为首轮叫牌人");
        } else {
            System.out.println("本轮" + players[xx].getName() + "首先叫牌");
        }
        int number = 0;
        for (int i = 0; i < 3; i++, xx = (xx + 1) % 3) {
            if(i==0)  number = 0;
            System.out.println(players[xx].getName() + ",你是否要叫牌(1分(1)，2分(2)，3分(3)，不叫(0))");
            int score = 0;
            int pl = 0;
            if (xx == 0) {
                pl = sc.nextInt();
            } else {
                pl = new Random().nextInt(0, 4);
                System.out.println(pl);
            }
            switch (pl) {
                case 0:
                    number += 1;
                    System.out.println("        " + players[xx].getName() + "不叫牌");
                    break;
                case 1:
                    score = 1;
                    if (score > score_total) {
                        player = xx;
                        System.out.println();
                        score_total = score;
                    } else {
                        System.out.println("        " + players[xx].getName() + "叫牌失败");
                    }
                    break;
                case 2:
                    score = 2;
                    if (score > score_total) {
                        player = xx;
                        System.out.println("        " + players[player].getName() + "叫牌2分,叫牌成功");
                        score_total = score;
                    } else {
                        System.out.println("        " + players[xx].getName() + "叫牌失败");
                    }
                    break;
                case 3:
                    player = xx;
                    System.out.println("        " + players[player].getName() + "叫牌3分,叫牌成功");
                    i = 3;
                    break;
                default:
                    break;
            }

            if (pl == 3) {
                player = xx;
                players[player].setIsDiZhu(true);
                break;
            }
            if(number==3) {
                return 3;
            }
        }
        players[player].setNumber_card(20);
        for (int i = 0; i < 3; i++) {
            if(i!=player) {
                players[player].setNumber_card(17);
            }
        }
        players[player].AddPoker_and_Card(buttom, map);
        System.out.println("第" + xnumber[1] + "轮: " + "恭喜" + players[player].getName() + "抢到了地主");
        xnumber[1]++;
        System.out.print("地主:    ");
        Show.ShowMyPoker(players,player);
        return player;
    }

    public static int Update_card(Map<Integer, String> map, player[] players, int i) {
        /*
        数组是0到9
        0~1.单牌                                                1
        1~2.对牌                                                2
        2~3.三张牌                                               3
        3~4.炸弹    四张牌                                        4
        4~5.三带一     三张牌+一张单牌或一对牌                       4 5
        5~6.单顺                                                5 6 7 8 9 10 11 12 13
        6~7.双顺                                                6 8 10 12 14 16 18 20
        7~8.三顺                                                6 9 12 15 18
        8~9.飞机带翅膀  三顺+同数量单牌或对牌                        4 5 8 10 12 15 16 20
        9~10.火箭   大小王                                       2
        10~11四带二
        出牌分两种情况
        1.无需判断上家手牌   没有手牌类型限制，没有出牌数量的限制
        2.需要判断上家手牌   有手牌类型以及出牌数量的限制
         */
        ArrayList<String> mapp = new ArrayList<>();
        for (int j = 0; j < players[i].getCard().size(); j++) {
            mapp.add(map.get(players[i].getCard().get(j)));
        }
        players[i].setPoker(mapp);
        players[i].setNumber_card(players[i].getCard().size());


        if (players[i].getCard().size() <= 0) return 0;
        boolean[] abl = new boolean[11];
        //0.单牌
        ArrayList<Integer> arr02 = new ArrayList<>();
        int a0 = 1;
        int a0w = players[i].getCard().get(0);
        arr02.add(a0w);
        for (int j = 1; j < players[i].getCard().size(); j++) {
            if (a0w < 2) {
                a0w = players[i].getCard().get(j);
                arr02.add(a0w);
                a0 += 1;
            } else if ((players[i].getCard().get(j) - 2) / 4 != (a0w - 2) / 4) {
                a0w = players[i].getCard().get(j);
                arr02.add(a0w);
                a0 += 1;
            }
        }
//        for (int j = 0; j < a0; j++) {
//            arr02.add(players[i].getcard(j));
//        }
        players[i].setNumber_card(arr02.size());
        players[i].setOne_card(arr02);
        players[i].setOne_cards(a0);
        if (a0 > 0) abl[0] = true;
        else abl[0] = false;
//        ArrayList<String> ss0 = new ArrayList<>();
//        for (int j = 0; j < players[i].getCard().size(); j++) {
//            ss0.add(map.get(players[i].getCard().get(j)));
//        }
//        players[i].setPoker(ss0);


        //1.对牌
        ArrayList<Integer> arr1 = new ArrayList<>();
        int a1 = 0;
        arr1.add(1);
        for (int j = 0; j < players[i].getCard().size() - 1; j++) {
            if ((((players[i].getCard().get(j) - 2) / 4) == (players[i].getCard().get(j + 1) - 2) / 4) && players[i].getCard().get(j) >= 2 && players[i].getCard().get(j + 1) >= 2)
                a1 = 0;
            else a1 = 1;
            arr1.add(a1);
        }
        arr1.add(1);
//        System.out.println(arr1);
//        System.out.println();
        a1 = 0;
        ArrayList<Integer> arr12 = new ArrayList<>();
        for (int j = 0; j < arr1.size() - 1; j++) {
            if (arr1.get(j) == 1 && arr1.get(j + 1) == 0) {
                a1 += 1;
                arr12.add(players[i].getCard().get(j));
            }
        }
        players[i].setTwo_cards(a1);
        players[i].setTwo_card(arr12);
        if (a1 > 0) abl[1] = true;
        else abl[1] = false;


        //2.三张牌
        int a2 = 0;
        int a2_0 = 0;
        ArrayList<Integer> arr22 = new ArrayList<>();
        for (int j = 0; j < arr12.size(); j++) {
            //j为遍历次数，对牌中的索引序号
            a2_0 = arr12.get(j);
            //a2_0为对牌中遍历到的对应的每个牌的序号，元素
            if (players[i].getCard().indexOf(a2_0) < players[i].getCard().size() - 2 && a2_0 > 1) {
                //手牌中搜索对牌中的每个牌的序号 并且不能是大小王
                int w = players[i].getCard().indexOf(a2_0);//手牌中对应对牌中每个牌的索引序号
                if ((players[i].getCard().get(w) - 2) / 4 == ((players[i].getCard().get(w + 2)) - 2) / 4) {
                    a2 += 1;
                    arr22.add(a2_0);
                }
            }
        }
        players[i].setThree_cards(a2);
        players[i].setThree_card(arr22);
        if (a2 > 0) abl[2] = true;
        else abl[2] = false;


        //3.炸弹(四张牌)
        int a3 = 0;
        int a3_0 = 0;
        ArrayList<Integer> arr32 = new ArrayList<>();
        for (int j = 0; j < a2; j++) {
            a3_0 = arr22.get(j);//三张牌中每个牌在扑克牌中的序号
            if (players[i].getCard().indexOf(a3_0) < players[i].getCard().size() - 3 && a3_0 > 1) {
                int w = players[i].getCard().indexOf(a3_0);
                if ((players[i].getCard().get(w) - 2) / 4 == (players[i].getCard().get(w + 3) - 2) / 4) {
                    a3 += 1;
                    arr32.add(a3_0);
                }
            }
        }
        players[i].setFour_cards(a3);
        players[i].setFour_card(arr32);
        if (a3 > 0) abl[3] = true;
        else abl[3] = false;


/*
        4.三带一
        int a41 = a0;
        int a42 = a1;
        int a43 = a2;
        int a4 = 0;
        int flag = 0;
        Dai[][][] arr42 = new Dai[2][][];
        for (int j = 0; j < 2; j++) {
            arr42[j] = new Dai[a2][a0];
        }
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < a2; k++) {
                arr42[j][k] = new Dai[a0];
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < a2; k++) {
                for (int l = 0; l < a0; l++) {
                    arr42[j][k][l] = new Dai();
                }
            }
        }

 */
        //4.三带一
        int a4 = 0;
        //给高维自定义类数组开空间时可以在循环内部搞定
        //循环内部开空间
        //由外到内
        //等式左边所以到需要内存空间的位置
        //等式右边给出所需要开的空间维度,仅仅需要最高维度的大小
        Dai[][][] arr42 = new Dai[2][][];
        for (int j = 0; j < 2; j++) {
            arr42[j] = new Dai[a2][];
            for (int k = 0; k < a2; k++) {
                arr42[j][k] = new Dai[a0];
                for (int l = 0; l < a0; l++) {
                    arr42[j][k][l] = new Dai();
                }
            }
        }

        /*
        for (int j = 0; j < a2; j++) {
            int x41 = 0;
            int x42 = 0;
            int w = arr02.indexOf(arr22.get(j));
            if(w+3<arr02.size()) {
                if((arr02.get(w)-2)/4 == (arr02.get(w+3)-2)/4) {
                    flag = 1;
                }
            }
            if(flag==1) {
                x41 = a41 - 4;
                x42 = a42 - 1;
                a4 += x41 + x42;
            }
            else {
                x41 = a41 - 3;
                x42 = a42 - 1;
                a4 += x41 + x42;
            }
            flag = 0;

        }*/
        //第一位     单排,对牌
        //第二位索引  三张牌
        //第三位索引  单排或多牌

        //每一位进行判断
        int w4_0 = 1000;
        for (int j = 0; j < a2; j++) {//带单牌
            for (int k = 0; k < a0; k++) {
                if ((arr22.get(j) - 2) / 4 != (players[i].getCard().get(k) - 2) / 4) {//保证单牌和三张牌点数不同
                    if ((w4_0 - 2) / 4 != (players[i].getCard().get(k) - 2) / 4) {
                        a4 += 1;
                        arr42[0][j][k].setBig(arr22.get(j));
                        arr42[0][j][k].setSmall(players[i].getCard().get(k));
                        arr42[0][j][k].setBigs(3);
                        arr42[0][j][k].setSmalls(1);
                        w4_0 = players[i].getCard().get(k);
                    }
                }
            }
        }
        for (int j = 0; j < a2; j++) {//带对牌
            for (int k = 0; k < a1; k++) {
                if ((arr22.get(j) - 2) / 4 != (arr12.get(k) - 2) / 4) {
                    a4 += 1;
                    arr42[1][j][k].setBig(arr22.get(j));
                    arr42[1][j][k].setSmall(arr12.get(k));
                    arr42[1][j][k].setBigs(3);
                    arr42[1][j][k].setSmalls(2);
                }
            }
        }

//        for (int j = 0; j < 2; j++) {
//            for (int k = 0; k < a2; k++) {
//                for (int l = 0; l < a0; l++) {
//                    if(arr42[j][k][l].getBig()!=0) {
//                        System.out.println("j: " + j + " k: " + k + " l: " + l + "  " + map.get(arr42[j][k][l].getBig()) + " " + map.get(arr42[j][k][l].getSmall()));
//                    }
//                }
//                System.out.println();
//            }
//            System.out.println("data is upon");
//        }
        players[i].setThree_and_one_or_two_cards(a4);
        players[i].setThree_and_one_or_two_card(arr42);
        if (a4 > 0) abl[4] = true;
        else abl[4] = false;


        //5.单顺
        /*
         0         1          2           3            4               5               6               7
         5(8)      6(7)       7(6)        8(5)         9(4)            10(3)           11(2)           12(1)
        34567     345678    3456789    345678910    345678910J      345678910JQ     345678910JQK    345678910JQK
        45678     456789    45678910   45678910J    45678910JQ      45678910JQK     45678910JQKA
        56789     5678910   5678910J   5678910JQ    5678910JQK      5678910JQKA
        678910    678910J   678910JQ   678910JQK    678910JQKA
        78910J    78910JQ   78910JQK   678910JQKA
        8910JQ    8910JQK   8910JQKA
        910JQK    910JQKA
        10JQKA
        (1+8)*8/2 = 36个
        int[][] arr = new int[8][8];
         */
        int a5 = 0;
        int a55 = 0;
        int[][] arr52 = new int[8][8];
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                arr52[j][k] = 0;
            }
        }
        for (int j = 4; j < 11; j++) {//单顺类型的个数固定
            for (int k = 0; k < a0 - j; k++) {//首位固定
                int l = k + 1;
                int pl = k;
                int cc = 0;
                while (l < a0) {
                    if (players[i].getCard().get(pl) >= 0 && players[i].getCard().get(pl) <= 5) break;
                    if (players[i].getCard().get(l) >= 0 && players[i].getCard().get(l) <= 5) break;
                    if (((players[i].getCard().get(l) - 2) / 4) - ((players[i].getCard().get(pl) - 2) / 4) == 1 && cc < j) {
                        cc += 1;
                        pl = l;
                    }
                    l += 1;
                    if (cc == j) {
//                        System.out.println();
//                        System.out.print(" a5:"+a5);
//                        System.out.print(" j:"+j);
//                        System.out.println(" k:"+k);
//                        System.out.println(" cc:"+cc);
//                        System.out.println(" "+(10-(arr02.get(l-1)-10)/4));
//                        System.out.print(" "+(13-(arr02.get(l-1)-10)/4));
//                        a5 += 1;
                        if (arr52[j - 4][10 - (players[i].getCard().get(l - 1) - 10) / 4] == 13 - (players[i].getCard().get(l - 1) - 10) / 4)
                            break;
                        arr52[j - 4][10 - (players[i].getCard().get(l - 1) - 10) / 4] = 13 - (players[i].getCard().get(l - 1) - 10) / 4;
                        a5 += 1;
                        //2,A,K,Q,J,10,9,8,7,6,5,4,3
                        //    0,1,2,3,4,5,6,7,8,9,10
                        break;
                    }
                }
            }
        }
//        for (int j = 0; j < 8; j++) {
//            for (int k = 0; k < 8; k++) {
//                if(arr52[j][k] != 0) {
//                    a55 += 1;
//                }
//            }
//        }
        players[i].setOne_straight_five_cards(a5);
        players[i].setOne_straight_five_card(arr52);
        if (a5 > 0) abl[5] = true;
        else abl[5] = false;
//        for (int j = 0; j < 8; j++) {
//            for (int k = 0; k < 8; k++) {
//                System.out.print(arr52[j][k] + " ");
//            }
//            System.out.println();
//        }


        //6.双顺
        /*
          0            1                2                 3                   4                     5                       6                           7
          3            4                5                 6                   7                     8                       9                          10
        334455      33445566        3344556677      334455667788        33445566778899      334455667788991010      334455667788991010JJ    334455667788991010JJQQ
        445566      44556677        4455667788      445566778899        4455667788991010    4455667788991010JJ      4455667788991010JJQQ    4455667788991010JJQQKK
        556677      55667788        5566778899      55667788991010      55667788991010JJ    55667788991010JJQQ      55667788991010JJQQKK    55667788991010JJQQKKAA
        667788      66778899        667788991010    667788991010JJ      667788991010JJQQ    667788991010JJQQKK      667788991010JJQQKKAA
        778899      7788991010      7788991010JJ    7788991010JJQQ      7788991010JJQQKK    7788991010JJQQKKAA
        88991010    889991010JJ     88991010JJQQ    88991010JJQQKK      88991010JJQQKKAA
        991010JJ    991010JJQQ      991010JJQQKK    991010JJQQKKAA
        1010JJQQ    1010JJQQKK      1010JJQQKKAA
        JJQQKK      JJQQKKAA
        QQKKAA

         */
        int a6 = 0;
        int[][] arr62 = new int[8][10];
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 10; k++) {
                arr62[j][k] = 0;
            }
        }

        for (int j = 2; j < 9; j++) {//双顺类型的个数固定
            for (int k = 0; k < a1 - j; k++) {//首位固定
                int l = k + 1;
                int pl = k;
                int cc = 0;
                while (l < a1) {
                    if ((arr12.get(l) - 2) / 4 == 0 || (arr12.get(pl) - 2) / 4 == 0) break;
                    if (((arr12.get(l) - 2) / 4) - ((arr12.get(pl) - 2) / 4) == 1 && cc < j) {
                        cc += 1;
                        pl = l;
                    }
                    l += 1;
                    if (cc == j) {
                        a6 += 1;
                        arr62[j - 2][10 - (arr12.get(l - 1) - 10) / 4] = 13 - (arr12.get(l - 1) - 10) / 4;
                        //2,A,K,Q,J,10,9,8,7,6,5,4,3
                        //    0,1,2,3,4,5,6,7,8,9,10,11,12
                        //
                        break;
                    }
                }
            }
        }
//        for (int j = 0; j < 8; j++) {
//            for (int k = 0; k < 10; k++) {
//                System.out.print(arr62[j][k]+" ");
//            }
//            System.out.println();
//        }
        players[i].setTwo_straight_three_cards(a6);
        players[i].setTwo_straight_three_card(arr62);
        if (a6 > 0) abl[6] = true;
        else abl[6] = false;


        //7.三顺
        /*
           0            1                2                3                          4
           2            3                4                5                          6
        333444      333444555       333444555666     333444555666777        333444555666777888
        444555      444555666       444555666777     444555666777888        444555666777888999
        555666      555666777       555666777888     555666777888999        555666777888999101010
        666777      666777888       666777888999     666777888999101010     666777888999101010JJJ
        777888      777888999       77888999101010   777888999101010JJJ     777888999101010JJJQQQ
        888999      888999101010    888999101010JJJ  888999101010JJJQQQ     888999101010JJJQQQKKK
        999101010   999101010JJJ    999101010JJJQQQ  999101010JJJQQQKKK     999101010JJJQQQKKKAAA
        101010JJJ   101010JJJQQQ    101010JJJQQQKKK  101010JJJQQQKKKAAA
        JJJQQQ      JJJQQQKKK       JJJQQQKKKAAA
        QQQKKK      QQQKKKAAA
        KKKAAA
         */
        int a7 = 0;
        int[][] arr72 = new int[5][11];
        /*
        第一位索引+2为三张牌种类数
        第二位索引+3为点数，也是数值
         */
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 11; k++) {
                arr72[j][k] = 0;
            }
        }
        //第一项索引+2 种不同的三张牌
        //第二项+3    为点数
        if(a2>=2) {
            for (int j = 1; j < 6; j++) {//三顺类型的个数固定,需要比较的次数
                //长度为j+1，比较次数为j次
                for (int k = 0; k < a2 - j; k++) {//首位固定,+3是点数
                    int l = k + 1;
                    int pl = k;
                    int cc = 0;
                    if(((arr22.get(l) - 2) / 4) - ((arr22.get(pl) - 2) / 4) != 1)  continue;
                    while (l < a2) {//判断后一项是否越界
                        if ((arr22.get(l) - 2) / 4 == 0 || (arr22.get(pl) - 2) / 4 == 0) break;//2去掉
                        if (((arr22.get(l) - 2) / 4) - ((arr22.get(pl) - 2) / 4) == 1 && cc < j) {
                            cc += 1;
                            pl = l;
                        }//后一项比前一项大一
                        l += 1;
                        if (cc == j) {
                            a7 += 1;
                            arr72[j - 1][10 - (arr22.get(l - 1) - 10) / 4] = 13 - (arr22.get(l - 1) - 10) / 4;
                            //2,A,K,Q,J,10,9,8,7,6,5,4,3
                            //    0,1,2,3,4,5,6,7,8,9,10
                            break;
                        }
                    }
                }
            }
        }
        players[i].setThree_straight_two_cards(a7);
        players[i].setThree_staight_two_card(arr72);
        if (a7 > 0) abl[7] = true;
        else abl[7] = false;

//        System.out.println("arr72:\n");
//        for (int j = 0; j < 5; j++) {
//            for (int k = 0; k < 11; k++) {
//                System.out.print(arr72[j][k]+" ");
//            }
//            System.out.println();
//        }



        //8.飞机带翅膀
        /*
        三顺加上同数量的单牌或对牌
        单或多,数量,三顺点数
        单或多数量2
        数量2~5   2~4
           0~3   0~2
        三顺数量a7


        单:
        4*n
        333444 56
        双:
        5*n
         */
        int a8 = 0;
        int[][][] arr82 = new int[2][4][11];
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 4; k++) {
                for (int l = 0; l < 11; l++) {
                    arr82[j][k][l] = 0;
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            if (j == 0) {//带单
                for (int k = 0; k < 4; k++) {//数量k+2
                    for (int l = 0; l < 11; l++) {//三顺点数+3
                        if (players[i].getCard().size() >= 4 * k + 8 && arr72[k][l] != 0) {
                            arr82[j][k][l] = arr72[k][l];
                            a8 += 1;
                        }
                    }
                }
            } else {//带对
                for (int k = 0; k < 3; k++) {//数量
                    for (int l = 0; l < 11; l++) {
                        if (arr1.size() >= 5 * k + 10 && arr72[k][l] != 0) {
                            arr82[j][k][l] = arr72[k][l];
                            a8 += 1;
                        }
                    }
                }
            }
        }
        players[i].setThree_straight_two_and_one_or_two_cards(a8);
        players[i].setThree_straight_two_and_one_or_two_card(arr82);
        if (a8 > 0) abl[8] = true;
        else abl[8] = false;

//        for (int j = 0; j < 2; j++) {
//            for (int k = 0; k < 4; k++) {
//                for (int l = 0; l < 11; l++) {
//                    System.out.print(arr82[j][k][l] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        }


        //9.火箭
        int a9 = 0;
        if (players[i].getCard().size() >= 2) {
            if (players[i].getCard().get(0) == 0 && players[i].getCard().get(1) == 1) {
                players[i].setRocket(true);
                a9 = 1;
            } else {
                players[i].setRocket(false);
            }
        } else {
            players[i].setRocket(false);
        }
        if (a9 > 0) abl[9] = true;
        else abl[9] = false;


        //10.四带二
//        int a10 = 0;
//        a10 += a3 * (a0-1);
//        a10 += a3 * (a1-1);
//        players[i].setFour_and_two_cards(a10);

        int a10  = 0;
        Dai[][][] arr102= new Dai[2][][];
        for (int j = 0; j < 2; j++) {
            arr102[j] = new Dai[a3][];
            for (int k = 0; k < a3; k++) {
                arr102[j][k] = new Dai[a0];
                for (int l = 0; l < a0; l++) {
                    arr102[j][k][l] = new Dai();
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            if(j==0) {
                for (int k = 0; k < a3; k++) {
                    for (int l = 0; l < a0; l++) {
                        if ((arr32.get(k) - 2) / 4 != (arr02.get(l) - 2) / 4 && arr02.size()>=3) {
                            a10 += 1;
                            arr102[j][k][l].setBig(arr32.get(k));
                            arr102[j][k][l].setSmall(arr02.get(l));
                            arr102[j][k][l].setBigs(4);
                            arr102[j][k][l].setSmalls(1);
                        }
                    }
                }
            }
            else {
                for (int k = 0; k < a3; k++) {
                    for (int l = 0; l < a1; l++) {
                        if ((arr32.get(k) - 2) / 4 != (arr12.get(l) - 2) / 4) {
                            a10 += 1;
                            arr102[j][k][l].setBig(arr32.get(k));
                            arr102[j][k][l].setSmall(arr12.get(l));
                            arr102[j][k][l].setBigs(4);
                            arr102[j][k][l].setSmalls(2);
                        }
                    }
                }
            }
        }
        players[i].setFour_and_two_cards(a10);
        players[i].setFour_and_two_card(arr102);
        int a100 = 0;
        if(players[i].getFour_card().size()>=1&&players[i].getCard().size()>=6) a100 = 1;
        if (a100==1) abl[10] = true;
        else abl[10] = false;

        players[i].setJudge(abl);


        return 1;
    }

    public static int Play_a_hand(player[] players, int i, Map<Integer, String> map, int y, int j, int x,ArrayList<Integer> arrList,boolean change,boolean[] can) {//x是对应的序号,-1为不存在
        int tt = 0;
        can[0] = false;
        switch (j) {
            case 0:
                tt = 0;
                if (players[i].getCard().size() <= 0 || x == -1) break;
                for (int k = players[i].getCard().size()-1; k >= 0; k--) {
                    if(players[i].getCard().get(k)>1) {
                        int a = (players[i].getCard().get(k) - 2) / 4;
                        if (a < (x - 2) / 4) {
                            if(change==true) {
                                arrList.add(players[i].getCard().get(k));
                                players[i].getCard().remove(k);
                                k += 1;
                            }
                            tt += 1;
                        }
                    }
                    else if(players[i].getCard().get(k)==0){
                        if(change==true) {
                            arrList.add(players[i].getCard().get(k));
                            players[i].getCard().remove(k);
                        }
                        tt += 1;
                    }
                    else {
                        if(x==0&&players[i].getCard().get(k)==1) continue;
                        else {
                            if(change==true) {
                                arrList.add(players[i].getCard().get(k));
                                players[i].getCard().remove(k);
                            }
                            tt += 1;
                        }
                    }
                    if (tt == 1) {
                        can[0] = true;
                        break;
                    }
                }
                if(change==true) {
                    Update_card(map,players,i);
                }
                break;
            case 1:
                tt = 0;
                if (players[i].getTwo_card().size() <= 0 || x == -1) break;
                for (int k = players[i].getTwo_card().size() - 1; k >= 0; k--) {
                    int a = (players[i].getTwo_card().get(k) - 2) / 4;
                    if (a < (x - 2) / 4) {
                        for (int l = 0; l < players[i].getCard().size(); l++) {
                            if(players[i].getCard().get(l)==0 || players[i].getCard().get(l)==1) continue;
                            if ((players[i].getCard().get(l) - 2) / 4 == a) {
                                if(change==true) {
                                    arrList.add(players[i].getCard().get(l));
                                    players[i].getCard().remove(l);
                                    l -= 1;
                                }
                                tt += 1;
                            }
                            if (tt == 2) {
                                can[0] = true;
                                break;
                            }
                        }
                    }
                    if (tt == 2) {
                        if(change==true) {
                            Update_card(map, players, i);
                        }
                        break;
                    }
                }
                break;
            case 2:
                tt = 0;
                if (players[i].getThree_card().size() <= 0 || x == -1) break;
                for (int k = players[i].getThree_card().size() - 1; k >= 0; k--) {
                    int a = (players[i].getThree_card().get(k) - 2) / 4;
                    if (a < (x - 2) / 4) {
                        for (int l = 0; l < players[i].getCard().size(); l++) {
                            if(players[i].getCard().get(l)==0 || players[i].getCard().get(l)==1) continue;
                            if ((players[i].getCard().get(l) - 2) / 4 == a) {
                                if(change==true) {
                                    arrList.add(players[i].getCard().get(l));
                                    players[i].getCard().remove(l);
                                    l -= 1;
                                }
                                tt += 1;
                            }
                            if (tt == 3) {
                                can[0] = true;
                                break;
                            }
                        }
                    }
                    if (tt == 3) {
                        if(change==true) {
                            Update_card(map, players, i);
                        }
                        break;
                    }
                }
                break;
            case 3:
                tt = 0;
                if (players[i].getFour_card().size() <= 0 || x == -1) break;
                for (int k = players[i].getFour_card().size()-1; k >= 0; k--) {
                    int a = (players[i].getFour_card().get(k) - 2) / 4;
                    if (a < (x - 2) / 4) {
                        for (int l = 0; l < players[i].getCard().size(); l++) {
                            if(players[i].getCard().get(l)==0 || players[i].getCard().get(l)==1) continue;
                            if ((players[i].getCard().get(l) - 2) / 4 == a) {
                                if(change==true) {
                                    arrList.add(players[i].getCard().get(l));
                                    players[i].getCard().remove(l);
                                    l -= 1;
                                }
                                tt += 1;
                            }
                            if (tt == 4) {
                                can[0] = true;
                                break;
                            }
                        }
                    }
                    if (tt == 4) {
                        if(change==true) {
                            Update_card(map, players, i);
                        }
                        break;
                    }
                }
                break;
            case 4:
                tt = 0;
                if (players[i].getThree_card().size() <= 0) break;
                if(y==5) {
                    if(players[i].getTwo_card().size()<2) break;
                    if((players[i].getThree_card().get(0)-2)/4>(x-2)/4)  break;
                }
                else {
                    if(players[i].getCard().size()<4)  break;
                }
                for (int k = players[i].getThree_card().size()-1; k >= 0 ; k--) {
                    if((players[i].getThree_card().get(k)-2)/4<(x-2)/4) {
                        for (int l = 0; l < players[i].getCard().size(); l++) {
                            if(players[i].getCard().get(l)==0 || players[i].getCard().get(l)==1)  continue;
                            if((players[i].getCard().get(l)-2)/4==(players[i].getThree_card().get(k)-2)/4) {
                                if(change==true) {
                                    arrList.add(players[i].getCard().get(l));
                                    players[i].getCard().remove(l);
                                    l -= 1;
                                }
                                tt += 1;
                            }
                            if(tt==3) break;
                        }
                        if(tt==3) break;
                    }
                }
                if(change==true) {
                    Update_card(map,players,i);
                }
                else {
                    if(tt==3) {
                        if(y==5) {
                            if(players[i].getTwo_card().size()>=2) {
                                can[0] = true;
                                break;
                            }
                        }
                        else {
                            if(players[i].getCard().size()>4) {
                                can[0] = true;
                                break;
                            }
                        }
                    }
                }
                if(tt==3) {
                    if(y==5) {
                        if(players[i].getTwo_card().size()==0) return-1;
                        int mm = players[i].getTwo_card().get(players[i].getTwo_card().size()-1);
                        tt = 0;
                        for (int k = 0; k < players[i].getCard().size(); k++) {
                            if((players[i].getCard().get(k)-2)/4==(mm-2)/4) {
                                arrList.add(players[i].getCard().get(k));
                                players[i].getCard().remove(k);
                                k -= 1;
                                tt += 1;
                            }
                            if(tt==2) {
                                Update_card(map,players,i);
                                break;
                            }
                        }
                    }
                    else {
                        arrList.add(players[i].getCard().get(players[i].getCard().size()-1));
                        players[i].getCard().remove(players[i].getCard().size()-1);
                        Update_card(map,players,i);
                    }
                }
                break;
//                for (int k = 1; k >= 0; k++) {
//                    for (int l = 0; l < players[i].getThree_cards(); l++) {
//                        for (int m = 0; m < players[i].getCard().size(); m++) {
//                            for (int ll = players[i].getThree_and_one_or_two_card()[k][m][l].getBig(); ll >= 0; ll--) {
//                                int a = (ll - 2) / 4;
//                                if (a < (x - 2) / 4) {
//                                    for (int m = 0; m < players[i].getCard().size(); m++) {
//                                        if(players[i].getCard().get(m)==0 || players[i].getCard().get(m)==1) continue;
//                                        int[] arr402 = new int[3];
//                                        if ((players[i].getCard().get(m) - 2) / 4 == m) {
//                                            arr402[tt] = players[i].getCard().get(m);
//                                            players[i].getCard().remove(m);
//                                            tt += 1;
//                                            m -= 1;
//                                        }
//                                        if (tt == 3) {
//                                            for (int n = 0; n < 3; n++) {
//                                                arrList.add(arr402[n]);
//                                            }
//                                            Update_card(map, players, i);
//                                            break;
//                                        }
//                                    }
//                                }
//                                if (k == 1) {
//                                    tt = 0;
//                                    int m = players[i].getCard().get(players[i].getTwo_card().size() - 1);
//                                    for (int n = 0; n < players[i].getCard().size(); n++) {
//                                        if(players[i].getCard().get(n)==0 || players[i].getCard().get(n)==1) continue;
//                                        int[] arr412 = new int[2];
//                                        if ((players[i].getCard().get(n) - 2) / 4 == m) {
//                                            arr412[tt] = players[i].getCard().get(n);
//                                            players[i].getCard().remove(n);
//                                            tt += 1;
//                                            n -= 1;
//                                        }
//                                        if (tt == 2) {
//                                            for (int o = 0; o < 2; o++) {
//                                                arrList.add(arr412[o]);
//                                            }
//                                            break;
//                                        }
//                                    }
//                                } else {
//                                    tt = 0;
//                                    arrList.add(players[i].getCard().get(players[i].getCard().size() - 1));
//                                    players[i].getCard().remove(players[i].getCard().size() - 1);
//                                    tt += 1;
//                                    if (tt == 1) break;
//                                }
//                                if (tt == 1 || tt == 2) {
//                                    break;
//                                }
//                            }
//                            if (tt == 1 || tt == 2) {
//                                Update_card(map, players, i);
//                                break;
//                            }
//                        }
//                    }
//                }
//                break;
            case 5:
                int a = y - 5;
                tt = 0;
                if (players[i].getOne_straight_five_cards() <= 0 || x == -1) break;
                for (int k = 0; k < 8; k++) {
                    int mm = 15 - players[i].getOne_straight_five_card()[a][k];
                    if (mm < (x - 2) / 4 && mm != 15) {//找到单顺的点数最小的手牌mm
                        if(change==true) {
                            int[] arr502 = new int[y];//存储y个数据,存在数组arr502中
                            for (int m = 0; m < players[i].getCard().size(); m++) {//手牌中查找
                                if(players[i].getCard().get(m)==0 || players[i].getCard().get(m)==1) continue;//大小王除掉
                                if ((players[i].getCard().get(m) - 2) / 4 == mm) {//找对应同花色的手牌
                                    arr502[tt] = players[i].getCard().get(m);//加入数组
                                    players[i].getCard().remove(m);//从手牌中删除
                                    tt += 1;//数组下标加一
                                    mm -= 1;//mm变成单顺的下一位点数对应点数的-2/4
                                    m = -1;//根据手牌下标,从头遍历
                                }
                                if (tt == y) {//数组长度为零,就是因为到不了这里,也就是tt和y不相等
                                    for (int l = 0; l < y; l++) {
                                        arrList.add(arr502[l]);
                                    }
                                    break;
                                }
                            }
                            if (tt == y) {
                                Update_card(map, players, i);
                                break;
                            }
                        }
                        else {
                            can[0] = true;
                            break;
                        }
                    }
                }
                break;
            case 6:
                int a6 = y / 2 - 3;//定好纵坐标
                tt = 0;
                if (players[i].getTwo_straight_three_cards() <= 0 || x == -1) break;
                for (int k = 0; k < 10; k++) {
                    int mm = 15 - players[i].getTwo_straight_three_card()[a6][k];
                    if (mm < (x - 2) / 4 && mm != 15) {//确定坐标a6,k
                        if(change==true) {
                            int[] arr602 = new int[y];
                            int xnum = 0;
                            for (int l = 0; l < a6+3; l++) {//取倒数y/2位, 从对牌中依次取
                                tt = 0;
                                for (int n = 0; n < players[i].getCard().size(); n++) {
                                    if(players[i].getCard().get(n)==0 || players[i].getCard().get(n)==1) continue;
                                    if (mm == (players[i].getCard().get(n) - 2) / 4) {
                                        arr602[xnum] = players[i].getCard().get(n);
                                        players[i].getCard().remove(n);
                                        n -= 1;
                                        tt += 1;
                                        xnum += 1;
                                    }
                                    if (tt!=0 && tt%2==0) {
                                        mm -= 1;
                                        break;
                                    }
                                }
                                if (xnum==y) {
                                    for (int m = 0; m < xnum; m++) {
                                        arrList.add(arr602[m]);
                                    }
                                    break;
                                }
                            }
                            Update_card(map,players,i);
                            break;
                        }
                        else {
                            can[0] = true;
                            break;
                        }
                    }
                }
                break;
            case 7:
                //第一项索引+2 种不同的三张牌
                //第二项+3    为点数
                int a7 = y / 3 - 2;//确定位置
                tt = 0;
                if (players[i].getThree_straight_two_cards() <= 0 || x == -1) break;
                for (int k = 0; k < 11; k++) {
                    int mm = 15 - players[i].getThree_straight_two_card()[a7][k];//对应三张牌的-2/4的序号
                    if (mm < (x - 2) / 4 && mm != 15) {//确定坐标a7,k
                        if(change==true) {
                            int[] arr702 = new int[y];//存放y个数据
                            tt = 0;
                            int xnum = 0;
                            for (int l = 0; l < a7+2; l++) {//从三张牌中依次取出，,共y/3种
                                for (int m = 0; m < players[i].getCard().size(); m++) {//从手牌中找同-2/4序号的手牌
                                    if(players[i].getCard().get(m)==0 || players[i].getCard().get(m)==1) continue;
                                    if ((players[i].getCard().get(m) - 2) / 4 == mm) {
                                        arr702[tt] = players[i].getCard().get(m);
                                        players[i].getCard().remove(m);
                                        tt += 1;
                                        m -= 1;
                                        xnum += 1;
                                    }
                                    if (xnum==3) {
                                        xnum = 0;
                                        mm -= 1;
                                        break;
                                    }
                                }
                                if(tt==y) {
                                    for (int p = 0; p < tt; p++) {
                                        arrList.add(arr702[p]);
                                    }
                                    break;
                                }
                            }
                            if(tt==y) {
                                Update_card(map, players, i);
                                break;
                            }
                        }
                        else {
                            can[0] = true;
                            break;
                        }
                    }
                }
                break;
            case 8:

                //第一层索引
                int a83 = 0;
                if (y % 5 == 0) a83 = 2;
                else a83 = 1;

                //第二层索引
                int a82 = 0;
                if (a83 == 1) a82 = y / 4;
                else a82 = y / 5;
                //a82+2为（3+1）或（3+2）的数量
                System.out.println(y);
                int a78 = a82 - 2;
                tt = 0;
                if (players[i].getThree_straight_two_cards() <= 0 || x == -1) break;
                for (int k = 0; k < 11; k++) {
                    int mm = 15 - players[i].getThree_straight_two_card()[a78][k];
                    if (mm < (x - 2) / 4 && mm != 15) {//确定坐标a7,k
                        int[] arr708 = new int[a82*3];
                        int xnum = 0;
                        for (int l = 0; l < a78+2; l++) {
                            tt = 0;
                            for (int m = 0; m < players[i].getCard().size(); m++) {
                                if(players[i].getCard().get(m)==0 || players[i].getCard().get(m)==1) continue;
                                if ((players[i].getCard().get(m) - 2) / 4 == (players[i].getThree_card().get(l) - 2) / 4) {
                                    if(change==true) {
                                        arr708[xnum] = players[i].getCard().get(m);
                                        players[i].getCard().remove(m);
                                    }
                                    tt += 1;
                                    m -= 1;
                                    xnum += 1;
                                }
                                if (tt!=0 && tt%3==0) {
                                    mm -= 1;
                                    break;
                                }
                            }
                            if(tt==a82*3) {
                                if(change==true) {
                                    for (int p = 0; p < tt; p++) {
                                        arrList.add(arr708[p]);
                                    }
                                }
                                break;
                            }
                            if(tt==a82*3)  break;
                        }
                        if(tt==a82*3) break;
                    }
                    if(tt==a82*3) {
                        if(change==true) {
                            Update_card(map, players, i);
                        }
                        break;
                    }
                }

                if(change==false && tt != a82*3) {
                    break;
                }
                if(a83==1) {
                    for (int k = 0; k < a82; k++) {
                        if(change==true) {
                            arrList.add(players[i].getCard().get(players[i].getCard().size()-1));
                            players[i].getCard().remove(players[i].getCard().size()-1);
                        }
                        if(k==a82) {
                            can[0] = true;
                        }
                    }

                }
                else {
                    int k = 0;
                    for (int l = players[i].getTwo_card().size()-1; l >= 0 ; l--) {
                        tt = 0;
                        for (int m = 0; m < players[i].getCard().size(); m++) {
                            if((players[i].getCard().get(m)-2)/4==(players[i].getTwo_card().get(l)-2)/4) {
                                if(change==true) {
                                    arrList.add(players[i].getCard().get(m));
                                    players[i].getCard().remove(m);
                                    m -= 1;
                                }
                                tt += 1;
                            }
                            if(tt==2) break;
                        }
                        k += 1;
                        if(k==a82) {
                            can[0] = true;
                            break;
                        }
                    }
                    if(change==true) {
                        Update_card(map,players,i);
                    }
                }
            case 9:
                arrList.add(0);
                arrList.add(1);
                players[i].getCard().remove(0);
                players[i].getCard().remove(0);
                Update_card(map,players,i);
                return 2;
            case 10:
                tt = 0;
                if (players[i].getFour_cards() <= 0 || x == -1) break;
                for (int k = players[i].getFour_card().size()-1; k >= 0 ; k--) {
                    if((players[i].getFour_card().get(k)-2)/4<(x-2)/4) {//确定可以出牌
                        if(change==true) {
                            for (int l = 0; l < players[i].getCard().size(); l++) {//从手牌中找到那四张牌
                                if((players[i].getCard().get(l)-2)/4==(players[i].getFour_card().get(k)-2)/4) {//比较花色
                                    arrList.add(players[i].getCard().get(l));
                                    players[i].getCard().remove(l);
                                    l -= 1;
                                    tt += 1;
                                }
                                if(tt==4) break;
                            }
                            if(tt==4) break;
                        }
                    }
                }
                if(tt==4) {
                    tt = 0;
                    if(y==8) {
                        int ttt = 1;
                        while(ttt<=2) {
                            int mm = players[i].getTwo_card().get(players[i].getTwo_card().size()-ttt);//找最小两张对牌
                            for (int k = 0; k < players[i].getCard().size(); k++) {//遍历手牌
                                if((players[i].getCard().get(k)-2)/4==(mm-2)/4) {//花色相同
                                    if(change==true) {
                                        arrList.add(players[i].getCard().get(k));
                                        players[i].getCard().remove(k);
                                        k -= 1;
                                    }
                                    tt += 1;
                                }
                                if(tt==2) {
                                    ttt += 1;
                                    break;
                                }
                            }
                            if(ttt==2) {
                                can[0] = true;
                            }
                        }
                    }
                    else {
                        for (int k = 0; k < 2; k++) {
                            if(change==true) {
                                arrList.add(players[i].getCard().get(players[i].getCard().size()-1));
                                players[i].getCard().remove(players[i].getCard().size()-1);
                            }
                            if(k==2) {
                                can[0] = true;
                            }
                        }
                    }
                    if(change==true) {
                        Update_card(map,players,i);
                    }
                }
                break;
            default:
                System.out.println("没有匹配的手牌");
                return -1;
        }
        return arrList.size();
    }

    public static ArrayList<Integer> Judge_card_type(String str,player[] players,int i,int[] data) {
        //str为输入,data[0]表示对应手牌类型的编号,data[1]表示对应手牌类型待比较的数值(统一为最小值)
        /*
        0     不存在
        1     单牌0
        2     对牌1    或    火箭9
        3     三张牌2
        4     炸弹3    或     三带单4
        5     单顺5    或     三带对4
        6     单顺5    或     双顺6      或     三顺7      或     四带二10
        7     单顺5
        8     单顺5    或     双顺6      或     飞机带翅膀8（6+2）
        9     单顺5    或     三顺7
        10    单顺5    或     双顺6      或     飞机带翅膀8（6+4）
        11    单顺5
        12    单顺5    或     双顺6      或      三顺7     或      飞机带翅膀8（9+3）
        13    不存在
        14    双顺6
        15    三顺7    或     飞机带翅膀8（9+6）
        16    双顺6
        17    不存在
        18    双顺6    或     三顺7
        19    不存在
        20    双顺6    或     飞机带翅膀8（12+8）
         */
        int len = str.length();
        ArrayList<Integer> list = new ArrayList<>();
        for (int l = 0; l < len; l++) {
            int x = 0;
            switch(str.charAt(l)) {
                case '2':
                    x = 0;
                    break;
                case '1':
                case 'A':
                case 'a':
                    x = 1;
                    break;
                case 'K':
                case 'k':
                    x = 2;
                    break;
                case 'Q':
                case 'q':
                    x = 3;
                    break;
                case 'J':
                case 'j':
                    x = 4;
                    break;
                case '9':
                    x = 6;
                    break;
                case '8':
                    x = 7;
                    break;
                case '7':
                    x = 8;
                    break;
                case '6':
                    x = 9;
                    break;
                case '5':
                    x = 10;
                    break;
                case '4':
                    x = 11;
                    break;
                case '3':
                    x = 12;
                    break;
                case 'X':
                case 'x':
                    x = -1;
                    break;
                case 'D':
                case 'd':
                    x = -2;
                    break;
                default:
                    x = -3;
                    break;
            }
            if(l+1<len) {
                if( str.charAt(l)=='1' && str.charAt(l+1)=='0') {
                    x = 5;
                    l += 1;
                }
            }
            for (int j = 0; j < players[i].getCard().size(); j++) {
                if(x>=0) {
                    if(players[i].getCard().get(j)==0 || players[i].getCard().get(j)==1) continue;
                    int a1 = 0;
                    int a2 = 0;
                    for (int k = 0; k < players[i].getCard().size(); k++) {
                        if((players[i].getCard().get(k)-2)/4==x) a1 += 1;
                    }
                    for (int k = 0; k < list.size(); k++) {
                        if((list.get(k)-2)/4==x)  a2 += 1;
                    }
                    if(a1<a2) {
                        data[0] = -1;
                        data[1] = -1;
                        return null;
                    }
                    if((players[i].getCard().get(j)-2)/4==x) {
                        int k = list.size();
                        if(k>0 && x==(list.get(k-1)-2)/4) {
                            if(players[i].getCard().get(j)<=list.get(k-1))  continue;
                        }
                        list.add(players[i].getCard().get(j));
                        break;
                    }
                }
                else if(x==-1) {
                    if(players[i].getCard().get(j)==1) {
                        list.add(1);
                        break;
                    }
                }
                else if(x==-2){
                    if(players[i].getCard().get(j)==0) {
                        list.add(0);
                        break;
                    }
                }
                else {
                    System.out.println("出牌有误");
                    data[0] = -1;
                    data[1] = -1;
                    return null;
                }
            }

        }
        len = list.size();
        Sort.sort(list);
        if (len <= 0 || len == 13 || len == 17 || len == 19) {
            data[0] = -1;
            data[1] = -1;
        }
        else if (len == 1) {
            data[0] = 0;
            data[1] = list.get(0);
        }
        else if (len == 2) {
            if ((list.get(0) == 0 && list.get(1) == 1) || (list.get(1) == 0 && list.get(0) == 1)) {
                data[0] =  9;
                data[1] = list.get(1);
            }
            else {
                data[0] =  1;
                data[1] = list.get(0);
            }
        }
        else if (len == 3) {
            data[0] =  2;
            data[1] = list.get(0);
        }
        else if (len == 4) {
            if ((list.get(0) - 2) / 4 == (list.get(1) - 2) / 4 && (list.get(0) - 2) / 4 == (list.get(2) - 2) / 4 && (list.get(0) - 2) / 4 == (list.get(3) - 2) / 4) {
                data[0] =  3;
                data[1] = list.get(0);
            }
            else {
                data[0] =  4;
                data[1] = list.get(0);
                int num = 0;
                int x = (list.get(0)-2)/4;
                for (int j = 0; j < len; j++) {
                    num = 0;
                    if((list.get(j)-2)/4==x) {
                        num += 1;
                    }
                    if(num==2) {
                        data[1] = list.get(j);
                        break;
                    }
                }
            }
        }
        else if (len == 5) {
            if ((list.get(0) - 2) / 4 != (list.get(1) - 2) / 4 && (list.get(1) - 2) / 4 != (list.get(2) - 2) / 4 && (list.get(2) - 2) / 4 != (list.get(3) - 2) / 4 && (list.get(3) - 2) / 4 != (list.get(4) - 2) / 4) {
                data[0] =  5;
                data[1] = list.get(4);
            }
            else {
                data[0] =  4;
                data[1] = list.get(0);
                int num = 0;
                for (int k = 0; k < 3; k++) {
                    int x = (list.get(k)-2)/4;
                    for (int j = 0; j < len; j++) {
                        num = 0;
                        if((list.get(j)-2)/4==x) {
                            num += 1;
                        }
                        if(num==3) {
                            data[1] = list.get(j);
                            break;
                        }
                    }
                }
            }
        }
        else if (len == 6) {
            int num = 0;
            int j = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt>num)  num = tt;
                j += 1;
            }
            if(num==0) {
                data[0] = 5;
                data[1] = list.get(5);
            }
            else if(num==1) {
                data[0] = 6;
                data[1] = list.get(4);
            }
            else if(num==2) {
                data[0] = 7;
                data[1] = list.get(3);
            }
            else {
                data[0] =  10;
                data[1] = list.get(0);
                int num1 = 0;
                for (int k = 5; k >= 3; k--) {
                    int x = (list.get(k)-2)/4;
                    num1 = 0;
                    for (int p = len-1; p >= 0; p--) {
                        if((list.get(p)-2)/4==x) {
                            num1 += 1;
                        }
                        if(num1==4) {
                            data[1] = list.get(p);
                            k = 2;
                            break;
                        }
                    }
                }
            }
        }
        else if (len == 7) {
            data[0] =  5;
            data[1] = list.get(6);
        }
        else if (len == 8) {
            int num = 0;
            int j = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt>num)  num = tt;
                j += 1;
            }
            if(num==0) {
                data[0] = 5;
                data[1] = list.get(7);
            }
            else if(num==1) {
                data[0] = 6;
                data[1] = list.get(6);
            }
            else {
                data[0] = 8;
                for (int k = 7; k >= 5; k--) {
                    int x = (list.get(k)-2)/4;
                    num = 0;
                    for (int p = len-1; p >= 0; p--) {
                        if((list.get(p)-2)/4==x) {
                            num += 1;
                        }
                        if(num==3) {
                            data[1] = list.get(p);
                            break;
                        }
                    }
                }
            }
        }
        else if (len == 9) {
            int num = 0;
            int j = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt>num)  num = tt;
                j += 1;
            }
            if(num==0) {
                data[0] = 5;
                data[1] = list.get(8);
            }
            else {
                data[0] = 7;
                data[1] = list.get(6);
            }
        }
        else if (len == 10) {
            int num = 0;
            int j = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt>num)  num = tt;
                j += 1;
            }
            if(num==0) {
                data[0] = 5;
                data[1] = list.get(9);
            }
            else if(num==1) {
                data[0] = 6;
                data[1] = list.get(8);
            }
            else {
                data[0] =  8;
                int num1 = 0;
                for (int k = 9; k >= 5; k--) {
                    int x = (list.get(k)-2)/4;
                    num1 = 0;
                    for (int p = len-1; p >= 0; p--) {
                        if((list.get(p)-2)/4==x) {
                            num1 += 1;
                        }
                        if(num1==3) {
                            data[1] = list.get(p);
                            k = 4;
                            break;
                        }
                    }
                }
            }
        }
        else if (len == 11) {
            data[0] =  5;
            data[1] = list.get(10);
        }
        else if (len == 12) {
            int num = 0;
            int j = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt>num)  num = tt;
                j += 1;
            }
            if(num==0) {
                data[0] = 5;
                data[1] = list.get(11);
            }
            else if(num==1) {
                data[0] = 6;
                data[1] = list.get(10);
            }
            else if(num==2) {
                data[0] = 7;
                data[1] = list.get(9);
            }
            else {
                data[0] =  8;
                int num1 = 0;
                for (int k = 11; k >= 8; k--) {
                    int x = (list.get(k)-2)/4;
                    num1 = 0;
                    for (int p = len-1; p >= 0; p--) {
                        if((list.get(p)-2)/4==x) {
                            num1 += 1;
                        }
                        if(num1==3) {
                            data[1] = list.get(p);
                            k = 7;
                            break;
                        }
                    }
                }
            }
        }
        else if (len == 14) {
            data[0] =  6;
            data[1] = list.get(12);
        }
        else if (len == 15) {
            int num = 0;
            int j = 0;
            int x = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt==2) x += 1;
                if(tt>num)  num = tt;
                j += 1;
            }
            if (num==2 && x==5) {
                data[0] =  7;
                data[1] = list.get(12);
            }
            else {
                data[0] =  8;
                int num1 = 0;
                for (int k = 14; k >= 8 ; k--) {
                    x = (list.get(k)-2)/4;
                    num1 = 0;
                    for (int p = len-1; p >= 0; p--) {
                        if((list.get(p)-2)/4==x) {
                            num1 += 1;
                        }
                        if(num1==3) {
                            data[1] = list.get(p);
                            k = 7;
                            break;
                        }
                    }
                }
            }
        }
        else if (len == 16) {
            data[0] =  6;
            data[1] = list.get(14);
        }
        else if (len == 18) {
            int num = 0;
            int j = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt>num)  num = tt;
                j += 1;
            }
            if(num==1) {
                data[0] = 6;
                data[1] = list.get(16);
            }
            else {
                data[0] = 7;
                data[1] = list.get(15);
            }
        } else if (len == 20) {
            int num = 0;
            int j = 0;
            while(j< list.size()-1) {
                int tt = 0;
                while(j+1<list.size() && (list.get(j+1)-2)/4==(list.get(j)-2)/4) {
                    tt += 1;
                    j += 1;
                }
                if(tt>num)  num = tt;
                j += 1;
            }
            if(num==1) {
                data[0] = 6;
                data[1] = list.get(18);
            }
            else {
                data[0] =  8;
                int num1 = 0;
                for (int k = 19; k >= 14 ; k--) {
                    int x = (list.get(k)-2)/4;
                    num1 = 0;
                    for (int p = len-1; p >= 0; p--) {
                        if((list.get(p)-2)/4==x) {
                            num1 += 1;
                        }
                        if(num1==3) {
                            data[1] = list.get(p);
                            k = 7;
                            break;
                        }
                    }
                }
                for (int k = 19; k >= 15 ; k--) {
                    int x = (list.get(k)-2)/4;
                    num1 = 0;
                    for (int p = len-1; p >= 0; p--) {
                        if((list.get(p)-2)/4==x) {
                            num1 += 1;
                        }
                        if(num1==4) {
                            data[1] = list.get(p);
                            k = 7;
                            break;
                        }
                    }
                }
            }
        }
        else {
            data[0] =  -1;
            data[1] = -1;
        }
        return list;
    }

    public static int Random_play_a_hand(player[] players, int i) {
        int p = 0;
        int x = 0;
        int num = 0;
        for (int j = 0; j < 11; j++) {
            if (players[i].getJudge()[j] == true) {
                p = 1;
                num += 1;
            }
        }
        if (p == 1) {//有手牌
            if(num>1) {//有除了单牌之外的类型
                x = new Random().nextInt(0, 11);
                while (players[i].getJudge()[x] == false || x==0) {
                    x = (x+1)%11;
                }
            }
            else x = 0;
        }
        else x = -1;
        return x;
    }

    public static int Auto_play_card(player[] players, int i, Map<Integer, String> map, boolean[] Group) {
        int jj = -1;
        int yy = -1;
        int xx = 54;

        int j = -2;
        int y = -2;
        int TT = 1;
        ArrayList<Integer> list = new ArrayList<>();
        int[] a = new int[2];
        boolean[] can = new boolean[1];
        boolean change = true;
        int ttt = 0;
        while (players[0].getCard().size() > 0 && players[1].getCard().size() > 0 && players[2].getCard().size() > 0) {
//            System.out.print(players[i].getName() + "出牌: ");
//            if (Num_group(Group)[0] == 1 && Num_group(Group)[1] == i) {
//                int x = Random_play_a_hand(players, i);
//                if (x != -1) {
//                    x = 0;
//                    Play_a_hand(players, i, map, 54, 1, x);
//                } else continue;
//
//            }
            for (int k = 0; k <2 ; k++) {
                a[k] = -1;
            }
//            while(list.size()>0) {
//                list.remove(list.size() - 1);
//            }
            TT = 1;
            Group[i] = true;
            if(Num_group(Group)[0]==1 && Num_group(Group)[1]==i) {
                //自由出牌
                System.out.print(players[i].getName());
                System.out.print(" 自由出牌: ");
                System.out.println();
                if(i!=0) {//电脑出牌时,j和jj,x和xx内容一样
                    j = Random_play_a_hand(players,i);
                    y = Number_y(players,i,j);
                    System.out.println("j: "+j);
                    System.out.println("y: "+y);
                    Play_a_hand(players,i,map,y,j,54,list,true,can);
                    xx = list.get(0);
                    jj = j;
                    yy = y;
                }
                else {
                    System.out.print("(");
                    for (int k = 0; k < players[i].getCard().size(); k++) {
                        System.out.print(map.get(players[i].getCard().get(k))+" ");
                    }
                    System.out.print(")");
                    System.out.print("        ");
                    Scanner sc = new Scanner(System.in);
                    String str;
                    while(true) {
                        str = sc.nextLine();
                        if(str.length()>0)  break;
                    }
                    list = deal.Judge_card_type(str,players,i,a);
                    xx = a[1];
                    jj = a[0];
                    yy = list.size();
                    System.out.println("jj: "+j);
                    System.out.println("yy: "+y);
                    int kk = 0;
                    for (int k = 0; k < list.size(); k++) {
                        for (int p = 0; p < players[i].getCard().size(); p++) {
                            if(players[i].getCard().get(p)==list.get(k)) {
                                players[i].getCard().remove(p);
                                kk = 1;
                                break;
                            }
                        }
                    }
                    if(kk==1) {
                        Update_card(map,players,i);
                    }
                }
//                System.out.println("arrlen: "+arrlen);
//                System.out.println("list.size(): "+list.size());
//                if(arrlen>0) {
//                System.out.print("j: "+ j + " ");
                Group[i] = true;
                deal.PrintInt(jj);
//                System.out.println("arrlen: "+arrlen);
                for (int k = 0; k < list.size(); k++) {
//                    System.out.print(list.get(k) + " ");
//                    System.out.print("              ");
                    System.out.print(map.get(list.get(k)) + " ");

                }
                System.out.print("长度: "+yy+"      ");
                if(list.size()>=3) System.out.print("----------------------------------------------------------------------------------");
                System.out.println();System.out.println("      剩余牌数:  " + players[i].getCard().size());
                list.clear();
                if(i==0) {
                    System.out.print("          ");
                    for (int k = 0; k < players[i].getCard().size(); k++) {
                        System.out.print(map.get(players[i].getCard().get(k))+" ");
                    }
                    System.out.println();
                }
//                }
//                else {
//                    Group[i] = false;
//                    System.out.println(" 要不起 ");
//                }
            }
            else {
                //上家是jj,yy,xx
                System.out.print(players[i].getName());
                if(jj==9)  TT = 0;
                else {//上家出的不是火箭
                    if(players[i].getJudge()[jj]==true || (jj!=3 && (players[i].getJudge()[3]==true || players[i].getJudge()[9]==true))) {//有同类型手牌或者手里有炸弹,火箭
                        if(i==0) {//自己出牌
                            Play_a_hand(players,i,map,yy,jj,xx,list,false,can);
                            if(can[0]==true  || (jj!=3&&jj!=9&&(players[i].getJudge()[3]==true||players[i].getJudge()[9]==true))) {//有同类型比上家大的手牌,或有炸弹或火箭
                                System.out.print("(");
                                for (int k = 0; k < players[i].getCard().size(); k++) {
                                    System.out.print(map.get(players[i].getCard().get(k)));
                                    if(k<players[i].getCard().size()-1) System.out.print(" ");
                                }
                                System.out.print(")");
                                System.out.print("        ");
                                Scanner sc = new Scanner(System.in);
                                String str;
                                while(true) {
                                    str = sc.nextLine();
                                    if(str.length()>0)  break;
                                }
                                if(str.charAt(0)!='0') {//选择出牌
                                    list = deal.Judge_card_type(str,players,i,a);
                                    if(a[0]!=jj && a[0]!=3 && a[0]!=9) {
                                        System.out.println("牌型不匹配");
                                        continue;
                                    }
                                    jj = a[0];
                                    yy = list.size();
                                    xx = a[1];
                                    int kk = 0;
                                    for (int k = 0; k < list.size(); k++) {
                                        for (int p = 0; p < players[i].getCard().size(); p++) {
                                            if(players[i].getCard().get(p)==list.get(k)) {
                                                players[i].getCard().remove(p);
                                                kk  = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if(kk==1) {
                                        Update_card(map,players,i);
                                    }
                                    Group[i] = true;
                                    System.out.println();
                                    deal.PrintInt(jj);
                                    for (int k = 0; k < list.size(); k++) {
                                        System.out.print(map.get(list.get(k))+" ");
                                    }
                                    if(list.size()>=3) System.out.print("----------------------------------------------------------------------------------");
                                    System.out.println();System.out.println("      剩余牌数:  " + players[i].getCard().size());
                                    list.clear();
//                                    if(i==0) {
//                                        System.out.print("          ");
//                                        for (int k = 0; k < players[i].getCard().size(); k++) {
//                                            System.out.print(map.get(players[i].getCard().get(k))+" ");
//                                        }
//                                        System.out.println();
//                                    }
                                }
                                else {//可以出牌,但是选择了不出牌
                                    TT = 0;
                                    ttt = 1;
                                }
                            }
                            else TT = 0;//没有炸弹,没有火箭,同类型手牌中没有比上家大的
                        }
                        else {//电脑出牌
                            int pp = new Random().nextInt(0,10);
                            if(jj!=3 && (players[i].getJudge()[3]==true || players[i].getJudge()[9]==true) && pp<3) {//上家不是炸弹,并且手里有炸弹或火箭
                                if(players[i].getJudge()[3]==true) {//手牌中有炸弹
                                    jj = 3;
                                    yy = 4;
                                    Play_a_hand(players,i,map,yy,jj,54,list,true,can);
                                    xx = list.get(0);
                                    Group[i] = true;
                                    System.out.println();
                                    deal.PrintInt(jj);
                                    for (int k = 0; k < list.size(); k++) {
                                        System.out.print(map.get(list.get(k))+" ");
                                    }
                                    if(list.size()>=3) System.out.print("----------------------------------------------------------------------------------");
                                    System.out.println();System.out.println("      剩余牌数:  " + players[i].getCard().size());
                                    list.clear();
                                    if(i==0) {
                                        System.out.print("          ");
                                        for (int k = 0; k < players[i].getCard().size(); k++) {
                                            System.out.print(map.get(players[i].getCard().get(k))+" ");
                                        }
                                        System.out.println();
                                    }
                                }
                                else {
                                    jj = 9;
                                    yy = 2;
                                    Play_a_hand(players,i,map,yy,jj,54,list,true,can);
                                    xx = list.get(0);
                                    Group[i] = true;
                                    System.out.println();
                                    deal.PrintInt(jj);
                                    for (int k = 0; k < list.size(); k++) {
                                        System.out.print(map.get(list.get(k))+" ");
                                    }
                                    if(list.size()>=3) System.out.print("----------------------------------------------------------------------------------");
                                    System.out.println();System.out.println("      剩余牌数:  " + players[i].getCard().size());
                                    list.clear();
                                    if(i==0) {
                                        System.out.print("          ");
                                        for (int k = 0; k < players[i].getCard().size(); k++) {
                                            System.out.print(map.get(players[i].getCard().get(k))+" ");
                                        }
                                        System.out.println();
                                    }
                                }
                            }
                            else {
                                Play_a_hand(players,i,map,yy,jj,xx,list,false,can);
                                if(can[0]==true) {
                                    Play_a_hand(players,i,map,yy,jj,xx,list,true,can);
                                    xx = list.get(0);
                                    Group[i] = true;
                                    System.out.println();
                                    deal.PrintInt(jj);
                                    for (int k = 0; k < list.size(); k++) {
                                        System.out.print(map.get(list.get(k))+" ");
                                    }
                                    if(list.size()>=3) System.out.print("----------------------------------------------------------------------------------");
                                    System.out.println();System.out.println("      剩余牌数:  " + players[i].getCard().size());
                                    list.clear();
                                }
                                else TT = 0;
                            }
                        }
                    }
                    else TT = 0;
                }
            }
            if(TT==0) {
                Group[i] = false;
                if(ttt==0) System.out.println(" 要不起 ");
                if(ttt==1) {
                    System.out.print("不出");
                    ttt = 0;
                }
                System.out.println("      剩余牌数:  " + players[i].getCard().size());
                if(i==0) {
                    System.out.print("          ");
                    for (int k = 0; k < players[i].getCard().size(); k++) {
                        System.out.print(map.get(players[i].getCard().get(k))+" ");
                    }
                    System.out.println();
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = (i + 1) % 3;//下一位
        }
        for (int k = 0; k < 3; k++) {
            if(players[k].getCard().size()==0)  return k;
        }
        return -1;
    }

    public static int[] Num_group(boolean[] Group) {
        int arr[] = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = 0;
        }
        int x = 0;
        for (int i = 0; i < 3; i++) {
            if (Group[i] == true) {
                x += 1;
                arr[x] = i;
            }
        }
        arr[0] = x;
        return arr;
    }

    public static int Number_y(player[] players,int i,int j) {
              /*
        数组是0到9
        0~1.单牌                                                1
        1~2.对牌                                                2
        2~3.三张牌                                               3
        3~4.炸弹    四张牌                                        4
        4~5.三带一     三张牌+一张单牌或一对牌                       4 5
        5~6.单顺                                                5 6 7 8 9 10 11 12 13
        6~7.双顺                                                6 8 10 12 14 16 18 20
        7~8.三顺                                                6 9 12 15 18
        8~9.飞机带翅膀  三顺+同数量单牌或对牌                        4 5 8 10 12 15 16 20
        9~10.火箭   大小王                                       2
        10~11四带二
        出牌分两种情况
        1.无需判断上家手牌   没有手牌类型限制，没有出牌数量的限制
        2.需要判断上家手牌   有手牌类型以及出牌数量的限制
         */
        switch(j) {
            case 0:
            case 1:
            case 2:
            case 3:
                return j+1;
            case 4:
                if(players[i].getTwo_card().size()>=2) return 5;
                else return 4;
            case 5:
                for (int k = 7; k >= 0 ; k--) {
                    for (int l = 0; l < 8; l++) {
                        if(players[i].getOne_straight_five_card()[k][l]!=0) return k+5;
                    }
                }
            case 6:
                for (int k = 7; k >= 0 ; k--) {
                    for (int l = 0; l < 10; l++) {
                        if(players[i].getTwo_straight_three_card()[k][l]!=0)  return 2*k+6;
                    }
                }
            case 7:
                for (int k = 4; k >= 0 ; k--) {
                    for (int l = 0; l < 11; l++) {
                        if(players[i].getThree_straight_two_card()[k][l]!=0)  return 3*k+6;
                    }
                }
            case 8:
                for (int k = 1; k >= 0 ; k--) {
                    for (int l = 3; l >= 0; l--) {
                        for (int m = 0; m < 11; m++) {
                            if (players[i].getThree_straight_two_and_one_or_two_card()[k][l][m] != 0) {
                                if (k == 1) return 5 * (l + 2);
                                else return 4 * (l + 2);
                            }
                        }
                    }
                }
            case 9:
                return 2;
            case 10:
                if(players[i].getTwo_card().size()>=3) return 8;
                else return 6;
            default:
                return -1;
        }
    }

    public static void PrintInt(int i) {
        switch(i) {
            case 0:
                System.out.print("单牌        ");
                break;
            case 1:
                System.out.print("对牌        ");
                break;
            case 2:
                System.out.print("三张牌      ");
                break;
            case 3:
                System.out.print("炸弹        ");
                break;
            case 4:
                System.out.print("三带一      ");
                break;
            case 5:
                System.out.print("单顺        ");
                break;
            case 6:
                System.out.print("双顺        ");
                break;
            case 7:
                System.out.print("三顺        ");
                break;
            case 8:
                System.out.print("飞机带翅膀    ");
                break;
            case 9:
                System.out.print("火箭        ");
                break;
            case 10:
                System.out.print("四带二      ");
                break;
            default:
                System.out.print("没有对应的类型");
                break;
        }
    }

    public static void Printplayers(player[] players) {
        System.out.println("总局数:  "+players[0].getNum());
        System.out.println("~~~~~~~~~~~~~~~~各位玩家成绩如下~~~~~~~~~~~~~~~~");
        for (int i = 0; i < 3; i++) {
            System.out.println("姓名:  "+players[i].getName());
            System.out.println("总胜场数:  "+players[i].getWinnum());
            System.out.println("胜率:  "+players[i].getWinscore());
            System.out.println("------------------------------------------------");
        }
    }

}


