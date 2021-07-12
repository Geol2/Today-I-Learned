# 싱글턴 패턴
- 자원 공유를 위해 객체 생성 개수를 1개로 제한한다.

1. 객체 생성
  1.1 new 키워드

```php
<?php
class Config {
  private function __construct() {
    echo __CLASS__."가 생성이 되었습니다.\n";
  }

  private function __clone() {
    // 객체 생성에서 복제 메서드를 막기위해 private 선언.
    echo __CLASS__."가 생성이 되었습니다.\n";
  }

  // 싱글턴 객체 생성 메서드
  private static function getInstance() {
    if( !isset(self::$Instance) ) {
      echo __CLASS__."객체를 생성합니다.\n";
      return new self();
    }

    echo __CLASS__."객체를 반환합니다.\n";
    return self::$Instance;
  }
}
```
클래스를 만들고 지정합니다.

```php
include "Config.php";
$obj = Config::getInstance();
```
싱글턴 객체 호출