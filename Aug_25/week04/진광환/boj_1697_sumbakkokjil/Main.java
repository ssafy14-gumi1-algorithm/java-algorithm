package boj.boj_1697_sumbakkokjil;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*TODO:
- 상수

- 변수
    N : 수빈이의 현재 위치
    K : 동생의 현재 위치
    둘다 0 이상 십만 이하
- 조건
    수빈이는 걷거나 순간이동을 할 수 있다
- 구하는 값

- 아이디어
    걷기 => 인덱스 1이동 또는 -1이동
    순간이동 => 인덱스의 2배 위치

    한 위치에서 3가지 서로 다른 위치로 갈 수 있는 그래프 만들기
    숫자는 100000일때까지?

*/
public class Main {

    static final int N = 100000;
    static List<Integer>[] graph = new ArrayList[N+1];

    static int bfs(int start, int goal){
        int[] visited = new int[N+1];
        Arrays.fill(visited, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = 0;

        while (!q.isEmpty()) {
            int v = q.remove();

            for (int nxt : graph[v]){
                if (visited[nxt] ==-1){
                    if (nxt == goal){
                        return visited[v] + 1;
                    }
                    visited[nxt] = visited[v] + 1;
                    q.add(nxt);
                }
            }
        }
        return 0;
    }

    static void addElementOnCondition(int value, int idx){
        if (0<=value && value<=N && idx != value){
            graph[idx].add(value);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());


        for (int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N+1; i++){
            int ni1 = i+1;
            int ni2 = i-1;
            int ni3 = 2*i;
            addElementOnCondition(ni1, i);
            addElementOnCondition(ni2, i);
            addElementOnCondition(ni3, i);
        }

        System.out.println(bfs(s,g));
    }
}


