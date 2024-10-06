# Axios

Axios를 사용해서 데이터를 서버에 전송할 수 있다.


## 동기 방식

```javascript
class Axios {

  async axiosPost(data, headers) {
    let result = null;
    let isEmpty = new IsEmpty;

    try {
        let promise = await axios.post(data.url, data.body, headers);
        if(!isEmpty.isEmpty(promise.data.data)) {
            result = promise.data.data;
        } else if(!isEmpty.isEmptyObject(promise.data.FILE_INFO)) {
            result = promise.data.FILE_INFO;
        }

    } catch (error) {
        console.log("axios error : " + error);
    }

    return result;
  };

}
```