import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // LOL

        // 당신 친구 지민이는 지금 할 일이 없다. 그리고 매우 심심하다. 그래서 쓸데없는 짓으로 시간을 때우려고 한다.
        // 그래서 단어 하나가 주어질 때 단어에 'lol'이 들어가도록 글자를 추가하거나 변경하거나 삭제하는 쓸데없는 프로그램을 작성하려고 한다.
        // 하지만 지민이는 갑자기 다른 쓸데없는 다른 프로그램을 작성하고 싶어졌다.
        // 그래서 당신도 할 일이 없기 때문에 지민이의 프로그램을 대신 작성할 것이다.
        // 하지만 당신은 지민이보다 프로그래밍을 못하기 때문에 추가/삭제/변경할 글자수의 최솟값을 출력해야 한다.

        // [입력]
        // 첫 번째 줄에 테스트케이스의 수 T(0 < T ≤ 100)가 주어진다.
        // 두 번째 줄부터 T+1번째 줄까지 단어가 하나씩 주어진다.
        // 단어는 영어 소문자로만 이루어져 있다.
        // 단어의 최대 길이는 50글자이다.

        // [출력]
        // i번째 줄에 해당 단어에 몇 개의 글자를 추가/수정/삭제해야 'lol'이라는 부분 문자열이 생기는지 출력하라.

        // [문제풀이]
        // 문자별로 while문을 돌리자
        // o나 l을 만나면 로직 시작
        // l의 경우엔 lol이 완성되면 바로 0 반환하고 종료
        // l 다음에 o나 l이 나오는지 확인하고, o면 다음이 l인지 추가 확인, 없거나 l이면 길이를 저장, 인덱스를 l부터 설정(1~2)
        // o가 나오면 ol까지 가능한지 확인하고, 길이를 저장
        // 변경은 어떻게 하지>?
        //

        int N = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < N; t++) {
            String word = sc.nextLine();
            int i = 0;
            int ans = 3;

            while (i < word.length()) {
                if (word.charAt(i) == 'l') {
                    i += 1;
                    int update_ans = 2;
                    if (i < word.length() && word.charAt(i) == 'o') {
                        i += 1;
                        update_ans -= 1;
                        if (i < word.length() && word.charAt(i) == 'l') {
                            ans = 0;
                            break;
                        } else {
                            i += 1;
                            if (ans > update_ans) {
                                ans = update_ans;
                            }
                        }
                    } else if (i < word.length() && word.charAt(i) == 'l') {
                        update_ans -= 1;
                        if (ans > update_ans) {
                            ans = update_ans;
                        }
                    } else if (i + 1 < word.length() && word.charAt(i + 1) == 'l') {
                        i += 1;
                        update_ans -= 1;
                        if (ans > update_ans) {
                            ans = update_ans;
                        }
                    } else {
                        i += 1;
                        if (ans > update_ans) {
                            ans = update_ans;
                        }
                    }
                } else if (word.charAt(i) == 'o') {
                    i += 1;
                    int update_ans = 2;
                    if (i < word.length() && word.charAt(i) == 'l') {
                        update_ans -= 1;
                        if (ans > update_ans) {
                            ans = update_ans;
                        }
                    } else {
                        i += 1;
                        if (ans > update_ans) {
                            ans = update_ans;
                        }
                    }
                } else {
                    i += 1;
                }
            }

            System.out.println(ans);
        }

        sc.close();
    }
}
