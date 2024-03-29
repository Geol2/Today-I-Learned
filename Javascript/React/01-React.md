# Class Component

## 간단한 첫 컴포넌트 생성

1. 파일 만들기
2. 코드 작성하기
3. 모듈 내보내기 및 불러오기

### props 사용하기

- `defaultProps`, `propTypes`는 꼭 필요한 개념은 아님

<img src="/assets/images/react/subject.png" width="360">

```javascript
import { Component } from "react";
import PropTypes from 'prop-types';

import './../App.css';
import './Header.css';

class Header extends Component {

    render() {
        const { name } = this.props;
        
        return (
        <header>
            <div className="h70px flex-container">
                <div className="center">
                    { name }
                </div>

                <div className="flex-right-items">
                
                </div>
            </div>
        </header>
        );
    }
}

Header.defaultProps = {
    name: 'devStack'
};

Header.propTypes = {
    name: PropTypes.string
};

export default Header;
```

### state 

- 컴포넌트 내부에서 바뀔 수 있는 값

- 클래스형 컴포넌트 : `state`
- 함수형 컴포넌트 : `useState`

---

# Hook

## useState

- 함수 컴포넌트에서 상태를 관리해야 한다면 사용할 수 있는 기능
- `const [value, setValue] = useState(0)` 에서 useState 함수의 파라미터는 기본값이 된다.
- `value` 는 상태값 설정, `setValue` 는 상태를 설정

### 클래스형 컴포넌트

```javascript
import {Component} from 'react';

class App extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            value : 0
        };
    }

    render() {
        return (
        <div className="App">
            <div className="Test">
                <p>
                    현재 카운트 값은 {this.state.value} 입니다.
                </p>
                <button onClick={ () => this.setState( {value: this.state.value + 1} ) }> Click </button>
            </div>
        </div>
        );
    }

}
```

### 함수형 컴포넌트

```javascript

// Counter.js
import { Component, useState } from 'react';

const Counter = () => {
    const [value, setValue] = useState(0);

    return (
        <div>
            <p>
                현재 카운터 값은 {value}입니다.
            </p>
            <button onClick={() => setValue(value + 1)} > + 1</button>
            <button onClick={() => setValue(value - 1)} > - 1</button>
        </div>
    )
}

export default Counter;

// App.js
render() {
    return (
        <Counter />
    )
}
```


## useEffect

- 컴포넌트가 렌더링 될 때마다 특정 작업을 수행하도록 설정할 수 있는 기능
- `componentDidMount`, `componentDidUpdate`를 합친 형태로 봐도 된다
- 첫 렌더링과 업데이트 시 실행하고 싶으면, `useEffect( () => {...} )`
- 첫 렌더링 시에만 동작하게 하고 싶으면, `useEffect( () => {...}, [])`
- 특정 값 업데이트 시 동작하게 하고 싶으면, `useEffect( () => {...}, [함수이름])`

### 함수형 컴포넌트

```javascript
// Counter.js
import { useEffect, useState } from 'react';

const Info = () => {
    const [name, setName] = useState('');
    const [nickName, setNickName] = useState('');

    useEffect( () => {
        console.log('렌더링이 완료되었습니다.');
        console.log(name, nickName);
    });

    const onChangeName = (event) => {
        setName(event.target.value);
    };

    const onChangeNickName = (event) => {
        setNickName(event.target.value);
    };

    return (
        <div>
            <input className='name' onChange={onChangeName}></input>
            <input className='nickName' onChange={onChangeNickName}></input>
            이름 : {name}
            닉네임 : {nickName}
        </div>
    )
}

export default Info;

// App.js
import { Component } from 'react';
import Counter from './Counter.js';

class App extends Component {

    render() {
        return (
        <div className="App">
            <Counter></Counter>
        </div>
        );
    }

}

export default App;
```

### 클래스형 컴포넌트

```javascript
import { Component, useEffect } from 'react';

import './App.css';

class App extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            name : '',
            nickName : ''
        };
    }

    componentDidMount() {
        console.log('componentDidMount()'); // 페이지 첫 렌더링 시, 실행됨
    }

    componentDidUpdate(prevProps, prevState) {
        console.log(prevState); //onChange 함수가 실행되면서 상태값이 변해서 해당 코드가 실행됨
    }

    onChangeName = (event) => {
        this.setState({name: event.target.value});
    }

    onChangeNickName = (event) => {
        this.setState({nickName: event.target.value});
    }

    render() {
        return (
            <div>
                <input className='name' onChange={this.onChangeName}/>
                <input className='nickName' onChange={this.onChangeNickName} />
            </div>
        );
    }
}

export default App;
```