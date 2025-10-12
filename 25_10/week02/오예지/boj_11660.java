package JAVA_AlgorithmStudy.Oct.week02.오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11660 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][N + 1];

        // 배열 입력 받음 (1-based)
        for (int i = 1; i <= N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        // 누적합 배열 만들기
        int[][] prefix = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefix[i][j] = arr[i][j]
                        + prefix[i-1][j]
                        + prefix[i][j-1]
                        - prefix[i-1][j-1];
            }
        }

        // 누적합 배열 이용해서 답 구하기
        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st2.nextToken());
            int y1 = Integer.parseInt(st2.nextToken());
            int x2 = Integer.parseInt(st2.nextToken());
            int y2 = Integer.parseInt(st2.nextToken());

            int result = prefix[x2][y2]
                    // 위쪽 부분 제거
                    - prefix[x1-1][y2]
                    // 왼쪽 부분 제거
                    - prefix[x2][y1-1]
                    // 두번 제거된 부분을 다시 더해줌
                    + prefix[x1-1][y1-1];

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
