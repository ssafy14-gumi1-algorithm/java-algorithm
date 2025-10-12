package JAVA_AlgorithmStudy.Oct.week02.오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
유기농 배추
N: 밭의 세로 길이
M: 밭의 가로 길이
K: 배추가 심어져있는 위치의 개수
배추의 위치 X, Y
*/

public class boj_1012 {
    static int N, M, K;
    static int[][] arr;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스의 개수
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 가로, 세로, 위치 순으로 주어짐
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N][M];
            visited = new boolean[N][M];

            // X가 가로 좌표, Y가 세로 좌표
            for (int i=0; i<K; i++){
                StringTokenizer st1 = new StringTokenizer(br.readLine());

                int X = Integer.parseInt(st1.nextToken());
                int Y = Integer.parseInt(st1.nextToken());

                arr[Y][X] = 1;

            }
            int count = 0;

            for (int i=0; i<N; i++) {
                for (int j = 0; j < M; j++) {
                    // 해당칸이 배추이면서 방문하지 않은 경우
                    if (arr[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

    public static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int d=0; d<4; d++){
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx>=0 && nx<N && ny>=0 && ny<M){
                    if (!visited[nx][ny] && arr[nx][ny]==1){
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
