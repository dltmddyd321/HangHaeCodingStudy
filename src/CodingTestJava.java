import java.util.*;

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

    public int[] solution1(int[] progresses, int[] speeds) {
        int[] daysToComplete = new int[progresses.length];

        for (int i = 0; i < progresses.length; i ++) {
            int needCheck = 100 - progresses[i];
            daysToComplete[i] = (needCheck % speeds[i] == 0) ? (needCheck / speeds[i]) : (needCheck / speeds[i] + 1);
        }

        List<Integer> result = new ArrayList<>();
        int maxDays = daysToComplete[0];
        int cnt = 1;

        for (int i = 1; i < progresses.length; i ++) {
            if (daysToComplete[i] <= maxDays) {
                cnt ++; //기존 값을 축적
            } else { //더 큰 값이므로 여태까지는 저장해두고, 새로운 카운트 시작
                result.add(cnt);
                maxDays = daysToComplete[i];
                cnt = 1;
            }
        }
        result.add(cnt);

        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i ++) {
            res[i] = result.get(i);
        }

        return res;
    }

    public int[] solution2(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] daysToComplete = new int[n];

        // 각 작업이 완료되기까지 걸리는 일수를 계산
        for (int i = 0; i < n; i++) {
            int remainingProgress = 100 - progresses[i];
            daysToComplete[i] = (remainingProgress % speeds[i] == 0) ?
                    (remainingProgress / speeds[i]) :
                    (remainingProgress / speeds[i] + 1);
        }

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(daysToComplete[0]);
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if (queue.peek() != null && daysToComplete[i] <= queue.peek()) {
                cnt++; // 기존 값을 축적
            } else { // 더 큰 값이므로 여태까지는 저장해두고, 새로운 카운트 시작
                result.add(cnt);
                queue.poll(); // 큐에서 제거
                queue.offer(daysToComplete[i]); // 큐에 현재 값을 추가
                cnt = 1;
            }
        }
        result.add(cnt); // 마지막 카운트 추가

        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }
}