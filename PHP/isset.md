# isset( mixed $var , mixed ...$vars ) : bool

- 변수가 선언되어 있는지 확인하며, `null`과는 다른지 확인하는 방법이다.

---

- `$var`가 `null`이라면 isset 함수는 `false`를 반환하고 `\0`과는 다르고 `null` 이외의 값이 있다면 `true`를 반환한다
- `$var`는 변수를 체크한다
- `$vars`는 추가 변수를 체크한다

```php
<?php

$var = '';

// This will evaluate to TRUE so the text will be printed.
if (isset($var)) {
    echo "This var is set so I will print.";
}

// In the next examples we'll use var_dump to output
// the return value of isset().

$a = "test";
$b = "anothertest";

var_dump(isset($a));      // TRUE
var_dump(isset($a, $b)); // TRUE

unset ($a);

var_dump(isset($a));     // FALSE
var_dump(isset($a, $b)); // FALSE

$foo = NULL;
var_dump(isset($foo));   // FALSE

?>
```
