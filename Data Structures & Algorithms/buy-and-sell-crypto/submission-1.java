class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int price : prices) {
            // Track the lowest buying price seen so far
            if (price < minPrice) {
                minPrice = price;
            }

            // Calculate profit if sold today: current price - minimum buy price
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }
}