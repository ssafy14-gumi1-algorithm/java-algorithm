package boj.boj_2583_yeild_region;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/*TODO:
-상

-변
    M, N :직사각형의 크기 <=100
    K : 그리는 직사각형의 개수

-조
    K개의 직사각형의 내부를 제외한 나머지 부분이 몇개의 분리된 영역으로 나누어진다.
    왼쪽 아래 꼭짓점의 좌표는 0,0이고 오른쪽 위 꼭짓점의 좌표는 N,M이다
-구
     직사각형 내부를 제외한 나머지 부분이 몇개의 분리된 영역으로 나누어지는지,
     분리된 각 영역의 넓이가 얼마인가?
     -> 분리된 영역 넓이의 출력순서는?
-아
    일단 사각형 영역 다 -1로 바꾸기
    사각형 영역을 계산할때 평면 좌표계, index좌표계로 바꾸기
*
* */
public class Main {

    private static int M, N, K, area;
    private static final int[] di = {0,1,0,-1};
    private static final int[] dj = {1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        int[][] arr = new int[M][N];

        // 사각형 -1로 칠해서 못가는 영역으로 만들기
        // 어차피 넓이랑 영역 수만 구하는거라 좌표순서는 상관 없음
        // 왼쪽아래 -> 오른쪽 위 좌표라 그냥 x1에서 x2까지, y1에서 y2까지 가면 됨
        for (int i = 0; i < K; i ++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = y1; j < y2; j ++){
                for (int k = x1; k < x2; k++) {
                    arr[j][k] = -1;
                }
            }
        }

        // 1. 영역의 개수 구하기
        List<Integer> areaArray = new ArrayList<>();

        for (int i = 0; i< M; i ++){
            for (int j = 0; j < N; j++){
                area = 0;
                if (arr[i][j] == 0){
                    dfs(arr, i, j);
                    areaArray.add(area);
                }
            }
        }
        Collections.sort(areaArray);
        int areaArraySize = areaArray.size();
        System.out.println(areaArraySize);

        for (int i = 0; i< areaArraySize; i ++){
            System.out.print(areaArray.get(i) + " ");
        }
    }


    private static void dfs(int[][] arr, int i_idx, int j_idx){
        arr[i_idx][j_idx] = 1;
        area ++;
        for (int i = 0; i < 4; i++){
            int ni = i_idx + di[i];
            int nj = j_idx + dj[i];
            if (0<=ni && ni<M && 0<=nj && nj< N){
                if (arr[ni][nj] == 0){
                    dfs(arr, ni, nj);
                }
            }
        }

    }
}
