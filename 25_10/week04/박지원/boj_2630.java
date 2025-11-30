package week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2630 {

    public static int n;
    public static int[][] paper;
    public static int white, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input n이랑 paper 정보
        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, n - 1, n - 1);

        System.out.println(white);
        System.out.println(blue);
    }

    // 왼쪽 위 좌표(x1, y1) 오른쪽 아래 좌표 (x2, y2)
    public static void recur(int x1, int y1, int x2, int y2) {
        // 전체가 같은 색이면 쪼개기 종료
        if (isSame(x1, y1, x2, y2)) {
            if (paper[x1][y1] == 0) white++;
            else blue++;
            return;
        }

        // 재귀 호출 4개로 쪼개기
        recur(x1, y1, (x1 + x2) / 2, (y1 + y2) / 2); // 왼쪽 위
        recur(x1, (y1 + y2) / 2 + 1, (x1 + x2) / 2, y2); // 오른쪽 위
        recur((x1 + x2) / 2 + 1, y1, x2, (y1 + y2) / 2); // 왼쪽 아래
        recur((x1 + x2) / 2 + 1, (y1 + y2) / 2 + 1, x2, y2); // 오른쪽 아래
    }

    public static boolean isSame(int x1, int y1, int x2, int y2) {
        // 1칸짜리는 무조건 true임
        if (x1 == x2 && y1 == y2) return true;

        int color = paper[x1][y1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (paper[i][j] != color)
                    return false;
            }
        }
        return true;
    }

}