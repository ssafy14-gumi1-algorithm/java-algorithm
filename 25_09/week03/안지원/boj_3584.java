// 가장 가까운 공통조상

// 루트가 있는 트리(rooted tree)가 주어지고,
// 그 트리 상의 두 정점이 주어질 때 그들의 가장 가까운 공통 조상(Nearest Common Ancestor)은 다음과 같이 정의됩니다.

// 두 노드의 가장 가까운 공통 조상은,
// 두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은(즉 두 노드에 가장 가까운) 노드를 말합니다.

// 예를 들어 15와 11를 모두 자손으로 갖는 노드는 4와 8이 있지만,
// 그 중 깊이가 가장 깊은(15와 11에 가장 가까운) 노드는 4 이므로 가장 가까운 공통 조상은 4가 됩니다.

// 루트가 있는 트리가 주어지고, 두 노드가 주어질 때 그 두 노드의 가장 가까운 공통 조상을 찾는 프로그램을 작성하세요

// [입력]
// 첫 줄에 테스트 케이스의 개수 T가 주어집니다.

// 각 테스트 케이스마다, 첫째 줄에 트리를 구성하는 노드의 수 N이 주어집니다. (2 ≤ N ≤ 10,000)

// 그리고 그 다음 N-1개의 줄에 트리를 구성하는 간선 정보가 주어집니다.
// 한 간선 당 한 줄에 두 개의 숫자 A B 가 순서대로 주어지는데,
// 이는 A가 B의 부모라는 뜻입니다.
// (당연히 정점이 N개인 트리는 항상 N-1개의 간선으로 이루어집니다!)
// A와 B는 1 이상 N 이하의 정수로 이름 붙여집니다.

// 테스트 케이스의 마지막 줄에 가장 가까운 공통 조상을 구할 두 노드가 주어집니다.

// [출력]
// 각 테스트 케이스 별로, 첫 줄에 입력에서 주어진 두 노드의 가장 가까운 공통 조상을 출력합니다.

// [문제풀이]
// 그림이 완전 이진트리가 아니네
// 시작점, 끝점, 부모 1차원 배열들 만들어서 트리를 구현해줘야겠네
// 트리 끝 점 부터 부모가 0 나올때 까지 쭉 올라가면서 거친 부모 노드들 전부 기록한 배열 반환하는 재귀 하나 만들까
// 두 정점들의 부모 전부 구해서 이중 for문 돌리고 가장 먼저 동일한 부모를 찾으면 그게 가장 가까운 공통 조상이겠넴

import java.io.*;
import java.util.*;

class boj_3584 {
    static int T;
    static int N;
    static int[][] tree;
    static int n1;
    static int n2;
    static BufferedReader br;
    static StringTokenizer st;

    // 공통 조상 구하기(그냥 while문으로)
    static void get_ancestor(int parent, ArrayList<Integer> ancestors){
        int now_parent = parent;
        while(true){
            // 조상 끝까지 이동했다면 종료(기저조건)
            if(tree[now_parent][2] == 0){
                break;
            }
            // 조상 담기
            ancestors.add(tree[now_parent][2]);
            // 다음 부모로 이동
            now_parent = tree[now_parent][1];
        }
    }

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        // 습관이 되어부렀어
        for(int tc = 1; tc < T+1; tc++){
            N = Integer.parseInt(br.readLine());
            tree = new int[N+1][3];
            // 트리 만들기
            for(int i = 0; i < N+1; i++){
                int[] add_arr = {0, 0, 0};
                tree[i] = add_arr;
            }
            // 트리 채우기
            for(int i = 0; i < N-1; i++){
                st = new StringTokenizer(br.readLine());
                // 정점 정보
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                // 정점 연결하기
                tree[s][0] = e;
                tree[e][1] = s;
                // 부모 설정
                tree[e][2] = s;
            }

            st = new StringTokenizer(br.readLine());
            // 공통조상 구할 노드들 빼기
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            // 조상들 구하기(최초 노드, 노드 조상 담아두기)
            ArrayList<Integer> n1_ancestors = new ArrayList<>();
            n1_ancestors.add(n1);
            n1_ancestors.add(tree[n1][2]);
            ArrayList<Integer> n2_ancestors = new ArrayList<>();
            n2_ancestors.add(n2);
            n2_ancestors.add(tree[n2][2]);
            // n1 공통조상 구하기
            get_ancestor(tree[n1][1], n1_ancestors);
            // n2 공통조상 구하기
            get_ancestor(tree[n2][1], n2_ancestors);

            for(int ancestor1 : n1_ancestors){
                boolean flag = false;
                for(int ancestor2 : n2_ancestors){
                    if(ancestor1 == ancestor2){
                        flag = true;
                        System.out.println(ancestor1);
                        break;
                    }
                }
                if(flag){
                    break;
                }
            }
        }
    }
}