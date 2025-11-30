package week01.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2206 {
    public static class Coord{
        int x;
        int y;
        int cnt;
        boolean isBroken;

        public Coord(int x, int y, int cnt, boolean isBroken) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isBroken = isBroken;
        }
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int n, m;
    public static int[][] map; // 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i =0; i<n; i++) {
            String line = br.readLine();
            for(int j = 0; j<m; j++) {
                map[i][j] = line.charAt(j) -'0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    private static int bfs(int startX, int startY) {
        Queue<Coord> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][2];

        // 시작 지점 넣기
        queue.offer(new Coord(startX, startY, 1, false));
        visited[startX][startY][0] = true;

        while(!queue.isEmpty()) {
            Coord curr = queue.poll();

            if(curr.x == n-1 && curr.y == m-1) {
                return curr.cnt;
            }

            for(int dir =0; dir<4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;

                if(map[nx][ny]==1) {
                    if(!curr.isBroken && !visited[nx][ny][1]) {
                        queue.offer(new Coord(nx, ny, curr.cnt+1, true));
                        visited[nx][ny][1] = true;
                    }
                }else{
                    int broken = curr.isBroken ? 1 : 0;
                    if(!visited[nx][ny][broken]) {
                        queue.offer(new Coord(nx, ny, curr.cnt+1, curr.isBroken));						visited[nx][ny][broken]  = true;

                    }
                }
            }
        }


        return -1;
    }

}