// 안전영역

// 먼저 어떤 지역의 높이 정보를 파악한다.
// 그 지역에 많은 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사하려고 한다.
// 장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다고 가정한다.

// 어떤 지역의 높이 정보는 행과 열의 크기가 각각 N인 2차원 배열 형태로 주어지며 배열의 각 원소는 해당 지점의 높이를 표시하는 자연수이다.

// 어떤 지역의 높이 정보가 주어졌을 때, 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 계산하는 프로그램을 작성하시오.
// 안전한 영역이 붙어있는 경우에 1개로 취급함(영역이니깐)

// 장마는 아예 잠기는 일이 없는 경우 0과 모든 건물이 잠기는 경우인 높이의 최대값을 기준으로 모든 장마 캐이스를 테스트 해야함
// 시간복잡도는 전혀 문제 없으니 결과 카운트들을 배열 하나에 계속 담고, 그 배열의 최댓값을 출력해주면 될듯

// 문제는 그냥 섬 갯수 구하기 처럼 진행하는데 4방향 델타로 풀면 될듯

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] area;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        area = new int[N][N];
        int maxHeight = 0;

        // 지역 입력 및 최대 높이 추출
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                // 지역 입력
                area[y][x] = Integer.parseInt(st.nextToken());
                // 최대 높이 추출
                if (area[y][x] > maxHeight) {
                    maxHeight = area[y][x];
                }
            }
        }
        // 가장 많은 안전지역 수 = result
        int result = 0;

        // 강수량 0부터 maxHeight - 1까지 테스트
        for (int rainDeep = 0; rainDeep <= maxHeight; rainDeep++) {
            // 방문여부 생성
            visited = new boolean[N][N];
            // 깊이별로 안전구역 갯수 세기
            int safeAreaCount = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    // 호우 깊이보다 현재 지역의 높이가 더 높은 경우 DFS 진행
                    if (area[y][x] > rainDeep && !visited[y][x]) {
                        dfs(x, y, rainDeep);
                        safeAreaCount++;
                    }
                }
            }
            // 현재 안전구역 갯수와 result중 더 큰값을 result값으로 재할당하기
            result = Math.max(result, safeAreaCount);
        }

        System.out.println(result);
    }

    // DFS
    static void dfs(int x, int y, int rainDeep) {
        // 방문 체크
        visited[y][x] = true;

        // 다음 지역 이동(인접한 다른 안전지역 있는지 확인용)
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                // 방문한 지역이 아니면 진행
                if (!visited[ny][nx] && area[ny][nx] > rainDeep) {
                    dfs(nx, ny, rainDeep);
                }
            }
        }
    }
}
