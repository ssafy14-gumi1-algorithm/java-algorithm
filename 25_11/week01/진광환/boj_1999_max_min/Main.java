package boj.boj_1999_max_min;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*TODO:
- 상수

- 변수
    N : 1, 250사이의 값
    K개의 질문이 주어짐
    B : 부분행렬의 크기
    I: 부분 행렬의 가장 위쪽의 행 번호,
    J: 부분 행렬 가장 왼쪽의 열 번호 (1 ≤ i, j ≤ N-B+1)
- 조건
    모든 질문은 같은 B값을 같는다
- 구하는 값
    질문에 답하는 프로그램
    K 개 줄에 차례로 각 질문의 답변을 츨력한다
- 아이디어

*/
public class Main {
    static int N, B, K;
    static int[][] arr;
    static int I, J;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i ++ ){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int attempt = 0; attempt < K; attempt++){
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            J = Integer.parseInt(st.nextToken());
            solve(I, J);
        }
    }

    private static void solve(int I, int J) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = I-1; i < I-1+B; i ++){
            for (int j = J-1; j < J-1+B; j ++){
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }
        System.out.println(max - min);
    }
}


