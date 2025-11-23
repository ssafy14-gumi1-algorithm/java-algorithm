package algoStudy.Nov.week04.오예지;

import java.io.*;
import java.util.*;

// 최대 힙과 최소 힙의 길이를 맞춰줘야 함

public class boj_1655 {
    // 최소 힙
    static PriorityQueue<Integer> minPq = new PriorityQueue<>();
    // 최대 힙
    static PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();



        int N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            pqAdd(num);
            sb.append(getMedian()).append('\n');
        }

        System.out.println(sb.toString());

    }

    public static void pqAdd(int num){
        // 최소 힙에는 큰 숫자들을 넣고, 최대 힙에는 작은 숫자들을 넣어야 peek를 봤을 때 중간값을 찾을 수 있음
        if (maxPq.isEmpty() || num < maxPq.peek()){
            maxPq.offer(num);
        }else{
            minPq.offer(num);
        }

        // 양쪽 개수 확인 - 2개 차이나는 경우에는 개수를 맞춰줌
        if (maxPq.size() > minPq.size() + 1){
            minPq.offer(maxPq.poll());
        } else if (minPq.size() > maxPq.size() + 1) {
            maxPq.offer(minPq.poll());
        }
    }

    public static int getMedian() {
        if (minPq.size() == maxPq.size()){
            return maxPq.peek();
        }else if (maxPq.size() > minPq.size()){
            return maxPq.peek();
        }else {
            return minPq.peek();
        }
    }
}
