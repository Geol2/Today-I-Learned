# Truthy And Falsy

Falsy 값은 다음과 같다고 한다. `null`, `undefined`, `0`, `''`, `NaN`

나머지는 Truthy 한 값이다.

Falsy 체크는 다음과 하면 가독성에 유리하다.

```javascript
function print(person) {
  if (!person) { // Point
    console.log('person이 없네요');
    return;
  }
  console.log(person.name);
}

const person = null;
print(person);
```


아래과 print 함수 내에서 조건을 남발하는 경우는 좋지 않다.

```javascript
function print(person) {
  if (person === undefined || person === null) { // Point
    console.log('person이 없네요');
    return;
  }
  console.log(person.name);
}
```