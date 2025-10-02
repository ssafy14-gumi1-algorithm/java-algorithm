package 오예지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class boj_13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 다리 최대 하중

        int[] trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.add(0); // 다리 길이만큼 0으로 채움
        }

        int time = 0;
        int currentWeight = 0;
        int idx = 0; // 현재 대기 중인 트럭 인덱스

        while (idx < n || currentWeight > 0) {
            time++;

            // 1. 다리 맨 앞 칸에서 트럭(또는 0) 빼기
            currentWeight -= bridge.poll();

            // 2. 새로운 트럭 올릴 수 있으면 push
            if (idx < n && currentWeight + trucks[idx] <= L) {
                bridge.add(trucks[idx]);
                currentWeight += trucks[idx];
                idx++;
            } else {
                bridge.add(0);
            }
        }

        System.out.println(time);
    }
}

