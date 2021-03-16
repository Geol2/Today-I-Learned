# **use**, **namespace** Keyword

## **use**
- use 키워드를 사용하기 전 코드이고 여러 개를 만들어야 하면 줄줄 길어진다.

```php
<?php
  $model = new App\Medels\NewsModel();
```

- use 키워드를 사용함으로써 더 깔끔하게 만들어줄 수 있다.
- 기본적으로 사용되는 별칭으로 봐도 무방하다.
```php
<?php

  use App\Models\NewsModels;
  ...
  $model = new NewsModel();
  ...
```

- use ... as ... : as 를 사용함으로써 별칭을 붙여줄 수도 있다.
```php
<?php

  use App\Models\NewsModels as NewModel;
  ...
  $model = new NewModel();
  ...
```
- 함수, 상수에 별칭을 사용할 수도 있다.
```php
<?php
  use func ...; // 함수에 별칭
  use constant ...; // 상수에 별칭
```

<br>
- 아래와 같이 사용하진 않고 타이핑을 더해서 한 문장마다 use를 사용하도록 한다.

```php
<?php
  use Symfony\Component\HttpFoundation\Request,
      Symfony\Component\HttpFoundation\Response,
      Symfony\Component\HttpFoundation\Cookie; // (X)
```
```php
<?php
  use Symfony\Component\HttpFoundation\Request;
  use Symfony\Component\HttpFoundation\Response;
  use Symfony\Component\HttpFoundation\Cookie; // (O)
```

---
## **namespace**

- namepace는 같은 함수가 사용이 되었을 때, 소유권을 나타낼 수 있다.
- App 아래에 Controllers 서브네임스페이스를 선언하였고 이 네임스페이스 아래에 모든 클래스, 함수, 인터페이스, 상수가 존재하게 한다.
- 하나의 파일에 해당 네임스페이스에 통하는 모든 클래스를 넣어둘 필요는 없고 하나의 파일엔 하나의 클래스로 여러 파일로 나누어서 작성하도록 한다.
```
<?php namespace App\Controllers; 
    ...
    class News extends Controller {
        ...
    }
```