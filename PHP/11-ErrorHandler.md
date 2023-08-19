# ErrorHandler

## 사용법

```php
set_error_handler(?callable $callback, int $error_levels = E_ALL): ?callable
```

$callback에 콜백할 함수를 지정해준다

$error_level의 기본값은 E_ALL

error_reporting() 설정에 관계없이 오류 핸들러가 호출되지만, error_reporting의 현재 값을 읽고 적절하게 동작하는 것은 여전히 가능합니다.

PHP 7.2부터 콜백 함수에 5번째 파라미터는 deprecated 되었으며 8.0부터는 삭제처리가 되었다

콜백 함수 내에서 리턴을 사용할 수 없지만 set_error_handler를 사용할 수 없다면 false를 리턴해준다

## 예제

```php
<?php

function error_handler($errno, $errorStr, $errorFile, $errorLine) {
  echo "[".$errno."]";
  echo " ".$errorStr;
  echo " ".$errorFile;
  echo " ".$errorLine."\n";
}

$isError = set_error_handler("error_handler"); # 제대로 호출이 된다면 null, 콜백함수를 제대로 지정되지 않았다면 에러가 발생되고 진행되지 않는다
if ($isError === false) {
  echo "function 'set_error_handler' error";
  exit();
}

for($i = 0; $i < 2; $i++) {
  $test = 2;

  if ($test > 1) {
    $test2 = $arr['test']; # Undefined key error
  }
}

echo "destination";
```

error_handler 내에 return false 를 사용하면 시스템에서 에러를 출력해주고 다음 for 문이 실행된다


```
# 출력
[2] Undefined variable $arr /opt/project/example_error_handler.php 20
[2] Trying to access array offset on value of type null /opt/project/example_error_handler.php 20
[2] Undefined variable $arr /opt/project/example_error_handler.php 20
[2] Trying to access array offset on value of type null /opt/project/example_error_handler.php 20
destination
```