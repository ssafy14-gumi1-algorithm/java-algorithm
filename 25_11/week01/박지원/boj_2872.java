package week01.박지원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2872 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        for(int i =0; i<n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int expected = n;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == expected) expected--;
        }
        System.out.println(expected);
    }

}