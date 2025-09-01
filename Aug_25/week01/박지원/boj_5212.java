package 박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_5212 {

    public static int[] dx = {0, -1, 0, 1};
    public static int[] dy = {1, 0, -1, 0};

    public static int minX = Integer.MAX_VALUE;
    public static int maxX = Integer.MIN_VALUE;
    public static int minY = Integer.MAX_VALUE;
    public static int maxY = Integer.MIN_VALUE;

    static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째 줄에 지도의 크기 R과 C (1 ≤ R, C ≤ 10)가 주어진다. 다음 R개 줄에는 현재 지도가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r + 1][c + 1];
        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        // 50년이 지나면, 인접한 세 칸 또는 네 칸에 바다가 있는 땅은 모두 잠겨버린다
        // 좌표를 돌면서 X라면 4방향에 바다가 몇 개인지 체크
        int[][] numOfOcean = getNumOfOcean(r, c, map);

        // [output] 최소 최대 위치에 있는 섬기준으로 지도 출력
        // numOfOcean이 2초과면 바다, 아니면 육지
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (numOfOcean[i][j]>2) System.out.print('.');
                else System.out.print('X');
            }
            System.out.println();
        }

    }

    private static int[][] getNumOfOcean(int r, int c, char[][] map) {
        int[][] numOfOcean = new int[r + 1][c + 1];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '.') {
                    numOfOcean[i][j] = 4; // 바다는 육지가 될 수 없으므로 4로 둠
                    continue;
                }

                // i, j가 섬인 경우, 주변 바다 갯수 세기
                int cnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    // 범위를 벗어난 곳은 바다임
                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) cnt++;
                    else if (map[nx][ny] == '.') { // 범위 내에서 .으로 표시된 부분은 바다임
                        cnt++;
                    }
                }

                numOfOcean[i][j] = cnt;

                // 바다가 2개 이하면 50년 후에도 살아남는 섬
                if (cnt <= 2) {
                    minX = Math.min(i, minX);
                    minY = Math.min(j, minY);
                    maxX = Math.max(i, maxX);
                    maxY = Math.max(j, maxY);
                }

            }
        }
        return numOfOcean;
    }
}
