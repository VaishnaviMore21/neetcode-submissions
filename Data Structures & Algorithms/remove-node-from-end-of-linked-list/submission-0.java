class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. Create a dummy node that points to the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        // 2. Move fast n + 1 steps ahead to create a gap of n nodes
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // 3. Move both pointers until fast reaches the end (null)
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // 4. Remove the target node
        slow.next = slow.next.next;
        
        // 5. Return the updated list starting from dummy.next
        return dummy.next;
    }
}