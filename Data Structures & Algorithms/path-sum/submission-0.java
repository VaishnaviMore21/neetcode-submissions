

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> pathStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        pathStack.push(root);
        sumStack.push(root.val);
        
        while (!pathStack.isEmpty()) {
            TreeNode node = pathStack.pop();
            int currentSum = sumStack.pop();

            // Leaf node check
            if (node.left == null && node.right == null) {
                if (currentSum == targetSum) {
                    return true;
                }
            }

            // Right child
            if (node.right != null) {
                pathStack.push(node.right);
                sumStack.push(currentSum + node.right.val);
            }

            // Left child
            if (node.left != null) {
                pathStack.push(node.left);
                sumStack.push(currentSum + node.left.val);
            }
        }

        return false; 
    }
}