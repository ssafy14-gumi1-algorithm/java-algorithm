package JAVA_AlgorithmStudy.Oct.week03.오예지;

import java.util.*;
import java.io.*;

// 14500. 테트로미노

// 두 칸을 기준으로 잡고 풀면되지 않을까??
// 두 칸을 기준으로 6개 위치 중에 2개의 위치를 골라서 합을 해줌!

public class boj_14500 {
    static int N, M;
    static int[][] arr;

    // 왼쪽 칸을 기준으로 6개의 블럭의 위치
    static int[] block1X = {-1, -1, 0, 1, 1, 0};
    static int[] block1Y = {0, 1, 2, 1, 0, -1};

    static int[] block2X = {-1, 0, 1, 2, 1, 0};
    static int[] block2Y = {0, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        arr = new int[N][M];

        for (int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        int maxSum = 0;
        // 누워있는 블럭
        for (int i=0; i<N; i++) {
            for (int j=0; j<M-1; j++) {
                // 0부터 5까지의 숫자 중에서 2개를 조합
                for (int x=0; x<6; x++) {
                    // block1
                    int ni1 = i + block1X[x];
                    int nj1 = j + block1Y[x];

                    // 범위를 벗어나는 블럭
                    if (ni1<0 || ni1>=N || nj1<0 || nj1>=M) continue;

                    for (int y=x+1; y<6; y++) {
                        // block2
                        int ni2 = i + block1X[y];
                        int nj2 = j + block1Y[y];

                        // 범위를 벗어나는 블럭
                        if (ni2<0 || ni2>=N || nj2<0 || nj2>=M) continue;

                        // 두 블럭 다 범위에 있다면
                        int totalSum = arr[i][j]+arr[i][j+1]
                                + arr[ni1][nj1] + arr[ni2][nj2];

                        maxSum = Math.max(maxSum, totalSum);
                    }
                }
            }
        }

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M; j++) {
                // 0부터 5까지의 숫자 중에서 2개를 조합
                for (int x=0; x<6; x++) {
                    // block1
                    int ni1 = i + block2X[x];
                    int nj1 = j + block2Y[x];

                    // 범위를 벗어나는 블럭
                    if (ni1<0 || ni1>=N || nj1<0 || nj1>=M) continue;

                    for (int y=x+1; y<6; y++) {
                        // block2
                        int ni2 = i + block2X[y];
                        int nj2 = j + block2Y[y];

                        // 범위를 벗어나는 블럭
                        if (ni2<0 || ni2>=N || nj2<0 || nj2>=M) continue;

                        // 두 블럭 다 범위에 있다면
                        int totalSum = arr[i][j]+arr[i+1][j]
                                + arr[ni1][nj1] + arr[ni2][nj2];

                        maxSum = Math.max(maxSum, totalSum);
                    }
                }
            }
        }

        System.out.println(maxSum);

    }
}
