// 뱀

// 'Dummy' 라는 도스게임이 있다.
// 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다.
// 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.

// 게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다.
// 보드의 상하좌우 끝에 벽이 있다. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다.
// 뱀은 처음에 오른쪽을 향한다.

// 뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.
// - 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
// - 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
// - 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
// - 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.

// 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.

// [입력]
// N (2 ≤ N ≤ 100)
// K (0 ≤ K ≤ 100)  → 다음 K줄: 사과 위치 (행, 열) (1-based)
// L (1 ≤ L ≤ 100)  → 다음 L줄: X C  (X초가 끝난 뒤 C가 'L' 또는 'D')

// [출력]
// 게임이 몇 초에 끝나는지 출력.

// [문제풀이]
// 빈공간: 0, 뱀: 1, 사과: 2
// - 외곽 벽 처리는 범위 체크(1..N)로 대체.
// - 방향 전환은 “X초가 끝난 뒤”에 적용되므로, 이동 후 시간이 X가 되면 회전.
// - 몸 좌표는 큐로 관리: 머리가 이동한 좌표를 꼬리 후보 큐(tails)에 넣고,
//   사과가 없으면 꼬리를 한 칸 줄인다(맨 앞을 pop).
// - 충돌(벽/자기몸) 즉시 종료 시간을 반환.

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board; // 0: 빈칸, 1: 뱀, 2: 사과
    // 우, 하, 좌, 상 (문제에서 처음 방향은 오른쪽)
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static class Turn {
        int time; // X초가 끝난 뒤
        char dir; // 'L' 혹은 'D'
        Turn(int t, char c) { time = t; dir = c; }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt")); // 로컬 테스트 시 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());
        int K = Integer.parseInt(br.readLine().trim());

        board = new int[N + 2][N + 2]; // 1..N만 사용(가장자리 범위 체크로 벽 대체)

        // 사과 배치
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            board[y][x] = 2;
        }

        int L = Integer.parseInt(br.readLine().trim());
        Deque<Turn> turns = new ArrayDeque<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            turns.addLast(new Turn(t, c));
        }

        System.out.println(simulate(turns));
    }

    // 게임 진행: 종료 시간을 반환
    static int simulate(Deque<Turn> turns) {
        // 시작 상태: 머리 (1,1), 길이 1, 방향 우(0), 시간 0
        // 구현 편의를 위해 “다음 머리 위치”를 큐에 넣는 대신,
        // 현재 머리 좌표와 시간을 담아 한 칸씩 전진한다.
        int time = 0;
        int dir = 0; // 0:우,1:하,2:좌,3:상

        // 뱀의 몸 좌표들을 앞→뒤 순서로 보관(머리쪽이 뒤로 오도록 관리)
        Deque<int[]> body = new ArrayDeque<>();
        body.addLast(new int[]{1, 1});
        board[1][1] = 1;

        // 다음 회전 정보
        int nextTurnTime = turns.isEmpty() ? Integer.MAX_VALUE : turns.peekFirst().time;
        char nextTurnDir  = turns.isEmpty() ? ' ' : turns.peekFirst().dir;

        int headY = 1, headX = 1;

        while (true) {
            // 한 칸 전진
            int ny = headY + dy[dir];
            int nx = headX + dx[dir];
            time++;

            // 벽 또는 자기 몸과 충돌?
            if (ny < 1 || nx < 1 || ny > N || nx > N || board[ny][nx] == 1) {
                return time;
            }

            // 머리 이동
            if (board[ny][nx] == 2) {
                // 사과: 머리 늘리고 꼬리 유지
                board[ny][nx] = 1;
                body.addLast(new int[]{ny, nx});
            } else {
                // 빈칸: 머리 늘리고 꼬리 한 칸 줄이기
                board[ny][nx] = 1;
                body.addLast(new int[]{ny, nx});
                int[] tail = body.pollFirst();
                board[tail[0]][tail[1]] = 0;
            }

            headY = ny; headX = nx;

            // “X초가 끝난 뒤” 회전
            if (time == nextTurnTime) {
                if (nextTurnDir == 'L') dir = (dir + 3) % 4; // 왼쪽 회전
                else if (nextTurnDir == 'D') dir = (dir + 1) % 4; // 오른쪽 회전
                // 다음 회전 정보 준비
                turns.pollFirst();
                if (!turns.isEmpty()) {
                    nextTurnTime = turns.peekFirst().time;
                    nextTurnDir  = turns.peekFirst().dir;
                } else {
                    nextTurnTime = Integer.MAX_VALUE;
                }
            }
        }
    }
}
