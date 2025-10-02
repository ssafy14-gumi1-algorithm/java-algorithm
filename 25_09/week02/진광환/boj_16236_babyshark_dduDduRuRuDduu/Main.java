package boj.boj_16236_babyshark_dduDduRuRuDduu;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*TODO:
- 상수
    2 : 아기상어의 초기 크기
- 변수
    N : 공간의 크기
    M : 물고기의 마리 수
- 조건
    1칸에는 물고기가 최대 1마리 존재
    물고기 크기는 자연수
    아기상어는 자기보다 큰 물고기가 있는 칸은 지나갈 수 없다.
    자기보다 작거나 같은 칸은 지나갈 수 있다.

    하지만 자신의 크기보다 작은 물고기만 먹을 수 있다.
    먹을 수 있는 물고기가 1마리보다 많다면 거리가 가장 가까운 물고기를 먹으러 간다.

    이동과 동시에 물고기 먹으며 그 칸은 빈칸이 된다.
    자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.

    근데 그러면 물고기가 없는 칸은 못지나감? ㄴㄴ 지나감
    더이상 먹을 수 있는 물고기가 없다 -> 물고기를 다 먹었거나 4방향 범위 안에 있는 물고기가 모두 나보다 크거나 같다.


- 구하는 값

- 아이디어
    2차원 탐색문제
    거리의 최솟값을 구해야 하므로 bfs이용하기
    엄마 상어에게 도움을 요청한다? -> 재귀, 혹은 bfs탐색이 끝난다.
    정해진 규칙대로 이동한다.
    빈칸은 -1로 바꾸기
    bfs로 가장 최소의 물고기 > 좌표가 가장 왼쪽인 물고기 선택하기
    이동한 만큼 시간 더해주기



- 시간복잡도
    bfs는 N^2이다 N*N배열을 도니깐 N*3일듯?
*/
public class Main {


    static int N, sharkWidth, sharkStomach;
    static int[][] arr;
    static int[] start;

    static final int[] di = {0,1,0,-1};
    static final int[] dj = {1,0,-1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 필요한 스태틱 상수 설정
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        sharkWidth = 2;

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                int val = Integer.parseInt(st.nextToken());
                if( val == 9) start = new int[] {i, j};
                arr[i][j] = val;
            }
        }

        List<int[]> candi = bfs(start);

        for (int i = 0; i < candi.size(); i++){
            System.out.println(Arrays.toString(candi.get(i)));
        }
//        System.out.println(Arrays.deepToString());

    }
    // bfs로 상어가 이동한 좌표를 구하기
    static List<int[]> bfs(int[] startCoord){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(startCoord);
        List<int[]> preyCandidates = new ArrayList<>();

        int[][] visited = new int[N][N];

        for (int i = 0; i < N; i++){
            Arrays.fill(visited[i] , -1);
        }
        visited[startCoord[0]][startCoord[1]] = 0;


        while (!q.isEmpty()) {
            // 일반적인 bfs코드 짜기
            int[] v = q.poll();

            for (int dir = 0; dir < 4; dir++){
                int ni = v[0] + di[dir];
                int nj = v[1] + dj[dir];
                if (0<=ni && ni<N && 0<= nj && nj <N && visited[ni][nj] == -1){
                    int fishWidth = arr[ni][nj];
                    // 2. 나보다 작거나 같은 좌표만 가기
                    if (fishWidth <= sharkWidth){  // 자기랑 똑같으면 그냥 지나가기
                        visited[ni][nj] = visited[v[0]][v[1]] + 1;
                        q.add(new int[] {ni, nj});
                    }

                    // 3. 먹이 후보군 설정
                    if (0< fishWidth && fishWidth < sharkWidth) {
                        preyCandidates.add(new int[] {visited[ni][nj], ni, nj});
                    }
                }
            }
        }

        // 4. 그 중에서 가장 왼쪽 위의 먹이 결정하기
        // [거리, i좌표, j좌표로 이루어진 배]
        int min_dist = preyCandidates.get(0)[0];

        for (int i = 0; i < preyCandidates.size(); i++){
            if (preyCandidates.get(i)[0] == min_dist){
                int min_idx = 0;

            }
        }
        return preyCandidates;
    }

//    static void eat(){
//        if (sharkStomach == )
//    }
}


