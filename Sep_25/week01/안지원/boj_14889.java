// 스타트와 링크

// 축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다.
// 이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.

// 사람에게 번호를 1부터 N까지로 배정했고, 아래와 같은 능력치를 조사했다.
// 능력치 Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
// 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합이다.
// Sij는 Sji와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.

// [입력]
// 첫째 줄에 N(4 ≤ N ≤ 20, N은 짝수)이 주어진다.
// 둘째 줄부터 N개의 줄에 S가 주어진다.
// 각 줄은 N개의 수로 이루어져 있고, i번 줄의 j번째 수는 Sij 이다.
// Sii는 항상 0이고, 나머지 Sij는 1보다 크거나 같고, 100보다 작거나 같은 정수이다.

// [출력]
// 첫째 줄에 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력한다.

// [문제풀이]
// 문제 느낌이 조합이네
// (n)C(n//2)을 먼저 구해서 팀을 2팀으로 먼저 만들어줌
// 그 2팀에서 (n//2)C(2)로 조합들을 나눠줘서 조합의 값을 다 더해주고, 그 차이를 절대값으로 구해주기
// 구한 절대값을 현재 최솟값과 비교하면서, 가장 낮은 차이를 구하면 됨
// 만약 0이 나온다면 바로 0 출력하면 됨
import java.io.*;
import java.util.*;
class boj_14889 {
    static int N;
    // 시너지 표
    static int[][] synergy;
    static List<Integer> start = new ArrayList<>();       // start팀 담는 배열
    static int answer = Integer.MAX_VALUE; // 정답의 최소값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        synergy = new int[N][N];
        // 시너지 표 만들기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                synergy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1);  // idx=1부터, 현재 스타트팀 인원=1

        System.out.println(answer);
    }

    // idx: 현재 사람 인덱스, cnt: 현재까지 스타트팀에 뽑은 수
    static void dfs(int idx, int cnt) {
        // 스타트팀이 N/2명이 되면 점수 계산
        if (cnt == N / 2) {
            updateAnswer();
            return;
        }
        // 끝까지 갔으면 중단
        if (idx >= N) return;

        // 현재 idx를 스타트팀에 넣는 경우
        start.add(idx+1);
        dfs(idx + 1, cnt + 1);

        // 현재 idx를 링크팀에 두는 경우
        // ArrayList는 왜 pop처럼 끝에서 빼질 않는가
        start.remove(start.size() - 1);
        dfs(idx + 1, cnt);
    }

    // 현재 start 리스트에 기반해 링크팀을 구성하고 두 팀의 시너지 차이를 계산
    static void updateAnswer() {
        // 링크팀 구하기용 boolean 배열
        boolean[] inStart = new boolean[N + 1];
        // start팀 맴버인 경우 true로 변경
        for (int v : start) inStart[v] = true;

        // 링크팀
        List<Integer> link = new ArrayList<>();
        // 스타트 팀에 없는 맴버들 링크 팀에 추가하기
        for (int i = 1; i <= N; i++) if (!inStart[i]) link.add(i);

        // 스타트 점수, 링크 점수
        int sSum = 0, lSum = 0;

        // 스타트팀 시너지 합산 (1-based → 0-based 인덱싱으로 변환)
        for (int i = 0; i < start.size(); i++) {
            int a = start.get(i) - 1;
            for (int j = i + 1; j < start.size(); j++) {
                int b = start.get(j) - 1;
                sSum += synergy[a][b] + synergy[b][a];
            }
        }

        // 링크팀 시너지 합산
        for (int i = 0; i < link.size(); i++) {
            int a = link.get(i) - 1;
            for (int j = i + 1; j < link.size(); j++) {
                int b = link.get(j) - 1;
                lSum += synergy[a][b] + synergy[b][a];
            }
        }

        // 스타트팀, 링크팀 점수차이 answer랑 비교해서 낮은거로 업데이트
        answer = Math.min(answer, Math.abs(sSum - lSum));
    }
}
