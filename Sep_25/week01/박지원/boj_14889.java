import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_14889 {
    // 스타트 팀 / 링크 팀 이렇게 2개로 나누어서 능력치 계산 nC{n/2} -> minDiff 갱신

    public static int n;
    public static int r;
    public static int[][] stat;

    public static boolean[] selected;

    public static int minDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [input] n 입력 받기
        n = Integer.parseInt(br.readLine());

        stat = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // [solution] nCr 하러 가기
        r = n/2;
        selected = new boolean[n];
        minDiff = Integer.MAX_VALUE;
        comb(0, 0);

        // [output] 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력
        System.out.println(minDiff);
    }

    private static void comb(int idx, int cnt) {
        // 종료 조건(r개 선택함)
        if(cnt == r){
            minDiff = Math.min(minDiff, calcDiff());
            return;
        }

        // [가지치기] 남은 원소로 r-cnt개를 채울 수 없는 경우
        if (n - idx < r - cnt) return;

        // 재귀 호출
        for (int i = idx; i < n; i++) {

            selected[i] = true;
            comb(i+1, cnt+1);
            selected[i] = false;
        }
    }

    // selected 배열 기준으로 스타트팀(selected = true), 링크 팀(selected = false) 나누기
    public static int calcDiff(){
        List<Integer> start = new ArrayList<>();
        List<Integer> link = new ArrayList<>();

        for(int i =0; i<n; i++){
            if(selected[i]) start.add(i);
            else link.add(i);
        }

        // r명 선택했으니까 start.size() == r
        int sumStartTeam = getTeamStat(start);
        int sumLinkTeam = getTeamStat(link);

        return Math.abs(sumStartTeam-sumLinkTeam);
    }

    private static int getTeamStat(List<Integer> team) {
        int teamStat = 0;
        for(int i =0; i<r; i++){
            // team 리스트의 i번째 사람을 꺼내
            int p1 = team.get(i);

            for (int j = i+1; j < r; j++) {
                if(i==j) continue;
                // team 리스트의 j번째 사람을 꺼내
                int p2 = team.get(j);
                // i번째 사람과 j번째 사람의 시너지를 더해줌
                // [i][j]와 [j][i]가 다르기 때문에 양방향 모두 더해줘야 함
                teamStat += stat[p1][p2];
                teamStat += stat[p2][p1];
            }
        }
        return teamStat;
    }
}
