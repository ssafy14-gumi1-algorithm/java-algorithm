package algoStudy.Nov.week04.오예지;

import java.io.*;

public class boj_7682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            if (str.equals("end")) break;

            char[] b = str.toCharArray();

            int cntX = 0;
            int cntO = 0;
            int cntDot = 0;

            for (int i = 0; i < 9; i++) {
                if (b[i] == 'X') cntX++;
                else if (b[i] == 'O') cntO++;
                else if (b[i] == '.') cntDot++;
            }

            boolean winX = isWin(b, 'X');
            boolean winO = isWin(b, 'O');

            boolean valid = false;

            // 말 개수 기본 조건
            if (cntX == cntO || cntX == cntO + 1) {
                // 둘 다 이기는 경우
                if (winX && winO) {
                    valid = false;
                }
                // X만 이김 -> X가 한 수 더 많아야 함
                else if (winX) {
                    if (cntX == cntO + 1) valid = true;
                }
                // O만 이김 -> X와 O 수가 같아야 함
                else if (winO) {
                    if (cntX == cntO) valid = true;
                }
                // 아무도 안 이김 -> 보드가 꽉 차 있고, X가 한 수 더 많아야 게임 끝
                else {
                    if (cntDot == 0 && cntX == cntO + 1) valid = true;
                }
            }

            sb.append(valid ? "valid\n" : "invalid\n");
        }

        System.out.print(sb.toString());
    }

    // b: 길이 9 배열, p: 'X' 또는 'O'
    public static boolean isWin(char[] b, char p) {
        // 가로
        for (int i = 0; i < 3; i++) {
            if (b[3*i] == p && b[3*i + 1] == p && b[3*i + 2] == p) return true;
        }
        // 세로
        for (int j = 0; j < 3; j++) {
            if (b[j] == p && b[j + 3] == p && b[j + 6] == p) return true;
        }
        // 대각선
        if (b[0] == p && b[4] == p && b[8] == p) return true;
        if (b[2] == p && b[4] == p && b[6] == p) return true;

        return false;
    }
}

