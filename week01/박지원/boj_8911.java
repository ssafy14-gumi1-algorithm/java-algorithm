package 박지원;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_8911 {

    // 우 하 좌 상
    public static int[] dx = {0, -1, 0, 1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            String move = br.readLine();

            // 가장 처음에 (0, 0)에 있고, 북쪽을 쳐다보고 있다
            int x = 0;
            int y = 0;
            int dir = 3;

            int maxX = 0;
            int maxY = 0;
            int minX = 0;
            int minY = 0;

            for (int i = 0; i < move.length(); i++) {
                char command = move.charAt(i);

                switch (command) {
                    case 'L':
                        dir = (dir + 3) % 4;
                        break;
                    case 'R':
                        dir = (dir + 1) % 4;
                        break;
                    case 'F':
                        x += dx[dir];
                        y += dy[dir];
                        // System.out.println("x = " + x + " y = " + y);
                        break;
                    case 'B':
                        x += (-dx[dir]);
                        y += (-dy[dir]);
                        break;
                }

                minX = Math.min(x, minX);
                minY = Math.min(y, minY);
                maxX = Math.max(x, maxX);
                maxY = Math.max(y, maxY);
            }
//            System.out.println("minX = " + minX);
//            System.out.println("maxX = " + maxX);
//            System.out.println("minY = " + minY);
//            System.out.println("maxY = " + maxY);

            int w = maxX - minX;
            int h = maxY - minY;
            System.out.println(w * h);
        }
    }
}
