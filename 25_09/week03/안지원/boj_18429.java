// 근손실

// 대학원생은 운동 기간동안 항상 중량이 500 이상으로 유지가 되도록 N일간의 운동 플랜을 세우고자 한다.
// 1일차부터 N일차까지의 모든 기간동안, 어떤 시점에서라도 중량이 500보다 작아지지 않도록 해야 한다.

// N개의 운동 키트에 대한 정보가 주어졌을 때,
// N일간 하루에 1개씩의 운동 키트를 사용하는 모든 경우 중에서,
// 운동 기간동안 항상 중량이 500 이상이 되도록 하는 경우의 수를 출력하는 프로그램을 작성하시오.

// [입력]
// 첫째 줄에 자연수 N과 K가 공백을 기준으로 구분되어 주어진다.
// (1 ≤ N ≤ 8, 1 ≤ K ≤ 50)
// 둘째 줄에 각 운동 키트의 중량 증가량 A가 공백을 기준으로 구분되어 주어진다.
// (1 ≤ A ≤ 50)

// [출력]
// N일 동안 N개의 운동 키트를 사용하는 모든 경우 중에서,
// 운동 기간동안 항상 중량이 500 이상이 되도록 하는 경우의 수를 출력한다.

// [문제풀이]
// N이 8이면 순열로 풀어도 되겠네
// 순열로 돌리면서 한번이라도 500 이하로 떨어지면 다음 경우로 넘어가고, 끝까지 갔으면 cnt+1

import java.io.*;
import java.util.*;

class boj_18429 {
    // 결과 전역변수
    static int ans;
    // N, K
    static int N;
    static int K;
    // 운동 키트
    static int[] workout_kit;
    // 방문 여부
    static boolean[] visited;
    // 순열 구하기
    static void perm(int lev, int muscle){
        // 끝까지 근손실 없을 경우 ans에 1 추가하고 종료
        if(lev == N){
            ans += 1;
            return;
        }

        for(int i = 0; i < N; i++){
            // 다음날 근육
            int next_muscle = muscle + workout_kit[i] - K;
            // 사용하지 않은 키트고. 다음날 근육이 500 이하로 안 떨어졌다면 이어가기
            if(!visited[i] && next_muscle >= 500){
                visited[i] = true;
                perm(lev + 1, next_muscle);
                visited[i] = false;
            }
        }
    }

    // 출력
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 운동 키트 배열 초기화
        workout_kit = new int[N];
        // 방문 배열 초기화
        visited = new boolean[N];
        // 새 st 준비
        st = new StringTokenizer(br.readLine());
        // 운동 키트랑 방문 배열 담기
        for(int i = 0; i < N; i++){
            workout_kit[i] = Integer.parseInt(st.nextToken());
            visited[i] = false;
        }
        // 결과 초기화
        ans = 0;
        // 순열로 근손실 없는 루틴 구하기
        perm(0, 500);
        // 결과 출력
        System.out.println(ans);
    }
}
