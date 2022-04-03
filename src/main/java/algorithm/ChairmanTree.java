package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChairmanTree {
    static class Node {
        int start;
        int end;
        int sum;
        Node left;
        Node right;
        public Node(){}
        public Node(int start,int end,int sum) {
            this.sum = sum;
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
        }
    }
    Node[] root;//保存每个版本的根节点
    int[] arr;
    int[] sortArr;
    public ChairmanTree(){}
    public ChairmanTree(int len,int[] arr){
        root = new Node[len];
        this.arr = arr;
        sortRepair();
    }

    private void sortRepair() {
        Set<Integer> set = new HashSet<>();
        for(int num : this.arr) {
            set.add(num);
        }
        sortArr = new int[set.size()];
        int i = 0;
        for(int num : set) {
            sortArr[i++] = num;
        }
        Arrays.sort(sortArr);
    }
    private int getId(int num) {
        int left = 0;
        int right = sortArr.length-1;
        while (left < right) {
            int mid = left + ((right-left)>>1);
            if(sortArr[mid] == num) {
                return mid;
            }else if(sortArr[mid] > num) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    public void insert(int start,int end,Node pre,Node node,int num) {
        if(pre != null) {
            copy(pre,node);
        }
        node.sum++;
        if(start == end) {
            return;
        }
        int mid = start + ((end-start)>>1);
        if(num <= mid) {
            node.left = new Node(start,mid,0);
            insert(start,mid,pre == null ? null : pre.left,node.left,num);
        }else {
            node.right = new Node(mid+1,end,0);
            insert(mid+1,end,pre == null ? null : pre.right,node.right,num);
        }
    }
    public void copy(Node pre,Node node) {
        node.right = pre.right;
        node.left = pre.left;
        node.start = pre.start;
        node.end = pre.end;
        node.sum = pre.sum;
    }

    public int query(int start,int end,Node L,Node R,int k) {
        if(start == end) {
            return start;
        }
        int mid = start + ((end-start)>>1);
        int temp = ((R == null || R.left == null) ? 0 : R.left.sum) - ((L == null || L.left == null)?0 : L.left.sum);
        if(temp >= k) {
            return query(start,mid,L == null ? null : L.left,R==null ? null:R.left,k);
        }else {
            return query(mid+1,end,L == null ? null : L.right,R==null ? null:R.right,k-temp);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {25957,6405,15770,26287,26465};
        ChairmanTree chairmanTree = new ChairmanTree(arr.length,arr);
        for(int i = 0; i < arr.length; i++) {
            chairmanTree.root[i] = new Node(0,arr.length-1,0);
            chairmanTree.insert(0,chairmanTree.sortArr.length-1,i == 0 ?null:chairmanTree.root[i-1],chairmanTree.root[i], chairmanTree.getId(arr[i]));
        }
        int[][] lr = new int[][]{{1,1,1},{2,3,1},{3,4,1},{0,1,2},{3,3,1}};
        for(int[] a : lr) {
            int i = chairmanTree.query(0,chairmanTree.sortArr.length-1,a[0]-1>=0 ? chairmanTree.root[a[0]-1]:null,chairmanTree.root[a[1]],a[2]);
            System.out.println(chairmanTree.sortArr[i]);
        }
    }
}
