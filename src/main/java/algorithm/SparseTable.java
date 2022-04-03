package algorithm;

public class SparseTable {
    //d[i][j]: 代表区间[i,i+2^j-1]的最小值
    int[][] d = new int[1000006][25];
    int[] mn = new int[1000006];

    public void rmq_init(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            d[i][0] = arr[i];
        }

        for(int j = 1; (1<<j) < n; j++) {
            for(int i = 0; i+(1<<j)-1<n; i++) {
                d[i][j] = Math.min(d[i][j-1],d[i+(1<<j-1)][j-1]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println((int)Math.pow(2,31));
    }
}
