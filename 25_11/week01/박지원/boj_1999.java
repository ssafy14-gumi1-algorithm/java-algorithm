package week01.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1999 {

    public static int n, b, k;
    public static int[][] arr;
    private static int[][] maxArr;
    private static int[][] minArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxArr = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(maxArr[i], Integer.MIN_VALUE);
        }
        minArr = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minArr[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - b; j++) {
                for (int l = 0; l < b; l++) {
                    maxArr[i][j] = Math.max(maxArr[i][j], arr[i][j + l]);
                    minArr[i][j] = Math.min(minArr[i][j], arr[i][j + l]);
                }
            }
        }

        // k개의 질문
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            int maxValue = maxArr[x][y];
            int minValue = minArr[x][y];
            for (int j = 0; j < b; j++) {
                maxValue = Math.max(maxValue, maxArr[x+j][y]);
                minValue = Math.min(minValue, minArr[x+j][y]);
            }
            System.out.println(maxValue -  minValue);
        }
    }
}
