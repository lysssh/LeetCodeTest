package MyLeetCodeUtil;

import java.security.interfaces.RSAKey;
import java.util.Arrays;
import java.util.Scanner;

public class NumArray {
    int[] segmentArray;
    int len;
    void creat_tree(int node,int left,int right,int[] arr) {
        if(left == right) {
            this.segmentArray[node] = arr[left];
            return;
        }
        int left_node = (node << 1) + 1;
        int right_node = (node << 1) + 2;
        int mid = left + ((right-left) >> 1);
        creat_tree(left_node,left,mid,arr);
        creat_tree(right_node,mid+1,right,arr);
        this.segmentArray[node] = this.segmentArray[left_node] + this.segmentArray[right_node];
    }
    void update_tree(int node,int left,int right,int index,int val) {
        if(index < left || index > right) {
            return;
        }
        if(left == right) {
            this.segmentArray[node] = val;
            return;
        }
        int left_node = (node << 1) + 1;
        int right_node = (node << 1) + 2;
        int mid = left + ((right-left) >> 1);
        update_tree(left_node,left,mid,index,val);
        update_tree(right_node,mid+1,right,index,val);
        this.segmentArray[node] = this.segmentArray[left_node] + this.segmentArray[right_node];
    }

    int query (int node,int left,int right,int L,int R) {
        if(L > right || R < left) {
            return 0;
        }
        if(L <= left && R >= right) {
            return this.segmentArray[node];
        }

        int left_node = (node << 1) + 1;
        int right_node = (node << 1) + 2;
        int mid = left + ((right-left) >> 1);
        int sum = query(left_node,left,mid,L,R);
        sum += query(right_node,mid+1,right,L,R);
        return sum;
    }

    int getLen(int len) {
        int x = 1;
        while (x < len) {
            x = x << 1;
        }
        return (x << 1) -1;
    }

    public NumArray(int[] nums) {
        this.len = nums.length;
        this.segmentArray = new int[getLen(len)];
        creat_tree(0,0,len-1, nums);
    }

    public void update(int index, int val) {
        update_tree(0,0,len-1,index,val);
    }

    public int sumRange(int left, int right) {
        return query(0,0,len-1,left,right);
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1,3,5});
        numArray.sumRange(0,2);
    }

}
