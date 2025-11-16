package week03.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2594 {
    public static class Ride{
        int startTime;
        int endTime;

        public Ride(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static int n;
    public static List<Ride> rides;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        rides = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // HHMM형식
            int end = Integer.parseInt(st.nextToken());

            // hhmm -> min 으로 바꿔서 리스트에 넣기
            start = toMin(start) - 10;
            end = toMin(end) + 10;
            rides.add(new Ride(start, end));
        }

        // 정렬 시작시간이 빠른 순, 시작시간이 같으면 끝 시간이 빠른 순
        rides.sort((o1, o2) -> {
            if(o1.startTime==o2.startTime){
                return Integer.compare(o1.endTime, o2.endTime);
            }
            return Integer.compare(o1.startTime, o2.startTime);
        });

        int prevEnd = 600; // 10시 == 600(60*10)
        int answer = 0; // 가장 긴 쉬는 시간

        for (int i = 0; i < n; i++) {
            Ride curr = rides.get(i);

            if(curr.startTime > prevEnd){
                answer = Math.max(answer, curr.startTime-prevEnd);
            }

            // 구간 겹침 방지
            prevEnd = Math.max(prevEnd, curr.endTime);
        }

        // 폐장 시간 22시(22*60 = 1320)
        if(prevEnd<1320){
            answer = Math.max(answer, 1320-prevEnd);
        }
        System.out.println(answer);
    }

    private static int toMin(int time) {
        int hour = time/100;
        int min = time%100;
        return hour * 60 + min;
    }
}
