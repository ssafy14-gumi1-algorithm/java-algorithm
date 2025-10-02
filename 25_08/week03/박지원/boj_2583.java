package week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2583 {

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int n;
    public static int m;
    public static int k;

    public static int[][] arr;
    public static boolean[][] visited;

    public static List<Integer> areas;
    public static int cnt; // 영역의 갯수
    public static int area;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // [input] 첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다.
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m]; // 원본 배열
        areas = new ArrayList<>(); // 영역 넓이를 넣을 리스트
        visited = new boolean[n][m]; // 방문 체크


        // [input] 둘째 줄부터 K개의 줄
        // 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값
        // 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다.
        // 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.
        for (int K = 0; K < k; K++) {
            st = new StringTokenizer(br.readLine());
            // 왼쪽 아래 꼭지점 x, y 좌표값
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            // 오른쪽 위 꼭지점 x, y좌표값
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    arr[i][j] = 1;
                }
            }
        }

        // [solution] dfs로 0인 구역 하나씩 돌면서 영역 갯수 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || arr[i][j] == 1) continue;

                visited[i][j] = true; // 방문 체크
                cnt++; // 영역 1개 추가

                area = 1; // 영역 크기 1로 초기화
                dfs(i, j); // 영역 전부 돌기

                areas.add(area);
            }
        }

        // [output] 첫째 줄에 분리되어 나누어지는 영역의 개수를 출력
        System.out.println(cnt);
        // [output] 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력
        areas.sort((e1, e2) -> e1 - e2);
        for (Integer e : areas) {
            System.out.print(e + " ");
        }
    }

    private static void dfs(int x, int y) {
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 배열 범위 밖이면 넘어가
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            // 방문 한 칸이거나, 1로 표시한 구역은 영역이 아니니까 체크하지마
            if (visited[nx][ny] || arr[nx][ny] == 1) continue;

            area++; // 영역 크기 1 늘리기
            visited[nx][ny] = true;
            dfs(nx, ny);
        }
    }
}
