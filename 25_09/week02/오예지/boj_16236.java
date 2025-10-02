package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16236 {

    static int N;
    static int[][] arr;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};
    static int moveCnt = 0, eatCnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        int si = 0, sj = 0;
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9){
                    si = i;
                    sj = j;
                }
            }
        }

        arr[si][sj] = 0;
        int size = 2;

        moveShark(si, sj, size);

        System.out.println(moveCnt);

    }

    static void moveShark(int i, int j, int size){
        while (true) {
            List<int[]> fish = findFish(i, j, size);

            if (fish.isEmpty()) return;

            // 먹이 후보 리스트를 우선순위에 따라 정렬하고, 가장 우선순위가 높은 물고기를 선택
            // 거리, 행, 열 순으로 정렬
            fish.sort((a, b) -> {
                    if (a[0] != b[0]) return a[0] - b[0];  // 거리가 가까운 순
                    if (a[1] != b[1]) return a[1] - b[1];  // 위쪽에 있는 순
                    return a[2] - b[2];                    // 왼쪽에 있는 순
            });

            int[] target = fish.get(0);     // 가장 앞에 있는 물고기
            // 거리, 행, 열순으로 배열에 들어가 있음
            int dist = target[0];
            int ni = target[1];
            int nj = target[2];

            i = ni;
            j = nj;
            moveCnt += dist;

            arr[ni][nj] = 0;
            eatCnt++;
            if (eatCnt == size) {
                size++;
                eatCnt = 0;
            }
        }
    }

    static List<int[]> findFish(int si, int sj, int size){
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[N][N];
        // visited 배열을 만드는데, -1로 채워줌
        for (int [] row : visited) Arrays.fill(row, -1);

        q.add(new int[]{si, sj});
        visited[si][sj] = 0;

        List<int[]> fish = new ArrayList<>();

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int d = 0; d < 4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (ni >= 0 && ni < N && nj >= 0 && nj < N && visited[ni][nj] == -1){
                    if (arr[ni][nj] <= size){
                        visited[ni][nj] = visited[ci][cj] + 1;
                        q.add(new int[]{ni, nj});

                        if (arr[ni][nj] > 0 && arr[ni][nj] < size){
                            fish.add(new int[]{visited[ni][nj], ni, nj});
                        }
                    }
                }
            }
        }
        return fish;
    }
}
