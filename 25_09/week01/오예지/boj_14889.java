package week01.오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14889 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideTeam(0, 0);
        System.out.println(minDiff);
    }

    // 조합을 재귀로 구현함 -> 지피티가 해줬다
    static void divideTeam(int idx, int count){
        if (count == N / 2){
            calcDiff();
            return ;
        }

        for (int i = idx; i < N; i++) {
            if (!visited[i]){
                visited[i] = true;
                divideTeam(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    // 두 팀의 차이값을 구함 - 최솟값
    static void calcDiff() {
        int sumStart = 0;
        int sumLink = 0;

        // 조합을 for문으로 구현함 -> 팀에서 2명을 선택
        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++){
                if (visited[i] && visited[j]) {
                    sumStart += arr[i][j] + arr[j][i];
                } else if (!visited[i] && !visited[j]){
                    sumLink += arr[i][j] + arr[j][i];
                }
            }
        }

        int diff = Math.abs(sumStart - sumLink);
        minDiff = Math.min(minDiff, diff);
    }
}
