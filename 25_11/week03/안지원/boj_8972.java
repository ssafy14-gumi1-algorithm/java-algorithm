import java.io.*;
import java.util.*;

/*
미친 아두이노

요즘 종수는 아두이노를 이용해 "Robots"이라는 게임을 만들었다.
종수는 아두이노 한대를 조정하며, 미친 아두이노를 피해다녀야 한다.
미친 아두이노는 종수의 아두이노를 향해 점점 다가온다.
하지만, 미친 아두이노의 움직임은 예측할 수 있다.

게임은 R×C크기의 보드 위에서 이루어지며, 아래와 같은 5가지 과정이 반복된다.

먼저, 종수가 아두이노를 8가지 방향(수직,수평,대각선)으로 이동시키거나, 그 위치에 그대로 놔둔다.
종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되며, 종수는 게임을 지게 된다.
미친 아두이노는 8가지 방향 중에서 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동한다. 즉, 종수의 위치를 (r1,s1), 미친 아두이노의 위치를 (r2, s2)라고 했을 때, |r1-r2| + |s1-s2|가 가장 작아지는 방향으로 이동한다.
미친 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되고, 종수는 게임을 지게 된다.
2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.

종수의 시작 위치, 미친 아두이노의 위치, 종수가 움직이려고 하는 방향이 주어진다.
입력으로 주어진 방향대로 종수가 움직였을 때, 보드의 상태를 구하는 프로그램을 작성하시오.
중간에 게임에서 지게된 경우에는 몇 번째 움직임에서 죽는지를 구한다.

[입력]
첫째 줄에 보드의 크기 R과 C가 주어진다. (1 ≤ R, C ≤ 100)
다음 R개 줄에는 C개의 문자가 주어지며, 보드의 상태이다.
'.'는 빈 칸, 'R'은 미친 아두이노, 'I'는 종수의 위치를 나타낸다.
마지막 줄에는 길이가 100을 넘지않는 문자열이 주어지며, 종수가 움직이려고 하는 방향이다.
5는 그 자리에 그대로 있는 것을 나타내고, 나머지는 아래와 같은 방향을 나타낸다.
보드를 벗어나는 입력은 주어지지 않는다.

[출력]
중간에 게임이 끝나는 경우에는 "kraj X"를 출력한다.
X는 종수가 게임이 끝나기 전 까지 이동한 횟수이다.
그 외의 경우에는 보드의 상태를 입력과 같은 형식으로 출력한다.

[문제 풀이]
미친 아두이노들은 좌표를 배열에 담아둔다.(R을 좌표에 담을때 인덱스로 바꿔준다. 0, 1, 2 이렇게)
종수가 이동할때마다 카운트를 저장해둠
종수 아두이노를 먼저 움직이고, 미친 아두이노들을 이동시킴
아두이노들이 서로 터지게 되면 터지는 아두이노의 인덱스에 해당되는 좌표들을 빈 좌표로 변경해주기
이동 끝까지 다 했으면 인덱스로 표기했던 아두이노들 전부 R로 바꿔주고 출력
중간에 종수 아두이노 터지면 카운트 반환하고 종료

1:48
3:02
*/

public class Main {

    // dx, dy: 숫자 키패드(1~9) 방향 매핑(5는 제자리)
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] dy = { 1, 1, 1,  0, 0, 0, -1,-1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // R
        int M = Integer.parseInt(st.nextToken()); // C

        // game_arr 입력
        char[][] game_arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                game_arr[i][j] = line.charAt(j);
            }
        }

        // moves (문자열을 한 글자씩 int로)
        String moveLine = br.readLine();
        int[] moves = new int[moveLine.length()];
        for (int i = 0; i < moveLine.length(); i++) {
            moves[i] = moveLine.charAt(i) - '0';
        }

        // 광수 좌표
        int gx = 0;
        int gy = 0;
        // 미친 아두이노 좌표
        List<int[]> m_dir = new ArrayList<>();

        // 좌표 담아주기
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (game_arr[y][x] == 'I') {
                    gx = x;
                    gy = y;
                } else if (game_arr[y][x] == 'R') {
                    m_dir.add(new int[]{x, y});
                }
            }
        }

        int cnt = 0;
        boolean catchFlag = false;

        // move 시뮬레이션
        for (int mv : moves) {
            // 업데이트할 게임판, 미친 아두이노 좌표
            char[][] update_game = new char[N][M];
            for (int i = 0; i < N; i++) {
                Arrays.fill(update_game[i], '.');
            }
            List<int[]> update_m_dir = new ArrayList<>();

            cnt += 1;

            // 종수 아두이노 이동
            gx += dx[mv - 1];
            gy += dy[mv - 1];

            // 종수 아두이노가 미친 아두이노를 만나면 바로 게임 종료
            if (game_arr[gy][gx] == 'R') {
                catchFlag = true;
                break;
            }
            update_game[gy][gx] = 'I';

            List<int[]> break_dir = new ArrayList<>();

            // 미친 아두이노 이동
            for (int idx = 0; idx < m_dir.size(); idx++) {
                int[] cur = m_dir.get(idx);
                int x = cur[0];
                int y = cur[1];

                // 종수 아두이노랑 최소 간격(100정도면 될 줄 알았는데 100보다 더 긴 경우가 있었나봄 이거땜에 틀렸음)
                int min_len = Integer.MAX_VALUE;
                int mx = x;
                int my = y;

                for (int i = 0; i < 9; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    int g_len = Math.abs(gx - nx) + Math.abs(gy - ny);
                    if (g_len < min_len) {
                        mx = nx;
                        my = ny;
                        min_len = g_len;
                    }
                }

                // min_len이 0이면 종수 잡은거니 바로 종료
                if (min_len == 0) {
                    catchFlag = true;
                    break;
                }

                // 미친 아두이노 이동시키기(겹치면 폭파)
                if (update_game[my][mx] == 'R') {
                    break_dir.add(new int[]{mx, my});
                } else {
                    update_game[my][mx] = 'R';
                }
            }

            // 종수 잡았으면 종료
            if (catchFlag) break;

            // 폭파할 블록있으면 폭파
            for (int[] p : break_dir) {
                int bx = p[0], by = p[1];
                update_game[by][bx] = '.';
            }

            // 미친 아두이노 좌표 업데이트
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (update_game[y][x] == 'R') {
                        update_m_dir.add(new int[]{x, y});
                    }
                }
            }

            // 업데이트
            game_arr = update_game;
            m_dir = update_m_dir;
        }

        // 잡았으면 카운트 반환, 아니면 game 반환
        StringBuilder sb = new StringBuilder();
        if (catchFlag) {
            sb.append("kraj ").append(cnt).append('\n');
        } else {
            for (int y = 0; y < N; y++) {
                sb.append(game_arr[y]).append('\n');
            }
        }
        System.out.print(sb.toString());
    }
}
