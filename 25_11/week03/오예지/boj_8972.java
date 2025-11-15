package AlgorithmStudy.Nov.week03.오예지;

import java.util.*;
import java.io.*;

public class boj_8972 {
    static int R, C;
    static int[] di = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dj = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] field;
    static int jong_i, jong_j;
    static boolean flag = false; // 종수 사망 여부

    static ArrayList<Coord> mad = new ArrayList<>();

    static class Coord{
        int i, j;
        public Coord(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        field = new char[R][C];

        for (int i=0; i<R; i++){
            String str = br.readLine();
            for (int j=0; j<C; j++){
                char temp = str.charAt(j);
                field[i][j] = temp;

                if (temp=='R'){
                    mad.add(new Coord(i, j));
                }else if (temp == 'I'){
                    jong_i = i;
                    jong_j = j;
                }
            }
        }

        String command = br.readLine();

        // ------------------ 시뮬레이션 ------------------
        for (int num = 1; num <= command.length(); num++){
            int c = command.charAt(num-1) - '0';

            // 1. 종수 이동
            moveJong(c);
            if (flag) { // 종수가 R 위로 간 경우
                System.out.println("kraj " + num);
                return;
            }

            // 2. 미친 아두이노 이동
            for (int i = 0; i < mad.size(); i++){
                moveMad(i);
                if (flag) { // 미친 아두이노가 종수를 밟은 경우
                    System.out.println("kraj " + num);
                    return;
                }
            }

            // 3. 보드 재설정 + 충돌 R 제거
            set_field();
        }

        // 여기까지 왔다는 건 끝까지 안 죽음 -> 최종 보드 출력
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                sb.append(field[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static int distance(int r1, int s1, int r2, int s2){
        return Math.abs(r1-r2) + Math.abs(s1-s2);
    }

    // index번째 미친 아두이노 이동
    public static void moveMad(int index){
        Coord cur = mad.get(index);
        int ri = cur.i;
        int rj = cur.j;

        int minDis = Integer.MAX_VALUE;
        int bestDir = 5; // 일단 제자리(5)로 초기화

        // 1~9 방향 모두 보되, 범위 밖은 스킵
        for (int d = 1; d <= 9; d++){
            int ni = ri + di[d];
            int nj = rj + dj[d];

            if (ni < 0 || ni >= R || nj < 0 || nj >= C) continue;

            int dt = distance(jong_i, jong_j, ni, nj);

            if (dt < minDis){
                minDis = dt;
                bestDir = d;
            }
        }

        int ni = ri + di[bestDir];
        int nj = rj + dj[bestDir];

        // 이동한 위치가 종수 위치면 바로 사망 처리
        if (ni == jong_i && nj == jong_j){
            flag = true;
        }

        mad.set(index, new Coord(ni, nj));
    }

    public static void set_field(){
        // 모든 칸 초기화
        for (int i=0; i<R; i++){
            Arrays.fill(field[i], '.');
        }

        // 종수 위치 찍기
        field[jong_i][jong_j] = 'I';

        // 미친 아두이노들 먼저 찍으면서, 서로 겹치는지 확인
        int[][] count = new int[R][C];
        for (Coord cor : mad){
            count[cor.i][cor.j]++;
        }

        ArrayList<Coord> nextMad = new ArrayList<>();

        for (Coord cor : mad){
            int r = cor.i;
            int c = cor.j;

            // 둘 이상 모이면 폭발 → 안 남김
            if (count[r][c] >= 2) continue;

            // 살아남은 R만 보드에 표시
            if (field[r][c] == 'I') {

            } else {
                field[r][c] = 'R';
            }
            nextMad.add(new Coord(r, c));
        }

        mad = nextMad;
    }

    public static void moveJong(int c){
        jong_i += di[c];
        jong_j += dj[c];

        if (field[jong_i][jong_j] == 'R'){
            flag = true;
        }
    }
}
