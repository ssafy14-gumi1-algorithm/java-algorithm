package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2636 {
    static int R;
    static int C;
    static int[][] cheese;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cheese = new int[R][C];
        for (int i=0; i<R; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++){
                cheese[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        int[] result = melting();
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    // 외부 공기 찾기
    static int[][] findOutside() {
        int[][] air = new int[R][C];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        air[0][0] = 1;

        while (!q.isEmpty()){
            int[] cur = q.poll();
            // Coord 쓰려다가 포기..
            int i = cur[0], j = cur[1];

            for (int d=0; d<4; d++){
                int ni = i + di[d];
                int nj = j + dj[d];

                if (0 <= ni && ni < R && 0 <= nj && nj < C){
                    if (cheese[ni][nj] == 0 && air[ni][nj] == 0){
                        air[ni][nj] = 1;
                        q.add(new int[]{ni, nj});
                    }
                }
            }
        }
        return air;
    }

    static List<int[]> findMelt(int[][] air){
        List<int[]> melt = new ArrayList<>();
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if (cheese[i][j] == 1){
                    for (int d=0; d<4; d++){
                        int ni = i + di[d];
                        int nj = j + dj[d];

                        if (0 <= ni && ni < R && 0 <= nj && nj < C){
                            if (air[ni][nj] == 1){
                                melt.add(new int[]{i, j});
                                break;
                            }
                        }
                    }
                }
            }
        }
        return melt;
    }

    static int curCheese(){
        int cnt = 0;
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if (cheese[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    static int[] melting() {
        int time= 0;
        int lastCheese = 0;

        while (true){
            int currentCheese = curCheese();
            if (currentCheese==0) return new int[]{time, lastCheese};

            lastCheese = currentCheese;

            int[][] air = findOutside();
            List<int[]> toMelt = findMelt(air);

            for (int[] pos : toMelt){
                cheese[pos[0]][pos[1]] = 0;
            }

            time ++;
        }
    }
}
