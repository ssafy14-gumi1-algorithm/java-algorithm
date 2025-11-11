package week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1726 {
    // 북 동 남 서(시계방향 회전을 위함)
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int n, m;
    public static int[][] map; // 0: 갈 수 있음, 1: 갈 수 없음
    public static boolean[][][] visited;
    public static int endX, endY, endDir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫째 줄에 공장 내 궤도 설치 상태를 나타내는 직사각형의 세로 길이 n과 가로 길이 m
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // n줄에 걸쳐 한 줄에 N개씩 각 지점의 궤도 설치 상태를 나타내는 숫자 0 또는 1이 빈칸을 사이에 두고 주어진다.
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 다음 줄에는 로봇의 출발 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다.
        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) - 1;
        int startY = Integer.parseInt(st.nextToken()) - 1;

        // 방향은 동쪽이1, 서쪽이 2, 남쪽이 3, 북쪽이 4 -> dx, dy에 맞게(북동남서) 재설정
        int dir = Integer.parseInt(st.nextToken());
        dir = inputToDir(dir);

        // 마지막 줄에는 로봇의 도착 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다.
        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken()) - 1;
        endY = Integer.parseInt(st.nextToken()) - 1;

        endDir = Integer.parseInt(st.nextToken());
        endDir = inputToDir(endDir);

        // 최단거리니까 bfs로 반환하게 해
        visited = new boolean[n][m][4]; //  [n][m][dir]
        System.out.println(bfs(startX, startY, dir));
    }

    // 입력으로 들어온 dir을 dx, dy 배열의  dir로 바꿔주기
    public static int inputToDir(int dir) {
        if (dir == 2) return 3;
        else if (dir == 3) return 2;
        else if (dir == 4) return 0;
        return 1;
    }

    public static int bfs(int startX, int startY, int startDir) {
        Queue<Coord> queue = new ArrayDeque<>();
        // 시작점 넣기
        queue.offer(new Coord(startX, startY, startDir, 0));
        visited[startX][startY][startDir] = true;

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();
            // System.out.println(String.format("[%d] (%d, %d): %d", curr.cnt, curr.x, curr.y, curr.dir));

            // [종료조건] 도착 지점 + 도착 방향이 맞으면
            if (curr.x == endX && curr.y == endY && curr.dir == endDir) {
                return curr.cnt;
            }

            // [회전] 시계 방향
            int nd = (curr.dir + 1) % 4;
            if (!visited[curr.x][curr.y][nd]) {
                queue.offer(new Coord(curr.x, curr.y, nd, curr.cnt + 1));
                visited[curr.x][curr.y][nd] = true;
            }

            // [회전] 반시계방향
            nd = (curr.dir + 3) % 4;
            if (!visited[curr.x][curr.y][nd]) {
                queue.offer(new Coord(curr.x, curr.y, nd, curr.cnt + 1));
                visited[curr.x][curr.y][nd] = true;
            }

            // 도착지점에 왔으면 이동은 이제 할 필요 없음
            if (curr.x == endX && curr.y == endY) continue;

            // [이동] 1, 2, 3
            for (int k = 1; k <= 3; k++) {
                int nx = curr.x + (dx[curr.dir] * k);
                int ny = curr.y + (dy[curr.dir] * k);

                // 배열 밖 이동x
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // System.out.println(String.format("target: (%d, %d)-%d", nx, ny, curr.dir));
                // 재방문x
                if (visited[nx][ny][curr.dir]) continue;
                // 로봇이 못 가는 장소
                if (map[nx][ny] == 1) break;

                queue.offer(new Coord(nx, ny, curr.dir, curr.cnt + 1));
                visited[nx][ny][curr.dir] = true;
            }

        }

        // 이런 경우는 없음
        return -1;
    }

    public static class Coord {
        int x;
        int y;
        int dir;
        int cnt;

        public Coord(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

}