import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 놀이공원

        // 놀이공원에서 여러 개의 놀이기구를 맡아 일하는 세혁이와 근영이는 서로 좋아하는 사이이다.
        // 그들은 쉬는 시간을 이용하여 둘만의 시간을 가지기를 원한다.
        // 그래서 매일 일과 시작 전에 놀이기구의 운영 일정을 보고, 그날 둘이 함께할 수 있는 가장 긴 휴식시간이 언제인지를 찾으려고 한다.

        // 놀이공원에서 일하는 모든 사람들은 어떤 놀이기구가 작동을 시작하기 10분 전부터,
        // 모든 놀이기구가 작동을 멈춘 후 10분 후까지는 쉴 수 없고, 그 나머지 일과 시간에만 쉴 수 있다.

        // 하루 일과를 시작하는 시각은 오전 10시이고,
        // 일과를 마치는 시각은 오후 10시이다.
        // 예를 들어 세 개의 놀이기구가 작동하는 시간이 다음과 같다고 하면,

        // 놀이기구 1: 오전 10시 30분 - 오후 1시
        // 놀이기구 2: 오후 7시 00분 - 오후 9시 10분
        // 놀이기구 3: 오후 12시 30분 - 오후 4시 50분

        // 세혁이와 근영이는 놀이기구 1이 운행되기 전에 20분,
        // 놀이기구 3의 운행이 끝나고 놀이기구 2의 운행시작 전 사이에 1시간 50분,
        // 놀이기구 2의 운행이 끝난 후에 40분 동안 쉴 수 있다.
        // 따라서 둘이 함께할 수 있는 가장 긴 시간은 1시간 50분이다.

        // 놀이기구 운영 일정이 주어질 때, 그 날 세혁이와 근영이가 함께할 수 있는 가장 긴 시간을 구하는 프로그램을 작성하시오.

        // [입력]
        // 첫째 줄에 놀이기구의 개수 N이 주어진다.
        // 이어 N줄에 걸쳐 각 놀이기구의 운행시작 시각과 종료 시각이 빈 칸을 사이에 두고 주어진다.
        // 시각은 시간단위 두 자리, 분 단위 두 자리로 구성되며 오후 1시는 13시, 오후 2시는 14시, ... , 오후 10시는 22시로 표현된다.
        // N은 50이하의 자연수이다.

        // [출력]
        // 첫째 줄에 세혁이와 근영이가 함께할 수 있는 가장 긴 시간을 분 단위로 출력한다.
        // 만약 함께할 수 있는 시간이 없다면 첫째 줄에 0을 출력한다.

        // [문제풀이]
        // 오전 10시(1000) ~ 오후 10시(2200)까지 운영됨
        // 시작 전 10분, 시작 후 10분도 쉬지 못하는 시간임
        // 그 사이에 쉴 수 있는 가장 긴 시간을 출력하면 됨
        // 싹 다 1000을 빼주고 스타트

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] times = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
        }

        // 일 시작
        int s_time = 0;
        // 일 끝(12시간)
        int e_time = 720;

        // times 전부 분으로 변경해주기
        for (int i = 0; i < N; i++) {
            int s_t = times[i][0];
            int e_t = times[i][1];
            // 시간
            int s_h = s_t / 100 - 10;
            int e_h = e_t / 100 - 10;
            // 분 구해주기
            times[i][0] = Math.max(s_t % 100 + s_h * 60 - 10, 0);
            times[i][1] = Math.min(e_t % 100 + e_h * 60 + 10, 720);
        }

        // 720짜리 1차원 배열 만들어주기
        boolean[] m_list = new boolean[720];
        Arrays.fill(m_list, true);

        // 일하는 시간은 false로 변경
        for (int i = 0; i < N; i++) {
            int s_m = times[i][0];
            int e_m = times[i][1];
            for (int j = s_m; j < e_m; j++) {
                if (j >= 0 && j < 720) {
                    m_list[j] = false;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        int now = 0;

        // m_list 돌려서 최댓값 계속 갱신해주기
        for (boolean time : m_list) {
            // 쉬는시간이면 now에 추가해주기
            if (time) {
                now++;
            }
            // 일하는시간이면 최댓값 검증 + now 초기화
            else if (!time && now > 0) {
                ans.add(now);
                now = 0;
            }
        }
        ans.add(now);

        int maxRest = 0;
        for (int value : ans) {
            if (value > maxRest) maxRest = value;
        }

        System.out.println(maxRest);
    }
}
