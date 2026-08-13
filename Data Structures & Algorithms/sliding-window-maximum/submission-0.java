

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;

        // Deque stores indices
        Deque<Integer> deque = new ArrayDeque<>();

        int[] result = new int[n - k + 1];

        // First window
        for (int i = 0; i < k; i++) {

            // Remove smaller elements from the back
            while (!deque.isEmpty() &&
                   nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        // Maximum of first window
        result[0] = nums[deque.peekFirst()];

        // Remaining windows
        for (int i = k; i < n; i++) {

            // Remove element outside the window
            if (!deque.isEmpty() &&
                deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove smaller elements
            while (!deque.isEmpty() &&
                   nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);

            // Front contains maximum
            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }
}