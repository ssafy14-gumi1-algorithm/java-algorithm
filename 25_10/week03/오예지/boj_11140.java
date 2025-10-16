package JAVA_AlgorithmStudy.Oct.week03.오예지;

import java.io.*;

public class boj_11140 {
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			String word = br.readLine();
			
			sb.append(checkWord(word)).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static int checkWord(String word) {
	    // 이미 "lol"이 포함되어 있으면
	    if (word.contains("lol")) return 0;

	    // 삽입/교체 1번으로 만들 수 있는 경우
	    if (word.contains("lo") || word.contains("ol") || word.contains("ll")) return 1;

	    // 세 글자씩 봤을 때, 'l'이면 가운데를 'o'로 교체 1번
	    for (int i = 0; i + 2 < word.length(); i++) {
	        if (word.charAt(i) == 'l' && word.charAt(i + 2) == 'l') return 1;
	    }

	    // 글자 중 'l' 또는 'o'가 하나라도 있으면 2
	    boolean hasL = false, hasO = false;
	    for (int i = 0; i < word.length(); i++) {
	        char c = word.charAt(i);
	        if (c == 'l') hasL = true;
	        else if (c == 'o') hasO = true;
	    }
	    if (hasL || hasO) return 2;

	    // 'l'도 'o'도 없다면 최소 3
	    return 3;
	}

}
