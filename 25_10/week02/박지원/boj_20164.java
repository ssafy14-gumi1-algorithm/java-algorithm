package week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_20164 {

    // 입력 값
    public static int n;
    // 출력 값
    public static int maxCnt = Integer.MIN_VALUE, minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // [input]
        n = Integer.parseInt(br.readLine());
        recur(n, 0);

        // [output] 최소값과 최대값을 순서대로 출력
        System.out.println(minCnt + " " + maxCnt);
    }

    // num은 현재 수, cnt는
    public static void recur(int num, int cnt) {
        // 1. 각 자리 수 홀수 세기
        cnt += CountOdd(num);

        // 2-1. 한자리 수인 경우 갱신
        if (num < 10) {
            maxCnt = Math.max(maxCnt, cnt);
            minCnt = Math.min(minCnt, cnt);
            return;
        }

        // 두자리 이상인 경우 나눠야 함
        String s = String.valueOf(num);
        int len = s.length();

        // 2-2. 2자리 수
        if (len == 2) {
            int next = (num / 10) + (num % 10);
            recur(next, cnt);
            return;
        }

        // 2-3. 3자리 수 = 나누는 곳이 2곳
        for (int i = 1; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                // substring은 startIdx부터 endIdx-1까지 자름
                int subNum1 = Integer.parseInt(s.substring(0, i));
                int subNum2 = Integer.parseInt(s.substring(i, j));
                int subNum3 = Integer.parseInt(s.substring(j));

                recur(subNum1 + subNum2 + subNum3, cnt);
            }
        }
    }

    private static int CountOdd(int num) {
        int cnt = 0;
        while (num > 0) {
            int digit = num % 10;
            if (digit % 2 != 0) cnt++;
            num /= 10;
        }
        return cnt;
    }
}
