package JAVA_AlgorithmStudy.Oct.week04.오예지;

import java.util.*;
import java.io.*;

public class boj_2630 {
    static int N;
    static int[][] field;
    static int resultCnt[] = new int[2];

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N은 2의 제곱수
        N = Integer.parseInt(br.readLine());

        field = new int[N][N];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, N, N);

        sb.append(resultCnt[0]).append("\n").append(resultCnt[1]);

        System.out.println(sb);

    }

    public static void recur(int x1, int y1, int x2, int y2) {
        if (x1==x2 && x2==y2) {
            resultCnt[field[x1][y1]]++;
        }

        int result = check(x1, y1, x2, y2);

        int midX = (x1+x2)/2;
        int midY = (y1+y2)/2;

        if (result==2) {
            recur(x1, y1, midX, midY);
            recur(x1, midY, midX, y2);
            recur(midX, y1, x2, midY);
            recur(midX, midY, x2, y2);
        }else {
            resultCnt[result]++;
        }
    }

    public static int check(int x1, int y1, int x2, int y2) {
        int temp = field[x1][y1];
        for (int i=x1; i<x2; i++) {
            for(int j=y1; j<y2; j++) {
                if (field[i][j] != temp) {
                    return 2;
                }
            }
        }

        return temp;
    }
}
