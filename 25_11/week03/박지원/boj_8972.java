package 박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_8972 {

    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 1~9 방향 (0,5는 사용 안 함)
    public static int[] dx = {-200, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    public static int[] dy = {-200, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static int r, c;
    public static char[][] board;

    public static Coord player;
    public static List<Coord> enemies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        enemies = new ArrayList<>();

        // 초기 보드 입력 및 위치 파싱
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'I') {
                    player = new Coord(i, j);
                } else if (board[i][j] == 'R') {
                    enemies.add(new Coord(i, j));
                }
            }
        }

        String command = br.readLine();
        for (int i = 0; i < command.length(); i++) {
            int dir = command.charAt(i) - '0'; // 1 ~ 9

            // 1. 종수 이동
            if (!movePlayer(dir)) {
                System.out.println("kraj " + (i + 1));
                return;
            }

            // 2. 미친 아두이노 이동
            if (!moveEnemies()) {
                System.out.println("kraj " + (i + 1));
                return;
            }
        }

        // 모든 명령을 수행하고 살아남은 경우 최종 보드 출력
        printBoard();
    }

    private static boolean movePlayer(int dir) {
        int nx = player.x + dx[dir];
        int ny = player.y + dy[dir];

        // 이동한 칸에 미친 아두이노가 있으면 패배
        if (board[nx][ny] == 'R') {
            return false;
        }

        // 기존 위치 비우고 새 위치로 이동
        board[player.x][player.y] = '.';
        player.x = nx;
        player.y = ny;
        board[player.x][player.y] = 'I';

        return true;
    }

    private static boolean moveEnemies() {
        // 아두이노의 수 배열(아두이노들이 한 번에 이동하므로 cnt 배열 만들어서 이동시켜줌
        int[][] cnt = new int[r][c];

        // 1. 각 미친 아두이노의 다음 위치 정하기
        for (Coord enemy : enemies) {
            // 기존 자리 비우기 (새로 다시 채울 예정)
            board[enemy.x][enemy.y] = '.';

            int bestX = enemy.x;
            int bestY = enemy.y;
            int minDist = Integer.MAX_VALUE; // 종수랑 가장 가까운 거리의 좌표로 이동해야 함

            // 1~9 방향 중, 5(제자리)는 제외
            for (int dir = 1; dir <= 9; dir++) {
                if (dir == 5) continue;

                // 이동할 좌표
                int nx = enemy.x + dx[dir];
                int ny = enemy.y + dy[dir];

                // 배열 범위 밖
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                // 거리 계산(지문에서 주어진대로 계산함)
                int dist = Math.abs(nx - player.x) + Math.abs(ny - player.y);
                // 최단거리면 이동할 좌표 및 최단거리 갱신
                if (dist < minDist) {
                    minDist = dist;
                    bestX = nx;
                    bestY = ny;
                }
            }

            // 이동 결과가 종수 위치면 즉시 게임 종료
            if (bestX == player.x && bestY == player.y) {
                return false;
            }

            cnt[bestX][bestY]++; // 해당 칸으로 이동 예정인 미친 아두이노 수 증가
        }

        // 2. 폭발 처리 후 enemy 리스트 재구성
        enemies.clear();

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (cnt[x][y] == 1) {
                    enemies.add(new Coord(x, y));
                }
            }
        }

        // 보드에 반영
        for (Coord e : enemies) {
            board[e.x][e.y] = 'R';
        }

        return true;
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            sb.append(board[i]).append('\n');
        }
        System.out.print(sb);
    }
}
