// 최대최소
//
// N×N의 행렬이 있다(1 ≤ N ≤ 250).
// 행렬의 각 성분은 250보다 작거나 같은 음이 아닌 정수이다.
// 당신에게는 K(1 ≤ K ≤ 100,000)개의 질문이 주어진다.
// 각 질문은, 주어진 행렬의 B×B 크기의 부분행렬의 최댓값과 최솟값의 차이를 묻는 질문들이다(1 ≤ B ≤ N).
// 각 질문에 대해서, 부분행렬의 가장 왼쪽 위의 위치가 주어지며, 모든 질문들은 같은 B값을 갖는다.
// 질문에 답하는 프로그램을 작성하시오.
//
// [입력]
// 첫째 줄에는 세 정수 N, B, K가 주어진다.
// 다음 N개의 줄에는 행렬이 주어진다.
// 차례로 1행, 2행, …, N행이 된다.
// 각 줄에는 N개의 정수가 주어지며, 이는 차례로 1열의 성분, 2열의 성분, …, N열의 성분이 된다.
// 다음 K개의 줄에는 질문들이 주어진다.
// 각 질문들은 두 정수 i, j로 주어진다.
// i는 부분행렬의 가장 윗쪽의 행 번호이며, j는 부분행렬의 가장 왼쪽의 열 번호이다(1 ≤ i, j ≤ N-B+1)
//
// [출력]
// K개의 줄에, 차례로 각 질문의 답변(부분행렬의 최댓값과 최솟값의 차이값)을 출력한다.
//
// [문제풀이]
// AI 수업때 들었던 뭔가뭔가랑 비슷해보임
// 근데 행렬이 250 * 250임 62,500 => 시간초과 나기 딱이네
// 근데 문제가 부분행렬에 있는 최대, 최소를 구하는 문제라 시간초과까지 갈 일은 없어보이는데?
// 아니네 10만 * 6만 5천이면 6,500,000,000 ㅋㅋ
// 행렬 안에서 바로 최대, 최소를 집어낼 수 있는 방법이 뭐가 있을까?
// 행렬 안의 숫자는 최대 250이라고 하네
// 250짜리 일차원 배열을 만들어서 뭔갈 하는 방법이 없을까?
// 아니면 배열을 B*B로 압축시킬 수 있지 않을까?
//
// 행렬을 1차원 배열로 바꿀까?
// ㄴㄴ 무조건 행렬 압축해야할듯
// 근데 행렬압축은 시간복잡도가 괜찮나?
// 일단 한번 해봐?
// 이러면 일단 질문들은 바로 O(1)로 처리할 수 있어서 시간이 확 줄어들듯

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N = 행렬, B = 부분행렬, K = 질문 갯수
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 압축시킬 배열
        int size = N - B + 1;
        int[][] pressArr = new int[size][size];

        // 배열 압축시키기
        for (int y = 0; y <= N - B; y++) {
            for (int x = 0; x <= N - B; x++) {
                int minV = 251;
                int maxV = 0;
                // B * B 행렬 최소, 최대 구하기
                for (int i = 0; i < B; i++) {
                    // 행 최솟값 / 최댓값 (arr[y+i][x .. x+B-1])
                    int rowMin = 251;
                    int rowMax = 0;
                    for (int c = x; c < x + B; c++) {
                        int v = arr[y + i][c];
                        if (v < rowMin) rowMin = v;
                        if (v > rowMax) rowMax = v;
                    }
                    if (minV > rowMin) minV = rowMin;
                    if (maxV < rowMax) maxV = rowMax;
                }
                // 압축 행렬에 최대 - 최소값 담아주기
                pressArr[y][x] = maxV - minV;
            }
        }

        StringBuilder out = new StringBuilder();
        // 질문 결과 출력
        for (int q = 0; q < K; q++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()); // 1-based
            int x = Integer.parseInt(st.nextToken()); // 1-based
            out.append(pressArr[y - 1][x - 1]).append('\n');
        }
        System.out.print(out.toString());
    }
}
