import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean solution(String[] phoneBook) {
        Arrays.sort(phoneBook);
        for (int i = 0; i < phoneBook.length - 1; i++)
            if (phoneBook[i + 1].startsWith(phoneBook[i]))
                return false;
        return true;
    }

    private static List<int[]> resultList;

    public static void hanoi(int n, int start, int middle, int end) {
        if (n == 1) {
            resultList.add(new int[]{start, end});
            return;
        }

        hanoi(n - 1, start, end, middle); //middle을 일단 목적지로

        resultList.add(new int[]{start, end});

        hanoi(n - 1, middle, start, end);
    }

    public static int[][] solution(int n) {

        resultList = new ArrayList<>();
        hanoi(n, 1, 2, 3);

        int[][] answer = new int[resultList.size()][];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }
}