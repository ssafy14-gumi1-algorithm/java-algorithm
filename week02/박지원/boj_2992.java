package week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2992 {

    public static String x;
    public static int len;
    public static boolean[] visited;
    public static char[] numbers;

    public static int minNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        x = br.readLine();
        len = x.length();

        minNum = Integer.MAX_VALUE;
        visited = new boolean[len];
        numbers = new char[len];

        // 정수 X가 주어졌을 때, X와 구성이 같으면서 X보다 큰 수 중 가장 작은 수를 출력
        // 느낌이 순열 느낌임
        perm(0);
        System.out.println(minNum == Integer.MAX_VALUE ? 0 : minNum);
    }

    public static void perm(int cnt) {
        // 종료 조건
        if (cnt == len) {
            int permNum = Integer.parseInt(String.valueOf(numbers));

            if (permNum > Integer.parseInt(x)) {
                minNum = Math.min(minNum, permNum);
            }
            return;
        }

        // 재귀 호출
        for (int i = 0; i < len; i++) {
            if (visited[i]) continue;

            numbers[cnt] = x.charAt(i);
            visited[i] = true;
            perm(cnt + 1);
            visited[i] = false;
        }
    }
}
