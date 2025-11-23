import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 소문난 칠공주
//
// 총 25명의 여학생들로 이루어진 여학생반은 5×5의 정사각형 격자 형태로 자리가 배치되었고,
// 얼마 지나지 않아 이다솜과 임도연이라는 두 학생이 두각을 나타내며 다른 학생들을 휘어잡기 시작했다.
// 곧 모든 여학생이 ‘이다솜파’와 ‘임도연파’의 두 파로 갈라지게 되었으며,
// 얼마 지나지 않아 ‘임도연파’가 세력을 확장시키며 ‘이다솜파’를 위협하기 시작했다.
//
// 위기의식을 느낀 ‘이다솜파’의 학생들은 과감히 현재의 체제를 포기하고,
// ‘소문난 칠공주’를 결성하는 것이 유일한 생존 수단임을 깨달았다.
// ‘소문난 칠공주’는 다음과 같은 규칙을 만족해야 한다.
//
// 1. 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
// 2. 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
// 3. 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
// 4. 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다.
// 5. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
//
// 여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.
//
// [입력]
// 'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값으로 갖는 5*5 행렬이 공백 없이 첫째 줄부터 다섯 줄에 걸쳐 주어진다.
//
// [출력]
// 첫째 줄에 ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 출력한다.
//
// [문제 풀이]
// 7명이 가로, 세로로 인접하게 위치해야 함
// S맴버가 최소한 4명 이상은 포함되어있게 조합을 만들어야 함
// 배열 크기는 5*5 = 25칸 => 시간복잡도를 크게 신경 안 써도 될 거 같음
// 첫번째 학생을 무조건 포함하는 조건으로 백트레킹 써서 풀면 될 거 같음
// 반복문 돌려서 맨 처음 만나는 맴버는 원복없이 True 체크 해주고 나머지 맴버들을 원복하면서 7뎊스로 구할 수 있는지 백트레킹 해보기
//
// 맨 처음 만나는 맴버도 원복해줘야하네 왜일까?
// 아니네
// 이제보니깐 테트로미노 ㅜ 같은 녀석이였네;
// ㅜ자는 어떻게 처리했더라
// 전부 다 탐색하게 했나?
// 일단 원복 필요없는건 맞음
//
// dfs 말고 bfs로 접근해야하나?
// 블럭을 어디에나 추가할 수 있게 해야하는데..
// 7개 조합 만들때까지 좌표들을 담아서 걔네들을 전부 반복문 돌려서 4방향으로 이동시켜야겠다
// 아오 귀찮아
//
// 만들어진 조합 결과를 또 배열로 담은 다음에 완성된 조합을 또 검증해봐야하나..?;
// 만들어진 조합 튜플로 바꿔서 set으로 저장해야하나..
//
// [시작 시간]
// 3 : 00
// [종료 시간]
// 3 : 59
public class Main {

    // 델타
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static char[][] map_arr = new char[5][5];
    static boolean[][] visited = new boolean[5][5];

    // 7개 좌표 배열
    static ArrayList<int[]> dir_arr = new ArrayList<>();

    // 결과값(모든 경우의 수)
    static HashSet<String> ans = new HashSet<>();

    // 백트레킹
    // 도연 yc 갯수
    static void backtracking(int yc, int lev) {
        // 도연이 4 이상인 경우 안되는 조합임
        if (yc >= 4) {
            return;
        }
        // lev가 7이면 조합 완성됨
        if (lev == 7) {
            // dir_arr 복사본 만들기
            ArrayList<int[]> c_dir_arr = new ArrayList<>();
            for (int[] p : dir_arr) {
                c_dir_arr.add(new int[]{p[0], p[1]});
            }
            // 복사본 정렬
            c_dir_arr.sort((a, b) -> {
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            });

            // set에 중복 거르면서 저장하기 위해 문자열로 조합 다시 만들기
            StringBuilder key = new StringBuilder();
            for (int[] p : c_dir_arr) {
                key.append(p[0]).append(',').append(p[1]).append(';');
            }
            ans.add(key.toString());
            return;
        }

        // 조합 이어가기
        // 현재까지 만들어진 조합 반복문 돌리기
        for (int idx = 0; idx < dir_arr.size(); idx++) {
            int[] cur = dir_arr.get(idx);
            int x = cur[0];
            int y = cur[1];

            // 4방향 무빙
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    dir_arr.add(new int[]{nx, ny});
                    // 도연파면 yc + 1 해주기
                    if (map_arr[ny][nx] == 'Y') {
                        backtracking(yc + 1, lev + 1);
                    } else {
                        backtracking(yc, lev + 1);
                    }
                    visited[ny][nx] = false;
                    dir_arr.remove(dir_arr.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map_arr[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[5][5];
        dir_arr = new ArrayList<>();
        ans = new HashSet<>();

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                visited[y][x] = true;
                // 좌표 담아주기
                dir_arr.add(new int[]{x, y});
                // 도연파면 yc + 1 해주기
                if (map_arr[y][x] == 'Y') {
                    backtracking(1, 1);
                } else {
                    backtracking(0, 1);
                }
                // 좌표 원복
                dir_arr.remove(dir_arr.size() - 1);
                // (Python 원본에는 visited[y][x] 원복이 없었으므로 여기서도 그대로 두었습니다)
            }
        }

        // print(len(list(ans)))
        System.out.println(ans.size());
    }
}
