package Sep_25.week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2636 {
    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coord) {
                Coord coord = (Coord) obj;
                return this.x == coord.x && this.y == coord.y;
            }
            return false;
        }
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int n, m;
    public static int[][] arr;

    public static int remainCheese, time;

    public static void main(String[] args) throws IOException {
        // 첫째 줄에는 사각형 모양 판의 세로와 가로의 길이가 양의 정수로 주어진다. 세로와 가로의 길이는 최대 100이다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 판의 각 가로줄의 모양이 윗 줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다.
        // 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                // 총 치즈 개수 세기
                if (arr[i][j] == 1) remainCheese++;
            }
        }

        // [Solution] 시간을 하나씩 늘려가면서
        while (true) {
            // [BFS] 녹일 거 찾은 다음 없으면 끝내기
            Set<Coord> toMelt = getEdgeCheese(0, 0);
            if (toMelt == null) {
                break;
            }

            // 녹이고 1시간 증가
            melt(toMelt);
            time++;
            // 테두리 치즈 개수만큼 제거
            if (remainCheese > toMelt.size()) remainCheese -= toMelt.size();
        }

        System.out.println(time);
        System.out.println(remainCheese);
    }

    // [BFS] 녹일 치즈의 리스트 만들기
    public static Set<Coord> getEdgeCheese(int startX, int startY) {
        Set<Coord> edgeCoord = new HashSet<>();
        Queue<Coord> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        queue.offer(new Coord(startX, startY));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {

            Coord curr = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];

                // 배열 범위 밖이거나 방문한 곳은 건너뛰기
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny]) continue;

                // 치즈는 리스트에 넣고 건너뛰기(탐색x)
                // set이므로 중복 좌표는 들어가지 않는다
                if (arr[nx][ny] == 1) {
                    edgeCoord.add(new Coord(nx, ny));
                }
                // 치즈 없는 곳이면 다른 테두리 치즈를 찾아 떠나자
                else if (arr[nx][ny] == 0) {
                    queue.offer(new Coord(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        if (edgeCoord.isEmpty()) return null;
        else return edgeCoord;
    }

    // 치즈 녹이기
    public static void melt(Set<Coord> toMelt) {
        for (Coord coord : toMelt) {
            arr[coord.x][coord.y] = 0;
        }
    }
}
