package 박지원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class swea_12712 {
    static int[][] plusShape = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int[][] multipleShape = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    static int n, m;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            // [input] n, m, 이차원 배열
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int[][] bug = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    bug[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // [solution]
            int answer = 0;
            // i, j는 시작점 -> 모든 좌표에서 + x 모양 해보기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // + 모양과 x 모양 중 최대 값을 answer에 넣기
                    answer = Math.max(answer, simulation(bug, plusShape, i, j));
                    answer = Math.max(answer, simulation(bug, multipleShape, i, j));
                }
            }

            // [output]
            System.out.println("#" + test_case + " " + answer);
        }

    } // main()

    public static int simulation(int[][] map, int[][] shape, int x, int y) {
        int sum = map[x][y]; // i, j를 매개변수 x, y의 인자로 넘김 i, j 좌표를 sum에 넣어줌
        for (int dir = 0; dir < 4; dir++) {
            for (int i = 1; i < m; i++) {
                int nx = x + shape[dir][0] * i;
                int ny = y + shape[dir][1] * i;

                // 배열 범위를 벗어나면 접근x
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                sum += map[nx][ny];
            }
        }
        return sum;
    }
}