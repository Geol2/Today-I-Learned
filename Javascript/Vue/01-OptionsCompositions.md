# Options와 Composition API 간의 차이

## Options API

`this`를 주로 사용해서 작성되며, `data()`와 `methods` 등의 키워드가 사용되고 있다

```vue
<script>
export default {
  // data()에서 반환된 속성들은 반응적인 상태가 되어
  // `this`에 노출됩니다.
  data() {
    return {
      count: 0
    }
  },

  // methods는 속성 값을 변경하고 업데이트 할 수 있는 함수.
  // 템플릿 내에서 이벤트 헨들러로 바인딩 될 수 있음.
  methods: {
    increment() {
      this.count++
    }
  },

  // 생명주기 훅(Lifecycle hooks)은 컴포넌트 생명주기의
  // 여러 단계에서 호출됩니다.
  // 이 함수는 컴포넌트가 마운트 된 후 호출됩니다.
  mounted() {
    console.log(`숫자 세기의 초기값은 ${ this.count } 입니다.`)
  }
}
</script>

<template>
  <button @click="increment">숫자 세기: {{ count }}</button>
</template>
```


## Composition API

```vue
<script setup>
import { ref, onMounted } from 'vue'

// 반응적인 상태의 속성
const count = ref(0)

// 속성 값을 변경하고 업데이트 할 수 있는 함수.
function increment() {
  count.value++
}

// 생명 주기 훅
onMounted(() => {
  console.log(`숫자 세기의 초기값은 ${ count.value } 입니다.`)
})
</script>

<template>
  <button @click="increment">숫자 세기: {{ count }}</button>
</template>
```


## 어떤 것을 사용하는 것이 좋을까?

둘 다 어떤 것을 사용하는지는 호환이 되므로 크게 상관없다고 한다. 

하지만, `Options`는 초보자나 작은 애플리케이션 환경에서 사용된다.

`Composition`은 대형 어플리케이션에 사용되는 것을 권장하고 있고 동작 방식의 이해만 있다면 더 유연한 패턴 개발이 가능하다고 한다.

`Options`는 `Composition` 위에서 동작된다.