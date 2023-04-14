# crontab

- 스케줄러와 같은 기능을 하며, 특정시간에 특정 작업을 하는 기능이다.

- `crontab`을 확인하면,

```shell
usage: crontab [-u user] file
       crontab [-u user] { -e | -l | -r }

crontab -l
crontab -e
crontab -r
```
- 흔히, 세 가지로 사용될 수 있는 듯하다.

## crontab -l
- 현재 예약 실행 중인 작업들의 리스트를 보여준다.

## crontab -l > [파일경로].txt
- 현재 예약 실행 중인 작업들의 리스트를 백업한다.

## crontab -e
- 현제 예약 실행 중인 작업들의 리스트를 편집할 수 있다.

## crontab -r
- 현재 예약 실행 중인 작업들의 리스트를 삭제한다.

## 주기별 예제

### 간단한 설명

```markdown
*  * *  *   *      /home/cron/test.sh
분 시 일 월 요일(0-7)  실행할 명령어

분(0-59)
시(0-23)
일(0-31)
월(1-12)
요일(0-7, (0,7)=일요일, 1=월 ...)
명령어(실행할 명령어)
```

### 간단한 예제

```shell
* * * * * /home/cron/test.sh
```
- 매 분 test.sh를 실행한다.

```shell
0,20,40 * * * * /home/cron/test.sh
```
- 매일 매 시간마다 0, 20, 40분에 test.sh를 실행한다.

```shell
0-30 * * * * /home/cron/test.sh
```
- 매일 매 시간마다 0~30분까지 매분 test.sh를 실행한다.

```shell
*/5 * * * * * /home/cron/test.sh
```
- 매일 매 시간마다 5분마다 test.sh를 실행한다.


## Codeigniter4 crontab 관련 예제

- 크론탭에서 `클래스명` `함수명`/`파라미터`로 크론을 생성할 수 있다.

```shell

* * * * * /usr/bin/php -q [클래스명] [함수명] >> [로그파일위치]/*.log

* * * * * /usr/bin/php -q [클래스명] [함수명]/[파라미터명]/*.log

```
