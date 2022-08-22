package DouDiZhu;

public class Introduce {
    /*
    判断出牌类型时是根据长度判断的，所以出牌时要不能出手牌中没有的牌
    大王是d，小王是x

    思路：
    1.先用deal的public static void Create_card(Map<Integer, String> map)创建卡牌
     0至53点数从大到小排列，若点数相同，按红桃，方片，梅花，黑桃排列
    2.给玩家类数组player[] players开空间
      给阵营数组boolean[] Group开空间
      给int[] xnumber的0索引处随机赋值首第一轮叫牌人的序号，1索引处赋值当前游戏局数
      每过一轮，xnumber的0索引处向后移，1索引处局数加一

     3.发牌
     deal的public static void Hand_out_card(Map<Integer, String> map, Map<Integer, String> buttom02, player[] players,ArrayList<Integer> buttom)
     将0到53的数字依次加入到ArrayList<Integer> card单列集合中，用Collections.shuffle(card)方法将其打乱
     每三张牌分别发给三位玩家，最后三张牌作为底牌，并展示
     4.叫牌
     deal的public static int Call_card(player[] players, int xnumber[],Map<Integer, String> map,ArrayList<Integer> buttom)
     循环三次，电脑随机打出分数，分数最大者为地主，返回地主所在player[] players的序号，并在主函数中进行阵营的分配
     特判3立马停止，三个0则返回3，根据主函数返回值是否为3判断是否需要重新发牌，叫牌
     5.更新手牌中存在的类型
     deal的public static int Update_card(Map<Integer, String> map, player[] players, int i)
     6.自动出牌
     轮到玩家时，若没有手牌比上家大，直接输出“要不起”，若有手牌比上家大，玩家可选择出牌（无关顺序，输出点数即可），可选择不出牌（0）
     大王是d，小王是x，A是a或者1（大小写均可）
     7.每局结束后显示获胜方及获胜玩家

     8.显示总局数，每个玩家的胜局数，胜率
     */
}
