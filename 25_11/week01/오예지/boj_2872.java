package JAVA_AlgorithmStudy.Nov.week01.오예지;

import java.util.*;
import java.io.*;

// 제일 큰 숫자보다 아래있는 숫자들 중에 큰 숫자부터 위로 올리기
// 제일 큰 숫자가 아래로 갔다면 그 다음 숫자
// 책은 아래부터 위로 내림차순
// 근데 이걸 어케 구현하지?

// 1. 시뮬레이션처럼 풀기 -> 시간 초과...
// 2. 자료구조적으로 접근해야함
    // - 문제접근을 잘못한듯
    // - 탑처럼 쌓여있다 -> 스택처럼 생각해야함
    // 즉 맨 아래부터 이미 정렬된 부분을 제외하고, 나머지를 올려야 함
    // 여러 권의 책을 한번에 들어내는 경우 -> 1번으로 치네

public class boj_2872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] book = new int [N];

        for (int i=0; i<N; i++){
            book[i] = Integer.parseInt(br.readLine());
        }

        int target = N;
        for (int i=N-1; i>=0; i--){
            if (book[i]==target){
                target--;
            }
        }

        System.out.println(target);

    }
}
