package boj.boj_14889_start_and_link;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*TODO:
- 상수

- 변수
    N: 짝수임
    능력치 sij는 i번 사람과 j번 사람이 같은 팀에 속했을 떄 팀에 더해지는 능력치

- 조건
    각 팀은 N의 반으로 이루어짐
    팀의 능력치는 팀에 속한 모든 쌍의 능력치 sij의 합이다
    sij는 sji와 다를수도
    팀에 더해지는 능력치는 둘다 더해진다
- 구하는 값
    두 팀간의 능력치의 최솟값
- 아이디어
    딱 보면 조합으로 풀어야 할 듯 하다
*/
public class Main {
    static int N;
    static int R;
    static int[][] arr;
    static int[] path;
    static int result = Integer.MAX_VALUE;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        R = N / 2;
        path = new int[N];
        arr = new int[N][N];
        Arrays.fill(path, -1);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        recur(0);
        System.out.println(result);
    }


    static void recur(int idx) {
        // path배열을 활용해 조합인척 하는 순열 만들기
        // '조합'인척의 핵심 부분 : 가지치기
        // 숫자를 고르는 문제이다 -> 같은 원소에 순서가 다른 경우는 오름차순만 남겨두자 그러면 조합이랑 똑같음
        if (idx >= 2 && path[idx-1] <= path[idx-2]) {
            return;
        }


        // 한쪽 팀을 다 골랐을 때
        if (idx == R) {

            // 다른 쪽 팀을 path배열의 R~N위치에 마저 채워넣기
            int index = R;
            for (int i = 1; i <= N; i++){

                // path에 앞쪽부분에 없는 애들을 path뒤쪽에 채워넣기
                if (!includes(i)){
                    path[index] = i;
                    index++;
                }
            }

            // 완성된 배열로 점수의 최솟값 계산하기
            result = Math.min(calculate(path), result);


            // 계산이 완료된 배열에서 다른 쪽 팀의 점수 없애기
            for (int i = R; i < N; i ++){
                path[i] = -1;
            }
            return;
        }

        // 일반적인 순열 코드
        for (int i = 1; i <= N; i++) {
            path[idx] = i;
            recur(idx + 1);
            path[idx] = -1;
        }
    }

    static boolean includes(int target) {
        for (int i = 0; i < N; i ++){
            if (target == path[i]) return true;
        }

        return false;
    }


    static int calculate(int[] targetArray){

        int start = 0;
        int link = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < R; j++){
                if (i!= j){

                    int x = targetArray[i] - 1;
                    int y = targetArray[j] - 1;

                    int a = targetArray[R+i] -1;
                    int b = targetArray[R+j] - 1;
                    start = start + arr[x][y];
                    link = link + arr[a][b];
                }
            }
        }

        int result = Math.abs(start-link);

        return result;
    }
}


