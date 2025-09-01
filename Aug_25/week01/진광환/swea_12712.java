package swea.eliminating_flies_3_12712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*TODO:
- 상수
- 변수
    N : 파리가 있는 배열의 크기 : 정사각행렬이므로 가로세로는 상관 ㄴㄴ
    M : 스프레이의 세기 = M만큼 di, dj순회를 돌아야 함
- 조건
    + 또는 x형태로 돌어야 함으로 이중 포문 안에 배열 원소 하나하나마다 1중 for문 두번 돌아야함
    뿌려진 일부가 영역을 벗어나도 상관없다 = 유효성 검사 해야함
- 구하는 값
    한번에 잡을 수 있는 최대 파리수 구하기
- 아이디어
* */
public class Solution {

    static int N;
    static int M;
    static int arr[][];
    static int[] di  = {1,0,-1,0,1,1,-1,-1};
    static int[] dj = {0,1,0,-1,1,-1,-1,1};

    static int solve(int[][] arr){
        int maximum = 0;

        // 모든 칸에 대해
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){

                int temp_sum_cross = arr[i][j];
                int temp_sum_x = arr[i][j];

                // 스프레이 뿌리기
                for(int k=1; k<M; k++){
                    for(int l=0; l<8; l++){
                        int ni = i + (k*di[l]);
                        int nj = j + (k*dj[l]);
                        if(0<=ni && ni<N && 0<=nj && nj<N) {
                            if (l < 4) temp_sum_cross = temp_sum_cross + arr[ni][nj];
                            else temp_sum_x = temp_sum_x + arr[ni][nj];
                        }
                    }
                }
                int temp_max = Math.max(temp_sum_x, temp_sum_cross);
                maximum = Math.max(maximum, temp_max);
            }
        }
        return maximum;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            // 배열 입출력 받기
            for (int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.printf("#%d %d", test_case, solve(arr));
            System.out.println();


        }
    }
}