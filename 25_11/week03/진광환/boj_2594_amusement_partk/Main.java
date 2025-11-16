package boj.boj_2594_amusement_partk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*TODO:
- 상수

- 변수

- 조건
    세혁이와 근영이는 서로 좋아하는 사이이다.
    그날 둘이 함께 할 수 있는 가장 긴 휴식시간이 언제인지를 찾으려고 한다.
    어떤 놀이기구가 작동을 시작하기 10분 전부터, 모든 놀이기구가 작동을 멈춘 후 10분 후까지는 쉴수가 없고, 그 나머지 시간에만 쉴 수 있다.
    -> 마지막에 적용해보자


- 구하는 값
- 아이디어
    타임라인을 저장하는 배열 arr를 생성해서
    놀이기구 시간대 일정을 전부 받은 후 배열에 분단위로 기록 후
    맨 처음 10분과 맨 이후 10분을 1로 바꾼다음
    숫자가 0인 부분의 가장 긴 길이를 구한다.

*/
public class Main {

    static int N;

    static int[] arr;
    static final int DUE = 720;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/boj/boj_2594_amusement_partk/input3.txt")); // 파일 경로는 프로젝트 구조에 맞게 수정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[DUE]; // 놀이공원 운영시간 12시간 * 60분 -> 720개의 인덱스 필요 따라서 배열의 크기는 721
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String start = st.nextToken();
            String end = st.nextToken();
            int start_idx = convertNumberIntoTimeLine(start);
            int end_idx = convertNumberIntoTimeLine(end) - 1;


            for (int j = start_idx - 10; j <= end_idx + 10; j++) {
                if (0 <= j && j < DUE) arr[j] = 1;
            }
        }


        // 가장 긴 0 구간 세기

        int result = 0;
        int temp = 0;
        for (int min : arr) {
            if (min == 0) {
                temp++;
            } else {
                result = Math.max(result, temp);
                temp = 0;
            }
        }
        result = Math.max(result, temp);
        System.out.println(result);
    }

    static int convertNumberIntoTimeLine(String number) {
        char[] digits = number.toCharArray();

        int minute1 = digits[3] - '0';
        int minute10 = digits[2] - '0';
        int hour1 = digits[1] - '0';
        int hour10 = digits[0] - '0' - 1;

        // 시각을 분단위의 인덱스로 바꾸기
        int index = minute1 + minute10 * 10 + hour1 * 60 + hour10 * 600;

        return index;
    }
}

