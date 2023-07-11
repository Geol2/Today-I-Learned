## Magic Method

### __call($name, $args)

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