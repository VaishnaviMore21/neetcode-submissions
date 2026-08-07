class Solution {
    public boolean canJump(int[] nums) {
       int finalPosition=nums.length-1;
       for(int idx=nums.length-2;idx>=0;idx--)
       {
        if(nums[idx]+idx>=finalPosition)
        {
            finalPosition=idx;

        }
       } 

       return finalPosition==0;
    }
}
