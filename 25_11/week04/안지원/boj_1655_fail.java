import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    // 가운데를 말해요
    //
    // 백준이는 동생에게 "가운데를 말해요" 게임을 가르쳐주고 있다.
    // 백준이가 정수를 하나씩 외칠때마다 동생은 지금까지 백준이가 말한 수 중에서 중간값을 말해야 한다.
    // 만약, 그동안 백준이가 외친 수의 개수가 짝수개라면 중간에 있는 두 수 중에서 작은 수를 말해야 한다.
    //
    // 예를 들어 백준이가 동생에게 1, 5, 2, 10, -99, 7, 5를 순서대로 외쳤다고 하면,
    // 동생은 1, 1, 2, 2, 2, 2, 5를 차례대로 말해야 한다.
    // 백준이가 외치는 수가 주어졌을 때, 동생이 말해야 하는 수를 구하는 프로그램을 작성하시오.
    //
    // [입력]
    // 첫째 줄에는 백준이가 외치는 정수의 개수 N이 주어진다.
    // N은 1보다 크거나 같고, 100,000보다 작거나 같은 자연수이다.
    // 그 다음 N줄에 걸쳐서 백준이가 외치는 정수가 차례대로 주어진다.
    // 정수는 -10,000보다 크거나 같고, 10,000보다 작거나 같다.
    //
    // [출력]
    // 한 줄에 하나씩 N줄에 걸쳐 백준이의 동생이 말해야 하는 수를 순서대로 출력한다.
    //
    // [추가사항]
    // 시간제한이 파이썬3에서 0.6초라고 함
    //
    // [문제 풀이]
    // N이 10만이네 허허
    // 시간 제한이 0.6초라네 허허
    // 파이썬3가 연산이 1초에 3000만이니깐 1800만 안에 풀어야 하네 허허
    //
    // 메모이제이션 같은거 쓰는 방법이 있을까?
    //
    // -10000 ~ 10000이니깐 이만큼 배열을 만들고, 연결리스트를 만들면?
    // 1. 현재 중앙값을 계속 저장하고 있는다.
    // 2. 중앙값을 기준으로 다음에 받는 값이 작다, 크다에 따라 큰 값 카운트, 작은 값 카운트를 추가해준다.
    // 3. 발견한 다음값을 담아줄땐 중앙값 인덱스로부터 연결리스트를 이어가며 본인이 있어야 할 자리를 찾으면 연결리스트를 수정해준다.
    // 4. 홀수 갯수가 만들어진 경우 치우친 밸런스를 기준으로 중앙값을 업데이트 해준다.
    // 5. 짝수 갯수가 만들어진 경우 현재 길이에서 중앙에 위치한 두 값을 비교해 작은 값으로 업데이트 해준다.
    // 겁나 복잡할거 같은데
    //
    // 아니 ㅁㅊ 왜 숫자가 중복으로 들어갈 수 있는건데
    // 코드 다 갈아엎어야 할 수준인데
    //
    // 20000개 짜리 배열 말고 그때그때 append 하는 배열을 만들어야하나
    // 튜플에 몇 개 있는지 카운트도 넣자 일단..;
    //
    // [시작 시간]
    // 4 : 08
    // [종료 시간]
    // 6 : 09(포기)

    static int N;
    // -10000 ~ 10000 배열
    // link_list[i] = {작은쪽 연결 인덱스, 큰쪽 연결 인덱스, 개수}
    static int[][] link_list = new int[20001][];

    // 중앙값
    static int middle = -1;

    // 중앙값 기준으로 작은 값
    static int sm = 0;
    // 중앙값 기준으로 큰 값
    static int lg = 0;
    // 중앙값 기준으로 동일한 값
    static int mid_same = 0;

    // 현재 입력 번호 (1-based)
    static int now;

    // 중앙값 기준으로 다음 middle값 뽑아내는 함수
    static void print_middle() {
        // 짝수인 경우
        if (now % 2 == 0) {
            // 작은값, 큰 값 둘 다 같으면 middle값 그대로 유지
            if (sm == lg) {
                System.out.println(middle);
                return;
            }
            // 작은 값이 더 크다면
            else if (sm > lg) {
                // mid_same값을 각자 나눠줘서 동일해졌을 경우
                if (sm + 1 == lg && mid_same >= 1) {
                    System.out.println(middle);
                    return;
                } else {
                    if (link_list[middle][0] < middle) {
                        middle = link_list[middle][0];

                        System.out.println(middle);
                        return;
                    }
                }
            } else if (sm < lg) {
                if (sm == lg + 1 && mid_same >= 1) {
                    System.out.println(middle);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (now = 1; now <= N; now++) {
            // 현재 숫자(홀수 고려해서 +10000으로 계산)
            int num = Integer.parseInt(br.readLine()) + 10000;

            // 중앙값 안 정해졌으면 중앙값 업데이트
            if (middle == -1) {
                middle = num;
                // 0번째는 middle 다음으로 작은 수
                // 1번째는 middle 다음으로 큰 수
                // 2번째는 현재 숫자의 갯수
                link_list[num] = new int[]{-1, 20001, 1};
                continue;
            }

            // 현재 중앙값 기준 큰 값, 작은 값 튜플
            int[] now_middle = link_list[middle];

            // 중앙값이 동일하다면 same 카운트 추가
            if (middle == num) {
                mid_same += 1;
                if (link_list[num] != null) {
                    link_list[num][2] = link_list[num][2] + 1;
                } else {
                    // 이 케이스는 원래 구조상 안 나와야 하지만, 방어적으로 처리
                    link_list[num] = new int[]{-1, 20001, 1};
                }
                continue;
            }

            // 중앙값 정해졌으면 중앙값보다 큰값인지, 작은값인지 구분
            if (middle > num) {
                // 작은 값 카운트 추가
                sm += 1;
                // 현재 존재하는 값이면 갯수 추가
                if (link_list[num] != null) {
                    link_list[num][2] = link_list[num][2] + 1;
                    continue;
                }
                // now 다음으로 큰 값(이어야 함)
                int large = middle;
                // now 다음으로 작은 값(이어야 함)
                int small = now_middle[0];
                // 중앙값 보다 작은 값이 -1이라면(아직 지정되지 않았다면) 작은 값 num으로 업데이트 해주기
                if (small == -1) {
                    link_list[middle] = new int[]{num, now_middle[1], now_middle[2]};
                    link_list[num] = new int[]{-1, middle, 1};
                    continue;
                }
                // 현재 작은값 보다 작다면 large, small 업데이트
                while (small > num) {
                    large = small;
                    int[] tmp = link_list[small];
                    small = tmp[0];
                }
                // 작은 값이 -1이라면 끄트머리 업데이트
                if (small == -1) {
                    link_list[num] = new int[]{-1, large, 1};
                    int[] largeArr = link_list[large];
                    link_list[large] = new int[]{num, largeArr[1], largeArr[2]};
                    continue;
                }
                // num이 현재 작은값 보다 크다면 위치 업데이트
                int[] smallArr = link_list[small];
                int[] largeArr2 = link_list[large];
                link_list[num] = new int[]{small, large, 1};
                link_list[small] = new int[]{smallArr[0], num, smallArr[2]};
                link_list[large] = new int[]{num, largeArr2[1], largeArr2[2]};
            } else if (middle < num) {
                // 큰 값 카운트 추가
                lg += 1;
                // 현재 존재하는 값이면 갯수 추가
                if (link_list[num] != null) {
                    link_list[num][2] = link_list[num][2] + 1;
                    continue;
                }
                // now 다음으로 큰 값(이어야 함)
                int large = now_middle[1];
                // now 다음으로 작은 값(이어야 함)
                int small = middle;
                // 중앙값 보다 큰 값이 20001이라면(아직 지정되지 않았다면) 큰 값 num으로 업데이트 해주기
                if (large == 20001) {
                    link_list[middle] = new int[]{now_middle[0], num, now_middle[2]};
                    link_list[num] = new int[]{middle, 20001, 1};
                    continue;
                }
                // 현재 큰 값 보다 크다면 large, small 업데이트
                while (large < num) {
                    small = large;
                    System.out.println(Arrays.toString(link_list[large]));
                    int[] largeArr = link_list[large];
                    large = largeArr[1];
                }
                // 큰 값이 20001이라면 끄트머리 업데이트
                if (large == 20001) {
                    link_list[num] = new int[]{small, 20001, 1};
                    int[] smallArr = link_list[small];
                    link_list[small] = new int[]{smallArr[0], num, smallArr[2]};
                    continue;
                }
                // num이 현재 작은값 보다 크다면 위치 업데이트
                int[] smallArr2 = link_list[small];
                int[] largeArr2 = link_list[large];
                link_list[num] = new int[]{small, large, 1};
                link_list[small] = new int[]{smallArr2[0], num, smallArr2[2]};
                link_list[large] = new int[]{num, largeArr2[1], largeArr2[2]};
            }

            // 현재 중앙값이
        }

        // 안해.
    }
}
