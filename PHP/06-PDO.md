# 데이터 객체 PDO

데이터베이스 > PDO > CRUD

```php
try {
    $pdo = new PDO('mysql:dbname=myapp_test;host=127.0.0.1;', 'root', '1234');
    $pdo->exec("CREATE TABLE test(`id` INT PRIMARY KEY AUTO_INCREMAND)");
} catch(PDOException $e) {
    var_dump($e->getMessage());
}
```
