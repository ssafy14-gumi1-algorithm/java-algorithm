package week02.안지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2^11 까지 탐색하는건 시간복잡도 안 걸릴듯
// 부분집합 전부 탐색해서
public class Main {
    // static으로 인스턴스가 아닌 static 메서드로 만들기
    // 그냥 적어넣는건 인스턴스 선언이라고 함
    static int[] numArr = {1, 2, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 테스트케이스만큼 반복문 실행
        for(int tc = 1; tc < T+1; tc++) {
            // 목표 숫자 받기
            int num = Integer.parseInt(br.readLine());

            // 결과값
            int result = 0;

            // 1, 2, 3 모든 케이스로 덧셈 DFS 시작
            for(int i = 0; i < numArr.length; i++) {
                result += getCount(num, numArr[i]);
            }

            System.out.println(result);
        }
    }

    private static int getCount(int num, int nowNum) {
        // nowNum이 num 보다 클 때 종료(0을 반환)
        // nowNum이 num과 동일한 경우 1 반환

        // 현재 값이 num을 만족하면 1 반환
        if(nowNum == num) {
            return 1;
        }
        // 현재 idx가 num을 넘어서면 0 반환
        if(nowNum > num) {
            return 0;
        }

        int count = 0;
        // 1, 2, 3 하나씩 순차적으로 더해주면서 진행
        for(int i = 0; i < numArr.length; i++) {
            count += getCount(num, nowNum + numArr[i]);
        }

        return count;
    }
}