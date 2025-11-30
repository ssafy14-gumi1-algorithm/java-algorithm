package week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14500 {
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int n, m;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 점 완전 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(answer);

    }

    public static void dfs(int x, int y, int cnt, int sum) {

        // 블럭 4개 다 모았으면 answer 갱신
        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        // 4방향 탐색(백트래킹)
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            // 배열 밖, 방문한 곳
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

            // ㅗ 처리(직접 가지 않고, 제자리에서 방문체크만)
            if (cnt == 2) {
                visited[nx][ny] = true;
                dfs(x, y, cnt + 1, sum + arr[nx][ny]);
                visited[nx][ny] = false;
            }

            // 직접 찾아가는 블록 탐색
            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1, sum + arr[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}