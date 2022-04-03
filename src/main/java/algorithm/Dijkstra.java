package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Dijkstra {
    public static int dijkstra(int i,int[][] arr) {
        int[] distance = new int[arr.length];
        for(int j =0; j < arr.length; j++) {
            distance[j] = dead;
        }
        distance[i] = 0;
        Set<Integer> already = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        while (already.size()<arr.length) {
            int index = queue.poll();
            for(int j = 0; j < arr[index].length; j++

            ) {
                if(arr[index][j] != dead) {
                    distance[j] = Math.min(distance[index]+arr[index][j],distance[j]);
                    queue.add(j);
                }
            }
            already.add(index);
        }
        int len = 0;
        for(int j = 0; j < distance.length; j++) {
            System.out.println(distance[j]);
            len += distance[j];
        }
        return len;
    }

    private static final Integer dead = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {dead,5,7,dead,dead,dead,2},
                {5,dead,dead,9,dead,dead,3},
                {7, dead,dead,dead,8,dead,dead},
                {dead,9,dead,dead,dead,4,dead},
                {dead,dead,8,dead,dead,5,4},
                {dead,dead,dead,4,5,dead,6},
                {2,3,dead,dead,4,6,dead}};

        System.out.println(dijkstra(2,arr));

    }
}

