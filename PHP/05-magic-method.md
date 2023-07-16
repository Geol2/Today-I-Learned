## Magic Method

### __call($name, $args)

함수가 정의되어 있진 않은 함수가 호출되었을 때, 호출되는 매직함수이다

사용해보면 정말 특이하다

```php
<?php
class A {
    public function __call($name, $args) {
        var_dump(__METHOD__);
        var_dump($name, $args);
    }
}

$a = new A();
$a->foo("Hello, World");
# string(9) "A::__call" 
# array(1) {
#     [0] =>
#     string(12) => "Hello, World"
# }
```

### __callStatic()

`static` 인 점만 다르고 `__call` 과 동일하다

```php
<?php
class A {
    public static function __callStatic($name, $args) {
        var_dump(__METHOD__);
        var_dump($name, $args);
    }
}

$a = new A();
# string(3) "foo"
A::foo();
# array(0) {}
```

### __invoke()

객체의 호출을 담당하는 부분

```php
<?php
class A {
    public static function __invoke(... $args) {
        var_dump($args);
    }
}
$a = new A();
$a("Hello world", "Who are you?");
# array(2) {
#    [0] =>
#    string(12) "Hello world"
#    [1] =>
#    string(12) "Who are you?"
# }
```


## Property

### isset, unset
```php
<?php
class A {
    private $message;

    public function __isset($name) {
        var_dump($name);
        return isset($this->name);
    }

    public function __unset($name) {
        var_dump($name);
        unset($this->$name);
    }
}

$a = new A();
isset($a->message);
unset($a->message);

# ./property.php:7:
# string(7) "message"
# ./property.php:7:
# string(4) "name"
# ./property.php:12:
# string(7) "message"
```

### __set($name, $value), __get($name)

```php
<?php
class A {
    private $message;

    public function __set($name, $value) {
        $this->$name = $value;
    }

    public function __get($name) {
        return $this->$name;
    }
}

$a = new A();
$a->message = "Hello World";
var_dump($a->message);
# string(11) "Hello World"
```

### Serialize (직렬화)

```php
class A {
    private $message;

    public function __set($name, $value) {
        $this->$name = $value;
    }

    public function __get($name) {
        return $this->$name;
    }
}

class B implements Serializeable {
    private $message = 'Hello, World';

    public function serialize() {
        return serialize($this->message);
    }

    public function unserialize($serialize) {
        $this->message = unserialize($serailized);
    }
}

$a = new A();
$b = new B();
$serialized = serialize($a);

var_dump($serialized);
# string(33) "C:1:"B":20:{S:12:{"Hello, world";}}"
var_dump(unserialize($serialized));
# class B#3 (1) {
# private $message =>
# string(12) "Hello, world"
# }
```

