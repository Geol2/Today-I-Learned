# Install

redis 는 별도 설치를 하여 사용하도록 한다

## ubuntu
```bash
# Ubuntu
apt-get install php-dev # pecl 설치를 위해..
apt-get install redis-tools # redis-cli 사용

pecl install redis
## all yes

phpenmod redis

php -r "phpinfo();" | grep redis # 모듈 설치 확인

service redis start
service redis restart
```

## mac
```bash
brew install redis
```

## Tutorials
```php
# 튜토리얼 페이지에서 간단하게 찾아서 사용한 코드이다
# new Redis() 는 .dll파일에 의해 로딩이 되어 별도 vendor 패키지를 설치하지 않고 사용할 수 있다.
<?php
$redis = new Redis();
try {
    $redis->connect('[ip 주소]','[포트]', 2.5, NULL, 150);
    $redis->auth('[비밀번호]');
    $key = 'myKey1';
    $value = ['val' => 'myValue', 'foo' => 'bar'];
    $ttl = 3600;
    $redis->set($key, json_encode($value));
    $value = $redis->get($key);
    print_r($value);
} catch(RedisException $e) {
    var_dump($e);
}
$redis->close();
```

```json
{
    //...
    "required" : {
        "ext-redis": "*"
    }
}
```
redis-cli 에서 `> get myKey1`을 한 뒤 string 값을 확인한다

## Setting

`/etc/redis/redis.conf` 에서 확인한다

### 비밀번호 설정

- requirepass란을 확인한다

```bash
# requirepass ...
```

- 설정이 변경되면 재시작하여 설정이 반영되도록 한다

```bash
service redis restart
```

## Connect

`$ redis-cli` 입력을 통해 간단하게 사용할 수 있다.

비밀번호가 설정되어 있다면,

인증하라는 경고 `(error) NOAUTH Authentication required.` 가 발생되고 키를 조회할 수 없다

### auth
```bash
# 다음과 같은 명령어를 사용해서 비밀번호 인증을 하도록 한다
> auth [비밀번호]
```
