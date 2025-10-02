package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14888 {

    static int N;
    static int[] numList;
    static int[] operCnt;
    static int minV = Integer.MAX_VALUE;
    static int maxV = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numList = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i < N; i++){
            numList[i] = Integer.parseInt(st.nextToken());
        }

        operCnt = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++){
            operCnt[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, numList[0]);

        System.out.println(maxV);
        System.out.println(minV);

    }

    static void dfs(int idx, int result){
        if (idx == N){
            minV = Math.min(minV, result);
            maxV = Math.max(maxV, result);
            return;
        }

        for (int i = 0; i < 4; i++){
            if (operCnt[i] > 0) {
                operCnt[i]--;

                if (i == 0){
                    dfs(idx+1, result+numList[idx]);
                }
                else if (i == 1){
                    dfs(idx+1, result-numList[idx]);
                }
                else if (i == 2){
                    dfs(idx+1, result*numList[idx]);
                }
                else {
                    // 파이썬으로 풀 때는 이 부분이 필요해서 넣었는데, 자바에서 필요한지는 모르겠음
                    if (result < 0){
                        dfs(idx+1, -(-result / numList[idx]));
                    }else{
                        dfs(idx+1, result/numList[idx]);
                    }
                }

                operCnt[i]++;    // 원상복구
            }
        }
    }
}
