// 유기농 배추

// 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다.
// 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
// 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다.
// 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.

// [입력]
// 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
// 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의
// 가로길이 M(1 ≤ M ≤ 50),
// 세로길이 N(1 ≤ N ≤ 50),
// 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다.
// 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.

// [출력]
// 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.

// [문제풀이]
// 그냥 BFS 돌리면 끝나겠는데

import java.io.*;
import java.util.*;

public class Main {

    static int M, N, K;
    static int[][] farmArr;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void BFS(int X, int Y) {
        Queue<int[]> q = new LinkedList<>();
        // 시작점 등록
        q.offer(new int[]{X, Y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 방문하지 않은 인접 배추 있으면 거기 체크해주고 지렁이 이동
                if (nx >= 0 && ny >= 0 && nx < M && ny < N && farmArr[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 가로, 세로, 배추가 심어진 위치의 갯수
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 배추밭 만들기
            farmArr = new int[N][M];
            // 지렁이 방문 여부
            visited = new boolean[N][M];

            // 배추 심기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                farmArr[y][x] = 1;
            }

            int ans = 0;
            // 배추밭 BFS 진행
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (!visited[y][x] && farmArr[y][x] == 1) {
                        ans++;
                        visited[y][x] = true;
                        BFS(x, y);
                    }
                }
            }

            System.out.println(ans);
        }
    }
}
