package Sep_25.week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_14916 {
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];
        for (int i = 0; i <= n; i++) dp[i] = -1;

        dp[0] = 0; // 0원을 만들기 위해 필요한 동전 수는 0
        for (int i = 1; i <= n; i++) {
            if (i - 2 >= 0 && dp[i - 2] != -1) {
                if (dp[i] == -1 || dp[i] > dp[i - 2] + 1) {
                    dp[i] = dp[i - 2] + 1;
                }
            }
            if (i - 5 >= 0 && dp[i - 5] != -1) {
                if (dp[i] == -1 || dp[i] > dp[i - 5] + 1) {
                    dp[i] = dp[i - 5] + 1;
                }
            }
        }

        System.out.println(dp[n]);
    }
}