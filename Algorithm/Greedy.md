# 그리디(탐욕법)

- 현재 상황에서 지금 당장 좋은 것만 고르는 방법 중 하나.
- 탐욕법이라고도 하며 욕심쟁이 알고리즘 이라고도 한다.
- 문제에서 "가장 큰 순서대로...", "가장 작은 순서대로..."
- 이 알고리즘은 단순 특정 문제를 만났을 때, 현재 상황에서 가장 좋아 보이는 것만 선택해도 문제를 풀 수 있는지 파악해야 하는 문제로 문제를 풀기 위한 최소한의 아이디어를 떠올릴 수 있는 능력을 요구한다.

- 최소한의 아이디어를 떠올리고 이것이 정당한지 검토할 수 있어야 답을 도출할 수 있다.

----- 

## 예제 4) 1이 될 때까지

  - 거스름돈
  - 당신은 음식점의 계산을 도와주는 점원, 카운터에는 거스름돈으로 사용할 500원, 100원, 50원, 10원짜리 동전이 무한히 존재한다고 가정한다. 손님에게 거슬러 줘야할 동전의 최소 개수를 구하는 문제. 단, 거슬러 줘야 할 돈 N은 항상 10의 배수이다.

- 해설
  - 가장 큰 화폐 단위부터 돈을 거슬러 주는 것.
  - 500원부터 거슬러 줄 수 있으면 하고 그 다음 100월, 50원, 10원 순으로 차례로 거슬러 주면 된다.

```python
n = 1260
count = 0

coin_types = [500, 100, 50, 10]

for coin in coin_types:
    count += n // coin
    n %= coin

print(count)
```

`//`은 소수점 이하의 자리 수는 버린다.<br>

---

1. 첫 번째 `coin`은 500 이므로 `n // coin` 은 1260원을 500원으로 나눔.<br>
2. `count`는 `count +=`로 2개 <br>
3. `n = n % coin`의 `n`은 남은 260원

---

1. 두 번째 `coin`은 100 이므로 `n // coin` 은 260원을 100원으로 나눔.
2. `count`는 2개이므로 `count +=`로 4개
3. `n = n % coin`의 `n`은 60원

---

1. 세 번째 `coin`은 50 이므로 `n // coin` 은 60원을 50원으로 나눔.
2. `count`는 1개이므로 `count +=`로 5개
3. `n = n % coin`의 `n`은 10원

---

1. 네 번째 `coin`은 10 이므로 `n // coin` 은 10원을 10으로 나눔.
2. `count`는 1개이므로 `count +=`로 6개
3. `n = n % coin`의 `n`은 0원

---

4. for문을 빠져나와서 `print(count)` 실행

---

- 예제

  - 어떤 수 N이 1일 될 때까지 두 과정 중 하나를 선택해서 수행한다.

  1. N에서 1을 뺀다.
  2. N을 K로 나눈다.

  - 두 번째 과정은 N이 K로 나누어 떨어지는 경우에만 선택한다.
  - 예를 들어 N = 17, K = 4라고 가정하면, 17/4는 나누어 떨어지지 않으니까 1번 과정을 수행한다. 그러면 16/4는 나누어 떨어지므로 2번 과정을 수행할 수 있다. 4/4는 1이 되었으므로 총 3번 수행하게 되므로 3을 출력하고 프로그램을 종료한다.

- 과정

```python
n, k = map(int, input().split())

result = 0
while n >= k:
    while n % k != 0:
        n -= 1
        result +=  1
    n //= k
    result += 1

while n > 1:
    n -= 1
    result += 1

print(result);

```

-----

## 예제 2 ) 큰 수의 법칙

- 큰 수의 법칙
  - 2, 4, 5, 4, 6으로 이루어진 배열이 있을 때, M이 8, K가 3이라고 가정한다.
  - 그렇다면, 6 + 6 + 6 +5 + 6 + 6 + 6 + 5 = 46.
  - 3, 4, 3, 4, 3으로 이루어진 배열이 있을 때, M이 7, K가 2라고 가정한다.
  - 그렇다면, 4 + 4 + 4 + 4 + 4 + 4 = 28.
  - 2, 4, 5, 4, 6으로 이루어진 배열이 있을 때, M이 8, K가 3이라고 가정한다.
  - 그렇다면, 6 + 6 + 6 + 5 + 6 + 6 + 6 + 5 = 46. 

```python
n, m, k = map(int, input().split())
data = list(map(int, input().split()))

data.sort()
first = data[n-1]
second = data[n-2]

result = 0

while True:
    for i in range(k):  # 가장 큰 수를 K번 더하기
        if m == 0:      # m이 0이라면 반복문 탈출
            break;
        result += first
        m -= 1          # 더할 때마다 1씩 빼기
    if m == 0:          # m이 0이라면 반복문 탈출
        break
    result += second    # 두 번째로 큰 수를 한 번 더하기
    m -= 1              # 더할 때마다 1씩 빼기

print(result)
```

---
1. 첫 째 이 문제를 해결하기 위해서 일단 입력값 중에서 `가장 큰 수`와 `두 번째로 큰 수`만 저장한다.
2. 가장 큰 수를 `k`번 더하고 두 번째로 큰 수를 한 번 더하는 연산을 반복하면 된다.
<br>
<br>
<br>
<br>

----

## 예제 3) 숫자 카드 게임
- 여러 개의 숫자 카드 중에서 가장 높은 숫자가 쓰인 카드 한 장을 뽑는 게임.
  1. 숫자가 쓰인 카드들이 N X M 형태로 놓여 있다. 이때 N은 행의 개수를 의미하며, M은 열의 개수를 의미한다.
  2. 먼저 뽑고자 하는 카드가 포함되어 있는 행을 선택한다.
  3. 그다음 선택된 행에 포함된 카드들 중 가장 숫자가 낮은 카드를 뽑아야 한다.
  4. 따라서 처음에 카드를 골라낼 행을 선택할 때, 이후에 해당 행에서 가장 숫자가 낮은 카드를 뽑을 것을 고려하여 최종적으로 가장 높은 숫자의 카드를 뽑을 수 있도록 전략을 세워야 한다.

- 입력 예제 1
```
  3 3
  3 1 2
  4 1 4
  2 2 2
```
- 결과 예제 1
```
  2
```

- 입력 예제 2
```
  2 4
  7 3 1 8
  3 3 3 4
```
- 결과 예제 2
```
  3
```

```python
n, m = map(int, input().split())

result = 0
for i range(n):                           # 각각 한 줄 씩 확인하는 for 문이다.
  data = list(map(int, input().split()))
  min_value = min(data)                   # 현재 줄에서 '가장 작은 수' 찾기
  result = max(result, min_value)         # 가장 작은 수 중에서 큰수를 찾아 대입

print(result)
```
- 가장 난이도가 낮게 느껴졌다.