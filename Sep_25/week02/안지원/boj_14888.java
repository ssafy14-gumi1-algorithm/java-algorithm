// 연산자 끼워넣기

// N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다.
// 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다.
// 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

// 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다.
// 이때, 주어진 수의 순서를 바꾸면 안 된다.

// 예를 들어,
// 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고,
// 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다.

// 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다.
// 또, 나눗셈은 정수 나눗셈으로 몫만 취한다.
// 음수를 양수로 나눌 때는 C++14의 기준을 따른다.
// 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다.

// N개의 수와 N-1개의 연산자가 주어졌을 때,
// 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.

// [입력]
// 첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다.
// 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)
// 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데,
// 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(%)의 개수이다.

// [출력]
// 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
// 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다.
// 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.

// [문제 풀이]
// 특이한 조건이 2개인가 있음
// 1. 연산자 우선순위 없이 계산함
// 2. 숫자는 위치 바꿔선 안됨

// 받은 연산자 값들을 인덱스로 치환해서 새로운 배열에 담아주기
// 새로운 배열의 길이를 가지고 수열을 전부 구하기
// 구한 수열들을 가지고 계산식 진행(우선순위 없이 순차적으로 진행)

import java.io.*;
import java.util.*;

class boj_14888 {
    static int N;
    static int[] nums;          // A1..AN
    static int[] operators;     // 길이 N-1, 0:+, 1:-, 2:*, 3:/
    static int maxV = Integer.MIN_VALUE;
    static int minV = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        // 로컬 테스트: input.txt 사용하려면 다음 줄 잠깐 켜세요
        // System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        int[] cnt = new int[4]; // +, -, *, /
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) cnt[i] = Integer.parseInt(st.nextToken());

        // 연산자 배열 펼치기 (0:+, 1:-, 2:*, 3:/)
        operators = new int[N - 1];
        int idx = 0;
        for (int op = 0; op < 4; op++) {
            for (int k = 0; k < cnt[op]; k++) {
                operators[idx++] = op;
            }
        }

        // 연산자 전열 생성해서 계산
        permute(0);

        System.out.println(maxV);
        System.out.println(minV);
    }

    // nPn 순열 구하기
    static void permute(int lev) {
        if (lev == N - 1) {
            // 현재 operators 순서로 계산
            calculate(operators);
            return;
        }
        for (int j = lev; j < N - 1; j++) {
            swap(operators, lev, j);
            permute(lev + 1);
            swap(operators, j, lev);
        }
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // 앞에서부터 순차 계산 (연산자 우선순위 무시)
    static void calculate(int[] ops) {
        int res = nums[0];
        int target = 1;

        for (int op : ops) {
            int b = nums[target++];
            switch (op) {
                case 0: res = res + b; break;        // +
                case 1: res = res - b; break;        // -
                case 2: res = res * b; break;        // *
                case 3:                              // /
                    // 자바 int 나눗셈은 음수도 C++14처럼 '0 쪽으로' 버림
                    res = res / b;
                    break;
            }
        }
        if (res > maxV) maxV = res;
        if (res < minV) minV = res;
    }
}
