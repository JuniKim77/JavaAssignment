8장, 상속 vs 컴포지션



연관 = 집합 + 컴포지션(이지만, 통상 컴포지션이라고 부름)

- OO에서 재 사용성을 중요시하는 이유?
  - '바퀴를 다시 발명하지 마라. (Don't reinvent the wheel)'
  - 바퀴는 이미 동작과 상태가 명확한 물체
    - 설계, 구현, 테스팅까지 끝남.
    - 바퀴만 전문적으로 제조 혹은 판매하는 업체가 잇다.
  - 바퀴를 발명하지 말고 사다가 유용한 물체를 만들어라. (ex) 자전거 자동차
  - 바퀴를 다시 발명하려 시간낭비 할 필요가 없다.



- 클래스를 재 사용하면 좋은 점

1. 설계 & 코딩에 사용하는 시간을 절약

   - but, 실전에서 100% 적용은 불가능하다

   - 프로그램이 미래에 어떻게 변할지 예측이 불가능하기 때문.

   - ***재사용성에 눈이 멀어 잘못된 바퀴를 장착할 수도 있다.***

     : 예) 아날로그 벽시계를 상속받은 디지털 손목시계 -> 벽에도 붙이고, 손목에도 차고?

     : 오토바이로 부터 만든 자동차 -> 오토바이 2개를 합친 자동차?

2. 테스트에 걸리는 시간을 절약

   : 이미 테스트까지 끝난 클래스를 다시 테스트할 필요가 없다.

   : 상속시 부모 클래스는 이미 테스트가 끝난 상황.

   : 하지만 부모 클래스를 테스트 할 필요가 아예 없을까?

   - 새로운 방법으로 부모 클래스를 사용한다면?

     : 자식 클래스에서 부모 클래스를 다른 방식으로 사용.

     : 새로운 버그를 찾을 수도.

   - 혹은 부모 클래스를 변경한다면?

     : 사람은 구체적인 것을 먼저 본 뒤 추상화를 한다.

     : 따라서 새로운 자식 클래스가 상속받을 수 있도록 수정할 수 있다.
   
3.  관리 비용의 절약

   - 코드 중복이 없다. 

   - 관련된 코드가 모두 한 파일에 있다.

     -> 조작 & 데이터 모두 한 파일로 파악 가능

     -> 하지만 재사용성을 위해 클래스를 잘게 나누면 파일이 너무 많아진다.

- 결론 : 밸런스를 잘 유지해야 한다. **재사용성**과 **유지관리** 사이 <u>장점과 단점</u>이 있다.



OO 모델링 실력 높이기

- 많은 연습이 필요하다.

- 몇 개의 베스트 프렉티스로는 간단히 정리가 불가능

  why?

  	- 주관성이 높다.
  	- 너무나 다양한 프로그램이 존재한다.

  **따라서 익숙해질 때 까지 연습해야 한다.**

  : 한 가지로는 안 된다. 상황마다 대응하는 방법이 달라 계속적인 노력이 필요하다.

OO 모델링에는 지름길이 없다.

1. 많은 코딩 경험많이 해답 -> 아무리 성적 1등이라도 코딩양은 부족 (기본)

2. 도움 -> 다른 사람의 코드를 많이 사용해보기, 유명한 오픈소스 프로젝트, 괜찮은 프레임워크, 코드 리뷰를 주고 받자 (도움)

   : 가장 중요한 것은 기본이며, 도움만으로는 안된다.

**경험이 중요하다.** : 주관성이 강해질 수록 약장수가 많아짐. 

하지만 <u>''이런 원칙만 따르면 된다""</u>는 것은 없다.  -> **실버 블렛은 없다.**

프로그래밍은 직관성이 높은 편이지만 OO는 예외다.



상속 vs 컴포지션, 선택에 따른 4가지 기준

- 둘다 재사용성이 목적, 따라서 가능/불가능 측면에서만 보면 많은 경우에 둘다 사용 가능.
- 따라서 둘 중 하나를 사용하기 위해선 원칙이 필요하다.

<특수한 경우>

1. 기계상의 차이로 하나를 고를 때
2. 용도 때문에 상속을 골라야 할 때
3. 관리의 효율성을 고려할 때

<누구나 동의하는 것>

4. 그외 일반적인 상황



1. 기계상의 차이 때문에 하나를 골라야 할 경우,

- 상속과 메모리

  : 개체 생성시, 메모리가 하나의 덩어리

  : 상속받은 개체는 한 번에 메모리 할당을 함 (한 번의 new)

- 컴포지션과 메모리

  : 개체 생성시 메모리 여러 덩어리

  : 바깥 new, 컴포지션 new, 메모리를 두 번 할당한다.

  : 다만 포인터로 연결되어 있다.

- 용량 자체는 큰 차이가 없지만, 실행 성능에 많은 영향을 미친다.

이유) CPU와 메모리 사이의 데이터 전송 횟수 때문.

- 상속 모델로 만든 개체 : 개체가 한 번에 캐시 메모리에 들어갈 확률이 높다.
- 컴포지션으로 만든 개체 : 개체 내 부품 수 만큼 캐시 메모리로 로딩할 확률이 높다.

**엑세스를 몇 회 반복하는가는 성능에 큰 영향을 미친다.**

- 두 번째 병목점
  - 새로운 메모리 할당 + 해제 -> 언어에 따라 다르지만, 할당과 해제 둘 중 하나는 반드시 느리다.
  - 상속은 메모리 할당과 해제가 한 번씩 & 컴포지션은 한번 + 부품 수 만큼

2. 용도 때문에 상속을 고를 수 밖에 없을 때

- 다형성 : 부모에서 처리하면, 자식에 따라 처리가 달라진다.

  : 다른 형의 개체들을 한꺼번에 처리하고 싶을 때 유용

  1. 다양한 개체들을 리스트에 저장
  2. 거기서 같은 함수를 호출 (다형적으로)

  -> 다형성은 상속을 필요로 한다.

3. 관리의 효율성을 고려할 때

   1. 상속이 더 나을 때

      : 컴포지션의 경우 내부에서 부모(에 속하는) 메서드를 호출하는 릴레이 함수를 만들어야 함

      : 상속 모델시 자식 개체 상에서 부모의 메서드 호출 가능

   2. 컴포지션이 더 나을 때

      - 깊은 상속 관계 

        : 부모 클래스를 바꾸면 아래 모든 자식 클래스를 바꿔야 함.

        : 자식 클래스에서 문제가 없는 지 모두 확인해야 함.

        : 컴포지션도 비슷한 문제가 있으나, 상속보다는 덜하다.

        <u>상속보다 조립성을 강조했기 때문이다.</u>

        인터페이스 & 다형성이 이런 문제를 완화한다.

4. 일반적인 경우 : *상식*적으로 생각할 것. -> 코딩 표준 같은 것. (상식 : 일반적인, 공통적인)

   - 같은 코드에 다른 의견을 주면 리뷰하는 사람이 헷갈려 함.

   - 같은 회사 내에서도 리뷰보는 사람마다 다른 이야기를 한다. -> 빨리 통과할 수 있는 코드 리뷰어에게 감.ㅋㅋ

     So, 팀 간의 규칙들은 존재해야 한다.

   ### [has - a, is - a 관계에 충실하자]

   has-a : 컴포지션, 모니터 & 본체가 분리 (조립식 컴퓨터)

   is-a : 상속, 모니터 & 본체가 하나 (일체형 노트북)

   -> 내가 얼마나 아느냐에 따라서 구성에 영향을 준다. (노트북이어도 부품단위로 볼 수 있음)



#### 엔티티 컴포넌트 시스템(entity component system)

-> 엔터테이먼트 등 비즈니스적인 이유로 많이 바뀌는 상황에서 상속을 포기하고 컴포넌트

- 프로그래머가 컴포지션을 선호하는 또 다른 예
- 코드 변경 없이 자유롭게 개체를 만들 수 있게 하는 게 목적
- 아키텍처 패턴 중 하나 (중요하지 않음)
- 특히 게임 업계에서 많이 사용 (ex) Unity 게임 오브젝트

<img src ="data:image/png;base64,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">

- 현재 구조의 문제

  : 기획자 A가 마을 내를 자유롭게 돌아다니는 개선된 NPC를 기획함.

  : 코드 수정 후 테스트 해보니 재미가 없음.

  : 다시 프로그래머에게 부탁해서 롤백함 -> 이런 일이 반복되면 갈등이 깊어진다.

  - 여러 시도를 해봐야 게임이 재밌어진다.

  - 사람은 직접 만들어 시도 해보지 않으면 모르기 때문이다.

  - 하지만 상속 시스템에서는 업무 효율성이 떨어진다. -> 계속 프로그래머에게 부탁해야 하니까.

  - 재컴파일 없이 게임 기획자가 원하는 대로 조립할 수는 없을까?

    ##### -> 엔티티 컴포넌트 시스템
