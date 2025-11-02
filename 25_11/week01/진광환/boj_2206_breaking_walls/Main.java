package boj.boj_2206_breaking_walls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*TODO:
- 상수
    0 이동할 수 없음
    1 이동할 수 있음

- 변수
    N : 배열 세로
    M : 배열 가로

- 조건
    0,0에서 N-1, M-1로 이동하려고 한다.
    최단경로 이동
    한개의 벽을 부수고 이동하는 것이 경로가 짧아진다면 벽을 한개 부술 수 있다.

- 구하는 값
    벽을 부수고 이동했을 때 최단 거리
    그냥 하나씩 벽을 부숴보면 되는거 아님?
- 아이디어

*/
public class Main {
    static int N, M;
    static int[][] arr;
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int[][][] visited = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j][0] = -1;
                visited[i][j][1] = -1;
            }
        }

        visited[0][0][0] = 1;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] v = q.poll();
            int i = v[0];
            int j = v[1];
            int broken = v[2];

            // 도착 시 도착한 값 반환하기
            // broken 이면 broken된 상태의 것을 반환하기
            if (i == N - 1 && j == M - 1) {
                return visited[i][j][broken];
            }

            for (int dir = 0; dir < 4; dir++) {
                int ni = i + di[dir];
                int nj = j + dj[dir];
                if (0 <= ni && ni < N && 0 <= nj && nj < M) {
                    // 벽인데 아직 부수지 않았고, 부수는 상태로 방문하지 않았다면
                    if (arr[ni][nj] == 1 && visited[ni][nj][1] == -1 && broken == 0) {
                        visited[ni][nj][1] = visited[i][j][0] + 1;
                        q.offer(new int[]{ni, nj, 1});
                    } else if (arr[ni][nj] == 0 && visited[ni][nj][broken] == -1)
                    // 벽이 아니라면 broken상태를 유지하면서 방문 가능
                    {
                        visited[ni][nj][broken] = visited[i][j][broken] + 1;
                        q.offer(new int[]{ni, nj, broken});
                    }
                }
            }
        }
        return -1;
    }
}


