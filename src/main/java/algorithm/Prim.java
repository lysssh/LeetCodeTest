package algorithm;


import java.util.HashSet;
import java.util.Set;

//修路最短
public class Prim {
    //尽可能选择少的路线，并且每条最小
    public static void prim(int[][] arr,int i) {
        //表示已经访问过的节点
        Set<Integer> set = new HashSet<>();

        set.add(i);

        int h1 = -1;
        int h2 = -1;

        int minWeight = Integer.MAX_VALUE;

        int len = 0;

        for(i = 1; i < arr.length; i++) {
            for(int j : set) {
                for(int k = 0; k < arr.length; k++) {
                    if(!set.contains(k) && arr[j][k] < minWeight) {
                        minWeight = arr[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            if(h1 != -1 && h2 != -1) {
                len += arr[h1][h2];
                arr[h1][h2] = Integer.MAX_VALUE;
                set.add(h2);
                System.out.println(h1+"-->"+h2);
                h1 = h2 = -1;
                minWeight = Integer.MAX_VALUE;
            }

        }
        System.out.println(len);
    }
    private static final Integer dead = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {dead,5,7,dead,dead,dead,2},
                {5,dead,dead,9,dead,dead,3},
                {7, dead,dead,dead,8,2,dead},
                {dead,9,dead,dead,dead,4,dead},
                {dead,dead,8,dead,dead,5,4},
                {dead,dead,2,4,5,dead,6},
                {2,3,dead,dead,4,6,dead}};

        prim(arr,1);
    }
}
