package JAVA_AlgorithmStudy.Oct.week02.오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 일단 먼지가 먼저 확산함 -> 모든 칸에서 동시에 일어남
   -> 칸이 겹치는 경우 먼지의 양이 더해짐
2. 공청기가 돌아감
   -> 공청기 어케 돌림?
      1) 공청기 위치를 미리 구해둠
      2) 시계방향으로 도는 배열이랑 반시계방향으로 도는 배열을 미리 만들어놓기
      3) 돌다가 해당 칸에 미세먼지가 있으면 한 칸 씩 밀어주기
      4) 한 칸 씩 밀었을 때 공청기로 먼지가 들어가면 그 먼지는 없애기
*/

public class boj_17144 {
    static int R, C, T;
    static int[][] arr;

    // 상하좌우 확산 방향
    static int[] difDiri = {1, -1, 0, 0};
    static int[] difDirj = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        for (int i=0; i<R; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++){
                arr[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        // T초 이후의 남은 먼지의 양을 구해야 함
        for (int t=0; t<T; t++){
            // 1. 먼지 확산
//            System.out.println("t = "+ t);
            dust();
//            System.out.println("먼지 확산");
//            printarr();
            // 2. 공기청정기
            airPurifier();
//            System.out.println("공기청정기 작동 후");
//            printarr();
        }

        // 남은 먼지의 양을 카운팅
        int remainDust = 0;
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if (arr[i][j] != -1){
                    remainDust += arr[i][j];
                }
            }
        }

        System.out.println(remainDust);
    }

//    public static void printarr(){
//        for (int i=0; i<R; i++){
//            for (int j=0; j<C; j++){
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    // 먼지 확산
    public static void dust(){
        // 해당 배열에는 증가하는 먼지의 양을 저장해둠
        int[][] temp = new int[R][C];

        // 배열을 돌다가 해당칸에 먼지가 있으면 상하좌우로 확산하고 -> 이건 temp 배열에 저장
        // 확산 방향의 개수만큼 감소 -> 이건 기존 배열에서 바로 진행
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                int dir = 0;
                // 해당 칸에 먼지가 있음
                if (arr[i][j] != 0 && arr[i][j] != -1){
                    for (int d=0; d<4; d++){
                        int ni = i + difDiri[d];
                        int nj = j + difDirj[d];

                        // 공기 청정기 칸에는 먼지가 확산되지 않음
                        if (ni>=0 && ni<R && nj>=0 && nj<C && arr[ni][nj] != -1) {
                            dir++;
                            // 임시 배열에 증가시킬 양을 저장해둠
                            temp[ni][nj] += arr[i][j] / 5;
                        }
                    }
                    // 확산시킨 양만큼 감소
                    arr[i][j] -= (arr[i][j]/5)*dir;
                }
            }
        }

        // 임시 배열에 저장한 증가할 먼지의 양을 기존 배열에 적용
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if (arr[i][j] != -1){
                    arr[i][j] += temp[i][j];
                }
            }
        }
    }

    public static void airPurifier() {
        int air1Loc = 0, air2Loc = 0;

        // 공기청정기 위치 찾기
        for (int i = 0; i < R; i++) {
            if (arr[i][0] == -1) {
                air1Loc = i;
                air2Loc = i + 1;
                break;
            }
        }

        // 위쪽 공기청정기 (반시계 방향)
        int curX = air1Loc;
        int curY = 1; // 공기청정기 바로 오른쪽

        int prev = arr[curX][curY]; // 처음은 공기청정기로 들어오는 먼지가 없으므로 0
        arr[curX][curY] = 0;
        int[] dx = {0, -1, 0, 1}; // 오른쪽, 위, 왼쪽, 아래
        int[] dy = {1, 0, -1, 0};
        int d = 0;

        while (!(curX == air1Loc && curY == 0)) {
            int nx = curX + dx[d];
            int ny = curY + dy[d];

            if (nx < 0 || nx > air1Loc || ny < 0 || ny >= C) {
                d++;
                continue;
            }

            int tmp = arr[nx][ny];
            arr[nx][ny] = prev;
            prev = tmp;

            curX = nx;
            curY = ny;
        }

        // 아래쪽 공기청정기 (시계 방향)
        curX = air2Loc;
        curY = 1;
        prev = arr[curX][curY]; // 처음은 공기청정기로 들어오는 먼지가 없으므로 0
        arr[curX][curY] = 0;

        int[] dx2 = {0, 1, 0, -1}; // 오른쪽, 아래, 왼쪽, 위
        int[] dy2 = {1, 0, -1, 0};
        d = 0;

        while (!(curX == air2Loc && curY == 0)) {
            int nx = curX + dx2[d];
            int ny = curY + dy2[d];

            if (nx < air2Loc || nx >= R || ny < 0 || ny >= C) {
                d++;
                continue;
            }

            int tmp = arr[nx][ny];
            arr[nx][ny] = prev;
            prev = tmp;

            curX = nx;
            curY = ny;
        }

        // 공기청정기 칸은 항상 -1
        arr[air1Loc][0] = -1;
        arr[air2Loc][0] = -1;
    }
}
