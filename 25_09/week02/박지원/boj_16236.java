package Sep_25.week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16236 {

    public static class Coord{
        int x, y, time;
        public Coord(int x, int y, int time) {
            this.x = x; this.y = y; this.time = time;
        }
    }

    public static class Shark{
        int x, y, size, eatCount;
        public Shark(int x, int y) {
            this.x = x; this.y = y;
            this.size = 2;     // 초기 크기
            this.eatCount = 0; // 먹은 마리 수
        }
    }

    public static class Fish implements Comparable<Fish>{
        int x, y, size;
        int distFromShark; // 선택 시 BFS 결과 거리로 갱신해 사용
        boolean isEaten;

        public Fish(int x, int y, int size) {
            this.x = x; this.y = y; this.size = size;
            this.distFromShark = Integer.MAX_VALUE;
            this.isEaten = false;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.distFromShark == o.distFromShark) {
                if (this.x == o.x) return this.y - o.y;  // 열(왼쪽) 우선
                return this.x - o.x;                     // 행(위쪽) 우선
            }
            return this.distFromShark - o.distFromShark; // 거리 우선
        }
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int n;
    static int[][] map;

    static List<Fish> fishes = new ArrayList<>();
    static int totalTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        Shark shark = null;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {        // 상어 위치
                    shark = new Shark(i, j);
                    map[i][j] = 0;           // 시작 칸은 빈 칸으로 초기화
                } else if (1 <= map[i][j] && map[i][j] <= 6) {
                    fishes.add(new Fish(i, j, map[i][j])); // 물고기만 수집
                }
            }
        }

        while (true) {
            // 상어 위치 기준 모든 칸 최단거리 계산
            int[][] dist = bfsAll(shark);

            // 목표 물고기 선택하기
            Fish target = null;
            for (Fish fish : fishes) {
                // 이미 먹은 것, 크기가 크거나 같은 물고기는 못 먹음
                if (fish.isEaten) continue;
                if (fish.size >= shark.size) continue;

                int d = dist[fish.x][fish.y];
                if (d == -1) continue;                  // 도달 불가

                fish.distFromShark = d;
                // fish.compareTo(target) 지금 물고기랑 타겟 물고기 비교해서 0보다 작음-> 우선순위 높음
                if (target == null || fish.compareTo(target) < 0) {
                    target = fish;
                }
            }

            // 더 이상 먹을 수 있는 물고기가 없으면 종료
            if (target == null) break;

            // 이동 시간 누적 및 지도/상태 업데이트
            totalTime += target.distFromShark;

            // 상어 이동
            shark.x = target.x;
            shark.y = target.y;

            // 물고기 먹음 처리
            target.isEaten = true;
            map[target.x][target.y] = 0;

            // 성장 처리
            shark.eatCount++;
            if (shark.eatCount == shark.size) {
                shark.size++;
                shark.eatCount = 0;
            }
        }

        System.out.println(totalTime);
    }

    // 상어 위치에서 모든 칸까지의 최단거리(걸음 수)를 계산
    // 상어 크기보다 큰 물고기가 있는 칸은 통과 불가, 크기가 같은 물고기는 통과 가능
    private static int[][] bfsAll(Shark shark) {

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], -1);

        ArrayDeque<Coord> queue = new ArrayDeque<>();
        queue.offer(new Coord(shark.x, shark.y, 0));
        dist[shark.x][shark.y] = 0;

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (dist[nx][ny] != -1) continue;

                // 상어 크기보다 큰 물고기가 있는 칸은 '지나갈 수 없음'
                if (map[nx][ny] > shark.size) continue;

                dist[nx][ny] = curr.time + 1;
                queue.offer(new Coord(nx, ny, curr.time + 1));
            }
        }
        return dist;
    }
}