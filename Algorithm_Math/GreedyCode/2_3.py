n, m = map(int, input().split())

result = 0
for i range(n):                           # 각각 한 줄 씩 확인하는 for 문이다.
  data = list(map(int, input().split()))
  min_value = min(data)                   # 현재 줄에서 '가장 작은 수' 찾기
  result = max(result, min_value)         # 가장 작은 수 중에서 큰수를 찾아 대입

print(result)