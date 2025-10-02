package boj.boj_14888_inserting_operator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;




/*
* 상
*   연산자의 종류
* 변
*   주어지는 숫자
* 조
*   연산자 우선순위를 무시하고 앞에서부터 계산하기
*   주어진 수의 순서를 바꾸면 안된다.
*   정수 나눗셈
*   양수로 바꾼 뒤 몫을 취하고 그 몫은 음수로 바꾼다.
*   주어지는 수는 모두 양수, 즉 뺄셈에서만 음수가 발생한다.
* 구
*   첫쨰 줄엔 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
*
* 아
*   최댓값, 최솟값을 둘다 구하는 것이기 때문에 중간에 가지치기는 하면 안될듯
* , 만약에 최적화를 하고 싶으면 dp를 이용해야겠다
*   -10억으로 문제에서 최솟값과 최댓값이 주어졌으므로 이를 활용하자
* */
public class Main {

    static int N, min_val, max_val;
    static int[] operands;
    static int[] operators;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        operands = new int[N];
        operators = new int[4];
        min_val = Integer.MAX_VALUE;
        max_val = Integer.MIN_VALUE;

        for (int i = 0; i < N; i ++){
            operands[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < 4; i ++){
            operators[i] = Integer.parseInt(st.nextToken());
        }
// 최소, 최대 초기화 안했음
        recur(1, operands[0]);
        System.out.println(max_val);
        System.out.println(min_val);

    }


    static void recur(int idx, int curresult){
        // idx == 1부터 시작하기

        if( idx == N ){
            min_val = Math.min(min_val, curresult);
            max_val = Math.max(max_val, curresult);
            return;
        }

        for (int i = 0; i < 4 ; i ++){
            if (operators[i] > 0){
                operators[i]--;
                if (i == 0) recur(idx + 1, curresult + operands[idx]);
                else if (i == 1) recur(idx + 1, curresult - operands[idx]);
                else if (i == 2) recur(idx + 1, curresult * operands[idx]);
                else{
                    if (curresult < 0) {
                        int processedResult = curresult * -1;
                        recur(idx + 1, processedResult / operands[idx] * -1);
                    } else recur(idx + 1, curresult / operands[idx]);
                }
                operators[i]++;
            }
        }
    }
}
