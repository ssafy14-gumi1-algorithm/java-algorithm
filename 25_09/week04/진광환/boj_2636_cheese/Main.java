package boj.boj_2636_cheese;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] visited;
    static int cheeseCount;

    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new int[N][M];

        int result = 0;
        while (cheeseRemains()) {
            result ++;
            solve();
        }


        System.out.println(result);
        System.out.println(cheeseCount);

    }

    static void checkAir() {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{0, 0});
        visited[0][0] = 1;

        while (!q.isEmpty()) {

            int[] v = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ni = v[0] + di[dir];
                int nj = v[1] + dj[dir];

                if (0 <= ni && ni < N && 0 <= nj && nj < M) {
                    if (visited[ni][nj] == -1 && arr[ni][nj] == 0) {
                        visited[ni][nj] = 1;
                        q.offer(new int[] {ni, nj});
                    }
                }
            }
        }
    }

    static void meltCheeze(int i, int j) {
        for (int dir = 0; dir < 4; dir++) {
            int ni = i + di[dir];
            int nj = j + dj[dir];

            if (0 <= ni && ni < N && 0 <= nj && nj < M) {
                if (visited[ni][nj] == 1) {
                    arr[i][j] = 0;
                    return;
                }
            }
        }
    }

    static void solve(){
        // visited초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        // 공기 영역 체크
        checkAir();

        // 해당 순회에서 녹기
        cheeseCount = 0;

        // 외각 치즈 녹이기
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (arr[i][j] == 1) cheeseCount++;
                meltCheeze(i, j);
            }
        }
    }

    static boolean cheeseRemains(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (arr[i][j] == 1){
                    return true;
                }
            }
        }
        return false;
    }
}



