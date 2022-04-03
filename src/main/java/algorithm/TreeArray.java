package algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class TreeArray {
    int[] arr;
    int size;

    TreeArray(int x) {
        this.arr = new int[x+1];
        this.size = x+1;
    }

    public static int lowBit(int x) {
        return x & -x;
    }

    public int query(int x) {
        int res = 0;
        while (x > 0) {
            res += this.arr[x];
            x -= lowBit(x);
        }
        return res;
    }

    public void add(int x,int val) {
        this.size = Math.max(size,x+1);
        while (x < this.size) {
           this.arr[x] += val;
           x += lowBit(x);
        }
    }

    public void add(int left,int right,int val) {
        for(int i = left; i <= right; i++) {
            while (i < this.size) {
                this.arr[i] += val;
                i += lowBit(i);
            }
        }
    }

    public void creat(int[] arr) {
        this.size =  arr.length+1;
        for(int i = 0; i < arr.length; i++) {
            this.add(i+1,arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        TreeArray treeArray = new TreeArray(arr.length);
        treeArray.creat(arr);
        System.out.println(Arrays.toString(treeArray.arr));
        Scanner sc = new Scanner(System.in);
        while (true) {
            int num = sc.nextInt();
            if(num == -1) {
                break;
            }

            if(num == 0) {
                int x = sc.nextInt();
                int val = sc.nextInt();
                if(x > arr.length) {
                    System.out.println("数组越界");
                } else {
                    treeArray.add(x,val);
                    System.out.println(Arrays.toString(treeArray.arr));
                }
            }
        }
    }
}
