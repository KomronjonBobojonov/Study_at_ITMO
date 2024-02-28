public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String st = "";

        strs[strs.length+1] = "";
        int flag = 0;
        int r = 1;
        int t = 0;
        char q = strs[0].charAt(0);
        for(int i = 0; i < strs.length; i++){
            if(flag == strs.length){
                r++;
            }
            for (int j = 0; j < r; j++){
                if(strs[i].charAt(j) == q){
                    flag++;
                    t = j;
                }else{
                    flag++;
                }
            }
            if(i+1 == strs.length){
                i=0;
            }
        }
        return st;
    }
}
