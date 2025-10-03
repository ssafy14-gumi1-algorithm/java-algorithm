package 박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3109 {

    // 오른 대각선 위 - 오른쪽 - 오른 대각 아래 순으로 놓는 게 유리함
    public static int[] dx = {-1, 0, 1};
    public static int[] dy = {1, 1, 1};

    public static int r, c;
    public static char[][] map;
    public static boolean installed;
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [input] 첫 줄에는 r, c가 주어짐
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // [input] 공백 없이 map 정보가 주어짐
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < r; i++) {
            installed = false;
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y) {
        // 빵집까지 연결됐음
        if (y == c - 1) {
            answer++;
            installed = true;
            return;
        }


        for (int dir = 0; dir < 3; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

            // X(건물)나 P(파이브)로는 못 감 -> .인 경우에만 파이프 설치하기
            if (map[nx][ny] == '.') {
                map[nx][ny] = 'P';
                dfs(nx, ny);

                // 이번 깊이 우선 탐색에서 빵집까지 이었다면 다른 방향은 보지 않음
                if (installed) return;
            }
        }
    }
}
