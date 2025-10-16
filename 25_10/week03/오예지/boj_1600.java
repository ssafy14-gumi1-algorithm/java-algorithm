package JAVA_AlgorithmStudy.Oct.week03.오예지;

import java.io.*;
import java.util.*;

// 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내는 프로그램을 작성
// 배열의 0, 0부터 H, W까지 이동해야함
// 원숭이의 동작수의 최솟값을 출력 -> 사실 말처럼 이동하는걸 다 써야 최소가 되지 않나 싶기도 하고,

// 다익스트라?? -> 이게 맞는거 같긴 함 -> 근데 말처럼 이동하는 K번을 어떻게 줄 것인지
// bfs?? -> 최소이동을 보장할 수 없음 => 먼저 도착했다고 가장 적은 이동을 한게 아님 이동방식이 2가지이기 때문에

public class boj_1600 {
    static int K, W, H;
    static int[][] arr;
    static boolean[][][] visited;

    // 인접한 칸 이동을 위해서
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    // 체스말처럼 이동
    static int[] hi = {-1, 1, 2, 2, -1, 1, -2, -2};
    static int[] hj = {2, 2, -1, 1, -2, -2, -1, 1};

    static class Coord {
        int x;
        int y;
        int k;
        int moves;

        public Coord(int x, int y, int k, int moves) {
            this.x = x;
            this.y = y;
            this.k = k;
            // 지금까지 이동한 횟수
            this.moves = moves;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][W];			// 격자판
        visited = new boolean[H][W][K+1];		// 이동횟수를 입력할 배열


        // 격자판 입력 받음
        for (int i=0; i<H; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j<W; j++) {
                arr[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        int result = bfs(0, 0);

        System.out.println(result);
    }

    public static int bfs(int si, int sj) {
        Queue<Coord> q = new LinkedList<>();
        q.offer(new Coord(si, sj, 0, 0));
        // 0으로 채워진 배열
        visited[si][sj][0] = true;

        while(!q.isEmpty()) {
            Coord cur = q.poll();
            int ti = cur.x;
            int tj = cur.y;
            int k = cur.k;
            int moves = cur.moves;

            if (ti == H-1 && tj == W-1) {
                return moves;
            }

            // 일단 기본 이동
            for (int d=0; d<4; d++) {
                int ni = ti + di[d];
                int nj = tj + dj[d];

                if (ni>=0 && ni<H && nj>=0 && nj<W) {
                    // 장애물을 만남
                    if (arr[ni][nj] == 1) continue;
                    // 이미 방문한 곳 -> 이미 방문했던곳을 다시 방문하는 것은 최소 이동횟수가 되지 않음
                    if (visited[ni][nj][k]) continue;

                    visited[ni][nj][k] = true;
                    q.offer(new Coord(ni, nj, k, moves+1));
                }
            }

            // 말처럼 이동하는 경우
            if (k<K) {
                for (int d=0; d<8; d++) {
                    int ni = ti + hi[d];
                    int nj = tj + hj[d];

                    if (ni>=0 && ni<H && nj>=0 && nj<W) {
                        if (arr[ni][nj] == 1) continue;
                        if (visited[ni][nj][k+1]) continue;

                        visited[ni][nj][k+1] = true;
                        q.offer(new Coord(ni, nj, k+1, moves+1));
                    }
                }
            }
        }
        // while문을 빠져나갈때까지 도착점에 가지 못한 경우
        return -1;
    }
}
