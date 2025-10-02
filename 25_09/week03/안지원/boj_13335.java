// 트럭

// 강을 가로지르는 하나의 차선으로 된 다리가 하나 있다.
// 이 다리를 n 개의 트럭이 건너가려고 한다.
// 트럭의 순서는 바꿀 수 없으며, 트럭의 무게는 서로 같지 않을 수 있다.
// 다리 위에는 단지 [w 대의 트럭만 동시에 올라갈 수 있다.]
// 다리의 길이는 w 단위길이(unit distance)이며,
// 각 트럭들은 하나의 단위시간(unit time)에 하나의 단위길이만큼만 이동할 수 있다고 가정한다.
// [동시에 다리 위에 올라가 있는 트럭들의 무게의 합은 다리의 최대하중인 L보다 작거나 같아야 한다. ]
// 참고로, 다리 위에 완전히 올라가지 못한 트럭의 무게는 다리 위의 트럭들의 무게의 합을 계산할 때 포함하지 않는다고 가정한다.

// 다리의 길이와 다리의 최대하중, 그리고 다리를 건너려는 트럭들의 무게가 순서대로 주어졌을 때,
// 모든 트럭이 다리를 건너는 최단시간을 구하는 프로그램을 작성하라.

// [입력]
// 입력 데이터는 표준입력을 사용한다. 입력은 두 줄로 이루어진다.
// 입력의 첫 번째 줄에는 세 개의 정수 n (1 ≤ n ≤ 1,000) , w (1 ≤ w ≤ 100) and L (10 ≤ L ≤ 1,000)이 주어지는데,
// n은 다리를 건너는 트럭의 수, w는 다리의 길이, 그리고 L은 다리의 최대하중을 나타낸다.
// 입력의 두 번째 줄에는 n개의 정수 a1, a2, ⋯ , an (1 ≤ ai ≤ 10)가 주어지는데, ai는 i번째 트럭의 무게를 나타낸다.

// [출력]
// 출력은 표준출력을 사용한다. 모든 트럭들이 다리를 건너는 최단시간을 출력하라.

// [문제풀이]
// 그리디로 못 푼대...
// BFS 써야한대

import java.io.*;
import java.util.*;

class boj_13335 {
    static int n;
    static int w;
    static int L;

    // 현재 다리에 있는 트럭 총 무게
    static int now_weight;
    // 시간(결과값)
    static int time;
    // br
    static BufferedReader br;
    // st
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        // 트럭의 수: n, 다리의 길이: w, 다리의 최대하중: L
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        // 트럭 담을 deque
        Deque<Integer> trucks = new ArrayDeque<>();
        // 트럭 무게
        for(int i = 0; i < n; i++){
            int truck = Integer.parseInt(st.nextToken());
            trucks.addLast(truck);
        }

        // 현재 다리에 있는 트럭들
        Deque<Integer> bridge = new ArrayDeque<>(); // 길이 w 유지
        for (int i = 0; i < w; i++) bridge.addLast(0);

        // 현재 다리에 있는 트럭 총 무게
        now_weight = 0;

        // 총 시간(결과)
        time = 0;

        // 모든 트럭이 도로를 빠져나갈때 까지 반복
        while(bridge.size() > 0){
            time++;
            // 맨 앞에 빼주기
            int out_weight = bridge.pollFirst();
            // 무게에서 빼주기(트럭이면 무게가 빠져나갈거임)
            now_weight -= out_weight;

            // 남아있는 트럭이 있으면
            if(trucks.size() > 0){
                // 현재 다리에 있는 트럭들이 L을 초과하지 않으면 트럭 추가
                if(now_weight + trucks.peekFirst() <= L){
                    // 다음으로 출발할 트럭 빼기
                    int append_truck = trucks.pollFirst();
                    // 트럭 담아주기
                    bridge.addLast(append_truck);
                    // 다리 무게 추가
                    now_weight += append_truck;
                    // L을 초과했으면 그냥 움직이기
                } else {
                    // 그냥 이동 처리
                    bridge.addLast(0);
                }
            }

        }

        System.out.println(time);
    }
}