package DouDiZhu;

import java.util.*;
//player类的318行注释解开后，方便测试飞机带翅膀等复杂手牌类型
public class mainwork {
    public static void main(String[] args) {
        //创建Map集合,序号作为键,值作为扑克牌
        Map<Integer, String> map = new HashMap<>();


        deal.Create_card(map);


        //发牌
        //给玩家开空间
        //玩家手牌players开空间
        player[] players = new player[3];//players指向一个存有3个players类的内存空间
        for (int i = 0; i < 3; i++) {
            players[i] = new player();//为数组内每个player开辟内存空间
        }



        System.out.println("请输入你的名字");
        Scanner sc = new Scanner(System.in);
        String ss = sc.nextLine();

        players[0].setName(ss);
        players[1].setName("电脑1号");
        players[2].setName("电脑2号");

        //阵营数组
        boolean[] Group = new boolean[3];

        int s = 1;
        //叫牌
        Integer x = 0;//系统选定的首轮叫牌人的编号,在Call_card系统首轮会随机选取一位player
        Integer number = 1;//局数
        int[] xnumber = {0, 1};
        while (s == 1) {

            //给底牌开空间
            Map<Integer, String> buttom01 = new HashMap<>();
            ArrayList<Integer> buttom = new ArrayList<>();


            //发牌
            deal.Hand_out_card(map, buttom01, players,buttom);
            Show.ShowMyPoker(players,0);


            //底牌buttom
            ArrayList<String> buttom02 = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                buttom02.add(i, buttom01.get(i));
            }
            System.out.println("底牌:  ");
            System.out.println(buttom02);
            //牌型展示
//            Show.ShowPoker(players,buttom);


            //阵营分配
            int Dizhu = deal.Call_card(players, xnumber, map,buttom);
            if(Dizhu==3)  continue;
            for (int i = 0; i < 3; i++) {
                Group[i] = false;
            }
            Group[Dizhu] = true;
            for (int i = 0; i < 3; i++) {
                if(Group[i]==false)  players[i].setIsdizhu(false);
                else players[i].setIsdizhu(true);
            }


//            Show.ShowPoker(players, buttom02);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < 3; i++) {
                deal.Update_card(map, players, i);
//                Show.ShowPlayer(players, i, map);
            }
            int winner = deal.Auto_play_card(players, Dizhu, map, Group);//winner是获胜者

            if(players[winner].getIsdizhu()==true) {//地主获胜
                System.out.println("地主: "+players[winner].getName()+" 获胜");
                players[winner].Adddata(true);
                int ttt = 0;
                for (int i = 0; i < 3; i++) {
                    if(i!=winner) {
                        players[i].Adddata(false);
                        System.out.print(players[i].getName());
                        ttt += 1;
                        if(ttt==1) System.out.print(" 和 ");
                        if(ttt==2)  break;
                    }
                }
            }
            else {//农民获胜
                System.out.print("农民: ");
                int ttt = 0;
                for (int i = 0; i < 3; i++) {
                    if(i!=Dizhu) {
                        players[i].Adddata(true);
                        System.out.print(players[i].getName());
                        ttt += 1;
                        if(ttt==1) System.out.print(" 和 ");
                    }
                    if(i==Dizhu) {
                        players[i].Adddata(false);
                    }
                }
                System.out.print("获胜");
            }
            System.out.println();
            System.out.println("请问是否要继续游戏?");
            System.out.println("继续（1），停止（0）");
            s = sc.nextInt();
        }
        deal.Printplayers(players);
            //打印成绩

    }

}
/*
更新方法：
  根据用户手牌序号，更新各个牌型
出牌逻辑：
  最先出牌方  无 牌型限制，无 牌数限制
斗地主总体过程：
  1.创建扑克牌
  2.模拟发牌
  3.抢地主，并发牌
  4.根据地主分配阵营
  5.出牌阶段
  6.根据最先打完手牌的身份，判断获胜阵营
  7.记录每个人分别在两个阵营的总局数，获胜局数，以及胜率
  8.选择是否继续游戏   若继续，返回2 ； 选择否即结束程序
 */
