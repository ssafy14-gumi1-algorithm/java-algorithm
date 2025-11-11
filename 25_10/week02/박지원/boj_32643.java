package week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_32643 {


    public static boolean[] primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 소수 배열 만들기(에라토스테네스의 체)
        primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) continue;

            for (int j = i * i; j <= n; j += i) {
                primes[j] = false;
            }
        }

        // 질의가 100만개라서 i까지의 소수의 개수를 미리 계산해둬야 빠름
        int[] prefixSum = new int[n + 1];
        prefixSum[1] = 1; // 1은 소수가 아니지만 수열을 만들 때 필요하므로 소수 취급 함
        for (int i = 2; i <= n; i++) {
            prefixSum[i] = (primes[i]) ? prefixSum[i - 1] + 1 : prefixSum[i - 1];
        }

        // m개의 질문에 대답
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int cnt = prefixSum[b] - prefixSum[a];
            if (primes[a]) cnt++;
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}
