import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    // 틱택토

    // 틱택토 게임은 두 명의 사람이 번갈아가며 말을 놓는 게임이다.
    // 게임판은 3×3 격자판이며, 처음에는 비어 있다.
    // 두 사람은 각각 X 또는 O 말을 번갈아가며 놓는데, 반드시 첫 번째 사람이 X를 놓고 두 번째 사람이 O를 놓는다.
    // 어느 때든지 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는 데 성공하면 게임은 즉시 끝난다.
    // 게임판이 가득 차도 게임은 끝난다.

    // 게임판의 상태가 주어지면, 그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태인지를 판별하시오.

    // [입력]
    // 입력은 여러 개의 테스트 케이스로 이루어져 있다.
    // 각 줄은 9개의 문자를 포함하며, 'X', 'O', '.' 중 하나이다.
    // '.'은 빈칸을 의미하며, 9개의 문자는 게임판에서 제일 윗 줄 왼쪽부터의 순서이다.
    // 입력의 마지막에는 문자열 "end"가 주어진다.

    // [출력]
    // 각 테스트 케이스마다 한 줄에 정답을 출력한다.
    // 가능할 경우 "valid", 불가능할 경우 "invalid"를 출력한다.

    // [문제 풀이]
    // valid가 되는 경우를 생각해보자.
    // O는 후공, X는 선공임
    // O가 이기는 경우
    // 1. X와 O 숫자가 동일, O만 3줄이 완성됨
    // X가 이기는 경우
    // 1. X가 O보다 1개 더 많음, X만 3줄이 완성됨
    // 아직 진행중인 경우
    // 1. X가 O보다 1개 더 많거나 같음, 둘 다 3줄이 완성되지 않음
    // 이거 외엔 전부 invalid 처리 해주면 되지 않을까?

    // [시작시간]
    // 1 : 30
    // [종료시간]
    // 2 : 56

    // 8방향 델타
    static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    // 보드 배열
    static char[][] t_arr = new char[3][3];

    // 빙고찾기 dfs
    static boolean dfs(int x, int y, char targ, int dir, int cnt) {
        if (cnt == 3) {
            return true;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx >= 0 && ny >= 0 && nx < 3 && ny < 3 && t_arr[ny][nx] == targ) {
            boolean res = dfs(nx, ny, targ, dir, cnt + 1);
            return res;
        }

        return false;
    }

    // O, X 빙고 있는지 확인하는 함수
    static boolean[] find_bingo() {
        boolean O_bingo = false;
        boolean X_bingo = false;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (t_arr[y][x] == 'O') {
                    char targ = 'O';
                    for (int dir = 0; dir < 8; dir++) {
                        if (!O_bingo) {
                            O_bingo = dfs(x, y, targ, dir, 1);
                        }
                    }
                } else if (t_arr[y][x] == 'X') {
                    char targ = 'X';
                    for (int dir = 0; dir < 8; dir++) {
                        if (!X_bingo) {
                            X_bingo = dfs(x, y, targ, dir, 1);
                        }
                    }
                }
            }
        }

        return new boolean[] { O_bingo, X_bingo };
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String inp = br.readLine();
            if (inp == null) break;
            if (inp.equals("end")) break;

            int X = 0;
            int O = 0;

            char[] t_list = inp.toCharArray();
            t_arr = new char[3][3];

            // '.'으로 초기화
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    t_arr[i][j] = '.';
                }
            }

            // 1. 갯수 세기
            for (int i = 0; i < 9; i++) {
                char t = t_list[i];
                if (t == 'X') {
                    X += 1;
                    t_arr[i / 3][i % 3] = 'X';
                } else if (t == 'O') {
                    O += 1;
                    t_arr[i / 3][i % 3] = 'O';
                }
            }

            // O가 더 많으면 잘못된거
            if (O > X) {
                sb.append("invalid").append('\n');
                continue;
            }

            // 2. 갯수에 따른 X, O 빙고 여부 판단
            // 마지막 턴이 O인 경우
            if (O == X) {
                // O가 빙고를 만들었고, X는 빙고를 만들지 못했는지 확인
                boolean[] res = find_bingo();
                boolean O_bingo = res[0];
                boolean X_bingo = res[1];

                if (O_bingo && !X_bingo) {
                    sb.append("valid").append('\n');
                    continue;
                } else {
                    sb.append("invalid").append('\n');
                    continue;
                }
            }
            // 마지막 턴이 X인 경우
            else if (O + 1 == X) {
                // X가 빙고를 만들었고, O는 빙고를 만들지 못했는지 확인
                boolean[] res = find_bingo();
                boolean O_bingo = res[0];
                boolean X_bingo = res[1];

                if (X_bingo && !O_bingo) {
                    sb.append("valid").append('\n');
                    continue;
                }
                // 무승부로 마무리 된 경우 추가
                else if (!O_bingo && !X_bingo && O + X == 9) {
                    sb.append("valid").append('\n');
                    continue;
                } else {
                    sb.append("invalid").append('\n');
                    continue;
                }
            }
            // 나머진 전부 invalid
            else {
                sb.append("invalid").append('\n');
                continue;
            }
        }

        System.out.print(sb.toString());
    }
}
