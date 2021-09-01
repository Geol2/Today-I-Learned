# 1. 팩토리 클래스
- 프로그램 내부에서 필요한 객체를 생성하는 역할을 수행한다.
- 다수의 객체를 생성할 때, 이를 선택하기 위해 매개변수를 전달합니다.
- `const`를 사용하여 안전하게 동작하도록 할 수 있다.


```php
<?php 
class Korean {
  public function text() {
    return '안녕하세요.';
  }
}

class English {
  public function text() {
    return 'Hello text';
  }
}

class Factory {
  static public function getInstance($type = null) {
    echo "팩토리::객체를 생성하여 반환합니다.\n";
    if($type === "ko") {
      return new Korean();
    } else if($type === "en") {   
      return new English();
    }
  }
}

class Hello {
  public function greeting($type) {
    $ko = Factory::getInstance($type);
    return $ko->text();
  }
}

$obj = new Hello;
echo $obj->greeting("en")."\n";
echo $obj->greeting("ko")."\n";
```

- `const`의 활용 예
```php
class Factory {
// 위의 예제에서 팩토리 클래스만 발췌했습니다.
  const KOREAN = "ko";
  const ENGLISH = "en";
  static public function getInstance($type = null) {
    echo "팩토리::객체를 생성하여 반환합니다.\n";
    if($type === "ko") {
      return new Korean();
    } else if($type === "en") {   
      return new English();
    }
  }
}

$obj = new Hello;
echo $obj->greeting(Factory::ENGLISH). "\n";
echo $obj->greeting(Factory::KOREAN). "\n";
```
