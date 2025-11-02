// 벽 부수고 이동하기
//
// N×M의 행렬로 표현되는 맵이 있다.
// 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
// 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
// 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
//
// 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
//
// 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
//
// 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
//
// [입력]
// 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다.
// 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
//
// [출력]
// 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
//
// [문제 풀이]
// 늘 보던 맛인거 같은데
// 벽은 한개까지 부술 수 있음
// 최단경로니깐 BFS를 써야함
// 3차원 visited로 벽을 부순 경우까지 합쳐서 구하면 될듯
// 벽 부수기는 썼다, 안썼다로 나눌 수 있어서 오히려 편함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    static int N, M;
    static int[][] mapArr;
    static boolean[][][] visited; // [y][x][breakLeft] : breakLeft=1(파괴권 남음), 0(없음)
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().trim().split("\\s+");
        N = Integer.parseInt(first[0]);
        M = Integer.parseInt(first[1]);

        mapArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim(); // M자리의 0/1 문자열
            for (int j = 0; j < M; j++) {
                mapArr[i][j] = line.charAt(j) - '0';
            }
        }

        // 3차원 visited
        visited = new boolean[N][M][2];
        // 시작 지점 방문 표시: 파괴권 1개 보유 상태
        visited[0][0][1] = true;

        System.out.println(bfs());
    }

    // BFS
    // 큐 원소: {x, y, cnt, destroy}
    // 좌표, 이동횟수, 파괴가능(0, 1)
    static int bfs() {
        ArrayDeque<int[]> deq = new ArrayDeque<>();
        // 시작점: (0,0), 이동횟수 1, 파괴권 1
        deq.add(new int[]{0, 0, 1, 1});

        while (!deq.isEmpty()) {
            int[] cur = deq.pollFirst();
            int x = cur[0], y = cur[1], cnt = cur[2], destroy = cur[3];

            // 목적지 도착했으면 이동경로 반환
            if (x == M - 1 && y == N - 1) {
                return cnt;
            }

            // 다음 경로 이동
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 현재 지역 이동 가능한지 체크
                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    int cell = mapArr[ny][nx];

                    // 다음 지역이 벽인데, 부술 수 있으면 벽 부수고 다음으로 이동
                    if (cell == 1 && destroy == 1 && !visited[ny][nx][0]) {
                        visited[ny][nx][0] = true;
                        deq.add(new int[]{nx, ny, cnt + 1, 0});
                    }
                    // 0이면 방문여부 확인 후 이동
                    else if (cell == 0 && !visited[ny][nx][destroy]) {
                        visited[ny][nx][destroy] = true;
                        deq.add(new int[]{nx, ny, cnt + 1, destroy});
                    }
                }
            }
        }

        // 도착 못했으면 -1 반환
        return -1;
    }
}
