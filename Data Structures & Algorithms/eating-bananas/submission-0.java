class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Find maximum pile
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int ans = high;

        while (low <= high) {

            int k = low + (high - low) / 2;

            long hours = 0;

            // Calculate hours needed at speed k
            for (int pile : piles) {
                hours += (pile + k - 1) / k;

                // No need to continue if already too slow
                if (hours > h) {
                    break;
                }
            }

            if (hours <= h) {
                // k works, try a smaller speed
                ans = k;
                high = k - 1;
            } else {
                // k is too slow, increase speed
                low = k + 1;
            }
        }

        return ans;
    }
}