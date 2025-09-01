# Intro
Java 알고리즘 스터디\
1주차(08.03~08.10) 시뮬레이션 문제\
08.10 일요일 오후(시간 미정)에 webex로 서로의 문제풀이 공유(필수 3문제에 대해서만 진행)

week01 하위에 본인 이름 폴더 생성 후, 문제 풀이.java 및 문제 풀이.md 파일 업로드
```
폴더 구조
week1/
├── 박지원/
│   ├── boj_1234.java
│   ├── boj_1234.md
│   ├── swea_1234.java
│   └── swea_1234.md
```

# 문제 목록
## 필수 문제
2일 1문제 필수

[swea 12712 파리퇴치3](https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AXuARWAqDkQDFARa)

[boj 8911 거북이](https://www.acmicpc.net/problem/8911)

[boj 5212 지구 온난화](https://www.acmicpc.net/problem/5212)


## 선택 문제
1일 1문제를 풀고 싶은 사람 혹은 ***높은 난이도***를 원하는 사람을 위한 추가 문제 4개

[boj 7576 토마토](https://www.acmicpc.net/problem/7576)

[boj 14503 로봇 청소기](https://www.acmicpc.net/problem/14503)

[boj 14502 연구소](https://www.acmicpc.net/problem/14502)

[boj 17143 낚시왕](https://www.acmicpc.net/problem/17143)

# 알고리즘 설명
시뮬레이션 문제는 말 그래도 다른 알고리즘 테크닉 없이 문제를 주어진 조건과 상황에 맞게 "구현"하는 유형.\
쉬운 난이도는 배열을 이용해 구현하는 문제가 나오고, 조금 더 난이도를 높이면 dx, dy를 이용하는 문제가 나오는 것 같다.\
dx, dy를 이용하는 문제는 문제에서 상하좌우 혹은 인접한 칸(8방향)을 탐색하라고 나온다. dfs, bfs와 자주 연계됨.
## dx, dy 문제
<img width="920" height="394" alt="image" src="https://github.com/user-attachments/assets/eeb16a65-8843-4faa-befa-273b40da1aa3" />

탐색할 때는 아래와 같은 형식으로 코드가 작성됨 final로 한 이유 -> dx, dy는 내용물이 바뀌면 안 돼서
``` java
class Main {

    // 동 서 남 북
    public static final int[] dx = {0, 0, -1, 1};
    public static final int[] dy = {1, -1, 0, 0};
    // 8방향일 경우
    // public static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    // public static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        int x = 2;
        int y = 2;

        for (int dir=0; dir<4; dir++){
            // nx는 next_x, ny는 next_y
            int nx = x+dx[dir];
            int ny = y+dy[dir];

            System.out.println(STR."(\{nx}, \{ny})");
        }
    }
}
```
