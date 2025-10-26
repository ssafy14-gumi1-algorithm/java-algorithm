package JAVA_AlgorithmStudy.Oct.week04.오예지;

import java.util.*;
import java.io.*;

public class boj_1726 {
    static int N, M;
    static int[][] field;
    static boolean[][][] visited;

    // 동서남북이 순서대로  1, 2, 3, 4
    static int[] di = {0, 0, 0, 1, -1};
    static int[] dj = {0, 1, -1, 0, 0};

    static int ei, ej, ed;

    static class Coord{
        int i, j, dir, cnt;

        public Coord(int i, int j, int dir, int cnt) {
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        field = new int[M+1][N+1];
        visited = new boolean[M+1][N+1][5];

        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로봇의 출발 지점 위치와 바라보는 방향
        st = new StringTokenizer(br.readLine());
        int si = Integer.parseInt(st.nextToken());
        int sj = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());

        // 로봇의 도착 지점의 위치와 바라보는 방향
        st = new StringTokenizer(br.readLine());
        ei = Integer.parseInt(st.nextToken());
        ej = Integer.parseInt(st.nextToken());
        ed = Integer.parseInt(st.nextToken());

        // ----------------------- 입력 끝 -----------------------

        System.out.println(bfs(si, sj, sd));
    }

    // 큐에 좌표랑 현재 방향이 같이 들어가야함
    public static int bfs(int si, int sj, int sd) {
        Queue<Coord> q = new LinkedList<>();
        q.add(new Coord(si, sj, sd, 0));
        visited[si][sj][sd] = true;

        while(!q.isEmpty()) {
            Coord cur = q.poll();
            int ti = cur.i;
            int tj = cur.j;
            int td = cur.dir;
            int move = cur.cnt;

            // 종료 조건
            if (ti==ei && tj==ej && td==ed) {
                return move;
            }

            // Turn dir -> 회전하지 않음, 왼쪽, 오른쪽
            for (int dir=0; dir<3; dir++) {
                if (dir==0) {			// 회전하지 않음
                    continue;
                }else {
                    int newDir;
                    if(dir==1) {		// 왼쪽으로 회전
                        if (td==1) {
                            newDir = 4;
                        }else if(td==2) {
                            newDir = 3;
                        }else if(td==3) {
                            newDir = 1;
                        }else {
                            newDir = 2;
                        }
                    }else {				// 오른쪽으로 회전
                        if (td==1) {
                            newDir = 3;
                        }else if(td==2) {
                            newDir = 4;
                        }else if(td==3) {
                            newDir = 2;
                        }else {
                            newDir = 1;
                        }
                    }
                    if (!visited[ti][tj][newDir]) {
                        visited[ti][tj][newDir] = true;
                        q.add(new Coord(ti, tj, newDir, move+1));
                    }
                }
            }

            // Go k
            for (int k=1; k<=3; k++) {
                int ni = ti + di[td]*k;
                int nj = tj + dj[td]*k;
                int nd = td;

                // 범위를 벗어나면 for문을 종료
                if (ni<=0 || ni>M || nj<=0 || nj>N) break;

                if (field[ni][nj] == 1) break;

                // 이미 같은 방향으로 해당 칸을 방문한 적이 있거나 갈 수 없는 공간
                if (visited[ni][nj][nd]) continue;

                visited[ni][nj][nd] = true;
                q.add(new Coord(ni, nj, nd, move+1));

            }
        }

        return 0;
    }
}
