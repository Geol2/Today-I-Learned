## 설정

1. `Setting > PHP > ...`

- 언어레벨 확인

2. `CLI Interpreter > + > Docker Compose`
- Server : Docker
- Configuration files : ./docker-compose.yml
- General > refresh

3. OK

4. `PHP > Debug > Validate > Local Web Server or Shared Folder`

- Path 및 localhost 확인
- validate 통과 될 때까지 확인

5. 크롬에 Xdebug 툴 설치

## Xdebug

- php-ini-overrides
```
[Xdebug]
zend_extension = xdebug
xdebug.mode=debug
xdebug.client_port=9003
xdebug.client_host=host.docker.internal
xdebug.start_with_request = yes
xdebug.discover_client_host = true
#xdebug.client_host=127.0.0.1
xdebug.log = xdebug.log
```