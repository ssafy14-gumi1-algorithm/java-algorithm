package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_3584 {

    static int N;
    static int[][] nodes;

    static List<Integer> findParents(int n){
        List<Integer> parents = new ArrayList<>();

        while (n != 0){
            parents.add(n);
            n = nodes[n][2];
        }
        return parents;
    }

    static int findCommon(List<Integer> p1, List<Integer> p2){
        for (int a: p1) {
            if (p2.contains(a)){
                return a;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            nodes = new int[N + 1][3];    // 1번 노드부터 N번 노드까지 사용

            for (int i = 0; i < N-1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());    // 부모 노드
                int c = Integer.parseInt(st.nextToken());

                if (nodes[p][0] == 0) {
                    nodes[p][0] = c;
                } else {
                    nodes[p][1] = c;
                }
                nodes[c][2] = p;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            List<Integer> p1 = findParents(n1);
            List<Integer> p2 = findParents(n2);

            int common = findCommon(p1, p2);
            sb.append(common).append("\n");
        }
        System.out.print(sb);
    }
}
