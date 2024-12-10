# java-lotto-precourse

## 💪 프로젝트 개요

간단한 로또 발매기를 만든다.

- 로또 번호 숫자 범위는 1~45이다.
- 1개의 로또를 발행할 때 중복되지 않은 6개의 숫자를 뽑는다.
- 당첨 번호 추첨시 중복되지 않은 숫자 6개와 보너스 번호 1개를 뽑는다.
- 당첨은 1등 부터 5등까지 있다
    - 1등 : 6개 번호 일치 / 2,000,000,000원
    - 2등 : 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등 : 5개 번호 일치 / 1,500,000원
    - 4등 : 4개 번호 일치 / 50,000원
    - 5등 : 3개 번호 일치 / 5,000원
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행한다.
- 로또 1장 가격은 1000원이다.

## 🛠️ 구현할 기능 목록

### Input

- [ ] 구매 금액을 입력 받는다.
- [ ] 구매 금액이 1000원 단위인지 확인하고 몇 개를 구매했는지 반환한다.
- [ ] 당첨 번호를 입력 받는다.
- [ ] 보너스 번호를 입력 받는다.

### Output

- [ ] 몇 개를 구매했는지 출력한다.
- [ ] 구매한 결과를 출력한다.
- [ ] 당첨된 결과를 출력한다.
- [ ] 수익률을 출력한다. 이때 소수점 둘째자리에서 반올림한다.

### 구매한 당첨 번호들

- [X] 중복되지 않은 숫자여야한다.
- [X] 1부터 45 사이의 숫자여야한다.
- [X] 6개의 숫자여야한다.
- [X] 오름차순 순서대로 숫자들을 보관한다.

### 보너스 번호

- [X] 기존 당첨 번호와 중복이 되면 안된다.
- [X] 1부터 45 사이의 숫자여야한다.

### 랜덤

- [X] 로또 번호는 중복 없이 6개를 뽑는다.

### 로또 서비스

- [X] 사용자가 산 로또의 각 결과를 추출한다.
    - 1등 : 6개 번호 일치 / 2,000,000,000원
    - 2등 : 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등 : 5개 번호 일치 / 1,500,000원
    - 4등 : 4개 번호 일치 / 50,000원
    - 5등 : 3개 번호 일치 / 5,000원
- [ ] 총 수익을 계산한다.
- [ ] 수익률을 계산한다.
