package week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1600 {
    // 원숭이 dx, dy
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    // 말 움직임 dx, dy -> k번만 가능
    public static int[] horseDx = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static int[] horseDy = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int k, w, h;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 첫째 줄에 정수 K
        k = Integer.parseInt(br.readLine());
        // 둘째 줄에 격자판의 가로길이 W, 세로길이 H
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // 그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 1은 장애물
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다
        System.out.println(bfs(0, 0));
    }

    private static int bfs(int startX, int startY) {
        Queue<Coord> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[h][w][k + 1];

        // 시작 좌표 넣기
        queue.offer(new Coord(startX, startY, 0, 0));
        visited[startX][startY][0] = true;

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();

            if (curr.x == h - 1 && curr.y == w - 1) {
                return curr.move;
            }

            // horse move
            if (curr.horseMove < k) {
                for (int dir = 0; dir < 8; dir++) {
                    int nx = curr.x + horseDx[dir];
                    int ny = curr.y + horseDy[dir];
                    int nm = curr.horseMove + 1;
                    // 배열 밖이거나
                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                    // 방문한 곳
                    if (visited[nx][ny][nm]) continue;
                    // 벽은 못 가
                    if (map[nx][ny] == 1) continue;

                    queue.offer(new Coord(nx, ny, curr.move + 1, curr.horseMove + 1));
                    visited[nx][ny][nm] = true;
                }
            }

            // normal move
            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];
                int nm = curr.horseMove;

                // 배열 밖
                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                // 방문한 곳
                if (visited[nx][ny][nm]) continue;
                // 벽은 못 가
                if (map[nx][ny] == 1) continue;

                queue.offer(new Coord(nx, ny, curr.move + 1, curr.horseMove));
                visited[nx][ny][nm] = true;
            }

        }
        // 도착점에 못 가는 경우 -1 return
        return -1;

    }

    public static class Coord {
        int x;
        int y;
        int move;
        int horseMove;

        public Coord(int x, int y, int move, int horseMove) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.horseMove = horseMove;
        }
    }
}