package Sep_25.week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_18429 {
    public static int n, k;
    public static int[] machines;

    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        machines = new int[n];
        for (int i = 0; i < n; i++) {
            machines[i] = Integer.parseInt(st.nextToken());
        }

        // idx, weight, visited,
        backtracking(0, 500, new boolean[n]);

        System.out.println(answer);
    }

    private static void backtracking(int idx, int weight, boolean[] visited) {
        // 종료 조건: 끝까지 왔으면
        if(idx==n){
            if(weight<500) return;
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if(visited[i]) continue;

            // 해당 기구를 선택했을 때 중량이 500을 넘지 못하면 선택하지 않음
            int currWeight = weight-k+machines[i];
            if(currWeight<500) continue;

            visited[i] = true;
            backtracking(idx+1, currWeight, visited);
            visited[i] = false;
        }
    }


}
