package boj.boj_11140_LOL;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*TODO:
- 상수

- 변수
    T : 테스트 케이스의 수 -> FOR문 하나 더 필요

- 조건
    지민이는 할 일이 없다. 매우 심심하다.
    단어의 최대 길이는 50글자이다
- 구하는 값
    해당 단어에 몇개의 글자를 추가/수정/삭제해야 lol이라는 부분 문자열이 생기는지출력하기
- 아이디어
    어떤 경우의 수든 간에 추가로 할 수 있어서 답의 최대는 3 최소는 0임
*/
public class Main {



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        //1. tc용 for문 넣기
        for (int tc = 0; tc < T; tc++){
            String inputString = br.readLine();
            System.out.println(solve(inputString));
        }
    }

    static int solve(String inputString){

        // l과 l사이에 다른 알파벳이 있는 경우
        if (inputString.contains("lol")) return 0;

        if (
                inputString.contains("lal")
                || inputString.contains("lbl")
                || inputString.contains("lcl")
                || inputString.contains("ldl")
                || inputString.contains("lel")
                || inputString.contains("lfl")
                || inputString.contains("lgl")
                || inputString.contains("lhl")
                || inputString.contains("lil")
                || inputString.contains("ljl")
                || inputString.contains("lkl")
                || inputString.contains("lll")
                || inputString.contains("lml")
                || inputString.contains("lnl")
                || inputString.contains("lpl")
                || inputString.contains("lql")
                || inputString.contains("lrl")
                || inputString.contains("lsl")
                || inputString.contains("ltl")
                || inputString.contains("lul")
                || inputString.contains("lwl")
                || inputString.contains("lxl")
                || inputString.contains("lyl")
                || inputString.contains("lzl")
        ) return 1;

        if (inputString.contains("ll")
                || inputString.contains("lo")
                || inputString.contains("ol")
        ) return 1;

        if (inputString.contains("l")
                || inputString.contains("o")
        ) return 2;

        // 수정을 해야 하는경우
        // 이게 맞네 ㅋㅋㅋㅋ
        return 3;
    }
}


