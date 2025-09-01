import java.io.*;
import java.util.*;

class boj_11559 {
    static final int H = 12, W = 6;
    static char[][] board = new char[H][W];
    static boolean[][] visited = new boolean[H][W];
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 12줄 × 6문자
        for (int y = 0; y < H; y++) {
            // input 읽기
            String line = br.readLine();
            // 게임판 board에 값 하나씩 집어넣기
            for (int x = 0; x < W; x++) {
                board[y][x] = line.charAt(x);
            }
        }

        // 연쇄 카운트(결과값)
        int chain = 0;

        // "터질 게 더 이상 없을 때까지" 반복
        while (true) {
            // 방문여부 만들기
            for (int y = 0; y < H; y++) Arrays.fill(visited[y], false);

            // 이번 턴에 동시에 터질 모든 칸을 수집
            List<int[]> toRemove = new ArrayList<>();

            for (int y = 0; y < H; y++) {
                for (int x = 0; x < W; x++) {
                    if (board[y][x] != '.' && !visited[y][x]) {
                        List<int[]> comp = bfs(x, y, board[y][x]);
                        if (comp.size() >= 4) {
                            toRemove.addAll(comp);
                        }
                    }
                }
            }

            // 아무 것도 안 터지면 종료
            if (toRemove.isEmpty()) break;

            // 동시에 터지기
            for (int[] p : toRemove) {
                board[p[1]][p[0]] = '.';
            }

            // 중력 적용
            applyGravity();

            // 연쇄 +1
            chain++;
        }

        System.out.println(chain);
    }

    // 동일 색 연결 컴포넌트 BFS로 모으기
    static List<int[]> bfs(int sx, int sy, char color) {
        // 큐
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        // 같은색 블럭 4개 이상 연결될 시 블럭 파괴용 좌표
        List<int[]> comp = new ArrayList<>();
        // 처음 방문여부 체크
        visited[sy][sx] = true;
        // 큐 초기화
        queue.add(new int[]{sx, sy});

        while (!queue.isEmpty()) {
            // 큐에서 현재좌표 뽑아내기
            int[] cur = queue.poll();
            // x, y좌표 뽑기
            int x = cur[0], y = cur[1];
            // 현재 좌표 블럭 파괴용 좌표에 담기
            comp.add(cur);

            // 4방향으로 탐색 시작
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                // 현재 좌표가 틀을 벗어나지 않고, 다음 지역이 같은색에 방문하지 않은 지역이면 이동하기
                if (0 <= nx && nx < W && 0 <= ny && ny < H
                        && !visited[ny][nx]
                        && board[ny][nx] == color) {
                    // 다음 지역 방문체크
                    visited[ny][nx] = true;
                    // 큐에 다음지역 추가하기
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        // 파괴될 블럭 좌표 배열 출력
        return comp;
    }

    // 각 열마다 아래로 당기기(두 포인터 방식)
    static void applyGravity() {
        for (int x = 0; x < W; x++) {
            int write = H - 1; // 채워넣을 위치(바닥부터 위로)
            // 현재 열의 바닥행 부터 맨 위로 올라오는 반복문 실행
            for (int y = H - 1; y >= 0; y--) {
                // 현재 위치에 블럭이 존재하면
                if (board[y][x] != '.') {
                    // 블럭 저장
                    char c = board[y][x];
                    // 현재 블럭을 .으로 교체
                    board[y][x] = '.';
                    // 채워넣을 위치에 블럭 넣기
                    board[write][x] = c;
                    // 채워넣을 위치 상승
                    write--;
                }
            }
        }
    }
}
