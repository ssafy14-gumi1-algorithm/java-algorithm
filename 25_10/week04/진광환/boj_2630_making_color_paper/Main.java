package boj.boj_2630_making_color_paper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*TODO:
- 상수

- 변수
    N : 전체 종이의 크기 2 ,4, 8, 16, 32, 64,128
- 조건
    하얀색 또는 파란색 색종이 만들어야 한다.
    전체 종이가 모두 같은 색으로 칠해져 있지 않으면
    2바이 2로 계속 자른다
    모두 파란색으로 칠해져 있거나
    하나의 정사각형이 될 때까지 반복한다.

- 구하는 값
    잘라진 하얀색 색종이와 파란색 색종이의 합을 구하라

- 아이디어
    재귀 호출로 4귀퉁이 각 부분 저기하기

*/
public class Main {
    static int[][] paper;
    static int whiteCount = 0;
    static int blueCount = 0;



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        for (int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j ++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }

    static boolean isSameColor(int x, int y, int size){
        int color = paper[x][y];

        for (int i = x; i < x + size; i++){
            for (int j = y; j < y + size; j ++){
                if (paper[i][j] != color){
                    return false;
                }
            }
        }
        return true;
    }

    static void divide(int x, int y, int size){
        if (isSameColor(x,y,size)){
            if (paper[x][y] == 0){
                whiteCount ++;
            } else {
                blueCount++;
            }
            return;
        }

        int newSize = size / 2;
        divide(x, y, newSize);
        divide(x, y + newSize, newSize);
        divide(x + newSize, y, newSize);
        divide(x + newSize, y + newSize, newSize);

    }
}


