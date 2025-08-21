package week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2468 {

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int n;
    public static int[][] map;
    public static boolean[][] visited;

    public static int maxSafe = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // init
        map = new int[n+1][n+1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 높이는 1이상 100 이하의 정수 + 비가 오지 않는 경우0
        for (int h = 0; h <= 100; h++) {
            visited = new boolean[n+1][n+1];
            int safeCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(!visited[i][j] && map[i][j]>h){
                        safeCnt++;

                        visited[i][j] = true;
                        dfs(i, j, h);
                    }
                }
            }
            maxSafe = Math.max(maxSafe, safeCnt);
        }

        System.out.println(maxSafe);
    }

    public static void dfs(int x, int y, int h){

        // 4방향 탐색하면서 조건에 맞는 땅은 visited 체크
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
            if(visited[nx][ny]) continue;
            if(map[nx][ny]<=h) continue; // 높이 이하인 섬은 싹 다 가라앉음

            visited[nx][ny] = true;
            dfs(nx, ny, h);
        }
    }
}
