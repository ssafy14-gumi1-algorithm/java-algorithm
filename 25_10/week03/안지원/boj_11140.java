import java.util.*;

public class Main {
    // LOL

    // 당신 친구 지민이는 지금 할 일이 없다. 그리고 매우 심심하다. 그래서 쓸데없는 짓으로 시간을 때우려고 한다.
    // 그래서 단어 하나가 주어질 때 단어에 'lol'이 들어가도록 글자를 추가하거나 변경하거나 삭제하는 쓸데없는 프로그램을 작성하려고 한다.
    // 하지만 지민이는 갑자기 다른 쓸데없는 다른 프로그램을 작성하고 싶어졌다.
    // 그래서 당신도 할 일이 없기 때문에 지민이의 프로그램을 대신 작성할 것이다.
    // 하지만 당신은 지민이보다 프로그래밍을 못하기 때문에 추가/삭제/변경할 글자수의 최솟값을 출력해야 한다.

    // [입력]
    // 첫 번째 줄에 테스트케이스의 수 T(0 < T ≤ 100)가 주어진다.
    // 두 번째 줄부터 T+1번째 줄까지 단어가 하나씩 주어진다.
    // 단어는 영어 소문자로만 이루어져 있다.
    // 단어의 최대 길이는 50글자이다.

    // [출력]
    // i번째 줄에 해당 단어에 몇 개의 글자를 추가/수정/삭제해야 'lol'이라는 부분 문자열이 생기는지 출력하라.

    // [문제풀이]
    // 문자별로 while문을 돌리자
    // o나 l을 만나면 로직 시작
    // l의 경우엔 lol이 완성되면 바로 0 반환하고 종료
    // l 다음에 o나 l이 나오는지 확인하고, o면 다음이 l인지 추가 확인, 없거나 l이면 길이를 저장, 인덱스를 l부터 설정(1~2)
    // o가 나오면 ol까지 가능한지 확인하고, 길이를 저장
    // 변경은 어떻게 하지>?

    static int W, H, K;
    static int[][] area;
    static int ans = -1;

    // 일반 4방향 델타
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 말 8방향 델타
    static int[] hx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력값 설정 (예시)
        K = sc.nextInt(); // 말 이동 횟수 제한
        W = sc.nextInt(); // 가로
        H = sc.nextInt(); // 세로
        area = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                area[i][j] = sc.nextInt();
            }
        }

        bfs();

        System.out.println(ans);
        sc.close();
    }

    static void bfs() {
        // k 갯수에 따른 방문여부 담을 3차원 배열
        boolean[][][] visited = new boolean[H][W][K + 1];
        visited[0][0][K] = true;

        // x축, y축, 이동횟수, 말 무빙 횟수, 방문여부
        Queue<int[]> deq = new ArrayDeque<>();
        deq.add(new int[]{0, 0, 0, K});

        while (!deq.isEmpty()) {
            int[] now = deq.poll();
            int x = now[0];
            int y = now[1];
            int cnt = now[2];
            int k = now[3];

            // 도착지 도착했으면 가장 먼저 도착한거니 cnt 반환하고 종료
            if (x == W - 1 && y == H - 1) {
                ans = cnt;
                break;
            }

            // 말 무빙 남아있으면 사용한 케이스 추가
            if (k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + hx[i];
                    int ny = y + hy[i];
                    // 현재 방문하는 위치에 동일한 k를 가진 방문여부가 없는지 체크
                    if (nx >= 0 && ny >= 0 && nx < W && ny < H && area[ny][nx] != 1 && !visited[ny][nx][k - 1]) {
                        // 이동 케이스 추가
                        // 현재 남은 k기준 방문체크
                        visited[ny][nx][k - 1] = true;
                        deq.add(new int[]{nx, ny, cnt + 1, k - 1});
                    }
                }
            }

            // 원숭이 무빙 케이스
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 현재 방문하는 위치에 동일한 k를 가진 방문여부가 없는지 체크
                if (nx >= 0 && ny >= 0 && nx < W && ny < H && area[ny][nx] != 1 && !visited[ny][nx][k]) {
                    // 이동 케이스 추가
                    // 현재 남은 k기준 방문체크
                    visited[ny][nx][k] = true;
                    deq.add(new int[]{nx, ny, cnt + 1, k});
                }
            }
        }
    }
}