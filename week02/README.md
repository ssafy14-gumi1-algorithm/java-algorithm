# Intro
Java 알고리즘 스터디\
2주차(08.11~08.17) 재귀 문제\
08.17 일요일 오후(시간 미정)에 webex로 서로의 문제풀이 공유(필수 3문제에 대해서만 진행)

week02 하위에 본인 이름 폴더 생성 후, 문제 풀이.java 및 문제 풀이.md 파일 업로드
```
폴더 구조
week02/
├── 박지원/
│   ├── boj_1234.java
│   ├── boj_1234.md
│   ├── swea_1234.java
│   └── swea_1234.md
```

# 문제 목록
dp, for문으로 풀 수도 있는데, 재귀를 이용해서 풀어주세요!!

## 기초 문제
[boj_27433 팩토리얼 2](https://www.acmicpc.net/problem/27433)

[boj_10870 피보나치 수 5](https://www.acmicpc.net/problem/10870)

## 필수 문제
2일 1문제 필수

[boj_9095 1, 2, 3 더하기](https://www.acmicpc.net/problem/9095)

[boj_1759 암호 만들기](https://www.acmicpc.net/problem/1759)

[boj_2992 크면서 작은 수](https://www.acmicpc.net/problem/2992)

## 추가 문제
[boj_2630 색종이 만들기](https://www.acmicpc.net/problem/2630)

# 알고리즘 설명
재귀 함수는 스스로를 호출하는 함수.\
큰 문제를 작은 문제로 쪼개어 해결할 때 도움이 됨. 팩토리얼, 피보나치, 하노이탑 등 수학적인 문제를 재귀로 해결 가능.\
stack이나 반복문을 이용해서도 구현할 수 있으나, 재귀 함수로 작성하는 것이 직관적인 경우가 많음(아직은 안 와닿을 수 있음).\
dfs, 백트래킹 알고리즘(재귀+가지치기)의 기초가 됨

스스로를 호출하는 함수이기때문에 아래의 1부터 n까지의 합, 팩토리얼 정도는 직접 재귀를 그려보는 게 도움이 될 수도 있다.\
개인적으로는 재귀 호출 방식을 그렇게 익히고, 어려운 문제는 그냥 규칙 정도로 받아들이는 게 좋다고 생각한다(너무 깊게 들어가면 스스로도 헷갈리기 시작함). 재귀가 어딘가 이상하다면 디버깅 기능을 적극 활용해보자...

재귀함수는 스스로를 호출하기 때문에 무한 반복에 갇힐 위험이 있다. 따라서 반드시 종료 조건을 명시해야 한다.

## 재귀 함수의 구조
1. 종료 조건(=기저 조건, base case)
2. 재귀 호출

### 예시
제일 간단한 건 1부터 n까지의 수 더하기!
```java
public static int recursion(int n){
    // 종료 조건
    if (n == 1) return 1;
    
    // 재귀 호출
    return n + recursion(n-1);
}
```
재귀를 이용해서 순열($nPr$)만들기
```java
// cnt: 현재까지 뽑은 원소의 수
private static void permutation(int cnt) {
    
    // 종료 조건
    if (cnt == R) {
        // 하나의 경우의 수에 대한 로직 처리
        System.out.println(Arrays.toString(numbers)); // 뽑은 숫자 배열 출력
        totalCnt++; // 경우의 수가 몇 개인지 세는 부분
        return;
    }
    
    // 유도부분
    for (int i = 0; i < N; i++) {  // i: 원소, 가능한 모든 수 시도
        
        // 원소 선택 여부 확인(중복 순열이 아니라서 i를 뽑았는지 체크 필요)
        if (isSelected[i]) {
            continue;
        }
        
        numbers[cnt] = input[i];  // 원소 뽑기
        isSelected[i] = true;  // 뽑은 숫자 체크
        
        permutation(cnt + 1);  // 다음 숫자 뽑으러 가기
        isSelected[i] = false;  // 리턴하고 돌아 왔을 때 뽑지 않은 상태로 되돌림
    }
}
```
재귀를 이용해서 조합($nCr$)만들기
```java
// cnt: 직전까지 뽑은 조합에 포함된 원소 개수
// start: 뽑을 원소의 인덱스 시작 번호
private static void combination(int cnt, int start) {
    
    // 종료 조건(r개 뽑았으면 종료)
    if (cnt == R) {
        System.out.println(Arrays.toString(numbers)); // 뽑은 숫자 배열 출력
        totalCnt++; // 경우의 수가 몇 개인지 세는 부분
        return;
    }
    
    // 재귀 호출
    for (int i = start; i < N; i++) {
        
        // 원소 뽑기
        numbers[cnt] = input[i];
        
        // 다음 원소 뽑으러 가기
        combination(cnt + 1, i + 1);
    }
}
```