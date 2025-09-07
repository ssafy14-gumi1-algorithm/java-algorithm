package boj.boj_2573_iceberg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 상수
 * 변수
 * 조건
 * 구하는 값
 * 빙산이 분뢰되는 최초의 시간, 빙산이 다 녹을 때까지 분리되지 않으면 0 출력하기
 * 아이디어
 * 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
 * 4칸 확인하고 하나씩 그만큼 빙산의 높이를 줄이는 함수
 * bfs로 빙산의 개수 세는 함수
 * visited를 어떻게 활용할까?
 * 순회 돌면서 visited에 빙산의 높이를 깎는 수 채우기
 * 만약 개수가 1이라면 빙산 깎고 년도 +1
 * 만약 개수가 2라면 년도 반환하기
 */
public class Main {

    static int N, M;
    static int[][] arr;
    static final int[] di = {0, 1, 0, -1};
    static final int[] dj = {1, 0, -1, 0};
    static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    static int solve() {


        int yearCount = 0;
        while (countIsland() == 1) {
            yearCount++;
            int zeroCount = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int value = visited[i][j];
                    if (value != -1) {
                        arr[i][j] = Math.max(arr[i][j] - value, 0);
                    }

                    if (arr[i][j] == 0) {
                        zeroCount ++;
                    }
                }
            }
            if (zeroCount == N*M) return 0;
        }
        return yearCount;
    }


    static int countIsland() {

        int numberOfIsland = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0 && visited[i][j] == -1) {
                    numberOfIsland = bfs(visited, i, j, numberOfIsland);
                }
            }
        }

        return numberOfIsland;
    }

    static int bfs(int[][] visited, int start_i, int start_j, int islandCount) {

        int[] start = new int[]{start_i, start_j};
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(start);

        visited[start_i][start_j] = countSurroundingSea(start_i, start_j);

        while (!q.isEmpty()) {

            int[] v = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ni = v[0] + di[dir];
                int nj = v[1] + dj[dir];
                if (0 <= ni && ni < N && 0 <= nj && nj < M) {
                    if (visited[ni][nj] == -1 && arr[ni][nj] != 0) {
                        visited[ni][nj] = countSurroundingSea(ni, nj);
                        q.offer(new int[]{ni, nj});
                    }
                }
            }
        }

        return islandCount + 1;
    }

    private static int countSurroundingSea(int i_idx, int j_idx) {

        int seaCount = 0;
        for (int dir = 0; dir < 4; dir++) {
            int ni = i_idx + di[dir];
            int nj = j_idx + dj[dir];
            if (0 <= ni && ni < N && 0 <= nj && nj < M) {
                if (arr[ni][nj] == 0) {
                    seaCount++;
                }
            }
        }
        return seaCount;
    }

}
