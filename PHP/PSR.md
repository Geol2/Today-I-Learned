# PSR

- PHP 코딩 규칙 표준안
- ref. [PSR_standard_ko](https://psr.kkame.net/)
- ref. [PSR_standard_en](https://www.php-fig.org/psr/)
- 영어 링크가 항상 우선시 된다.

---

# PSR-1 : basic-coding-standard

- 반드시 `<?php`와 `<?=` 태그만 사용되어야 한다.
- 반드시 클래스를 작성할 때, 첫 글자는 대문자를 작성하도록 한다.
- 반드시 클래스의 상수를 작성할 땐, `대문자`와 `_`를 사용하도록 한다.
- 반드시 함수는 카멜케이스로 사용하도록 한다.
- 네임스페이스와 클래스는 `Autoloading`에 관련된 PSR-0, 4 규칙을 적용하도록 한다.
- 반드시 `utf-8` 문자를 사용하도록 한다.

---

# PSR-12 : extended-coding-style-guide

- 요약된 PSR-1 규칙을 모두 따라야 한다.
- 한줄은 80자 이하로 사용하도록 하고, 반드시 120자 이하로 적용하도록한다.
- 들여쓰기 4칸을 사용하도록 하고, Tab은 쓰지 말도록 한다. (난 2칸을 사용하기도 한다...)
- 예약 키워드와 타입은 무조건 소문자를 사용하도록 한다. `boolean` -> `bool`, `interger` -> `int`

```php
<?php
function fooBarBaz($arg1, &$arg2, $arg3 = [], &...$arg4)
{
  // function body
}
```

- `&` 참조 연산자는 C언어의 참조 연산자와 비슷하게 생각하면 된다.

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
?>
```

```php
<?php
function add($a, $b) {
    return $a + $b;
}

echo add(...[1, 2])."\n"; // 3

$a = [1, 2];
echo add(...$a); // 3
?>
```

- `...`에 대한 사용법.

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
