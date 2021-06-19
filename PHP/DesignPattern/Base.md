1. 클래스

```php
<?php 
class Korean {
    public function text() {
        return "안녕하세요.";
    } 
}
class Hello {
    public function greeting() {
        $ko = new Korean;
        return $ko->text();
    }
}

$Hello = new Hello();
echo $Hello->greeting();
```
- 해당 관계는 `$Hello` 라는 객체를 만들었고, `$Hello`의 `greeting()` 이라는 함수에 접근하는 함수이다.
- `greeting()` 이라는 함수 안에는 `$ko`를 선언하여 `Korean` 이라는 객체를 만들어서 `text()`의 함수에 접근하여 안녕하세요. 라는 문자열을 출력해주는 것을 확인할 수 있다.

2. 의존성 주입
```php
<?php 
class Korean {
    public function text() {
        return '안녕하세요.';
    }
}

class Hello {
    private $korean;

    public function __construct($obj)
    {
        $this->korean = $obj;
    }

    public function greeting() {
        return $this->korean->text();
    }
}

$korean = new Korean;

$ko_obj = new Hello($korean);
echo $ko_obj->greeting();
```
