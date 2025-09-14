package Sep_25.week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_14501 {
    // 오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] t = new int[n + 1];
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]: i일부터 퇴사일까지 생각했을 때, 최대 이익
        int[] dp = new int[n + 2];
        for (int i = n; i >= 0; i--) {
            int endDay = i + t[i];
            if (endDay <= n + 1) {
                // dp[i+1] = 상담 안 하는 경우, dp[endDay]+p[i] = 상담하는 경우
                dp[i] = Math.max(dp[i + 1], dp[endDay] + p[i]);
            } else { // 오늘 상담하면 퇴사일 넘음 → 오늘은 못함
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);
    }
}
