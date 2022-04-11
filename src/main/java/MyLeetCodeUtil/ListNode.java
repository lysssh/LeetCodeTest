package MyLeetCodeUtil;

public class ListNode {
    public ListNode next;
    public int val;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == 1) {
            return reverseListNode(head,right-left+1);
        }else {
            ListNode cur = head;
            int len = right - left + 1;
            while (cur != null && left > 2) {
                cur = cur.next;
                left--;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur.next = reverseListNode(next,len);
            return head;
        }
    }
    public ListNode reverseListNode(ListNode head, int len) {
        ListNode tail = head;
        ListNode cur = null;
        while (head != null && len > 0) {
            ListNode next = head.next;
            head.next = cur;
            cur = head;
            head = next;
            len--;
        }
        tail.next = head;
        return cur;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode tail = head;
        for(int i = 2; i < 6; i++) {
            ListNode node = new ListNode(i);
            tail.next = node;
            tail = tail.next;
        }
        head.reverseBetween(head,2,4);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
