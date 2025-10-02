import java.util.*;

public class Main {
    static int N, H;        // N: 격자 크기, H: 높이
    static int[][] A;         
    static int[][] visited;    

    // 방향 이동 
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    // DFS 탐색: (r,c)에서 시작해 인접한 안전 영역을 모두 방문 처리
    static void dfs(int r, int c, int h) {
        visited[r][c] = 1; // 방문 표시
        for (int k = 0; k < 4; k++) {
            int nr = r + di[k];
            int nc = c + dj[k];
            // 범위 내, 물에 잠기지 않은 땅이면서 아직 방문하지 않았다면 재귀
            if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                if (A[nr][nc] > h && visited[nr][nc] == 0) {
                    dfs(nr, nc, h);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        A = new int[N][N];
        H = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = sc.nextInt();        // 높이 정보 입력
                if (A[i][j] > H) H = A[i][j]; // 최대 높이 갱신
            }
        }

        int answer = 0; // 안전 영역 최대 개수

        // 안전 영역 개수 세기
        for (int h = 0; h <= H; h++) {
            visited = new int[N][N]; // 매 강수량마다 방문 배열 초기화
            int cnt = 0;             // 현재 강수량에서의 안전 영역 개수

            // 전체 격자 순회
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 새로운 안전영역 
                    if (A[i][j] > h && visited[i][j] == 0) {
                        dfs(i, j, h); // DFS로 연결된 영역 전체 방문 처리
                        cnt++;        // 안전 영역 개수 +1
                    }
                }
            }

            // 안전 영역 최대 개수 
            if (cnt > answer) answer = cnt;
        }

        // 결과 출력
        System.out.println(answer);
        sc.close();
    }
}
