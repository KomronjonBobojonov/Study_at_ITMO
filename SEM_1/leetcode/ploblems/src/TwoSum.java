public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){
            int sum = nums[i];
            for(int j = i+1; j < nums.length; j++){
                sum+=nums[j];
                if(sum == target){
                    return new int[]{i, j};
                }
                if(sum > target){
                    continue;
                }
            }
        }
        throw  new IllegalArgumentException("NO");
    }
}
