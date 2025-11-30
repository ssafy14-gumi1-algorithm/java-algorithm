package week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17144 {

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int r, c, t;
    public static int[][] map;
    public static Coord[] airCleaner = new Coord[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // -1이면 airCleaner 좌표 입력
                // [0]이 top, [1]이 bottom이 된다(순서가 그럼)
                if (map[i][j] == -1 && airCleaner[0] == null) {
                    airCleaner[0] = new Coord(i, j);
                    airCleaner[1] = new Coord(i + 1, j);
                }
            }
        }

        // T초 동안 반복해야 함
        for (int i = 0; i < t; i++) {
            // 1. 확산(모든 칸이 동시에 일어나야 함) -> 새 배열에 각각 확산 값 계산
            diffusion();
            // 2. shift 연산
            shift();
        }

        // 반복이 끝난 후 남아있는 미세먼지 양
        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) continue;
                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    // 1. 미세먼지 확산
    private static void diffusion() {
        int[][] afterStatus = new int[r][c];

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                // 확산되는 양(=4방향에 더해줄 값)
                int amount = map[x][y] / 5;
                if (amount == 0) {
                    afterStatus[x][y] += map[x][y];
                    continue;
                }

                int cnt = 0; // 확산된 방향의 개수
                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == -1) continue;
                    afterStatus[nx][ny] += amount;
                    cnt++;
                }

                // 남은 미세먼지 양
                int remain = map[x][y] - amount * cnt;
                afterStatus[x][y] += remain;
            }
        }

        // 확산 한 번에 적용하기
        map = afterStatus;
        // 공청기 위치 다시 적어주기
        map[airCleaner[0].x][airCleaner[0].y] = -1;
        map[airCleaner[1].x][airCleaner[1].y] = -1;
    }

    // 2. 공청기 작동
    private static void shift() {
        int ax = airCleaner[0].x;
        int ay = airCleaner[0].y;
        // 위쪽 공기청정기의 바람은 반시계방향으로 순환
        // [top cleaner] ↓ down shift
        for (int x = ax - 1; x > 0; x--) {
            map[x][ay] = map[x - 1][ay];
        }
        // [top cleaner] ← left shift
        for (int y = 0; y < c - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
        // [top cleaner] ↑ up shift
        for (int x = 0; x < ax; x++) {
            map[x][c - 1] = map[x + 1][c - 1];
        }
        // [top cleaner] → right shift
        for (int y = c - 1; y > 1; y--) {
            map[ax][y] = map[ax][y - 1];
        }
        map[ax][ay + 1] = 0;

        // 아래쪽 공기청정기의 바람은 시계방향으로 순환
        ax = airCleaner[1].x;
        ay = airCleaner[1].y;
        // [bottom cleaner] ↑ up shift
        for (int x = ax + 1; x < r - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
        // [bottom cleaner] ← left shift
        for (int y = 0; y < c - 1; y++) {
            map[r - 1][y] = map[r - 1][y + 1];
        }
        // [bottom cleaner] ↓ down shift
        for (int x = r - 1; x > ax; x--) {
            map[x][c - 1] = map[x - 1][c - 1];
        }
        // [bottom cleaner] → right shift
        for (int y = c - 1; y > 1; y--) {
            map[ax][y] = map[ax][y - 1];
        }
        map[ax][ay + 1] = 0;
    }

    public static void printMap() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 좌표 클래스
    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
