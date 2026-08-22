
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(candidates);

        backtrack(0, target, candidates, new ArrayList<>(), ans);

        return ans;
    }

    private void backtrack(int index, int target, int[] candidates,
                           List<Integer> current,
                           List<List<Integer>> ans) {

        // Target reached
        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < candidates.length; i++) {

            // Skip duplicates at the same level
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // No need to check further
            if (candidates[i] > target) {
                break;
            }

            // Choose
            current.add(candidates[i]);

            // Move to next index because each number can be used once
            backtrack(i + 1, target - candidates[i],
                      candidates, current, ans);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}