// 구간 합 구기

// N×N개의 수가 N×N 크기의 표에 채워져 있다.
// (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오.
// (x, y)는 x행 y열을 의미한다.

// [입력]
// 첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000)
// 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다.
// 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다.
// 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)

// [출력]
// 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.

// [문제 풀이]
// 시간복잡도만 안 걸리면 x1, y1은 인덱스 기준으로 -1 해주고 x2, y2는 range 특성상 그대로 집어넣어서 2중 for문으로 풀면 되겠는데
// 시간초과 뜨네
// 1행에 대해 누적합을 구한 행열을 하나 만들면 2중 for문까지 안 가고 풀 수 있음
// 이거 한번 해보자
// 이것도 시간초과 뜨네
// 열도 누적합 구해서 한번에 찍는게 가능한가??
// 일단 만들어보자
// 문제조건을 더럽게 냈네
// x가 행이고 y가 열이라고 해서 기존에 행열그래프 [y][x]가 아니라 [x][y]형태로 풀어야 함
// 문제 조건 같은건 제발 이런식으로 안 냈으면 좋겠다 진짜...

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] numArr = new int[N][N];
        int[][] dirArr = new int[M][4];

        // 표 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                numArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 질의 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                dirArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행 방향 누적합
        for (int y = 0; y < N; y++) {
            for (int x = 1; x < N; x++) {
                numArr[y][x] = numArr[y][x] + numArr[y][x - 1];
            }
        }

        // 열 방향 누적합
        for (int x = 0; x < N; x++) {
            for (int y = 1; y < N; y++) {
                numArr[y][x] = numArr[y][x] + numArr[y - 1][x];
            }
        }

        StringBuilder sb = new StringBuilder();

        // 질의 처리
        for (int i = 0; i < M; i++) {
            int y1 = dirArr[i][0];
            int x1 = dirArr[i][1];
            int y2 = dirArr[i][2];
            int x2 = dirArr[i][3];

            x1 -= 1;
            y1 -= 1;
            x2 -= 1;
            y2 -= 1;

            int now = numArr[y2][x2];
            int result;

            if (y1 == 0 && x1 == 0) {
                result = now;
            } else if (y1 == 0) {
                result = now - numArr[y2][x1 - 1];
            } else if (x1 == 0) {
                result = now - numArr[y1 - 1][x2];
            } else {
                result = now + numArr[y1 - 1][x1 - 1] - numArr[y2][x1 - 1] - numArr[y1 - 1][x2];
            }

            sb.append(result).append('\n');
        }

        System.out.print(sb.toString());
    }
}
