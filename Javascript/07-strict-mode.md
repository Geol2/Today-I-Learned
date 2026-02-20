# strict-mode

```javascript
"use strict";

// ... 최상단에 추가해야함!
```

JavaScript 를 더 엄격한 모드로 실행하도록 하는 지시문

일반적인 JavaScript는 문법 오류나 위험한 코드도 자동으로 수정하거나 무시하는 경향이 있다

꽤 오랫동안 호환성 이슈 없이 발전해왔는데 이 장점은 한 번 작성된 코드는 망가지지 않는다는 장점이 있지만

창시자들의 불완전한 결정이 영원히 박제된다는 단점이 존재한다

ES5가 등장 이후 새로운 기능이 추가되고 기존 기능 중 일부가 변경되기 시작했는데 이로 인해 호환성 문제가 발생할 수 있다

그래서 ES5 기본 모드에서는 활성화되지 않도록 설계되었고 `use strict`라는 특별한 지시자를 사용해 활성화 시에만 변경사항이 적용되게 되었다고 한다

"use strict"를 사용해서 더 엄격한 규칙을 적용하도록 한다

## 주요변화

1. 선언 없는 변수 할당 금지

```javascript
"use strict"
x = 1; // Error
var x = 1; // good
```

2. 읽기 전용속성 수정 금지

```javascript
'use strict';

var obj = {};
Object.defineProperty(obj, 'x', { value: 1, writable: false });
obj.x = 2; // Error
```

3. `delete` 연산자 제약

```javascript
'use strict';

var x = 1;
delete x; // Error
```

4. `eval()` 격리


```javascript
'use strict';

eval('var x = 1');
console.log(typeof x); // 'undefined'
```

5. `this` 바인딩 변화

```javascript
'use strict';

function test() {
  console.log(this);  // undefined (일반 모드에선 global/window)
}
test();
```

6. 함수 매개변수 중복 금지

```javascript
'use strict';

function sum(a, a, c) {  // Error
  return a + a + c;
}
```

7. `import/export`, 클래스를 사용하면 자동으로 `strict mode`가 적용

```javascript

class MyClass {
  method() {
    // strict mode에서 실행됨
  }
}
```