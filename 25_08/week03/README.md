# Intro
Java 알고리즘 스터디\
3주차(08.18~08.24) dfs 문제\
08.24 일요일 오후(시간 미정)에 webex로 서로의 문제풀이 공유(필수 3문제에 대해서만 진행)

week03 하위에 본인 이름 폴더 생성 후, 문제 풀이.java 및 문제 풀이.md 파일 업로드
```
폴더 구조
week03/
├── 박지원/
│   ├── boj_1234.java
│   ├── boj_1234.md
│   ├── swea_1234.java
│   └── swea_1234.md
```

# 문제 목록
## 기초 문제
[boj_1260 DFS와 BFS](https://www.acmicpc.net/problem/1260)

## 필수 문제
2일 1문제 필수

[boj_4963 섬의 개수](https://www.acmicpc.net/problem/4963)

[boj_2468 안전 영역](https://www.acmicpc.net/problem/2468)

[boj_2583 영역 구하기](https://www.acmicpc.net/problem/2583)

## 추가 문제
[boj_17471 게리맨더링](https://www.acmicpc.net/problem/17471)

# 알고리즘 설명
## 그래프를 인접 행렬과 인접 리스트로 나타내기
### 그래프란?
객체들의 연결관계를 표현하기 위해 고안된 자료구조.\
여러 도시를 연결하는 도로망, 사람들 간의 지인 관계, 웹사이트 간의 링크 관계 등이 우리 주변에서 찾을 수 있는 연결 구조의 예시\
그래프G(V, E)와 같은 형식으로 나타내곤 한다. 무슨 뜻이냐면 G라는 그래프는 어떤 자료, 정점들의 집합을 의미하는 V, 그리고 정점들을 연결하는 간선들 E의 집합으로 구성되어 있다는 뜻.\
어렵게 말했지만 여러 정점과 정점들의 연결관계인 간선으로 이루어진 게 그래프라고 생각하면 편하다.
![그래프의 종류](https://yozm.wishket.com/media/news/2411/1__%EA%B7%B8%EB%9E%98%ED%94%84_%EA%B8%B0%EB%B3%B8_%EA%B0%9C%EB%85%90.png)
위 사진에서 번호가 적힌 동그라미가 정점, 정점 사이에 이어진 선을 간선이라고 부름(그냥 이름이니까 어렵게 생각x)\
왼쪽 위 그림의 경우 간선에 가중치가 없음(한 정점에서 다른 정점으로 가는 비용) + 1에서 3으로 갈 수도 있고, 3에서 1로도 갈 수 있음(방향이 없으니까)\
오른쪽 위 그림의 경우 화살표로 방향을 정해줌. 1에서 3으로는 갈 수 있지만, 3에서 1로는 갈 수 없음. 왼쪽 위와 마찬가지로 간선의 가중치는 없음

왼쪽 아래 그림의 경우, 가중치(비용)가 있고, 방향이 없음. 1에서 3으로 가는 데에 3의 비용이 들고, 3에서 1로 가는 데에 3의 비용이 듦.\
오른쪽 아래 그림의 경우, 가중치가 있고, 방향이 있음. 1에서 3으로 가는 데에 3의 비용이 들고, 3에서 1로는 갈 수 없음.

방향이 있는 그래프: 방향 그래프 = 유향 그래프\
방향이 없는 그래프: 양방향 그래프 = 무향 그래프

사실 그 밖에도 다양한 종류의 그래프가 있지만, 지금은 이 정도만 알아도 된다!(나도 모름)\

### 그래프 표현 방법: 인접 행렬
근데 컴퓨터한테 그래프를 어떻게 표현할 건데? 가장 간단한 방법은 이차원 배열로 두 정점 사이에 간선 정보를 표현하는 것
아까 위에 그림에서 왼쪽 위 가중치 없는 무방향 그래프를 인접 행렬로 나타내보겠다.
```java
int[][] graph = new int[9][9]; // 1번 정점부터 8번 정점까지 존재

graph[1][3] = 1;
graph[3][1] = 1; // 방향 없는 그래프는 반대쪽도 넣어준다.

graph[1][2] = 1;
graph[2][1] = 1;

graph[1][8] = 1;
graph[8][1] = 1;

// 왼쪽 서브트리 연결
graph[3][4] = 1;
graph[4][3] = 1;

graph[3][5] = 1;
graph[5][3] = 1;

graph[4][5] = 1;
graph[5][4] = 1;

// 오른쪽 서브트리 연결
graph[2][8] = 1;
graph[8][2] = 1;

graph[2][6] = 1;
graph[6][2] = 1;

graph[6][7] = 1;
graph[7][6] = 1;
/*
0 | 0 0 0 0 0 0 0 0 0
1 | 0 0 1 1 0 0 0 0 1    
2 | 0 1 0 0 0 1 0 0 1   
3 | 0 1 0 0 1 1 0 0 0   
4 | 0 0 0 1 0 1 0 0 0   
5 | 0 0 0 1 1 0 0 0 0   
6 | 0 0 1 0 0 0 0 1 0   
7 | 0 0 0 0 0 0 1 0 0   
8 | 0 1 1 0 0 0 0 0 0   
 */
```
방향 그래프나 간선 그래프의 경우 아래와 같이 적어주면 된다.\
`graph[from][to] = weight`\
보통 입력으로 연결되는 두 간선이 주어지므로, 아래와 같이 코드를 작성하여 간선을 연결해준다.

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

void main() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int[][] graph = new int[101][101]; // 1~100번 정점까지 존재한다고 가정
    
    for (int i = 0; i < e; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine);
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        graph[from][to] = weight;
        // 양방향 그래프라면 아래 줄도 추가
        // graph[to][from] = weight;
    }
}
```
### 그래프 표현 방법: 인접 리스트
아까 위에 인접 행렬로 그렸던 그래프를 보자. 간선이 없는 부분은 굳이 표시할 필요가 없을 것 같다 = 0으로 표시된 부분은 사실상 전부 낭비되고 있다.
``` declarative
0 | 0 0 0 0 0 0 0 0 0
1 | 0 0 1 1 0 0 0 0 1    
2 | 0 1 0 0 0 1 0 0 1   
3 | 0 1 0 0 1 1 0 0 0   
4 | 0 0 0 1 0 1 0 0 0   
5 | 0 0 0 1 1 0 0 0 0   
6 | 0 0 1 0 0 0 0 1 0   
7 | 0 0 0 0 0 0 1 0 0   
8 | 0 1 1 0 0 0 0 0 0   
```
자바의 ArrayList, C++의 vector 자료구조를 이용해서 각 정점에 연결된 정점 번호만 담을 수 있다면 어떨까? 정확히 정점 개수+간선 개수만큼의 메모리만 사용될 것이다.\
![그래프를 인접리스트로 표현하기](https://blog.kakaocdn.net/dna/bDJIQq/btrwon4S9BB/AAAAAAAAAAAAAAAAAAAAABcHNGz6eSpyqubmIEJPNfH_XwXVXvBzFG8F_wTKCida/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1756652399&allow_ip=&allow_referer=&signature=wroPhlsTPZOvi%2F%2BMPTt3R4tbcj0%3D)

인접리스트는 정적 배열이 아니라 동적 배열의 형태로 연결관계를 표현한다.
위 그림(무방향 그래프)의 경우 인접리스트에 넣으면 아래와 같은 결과가 나올 것이다.
```declarative
0 | []
1 | [2, 3, 4]
2 | [1, 3, 5]
3 | [1, 2, 4]
4 | [1, 3, 5]
5 | [2, 4]
```
인접 리스트 코드는 아래와 같이 구현한다.
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

void main() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    List<Integer>[] graph = new ArrayList[101]; // 1~100번 정점까지 존재한다고 가정
    // graph의 각 요소를 초기화해줘야 함
    for (int i = 0; i < 101; i++) {
        graph[i] = new ArrayList<>();
    }

    // e는 간선의 갯수(보통 위에서 정점 갯수, 간선 갯수를 입력으로 받을 것이다.)
    for (int i = 0; i < e; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine);
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        graph[from].add(to);
        // 양방향의 경우 아래 코드도 추가
        // graph[to].add(from);
    }
}
```
이렇게 하면 위의 인접리스트 그림 처럼 1번 정점에 연결된 간선 목록 `graph[1] = [2, 3, 4]` 이런 식으로 관리할 수 있다.

엥 그럼 가중치가 있으면 어떻게 함??? 여러 방법이 있을 거 같은데, 자바에서는 class를 이용하는 게 가장 무난한 방법 같다.
```java
public class adjList{
    public static class Edge{
        int to;
        int weight;
        Edge(int to, int weight){
            this.to = to;
            this. weight = weight;
        }
    }

    static void main(String[] args) {
        List<Edge>[] graph = new ArrayList[101]; // 1~100번 정점까지 존재한다고 가정
        // graph의 각 요소를 초기화해줘야 함
        for (int i = 0; i < 101; i++) {
            graph[i] = new ArrayList<>();
        }

        // e는 간선의 갯수(보통 위에서 정점 갯수, 간선 갯수를 입력으로 받을 것이다.)
        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken);
            graph[from].add(new Edge(to, weight));
            // 양방향의 경우 아래 코드도 추가
            // graph[to].add(new Edge(from, weight));
        }
    }
}
```

### 그래서 뭐 쓰라는 거임?
간선의 갯수가 적으면 인접 리스트를 쓰는 게 낫고, 간선의 갯수가 많으면 인접 행렬을 쓰는 게 나은 거 같다.
이걸 조금 더 고급스럽게 말하면 희소 그래프는 인접 리스트, 밀집 그래프는 인접 행렬을 사용하는 것이 유리하다고 표현할 수 있다.
- 희소 그래프: 간선의 수가 정점 갯수에 비해 훨씬 적은 그래프(인접 행렬이 거의 0으로 채워지는 그래프)
- 밀집 그래프: 간선의 수가 거의 정점 갯수와 동일(인접 행렬이 거의 1로 채워지는 그래프)

인접 행렬을 사용하면 배열 index로 접근하기 때문에 속도가 빠르다(O(1)). 대신 노드 수가 많아지고, 연결 정보가 적은 경우에는 메모리 낭비가 심하다.
보통 int[][]로 나타내는데, 노드가 100_000개라고 생각해보자. int 하나가 4byte이므로 100_000 * 100_000 = $10^{10}$ = 100억 개의 요소를 가짐.
따라서 해당 배열은 100억 * 4 byte = 400억 byte = 40GB의 메모리를 차지하게 된다.\
그런데 100억 개의 노드가 있는데, 간선이 1개 뿐이라고 해보자. OMG... 1개 간선을 표현하려고 메모리 40기가를 쓰게 되는 것.\
대신 간선의 갯수가 $n^2(n은 정점의 개수)$에 가깝다면, 낭비되는 메모리 없이 두 정점의 연결정보를 O(1)에 가져올 수 있으므로 유리함.

인접 리스트의 경우 연결 정보를 저장하기 때문에 메모리 낭비가 없다. 대신 1과 2가 연결되어 있는지 보기 위해 노드1에 연결된 정점들을 다 돌아봐야 한다. 1에 모든 정점이 연결되어 있는 경우 시간 복잡도 O(N)이 나올 수 있음\
또한 간선의 갯수가 많은 경우 인접행렬과 거의 비슷한 메모리를 차지하게 된다. 메모리를 비슷하게 차지하는데, 최악의 접근 시간이 O(n)이면 인접리스트의 메리트가 없음.

장황하게 말했지만, ~A형 수준까지는 인접 리스트를 사용하면 OK인 것으로 보임.\
B형에는 최적화가 들어가야 하기 때문에 자료구조를 신중하게 선택해야 한다고 들었다.

아래 이미지는 바킹독의 [실전 알고리즘] 0x18강 - 그래프 부분에서 가져왔다!
![바킹독의 실전 알고리즘 - 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2FEtDjb%2FbtrmykifAFX%2FAAAAAAAAAAAAAAAAAAAAAM9qmEARM4UXrDWnts4HXN7FqnInJwXFzGWGsUKroax5%2Fimg.png%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1756652399%26allow_ip%3D%26allow_referer%3D%26signature%3DLWFf%252FrqicoThl%252FrYHYIbetud93Y%253D)

### (+) 트리란?
이번주 문제 풀 때는 크게 중요한 개념은 아니므로 눈에 익혀만 둡시다.\
상하 구조로 된 계층형 그래프가 트리라고 할 수 있음.\
그래프에 부모/자식 관계가 들어간 특수한 형태의 그래프\
![트리 개념](https://i.namu.wiki/i/8pViDtKiYxEmcz1zj2WHZEpLHeu4q4n1bAjOOTvA4rLde3d-miR4lbCeFRjhzuTV1SLW5vFdg81Q6vb6fm1I9Q.webp)
트리에서는 정점을 노드라고 부르기도 함. 계층이 존재하기 때문에 레벨(깊이)가 존재\
맨 밑에 자식이 없는 노드를 리프노드라고 하고, 루트 노드는 제일 조상인 노드를 의미\
서브트리는 전체 트리에서 어떤 노드를 루트노드로 하는 부분집합 개념?이라고 보면 된다.

## 그래프 탐색 기법: dfs
그래프의 모든 정점들을 특정 순서에 따라 방문하는 알고리즘들을 그래프 탐색(search) 알고리즘이라고 한다.
탐색 알고리즘 중 가장 유명한 것이 깊이 우선 탐색(dfs)와 너비 우선 탐색(bfs)이다.

이번주 주제인 깊이 우선 탐색은 말 그대로 깊은 순서로 탐색하는 방법이다. 시작 노드에서 최대한 깊이 들어간 후, 더 이상 갈 곳이 없을 때 이전 노드로 돌아와 다른 경로를 탐색하는 방식\
재귀나 스택을 이용하여 구현할 수 있으나(함수를 호출하면 메모리의 stack에 쌓임), 직관성 때문에 보통 재귀로 구현이 된다.
![깊이우선탐색과 너비 우선탐색](https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSU1bbOZLAHdOcYrOZo3zzge72u-jogTWthUrhA8cFsIGpCods72Ar1Gvv4I3ZgsTj5YVh3Hg)

출발지에서 도착지로 가는 여러 경로를 모두 찾아보는 데에 좋음(처음 도착지에 도달한 경로가 최단경로임이 보장되지 않음)

## dfs 기초코드
```java
void dfs(int x) {
    // 탐색한 정점은 탐색하지 않는다
    if (visited[x] == true) {
        return;
    }
    
    // 방문 체크
    visited[x] = true;
    // x와 연결된 정점 방문
    for (int y : x와 인접한 정점) {
        if (visited[y] == false) {
            dfs(y);
        }
    }
}
```
## 인접 행렬
```java
public class adjList{
    public static int[][] graph;
    public static boolean[] visited;

    static void main(String[] args) {
        graph = new int[101][101]; // 1~100번 정점까지 존재한다고 가정
        visited = new boolean[101]; // 정점 갯수만큼 배열 할당
        
        // e는 간선의 갯수(보통 위에서 정점 갯수, 간선 갯수를 입력으로 받을 것이다.)
        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from][to] = weight;
            // 양방향 그래프라면 아래 줄도 추가
            // graph[to][from] = weight;
        }
        dfs(0);
    }

    static void dfs(int x){
        // 종료 조건
        if(visited[x]){
            return;
        }

        // 방문 체크
        visited[x] = true;

        // 할 일 있으면 하기(여기서는 그냥 방문한 정점 출력)
        System.out.print(x + " ");

        // x와 연결된 정점 탐색(101개의 노드를 살펴보면서 연결되었는지 확인)
        for (int i=0; i < 101; i++) {
            // 방문한 정점은 다시 방문하지 않음
            if(visited[i]) continue;

            // 간선이 있는 정점이면 방문
            if(graph[x][i] != 0){
                dfs(i); // i가 정점 번호고, graph[x][i]는 가중치 정보이므로 i정점을 방문해야 한다.
            }
        }

    }
}
```
## 인접 리스트
```java
public class adjList{
    public static class Edge{
        int to;
        int weight;
        Edge(int to, int weight){
            this.to = to;
            this. weight = weight;
        }
    }

    public static List<Edge>[] graph;
    public static boolean[] visited;
    
    static void main(String[] args) {
        graph = new ArrayList[101]; // 1~100번 정점까지 존재한다고 가정
        // graph의 각 요소를 초기화해줘야 함
        for (int i = 0; i < 101; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[101]; // 정점 갯수만큼 배열 할당

        // e는 간선의 갯수(보통 위에서 정점 갯수, 간선 갯수를 입력으로 받을 것이다.)
        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken);
            graph[from].add(new Edge(to, weight));
            // 양방향의 경우 아래 코드도 추가
            // graph[to].add(new Edge(from, weight));
        }
        dfs(0);
    }
    
    static void dfs(int x){
        // 종료 조건
        if(visited[x]){
            return;
        }
        
        // 방문 체크
        visited[x] = true;
        
        // 할 일 있으면 하기(여기서는 그냥 방문한 정점 출력)
        System.out.print(x + " ");
        
        // x와 연결된 정점 탐색
        for (Edge edge : graph[x]) {
            // 방문한 정점은 다시 방문하지 않음
            if(visited[edge.to]) continue;
            
            // 방문하지 않은 정점은 방문하기
            dfs(edge.to);
        }
        
    }
}
```