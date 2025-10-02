package boj.turtle_8911;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*TODO:
- 상수
    F: 한눈금 앞으로
    B: 한눈금 뒤로
    L: 왼쪽으로 90도 회전
    R: 오른쪽으로 90도 회전
- 변수
    arr : charAt으로 받기
- 조건
    xy축에 평행한 방향으로만 이동한다.
    거북이가 지나간 영역이 직사각형을 만들지 않는 경우도 있다. 선분이면 넓이는 0 근데 뭐 길이 구해서 곱하면 될듯?

- 구하는 값
    거북이가 지나간 영역을 모두 포함할 수 있는 가장 작은 직사각형의 넓이
- 아이디어
    탐색한 지역의 각 좌표의 최대 최솟값을 구하면 됨
    F인지 L인지에 따라서 구하면 될듯
   idx라는 변수에다가 넣고 di, dx에서 한 눈금씩 움직이게 ㄱ
    왼쪽으로 90도 회전한다 -> idx가 1 줄어든다
    오른쪽으로 90도 회전한다 -> idx가 1늘어난다.
    후진용 di dj도 필요할듯?
    해당 좌표의 i,j최솟값 ㄱ
*/
public class Main {

    static char[] orders;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int N;

    static int solve(){
        int max_i_loc = 0;
        int min_i_loc = 0;
        int max_j_loc = 0;
        int min_j_loc = 0;


        int idx = 0;
        int i_loc = 0;
        int j_loc = 0;

        // order에 따라 이동하기
        for(char order: orders){
            if(order == 'F'){
                i_loc = i_loc + di[idx];
                j_loc = j_loc + dj[idx];

                max_i_loc = Math.max(max_i_loc, i_loc);
                min_i_loc = Math.min(min_i_loc, i_loc);

                max_j_loc = Math.max(max_j_loc, j_loc);
                min_j_loc = Math.min(min_j_loc, j_loc);

            } else if(order == 'B'){
                i_loc = i_loc - di[idx];
                j_loc = j_loc - dj[idx];

                max_i_loc = Math.max(max_i_loc, i_loc);
                min_i_loc = Math.min(min_i_loc, i_loc);

                max_j_loc = Math.max(max_j_loc, j_loc);
                min_j_loc = Math.min(min_j_loc, j_loc);

            } else if(order == 'L'){
                idx = (idx + 3) % 4;

            } else if(order == 'R'){
                idx = (idx + 1) % 4;
            }
        }
        int width = max_j_loc - min_j_loc;
        int height = max_i_loc - min_i_loc;
        return width * height;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int testCase=1; testCase<T+1; testCase++){
            orders = br.readLine().toCharArray();

//            st = new StringTokenizer(br.readLine());
//            String text = st.nextToken();
//            N = text.length();
//            orders = new char[N];
//            for(int i=0; i<N; i++){
//                orders[i] = text.charAt(i);
//            }

            System.out.println(solve());
        }
    }
}

