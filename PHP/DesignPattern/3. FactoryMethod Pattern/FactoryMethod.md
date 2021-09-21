# Factory Method

- 팩토리 패턴의 확장 패턴
- 팩토리 파턴과 템플릿 메서드 패턴이 결함된 패턴

## 추상화

- 추상화 기법을 사용하여 패턴을 확장

## 객체지향의 추상화

- 중요한 부분만 분리하여 처음부터 하나하나 살펴보지 않아도 이해하기 쉽게 만드는 작업
- 코드를 요약, 골격을 형성하는 작업
- 동작을 쉽게 이해하기 위해서 작성

```php
<?php
abstract class Factory {
  //...
}
?>
```

## 패턴 확장

### 팩토리

- LgProduct.php

```php
<?php
class LgProduct {
  public function name() {
    echo "LG Gram laptop";
  }
}
?>
```

- factory.php

```php
class Factory {
  public final function create() {
    return new LgProduct();
  }
}
```

- index.php

```php
require 'factory.php';
require 'LgProduct.php';

$fac = new Factory;
$pro = $fac->create();
$pro->name();
```

## 추상화

- 위의 추상화를 결합하고 패턴을 확장해 구현한다.
- 인터페이스 vs 추상화

- factory.php

```php
abstract factory {
  public final function create() {
      return $this->createProduct();
  }
  abstract public function createProduct(); // 추상메소드 선언
}
```

- 이 factory 클래스 안에는 추상 메소드가 선언이 되어 있고 독립적인 factory 클래스 선언은 에러를 발생시킴.

```
Fatal error: Uncaught Error: Cannot instantiate abstract class Factory in /Users/geol/Desktop/Dev/Today-I-Learned/PHP/DesignPattern/3. FactoryMethod Pattern/index.php:9 Stack trace: #0 {main} thrown in /Users/geol/Desktop/Dev/Today-I-Learned/PHP/DesignPattern/3. FactoryMethod Pattern/index.php on line 9
```

- 추상 클래스를 상속받아 구현부 클래스를 만들고 해당 `abstract public function` 메소드를 구현해주어야 한다.

## 개방-폐쇄 원칙 ( OCP )

- 소프트웨어 개체(클래스, 모듈, 함수 등등)는 확장에 대해 열려 있어야 하고, 수정에 대해서는 닫혀 있어야 한다 (위키)

## 최종

- Factory.php

```php
<?php
abstract class Factory {
  public final function create($model) {
    return $this->createProduct($model)
  }
  abstract public function createProduct($model);
}
?>
```

- ProductFactory.php

```php
<?php
class ProductFactory extends Factory {
  public function __construct() {
      echo __CLASS__."를 생성합니다.";
  }
  public function createProduct($model) {
    if($model == 'LG') {
      return new LgProduct();
    } else if ($model == 'SAMSUNG') {
      return new SamsungProduct();
    }
  }
}
?>
```

- index.php

```php
<?php
require "factory.php";
require "LgProduct.php";
require "SamsungProduct.php";
require "ProductFactory.php";

$fac = new ProductFactory;
$pro = $fac->create("LG");
$pro->name();

$pro = $fac->create("SAMSUNG");
$pro->name();

// ProductFactory를 생성합니다.
// LG Gram laptop
// Samsung Always laptop
```
