// 섬의 갯수

// 정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다.
// 섬의 개수를 세는 프로그램을 작성하시오.

// 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다.
// w와 h는 50보다 작거나 같은 양의 정수이다.
// 둘째 줄부터 h개 줄에는 지도가 주어진다
// 1은 땅, 0은 바다이다.

// [내 생각]
// 대각선끼리도 연결되어있으면 같은섬으로 취급
// 8방향 델타를 사용해서 탐색해야함
// visited는 하나만 만들어서 공유하도록 설정
// 함수가 종룔되었을때 무조건 1을 반환하는데, 연결되어있는 모든 섬의 visited를 채워주는게 관건

import java.io.*;
import java.util.*;

public class Main {
    static int w, h;
    static int[][] land;
    static boolean[][] visited;
    static final int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    static final int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 무슨 문제가 0, 0이 나올때 까지 input을 받는 제한이 있는거야
        // 누가 만들었어
        while (true) {
            // 첫 줄 읽기
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 종료 조건 (0, 0)
            if (w == 0 && h == 0) break;

            // 땅 정보 입력
            land = new int[h][w];
            // 방문 여부
            visited = new boolean[h][w];
            // 땅 정보 채워주기
            for (int y = 0; y < h; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < w; x++) {
                    land[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            // DFS를 통한 섬 개수 세기
            // 최종 출력할 섬 갯수
            int islandCount = 0;
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    // 방문하지 않은 섬이면 DFS 실행
                    if (land[y][x] == 1 && !visited[y][x]) {
                        dfs(x, y);
                        // 섬이 확인돼었으니 DFS 종료 후 섬 갯수 추가
                        islandCount++;
                    }
                }
            }

            // 결과 출력
            System.out.println(islandCount);
        }
    }

    // DFS
    static void dfs(int x, int y) {
        // 방문 체크
        visited[y][x] = true;

        // 8방향 델타로 이동하기(대각선도 인접으로 취급해서)
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < w && ny < h) {
                // 방문한 적 없는 땅인 경우 이동
                if (land[ny][nx] == 1 && !visited[ny][nx]) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
