# How to use in_array()
## in_array ( mixed $needle , array $haystack , bool $strict = false ) : bool
- 첫 번째 인자는 찾고자 하는 데이터
- 두 번째 인자는 첫번쨰 인자의 데이터가 있는지 확인할 배열 데이터
- 세 번째 인자는 true 시 배열 haystack 안의 needle의 타입을 확인함.
- 리턴 타입은 bool 타입으로 반환함.

### Example 1
```
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
```
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
```
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