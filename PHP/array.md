# Array

## array ( mixed ...$values ) : array

- PHP 에서의 배열은 일련의 정렬된 맵과 같음.
- 리스트, 해쉬 테이블 구현, 벡터, 스택, 다차원 배열 등 여러가지 처리가 가능한 장점이 있다.
- "key => values" 로 흔히 표현됨.

Example 1. 기본적인 배열 만드는 방법
```
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

[docs](https://www.php.net/manual/en/function.array)