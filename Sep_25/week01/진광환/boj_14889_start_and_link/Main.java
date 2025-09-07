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
        System.out.println(solve());
    }

    static int solve() {


        recur(0);




        return result;
    }

    static boolean includes(int target) {
        for (int i = 0; i < N; i ++){
            if (target == path[i]) return true;
        }

        return false;
    }

    static void recur(int idx) {

        if (idx >= 2 && path[idx-1] <= path[idx-2]) {


            return;
        }

        if (idx == R) {
            int index = R;
            for (int i = 1; i <= N; i++){
                if (!includes(i)){
                    path[index] = i;
                    index++;
                }
            }
//            System.out.println(Arrays.toString(path));

            result = Math.min(calculate(path), result);

            for (int i = R; i < N; i ++){
                path[i] = -1;
            }

            return;
        }


        for (int i = 1; i <= N; i++) {
            path[idx] = i;
            recur(idx + 1);
            path[idx] = -1;
        }
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


