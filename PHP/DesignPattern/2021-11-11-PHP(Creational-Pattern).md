---
title: "PHP - Creational Pattern"
categories:
  - PHP
tags:
  - Design Pattern
last_modified_at: 2021-11-11T22:20:00-23:59:59
---

# Factory Method

- 팩토리 패턴의 확장 패턴
- 팩토리 패턴과 템플릿 메서드 패턴이 결함된 패턴

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

```php
<?php

abstract class Factory {
  public final function create($model) {
    return $this->createProduct($model);
  }
  abstract public function createProduct($model);
}

class LgProduct {
  public function name() {
    echo "LG Gram laptop";
  }
}

class SamsungProduct {
  public function name() {
    echo "Samsung Always laptop";
  }
}

class ProductFactory extends Factory {
  public function __constuct() {
    echo __CLASS__."를 생성합니다.";
  }
  public function createProduct($model) {
    if($model == "LG") {
      return new LgProduct();
    } else if ($model == "SAMSUNG") {
      return new SamsungProduct();
    }
  }
}

$fac = new ProductFactory;
$pro = $fac->create("LG");
$pro->name();

echo "<br>";

$pro = $fac->create("SAMSUNG");
$pro->name();

// LG Gram laptop
// Samsung Always laptop
```


# Abstract Factory 추상 팩토리

- 팩토리 vs 팩토리 메서드 vs 추상 팩토리

  - 팩토리와 팩토리 메서드 : 추상화
  - 팩토리 메서드와 추상 팩토리 : 추상화된 그룹을 형성하고 관리

- Factory.php

```php
<?php

abstract class Factory
{
    abstract public function createTire();
    abstract public function createDoor();
}
```

- 추상 클래스 정의하여 추상 메서드를 선언함.

- TireProduct.php

```php
<?php

abstract class TireProduct
{
    abstract public function makeAssemble();
}
```

- 추상클래스 타이어라는 부품의 추상 메서드를 선언함.

- DoorProduct.php

```php
<?php

abstract class DoorProduct
{
    abstract public function makeAssemble();
}
```

- 추상클래스 문이라는 부품의 추상 메서드를 선언함.

- KoreaFactory.php

```php
<?php

class KoreaFactory extends Factory
{
    public function __construct() {
        echo __CLASS__."객체를 생성합니다.<br>";
    }

    public function createTire(): KoreaTireProduct
    {
        // TODO: Implement createTire() method.
        return new KoreaTireProduct;
    }

    public function createDoor(): KoreaDoorProduct
    {
        // TODO: Implement createDoor() method.
        return new KoreaDoorProduct;
    }
}
```

- 한국 공장 클래스에서 추상 팩토리를 상속받아서, 구현해야할 한국공장 문과 타이어를 만드는 함수들을 만들고 그 객체를 반환함.

- StateFactory.php

```php
<?php

class StateFactory extends Factory
{

    public function createTire(): StateTireProduct
    {
        // TODO: Implement createTire() method.
        return new StateTireProduct;
    }

    public function createDoor(): StateDoorProduct
    {
        // TODO: Implement createDoor() method.
        return new StateDoorProduct;
    }
}
```

- 마찬가지로 추상팩토리를 상속받아서 구현해야할 미국공장 문과 타이어를 만드는 함수들을 만들고 그 객체를 반환함.

- 현재 `KoreaDoorProuduct`, `KoreaTireProduct`, `StateDoorProduct`, `StateTireProduct` 클래스는 만들어지지 않았으므로 만들어주도록 한다.

- KoreaDoorProduct.php

```php
<?php

class KoreaDoorProduct extends DoorProduct
{
    public function __construct()
    {
        echo "Product = ".__CLASS__."객체를 생성합니다.<br>";
    }

    public function makeAssemble() {
        echo "메서드 호출 ".__METHOD__. "<br>";
        echo "한국형 도어 장착 <br>";
    }
}
```

- KoreaTireProduct.php

```php
<?php

class KoreaTireProduct extends TireProduct
{

    public function __construct()
    {
        echo "Product = " .__CLASS__. "객체를 생성합니다.<br>";
    }
    public function makeAssemble() {
        echo "메서드 호출 ".__METHOD__."<br>";
        echo "한국형 타이어 장착<br>";
    }
}
```

- StateDoorProduct.php

```php
<?php

class StateDoorProduct extends DoorProduct
{
    public function __construct()
    {
        echo "Product =" .__CLASS__."객체를 생성합니다.<br>";
    }

    public function makeAssemble()
    {
        // TODO: Implement makeAssemble() method.
        echo "메서드 호출 " .__METHOD__."<br>";
        echo "미국형 도어 장착<br>";
    }
}
```

- StateTireProduct.php

```php
<?php

class StateTireProduct extends TireProduct
{
    public function __construct()
    {
        echo "Product =" .__CLASS__."객체를 생성합니다.<br>";
    }

    public function makeAssemble()
    {
        // TODO: Implement makeAssemble() method.
        echo "메서드 호출 ".__METHOD__."<br>";
        echo "미국형 타이어 장착 <br>";
    }
}
```

- index.php
  - 패턴 결합을 통해 객체 생성을 그룹화하고 그룹화된 객체를 결합하여 실행한다.

```php
<?php
require "Factory.php";
require "StateFactory.php";
require "KoreaFactory.php";

require "DoorProduct.php";
require "TireProduct.php";

require "KoreaDoorProduct.php";
require "KoreaTireProduct.php";

require "StateDoorProduct.php";
require "StateTireProduct.php";

$korea = new KoreaFactory;

$ham = $korea->createTire();
$ham->makeAssemble();

$bread = $korea->createDoor();
$bread->makeAssemble();

$state = new StateFactory;

$ham = $state->createTire();
$ham->makeAssemble();

$bread = $state->createDoor();
$bread->makeAssemble();
```


# Builder

- 추상 팩토리를 확장해서 크고 복잡한 객체를 생성할 수 있음.
- 복잡한 구조를 가진 복합 객체의 생성 과정을 분리하여 처리하는 패턴.

- 복잡한 구조를 가진 복합 객체는 한 단계로만 생성하기 힘드며 구조에 맞게 객체를 생성하고 관계를 설정하는 로직이 필요하다.
- 복합객체 생성 요청이 들어오면, 빌더를 통해 알고리즘으로 클래스들을 나누어 단계에 맞게 생성하면 좋다.

- index.php

```php
<?php
require "Builder.php";
require "Factory.php";

require "Memory.php";
require "Storage.php";
require "Computer.php";

require "Algorithm.php";
require "ProductModel.php";

$algorithm = new ProductModel();

$factory = new Factory();
$factory->setAlgorism($algorithm);

$computer = $factory->build()->getInstance();

echo $computer;
```

- 이 코드를 어느정도 설명을 하면 추상클래스인 `Algorithm`을 상속받은 `ProductModel`이 컴퓨터의 성능을 나타내는 기능이 있다.
- 팩토리 객체를 생성해서 `$algorithm` 변수를 할당하여 팩토리에 의존성을 주입한다.
- 의존성이 주입된 복합객체를 만들어서 출력을 해주고 있다.

```php
<?php

abstract class Builder
{
    protected $algorithm;

    public function __construct()
    {
        echo "Builder " .__CLASS__. "를 생성하였습니다.<br>";
    }

    public function setAlgorism(Algorithm $algorism) {
        echo "빌드 객체를 저장합니다. <= ". get_class($algorism), "\n";
        $this->algorithm = $algorism;

        return $this;
    }

    public function getInstance() {
        return $this->algorithm->getInstance();
    }

    abstract public function build();
}
```

- 추상클래스인 `Builder`는 다양한 종류의 복합 객체를 생성 관리한다.
- `build()`는 하위 클래스에 위임하여 하위 클래스에서 구현하는데, 이를 구현하지 않으면 에러가 발생되게 설계한다.

```php
<?php

class Factory extends Builder
{
    public function __construct($algorithm=null) {
        echo __CLASS__."객체를 생성하였습니다.<br>";
        if($algorithm) {
            $this->algorithm = $algorithm;
        }
    }

    public function build(): Factory
    {
        // TODO: Implement build() method.
        echo "=== 빌드합니다. ===<br>";
        $this->algorithm->setCpu("i7");
        $this->algorithm->setRam([8,8]);
        $this->algorithm->setStorage([256,512]);

        return $this;
    }
}
```

- 이를 통하여 `Factory`클래스 말고 다른 하위클래스를 여러개를 만들어 적용할 수 있는 장점이 있다.

- Algorithm.php

```php
<?php

abstract class Algorithm
{
    protected $Composite;

    abstract public function setCpu($cpu);
    abstract public function setRam($size);
    abstract public function setStorage($size);

    public function getInstance() {
        return $this->Composite;
    }
}
```

- Product.php

```php
<?php

class ProductModel extends Algorithm
{
    public function __construct()
    {
        echo "Algorism ".__CLASS__."객체를 생성하였습니다.<br>";
        $this->Composite = new Computer();
    }

    public function setCpu($cpu)
    {
        // TODO: Implement setCpu() method.
        echo "CPU를 설정합니다.<br>";
        $this->Composite->_cpu = $cpu;
    }

    public function setRam($size): ProductModel
    {
        // TODO: Implement setRam() method.

        echo "RAM을 설정합니다.<br>";
        foreach($size as $mem) {
            echo "슬롯 " . $mem . "GB 장착 /";
            array_push($this->Composite->_ram, new Memory($mem));
        }
        echo "<br>";
        return $this;
    }

    public function setStorage($size): ProductModel
    {
        // TODO: Implement setStorage() method.
        echo "Storage를 설정합니다.<br>";
        foreach($size as $disk) {
            echo "슬롯 ".$disk."GB <br>";
            array_push($this->Composite->_storage, new Storage($disk));
        }
        return $this;
    }
}
```

- 추상 클래스를 상속받아서 구체화한 클래스는 다음과 같다.
- 생성자에 `Computer` 객체를 만들어서 `Composite` 객체를 통해서 클래스 멤버변수에 값을 세팅해줄 수 있다.

```php
<?php
class Computer {
    public $_cpu;
    public array $_ram = [];
    public array $_storage = [];

    public function __construct()
    {
        echo __CLASS__." 객체가 생성이 되었습니다.";
    }

    public function __toString()
    {
        return "이 컴퓨터의 사양은 CPU=". $this->_cpu.
        ", RAM= ".$this->memory()."GB".
        ", Storage= ".$this->storage()."GB".
        "입니다.\n";
    }

    public function memory(): int
    {
        $size = 0;
        foreach($this->_ram as $mem) {
            $size += $mem->getSize();
        }
        return $size;
    }

    public function storage(): int
    {
        $size = 0;
        foreach($this->_storage as $disk) {
            $size += $disk->getSize();
        }
        return $size;
    }
}
```

- `Computer` 클래스의 `__toString()` 함수는 매직메소드로 불리며, `__construct`도 마찬가지로 매직메소드이다.
  - 매직메소드는 `__method` 이름의 형태를 띈다.

```php
<?php

class Memory
{
    private mixed $size;

    public function __construct($size=null) {
        if($size) {
            $this->size = $size;
        }
    }

    public function setSize($size) {
        $this->size = $size;
    }

    public function getSize() {
        return $this->size;
    }
}
```

```php
<?php

class Storage
{
    private mixed $size;

    public function __construct($size=null) {
        if($size) {
            $this->size = $size;
        }
    }

    public function setSize($size) {
        $this->size = $size;
    }

    public function getSize() {
        return $this->size;
    }
}
```

# Prototype

## Prototype 이란?
-  생성 패턴 중 하나로 기존에 생성된 객체를 복제하여 생성하는 것

```php
<?php
class Hello {
    private $message;

    public function __construct($msg) {
        $this->message = $msg;
    }

    public function setMessage($msg) {
        $this->message = $msg;
    }

    public function getMessage() {
        return $this->message;
    }
}
```

```php
<?php 
inlude "hello.php";

$ko = new Hello("안녕하세요.");
$en = new Hello("hello World");
echo $ko->getMessage();
echo $en->getMessage();

// 안녕하세요.
// hello World
```
---

## 객체를 공유하는 얕은 복사
```php
include "hello.php";

// 객체를 생성합니다.
$obj = new Hello("안녕하세요");
echo "원복 내용=" .$obj->getMessage(). "\n";

// 객체를 복사합니다.
$obj2 = $obj;
$obj2->setMessage("Hello World");

// 원복 객체와 복제 객체의 메시지를 출력합니다.
echo "obj2 =" .$obj2->getMessage(). "\n";

echo "obj =" .$obj->getMessage() ."\n";

// 원복 내용=안녕하세요
// obj2 =Hello world
// obj =Hello world
```

- 얕은 복사는 처음 설정한 '안녕하세요' 값은 없어지고, 복제된 `$obj2`의 설정값은 `Hello world`가 출력됩니다.


## 객체를 공유하는 깊은 복사
- 일반적인 `= 연산자`로는 깊은 복사를 처리할 수 없으며 `Clonable` 인터페이스를 제공합니다.
- 실제 객체를 복사할 수 있는 `clone` 키워드를 제공한다.
- 생성자가 호출되는 과정이 없다.

```php
include "hello.php";

// 객체를 생성합니다.
$obj = new Hello("안녕하세요");
echo "원복 내용=" .$obj->getMessage(). "\n";

// 객체를 복사합니다.
$obj2 = clone $obj;
$obj2->setMessage("Hello World");

// 원복 객체와 복제 객체의 메시지를 출력합니다.
echo "obj2 =" .$obj2->getMessage(). "\n";

echo "obj =" .$obj->getMessage() ."\n";

// 원복 내용=안녕하세요
// obj2 =Hello world
// obj =안녕하세요
```
- 새로운 메모리 영역을 할당하여 실체 객체를 복제하는 것이므로 깊은 복사라고 할 수 있다. 깊은 복사를 사용하면 메모리의 자원이 소모된다.

> 객체 생성은 new 키워드를 사용하고, 객체 복제는 clone 키워드를 사용한다.

- 프로토타입 패턴은 복잡한 객체를 복제할 때 더 유용하고 적은 자원으로 빠르게 동일한 객체를 추가 생성할 수 있다.
- 복제는 *새로운 객체를 생성하는 것이 아니다.* 기존의 객체를 복사하면 객체 내의 상태값도 같이 복사가 되며 복제된 상태값을 변경할 수도 있고 별도의 **원형 관리자**를 도입하는 좋은 방식도 존재한다.