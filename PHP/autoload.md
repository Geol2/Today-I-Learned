# autoload

## spl_autoload_register() ( callable $autoload_function = ? , bool $throw = true , bool $prepend = false ) : bool

- 클래스의 정의와 사용 부분을 손쉽게 나누어 사용할 수 있게 해주는 방법
- 일반적으로 클래스의 정의를 분리해두고 호출하게 되면 직접 `include`나 `require`, `require_once`를 하지 않는 이상 해당 클래스 파일은 찾지 못하고 사용할 수 없게 된다.
- 한 파일에서 여러 클래스 파일을 읽어들일 때, `inlclude`를 남발하여 사용하여 함수나 클래스끼리 이름 충돌이 발생할 수 있는 문제가 있다.

---

```php
// autoload.php
<?php
spl_autoload_register( function ($path){
  $path = str_replace('\\','/', $path); // 맥북사용자..
  $path = $path.'.php';
  // var_dump("path: {$path}");
  require_once $path;
});

```

무명함수로 구현할 수도 있으며 따로 인자값을 함수 이름을 두어도 된다.

```php
// autoload.php
<?php
function autoload ($path) {
  $path = str_replace('\\','/', $path);
  $path = $path.'.php';
  // var_dump("path: {$path}");
  require_once $path;
}
spl_autoload_register( 'autoload' );
```

- 구현된 함수를 볼 땐, 결국엔 require_once를 사용하는 구조여서 왜 사용이 되는지 모를 땐, 그냥 각 파일을 전부 require_once 시켜서 사용해도 무방하다.

```php
// index.php
<?php
require_once 'autoload.php';

// 별칭을 두어 중복을 피하고 객체를 생성의 이름을 줄일 수 있도록 노력하자.
use greeting\en\Hi as EnHi;
use greeting\ko\Hi as KoHi;

$result = new Home();

$result2 = new Base();

$result3 = new EnHi();
$result4 = new KoHi();
```

- index.php 파일을 기준으로 `php -S localhost:10002` 로컬 개발 환경을 실행(기준)하여 테스트를 작성하여 보았다.
- 기준에서 greeting 폴더를 만들고 아래에 en과 ko폴더를 만들고 en 아래에 Hi.php, ko 폴더 아래에 Hi.php를 만들었다.

```php
// greeting/en/Hi.php
<?php namespace greeting\en;
class Hi {
  function __construct() {
    echo '<h1>Hi</h1>';
  }
}
```

```php
// greeting/ko/Hi.php
<?php namespace greeting\ko;
class Hi {
  function __construct() {
    echo '<h1>안녕</h1>';
  }
}
```

[namespace(참고링크)](https://github.com/Geol2/Today-I-Learned/blob/main/PHP/use_namespace.md) 를 사용해서 일단 함수의 중복을 나눠주기로 했다.

CodeIgniter4 내부를 보다 궁금증이 생겨 찾아보았다.
