<?php
abstract class AbFactory {
  public final function create() {
    return $this->createProduct();
  }
  abstract public function createProduct();
}

abstract class AbFactory1 {
  public final function create() {
    return $this->createProduct();
  }
  abstract public function createProduct();
}

interface Factory {
  public function create();
  public function createProduct();
}

interface Factory1 {
  public function create();
  public function createProduct();
}

// interface 를 사용함.
class ImProductCreate implements Factory, Factory1 {
  public function __construct() {
    echo 'ProductCreate 초기화';
  }
  public function create() {
    return $this->createProduct();
  }
  public function createProduct() {
    echo 'createProduct 생성';
  }
}

// Abstract 를 사용함.
class AbProductCreate extends AbFactory {
  public function __construct() {
    echo 'ProductCreate 초기화';
  }

  public function createProduct() {
    echo 'createProduct 생성';
  }
}

$fac = new AbProductCreate();
$fac->create();

$fac = new ImProductCreate();
$fac->create();

//ProductCreate::create();