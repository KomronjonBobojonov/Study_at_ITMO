public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        String str2 = new StringBuilder(str).reverse().toString();
        if(str.equals(str2)){
            return true;
        }else{
            return false;
        }
    }
}
