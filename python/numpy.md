# numpy

`import numpy as np` 로 관례적으로 사용되는 라이브러리

다차원 배열, 수치계산에 효율적으로 사용되지만 표준은 아니므로 코딩 테스트에는 흔히 포함되지 않는다

하지만 기타 라이브러리에는 기반이 되는 라이브러리가 된다

## column_stack(tuple)

전달한 리스트들을 2차원 배열로 변환합니다

`np.column_stack(([1, 2, 3], [4, 5, 6]))`

```
array([[1, 4],
       [2, 5],
       [3, 6]])
```

## mean(like_array, axis)

평균을 계산한다

```python
mean = np.mean(train_input, axis = 0)
```

## std(like_array, axis)

표준편차를 계산한다

```python
std = np.std(train_input, axis = 0)
```

## reshape(array, new_array)

-1의 의미 : reshpe(-1, 1)과 같이 -1을 지정하면, 해당 차원은 원래 배열의 데이터 수와 나머지 차원을 기반으로 자동으로 계산

예를 들어 `np.array([1, 2, 3, 4, 5, 6])`을 `reshape(2, 3)` 하면 2행 3열의 배열로 변환

`reshape(-1, 1)`은 모든 데이터를 열 벡터 형태로 바꿀 때 사용하고 행의 수는 자동으로 맞춰진다

`reshape(1, -1)`은 행 벡터 형태로 바꿀 때 사용한다