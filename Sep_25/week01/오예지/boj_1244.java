package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1244 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 스위치 입력을 받음
        int N = Integer.parseInt(br.readLine());

        // 스위치 상태 입력 + 배열 입력을 받으려면 배열을 먼저 선언해줘야 함
        int [] switches = new int[N];

        StringTokenizer st  = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            switches[i] = Integer.parseInt(st.nextToken());
        }

        // 학생수
        int S = Integer.parseInt(br.readLine());


        // 학생의 성별과 부여받은 숫자, 한 줄에 변수가 두개씩 들어옴
        for (int s = 0; s < S; s++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            // 여기까지가 입력 받는 부분

            // 남학생
            if (gender == 1){
                int num = number;
                // number의 배수인 스위치의 상태를 변경함
                while (num <= N){
                    switches[num-1] = (switches[num-1] + 1) % 2;
                    num += number;
                }
            }
            else{
                int left = number - 1;
                int right = number - 1;

                while (left - 1 >= 0 && right+1 < N && switches[left - 1] == switches[right + 1]) {
                    left--;
                    right++;
                }

                for (int i = left; i <= right; i++) {
                    switches[i] = (switches[i] + 1) % 2;
                }
            }
        }

        for (int i = 0; i < N; i++){
            System.out.print(switches[i] + " ");
            if ((i + 1) % 20 == 0){
                System.out.println();
            }
        }
    }
}