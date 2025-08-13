package week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_9095 {
    public static int answer;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            answer = 0;

            solution(0, 0, 0, 0);
            System.out.println(answer);
        }
    }

    /**
     *
     * @param idx 지금까지 고려한 idx
     * @param one 1의 갯수
     * @param two 2의 갯수
     * @param three 3의 갯수
     */
    private static void solution(int idx, int one, int two, int three) {
        // 종료조건1: 다 더한 값이 n이 된 경우
        if (one + two * 2 + three * 3 == n) {
            answer++;
            return;
        }

        // 종료조건2: idx가 n까지 간 경우
        if(idx==n){
            return;
        }

        solution(idx+1, one+1, two, three);
        solution(idx+1, one, two+1, three);
        solution(idx+1, one, two, three+1);
    }

}
