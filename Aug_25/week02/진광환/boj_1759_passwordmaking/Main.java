package boj.boj_1759_passwordmaking;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*TODO:
- 상수

- 변수
    L : 암호문 알파벳 개수
    C : 주어지는 문자의 개수
    arr : 주어지는 문자열을 담는 char[]
- 조건
    암호는 증가하는 순서대로 이루어져야 함
     (3 ≤ L ≤ C ≤ 15)
     알파벳 소문자로만 되어있음, 중복은 없음
- 구하는 값
    가능성 있는 암호를 모두 구하기
- 아이디어
    String메서드에서 증가하는 순서대로 이루어져 있는지 확인하는 메서드가 있는데 그거 확인해보자
    recur(idx, 문자열)
    인덱스가 끝까지 가면 종료
    만약 대상 문자가 사전순으로 앞서면 종료
    중복되는 것이 없으므로 사전순으로 같을 경우를 고려할 필요는 없음
*/
public class Main {

    static int L, C;
    static char[] arr;
    static char[] selected;
    static String vowels = "aeiou";

    static void recur(int idx, int selectedIdx){
        // 인덱스가 L에 다다르면
        if(selectedIdx == L){

            // 모음 개수 세기
            int vowelCount = 0;
            for (int i=0; i<L; i++){
                if (vowels.contains(String.valueOf(selected[i]))){
                    vowelCount ++;
                }
            }

            // 모음개수가 조건을 만족하면
            if (vowelCount > 0 && vowelCount <= L-2)
                System.out.println(new String(selected));
            return;
        }

        if (idx == C) return;

        selected[selectedIdx] = arr[idx];
        recur(idx + 1, selectedIdx + 1);

        recur(idx+1, selectedIdx);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];

        selected = new char[L];

        String line = br.readLine();

        for(int i = 0; i < 2*C; i = i+2){
            arr[i/2] = line.charAt(i);
        }

        Arrays.sort(arr);

        recur(0, 0);
    }
}


