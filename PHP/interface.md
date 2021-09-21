# interface

- 인터페이스 : 선언부만 만들어둔 추상적인 키워드.

```php
interface Product1 {
  public function create();
  public function createProduct();
}

interface Product2 {
  public function create();
  public function createProduct();
}

class Product implements Product1, Product2 {
  public function create() {
    return $this->createProduct();
  }
  public function createProduct() {
    return new Product();
  }
}

$fac = new Product();
$fac->createProduct();
```

- 구현부의 클래스에 `implements` 클래스를 통해서 위 예제처럼 여러 interface를 불러올 수 있다 ( 다중 상속 가능 )
- interface 내부의 함수들은 함수 선언부만 만들어두고 구현부의 클래스안에서 선언된 함수들을 빠짐없이 구현해주어야 한다 (abstract와 동일)
- 인터페이스 안에는 멤버변수를 사용하지 않는다
- 함수의 `static` 키워드를 사용하지 않는다
- `public` 으로만 선언이 가능하다 ( abstract, final 등등 사용불가 )
