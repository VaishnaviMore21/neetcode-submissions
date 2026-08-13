
class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int start, int[] nums,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // Add current subset
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            // Skip duplicate elements at same level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Pick
            current.add(nums[i]);

            // Move to next index
            backtrack(i + 1, nums, current, result);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}