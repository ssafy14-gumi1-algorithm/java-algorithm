// 백준 2573 빙산

// 빙산을 2차원 배열에 표시한다
// 빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다.
// 빙산 이외의 바다에 해당되는 칸에는 0이 저장된다

// 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
// 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
// 바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다.

// 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오

// [입력]
// 첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다
// N과 M은 3 이상 300 이하이다.

// 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다.
// 각 칸에 들어가는 값은 0 이상 10 이하이다.

// 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다.

// [풀이 과정]
// 최대 300 * 300 필드가 만들어짐 = 9000 => DFS 불가능
// BFS로 접근하자
// 로직을 2가지로 짜야할듯
// 1번째 : 섬 분리되었는지 구하기
// 2번째 : 섬 녹이기
// 섬이 2개로 분리될 때 까지 계속해서 반복하고, 분리되었을 경우에 분리된 년을 출력

import java.io.*;
import java.util.*;

public class boj_2573 {

    // 입력 크기
    static int N, M;

    // 필드와 방문 배열
    static int[][] field;
    static boolean[][] visited;

    // 4방향
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 섬 개수 세기 BFS (시작 좌표를 받아 1 반환)
    static int island_cnt(int sx, int sy) {
        List<int[]> queue = new ArrayList<>();
        queue.add(new int[]{sx, sy});

        while (!queue.isEmpty()) {
            int[] cur = queue.remove(0); // 비효율 유지
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < M && ny < N && field[ny][nx] != 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return 1;
    }

    // 섬 녹이기
    static void melt_island() {
        // 녹는 지역 표시
        List<int[]> melting_area = new ArrayList<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                // 현재 지역이 섬이면
                if (field[y][x] != 0) {
                    int sea = 0;
                    // 4면 검사해서 바다 카운트
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        // 파이썬과 달리 자바는 인덱스 음수/초과가 예외이므로 경계 체크만 추가
                        if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                            if (field[ny][nx] == 0) sea++;
                        }
                    }
                    if (sea > 0) {
                        melting_area.add(new int[]{x, y, sea});
                    }
                }
            }
        }

        // 섬 녹이기
        for (int[] t : melting_area) {
            int x = t[0], y = t[1], sea = t[2];
            int next = field[y][x] - sea;
            field[y][x] = (next < 0) ? 0 : next;
        }
    }
    // 실행하기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                field[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;

        // 섬 2개 쪼개질때 까지 무한반복
        while (true) {
            visited = new boolean[N][M];

            // 섬 갯수 세기
            int i_cnt = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (field[y][x] != 0 && !visited[y][x]) {
                        visited[y][x] = true;
                        i_cnt += island_cnt(x, y);
                    }
                }
            }

            if (i_cnt == 0) {
                year = 0;
                break;
            } else if (i_cnt > 1) {
                break;
            }

            // 1년 추가
            year += 1;

            // 섬 녹이기
            melt_island();
        }

        System.out.println(year);
    }
}

