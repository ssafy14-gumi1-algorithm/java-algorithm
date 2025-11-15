package AlgorithmStudy.Nov.week03.오예지;

import java.io.*;
import java.util.*;

public class boj_26146 {
    static int N, M;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> reverse = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList.get(v).add(w);
            reverse.get(w).add(v);

        }

        // 정방향 bfs
        visited = new boolean[N+1];
        bfs(1, adjList);
        for (int i=1; i<=N; i++){
            if (!visited[i]){
                System.out.println("No");
                return;
            }
        }

        // 역방향 bfs
        visited = new boolean[N+1];
        bfs(1, reverse);
        for (int i=1; i<=N; i++){
            if (!visited[i]){
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }


    public static void bfs(int start, ArrayList<ArrayList<Integer>> g){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()){
            int cur = q.poll();
            for (int next : g.get(cur)){
                if (!visited[next]){
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
}
