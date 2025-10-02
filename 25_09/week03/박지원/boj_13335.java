package Sep_25.week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_13335 {

    public static class Truck {
        int weight;
        int time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }

    public static int n, w, l; // 트럭의 수, 다리 길이, 다리의 최대 하중
    public static int[] trucks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [input1]
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        // [input2]
        trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(simulation());
    }

    public static int simulation() {
        Queue<Truck> queue = new ArrayDeque<>();

        int time = 0; // 총 걸린 시간
        int idx = 0; // 현재 Queue에 들어가고 싶은 트럭의 Idx
        int sumOfTruckWeight = 0;

        while (idx < n || !queue.isEmpty()) {
            // 트럭 한 칸씩 움직이기
            // Queue에 있는 트럭들을 하나씩 빼가면서 time하나씩 추가
            int size = queue.size();
            while (size-- > 0) {
                Truck curr = queue.poll();
                // 다리 끝에 있는 트럭은 꺼내 준다
                if (curr.time == w) {
                    // 현재 트럭의 무게 만큼 다리 위 트럭 무게 함이 줄어듦
                    sumOfTruckWeight -= curr.weight;
                } else {
                    // 그게 아닌 트럭은 time 1더해서 뒤에 넣어주기
                    curr.time++;
                    queue.offer(curr);
                }
            }

            // 트럭을 Queue(다리)에 넣기 위한 조건
            // 현재 트럭 + 다리 위에 있는 트럭의 수가 다리 길이 보다 작거나 같아야 함
            // 현재 트럭 무게 + 다리 위에 있는 트럭의 총 무게 합이 l보다 작거나 같아야 함
            if (idx < n && queue.size() < w && sumOfTruckWeight + trucks[idx] <= l) {
                // idx 번째 트럭을 다리 위에 올리고, 트럭 무게 합 증가
                queue.offer(new Truck(trucks[idx], 1));
                sumOfTruckWeight += trucks[idx++];
            }

            time++;
        }

        return time;
    }

    // 디버깅용
    public static void printStatus(int time, int idx, int idxWeight, int size, int sumOfTruckWeight){
        System.out.println("현재까지 걸린 시간: " + time);
        System.out.println("다리에 올라가야 하는 트럭의 idx: " + idx);
        System.out.println("다리에 올라가야 하는 트럭의 무게: " + idxWeight);
        System.out.println("현재 다리 위 트럭의 갯수: " + size);
        System.out.println("현재 다리 위 트럭 무게의 총 합: " + sumOfTruckWeight);
        System.out.println("=====================================");
    }
}
