# input
- input([prompt])은 사용자 입력을 받는 함수이다. 매개변수로 문자열을 주면 다음 세 번째 예에서 볼 수 있듯이 그 문자열은 프롬프트가 된다.

- [ ] 기호는 괄호 안의 내용을 생략할 수 있다는 관례 표기법임을 기억하자.

```python
>>> a = input()
hi
>>> a
'hi'
>>> b = input("Enter: ")
Enter: hi
위에서 입력받은 문자열을 확인해 보면 다음과 같다.

>>> b
'hi'
```

# ord
- 문자의 유니코드 값을 되돌려준다.
※ ord 함수는 chr 함수와 반대이다.

- 결과
```python
>>> ord('a')
97
>>> ord('가')
44032
```

# int
int(x)는 문자열 형태의 숫자나 소수점이 있는 숫자 등을 정수 형태로 돌려주는 함수로, 정수를 입력으로 받으면 그대로 돌려준다.

```python
>>> int('3')
3
>>> int(3.4)
3
int(x, radix)는 radix 진수로 표현된 문자열 x를 10진수로 변환하여 돌려준다.

2진수로 표현된 11의 10진수 값은 다음과 같이 구한다.

>>> int('11', 2)
3
16진수로 표현된 1A의 10진수 값은 다음과 같이 구한다.

>>> int('1A', 16)
26
```

# str

- 정수형 및 실수형 데이터를 str로 변환해주는 함수

```python
int_number = 3
int_to_str = str(3)
data_list = '테스트'

print(data_list + int_number) # error
print(data_list + number) # 테스트 3
```

---

# enumerate()

- 인덱스와 원소를 동시에 접근하면서 루프를 돌릴 수 있개 튜플을 생성함

```python
for i, entry in enumerate(['A', 'B', 'C']):
  print(i, entry)

# 0 A
# 1 B
# 2 C
```

# list()

리스트 생성자로 빈 리스트를 생성할 수 있다.

[](리스트 리터럴)과 형태는 비슷하지만 실제로 작동시간은 이 방법이 더 짧을 수 있다.

명시적으로 사용하고 싶을 경우, 좋은 방법에 해당될 수 있다.

```python
a = ['test', 'test', 'test1', 'test2', 'test3']
a.count('test')
# 2

b = list()
b.append('test')
b.append('test')
b.append('test2')
b.append('test3')
```

```python
from timeit import timeit

print(timeit("[]"))
print(timeit("list()"))

# 0.023734500049613416
# 0.039731499971821904
```

---

공부하다가 추가하는걸로...!