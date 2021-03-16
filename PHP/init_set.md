# init_set(string $option, string $value) : string|false

- PHP 환경을 설정하기 위한 함수이다.
- init_set 과 관련된 상수에 대한 내용이다. [참고 링크](https://www.php.net/manual/en/ini.list.php)

```php
<?php
    ini_set('display_errors', 0);
    echo ini_get('display_errors'); // 0
    
    ini_set('display_errors', 1);
    echo ini_get('display_errors'); // 1
?>
```