package boj.boj_5212_global_warming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] di = {1,0,-1,0};
    static int[] dj = {0,1,0,-1};
    static char[][] namhae;
    static int max_i;
    static int min_i;
    static int max_j;
    static int min_j;

    static void solve(int N, int M){
        max_i = 0;
        min_i = N;
        max_j = 0;
        min_j = M;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if (namhae[i][j] == 'X') {
                    int sea_count = 0;
                    for (int k=0; k<4; k++){
                        int ni = i + di[k];
                        int nj = j + dj[k];
                        if(0<= ni && ni<N && 0<=nj && nj<M){
                            if (namhae[ni][nj] == '.'){
                                sea_count++;
                            }
                        } else sea_count++;
                    }

                    if(sea_count >= 3){
                        namhae[i][j] = 'Y';
                    }
                    else {
                        if (i<min_i) min_i = i;
                        if (i>max_i) max_i = i;

                        if (j<min_j) min_j = j;
                        if (j>max_j) max_j = j;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        namhae = new char[N][M];

        for(int i=0; i<N; i++){
            String line = br.readLine();
            namhae[i] = line.toCharArray();
        }
        solve(N, M);

        for(int i=min_i; i<max_i+1; i++){
            for(int j=min_j; j<max_j+1; j++){
                if(namhae[i][j] == 'Y'){
                    System.out.print('.');
                } else System.out.print(namhae[i][j]);
            }
            System.out.println();
        }
    }
}
