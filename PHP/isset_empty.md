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

# empty(mixed $var): bool
- 변수가 비어있는지 확인하는 함수이다.

- example 1.
```php
<?php
$var = 0;

// Evaluates to true because $var is empty
if (empty($var)) {
    echo '$var is either 0, empty, or not set at all';
}

// Evaluates as true because $var is set
if (isset($var)) {
    echo '$var is set even though it is empty';
}
?>
```

- Output
```
$var is either 0, empty, or not set at all$var is set even though it is empty
```

- example 2.
```php
<?php
$testCase = array(
    1 => '',
    2 => "",
    3 => null,
    4 => array(),
    5 => FALSE,
    6 => NULL,
    7 => '0',
    8 => 0,
   
);

foreach ($testCase as $k => $v) {
    if (empty($v)) {
        echo "<br> $k => $v is empty";
    }
}

echo "<br>";

foreach ($testCase as $k => $v) {
    if( isset($v) ) {
        echo "<br> $k => $v is isset";
    }
}
```

- Output
```
1 => is empty
2 => is empty
3 => is empty
Notice: Array to string conversion in .\index.php on line 16
4 =>Array is empty
5 => is empty
6 => is empty
7 => 0 is empty
8 => 0 is empty

1 => is isset
2 => is isset
Notice: Array to string conversion in .\index.php on line 24

4 => Array is isset
5 => is isset
7 => 0 is isset
8 => 0 is isset
```