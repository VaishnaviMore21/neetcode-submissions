class Solution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        helper(root, inOrderList);

        // Validate strictly ascending order
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i) <= inOrderList.get(i - 1)) {
                return false; // Not a BST (handles out-of-order elements & duplicates)
            }
        }
        return true;
    }

    private void helper(TreeNode node, List<Integer> inOrderList) {
        if (node == null) return;

        helper(node.left, inOrderList);      // Left
        inOrderList.add(node.val);           // Root
        helper(node.right, inOrderList);     // Right
    }
}