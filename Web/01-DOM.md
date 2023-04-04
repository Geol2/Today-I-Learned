# DOM

DOM 에 대한 개념

문서 객체 모델 (Document Object Model)이라고 불리며 HTML, XML 문서의 프로그래밍 interface 이다

DOM은 문서의 구조화된 표현을 제공하며 프로그래밍 언어가 DOM 구조에 접근할 수 있는 방법을 제공해서 구조, 스타일, 내용 등을 동적으로 하는데 돕는다.

nodes와 objects로 문서 표현을 하고 스크립트와 프로그래밍 언어에서 사용될 수 있게 연결시켜주는 역할을 한다.

DOM은 웹페이지의 객체 지향 표현이고 언어를 이용해서 DOM을 조작할 수 있다

```javascript
let para = document.getElementsByTagName("p");
alert(para[0].nodes);
```

property, method, event 들은 objects로 구성된다

DOM과 자바스크립트는 밀접하게 연결되어 있지만, 나중에는 분리되어 발전해왔다

API = DOM + JS

## DOM 에 접근하기 위한 방법?

특별히 할 건 없다. 모든 웹 브라우저는 DOM을 항상 사용하고 있다.

## 데이터 타입

1. `document` : `document type`의 `object`를 리턴할 때, 이 `object`는 `root document object` 자체다

2. `element` : DOM 안에서 생성되는 `element`를 리턴한다. `document.createElement()`가 `node`를 참조하는 `object`를 리턴함

3. `nodeList` : `elements`의 배열이고 `nodeList`의 `Items`는 `index`를 통해 접근 가능하다
    - `list.item(1)`
    - `list[1]`

4. `attribute` : `createdAttribute()`와 같은 메소드가 호출되는 것은 `attribute`에 대한 특별한 인터페이스를 노출하는 `object reference`이고 `attribute`는 `nodes`이다.

5. `namedNodeMap` : array와 유사하지만 items가 있어서 추가/삭제가 되며 name 또는 index에 의해 접근 가능하다

## DOM interfaces

HTML FORM element는 HTMLFormElement 인터페이스로부터 name property를 얻고

className property는 HTMLElement 인터페이스로부터 얻는다

### Interfaces와 Objects

Objects 들은 여러 개의 다른 interfaces와 연관되어 있다