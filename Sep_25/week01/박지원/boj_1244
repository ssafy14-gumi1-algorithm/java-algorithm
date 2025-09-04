package com.ssafy.live12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1244 {

	public static final int BOY = 1;
	public static final int GIRL = 2;

	public static int n;
	public static int[] switches;

	public static int s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// [input]
		n = Integer.parseInt(br.readLine());

		switches = new int[n + 1]; // 1-based
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}

		s = Integer.parseInt(br.readLine());
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 성별
			int num = Integer.parseInt(st.nextToken()); // 받은 스위치 번호
			changeSwitch(gender, num);
		}

		// 스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력
		for (int i = 1; i <= n; i++) {
			sb.append(switches[i]).append(' ');
			if(i%20==0)sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void changeSwitch(int gender, int num) {
		if (gender == BOY) {
			// 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
			for (int i = 1; i <= n; i++) {
				if (i % num == 0) {
					switches[i] = (switches[i] == 0) ? 1 : 0;
				}
			}
		} else {
			// 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간
			// 에 속한 스위치의 상태를 모두 바꾼다.
			// 일단 자기자신을 바꾸고
			switches[num] = (switches[num] == 0) ? 1 : 0;

			// 대칭인 구간을 양옆으로 확장하면서 
			int left = num - 1;
			int right = num + 1;
			while ((left > 0 && right <= n) && switches[left] == switches[right]) {
				switches[left] = (switches[left] == 0) ? 1 : 0;
				switches[right] = (switches[right] == 0) ? 1 : 0;
				left -= 1;
				right += 1;
			}
		}
	}
}
