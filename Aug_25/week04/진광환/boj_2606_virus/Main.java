package boj.bfs_dfs.boj_2606_virus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new ArrayList[V+1];
        q = new LinkedList<>();

        boolean[] visited = new boolean[V+1];

        for (int i = 0; i < V+1; i++) graph[i] = new ArrayList<>();

        int s;
        int g;
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            graph[s].add(g);
            graph[g].add(s);

        }

        System.out.println(bfs(graph,visited));
    }

    private static int bfs(List<Integer>[] graph, boolean[] visited){
        int result = 0;
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int v = q.remove();

            for (int nxt : graph[v]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    q.add(nxt);
                }
            }
        }


        for (int i = 0; i < V+1; i++) {
            if (visited[i]) {
                result++;
            }
        }
        return result - 1;
    }
}
