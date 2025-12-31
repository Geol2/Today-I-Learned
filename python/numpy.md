# numpy

`import numpy as np` 로 관례적으로 사용되는 라이브러리

다차원 배열, 수치계산에 효율적으로 사용되지만 표준은 아니므로 코딩 테스트에는 흔히 포함되지 않는다

하지만 기타 라이브러리에는 기반이 되는 라이브러리가 된다

## column_stack

전달한 리스트들을 2차원 배열로 변환합니다

`np.column_stack(([1, 2, 3], [4, 5, 6]))`

```
array([[1, 4],
       [2, 5],
       [3, 6]])
```

## mean

평균을 계산한다

```python
mean = np.mean(train_input, axis = 0)
```

## std

표준편차를 계산한다

```python
std = np.std(train_input, axis = 0)
```