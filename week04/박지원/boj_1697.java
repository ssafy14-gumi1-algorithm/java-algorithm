package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1697 {
	public static class Subin {
		int pos;
		int time;

		public Subin(int curr, int time) {
			this.pos = curr;
			this.time = time;
		}
	}

	public static int n;
	public static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(n));
	}

	public static int bfs(int start) {
		Queue<Subin> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[100_000 + 1];

		queue.offer(new Subin(start, 0));
		visited[start] = true;

		while (!queue.isEmpty()) {
			Subin curr = queue.poll();

			// 동생을 찾으면 time 반환
			if (curr.pos == k) {
				return curr.time;
			}

			int[] nexts = { curr.pos + 1, curr.pos - 1, curr.pos * 2 };

			for (int next : nexts) {
				// 범위 밖
				if (next < 0 || next > 100_000)
					continue;
				// 이미 방문
				if (visited[next])
					continue;
				
				queue.offer(new Subin(next, curr.time + 1));
				visited[next] = true;
			}

		}

		return -1;
	}
}
