package week02.안지원

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class boj_1759 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String list_input = br.readLine();
        String[] strList = list_input.split(" ");

        // 받은 input 리스트 정렬
        Arrays.sort(strList);

        ArrayList<String> targList = new ArrayList<>();

        for(int i = 0; i < 1<<C; i++){
            // 배열 하나 만들기 참 귀찮네..
            ArrayList<String> now = new ArrayList<>();
            for(int j = 0; j < C; j++){
                // 자바는 왜 0, 1 암묵적 형변환을 안하는건가
                if((i & (1<<j)) != 0){
                    now.add(strList[j]);
                }
            }
            if(now.size() == L){
                targList.add(String.join("", now));
            }
        }

        // 자음 모음 체크한 배열 담기
        ArrayList<String> result = new ArrayList<>();

        for(int i = 0; i < targList.size(); i++){
            String str = targList.get(i);
            // 모음 갯수
            int ctn = 0;
            for(int j = 0; j < str.length(); j++){
                if(str.charAt(j) == 'a' || str.charAt(j) == 'i' || str.charAt(j) == 'o' || str.charAt(j) == 'u' || str.charAt(j) == 'e'){
                    ctn += 1;
                }
            }
            if(ctn >= 1 && str.length() - ctn >= 2){
                result.add(str);
            }
        }

        // 최종 정렬
        result.sort(null);

        // 결과 출력
        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
        }
    }
}