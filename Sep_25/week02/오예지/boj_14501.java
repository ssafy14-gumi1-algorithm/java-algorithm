package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14501 {

    static int N;
    static int maxCost = 0;
    static int[][] s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        s = new int[N][2];

        // 2차원 배열 입력
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            s[i][0] = Integer.parseInt(st.nextToken());  // 상담 소요 기간
            s[i][1] = Integer.parseInt(st.nextToken());  // 비용
        }

        recur(0, 0);

        System.out.println(maxCost);
    }

    static void recur(int day, int sumCost){
        if (day >= N){
            maxCost = Math.max(sumCost, maxCost);
            return;
        }

        // 상담을 잡는 경우
        if (day + s[day][0] <= N) {
            recur(day + s[day][0], sumCost + s[day][1]);
        }

        // 상담을 잡지 않는 경우
        recur(day + 1, sumCost);
    }
}
