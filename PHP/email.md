# PHP Mailer

- PHP에서 버튼을 눌렀을 때, 메일을 보내기 위해서 라이브러리를 사용할 수 있다.
- compose를 사용하여 설치를 하였으며 vendor 폴더에 있는 함수를 불러서 사용할 수 있었던 것으로 확인이 되었다.

```php
try {
  $mail->Host = "smtp.google.com";                   // 메일서버 주소
  $mail->SMTPAuth = true;                            // SMTP 인증을 사용함
  $mail->isSMTP();
  $mail->Port = 465;                                 // email 보낼때 사용할 포트를 지정
  $mail->SMTPSecure = PHPMailer::ENCRYPTION_SMTPS;   // SSL을 사용함
  $mail->Username = "이메일 계정";                      // 계정
  $mail->Password = "비밀번호";                         // 패스워드
  $mail->CharSet = 'utf-8';
  $mail->Encoding = "base64";

  $mail->SMTPAuth = true;

  $mail->From = "보내는 사람 이메일";                     // 보낸사람
  $mail->FromName = "보내는 사람 이름";
  $mail->addAddress('받는사람 이메일', '받는사람 이름');    // 받는사람
  $mail->isHTML(true);

  $mail->Subject = $company.' 사의 제휴/광고 문의 신청서 건';
  $body = "<h1> 제목 : ". $subject ."</h1><h1> 회사명 : ". $company ."</h1>".
    "<h2> 이메일 주소 : ".$email."</h2><br>".
    "<h2> 작성자/직급 : ".$writer."</h2><br>".
    "<h2> 연락처 : ". $contact ."</h2>".
    "<p>". $content ."</p>";

  $mail->Body = $body;
  $mail->send(); // 전송
} catch (Exception $e) {
  $json_mail_error = array(
    'cd' => 400,
    'msg' => "전송 실패했습니다."
  );
  echo json_encode($json_mail_error);
  exit;
}
```

- 예제코드에 불과하며 입맛에 맞게 고쳐볼 필요가 있다.
