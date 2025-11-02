package JAVA_AlgorithmStudy.Nov.week01.오예지;

import java.util.*;
import java.io.*;

public class boj_2206 {
    static int N, M;
    static int[][] field;
    static int[][][] visited;

    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static class Coord{
        int i, j, c;

        public Coord(int i, int j, int c) {
            this.i = i;
            this.j = j;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new int[N+1][M+1][2];

        // 문제에서 행렬을 1부터 시작함
        field = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            String line = br.readLine();
            for(int j=1; j<=M; j++) {
                field[i][j] = line.charAt(j-1)-'0';
            }
        }

        System.out.println(bfs(1, 1));
    }

    public static int bfs(int si, int sj) {
        Queue<Coord> q = new LinkedList<>();
        visited[si][sj][0] = 1;
        q.add(new Coord(si, sj, 0));

        while(!q.isEmpty()) {
            Coord temp = q.poll();
            int ti = temp.i;
            int tj = temp.j;
            int cnt = temp.c;

            // 종료 조건
            if (ti==N && tj==M) {
                return visited[ti][tj][cnt];
            }

            for(int d=0; d<4; d++) {
                int ni = ti + di[d];
                int nj = tj + dj[d];
                int nCnt = cnt;

                // 1. 일단 범위에 해당하는지 먼저 확인
                if(ni<1 || ni>N || nj<1 || nj>M) continue;

                // 2. 방문했는지 확인
                if (visited[ni][nj][nCnt] != 0) continue;

                // 3. 해당 칸이 벽이 아니라면 그냥 지나감
                if (field[ni][nj]==0) {
                    visited[ni][nj][nCnt] = visited[ti][tj][cnt]+1;
                    q.add(new Coord(ni, nj, nCnt));
                }

                if (field[ni][nj]==1) {
                    // 4. 벽을 부술 수 있는지? -> nCnt가 0인 경우에만 벽을 부시고 지나갈 수 있음
                    if (nCnt==0) {
                        nCnt++;
                        visited[ni][nj][nCnt]=visited[ti][tj][cnt]+1;
                        q.add(new Coord(ni, nj, nCnt));
                    }
                    // 4-1. 벽인데 부실수 없다면 지나갈 수 없음
                    else continue;
                }
            }
        }
        return -1;
    }
}
