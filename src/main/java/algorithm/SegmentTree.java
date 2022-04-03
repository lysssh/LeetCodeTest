package algorithm;

import java.util.Arrays;

public class SegmentTree {
    static class Node {
        int left,right;//数组区间【left,right】
        long lazy;//懒标记
        long sum;//区间和

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", lazy=" + lazy +
                    ", sum=" + sum +
                    '}';
        }
    }

    Node[] tree;
    int[] arr;

    SegmentTree(int[] arr) {
        this.tree = new Node[tree_len(arr.length)];
        this.arr = arr;
        creat_tree(0,0, arr.length-1);
    }

    private int tree_len(int size) {
        int len = 1;
        while (len < size) {
            len *= 2;
        }
        return len*2-1;
    }

    private void creat_tree(int node,int start,int end) {
        if(tree[node] == null) {
            tree[node] = new Node();
        }
        tree[node].left = start;
        tree[node].right = end;
        if(start == end) {
            tree[node].sum = arr[start];
            return;
        }
        int mid = start + (end - start)/2;
        int left_node = (node << 1) + 1;
        int right_node = (node << 1) + 2;
        creat_tree(left_node,start,mid);
        creat_tree(right_node,mid+1,end);
        tree[node].sum = tree[left_node].sum + tree[right_node].sum;
    }
    //区间修改
    public void update_tree(int node,int left,int right,long val) {
        if(left <= tree[node].left && right >= tree[node].right) {
            tree[node].lazy += val;
            tree[node].sum += (tree[node].right - tree[node].left + 1) * val;
            return;
        }
        update_children(node);
        int mid = tree[node].left + ((tree[node].right - tree[node].left) >> 1);
        int left_node = (node << 1) + 1;
        int right_node = (node << 1) + 2;
        if(left <= mid) {
            update_tree(left_node,left,right,val);
        }
        if(right > mid) {
            update_tree(right_node,left,right,val);
        }
        tree[node].sum = tree[left_node].sum + tree[right_node].sum;
    }
    //根据懒标记，完成之前的修改
    public void update_children(int node) {
        if(tree[node].lazy > 0) {
            int left_node = (node << 1) + 1;
            int right_node = (node << 1) + 2;
            tree[left_node].lazy += tree[node].lazy;
            tree[left_node].sum += (tree[left_node].right - tree[left_node].left + 1) * tree[left_node].lazy;
            tree[right_node].lazy += tree[node].lazy;
            tree[right_node].sum += (tree[right_node].right - tree[right_node].left + 1) * tree[right_node].lazy;
            tree[node].lazy = 0;
        }
    }


    public long query_tree(int node,int left,int right) {
        if(tree[node].left >= left && tree[node].right <= right) {
            return tree[node].sum;
        }
        int mid = tree[node].left + ((tree[node].right - tree[node].left) >> 1);
        int left_node = (node << 1) + 1;
        int right_node = (node << 1) + 2;
        long sum = 0;
        if(left <= mid) {
            sum += query_tree(left_node,left,right);
        }

        if(right > mid) {
            sum += query_tree(right_node,left,right);
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,7,9,11,13,15,17,19};
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println(Arrays.toString(segmentTree.tree));
    }
}
