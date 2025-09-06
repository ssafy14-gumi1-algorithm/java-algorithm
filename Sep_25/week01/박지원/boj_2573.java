import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2573 {
    public static class Coord{
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int n, m;
    public static int[][] map;

    // 완탐으로 모든 칸 주위에 바다 갯수만큼
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // [solution] 쪼개졌는지 확인 + 1년 경과
        int year = getYear();
        System.out.println(year);
    }

    private static int getYear() {
        int year = 0;

        while(true){
            // [종료 조건] 빙산이 다 녹았으면 0
            if(isAllMelt()) return 0;

            // [종료 조건] bfs로 빙산이 두 덩어리 이상으로 분리되었는지 확인 -> true면 종료
            if(isSeparate()) return year;

            melt(); // 1년 지남
            year++;
        }
    }

    private static boolean isAllMelt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 바다가 아닌 땅이 1개라도 있으면 모두 녹은 게 아님
                if(map[i][j]!=0) return false;
            }
        }
        return true;
    }

    private static boolean isSeparate() {
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 방문한 칸은 다시 방문하지 않음
                if(visited[i][j]) continue;
                // 바다가 아니면 bfs로 이어진 땅 싹 다 탐색하기
                if(map[i][j]==0) continue;

                bfs(i, j, visited);
                cnt++;

                //System.out.println(cnt);
                if(cnt>=2) return true;
            }
        }

        return false;
    }

    private static void bfs(int startX, int startY, boolean[][] visited) {

        Queue<Coord> queue = new ArrayDeque<>();

        // 시작점 넣기(방문 예약)
        queue.offer(new Coord(startX, startY));
        visited[startX][startY] = true;

        while(!queue.isEmpty()){
            Coord curr = queue.poll();

            // 4방향 탐색하기
            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];

                // 배열 범위를 벗어남 || 이미 방문했던 땅
                if(!inRange(nx, ny) || visited[nx][ny]) continue;

                // 바다로는 가지 않음
                if(map[nx][ny] == 0) continue;

                queue.offer(new Coord(nx, ny));
                visited[nx][ny] = true;
            }

        }
    }

    private static void melt() {
        // 주변 바다 갯수
        int[][] numOfSea = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 바다로는 가지 않기
                if(map[i][j] == 0) continue;

                numOfSea[i][j] = getSeaCnt(i, j);
            }
        }

        // 진짜로 녹이기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 바다로는 가지 않기
                if(map[i][j] == 0) continue;

                map[i][j] = Math.max(map[i][j]-numOfSea[i][j], 0);
            }
        }
    }

    private static int getSeaCnt(int x, int y) {
        int cnt = 0;
        for(int dir = 0; dir<4; dir++){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위를 벗어났는지 확인
            if(!inRange(nx, ny)) continue;

            // 바다면 count 1증가
            if(map[nx][ny]==0) cnt++;
        }
        return cnt;
    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

}
