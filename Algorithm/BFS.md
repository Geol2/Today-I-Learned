# BFS

트리에서 너비 우선 탐색을 하는데 가까운 노드부터 탐색하는 알고리즘, 큐를 이용하면 된다.

1. 탐색 시작 노드를 큐에 삽입하고 방문처리한다.
2. 큐에서 노드르 꺼내 해당 노드의 인접 노드 중에서 방문하지 않은 노드를 모두 큐에 삽입하고 방문 처리를 한다.
3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Algorithm/images/tree.png" />

위 그림을 1부터 시작한다면, 1을 큐에 넣고 방문처리를 한다. (큐 : 1)

큐에서 1을 꺼내고 인접 노드 2, 3, 8을 모두 큐에 삽입하고 방문처리를 한다. (큐 : 2 3 8)

큐에서 2를 꺼내고 7를 큐에 삽입하고 방문처리를 한다. (큐 : 3 8 7)

큐에서 3을 꺼내고 4, 5를 큐에 삽입하고 방문처리를 한다. (큐 : 8 7 4 5)

큐에서 8을 꺼내고 방문하지 않은 노드가 없으므로 넘어간다. (큐 : 7 4 5)

큐에서 7을 꺼내고 6을 큐에 삽입하고 방문처리를 한다. (큐 : 4 5 6)

큐에서 4를 꺼내고 방문하지 않은 노드가 없으므로 넘어간다. (큐 : 5 6)

큐에서 5를 꺼내고 방문하지 않은 노드가 없으므로 넘어간다. (큐 : 6)

마지막으로 큐에서 6을 꺼내고 방문하지 않은 노드가 없으므로 끝낸다.

꺼낸 순서대로 1 &rightarrow; 2 &rightarrow; 3 &rightarrow; 8 &rightarrow; 7 &rightarrow; 4 &rightarrow; 5 &rightarrow; 6 순으로 된다.

```python
def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True
    while queue:
        v = queue.popleft()
        print(v, end=' ')
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True

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

bfs(graph, 1, visited)
```