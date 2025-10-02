/* input
2
5 2
1 3 3 6 7
8 13 9 12 8
4 16 11 12 6
2 4 1 23 2
9 13 4 7 3
6 3
29 21 26 9 5 8
21 19 8 0 21 19
9 24 2 11 4 24
19 29 1 0 21 19
10 29 6 18 4 3
29 11 15 3 3 29
 */

package 안지원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class swea_12712 {
    public static void main(String[] args) throws Exception {
        // 입력을 빠르게 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());  // 행/열 크기
            int m = Integer.parseInt(st.nextToken());  // 한 방향으로 퍼지는 거리

            // 격자 입력 받기
            int[][] area = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    area[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 방향 델타 설정
            int[] dx = {1, 0, -1, 0};   // 우 하 좌 상 (직선 방향)
            int[] dy = {0, 1, 0, -1};

            int[] mx = {1, 1, -1, -1};  // 우하 좌하 좌상 우상 (대각선 방향)
            int[] my = {1, -1, -1, 1};

            int maxKill = Integer.MIN_VALUE;

            // 각 좌표에서의 최대 합 계산
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    int total1 = area[y][x];  // 직선 방향 합산
                    for (int i = 0; i < 4; i++) {
                        int sX = x;
                        int sY = y;
                        for (int j = 1; j < m; j++) {
                            sX += dx[i];
                            sY += dy[i];
                            if (sX >= 0 && sY >= 0 && sX < n && sY < n) {
                                total1 += area[sY][sX];
                            }
                        }
                    }

                    int total2 = area[y][x];  // 대각선 방향 합산
                    for (int i = 0; i < 4; i++) {
                        int sX = x;
                        int sY = y;
                        for (int j = 1; j < m; j++) {
                            sX += mx[i];
                            sY += my[i];
                            if (sX >= 0 && sY >= 0 && sX < n && sY < n) {
                                total2 += area[sY][sX];
                            }
                        }
                    }

                    maxKill = Math.max(maxKill, Math.max(total1, total2));
                }
            }

            // 결과 출력
            System.out.println("#" + test_case + " " + maxKill);
        }
    }
}
