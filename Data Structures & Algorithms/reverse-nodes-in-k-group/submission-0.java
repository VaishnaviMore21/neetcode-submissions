class Solution {

    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {

            // Find kth node
            ListNode kth = getKthNode(groupPrev, k);

            // Less than k nodes remaining
            if (kth == null) {
                break;
            }

            // Save next group
            ListNode groupNext = kth.next;

            // Reverse current group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            for (int i = 0; i < k; i++) {

                ListNode temp = curr.next;

                curr.next = prev;

                prev = curr;
                curr = temp;
            }

            // Save old first node
            ListNode oldGroupStart = groupPrev.next;

            // Connect previous group to reversed group
            groupPrev.next = kth;

            // Old first node is now the tail
            groupPrev = oldGroupStart;
        }

        return dummy.next;
    }
}