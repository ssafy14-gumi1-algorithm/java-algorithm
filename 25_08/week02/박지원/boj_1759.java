package week02.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1759 {

    // nCr을 구하는 문제라서
    public static int n; // 문제에서의 c
    public static int r; // 문제에서의 l

    public static char[] alpha;

    public static char[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        selected = new char[r];
        alpha = new char[n];
        for (int i = 0; i < n; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }

        // 정렬된 문자열을 선호하는 조교들의 성향
        Arrays.sort(alpha);
        // System.out.println(Arrays.toString(alpha));

        makePassword(0, 0);
    }

    /**
     *
     * @param start start인덱스부터 뽑기 시작
     * @param cnt   지금까지 cnt 개 뽑음
     */
    public static void makePassword(int start, int cnt) {
        // 종료조건: r개 뽑았으면 종료
        if (cnt == r) {
            // System.out.println(Arrays.toString(selected));
            // 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
            int consonant = 0; // 자음의 수
            int vowel = 0; // 모음의 수
            for (int i = 0; i < r; i++) {
                if (isVowel(selected[i])) vowel++;
                else consonant++;
            }
            // 가능성 있는 암호일 경우
            if (consonant >= 2 && vowel >= 1) {
                for (int i = 0; i < r; i++) {
                    System.out.print(selected[i]);
                }
                System.out.println();
            }
            return;
        }

        // 재귀 호출
        for (int i = start; i < n; i++) {
            selected[cnt] = alpha[i]; // cnt 번째 수로 alpha[i]를 뽑음
            makePassword(i + 1, cnt + 1);
        }
    }

    public static boolean isVowel(char a) {
        return a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u';
    }

}
