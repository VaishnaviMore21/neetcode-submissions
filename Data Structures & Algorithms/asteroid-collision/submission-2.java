
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int a : asteroids) {
            boolean destroyed = false;

            // Collision condition: Top moves right (> 0) and incoming moves left (< 0)
            while (!stack.isEmpty() && stack.peek() > 0 && a < 0) {
                if (stack.peek() < -a) {
                    stack.pop(); // Top asteroid explodes, continue checking
                } else if (stack.peek() == -a) {
                    stack.pop(); // Both asteroids explode
                    destroyed = true;
                    break;
                } else {
                    destroyed = true; // Incoming asteroid explodes
                    break;
                }
            }

            if (!destroyed) {
                stack.push(a);
            }
        }

        // Convert Stack to Array (maintains left-to-right order)
        int[] result = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            result[i] = stack.get(i);
        }

        return result;
    }
}