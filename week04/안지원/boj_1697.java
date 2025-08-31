import java.io.*;
import java.util.*;

class boj_1697 {
    // 최대 제한 상수 생성
    static final int MAX = 100000;
    // 방문 배열 만들기
    static boolean[] visited = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        System.out.println(bfs(N, K));
    }

    static int bfs(int start, int target) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 현재 위치
            int pos = cur[0];
            // 걸린 시간
            int time = cur[1];

            // 동생 위치 도착 → 최단 시간 반환
            if (pos == target) {
                return time;
            }

            // 다음 이동 후보들
            int[] nextPos = {pos - 1, pos + 1, pos * 2};
            for (int np : nextPos) {
                // 좌표를 벗어나지 않고, 다음 지역이 방문 안한 지역이면 이동
                if (np >= 0 && np <= MAX && !visited[np]) {
                    // 방문 지역 체크
                    visited[np] = true;
                    queue.add(new int[]{np, time + 1});
                }
            }
        }
        return -1; // 도달 불가한 경우 (문제 조건상 발생하지 않음)
    }
}
