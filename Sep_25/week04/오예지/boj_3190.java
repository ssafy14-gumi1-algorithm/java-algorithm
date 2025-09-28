package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_3190 {
    static int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    static int N;
    static int[][] arr;
    static List<Move> moveList = new ArrayList<>();

    public static class Move {
        int sec;
        char dir;

        public Move(int sec, char dir){
            this.sec = sec;
            this.dir = dir;
        }
    }

    // 이거 너무 어려워요,,
    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }

        // 이부분 지피티가 넣으라고 했는데 잘 모르겠음...
        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (!(o instanceof Coord)) return false;
            Coord c = (Coord) o;
            return this.x == c.x && this.y == c.y;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x, y);
        }
        // 여기까지
    }

    public static int moveSnake(int i, int j){
        Deque<Coord> snake = new ArrayDeque<>();
        snake.add(new Coord(i, j));

        int gameSec = 0;
        int tempDir = 0;
        int moveIdx = 0;

        while (true){
            gameSec++;

            Coord head = snake.peekLast();
            int nx = head.x + directions[tempDir][0];
            int ny = head.y + directions[tempDir][1];

            Coord newHead = new Coord(nx, ny);

            // 1) 벽에 충돌하는 경우
            if (nx<0 || nx>=N || ny<0 || ny>=N) return gameSec;

            // 2) 자기 몸과 충돌
            if (snake.contains(newHead)) return gameSec;

            // 3) 사과 확인
            if (arr[nx][ny] == 1){
                snake.addLast(newHead);
                arr[nx][ny] = 0;
            } else{
                snake.addLast(newHead);
                snake.pollFirst();
            }

            // 4) 방향 전환
            if (moveIdx < moveList.size() && gameSec ==moveList.get(moveIdx).sec){
                char d = moveList.get(moveIdx).dir;
                if (d == 'D'){
                    tempDir = (tempDir + 1)%4;
                } else {
                    tempDir = (tempDir + 3)%4;
                }
                moveIdx++;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        // arr에 사과가 있는 위치를 저장
        int K = Integer.parseInt(br.readLine());
        for (int i=0; i<K; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int appleX = Integer.parseInt(st1.nextToken());
            int appleY = Integer.parseInt(st1.nextToken());
            arr[appleX-1][appleY-1] = 1;
        }

        // 뱀의 이동할 시간과 방향을 저장함
        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st2.nextToken());
            char dir = st2.nextToken().charAt(0);

            moveList.add(new Move(sec, dir));

        }

        int result = moveSnake(0, 0);

        System.out.println(result);

    }
}
