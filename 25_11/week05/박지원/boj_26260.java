package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_26260 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < N; i++) {
            if (arr[i] == -1) {
                arr[i] = x;
                break;
            }
        }

        // 오름차순 정렬하면 중위 순회 결과라고 함
        Arrays.sort(arr);

        // 중위 순회 -> 후위 순회로 변경
        postorder(arr, 0, N - 1);

        System.out.println(sb);
    }

    static void postorder(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int mid = (left + right) / 2; // 가운데 원소가 루트

        // 왼쪽
        postorder(arr, left, mid - 1);
        // 오른쪽
        postorder(arr, mid + 1, right);
        // 루트
        sb.append(arr[mid]).append(' ');
    }
}
