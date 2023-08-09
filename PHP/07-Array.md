# Array

## array ( mixed ...$values ) : array

- PHP 에서의 배열은 일련의 정렬된 맵과 같음.
- 리스트, 해쉬 테이블 구현, 벡터, 스택, 다차원 배열 등 여러가지 처리가 가능한 장점이 있다.
- "key => values" 로 흔히 표현됨.

Example 1. 기본적인 배열 만드는 방법
```PHP
// PHP 배열 표준 선언 방법
<?php
    $array = array(
        "foo" => "bar",
        "bar" => "foo",
    );

    // PHP 배열 약어 선언
    $array = [
        "foo" => "bar",
        "bar" => "foo",
    ];
?>
```

Example 2. 자동 배열 인덱스 참고
```PHP
<?php
    $array = array(1, 1, 1, 1,  1, 8 => 1,  4 => 1, 19, 3 => 13);
    print_r($array);
?>
```
Example 2 output.
```PHP
Array
(
    [0] => 1
    [1] => 1
    [2] => 1
    [3] => 13
    [4] => 1
    [8] => 1
    [9] => 19
)
```
-----
## [docs](https://www.php.net/manual/en/function.array)


## array_walk(array|object &$array, callable $callback, mixed $arg = null): bool

첫 파라미터에 배열인 변수를 넘겨주면, 두 번째 함수나 함수이름으로 콜백되어 실행한다

```php
<?php
$elements = ['a', 'b', 'c'];

array_walk($elements, function ($value, $key) {
  echo "{$key} => {$value}\n";
}); ?>
# 0 => a
# 1 => b
# 2 => c
```

```php
<?php
$fruits = array("d" => "lemon", "a" => "orange", "b" => "banana", "c" => "apple");

function test_alter(&$item1, $key, $prefix)
{
    $item1 = "$prefix: $item1";
}

function test_print($item2, $key)
{
    echo "$key. $item2\n";
}

echo "Before ...:\n";
array_walk($fruits, 'test_print');

array_walk($fruits, 'test_alter', 'fruit');
echo "... and after:\n";

array_walk($fruits, 'test_print');
?>
# Before ...:
# d. lemon
# a. orange
# b. banana
# c. apple
# ... and after:
# d. fruit: lemon
# a. fruit: orange
# b. fruit: banana
# c. fruit: apple
```

## in_array ( mixed $needle , array $haystack , bool $strict = false ) : bool

- 첫 번째 인자는 찾고자 하는 데이터
- 두 번째 인자는 첫번쨰 인자의 데이터가 있는지 확인할 배열 데이터
- 세 번째 인자는 true 시 배열 haystack 안의 needle의 타입을 확인함.
- 리턴 타입은 bool 타입으로 반환함.

### Example 1
```php
<?php
    // array type "$os"
    $os = array("Mac", "NT", "Irix", "Linux");
    if (in_array("Irix", $os)) { // 만약, $os에 "Irix" 가 있다면...
        echo "Got Irix";
    }
    if (in_array("mac", $os)) { // 만약, $os에 "mac" 이 있다면...
        echo "Got mac";
    }
?>
```
Example 1 output :
```
Got Irix
```
---
### Example 2
```php
<?php
    $a = array('1.10', 12.4, 1.13);

    if (in_array('12.4', $a, true)) {
        echo "'12.4' found with strict check\n";
    }

    if (in_array(1.13, $a, true)) {
        echo "1.13 found with strict check\n";
    }
?>
```
Example 2 output :
```
1.13 found with strict check
```
---
### Example 3
```php
<?php
    $a = array(array('p', 'h'), array('p', 'r'), 'o');

    if (in_array(array('p', 'h'), $a)) {
        echo "'ph' was found\n";
    }

    if (in_array(array('f', 'i'), $a)) {
        echo "'fi' was found\n";
    }

    if (in_array('o', $a)) {
        echo "'o' was found\n";
    }
?>
```
Example 3 output : 
```
'ph' was found
'o' was found
```
---
[Docs](https://www.php.net/manual/en/function.in-array.php)