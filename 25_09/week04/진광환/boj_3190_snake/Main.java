package boj.boj_3190_snake;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, K, L, direction, head_i, head_j;
    static int[][] arr;
    static String[] commands;
    static Deque<Point> snake = new ArrayDeque<>();
    static final int[] di = {0, 1, 0, -1};
    static final int[] dj = {1, 0, -1, 0};

    static class Point {
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            // 사과의 값은 2로 설정하기
            arr[y-1][x-1] = 2;
        }

        snake.addFirst(new Point(0,0));
        arr[0][0] = 1;

        // 처음 방향 초기화
        direction = 0;


        L = Integer.parseInt(br.readLine());
        commands = new String[10001];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            commands[time] = command;
        }

        System.out.println(solve());
    }

    static int solve(){

        int time = 0;

        // 이동 가능한지 확인
        while (snakeCanMove(head_i, head_j)){

            // 움직이기
            moveSnake();

            // 시간 늘리기
            time ++;

            // 이동 방향 설정
            decideDirection(time);

//            printArray(arr, time);
        }
        return time + 1 ;
    }

    static void decideDirection(int time){
        String command = commands[time];
        if (command != null){
            if (command.equals("L")){
                // 좌회전
                direction = (direction + 3) % 4;
            } else if (command.equals("D")){
                // 우회전
                direction = (direction + 1) % 4;
            }
        }
    }

    static boolean snakeCanMove(int i_idx, int j_idx) {

        int ni = i_idx + di[direction];
        int nj = j_idx + dj[direction];

        // 머리가 이동하는 위치가 보드 안이라면
        if (0<=ni && ni<N && 0<=nj && nj<N){
            // 그리고 머리가 이동하는 위치에 뱀 꼬리가 없다면
            if (arr[ni][nj] != 1){
                // 이동 쌉가능
                return true;
            }
        }
        return false;
    }



    static void moveSnake(){

        // 머리의 좌표 옮기기
        Point head = snake.peekFirst();

        head_i = head.r + di[direction];
        head_j = head.c + dj[direction];

        // 머리 이동시키기 전에 사과인지 아닌지 저장해두기
        int apple = arr[head_i][head_j];

        // 뱀 머리 이동
        arr[head_i][head_j] = 1;
        snake.addFirst(new Point(head_i, head_j));

        // 꼬리의 좌표는 사과가 있는지 없는지에 따라 정해짐
        if (apple != 2){
            // 원래 꼬리가 있던 자리 지우기
            Point tail = snake.removeLast();
            arr[tail.r][tail.c] = 0;
        }
    }
    static void printArray(int[][] arr, int time){

        System.out.printf("----------------%d---------------\n", time);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

