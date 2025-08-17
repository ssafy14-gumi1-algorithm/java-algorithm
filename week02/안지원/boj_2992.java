// 크면서 작은 수
// 같은 글자 조합을 가지고 있으면서, 현재값 보단 큰 수 중에서 가장 작은 값을 구하는 문제
// 이건 조합 구현하는 방법을 한번 찾아보는게 좋을듯
// 4글자라고 하면 4C4를 전부 구하고 거기서 반복문을 돌려 기존값 보다 큰 바로 다음 값을 구하면 되는 문제
// 조합 공식이 뭐징

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class boj_1759 {
    // 정수 X로 만들수 있는 조합 담는 배열
    // 이렇게 class 바로 상위에 static으로 선언하면 전역변수로 사용 가능함
    static ArrayList<String> combinations = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String X = br.readLine();

        for(int i = 0; i < X.length(); i++){
            // 사용 여부 체크
            boolean[] visited = new boolean[X.length()];
            Arrays.fill(visited, true);

            // 조합 구하는 DFS 실행
            getComb(X, i, "", 1, visited);
        }

        System.out.println(getResult(X, combinations));
    }

    // combination에서 결과값 찾는 함수
    public static void getComb(String X, int now_idx, String word, int length, boolean[] visited){
        // 사용여부 확인
        if(!visited[now_idx]){
            return;
        }

        // 사용 체크
        visited[now_idx] = false;

        // 현재 문자 추가
        word += X.charAt(now_idx);

        // 문자열 완성됐으면 combination에 추가
        if(length == X.length()){
            combinations.add(word);
            return;
        }

        for(int next_idx = 0; next_idx < X.length(); next_idx++){
            if(next_idx != now_idx){
                // 자바 얕은복사는 clone()이란 메서드가 있네
                // 뭔 파이썬, JS, 자바 전부 다 달라 미쳐버리겠네;;
                getComb(X, next_idx, word, length + 1, visited.clone());
            }
        }
    }

    // 결과값 찾는 함수
    public static int getResult(String X, ArrayList<String> combinations){
        // 오름차순 정렬
        combinations.sort(null);
        // 정수 X
        int int_X = Integer.parseInt(X);
        for(int i = 0; i < combinations.size(); i++){
            // 정수 조합
            int int_combination = Integer.parseInt(combinations.get(i));

            // 둘이 비교해서 X보다 큰 가장 첫번째 수를 반환하고 종료
            if(int_X < int_combination){
                return int_combination;
            }
        }
        return 0;
    }
}
