## @media

첫 번째로 했던 자바스크립트 스터디에서 작업했던 Momentum 프로젝트가 있었다.

끝내기가 아까워서 반응형으로 만들어보았다. `@media` 를 사용해서 꾸며줄 수 있었다. 모바일에서도 제대로 보이기 위해서 해당 작업들을 진행했다.

PC 화면으로 지정되기 전까진 계속 모바일로 띄우도록 제대로 잡아줘야 할 것 같은 생각이 들었다.

```html
<!-- 뷰 포트 적용 -->
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
```

```css
@media all and (min-width: 1280px) {
    // 1280px 이상인 화면..
}

@media all and (max-width: 1279px) {
    // 1279px 이하인 화면..
}
```