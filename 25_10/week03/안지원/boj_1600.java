import java.util.*;

// import sys; sys.stdin = open('input.txt')
// input = sys.stdin.readline
// 말이 되고픈 원숭이

// 동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다.
// 그 녀석은 말(Horse)이 되기를 간절히 원했다.
// 그래서 그는 말의 움직임을 유심히 살펴보고 그대로 따라 하기로 하였다.
// 말은 말이다.
// 말은 격자판에서 체스의 나이트와 같은 이동방식을 가진다.
// 다음 그림에 말의 이동방법이 나타나있다.
// x표시한 곳으로 말이 갈 수 있다는 뜻이다.
// 참고로 말은 장애물을 뛰어넘을 수 있다.

// 근데 원숭이는 한 가지 착각하고 있는 것이 있다.
// 말은 저렇게 움직일 수 있지만 원숭이는 능력이 부족해서 총 K번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다.
// 대각선 방향은 인접한 칸에 포함되지 않는다.

// 이제 원숭이는 머나먼 여행길을 떠난다.
// 격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다.
// 인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다.
// 격자판이 주어졌을 때, 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내는 프로그램을 작성하시오.

// [입력]
// 첫째 줄에 정수 K가 주어진다.
// 둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다.
// 그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 1은 장애물을 뜻한다.
// 장애물이 있는 곳으로는 이동할 수 없다.
// 시작점과 도착점은 항상 평지이다.
// W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다.

// [출력]
// 첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.

// [문제풀이]
// 문제만 봤을땐 백트레킹 써서 풀 수 있을거 같은데
// 판의 크기 = 200 * 200 = 4000
// dfs 하면 스택초과 나려나
// 맨 왼쪽 위(0, 0)에서 맨 오른쪽 아래(H-1, W-1) 까지 가야함
// 말 점프는 장애물 뛰어넘을 수 있는데 원숭이 보법은 못 넘어감
// 델타를 4방향이랑 말 이동 델타 2개를 만들어둬야할듯

// 콜스택을 직접 구현하는 방법은 없을까?
// 일단 무지성 백트레킹 ㄱㄱ
// 아니다 걍 bfs 돌리면 되겠는데?
// k 카운트랑 deepcopy쓴 고유 visited 들고 다니면서 계산하면 되지 않을까?
// 오히려 최단경로 구하는거니 가장 먼저 도착하는 애가 결과값을거니 바로 반복문 중단 때리면 되고

// 시간초과 뜸
// 1차원 배열같은 경우엔 공통 visited 체크 하면서 가면 되긴한데, 2차원 배열도 가능한가..?
// 일단 visited 공통으로 사용 ㄱㄱ

// 훨씬 빠르게 도는거 같은데, 틀렸읍니다 나옴
// break 걸지 말고 ans 최소값 비교해서 업데이트하게 해볼까
// 그래도 틀렸습니다 나옴
// 그냥 동일한 visited 사용하면 안되나봄

// 말 보법이 문제인가?
// visited에 방문했을때 남아있는 k값 까지 똑같은 케이스가 있으면 그떄 걸러내면 되려나..?
// 그럼 visited를 리스트 형태로 만들어서 최대 30번 순회하는 visited체크를 실행하고, 이동할때 visited에 남은 k값을 담아주게 한다면..?
// 30 정도는 시간초과 안나지 않을까?

// k는 30 이하니깐 k를 사용하는 경우를 재귀로 실행해볼까?
// 이때만 visited를 새로운걸로 바꿔서 쓰는거지

// 시간초과 뜨네
// 걍 재귀해볼까
// 절대 안될듯

// k값 기준 visited가 맞았음
// 근데 k를 사용하는 경우엔 k-1 visited를 체크하고, 그냥 이동하는 경우엔 k visited를 체크해야 하는데
// 둘 다 k로 하거나 둘 다 k-1로 해서 틀리고있었음
// 그래도 접근법은 맞았으니 한잔해..........

public class Main {

    static int K, W, H;
    static int[][] area;
    static boolean[][][] visited;
    static int ans = -1;

    // 일반 4방향 델타
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 말 8방향 델타
    static int[] hx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        W = sc.nextInt();
        H = sc.nextInt();
        area = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                area[i][j] = sc.nextInt();
            }
        }

        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        visited = new boolean[H][W][K + 1];
        visited[0][0][K] = true;

        Queue<int[]> deq = new LinkedList<>();
        deq.add(new int[]{0, 0, 0, K}); // x, y, cnt, k

        while (!deq.isEmpty()) {
            int[] cur = deq.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];
            int k = cur[3];

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
                    if (nx >= 0 && ny >= 0 && nx < W && ny < H && area[ny][nx] != 1 && !visited[ny][nx][k - 1]) {
                        visited[ny][nx][k - 1] = true;
                        deq.add(new int[]{nx, ny, cnt + 1, k - 1});
                    }
                }
            }

            // 원숭이 무빙 케이스
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < W && ny < H && area[ny][nx] != 1 && !visited[ny][nx][k]) {
                    visited[ny][nx][k] = true;
                    deq.add(new int[]{nx, ny, cnt + 1, k});
                }
            }
        }
    }
}
