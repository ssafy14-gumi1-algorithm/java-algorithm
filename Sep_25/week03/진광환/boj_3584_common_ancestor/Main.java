package boj.boj_3584_common_ancestor;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, node1, node2;
    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T+1; tc++){
            N = Integer.parseInt(br.readLine());

            parents = new int[N+1];
            for (int i = 0; i < N-1; i++){
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                parents[child] = parent;
            }
            st = new StringTokenizer(br.readLine());

            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            Integer s = solve(node1, node2);
            System.out.println(s);
        }
    }
    static Integer solve(int node1, int node2){

        List<Integer> ancestors1 = new ArrayList<>();
        List<Integer> ancestors2 = new ArrayList<>();
        int idx1 = node1;
        int idx2 = node2;
        while (idx1 != 0){
            ancestors1.add(idx1);
            idx1 = parents[idx1];
        }

        while (idx2 != 0){
            ancestors2.add(idx2);
            idx2 = parents[idx2];
        }

        for (Integer value : ancestors1) {
            for (Integer integer : ancestors2) {
                if (Objects.equals(value, integer)) {
                    return value;
                }
            }
        }

        return 0;
    }
}
