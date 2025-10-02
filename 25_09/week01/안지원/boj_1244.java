// 스위치 끄고 켜기

// 스위치는 1, 0으로 이루어져 있음
// 남학생, 여학생에게 각각 1 ~ N의 숫자를 줌
// 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
// 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 바꾼다.
// 이때 구간에 속한 스위치 개수는 항상 홀수가 된다.

// 입력으로 스위치들의 처음 상태가 주어지고, 각 학생의 성별과 받은 수가 주어진다.
// 학생들은 입력되는 순서대로 자기의 성별과 받은 수에 따라 스위치의 상태를 바꾸었을 때, 스위치들의 마지막 상태를 출력하는 프로그램을 작성하시오.

// [입력]
// 첫째 줄에는 스위치 개수가 주어진다.(100 이하인 양의 정수)
// 둘째 줄에는 각 스위치의 상태가 주어진다.
// 셋째 줄에는 학생수가 주어진다.(100 이하인 양의 정수)
// 넷째 줄부터 마지막 줄까지 한 줄에 한 학생의 성별, 학생이 받은 수가 주어진다.
// 남학생은 1로, 여학생은 2로 표시하고, 학생이 받은 수는 스위치 개수 이하인 양의 정수이다

// [출력]
// 스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력한다.
// 예를 들어 21번 스위치가 있다면 이 스위치의 상태는 둘째 줄 맨 앞에 출력한다.
// 켜진 스위치는 1, 꺼진 스위치는 0으로 표시하고, 스위치 상태 사이에 빈칸을 하나씩 둔다.

// [문제 풀이]
// 남자(1)는 현재 숫자의 배수 인덱스들의 값을 전부 교체해주기
// 여자(2)는 현재 숫자의 인덱스에서 while문 실행해서 양 옆에 값 비교 후, 같으면 값을 바꿔주고 다음으로 이동하면서 서로 다른 수가 나올때 까지 반복하기
// 결과는 20개씩 끊어서 한 줄씩 출력하기

import java.io.*;
import java.util.*;

public class boj_1244 {

    // 스위치 개수
    static int N;

    // 스위치 상태 배열 (0/1)
    static int[] switching;

    // 남자 스위치 전환
    static void maleSwitch(int start) {
        List<Integer> queue = new ArrayList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int now = queue.remove(0);
            switching[now - 1] = Math.abs(switching[now - 1] - 1);
            if (now + start <= N) {
                queue.add(now + start);
            }
        }
    }

    // 여자 스위치 전환 (좌우 확장)
    static void femaleSwitch(int left, int right) {
        List<int[]> queue = new ArrayList<>();
        queue.add(new int[]{left, right});

        while (!queue.isEmpty()) {
            int[] cur = queue.remove(0);
            int l = cur[0];
            int r = cur[1];

            if (l - 1 >= 0 && r - 1 < N && switching[l - 1] == switching[r - 1]) {
                switching[l - 1] = Math.abs(switching[l - 1] - 1);
                switching[r - 1] = Math.abs(switching[r - 1] - 1);
                queue.add(new int[]{l - 1, r + 1});
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        switching = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) switching[i] = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                maleSwitch(idx);
            } else if (gender == 2) {
                // 시작점 변환
                switching[idx - 1] = Math.abs(switching[idx - 1] - 1);
                // 좌우 확장
                femaleSwitch(idx - 1, idx + 1);
            }
        }

        // 결과 출력: 10개씩 끊어서 줄바꿈
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(switching[i]);
            if (i == N - 1 || i % 10 == 9) sb.append('\n');
            else sb.append(' ');
        }
        System.out.print(sb.toString());
    }
}

