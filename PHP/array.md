# Array

## array ( mixed ...$values ) : array

- PHP 에서의 배열은 일련의 정렬된 맵과 같음.
- 리스트, 해쉬 테이블 구현, 벡터, 스택, 다차원 배열 등 여러가지 처리가 가능한 장점이 있다.
- "key => values" 로 흔히 표현됨.

Example 1. 기본적인 배열 만드는 방법
```PHP
// PHP 5.4 이하 라면..
<?php
    $array = array(
        "foo" => "bar",
        "bar" => "foo",
    );

    // PHP 5.4 부터 지원되는 배열 선언 방법
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
[docs](https://www.php.net/manual/en/function.array)