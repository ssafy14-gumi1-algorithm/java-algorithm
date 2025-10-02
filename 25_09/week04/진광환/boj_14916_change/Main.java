package boj.boj_14916_change;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*TODO:
- 상수

- 변수

- 조건

- 구하는 값

- 아이디어

*/
public class Main {

    static int N;
    static int result;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        result = Integer.MAX_VALUE;
        dp = new int[N+1];
        Arrays.fill(dp,  -1);
        recur(0);
        System.out.println(dp[0]);
    }

    static int recur(int changeCount) {

        if (N == 3 || N == 1) {
            return -1;
        }

        if (changeCount > N) return 100000;

        if (changeCount == N) {
            return 0;
        }

        if (dp[changeCount] != -1) {
            return dp[changeCount];
        }

        dp[changeCount] = Math.min(recur(changeCount + 5) + 1 , recur(changeCount + 2) + 1);

        return dp[changeCount];
    }
}

