package boj.boj_1244_switch_on_and_off;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*TODO:
- 상수

- 변수
    1 이상이고 스위치 개수 이하인 자연수
- 조건
    남학생은 스위치 번호가 자기가 받은 수의 배수이면 그 스위치의 상태를 바꾼다
    여학생은 자기가 받은 스위치 번호를 중심으로 가장 많은 스위치를 함하는 구간 -> ㄴㄴ 최대길이 회문을 찾아서 바꾸셈
    여학생은 항상 홀수의 스위치 개수를 조작한다.
- 구하는 값
    스위치의 마지막 상태
- 아이디어
    남학생과 여학생이 번호를 받는 순서도 중요할 듯

*/
public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int numberOfStudents = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfStudents; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int switchNumber = Integer.parseInt(st.nextToken());

            doTask(gender, switchNumber);
        }

        // 결과 출력
        for (int i = 1; i <= N; i++) {
            System.out.print(arr[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }

    static void doTask(int gender, int switchNumber) {
        if (gender == 1) {
            // 남학생의 작업
            for (int i = switchNumber; i <= N; i = i + switchNumber) {
                arr[i] = Math.abs(arr[i] - 1);
            }

        } else {
            // 여학생의 작업
            int idx = 1;

            arr[switchNumber] = Math.abs(arr[switchNumber] - 1);
            while (1 <= switchNumber - idx && switchNumber + idx <= N && arr[switchNumber - idx] == arr[switchNumber + idx]) {
                arr[switchNumber - idx] = Math.abs(arr[switchNumber - idx] - 1);
                arr[switchNumber + idx] = Math.abs(arr[switchNumber + idx] - 1);
                idx++;
            }



        }
    }
}


