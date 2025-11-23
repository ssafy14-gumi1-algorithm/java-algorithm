import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        // 가운데를 말해요

        // 백준이는 동생에게 "가운데를 말해요" 게임을 가르쳐주고 있다.
        // 백준이가 정수를 하나씩 외칠때마다 동생은 지금까지 백준이가 말한 수 중에서 중간값을 말해야 한다.
        // 만약, 그동안 백준이가 외친 수의 개수가 짝수개라면 중간에 있는 두 수 중에서 작은 수를 말해야 한다.

        // 예를 들어 백준이가 동생에게 1, 5, 2, 10, -99, 7, 5를 순서대로 외쳤다고 하면, 동생은 1, 1, 2, 2, 2, 2, 5를 차례대로 말해야 한다.
        // 백준이가 외치는 수가 주어졌을 때, 동생이 말해야 하는 수를 구하는 프로그램을 작성하시오.

        // [입력]
        // 첫째 줄에는 백준이가 외치는 정수의 개수 N이 주어진다.
        // N은 1보다 크거나 같고, 100,000보다 작거나 같은 자연수이다.
        // 그 다음 N줄에 걸쳐서 백준이가 외치는 정수가 차례대로 주어진다.
        // 정수는 -10,000보다 크거나 같고, 10,000보다 작거나 같다.

        // [출력]
        // 한 줄에 하나씩 N줄에 걸쳐 백준이의 동생이 말해야 하는 수를 순서대로 출력한다.

        // [추가사항]
        // 시간제한이 파이썬3에서 0.6초라고 함

        // [문제 풀이]
        // N이 10만이네 허허
        // 시간 제한이 0.6초라네 허허
        // 파이썬3가 연산이 1초에 3000만이니깐 1800만 안에 풀어야 하네 허허

        // 메모이제이션 같은거 쓰는 방법이 있을까?

        // -10000 ~ 10000이니깐 이만큼 배열을 만들고, 연결리스트를 만들면?
        // 1. 현재 중앙값을 계속 저장하고 있는다.
        // 2. 중앙값을 기준으로 다음에 받는 값이 작다, 크다에 따라 큰 값 카운트, 작은 값 카운트를 추가해준다.
        // 3. 발견한 다음값을 담아줄땐 중앙값 인덱스로부터 연결리스트를 이어가며 본인이 있어야 할 자리를 찾으면 연결리스트를 수정해준다.
        // 4. 홀수 갯수가 만들어진 경우 치우친 밸런스를 기준으로 중앙값을 업데이트 해준다.
        // 5. 짝수 갯수가 만들어진 경우 현재 길이에서 중앙에 위치한 두 값을 비교해 작은 값으로 업데이트 해준다.
        // 겁나 복잡할거 같은데

        // 아니 ㅁㅊ 왜 숫자가 중복으로 들어갈 수 있는건데
        // 코드 다 갈아엎어야 할 수준인데

        // 20000개 짜리 배열 말고 그때그때 append 하는 배열을 만들어야하나
        // 튜플에 몇 개 있는지 카운트도 넣자 일단..;

        // [시작 시간]
        // 4 : 08
        // [종료 시간]
        // 6 : 09(포기)

        // [문제점]
        // 힙 큐 쓰면 쉽게 풀리는 문제인데 혼자 난리 부르스를 떨었음;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // left = []   # 최대 힙 (음수로 저장)
        // right = []  # 최소 힙
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
        PriorityQueue<Integer> right = new PriorityQueue<>(); // 최소 힙

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            // 1. 일단 왼쪽/오른쪽 중 어디에 넣을지 결정
            if (left.isEmpty() || x <= left.peek()) {
                left.offer(x);
            } else {
                right.offer(x);
            }

            // 2. 힙 사이즈 균형 맞추기
            // left는 right보다 같거나 1개 더 많을 수 있음
            if (left.size() > right.size() + 1) {
                // left에서 하나 빼서 right로
                right.offer(left.poll());
            } else if (right.size() > left.size()) {
                // right에서 하나 빼서 left로
                left.offer(right.poll());
            }

            // 3. 현재 가운데 값 = left의 루트
            sb.append(left.peek()).append('\n');
        }

        // 한 번에 출력
        System.out.print(sb.toString());
    }
}
