package boj.boj_2468_safeRegion;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*TODO:
- 상수

- 변수

- 조건

- 구하는 값

- 아이디어

*/

public class Main {
    static int N;
    static final int[] di = {0,1,0,-1};
    static final int[] dj = {1,0,-1,0};



    // 섬 한개 구하기
    private static int bfs(int[][] arr, int[] start, int currentIslandNumber, int[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = 1;

        while (!q.isEmpty()){

            int [] v = q.remove();
            int i_idx = v[0];
            int j_idx = v[1];
            for (int dir = 0; dir < 4; dir++) {
                int ni = i_idx + di[dir];
                int nj = j_idx + dj[dir];
                if (0<=ni && ni< N && 0<=nj && nj<N){
                    if (arr[ni][nj] > 0 && visited[ni][nj] == 0){
                        visited[ni][nj] = 1;
                        q.add(new int[] {ni, nj});
                    }

                }
            }

        }
        currentIslandNumber ++;
        return currentIslandNumber;

    }
    // 해당 물높이의 섬의 총 개수 구하기
    public static int retrieveIslandNumber(int[][] arr){
        int[][] visited = new int[N][N];

        // 그래프 순회하면서 섬의 총 개수 구하기
        int islandNumber = 0;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if (arr[i][j] > 0 && visited[i][j] == 0) {
                    islandNumber = bfs(arr, new int[]{i, j}, islandNumber, visited);
                }
            }
        }

        return islandNumber;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];

        int maxHeight = 0;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j ++){

                int height = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, height);
                arr[i][j] = height;
            }
        }

        int maxIslandNumber = 1;
        for (int k = 0; k < maxHeight; k++) {
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++){
                    arr[i][j] = Math.max(arr[i][j] - 1, 0);
                }
            }
            // 각 물높이별 로 섬 개수의 최댓값구하기
            maxIslandNumber = Math.max(maxIslandNumber, retrieveIslandNumber(arr));
        }

        System.out.println(maxIslandNumber);

    }
}


