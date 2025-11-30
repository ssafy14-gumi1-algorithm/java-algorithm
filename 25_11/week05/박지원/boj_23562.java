package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_23562 {

    static int n, m;
    static long a, b;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            board[i] = line.toCharArray();
        }

        long INF = Long.MAX_VALUE / 4;
        long ans = INF;

        int maxK = Math.min(n, m) / 3; // 3k x 3k가 들어갈 수 있는 최대 k

        // k: 1 ~ maxK
        for (int k = 1; k <= maxK; k++) {
            int height = 3 * k;
            int width = 3 * k;

            // ㄷ 모양 bounding box의 왼쪽 위 좌표 (sx, sy)
            for (int sx = 0; sx + height <= n; sx++) {
                for (int sy = 0; sy + width <= m; sy++) {
                    long cost = calcCost(k, sx, sy);
                    if (cost < ans) ans = cost;
                }
            }
        }

        System.out.println(ans);
    }

    static boolean isD(int startX, int startY, int k, int i, int j) {
        // 3k x 3k 영역 밖이면 ㄷ에 포함되지 않음
        if (i < startX || i >= startX + 3 * k || j < startY || j >= startY + 3 * k) {
            return false;
        }

        int dx = i - startX;
        int dy = j - startY;

        // 왼쪽 세로줄 전체
        if (dy < k) return true;
        // 위쪽 가로줄 전체
        if (dx < k) return true;
        // 아래쪽 가로줄 전체
        if (dx >= 2 * k) return true;

        // 나머지 (가운데 부분 등)는 흰색
        return false;
    }

    static long calcCost(int k, int sx, int sy) {
        long cost = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean targetBlack;

                // 3k x 3k bounding box 안/밖 판별
                if (i >= sx && i < sx + 3 * k && j >= sy && j < sy + 3 * k) {
                    // 안에 있으면 ㄷ 모양 여부 체크
                    targetBlack = isD(sx, sy, k, i, j);
                } else {
                    // 바깥은 전부 흰색이어야 함
                    targetBlack = false;
                }

                char cur = board[i][j];

                // 현재 검은색인데 최종은 흰색이어야 하면 지우기
                if (cur == '#' && !targetBlack) {
                    cost += b;
                }
                // 현재 흰색인데 최종은 검은색이어야 하면 칠하기
                else if (cur == '.' && targetBlack) {
                    cost += a;
                }
            }
        }

        return cost;
    }
}