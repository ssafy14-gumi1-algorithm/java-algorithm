package 박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1189 {

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int r, c, k;

    public static char[][] map;
    public static boolean[][] visited;
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 왼쪽 아래(r-1, 0) 시작, 오른쪽 위(0, c-1) 도착
        visited[r - 1][0] = true;
        dfs(r - 1, 0, 1);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int dist) {
        // 가지치기 dist가 k 넘으면 볼 필요 없음
        if (dist > k) return;

        // dist가 k면서 집(0, c-1)에 도착한 경우
        if (dist == k && x == 0 && y == c - 1) {
            answer++;
            return;
        }

        // 4방향 탐색하면서 가기
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 배열 범위 밖 || 방문한 경우
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny]) continue;

            // 다음 칸이 T이면 못 지나감
            if (map[nx][ny] == 'T') continue;

            visited[nx][ny] = true;
            dfs(nx, ny, dist + 1);
            visited[nx][ny] = false;
        }
    }
}