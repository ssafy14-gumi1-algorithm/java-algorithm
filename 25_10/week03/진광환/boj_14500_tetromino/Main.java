package boj.boj_14500_tetromino;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*TODO:
- 상수

- 변수
    N, M종이의 크기

- 조건
    회전이나 대칭을 시켜도 된다.
- 구하는 값
    테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여있는 수들의 합의 최대
- 아이디어

*/
public class Main {

    static int result;
    static int N, M;
    static int[][] arr;
    static final int[][][] tetrominos = {
            {
                    {1, 1, 1, 1} // 바 가로
            },
            {
                    {1}, {1}, {1}, {1} // 바 세로
            },
            {
                    {1, 1}, {1, 1} // 정사각형
            },
            // 니은자
            {
                    {1, 0},
                    {1, 0},
                    {1, 1}
            },
            {
                    {1, 1},
                    {1, 0},
                    {1, 0},
            },
            {
                    {1, 1},
                    {0, 1},
                    {0, 1},
            },
            {
                    {0, 1},
                    {0, 1},
                    {1, 1},
            },
            // 가로가 긴 니은자
            {
                    {1, 1, 1},
                    {1, 0, 0}
            },
            {
                    {1, 1, 1},
                    {0, 0, 1}
            },
            {
                    {0, 0, 1},
                    {1, 1, 1}
            },
            {
                    {1, 0, 0},
                    {1, 1, 1}
            },

            // 번개모양
            {
                    {1, 0},
                    {1, 1},
                    {0, 1}
            },
            {
                    {0, 1},
                    {1, 1},
                    {1, 0}
            },
            {
                    {1, 1, 0},
                    {0, 1, 1}
            },
            {
                    {0, 1, 1},
                    {1, 1, 0}
            },
            // ㅗ 모양
            {
                    {0, 1, 0},
                    {1, 1, 1}
            },
            {
                    {1, 1, 1},
                    {0, 1, 0}
            },
            {
                    {0, 1},
                    {1, 1},
                    {0, 1}
            },
            {
                    {1, 0},
                    {1, 1},
                    {1, 0}
            },

    };


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[][] tetromino : tetrominos) {
            int n = tetromino.length;
            int m = tetromino[0].length;
            for (int i = 0; i < N - n + 1; i++) {
                for (int j = 0; j < M - m + 1; j++) {
                    int sum = 0;
                    for (int k = 0; k < n; k ++){
                        for (int l = 0; l < m; l ++){
                            if (tetromino[k][l] == 1){
                                sum += arr[i+k][j+l];
                            }
                        }
                    }
                    result = Math.max(result, sum);
                }
            }
        }
        System.out.println(result);
    }
}


