import java.io.*;
import java.util.*;

class boj_2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());   // 컴퓨터 수 (1..N)
        int M = Integer.parseInt(br.readLine());   // 연결 쌍 수

        // 제네릭 배열 대신 List<List<Integer>> 사용 → 경고 없음
        List<List<Integer>> adj = new ArrayList<>(N);
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        // 값 담기
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        // 방문 배열 생성
        boolean[] visited = new boolean[N];
        // bfs 돌려서 감염된 컴퓨터값 담기(결과값(
        int infected = bfsCount(adj, visited, 0);

        // 1번 컴퓨터(시작점) 제외하고 결과 출력(max로 -1이 나올시 0 출력)
        System.out.println(Math.max(0, infected - 1));
    }

    private static int bfsCount(List<List<Integer>> adj, boolean[] visited, int start) {
        // 큐 생성
        Queue<Integer> queue = new ArrayDeque<>();
        // 큐 초기화
        queue.add(start);
        // 현재 위치 체크
        visited[start] = true;

        // 바이러스 전파된 컴퓨터 카운트(결과값(
        int count = 0;
        while (!queue.isEmpty()) {
            // 현재값 뽑기
            int cur = queue.poll();
            // 바이러스 전파된 컴퓨터 추가
            count++;

            // 연결 리스트의 다음 연결지 반복문 실행
            for (int next : adj.get(cur)) {
                // 방문하지 않은 지역이면 체크 후 이동
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        // 감염된 컴퓨터 출력
        return count;
    }
}