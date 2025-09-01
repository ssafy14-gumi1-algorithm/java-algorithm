package boj.boj_9095_sumof123;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*TODO:
    - 상수
        1,2,3
    - 변수
        n
    - 조건
        합을 나타낼 때는 수를 1개 이상 사용해야 한다
    - 구하는 값
        n을 1,2,3으로 나타내는 방법의 수
    - 아이디어
        재귀함수를 호출해서 1을 더하는 경우, 2를 더하는 경우 구하기
        스태틱 변수로 합 표현하기

*/
public class Main {

    static int N;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++){
            N = Integer.parseInt(br.readLine());
            result = 0;
            recur(0);
            System.out.println(result);
        }
    }

    private static void recur(int sum) {

        if (sum == N ) {
            result++;
            return;
        }

        if (sum > N) return;

        recur(sum+1);
        recur(sum+2);
        recur(sum+3);
    }
}
