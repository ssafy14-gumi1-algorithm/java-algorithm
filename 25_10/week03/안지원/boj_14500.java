import java.io.*;
import java.util.*;

public class Main {
    // import sys; sys.stdin = open('input.txt')
    // input = sys.stdin.readline
    // 테트로미노

    // 폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.

    // 1. 정사각형은 서로 겹치면 안 된다.
    // 2. 도형은 모두 연결되어 있어야 한다.
    // 3. 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.

    // 아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다.
    // 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.
    // 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.
    // 테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.

    // [입력]
    // 첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)

    // 둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다.
    // i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다.
    // 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.

    // [출력]
    // 첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.

    // [문제 풀이]
    // 사각형이 최대 500 * 500 = 250000임 25만
    // 배열을 순회하면서, 가장 가장 큰 숫자 의 좌표를 따로 저장
    // 해당 좌표들을 기준으로 백트레킹을 실행 = 4칸까지 진행되면 최댓값인지 판단해서 업데이트
    // 최댓값을 구했다면 해당 값을 4로 나눠서 똑같은 숫자가 4번 나와도 최댓값 보다 작은 숫자들은 컷 해주기
    // 가장 큰 숫자 보다 작으면서, 최댓값을 4로 나눈 숫자보단 큰 수의 범위 사이에서 다시 좌표를 찾고 백트레킹 진행하기를 반복
    // 현재 가장 큰 숫자 보다 낮은 수가 4개가 나와도 최댓값을 못 넘기는 경우 최댓값 출력하고 종료

    // 뭔가 억까 케이스들 때문에 시간초과 걸릴거 같은데 일단 고

    // 가능성은 확인했음
    // 근데 ㅜ 이 모양이 변수임
    // 4방향 일자 탐색으로는 저 형태가 나올 수 없음
    // for문을 사용하지 말고, 현재 좌표와 남은 level을 가지고 가능한 모든 경우의 수를 한번에 때려박으면?
    // 조합 짜야겠는데?
    // 와... 시간복잡도 멸망인데 이거?

    // 도저히 모르겠어서 gpt 돌려봄
    // ㅏ, ㅜ, ㅓ, ㅗ만 따로 체크하는 함수 하나 만들어서 ans만 업데이트 해주면 되는거였음
    // dfs도 매 칸 마다 블록 4개를 탐색하는거니깐 스택이 초과될 일 자체가 없었음
    // 너무 이상하게 풀고 있었고...

    static int N, M;
    static int[][] paper_arr;
    static boolean[][] visited;
    static int max_ans = 0;

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static final int[][][] block_dirs = new int[][][] {
            { {0, 0}, {0, 1}, {-1, 0}, {0, -1} },
            { {1, 0}, {0, 0}, {-1, 0}, {0, -1} },
            { {1, 0}, {0, 1}, {0, 0}, {0, -1} },
            { {1, 0}, {0, 1}, {-1, 0}, {0, 0} },
    };

    static void dfs(int y, int x, int res, int lev) {
        // 탐색 완료했으면 최댓값 업데이트 하고 종료
        if (lev == 3) {
            if (res > max_ans) max_ans = res;
            return;
        }

        // 이동
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < M && ny < N && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(ny, nx, res + paper_arr[ny][nx], lev + 1);
                // 원복
                visited[ny][nx] = false;
            }
        }
    }

    // ㅏ, ㅜ, ㅓ, ㅗ 체크용 함수
    static void check_block(int y, int x) {
        for (int[][] block_dir : block_dirs) {
            int res = 0;
            boolean maked = true;
            for (int[] dir : block_dir) {
                int ddy = dir[0];
                int ddx = dir[1];
                int ny = y + ddy;
                int nx = x + ddx;
                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    res += paper_arr[ny][nx];
                } else {
                    maked = false;
                    break;
                }
            }
            if (maked) max_ans = Math.max(max_ans, res);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper_arr = new int[N][M];
        // 백트레킹용 방문 체크
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper_arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 결과 최댓값
        max_ans = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                visited[y][x] = true;
                dfs(y, x, paper_arr[y][x], 0);
                check_block(y, x);
                visited[y][x] = false;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max_ans);
        System.out.println(sb.toString());
    }
}
