# Intro
Java 알고리즘 스터디\
4주차(08.25~08.31) bfs 문제\
08.31 일요일 오후(시간 미정)에 webex로 서로의 문제풀이 공유(필수 3문제에 대해서만 진행)

week04 하위에 본인 이름 폴더 생성 후, 문제 풀이.java 및 문제 풀이.md 파일 업로드
```
폴더 구조
week04/
├── 박지원/
│   ├── boj_1234.java
│   ├── boj_1234.md
│   ├── swea_1234.java
│   └── swea_1234.md
```

# 문제 목록
## 기초 문제
[boj_1260 DFS와 BFS](https://www.acmicpc.net/problem/1260)

## 필수 문제
2일 1문제 필수

[boj_2606 바이러스](https://www.acmicpc.net/problem/2606)

[boj_1697 숨바꼭질](https://www.acmicpc.net/problem/1697)

[boj_11559 Puyo Puyo](https://www.acmicpc.net/problem/11559)

## 추가 문제
[boj_9019 DSLR](https://www.acmicpc.net/problem/9019)

# 알고리즘 설명
## 그래프와 인접리스트
[week03 README.md](../week03/README.md)의 설명을 참고해주세요!

## 그래프 탐색 기법: bfs
너비 우선 탐색

시작점에서 가까운 정점부터 순서대로 방문하는 알고리즘\
bfs는 주로 최단거리를 구해야 하는 문제에서 주로 사용됨.\
dfs는 최초의 경로가 최단 경로임이 보장되지 않지만, bfs는 최초의 경로가 최단경로임이 보장되기 때문\
나는 최단거리나 return이 있으면 편할 거 같은 경우(영역 갯수 세기 등), 동시에 여러 곳에서 시작해야 하는 경우(ex. 백준 토마토 문제) bfs를 사용하는 편!
![깊이우선탐색과 너비 우선탐색](https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSU1bbOZLAHdOcYrOZo3zzge72u-jogTWthUrhA8cFsIGpCods72Ar1Gvv4I3ZgsTj5YVh3Hg)
단, 가중치가 없는 그래프에서만 최단거리 보장. 가중치가 있을 경우 다익스트라 이용.

## bfs 기초코드
Queue 구현은 ArrayDeque 권장 (LinkedList 대비 성능 유리)\
queue에 넣을 때는 offer() 함수를 쓰는 게 빠름(add 쓰지 말 것)\
queue.offer할 때, 방문처리(방문 예약, `visited[x]= true`)도 같이 해줄 것. 그래야 queue에 중복으로 들어가지 않음.

최단 거리의 경우 dist배열을 사용할 수도 있고 `dist[nx][ny]=dist[x][y]+1`, class를 만들어서 객체를 Queue에 넣는 방법이 있음.

```java
// BFS 기본 구조 (큐 활용)
void bfs(int start) {
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] visited = new boolean[N + 1];
    int[] dist = new int[N + 1]; // 최단거리 저장용(필요 없으면 생략 가능)

    visited[start] = true;
    dist[start] = 0;
    q.add(start);

    while (!q.isEmpty()) {
        int cur = q.poll();
        System.out.print(cur + " "); // 현재 노드 처리

        // cur와 연결된 정점들을 순회
        for (int nxt : graph[cur]) {
            if (visited[nxt]) continue;
            visited[nxt] = true;
            dist[nxt] = dist[cur] + 1;
            q.add(nxt);
        }
    }
}
```
### 인접 행렬
```java

public class BFS_Matrix {
    static int N;                // 정점 개수
    static int[][] graph;        // 인접 행렬
    static boolean[] visited;    // 방문 여부
    static int[] dist;           // 최단 거리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, -1);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
//            graph[b][a] = 1; // 양방향이라면 반대도
        }

        bfs(1); // 1번 노드부터 탐색
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        dist[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.print(cur + " ");

            for (int i = 1; i <= N; i++) {
                if (graph[cur][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    dist[i] = dist[cur] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
```
### 인접 리스트
```java
public class BFS_List {
    static int N;                     // 정점 개수
    static List<Integer>[] graph;     // 인접 리스트
    static boolean[] visited;         // 방문 여부
    static int[] dist;                // 최단 거리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, -1);

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
//            graph[b].add(a); // 양방향이라면 반대도
        }

        bfs(1); // 1번 노드부터 탐색
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        dist[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.print(cur + " ");

            for (int nxt : graph[cur]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    dist[nxt] = dist[cur] + 1;
                    queue.add(nxt);
                }
            }
        }
    }
}

```

### dist배열 대신 class를 만들어서 해결하는 방법
```java
public class BFS_Class {
    static class Coord {
        int x, y, depth;
        Coord(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth; // 현재까지의 거리/레벨
        }
    }

    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void bfs(int sx, int sy) {
        Queue<Coord> queue = new ArrayDeque<>();
        visited[sx][sy] = true;
        queue.add(new Coord(sx, sy, 0));

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();
            System.out.println("(" + curr.x + "," + curr.y + ") depth=" + curr.depth);

            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (grid[nx][ny] == 0) continue; // 예: 벽이면 통과 불가

                visited[nx][ny] = true;
                queue.add(new Coord(nx, ny, curr.depth + 1));
            }
        }
    }
}

```