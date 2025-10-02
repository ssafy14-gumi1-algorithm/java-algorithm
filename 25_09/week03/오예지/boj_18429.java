package 오예지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class boj_18429 {

    static int N, K;
    static int[] kits;
    static boolean[] visited;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kits = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];

        recur(0, 500);  // 첫날, 초기 체중 500에서 시작

        System.out.println(cnt);
    }

    static void recur(int day, int weight) {
        if (day == N) {  // 모든 날짜 키트 사용 완료
            cnt++;
            return;
        }

        if (weight < 500) {  // 체중 500 미만이면 실패
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            recur(day + 1, weight + kits[i] - K);
            visited[i] = false;
        }
    }
}
