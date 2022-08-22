package DouDiZhu;

import java.util.ArrayList;

public class Sort {
    public static void sort(ArrayList<Integer> a) {
        int len = a.size();
        for (int i = 0; i < len-1; i++) {
            for(int j=i+1;j<len;j++) {
                if(a.get(i)>a.get(j)) {
                    Integer p = a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,p);
                }
            }
        }
    }
}
