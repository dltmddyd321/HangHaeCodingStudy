public class CodingTestJava {
    public static int[] solution(long n) {
        //문자열로 변환하여 거꾸로 뒤집는다.
        String reversedString = new StringBuilder(Long.toString(n)).reverse().toString();
        int[] result = new int[reversedString.length()];
        for (int i = 0; i < reversedString.length(); i++) {
            //Char를 int형으로 변환하면서 저장
            result[i] = Character.getNumericValue(reversedString.charAt(i));
        }
        return result;
    }
}
