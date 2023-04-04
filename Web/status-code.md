# HTTP Status Code

## 1xx : Inforamtion

## 2xx : Success

## 3xx : Redirection

## 4xx : Client Error

- 401 Unauthorized
    ```
    요청에 사용자 인증이 요구된다.
    응답에는 요청된 리소스에 적용 가능한지를 포함하는 WWW-Authenticate 헤더가 포함되어야 한다.
    요청에 이미 자격인증이 포함되었다면 401을 표시하여 인증을 거부되었음을 나타낸다. (인증되지 않음)

    인증이 가능하지만 실패했거나 아직 제공되지 않은 경우에 사용한다 
    ```

    신원을 확인하는 단계로 인증이 되지 않은 상태에서 자원을 이용할 수 없음을 나타낸다.

- 403 Forbidden
    ```
    요청에 문제가 없지만 서버에서 응답이 거부가 되었을 때 사용한다 (인가되지 않음)
    401 Unauthorized과 달리 인증은 아무 차이가 없다 
    ```
    신원은 확인되었으나 해당 권한이 없을 때, 자원을 이용할 수 없음을 나타낸다.

## 5xx : Server Error
