package boj.boj_2872_lib_in_my_house;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] book = new int[N];
        for (int i = 0; i < N; i++) {
            book[i] = Integer.parseInt(br.readLine());
        }
        int max = N;
        int result = 0;

        // i는 마지막부터 0까지 가기
        for (int i = N - 1; i >= 0; i--) {
            if (book[i] == max) {

                // 만약 max라면 기준 값을 하나 적게 해야함
                max--;
            } else {
                // max 값이 아니라면 앞으로 옮겨야 함
                result++;
            }
        }
        System.out.println(result);
    }
}

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.*;
//
///*TODO:
//- 상수
//
//- 변수
//    책 하나를 뺀 다음 가장 위에 놓기
//
//- 조건
//
//- 구하는 값
//
//- 아이디어
//
//*/
//public class Main {
//    static int N;
//    static LinkedList<Integer> arr;
//
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//        arr = new LinkedList<>();
//        for (int i = 0; i < N; i ++){
//            arr.add(Integer.parseInt(br.readLine()));
//        }
//        System.out.println(solve());
//    }
//
//    static int solve() {
//        int s = arr.size();
//        int result = 0;
//        if (s == 1 || s == 0) return result;
//
//        while(!isArranged()){
//            result ++;
//            partialMin();
//        }
//        return result;
//    }
//    static boolean isArranged(){
//        for (int i = 0; i < N-1; i++){
//            if (arr.get(i) > arr.get(i+1)) return false;
//        }
//        return true;
//    }
//    // 맨 앞 원소를 제외한 최솟값의 인덱스 구하기
//    static void partialMin() {
//        int min = arr.get(1);
//        int minIdx = 1;
//        for (int i = 2; i < N; i ++){
//            if (arr.get(i) < min) {
//                min = arr.get(i);
//                minIdx = i;
//            }
//        }
//
//        arr.remove(minIdx);
//        arr.addFirst(min);
//    }
//
//}
//
//
