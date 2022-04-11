package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }


    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        while (!queue.isEmpty()) {
            int cur = 0;
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                Node node = queue.poll();
                list.add(node.val);
                size--;
                if(node.children == null || node.children.size() == 0) {
                    continue;
                }
                for(int i = 0; i< node.children.size(); i++) {
                    if(node.children.get(i) != null) {
                        queue.add(node.children.get(i));
                        cur++;
                    }
                }
            }
            size = cur;
            res.add(list);
        }
        return res;
    }
}
