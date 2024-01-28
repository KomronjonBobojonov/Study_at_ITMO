public class RomanToInteger {
    public int romanToInt(String s) {
        int sum = 0;
        int[] m = new int[s.length()+1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                m[i] = 1;
            } else if (s.charAt(i) == 'V') {
                m[i] = 5;
            } else if (s.charAt(i) == 'X') {
                m[i] = 10;
            } else if (s.charAt(i) == 'L'){
                m[i] = 50;
            } else if (s.charAt(i) == 'C'){
                m[i] = 100;
            } else if (s.charAt(i) == 'D'){
                m[i] = 500;
            } else if (s.charAt(i) == 'M'){
                m[i] = 1000;
            }
        }

        for(int i = 0; i < m.length-1; i++){
            if(m[i] < m[i+1]){
                sum += m[i+1] - m[i];
                i++;
            }else{
                sum += m[i];
            }
        }

        return sum;
    }
}
