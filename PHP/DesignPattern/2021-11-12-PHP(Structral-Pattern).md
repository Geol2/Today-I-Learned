---
title: "PHP - Structral Pattern"
categories:
  - PHP
tags:
  - Design Pattern
last_modified_at: 2021-11-13T18:40:00-23:59:59
---

# 어댑터 패턴 (래퍼 패턴)

- 수정 불가능한 문제를 분리된 객체로 쉽개 해결할 수 있도록 도와주는 패턴
- 코드를 재사용하기 위한 인터페이스를 처리하고 그 인터페이스를 활용해 보정 코드를 작성하는 것

- 영국식 110V 전원 소켓을 이용하기 위해 표준 220V AC 플러그에 110V 변환 어댑터를 끼우는 것과 동일한 방식이다.

- 기존 시스템의 인터페이스가 업체에서 제공한 클래스와 맞지 않아 사용이 불가능할 때, 어댑터 패턴을 적용시켜 기존 시스템 변화없이 사용 가능하게 만듦

## 오래된 코드

- 오래된 코드를 재사용하기 위해 구조를 변경하는 패턴
- 최근에 개발된 코드들은 모두 갑자기 생겨난 것이 아니라, 수많은 개발자들이 노력한 덕분에 현재의 기능과 코드가 있는 것임
- 새로운 기능을 만들기위해 오래된 코드를 참고하거나 기존의 코드를 재사용도 한다
- 코드의 변화는 어느 상황에서든 일어날 수 있으므로 어느정도 예측이 필요하다

## 잘못된 코드

- 당시나 나중에 잘못된 코드가 발견이 될 수 있고 발견이 되면 수정을 해야하거나 재사용된 기능 중에 수정할 수 없는 코드들도 있다
- C나 JAVA에서 원본 소스코드를 분실해서 컴파일된 목적 파일만 남은 경우도 있다. 
- 이렇게 되면 유지보수는 불가능하며 문제를 직접 해결해야 하는데, 오류가 포함된 코드를 래핑하여 보정코드를 만들어서 사용하여 문제점을 우회하도록 한다

## 보정 코드

- 보정 동작이 너무 많으면 많은 영역의 코드가 수정되어야 하나, 가독성이 떨어지므로 별도의 객체를 생성해서 보정을 처리하는 것이 유리함

## 코드의 래퍼 처리

- 클라이언트 > 어댑터(변환처리객체) > 어뎁티(변환사용객체)

## 종류

- 클래스 어댑터 : 다중 상속 이용
- 객체 어댑터 : 구성을 이용

### 클래스 어댑터

- 다중 상속(클래스)을 이용하는 방식은 Ruby, JAVA나 PHP 등에서 지원하지 않고 있음
- 다중 상속은 C++, Python 등 지원한다

### 객체 어댑터

- 객체의 의존성을 이용해 문제를 해결함
- 기존 타겟인 객체의 인터페이스에 영향을 받으며, 인터페이스가 복잡할수록 많은 작업이 필요함

<img src="/assets/images/Adapter1.png">


- 기존 `MallardDuck` 등장

```php
interface Duck {
    public function quack();
    public function fly();
}

class MallardDuck implements Duck
{
    public function quack() {
        echo "꽥<br>";
    }

    public function fly() {
        echo "날고 있어요!<br>";
    }
}
```

- 새로운 `WildTurkey` 등장

```php
interface Turkey {
    public function gobble();
    public function fly();
} 
```

```php
class WildTurkey implements Turkey {
    public function gobble() {
        echo "골골<br>";
    }
    public function fly() {
        echo "짧은 거리를 날고 있어요!<br>";
    }
}
```

- `MallarDuck` 객체를 사용할 수 없어 `WildTurkey`객체를 사용하기 위해 어댑터 생성
- `interface`는 이미 다르므로 `Turkey`를 바로 사용할 수는 없음
- `Turkey`를 구현하는 작업이 필요함

```php
class TurkeyAdapter implements Duck {
    public Turkey $turkey;

    public function __construct() {
        $this->turkey = new WildTurkey();
    }

    public function turkeyAdapter($turkey) {
        $this->turkey = $turkey;
    }

    public function quack() {
        $this->turkey->gobble();
    }

    public function fly() {
        $this->turkey->fly();
    }
}
```

- 💨 테스트 코드

```php
$duck = new MallardDuck();

$turkey = new WildTurkey();

$adapter = new TurkeyAdapter();

echo "칠면조가 말합니다<br>";
$turkey->gobble();
$turkey->fly();

echo "<br>";
echo "<br>";

echo "오리가 말합니다.<br>";
$duck->quack();
$duck->fly();

echo "<br>";
echo "<br>";

echo "칠면조 어댑터가 말합니다.<br>";
$adapter->quack();
$adapter->fly();

// 결과 : 
// 칠면조가 말합니다
// 골골
// 짧은 거리를 날고 있어요!


// 오리가 말합니다.
// 꽥
// 날고 있어요!


// 칠면조 어댑터가 말합니다.
// 골골
// 짧은 거리를 날고 있어요!
```