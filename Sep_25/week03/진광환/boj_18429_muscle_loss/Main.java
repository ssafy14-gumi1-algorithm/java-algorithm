package boj.boj_18429_muscle_loss;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*TODO:
- 상수

- 변수
    K 하루의 중량 감소량
    N :운동키드의 개수

- 조건
    하루의 1개씩의 키트를 사용한다.

- 구하는 값
    운동기간동안 항상 중량이 500이상이 되도록 하는 경우의 수를 출력하기
- 아이디어

*/
public class Main {
    static int N, answer;
    static int K;
    static int[] kits, path, used;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        kits = new int[N];
        path = new int[N];
        used = new int[N];
        answer = 0;

        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken()) - K;
        }
        perm(0);

        System.out.println(answer);
    }


    public static void perm(int idx){
        if (idx == N) {

            int result = 0;
            for (int j = 0; j < N; j++){
                result = result + path[j];
                if (result < 0) return;
            }
            answer ++;
            return;
        }

        for (int i = 0; i < N; i++){
            if (used[i] == 0) {
                used[i] = 1;
                path[idx] = kits[i];
                perm(idx + 1);
                path[idx] = 0;
                used[i] = 0;
            }

        }
    }
}


