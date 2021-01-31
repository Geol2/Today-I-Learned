# ios_base::sync_with_stdio

## bool sync_with_stdio (bool sync = true);

### public static member function
### include : \<iostream>, \<ios>
---
기본적으로 iostream 객체와 cstdio 스트림은 동기화가 된다.<br>
이 뜻은 sync_with_stdio(true)를 사용한 것과 같은 효과를 나타낸다.

동기화가 되면 cout, printf 등을 혼합해서 사용해도 인터럽트가 발생되지 않지만,<br>
동기화를 끄게 되면 인터럽트가 발생될 가능성이 생겨서 문제가 발생할 수 있으므로 주의해서 사용하도록 한다.<br>

그럼에도 아래 구문을 사용하는 이유는 시간복잡도와 관계가 있어보인다.<br>
cin, cout은 scanf, printf보다 느리지만 동기화를 꺼주게 되면 시간 단축의 효과가 발생이 된다.

- sync_with_stdio(false);
- cin.tie(0);
- cout.tie(0);

[Docs](http://www.cplusplus.com/reference/ios/ios_base/sync_with_stdio/)