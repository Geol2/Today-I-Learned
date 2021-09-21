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