package AlgorithmStudy.Nov.week03.오예지;

import java.util.*;
import java.io.*;

public class boj_2594 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[13][60];

        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String start = st.nextToken();
            String end = st.nextToken();

            int start_hour = Integer.parseInt(start.substring(0, 2));
            int start_minute = Integer.parseInt(start.substring(2));

            start_minute -= 10;
            if (start_minute < 0){
                start_hour--;
                start_minute = 60+start_minute;
                if (start_hour<10){
                    start_hour = 10;
                    start_minute = 0;
                }
            }

            int end_hour = Integer.parseInt(end.substring(0, 2));
            int end_minute = Integer.parseInt(end.substring(2));

            end_minute += 10;
            if (end_minute>=60){
                end_hour++;
                end_minute = (end_minute)%60;
                if (end_hour>=22){
                    end_hour=22;
                    end_minute=0;
                }
            }


            int sh = start_hour-10;
            int sm = start_minute;

            while(true){
                if (sh == (end_hour - 10) && sm == end_minute) break;  // 먼저 체크하고

                visited[sh][sm] = true;  // 그 다음에 찍기

                sm++;
                if (sm >= 60){
                    sm = 0;
                    sh++;
                }
            }
        }

        boolean flag = false;
        int cnt = 0;
        int maxCnt=0;
        for(int k=0; k<12; k++){
            for(int j=0; j<60; j++) {
                if (visited[k][j] && flag){
                    flag = false;
                    maxCnt = Math.max(maxCnt, cnt);
                    cnt = 0;
                }
                if (!visited[k][j]){
                    flag = true;
                    cnt++;
                }
            }
        }

        maxCnt = Math.max(maxCnt, cnt);

        System.out.println(maxCnt);
    }
}
