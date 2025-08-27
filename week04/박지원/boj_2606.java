package week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2606 {

    public static int n;
    public static int e;

    public static List<Integer>[] adjList;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        e = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n+1];
        visited = new boolean[n+1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }


        // 1번 컴퓨터를 제외해야 하니까
        System.out.println(bfs(1));
    }

    public static int bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        int cnt = 0;

        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int curr = queue.poll();
            for (int next : adjList[curr]) {
                if(visited[next]) continue;

                queue.offer(next);
                visited[next] = true;
                cnt++;
            }
        }
        return cnt;
    }
}
