# Creating a scene (장면 만들기)

three.js 에 대한 간략한 설멍을 하고 있다. 간단한 실습 예제의 설명과 코드를 첨부.

시작하기

```shell
npm install three
npx http-server
```

```bash
.
├─ js
│  └─ three.js
├─ node_modules
│  └─ three
├─ index.html
├─ package-lock.json
└─ README.md
```

다음과 같은 폴더 구조를 만들었다.

`js/three.js` 에 그릴 수 있는 장면을 만드는 코드들을 삽입했다.

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>My first three.js app</title>
		<style>
			body { margin: 0; }
			canvas { display: block; }
		</style>
	</head>
	<body>
		<script type="module" src="/js/three.js"></script>
		<script>
			// Our Javascript will go here.
		</script>
	</body>
</html>
```

`type` 을 모듈로 지정하지 않으면 `npx http-server`에서 작동하지 않았다.

```javascript
import * as THREE from '/node_modules/three/build/three.module.js';

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );
```

`scene`을 만들고 `PerspectiveCamera`라는 종류의 카메라를 사용했다.

## PerspectiveCamera(field of view, aspect ratio, near, far)

1. field of view : 시야각
2. aspect ratio : 종횡비
3. near, far : far 값보다 멀리있는 요소나 near 값보다 가까이 있는 요소는 렌더링하지 않는다,

## WebGLRenderer

```javascript
const renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );
```

`WebGLRenderer`를 통해 생성된 `renderer` 인스턴스는 높이와 너비 세팅이 필요하다.


```javascript
const geometry = new THREE.BoxGeometry( 1, 1, 1 );
const material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
const cube = new THREE.Mesh( geometry, material );
scene.add( cube );
```

간단하게 큐브를 만들어볼건데, `BoxGeometry`를 이용해서 `MeshBasicMaterial`의 녹색을 설정하여 메쉬(솔리드 형상)를 만들어내도록 한다.

아직은 아무것도 보이지 않는데, 보이기 위해 scene을 렌더링하는 작업이 필요하다

```javascript
function animate() {
	requestAnimationFrame( animate );

    cube.rotation.x += 0.01;
    cube.rotation.y += 0.01; //애니메이션 효과

	renderer.render( scene, camera );
}
animate()
```

여기선 `setInterval` 대신 특화된 `requestAnimationFrame`을 사용을 권장한다.

`requestAnimationFrame`는 1초에 60번 장면을 호출한다. (60Hz와 비슷한걸까?ㅋㅋ)


최종코드는 아래와 같으나, 실습예제가 어떻게 움직이는지만 확인했다.

```javascript

// three.js 최종
import * as THREE from '/node_modules/three/build/three.module.js';

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );

const renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );


const geometry = new THREE.BoxGeometry( 1, 1, 1 );
const material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
const cube = new THREE.Mesh( geometry, material );
scene.add( cube );

camera.position.z = 5;


function animate() {
	requestAnimationFrame( animate );

    cube.rotation.x += 0.01;
    cube.rotation.y += 0.01;

	renderer.render( scene, camera );
}
animate();
```

<img src="/assets/images/three/cube.png">


이 과정에서 시작하기의 장면 만들기부터 로컬 환경에서 구동방법까지 일부 알아보고 동작이 됨을 확인했다.