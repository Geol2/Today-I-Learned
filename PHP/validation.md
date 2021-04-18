# PHP Validation

- 유효성 체크에 대한 설명.

---

1. 이메일 유효성 체크 예제 코드

```php
<?php>
if( !filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
  echo json_encode($json_error_email);
  exit;
}
```
