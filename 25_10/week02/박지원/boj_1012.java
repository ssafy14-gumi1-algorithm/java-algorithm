package week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1012 {
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    public static int n, m, k;
    public static int[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // k개의 배추 좌표 표시
            map = new int[n][m];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            // bfs로 배추 영역 개수 세기
            int answer = 0;
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        answer++;
                        bfs(i, j);
                    }
                }
            }
            System.out.println(answer);

        }
    }

    public static void bfs(int startX, int startY) {
        Queue<Coord> queue = new ArrayDeque<>();

        // 시작점 방문 예약
        queue.offer(new Coord(startX, startY));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];

                //범위를 벗어나거나 || 방문한 곳 || 빈 곳(0)
                if (nx < 0 || nx >= n || ny < 0 || ny >= m
                        || visited[nx][ny]
                        || map[nx][ny] == 0) continue;

                queue.offer(new Coord(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }

    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
