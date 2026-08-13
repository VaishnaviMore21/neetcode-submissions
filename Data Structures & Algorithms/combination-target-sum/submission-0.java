class Solution {
  
    void backtrack(int i, int target, int[] nums,
                   List<Integer> list, List<List<Integer>> ans) {
         if(target==0)
         {
            ans.add(new ArrayList<>(list));
            return;
         }

        if(i==nums.length || target<0)
        {
            return;
        }

        if(nums[i]<=target)
        {
            list.add(nums[i]);
            backtrack(i,target-nums[i],nums,list,ans);
            list.remove(list.size()-1);
        }

        backtrack(i+1,target,nums,list,ans);




                   }




    public List<List<Integer>> combinationSum(int[] nums, int target) {
       List<List<Integer>> ans = new ArrayList<>();

        backtrack(0, target, nums,
                  new ArrayList<>(), ans);
        return ans;
    }
}
