# >= PHP 8.0

## apache 2.4

- 다운로드 후, `Apache24 > conf > httpd.conf` 파일의 설정이 필요함.
- Define SRVROOT `"D:\LocalServer\httpd-2.4.46-win64-VS16\Apache24"`
- dir_module 설정.

```
<IfModule dir_module>
  DirectoryIndex index.php index.html
</IfModule>
```

- (부가) Xdebug 설정

```
PHPIniDir "d:\LocalServer\php-8.0.10-Win32-vs16-x64"
LoadModule php_module "d:\LocalServer\php-8.0.10-Win32-vs16-x64\php8apache2_4.dll"
AddType application/x-httpd-php .html .php
AddHandler application/x-httpd-php .php
```

- Windows 서비스 등록 관련
  - 컴퓨터\HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\PHP8FastCGI

```
AppDirectory : D:\LocalServer\php-8.0.10-Win32-vs16-x64
Application : D:\LocalServer\php-8.0.10-Win32-vs16-x64\php-cgi.exe
AppPrameters : -b 127.0.0.1:9000 -c D:\LocalServer\php-8.0.10-Win32-vs16-x64\php.ini
```

## PHP 8.0

- extension_dir 설정

```
Windows 환경
extension_dir = "D:\LocalServer\php-8.0.10-Win32-vs16-x64\ext"
```

- 가장 하단에 `xdebug` 설정에 대한 아래 내용을 추가함.

```
[Xdebug]
zend_extension = xdebug
;zend_extension = "D:\LocalServer\php-8.0.10-Win32-vs16-x64\ext\php_xdebug-3.0.2-7.4-vc15-x86_64.dll"
xdebug.mode = debug
xdebug.start_with_request = yes
xdebug.client_port = 9003
```

- Xdebug Setting
  - https://xdebug.org/wizard 접속
  - index.php에서 phpinfo() 이용
  - 페이지 소스 보기를 이용해서 `ctrl + c` 후, wizard 페이지에 `ctrl + v`
  - 결과페이지를 이용하여 설정을 완료하면 됨.
