package AlgorithmStudy.Nov.week02.오예지;

import java.io.*;
import java.util.*;

public class boj_2304 {
    static int N;
    static int[] listH;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        N = Integer.parseInt(br.readLine());

        listH = new int[1001];
        int maxL=0, maxH=0;

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            maxL = Math.max(maxL,  L);
            maxH = Math.max(maxH,  H);

            listH[L] = H;
        }

        int temp=0;
        int idx=1;
        while (true) {
            if (temp<listH[idx]) {
                temp = listH[idx];
            }
            stack.push(temp);

            idx++;

            if (temp==maxH) break;
        }

        temp = 0;
        for(int i=maxL; i>=idx; i--) {
            temp = Math.max(temp, listH[i]);
            stack.push(temp);
        }

        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }

        System.out.println(result);


    }
}
