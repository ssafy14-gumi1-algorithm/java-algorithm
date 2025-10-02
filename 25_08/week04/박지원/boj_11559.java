package week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class boj_11559 {

    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};


    public static int numOfChain; // [goal] 총 연쇄 횟수

    public static char[][] field; // 필드 정보
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        field = new char[12][6];

        // [input] 총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
        //이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
        //R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                field[i][j] = line.charAt(j);
                //입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈 칸이 있는 경우는 없다.
            }
        }

        // [solution] 뿌요가 가능하면 가능한 거 다 터트리고 중력 적용 반복
        while (true) {
            visited = new boolean[12][6];
            List<Coord> toClear = new ArrayList<>();
            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    // 방문한 칸이면 방문하지 않음 || 뿌요가 없는 칸은 방문하지 않음
                    if (visited[i][j] || field[i][j] == '.') continue;

                    List<Coord> coords = getPuyoCoord(i, j);
                    if (coords.size() >= 4) {
                        toClear.addAll(coords);
                    }
                }
            }

            // 터지는 게 없으면 반복 종료
            if(toClear.isEmpty()) break;

            numOfChain++; // 4개 이상이면 1연쇄
            clearField(toClear);
//            System.out.println("============[clear]===========");
//            printField();
            // 뿌요 되는 거 다 터트렸으면 전부 아래로 내려줌
            moveDown();

//            System.out.println("============[movedown]============");
//            printField();
        }

        // [output] 총 연쇄 횟수
        System.out.println(numOfChain);
    }

    // 필드 깨끗하게 비우기
    private static void clearField(List<Coord> coords) {
        for (Coord coord : coords) {
            field[coord.x][coord.y] = '.';
        }
    }

    /**
     * 같은 색 뿌요의 좌표를 리스트에 넣어서 4개 이상이면 뿌요 좌표 반환
     *
     * @param x
     * @param y
     * @return 뿌요가 4개 이상이면 뿌요의 좌표, 아니라면 빈리스트 반환
     */
    public static List<Coord> getPuyoCoord(int x, int y) {
        List<Coord> coords = new ArrayList<>();
        Queue<Coord> queue = new ArrayDeque<>();

        // 방문 예약
        queue.offer(new Coord(x, y));
        visited[x][y] = true;

        // 뿌요 좌표를 넣어주기
        coords.add(new Coord(x, y));

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + dx[dir];
                int ny = curr.y + dy[dir];

                // 배열 범위를 벗어나거나 방문한 곳은 건너뛰기
                if (!inRange(nx, ny) || visited[nx][ny]) continue;

                // 같은 색이라면 queue에 넣어서 방문 예약
                if (field[nx][ny] == field[curr.x][curr.y]) {
                    queue.offer(new Coord(nx, ny));
                    visited[nx][ny] = true;

                    // 뿌요 좌표 넣어주기
                    coords.add(new Coord(nx, ny));
                }
            }
        }

        // [return] 뿌요가 4개 이상일 때만 뿌요의 좌표 반환
        if (coords.size() < 4) return new ArrayList<>();
        else return coords;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < 12 && y >= 0 && y < 6;
    }

    // 뿌요 떨구기
    public static void moveDown() {
        for (int col = 0; col < 6; col++) {
            for (int row = 11; row >= 0; row--) {
                if (field[row][col] != '.') continue;

                int idx = row;
                while (field[idx][col] == '.') {
                    idx--;
                    if(idx<0) break;
                }

                if(idx<0) continue;
                field[row][col] = field[idx][col];
                field[idx][col] = '.';
            }
        }
    }

    // 디버깅용
    public static void printField() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
