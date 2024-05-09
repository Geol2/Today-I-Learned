# PHPMailer 사용법

PHPMailer 를 이용해서 이메일을 보내는 방법이다.

PHPMailer를 composer 로 설치도 해주어야 한다.

기본적으로 SMTP 서버 세팅이 끝났다고 가정했을 때, 아래와 같은 코드를 작성해주면 되는 것 같다.

```php
<?php
$toEmail = "big9401@naver.com";
$toName = "geol";

$subject = "asdfsadf";
$body = "body content";

# SSL 방식
# $smtpHost = "smtp.gmail.com";
# $smtpPort = 465;
# $smtpUsername = "big9401@gmail.com";
# $smtpPassword = "google-app-password"; # 앱 비밀번호를 사용해야 함
# $smtpSecure = "ssl";
# $smtpAutoTLS = false;

# TLS 방식
$smtpHost = "smtp.cafe24.com";
$smtpPort = 587;
$smtpUserName = "이메일";
$smtpPassword = "비밀번호";
$smtpSecure = false;
$smtpAutoTLS = false;

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMaile\Exception;
use PHPMailer\PHPMailer\SMTP;

require 'vendor/autoload.php';

$mail = new PHPMailer(true);
try {
  # $mail->SMTPDebug = 2;
  $mail->isSMTP();
  $mail->Host = $smtpHost;
  $mail->Port = $smtpPort;
  $mail->SMTPAuth = true;
  $mail->Username = $smtpUsername;
  $mail->Password = $smtpPassword;
  $mail->SMTPSecure = $smtpSecure;
  $mail->SMTPAutoTLS = $smtpAutoTLS;

  $mail->setFrom($fromEmail, $fromName);
  $mail->addAddress($toEmail, $toName);

  $mail->isHTML(true);
  $mail->Subject = $subject;
  $mail->Body = $body;

  $mail->send();
} catch (Exception e) {
  echo 'Exception Error : ' . $mail->ErrorInfo;
}

```