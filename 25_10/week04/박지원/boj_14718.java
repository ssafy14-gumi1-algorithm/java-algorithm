package week04.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_14718 {
    public static int n, k;
    public static List<Soldier> soldiers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에는 N명의 병사 수와 용감한 용사 진수가 이겨야 할 K명의 병사 수가 주어진다. (1 ≤ K ≤ N ≤ 100)
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 두 번째 줄부터 N+1 번째 줄까지 각 줄마다 병사들의 힘, 민첩, 지능을 세 개의 음이 아닌 정수로 주어진다. (0 ≤ 힘, 민첩, 지능 ≤ 1000000)
        Set<Integer> S = new HashSet<>();
        Set<Integer> D = new HashSet<>();
        Set<Integer> I = new HashSet<>();


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int str = Integer.parseInt(st.nextToken());
            int dex = Integer.parseInt(st.nextToken());
            int iq = Integer.parseInt(st.nextToken());
            soldiers.add(new Soldier(str, dex, iq));

            S.add(str);
            D.add(dex);
            I.add(iq);
        }

        int answer = Integer.MAX_VALUE;

        // s, d, i 조합 해서 내 스텟 조합 만들기
        for (int s : S) {
            for (int d : D) {
                for (int i : I) {

                    // s, d, i 이하의 soldier 개수 세기 -> k개 이상이면 answer 최소값 갱신
                    int cnt = 0;
                    for (Soldier soldier : soldiers) {
                        if (soldier.str <= s && soldier.dex <= d && soldier.iq <= i) {
                            cnt++;
                        }
                    }

                    if (cnt >= k) {
                        answer = Math.min(answer, s + d + i);
                    }
                }
            }
        }

        System.out.println(answer);

    }

    public static class Soldier {
        int str;
        int dex;
        int iq;

        public Soldier(int str, int dex, int iq) {
            this.str = str;
            this.dex = dex;
            this.iq = iq;
        }
    }
}