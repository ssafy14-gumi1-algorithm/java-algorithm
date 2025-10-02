package Sep_25.week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_3584 {

    public static int n;
    public static List<Integer>[] graph;

    public static int[] parent; // parent[i]: i노드의 부모노드
    public static int[] level;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){

            // [input1] 노드 개수 n 입력 받기
            n = Integer.parseInt(br.readLine());

            graph = new ArrayList[n+1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            parent = new int[n+1];
            level = new int[n+1];

            // [input2] 그래프 채우기
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to); // from에서 to로 가는 간선 추가
                parent[to] = from; // to의 부모는 from
            }

            // [input3] 최소 공통 조상을 구할 두 노드
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 루트 모르니까 parent 없는 놈이 루트임
            int root = -1;
            for (int i = 1; i <= n; i++) {
                if(parent[i]==0){
                    root = i;
                    break;
                }
            }

            // level(=depth) 배열 채우기 위한 dfs
            dfs(root, 0);

            // lca 구해서 출력하기
            System.out.println(getLca(a, b));
        }
    }

    public static void dfs(int curr, int depth){

        level[curr] = depth;

        for (Integer next : graph[curr]) {
            dfs(next, depth+1);
        }
    }

    private static int getLca(int a, int b) {
        // level(depth)이 다르면 같게 해줘야 함
        while(level[a]!=level[b]){
            if(level[a]>level[b]){
                a = parent[a];
            }else{
                b = parent[b];
            }
        }

        // a랑 b가 같아질 때까지 올려주기
        while (a!=b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

}
