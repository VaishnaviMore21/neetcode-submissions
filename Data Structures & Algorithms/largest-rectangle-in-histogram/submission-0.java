

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] prevSmaller = getPrevSmaller(heights, n);
        int[] nextSmaller = getNextSmaller(heights, n);
        
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nextSmaller[i] - prevSmaller[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    private int[] getPrevSmaller(int[] heights, int n) {
        int[] ps = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            ps[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ps;
    }

    private int[] getNextSmaller(int[] heights, int n) {
        int[] ns = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            ns[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return ns;
    }
}