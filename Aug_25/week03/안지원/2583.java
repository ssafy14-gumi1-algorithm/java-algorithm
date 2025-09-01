// 영역 구하기

// 눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다.
// 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.


// 첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다.(M, N, K는 모두 100 이하의 자연수이다.)
// 둘째 줄부터 K개의 줄에는 한 줄에 하나씩
// 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과
// 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다.
// 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다.
// 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다. => 빈 공간이 무조건 1개 이상 나온다.


// 왜 사각형 좌표를 저따구로 설정해놨는지 모르겠는데 0, 0이 왼쪽 아래고, M, N이 오른쪽 위라는걸 알아둬야함
// 사각형 모양 이상하게 만들어지는거 보고 뭐지 이거 했는데 문제 자체가 이상해게 만듬 누가 만들었냐

// 2차원 배열에서 y가 반전된 버전이라고 생각하고 그래프를 만들어야함
// 직사각형 그릴때 y를 M - y로 주면 반전 좌표로 구해질거 같음

import java.io.*;
import java.util.*;

public class Main {
    static int M, N, K;
    static int[][] area;
    static boolean[][] visited;
    static int width;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // 입력 버퍼 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 세로
        N = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken()); // 직사각형 수

        // 전체 영역
        area = new int[M][N];
        // 방문 여부
        visited = new boolean[M][N];

        // 전체 영역 1로 초기화
        for (int y = 0; y < M; y++) {
            Arrays.fill(area[y], 1);
        }

        // 영역에 직사각형 넣어주기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // y축의 범위를 반전해서 구하기
            for (int y = M - y2; y < M - y1; y++) {
                for (int x = x1; x < x2; x++) {
                    area[y][x] = 0; // 막힌 영역
                }
            }
        }

        // 빈 영역 담을 result 변수
        List<Integer> result = new ArrayList<>();

        // 현재 지역이 빈 영역이고, 방문한 적 없으면 DFS 실행
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (area[y][x] == 1 && !visited[y][x]) {
                    // 빈 영역의 넓이값 초기화
                    width = 0;
                    // DFS 실행
                    dfs(x, y);
                    // dfs 종료되면 width값 result 배열에 담아주기
                    result.add(width);
                }
            }
        }

        // 이건 gpt 돌렸더니 Collections란 객체에서 sort를 꺼내 쓰네요
        // 이건 뭘까요 허허
        Collections.sort(result);

        // 출력
        // StringBuilder이 배열처럼 append로 값을 추가하고, 출력할땐 join처럼 문자열로 변환되어 출력되는 유틸 클래스인가요?
        // 가변한 문자열 버퍼를 제공해준다고 하네요
        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append('\n');
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i < result.size() - 1) sb.append(' ');
        }
        System.out.println(sb);
    }

    // DFS
    static void dfs(int x, int y) {
        // 방문 체크
        visited[y][x] = true;
        // 전역변수로 설정된 현재 안전구역 width값 늘려주기
        width++;

        // 다음 지역으로 이동(visited 체크용)
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                // 다음 지역이 방문했던 지역이 아니면 dfs 진행
                if (area[ny][nx] == 1 && !visited[ny][nx]) {
                    dfs(nx, ny);
