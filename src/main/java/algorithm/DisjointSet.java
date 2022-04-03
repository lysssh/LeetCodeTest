package algorithm;

import java.util.Arrays;

//并查集
public class DisjointSet {


    private static void DisjointSet(int[][] edges) {
        int[] parent = new int[edges.length];
        Arrays.fill(parent,-1);

        for(int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];

            if(!union_vertices(x,y,parent)) {
                System.out.println("have cycle"+Arrays.toString(parent));
                return;
            }
        }
        System.out.println("not find cycle");
    }
    //查找
    private static int[] find_root(int[] parent,int x) {
        int x_root = x;
        int len = 0;
        while (parent[x_root] != -1) {
            x_root = parent[x_root];
            len++;
        }
        return new int[]{x_root,len};
    }

    //合并
    private static boolean union_vertices(int x,int y,int[] parent) {
        int[] x_root = find_root(parent,x);
        int[] y_root = find_root(parent,y);

        if(x_root[0]!=y_root[0]) {
            if(x_root[1] < y_root[1]) {
                parent[x_root[0]] = y_root[0];
            }else {
                parent[y_root[0]] = x_root[0];
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0,1},{1,2},{1,3},
                {2,4},{3,4},{2,5}
        };

        DisjointSet(edges);
    }
}
