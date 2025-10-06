package boj.boj_1138_align_in_one_line;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*TODO:
- 상수

- 변수
    N 사람 수 N은 10보다 작거나 같은 자연수

- 조건

- 구하는 값
    줄을 서있는 사람들의 수를 일렬로 출력하기
- 아이디어

*/
public class Main {

    static int N;
    static int[] arr, path;
    static boolean[] used;

    static String result;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        path = new int[N];
        used = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        perm(0);
    }

    static void perm(int idx) {


        if (idx == N){
            result = "";
            if(validateNumber()){
                for (int num: path){
                    System.out.print(num + " ");
                }
            }
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            if(!used[i]){
                used[i] = true;
                path[idx] = i;
                perm(idx + 1);
                path[idx] = 0;
                used[i] = false;
            }
        }
    }

    static boolean validateNumber(){
        // 하나의 숫자에 대해서 검증하기

        for (int j = 0; j < N; j++){
            int smallerNumberCount = 0;
            // 비교할 숫자 정하기
            int numberToCount = path[j];

            // 왼쪽에서 자기보다 작은 숫자 세기
            for ( int i = j; i >= 0; i --){
                if (path[i] > numberToCount) smallerNumberCount ++;
            }

            // 작은 숫자를 비교하기
            if (smallerNumberCount != arr[numberToCount]) return false;
        }
        return true;
    }
}


