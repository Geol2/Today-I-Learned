---
title: "PHP Languages"
categories:
  - PHP
tags:
  - PHP
last_modified_at: 2021-10-19T01:30:00-02:30
---

# PSR

- PHP 코딩 규칙 표준안
- ref. [PSR_standard_ko](https://psr.kkame.net/)
- ref. [PSR_standard_en](https://www.php-fig.org/psr/)
- 영어 링크가 항상 우선시 된다.

---

# PSR-1 : basic-coding-standard

- 반드시 `<?php`와 `<?=` 태그만 사용되어야 하며, 클래스에선 태그를 닫지 않는다.
- 반드시 클래스를 작성할 때, 첫 글자는 대문자를 작성하도록 한다.
- 반드시 클래스의 상수를 작성할 땐, `대문자`와 `_`를 사용하도록 한다.
- 반드시 함수는 카멜케이스로 사용하도록 한다.
- 네임스페이스와 클래스는 `Autoloading`에 관련된 PSR-0(만료), 4 규칙을 적용하도록 한다.
- 반드시 `utf-8` 문자를 사용하도록 한다.

---

# PSR-3 : Logger Interface

- `LoggerInterface`는 8개의 RFC5425(debug, info, notice, warning, error, critical, alert, emergency) 단계의 메서드를 제공해주어야 한다.
- 9번째 메서드인 로그 수준을 입력받아야 하며, 로그 수준 상수 중 하나를 불러오면 동일한 결과를 가져야 한다. 정의되지 않은 수준이나 알지 못하는 수준을 불러오면 반드시 `Psr\Log\InvalidArgumentException`을 던져야 하며, 사용자는 지원하는지 모른채 사용자 지정 수준을 사용해서는 안됨을 권장한다.

---

# PSR-4 : autoloader

- 이 PSR은 분리된 Class 파일을 불러와서 사용하기 위해 namespace 설정에 대한 내용입니다.
- 정규화된 클래스의 형식은 다음과 같음.

```text
\<NamespaceName>(\<SubNamespaceNames>)*\<ClassName>
```

- 예제

  - Class 이름 : \Zend\Acl
  - namespace 이름 : Zend
  - 기본 디렉토리 : /usr/includes/Zend/
  - 결과 파일 경로 : /usr/includes/Zend/Acl.php

- composer.json 파일을 이용해서도 가능.

```json
"autoload": {
    "psr-4": {
      "Config\\" : "config/"
    }
  }
```

```php
// DatabaseConfig.php
namespace Config;
class DatabaseConfig
{
  //...
}
```

```php
// App.php
namespace Config;
class App
{
  //...
}
```

```php
//index.php
use Config\DatabaseConfig as DatabaseConfig;
use Config\App as App;

$app = new App();
//...
$config = new DatabaseConfig('development', 'RedBeanPHP');
//...
```

- `Config\\`는 네임스페이스가 되며, `config/`는 설정된 폴더명이 된다.

# PSR-12 : extended-coding-style-guide

- 요약된 PSR-1 규칙을 모두 따라야 한다.
- 한줄은 80자 이하로 사용하도록 하고, 반드시 120자 이하로 적용하도록한다.
- 들여쓰기 4칸을 사용하도록 하고, Tab은 쓰지 말도록 한다. (난 2칸을 사용하기도 한다...)
- 예약 키워드와 타입은 무조건 소문자를 사용하도록 한다. `boolean` -> `bool`, `interger` -> `int`

- 인자 목록은 여러 줄로 표현할 수 있으며, 여러 줄로 표현할 시 한 인자당 한 줄씩 표현해주도록 한다.

```php
<?php

namespace Vendor\Package;

class ClassName
{
  public function aVeryLongMethodName(
    ClassTypeHint $arg1,
    &$arg2,
    array $arg3 = []
  ) {
    // method body
  }
}
```

- 인자 목록을 여러 줄로 표현해줄 때, 마지막 소괄호를 닫고 바로 그 줄에서 중괄호로 열어주도록 한다.

# Operator

## (Operator) ...

- `array_merge()` 함수의 의미를 따르고 있다.
- php에선 보기힘든 낯선 연산자 중 하나인데 라이브러리나 프레임워크 분석 도중 가끔씩 튀어나오는 연산자

### 예제

```php
<?php
// Using short array syntax.
// Also, works with array() syntax.
$arr1 = [1, 2, 3];
$arr2 = [...$arr1]; // [1, 2, 3]
$arr3 = [0, ...$arr1]; // [0, 1, 2, 3]
$arr4 = [...$arr1, ...$arr2, 111]; // [1, 2, 3, 1, 2, 3, 111]
$arr5 = [...$arr1, ...$arr1]; // [1, 2, 3, 1, 2, 3]
```

```php
<?php
function add($a, $b) {
    return $a + $b;
}

echo add(...[1, 2])."\n"; // 3

$a = [1, 2];
echo add(...$a); // 3
```

```php
<?php

namespace Vendor\Package;

class ClassName
{
  public function foo(int $arg1, &$arg2, $arg3 = [], &...$arg4)
  {
    // method body
  }
}
```

```php
<?php
function fooBarBaz($arg1, &$arg2, $arg3 = [], &...$arg4)
{
  // function body
}
```

```php
<?php
function sum(...$numbers) {
    $acc = 0;
    foreach ($numbers as $n) {
        $acc += $n;
    }
    return $acc;
}

echo sum(1, 2, 3, 4); // 10
```

---

## (Operator) Ampersand, &

- 참조연산자로 불리며, C언어에서 흔히 사용되는 `포인터`에서 참조를 위해 사용되는 연산자와 비슷하다. ( 모르면 C언어나 학부시절을 참고해보자! )
- `Passing by References`과 `Returning References` 종류가 있다.

### Passing by References (참조에 의한 전달)

```php
<?php
function foo(&$var)
{
    $var++;
}

$a = 5;
foo($a);
// $a is 6 here
```

- 참조를 위한 표현은 함수를 호출 할 때 사용되는 것이 아니라, 함수 선언에 참조표시를 할 수 있다.

```php
<?php
function bar() // Note the missing &
{
    $a = 5;
    return $a;
}
foo(bar());  // PHP 5.0.5부터 치명적인 오류가 발생합니다

foo($a = 5); // 변수가 아닌, 표현식입니다
foo(5);      // 치명적인 오류가 발생합니다
```

### Returning References (참조반환)

- 성능을 높이기 위해 참조별 반환을 최대한 사용하지 말라고 권고하고 있으며 엔진이 자동적으로 최적화한다고 한다. 참조를 반환해야할 기술적 이유가 있을 때만 반환하기를 권고하고 있다.

```php
<?php
class foo {
    public $value = 42;

    public function &getValue() {
        return $this->value;
    }
}

$obj = new foo;
$myValue = &$obj->getValue(); // $myValue is a reference to $obj->value, which is 42.
$obj->value = 2;
echo $myValue;                // prints the new value of $obj->value, i.e. 2.
```

```php
<?php
function foo(&$var)
{
    $var++;
}
function bar() // Note the missing &
{
    $a = 5;
    return $a;
}
foo(bar()); // Produces a notice

foo($a = 5); // Expression, not variable
foo(5); // Produces fatal error

class Foobar
{
}

foo(new Foobar()) // Produces a notice as of PHP 7.0.7
                  // Notice: Only variables should be passed by reference
```

---

- [... 연산자 참고자료](https://www.php.net/manual/en/language.types.array.php)
- [& 연산자 참고자료](https://www.php.net/manual/en/language.references.pass.php)

---

# Passing by Reference


## call by reference 는 무엇일까?

C에서 함수를 호출할 때, 매개변수로 들어온 값을 복사한다. 그럼 값에 의한 호출이 된다.<br>
복사된 값이 주소값이 되므로 결국엔 주소값을 전달했으니까 참조에 의한 전달로 부른다. (call by address 라는 말도 있다)

`f(&a)`를 호출했을 때, `void f(int *b) {` 에서 첫 번째 `b`에서 `a`의 값을 바꿀 수 있게 된다면
`call by reference`가 된다.

```c
void f(int *b) { // 3. b의 주소값은 1001이고 b는 1000을 복사해서 담고 있음
    (*b)++;      // 4. 1000의 값을 하나 증가시킴
}                // 5. *b는 11로 바뀜

int main() {
    int a = 10; // 1. a의 값 10, 주소값 1000

    f(&a);      // 2. 주소값 넘기기
                // 6. a는 11이 된다.
    return 0;
}
```

### Passing by Reference

PHP에서는 `Passing by Reference`라고 메뉴얼에 소개가 되어있다.

아래 `array_multisort` 처럼 함수의 리턴형이 `void`로 데이터를 반환하기 힘들 때, <br>
매개변수에서 `passing by reference`를 사용해서 데이터 변경하면 된다.

이 참조연산자는 마찬가지로 남발하지 않는 것이 건강에 좋다.

```php
<?php // 데이터베이스의 컬럼명을 기준으로 데이터를 오름차순 및 내림차순으로 정렬하는 코드
function array_sort_by_column(&$arr, $col, $direction = SORT_ASC) {
    $sort = [];
    foreach($arr as $key => $value) {
        $sort[$key] = $value[$col];
    }
    array_multisort($sort, $direction, $arr);
}
// ref. stackoverflow
?>
```

```php
<?php
function foo(&$var)
{
    $var++;
}

$a=5;
foo($a); // $a is 6 here

// ref. https://www.php.net/manual/en/language.references.pass.php
?>
```

## call_user_func()

정의한 함수들을 호출할 수 있다

PHP자체에 정의된 함수, 개발자가 정의한 함수, 클래스 내에 정의한 함수들을 호출할 수 있다

```php
function myFunc($s)
{
    return strtoupper($s);
}

$sFunc = 'myFunc';
echo call_user_func($sFunc, 'aabbcc');
# AABBCC
```

```php
<?php 
namespace Foobar;

class Foo {
    static public function test() {
        print "Hello world!\n";
    }
}

call_user_func(__NAMESPACE__ .'\Foo::test');
call_user_func(array(__NAMESPACE__ .'\Foo', 'test')); ?>
# Hello world!
# Hello world!
```