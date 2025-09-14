package Sep_25.week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14888 {

    public static int n;
    public static int[] numbers;

    public static int maxResult = Integer.MIN_VALUE;
    public static int minResult = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int add = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        int mul = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

        dfs(1, numbers[0], add, sub, mul, div);

        System.out.println(maxResult);
        System.out.println(minResult);
    }

    // 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행
    // 나눗셈은 정수 나눗셈으로 몫만 취한다.
    // 음수를 양수로 나눌 때는 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것
    private static void dfs(int idx, int lhs, int add, int sub, int mul, int div) {

        // 계산 끝났으면 최대 최소 결과 갱신해줘야 함
        if (idx == n) {
            maxResult = Math.max(maxResult, lhs);
            minResult = Math.min(minResult, lhs);
            return;
        }

        // 계산해야 할 값
        int rhs = numbers[idx];

        if (add > 0) {
            dfs(idx + 1, lhs + rhs, add - 1, sub, mul, div);
        }
        if (sub > 0) {
            dfs(idx + 1, lhs - rhs, add, sub - 1, mul, div);
        }
        if (mul > 0) {
            dfs(idx + 1, lhs * rhs, add, sub, mul - 1, div);
        }
        if (div > 0) {
            // rhs는 항상 양수(1 ≤ Ai ≤ 100)
            if (lhs < 0) {
                lhs *= -1; // 음수면 양수로 만든 다음에
                dfs(idx + 1, lhs / rhs * -1, add, sub, mul, div - 1);
            } else {
                dfs(idx + 1, lhs / rhs, add, sub, mul, div - 1);
            }
        }
    }
}
