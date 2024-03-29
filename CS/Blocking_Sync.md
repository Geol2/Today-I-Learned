# Blocking VS non-Blocking

제어의 관점

다른 주체가 작업할 때 자신의 **제어권**이 있는지 없는지로 볼 수 있다

## Blocking

자신의 작업을 진행하다가 다른 작업이 시작되면 기존 작업은 끝날 때까지 기다린 후 자신의 작업을 하는 것

다른 주체가 작업할 때, 자신에겐 제어권이 없다는 특징이 있다

### Blocking 예시

1. 서류 작성 후 상사에게 전달
2. 상사는 서류를 읽어보는 동안 직원에게 기다리라고 함
3. 상사는 서류를 다 읽은 후, 직원은 기존 일을 처리할 수 있음

## non-Blocking

다른 작업에 관련없이 자신의 작업을 하는 것

다른 주체가 작업할 때, 자신에게도 제어권이 있는 특징이 있다

### non-Blocking 예시

1. 서류 작성 후 상사에게 전달
2. 상사는 서류를 받고 직원에게 돌아가서 본인 할 일을 하라고 함
3. 직원은 자리로 돌아감

# Sync VS Async

작업 순서와 결과(처리)의 관점

결과를 돌려주었을 때, **순서와 결과에 관심**이 있는지 아닌지로 판단

## Synchronous

작업을 동시에 수행/종료하는 타이밍을 맞추거나, 끝나는 동시에 시작함

결과를 통해 처리를 하므로 결과와 순서에 관심이 있다고 할 수 있다

### Synchronous 예시

1. 서류를 작성 후 상사에게 전달
2. 상사가 서류를 읽어보는 동안 직원이 기다려도되고 다른 일을 해도 됨
3. 상사가 읽고 결과를 던져주면 결과를 통한 처리를 함


## Asynchronous

시작, 종료가 일치하지 않으며, 끝나는 동시에 시작하지 않음을 의미

결과에 대해 관심이 없음

### Asynchronous 예시

1. 서류를 작성 후 상사에게 전달
2. 상사가 서류를 읽어보는 동안 직원이 기다려도되고 다른 일을 해도 됨
3. 상사가 서류를 확인한 후, 해야할 일들을 정리해서 사원에게 나중에 확인하라고 적어서 보내줌
4. 사원은 언젠가 확인해서 처리함
