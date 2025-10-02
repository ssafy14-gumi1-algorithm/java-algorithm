package project;

import java.util.Scanner;

public class boj_4963 {
    // 지도, 방문체크 배열
    static int[][] grid;
    static int[][] visited;
    static int h, w;

    // 방향 이동 상수 정의 
    static final int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dj = {-1,  0,  1,-1, 1,-1, 0, 1};

    // DFS 탐색
    static void dfs(int r, int c) {
        visited[r][c] = 1; // 방문 처리
        for (int d = 0; d < 8; d++) { // 8방향 탐색
            int nr = r + di[d];
            int nc = c + dj[d];
            // 범위 확인 + 미방문 땅일 경우 재귀
            if (0 <= nr && nr < h && 0 <= nc && nc < w) {
                if (grid[nr][nc] == 1 && visited[nr][nc] == 0) {
                    dfs(nr, nc);
                }
            }
        }
    }

    // main Method 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            w = sc.nextInt(); // 너비 입력
            h = sc.nextInt(); // 높이 입력
            if (w == 0 && h == 0) break; // 종료

            // 지도 입력
            grid = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            visited = new int[h][w]; // 방문 배열 초기화
            int islands = 0;         // 섬 개수 초기화

            // 모든 칸 확인하며 DFS 실행
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == 1 && visited[i][j] == 0) { // 현 재땅, 방문 표시 없으면 섬 
                        dfs(i, j);   // 연결된 땅 방문 처리
                        islands++;   // 섬 개수 증가
                    }
                }
            }

            System.out.println(islands); // 결과 출력
        }

        sc.close();
    }
}
