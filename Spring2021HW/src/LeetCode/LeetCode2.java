package LeetCode;

import java.util.List;

public class LeetCode2 {
    @SuppressWarnings("unused")
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @SuppressWarnings("unused")
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int nowValue = 0;
        ListNode ans = null;
        ListNode p = null;
        while (l1 != null || l2 != null){
            if (l1 != null){
                nowValue += l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                nowValue += l2.val;
                l2 = l2.next;
            }
            if (ans == null){
                ans = new ListNode(nowValue % 10);
                nowValue /= 10;
                p = ans;
            }
            else{
                ListNode node = new ListNode(nowValue % 10);
                nowValue /= 10;
                p.next = node;
                p = node;
            }
        }
        while (nowValue > 0){
            ListNode tem = new ListNode(nowValue % 10);
            p.next = tem;
            p = tem;
            nowValue /= 10;
        }
        return ans;
    }

    public static int getVal(ListNode node){
        int out = 0;
        int level = 1;
        while (node != null){
            out += level * node.val;
            level *= 10;
            node = node.next;
        }
        return out;
    }

    public static ListNode createNode(int val){
        ListNode ans = new ListNode(val % 10);
        ListNode p = ans;
        val /= 10;
        while (val > 0){
            ListNode t = new ListNode(val % 10);
            val /= 10;
            p.next = t;
            p = t;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode t = createNode(99999);
        ListNode t2 = createNode(999);
        System.out.println(getVal(t));
        System.out.println(getVal(addTwoNumbers(t, t2)));
    }
}
