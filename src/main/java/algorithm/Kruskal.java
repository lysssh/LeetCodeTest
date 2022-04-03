package algorithm;

import java.util.*;

public class Kruskal {
    public static int kruskal(PriorityQueue<EData> priorityQueue,int len) {
        List<EData> list = new ArrayList<>();
        int[] arr = new int[len];
        int size = 0;
        int count = 0;
        while (!priorityQueue.isEmpty() && size<len) {
            EData eData = priorityQueue.poll();
            int end1 = eData.start,end2=eData.end;
            while (arr[end1]!=0) {
                end1 = arr[end1];
            }
            while (arr[end2]!=0) {
                end2 = arr[end2];
            }

            if(end1!=end2) {
                arr[end1] = end2;
                size++;
                count += eData.weight;
                list.add(eData);
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(list.toString());
        return count;
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

        PriorityQueue<EData> priorityQueue = new PriorityQueue<>(new Comparator<EData>() {
            @Override
            public int compare(EData o1, EData o2) {
                return o1.weight-o2.weight;
            }
        });


        for(int i = 0; i < arr.length-1; i++) {
            if(i==6) {
                System.out.println();
            }
            for(int j = i+1; j < arr[i].length; j++) {
                if(arr[i][j]!=dead) {
                    EData eData = new EData(i,j,arr[i][j]);
                    priorityQueue.add(eData);
                }
            }
        }

        System.out.println(kruskal(priorityQueue, 7));
    }
}

class EData {
    int start;
    int end;
    int weight;

    public EData(int start,int end,int weight) {
        this.start = start;
        this.weight = weight;
        this.end = end;
    }


    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
