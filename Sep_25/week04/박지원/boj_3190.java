package Sep_25.week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_3190 {

    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 우 하 좌 상
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static int n, k, playTime;
    public static int[][] map;

    public static int currDir;
    public static boolean alive;
    public static Deque<Coord> snake = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r - 1][c - 1] = 1; // 맨위가 1행 1열이라고 했으므로 1based->0based
        }

        // 시작 정보 넣어주기
        currDir = 0;
        alive = true;
        snake.offer(new Coord(0, 0)); // 뱀 움직이기 위한 좌표
        map[0][0]=2;//뱀이 있는 곳은 2로 표시하기

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken()); // 게임 시작 시간으로부터 X초가 끝난 뒤
            String dir = st.nextToken(); // 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻

            simulation(time, dir);
            if (!alive) break;
        }

        // 명령을 모두 수행한 다음에도 살아 있으면 벽에 부딪힐 때까지 계속 진행해야 함
        while(alive){
            playTime++;
            move();
        }

        System.out.println(playTime);
    }

    public static void simulation(int time, String dir) {
        // time까지 현재 dir 방향으로 움직여야 함
        while (playTime < time) {
            playTime++;
            move();

            if (!alive) return;
        }

        // 다 움직인 다음 방향 전환
        if (dir.equals("L")) {
            currDir = (currDir + 3) % 4;
        } else {
            currDir = (currDir + 1) % 4;
        }
    }

    private static void move() {
        // 머리 한 칸 전진
        Coord currHead = snake.getFirst();
        int nx = currHead.x + dx[currDir];
        int ny = currHead.y + dy[currDir];

        // 만약에 몸이나 벽에 부딪히면 alive false로 바꾸고 return 해주기
        if(nx<0 || nx>=n ||ny <0 || ny>=n || map[nx][ny]==2){
            alive= false;
            return;
        }

        // 해당 좌표가 사과인 경우
        if(map[nx][ny]==1){
            map[nx][ny] = 2;
            snake.offerFirst(new Coord(nx, ny)); // 새 머리 달아주기
        }else if(map[nx][ny]==0){
            map[nx][ny] = 2; // 머리 부분 좌표를 2로
            snake.offerFirst(new Coord(nx, ny));

            Coord tail = snake.removeLast();
            map[tail.x][tail.y] = 0;// 꼬리 부분 좌표를 0으로 만들어주기-
        }
    }
}
