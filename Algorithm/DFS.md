# DFS 깊이 우선 탐색

그래프에서 깊은 부분을 우선적으로 탐색하는 알고리즘, 스택과 재귀함수를 사용하도록 한다.

|   | 0 |   1  |   2  |
|---|---|------|------|
| 0 | 0 |  7   |  5   |
| 1 | 7 |  0   |  무한 |
| 2 | 5 | 무한  |  0   |

<img src="/assets/images/TIL/2022-11-02-1.png" width="450">


## 초기화

각 노드가 연결된 정보를 리스트 자료형으로 표현하고

각 노드가 방문된 정보를 리스트 자료형으로 표현한다.

## 로직

1. 탐색 시작 노드를 스택에 삽입하고 해당 노드는 방문 처리
2. 스택의 최상단 노드에 방문하지 않은 인접 노드가 있으면 인접 노드를 스택에 넣고 방문처리 한다. 방문하지 않은 인접 노드가 없으면 스택에서 최상단 노드를 꺼낸다.
3. 2번과정을 더이상 수행할 수 없을 때까지 반복한다.

### 위키

```c++
const int MAX = 100'001;

bool visited[MAX]; // 방문 배열. visited[node] = true이면 node는 방문이 끝난 상태이다.

void dfs(const vector<int> graph[], int current) { // graph는 인접 리스트, current는 현재 노드
    visited[current] = true; // current 방문

    for(int next: graph[current]) { // current의 인접 노드를 확인한다. 이 노드를 next라고 하자.
        if(!visited[next]) { // 만일 next에 방문하지 않았다면
            dfs(graph, next); // next 방문
        }
    }
}
```

```java
public class Main {
  public static boolean[] visited = new boolean[9];
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  public static void dfs(int x) {
    visited[x] = true;
    System.out.println(x + " ");
    for(int i = 0; i < graph.get(x).size(); i++) {
      int y = graph.get(x).get(i);
      if(!visited[y]) dfs(y);
    }
  }
}
```

### 예제

```python
def dfs(graph, v, visited) :
  visited[v] = True
  print(v, end=' ')
  for i in graph[v]:
    if not visited[i]:
      dfs(graph, i, visited)

graph = [
  [],
  [2, 3, 8],
  [1, 7],
  [1, 4, 5],
  [3, 5],
  [3, 4],
  [7],
  [2, 6, 8],
  [1, 7]
]

visited = [False] * 9

dfs(graph, 1, visited)
```
---------
해당 내용은 이것이 코딩테스트이다와 위키에서 발췌되어 정리된 내용이다. 문제 시, 삭제 예정