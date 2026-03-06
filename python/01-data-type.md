# 자료형

## 숫자형

숫자형에는 `정수형`, `실수형`, `8진수`와 `16진수`

숫자형을 활요하기 위해 `사칙연산`, x의 y제곱을 나타내기 위해 `**` 연산, 나눗셈 후에 나머지를 리턴하는 `%` 연산자

나눗셈 후 몫을 리턴하는 `//` 연산자가 존재한다

`/` 연산자를 사용하여 7 나누기 4를 하면 1.75가 되지만 `//`를 사용하면 1이 된다

## 문자열 자료형

문자열을 만들고 사용하는 방법은 여러 방법이 존재한다

큰따옴표로 양쪽 둘러싸기 `"어쩌구~"`, 작은따옴표로 양쪽 둘러싸기 `'어쩌구~'`, 큰따옴표 3개를 연속으로 써서 양쪽 둘러싸기 `""" 어쩌구~ """`, 작은따옴표 3개를 연속으로 써서 양쪽 둘러싸기 `''' 어쩌구~ '''`

문자열 안에 따옴표를 출력하고 싶을 땐, 큰따옴표를 사용했으면 작은따옴표를 포함할 수 있고

작은따옴표를 사용했으면 큰따옴표를 포함할 수 있다

큰따옴표를 사용해서 큰따옴표를 포함하려면 역슬래쉬를 사용하면 된다

작은따옴표를 사용해서 작은따옴표를 포함하려면 역슬래쉬를 사용하면 된다

여러줄인 문자열을 변수에 대입하고 싶을 땐 `\n`을 사용하거나 연속된 작은따옴표 3개 또는 큰따옴표 3개를 사용한다

#### 문자열 인덱싱

`a = "Life is too short, You need Python`

a[3] => 'e'
a[-0] => 'L'
a[-2] => 'o'
a[-5] => 'y'

#### 문자열 슬라이싱

`a = "Life is too short, You need Python`

a[0:5] => "Life "
a[12:17] => "short"
a[19:] => "You need Python"
a[:17] => "Life is too short"
a[:] => "Life is too short, You need Python"
a[19:-7] => "You need"

문자열은 기본적으로 변경불가능한 자료형이여서 변경이 불가능하지만 슬라이싱 기법을 사용하면 변경이 가능하다

#### 문자열 포매팅

```python
"I eat %d apples." % 3
'I eat 3 apples.'
```

```python
"I eat %s apples." % "five"
'I eat five apples.'
```

```python
"I ate %d apples. so I was sick for %s days." % (number, day)
'I ate 10 apples. so I was sick for three days.'
```

```
%s = 문자열
%c = 문자 1개
%d = 정수
%f = 부동소수
%o = 8진수
%x = 16진수
%% = 문자 자체의 %(Literal %)
```

```python
>>> "%10s" % "hi"
'        hi'

>>> "%-10sjane." % "hi"
'hi        jane'

>>> "%0.4f" % 3.42134234
'3.4213'

>>> "%10.4f" % 3.42134234
'    3.4213'
```

```python
>>> "I eat {0} apples".format(3)
'I eat 3 apples'

>>> "I eat {0} apples".format("five")
'I eat five apples'

>>> number = 3
>>> "I eat {0} apples".format(number)
'I eat 3 apples'

>>> number = 10
>>> day = "three"
>>> "I ate {0} apples. so I was sick for {1} days.".format(number, day)
'I ate 10 apples. so I was sick for three days.'
>>> "I ate {number} apples. so I was sick for {day} days.".format(number=10, day=3)
>>> "I ate {0} apples. so I was sick for {day} days.".format(10, day=3)

>>> "{0:<10}".format("hi")
'hi        '

>>> "{0:>10}".format("hi")
'        hi'

>>> "{0:^10}".format("hi")
'    hi    '

>>> "{0:=^10}".format("hi")
'====hi===='

>>> "{0:!<10}".format("hi")
'!!!!hi!!!!'

>>> y = 3.42134234
>>> "0:0.4f".format(y)
'3.413'

>>> "0:10.4f".format(y)
'    3.4213'

>>> "{{ and }}".format()
'{ and }'


```
