// 치즈

// 아래 <그림 1>과 같이 정사각형 칸들로 이루어진 사각형 모양의 판이 있고,
// 그 위에 얇은 치즈(회색으로 표시된 부분)가 놓여 있다.
// 판의 가장자리(<그림 1>에서 네모 칸에 X친 부분)에는 치즈가 놓여 있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있다.

// 이 치즈를 공기 중에 놓으면 녹게 되는데 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
// 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다.
// <그림 1>의 경우, 치즈의 구멍을 둘러싼 치즈는 녹지 않고 ‘c’로 표시된 부분만 한 시간 후에 녹아 없어져서 <그림 2>와 같이 된다.

// 다시 한 시간 후에는 <그림 2>에서 ‘c’로 표시된 부분이 녹아 없어져서 <그림 3>과 같이 된다.

// <그림 3>은 원래 치즈의 두 시간 후 모양을 나타내고 있으며, 남은 조각들은 한 시간이 더 지나면 모두 녹아 없어진다.
// 그러므로 처음 치즈가 모두 녹아 없어지는 데는 세 시간이 걸린다.
// <그림 3>과 같이 치즈가 녹는 과정에서 여러 조각으로 나누어 질 수도 있다.

// 입력으로 사각형 모양의 판의 크기와 한 조각의 치즈가 판 위에 주어졌을 때,
// 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과
// 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 구하는 프로그램을 작성하시오.

// [문제풀이]
// 외곽의 치즈만 시간이 지나며 녹는다(외부 공기와 접촉한 치즈).
// 매 시간 다음을 반복한다:
// 1) (0,0) 외부 공기에서 시작해 BFS로 빈 칸(0)만 퍼지며 탐색한다.
// 2) 탐색 중 인접한 칸이 치즈(1)면, 그 좌표를 “이번 시간에 녹일 후보”에 수집한다(이 칸은 큐에 넣지 않음).
// 3) BFS 종료 후 후보들을 모두 0으로 바꾸어 녹인다.
// 4) 녹인 개수를 기록하고, 더 이상 녹일 치즈가 없으면 종료.
// 시간복잡도는 각 시간당 O(N*M), 전체도 O(시간 * N*M)로 N,M ≤ 100에서 충분.

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;               // 0: 빈 칸, 1: 치즈
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt")); // 로컬 테스트 시 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        board = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int hours = 0;       // 모두 녹는 데 걸린 시간
        int lastCheese = 0;  // 모두 녹기 1시간 전에 남아있던 치즈 개수

        while (true) {
            int melted = meltOnce(); // 이번 시간에 녹인 치즈 개수
            if (melted == 0) break; // 더 이상 녹일 치즈가 없으면 종료
            lastCheese = melted;
            hours++;
        }

        System.out.println(hours);
        System.out.println(lastCheese);
    }

    // 치즈 녹이기 1 싸이클: 외부 공기(0,0)에서 BFS, 외부 공기와 맞닿은 치즈를 수집 후 일괄 제거
    static int meltOnce() {
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{0, 0});
        visited[0][0] = true;

        List<int[]> toMelt = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (visited[ny][nx]) continue;

                if (board[ny][nx] == 1) {
                    // 외부 공기와 접한 치즈 → 이번 시간에 녹인다
                    visited[ny][nx] = true;          // 중복 수집 방지
                    toMelt.add(new int[]{nx, ny});
                } else { // 빈 칸(외부 공기) → 계속 확장
                    visited[ny][nx] = true;
                    q.addLast(new int[]{nx, ny});
                }
            }
        }

        // 수집된 치즈를 모두 녹임
        for (int[] p : toMelt) {
            int x = p[0], y = p[1];
            board[y][x] = 0;
        }
        return toMelt.size();
    }
}