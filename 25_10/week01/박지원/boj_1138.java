package 박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1138 {
    public static int n;
    public static int[] record;

    public static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄
        n = Integer.parseInt(br.readLine());

        // 둘째 줄
        record = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            record[i] = Integer.parseInt(st.nextToken());
        }

        result = new int[n+1];
        Arrays.fill(result, -1);

        for (int i = 1; i <= n; i++) {
            // 내 왼쪽에 order만큼 빈칸이 있어야 함(그래야 나보다 큰 애가 그 자리에 들어갈 수 있으니까)
            int order = record[i];
            for (int pos = 1; pos <= n; pos++) {
                // 반칸을 만나면
                if(result[pos]==-1){
                    // 내 앞에 빈칸이 0개 필요-> 지금 있는 빈칸에 넣으면 됨
                    if(order==0){
                        result[pos] = i;
                        break;
                    }else{ // 앞으로 필요한 빈칸의 개수 1개 감소
                        order--;
                    }
                }

            }
            result[order] = i;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
