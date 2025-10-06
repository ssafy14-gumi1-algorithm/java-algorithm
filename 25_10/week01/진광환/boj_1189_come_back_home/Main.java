package boj.boj_1189_come_back_home;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
    static int R, C, K, result;
    static char[][] arr;
    static int[][] visited;
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        visited = new int[R][C];
        result = 0;
        for (int i = 0; i < R; i++) {
            Arrays.fill(visited[i], -1);
            arr[i] = br.readLine().toCharArray();
        }
        visited[R - 1][0] = 1;
        dfs(R - 1, 0, 1);
        System.out.println(result);
    }

    static void dfs(int cur_i, int cur_j, int dist) {
        if (dist == K){
            if (cur_i == 0 && cur_j == C-1){
                result++;
            }
        }
        // 일단 최단거리를 연구해보자

        for (int dir = 0; dir < 4; dir++) {
            int ni = cur_i + di[dir];
            int nj = cur_j + dj[dir];
            if (0 <= ni && ni < R && 0 <= nj && nj < C && visited[ni][nj] == -1 && arr[ni][nj] != 'T') {
                visited[ni][nj] = 1;
                dfs(ni, nj, dist+1);
                visited[ni][nj] = -1;
            }
        }

    }
}

