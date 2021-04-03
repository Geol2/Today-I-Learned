# substr()

## String.prototype.substr()

```javascript
const str = "Mozilla";

console.log(str.substr(1, 2));
// expected output: "oz"

console.log(str.substr(2));
// expected output: "zilla"
```

- substr의 첫번째 매개변수는 `str`의 인덱스 위치를 알려준다고 생각하면 편하다.
- `str.substr(1, 2)`의 `1`은 `o`가 된다. 그리고 `2`는 인덱스 위치에서부터 몇 글자를 표현해줄 것인지 알려준다고 생각하면 편해서 `oz`가 된다. 없으면 끝까지 다 읽어준다.
