import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2304 {

    public static int n;
    public static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        heights = new int[1000 + 1];

        int maxHeight = 0;
        int lMaxIdx = -1, rMaxIdx = -1; // 최댓값의 좌/우 인덱스
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            heights[l] = h;

            if (h > maxHeight) {
                maxHeight = h;
                lMaxIdx = l;
                rMaxIdx = l;
            } else if (h == maxHeight) {
                rMaxIdx = l; // 같은 높이 최댓값이 여러 개면 우측 끝을 계속 갱신
            }
        }

        // rMaxIdx가 설정되지 않았으면 최대값 1개 -> 왼쪽 인덱스로 맞추기
        if (rMaxIdx == -1)
            rMaxIdx = lMaxIdx;

        int sum = 0;
        int currMax = 0;

        // 왼쪽 → lMaxIdx 직전까지 누적
        for (int i = 0; i < lMaxIdx; i++) {
            if (currMax < heights[i]) {
                currMax = heights[i];
            }
            sum += currMax;
        }

        // 오른쪽 → rMaxIdx 직후부터 끝까지 누적
        currMax = 0;
        for (int i = 1000; i > rMaxIdx; i--) {
            if (currMax < heights[i]) {
                currMax = heights[i];
            }
            sum += currMax;
        }

        // 최댓값 구간(양 끝 포함) 면적 더하기
        sum += (rMaxIdx - lMaxIdx + 1) * maxHeight;

        System.out.println(sum);
    }
}