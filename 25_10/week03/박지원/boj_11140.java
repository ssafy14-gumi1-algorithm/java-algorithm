package week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11140 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String str = br.readLine();
            System.out.println(minCnt(str));
        }
    }

    private static int minCnt(String str) {
        // 1. 이미 완성된 경우
        if (str.contains("lol")) return 0;

        // 2. 1개만 추가하면 되는 경우
        if (str.contains("lo") || str.contains("ol") || str.contains("ll")) return 1;

        for (int i = 0; i + 2 < str.length(); i++) {
            if (str.charAt(i) == 'l' && str.charAt(i + 2) == 'l') return 1;
        }

        if (str.contains("l") || str.contains("o")) return 2;

        return 3;
    }

}
