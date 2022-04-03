package algorithm;


//字典树
public class Trie {
    public static void main(String[] args) {
        Node root = new Node();
        root.create("he","her","she","hello");
        bianli(root);

    }

    public static void bianli(Node root) {
        if(root == null) {
            return;
        }

        for(int i= 0; i < root.nodes.length; i++) {
            if(root.nodes[i] != null) {
                System.out.println((char)('a'+i)+"flag="+ root.nodes[i].flag);
                bianli(root.nodes[i]);
            }
        }
    }

}

class Node {
    Node[] nodes;
    int flag;

    Node() {
        this.nodes = new Node[26];
    }

    public void create(String ...s) {
        for(String str : s) {
            char[] chars = str.toCharArray();
            int len = chars.length;
            Node root = this;
            for(int i = 0; i<len; i++) {
                int to = chars[i]-'a';
                if(root.nodes[to] == null) {
                    root.nodes[to] = new Node();
                }
                root = root.nodes[to];
            }
            root.flag++;
        }
    }
}
