package leetcode.easy;

public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode(0);
        l1.val = 1;
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        l2.val = 1;
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode listNode = mergeTwoLists2(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null || list2==null) return null;

        ListNode result = new ListNode();

        ListNode node1 = list1;
        ListNode node2 = list2;
        ListNode cur = result;
        while(node1 != null || node2 != null){
            if(cur == null) cur = new ListNode();

            if(node1 == null){
                cur.val = node2.val;
                node2 = node2.next;
            }
            else if(node2 == null){
                cur.val = node1.val;
                node1 = node1.next;
            }
            else if(node1.val > node2.val){
                cur.val = node2.val;
                node2 = node2.next;
            }
            else if(node1.val <= node2.val){
                cur.val = node1.val;
                node1 = node1.next;
            }

            if (node1 != null || node2 != null) {
                cur.next = new ListNode();
                cur = cur.next;
            }
        }
        return result;
    }

    //시간복잡도(O(n+m)), 공간 복잡도(O(1))
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        //남아있는 리스트 연결
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
