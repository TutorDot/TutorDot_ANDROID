<역할>   
캘린더뷰, 수업일지뷰, 알림뷰, 마이페이지뷰, 로그인
- 김기현: 수업일지뷰 + 캘린더뷰
- 김회진: 캘린더뷰
- 이정민: 알림뷰 + 로그인뷰(시작부분부터 화면 연결) + 마이페이지



## 1. git

### 1) 깃커밋 형식

Add : 새로운 파일 추가

Update : 기존 파일 업데이트, 기능 추가

Fix : 기존 기능 보완

ex) Add BottomNavigation Fragments

바텀네비에 들어가는 프래그먼트들 추가함 이라는 뜻^^

### 2)깃 브랜치

kihyeon, hoijin, jungmin 

각자 이름으로 브랜치를 만들어서 누가 어느 부분을 수정했는지 알기 편하도록 함

## 2. 파일 정보

### 1)파일명 통일

뷰이름 + 어댑터

뷰이름 + 액티비티

뷰이름 + 프래그먼트

뷰이름 + ...

## 3. 목표일정

[목표일정](https://www.notion.so/a85e75bc821d43ba850687d376d50c6f)

## 4. 사용라이브러리와 목적
### 1) 리사이클러뷰

implementation 'androidx.recyclerview:recyclerview:1.1.0'

→ 캘린더 일정 목록 표시에 사용.

### 2) material 디자인 라이브러리

implementation "com.google.android.material:material:1.2.0-alpha05"

→ BottomNavigation 구글에서 제공하는 디자인 라이브러리.

### 3) 동그란 이미지 커스텀 뷰 라이브러리

implementation 'de.hdodenhof:circleimageview:3.1.0

→ 마이페이지뷰 동그란 프로필 적용 수정예정

### 4) Material Calendar 라이브러리

compile 'com.prolificinteractive:material-calendarview:1.4.3'

→ 캘린더뷰에서 캘린더 적용에 사용. 조금 수정하여 커스텀 예정.

### 5) 구글 캘린더 api

→ 캘린더뷰 캘린더에 사용 예정


## 5. 프로젝트 구조


Tutor. 앱 실행 -> 온보딩 화면 -> 로그인/회원가입 화면   
로그인의 경우 튜터, 튜티 역할 선택 후 로그인(이메일, 비밀번호 입력) -> 총 4개의 네비게이션바에서 프래그먼트 형식으로 구성되어있다. 맨 처음 보이는 것은 캘린더뷰이다.   
1) 캘린더뷰: 캘린더뷰에서 상단의 토글버튼(전체/특정 과외)으로 전체의 일정을 볼 수도 있고 특정 과외의 정보만을 볼 수 있다. 오른쪽 하단의 플로팅 버튼을 누르면 일정 추가뷰로 이동한다. 괴외 종류에 따라 색상이 다르게 추가 가능. 일정 추가 후 캘린더뷰에서 각 일정을 클릭하면 해당 일정의 상세 정보를 보여주는 뷰(일정 정보뷰)로 이동한다. 캘린더에 일정이 등록되면 자동으로 갱신된다. 시간, 장소가 보여지고 일정 이름 옆의 편집 버튼을 누르면 일정 수정뷰로 이동한다.   
2) 수업 일지뷰: 전체 과목의 수업 일지 목록이 보인다. 토글 버튼으로 특정 과외 일지만 볼 수 있고 상단에는 과외 시간 달성률 막대그래프가 나타난다. 특정 일지를 누르면 진도, 숙제, 숙제 이행률을 입력할 수 있다.   
3) 알림뷰: 수업일지 변경, 과외 전 알림, 수업 정보 변경, 수업료 입금 확인 등의 알림이 뜬다.   
4) 마이뷰: 간편 프로필 밑에 자신의 과외 목록이 있으며 버튼 클릭 시 과외를 추가할 수 있다. 과외 추가뷰로 이동하여 과목 색깔, 학생이름, 시간당 금액, 계좌 정보, 정기 수업시간을 입력한다. 특정 과외를 누르면 해당 과외 정보를 볼 수 있다. 그 중에서 과외 초대부분이 있는데 과외 초대뷰에서 초대 코드를 받을 수 있다. 방삭제/연결해제로 과외를 삭제할 수 있다.   
수업료 입금 알림, 수업 전날 알림, 정보 숨김 알림을 받을지 받지 않을 지 설정할 수 있다.


## 6. 핵심 기능 구현 방법 정리 / 구현화면

### 1) 스플래시

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/be7a087a-28be-4560-abe4-77d8f5307bf7/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/be7a087a-28be-4560-abe4-77d8f5307bf7/Untitled.png)

- Handler().postDelayed를 이용해 스플래시 화면을 보여줄 시간 설정

### 2) 로그인뷰

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3ba19ae7-7b05-471c-b034-b00f0fb4a582/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3ba19ae7-7b05-471c-b034-b00f0fb4a582/Untitled.png)

- imageView를 사용해 로고와 핸드폰 디자인 추가
- editText를 사용해 이메일과 비밀번호 입력창 추가
- imageButton를 이용해 로그인버튼 추가
- activity_login.kt 은 서버 연동전이므로 이메일과 비밀번호 입력 여부만 확인하고 로그인 후 calendaractivity로 연결

### 3) 회원가입뷰

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/408f05d0-dd46-47b2-b15a-67eaf5ad03d6/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/408f05d0-dd46-47b2-b15a-67eaf5ad03d6/Untitled.png)

- imageView를 사용해 로고 추가
- editText를 사용해 이름, 이메일, 비밀번호, 비밀번호 확인 입력창 추가
- imageButton를 이용해 튜터/튜티 선택, 동의, 회원가입 버튼 추가
- activity_signup.kt 은 서버 연동전이므로 모든 칸 입력 여부만 확인→비밀번호, 비밀번호 확인칸 같은지 확인→ 튜터/튜티 선택여부 확인→동의여부 확인하여 회원가입 후 loginactivity로 연결

### 4) 캘린더뷰

개발중..!

### 4-1) 일정추가

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4ee66c30-7436-4aee-bf31-e5d58fcfb48e/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4ee66c30-7436-4aee-bf31-e5d58fcfb48e/Untitled.png)

- imageView를 사용해 상단바 추가
- editText를 사용해 위치정보 입력
- imageButton을 이용해 취소, 저장버튼추가
- timePicker을 이용해 수업 시작, 종료시간 입력
- datePicker을 이용해 날짜 입력
- Linearlayout을 사용하는 이유는 과외 색상, 과외 이름, 토글버튼을 가로 정렬을 하기 위해서이다.
- activity_login.kt 은 서버 연동전이므로 이메일과 비밀번호 입력 여부만 확인하고 로그인 후 calenderactivity로 연결

### 4-2) 일정정보

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/77bd1e65-fc05-468e-8c4b-d0d0ae43c442/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/77bd1e65-fc05-468e-8c4b-d0d0ae43c442/Untitled.png)

- textView를 이용해 정보 띄우기
- imageView를 사용해 상단바 추가
- imageButton을 이용해 일정 삭제 버튼추가

### 4-3) 일정수정

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/479915eb-e485-446c-8f2f-8ba3e2f84db0/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/479915eb-e485-446c-8f2f-8ba3e2f84db0/Untitled.png)

- textView를 이용해 정보 띄우기
- imageView를 사용해 상단바 추가
- imageButton를 이용해 수정 버튼으로 내용 수정기능, 취소, 저장버튼추가

### 5) 일지뷰

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b4f12c16-a209-498d-9ab3-d5b0e216c678/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b4f12c16-a209-498d-9ab3-d5b0e216c678/Untitled.png)

- ConstraintLayout으로 상단뷰, 진행률 레이아웃, 일지 아이템뷰, 숙제 이행률 구성
- 프로그레스바와 일지창 구성
- 전체 뷰에 맞는 폰트 적용
- 중첩 RecyclerView를 이용해 일지 날짜와 일지 아이템 구성

*하위 리사이클러뷰 - 일지 아이템

*상위 리사이클러뷰 - 날짜와 일지 아이템 리사이클러뷰

- 월 수업일지 양쪽 화살표 클릭 이벤트 추가
- 일지 아이템 버튼 클릭 이벤트 추가
- 커스텀 ProgressBar 이용

### 6) 알림뷰

개발중..!

### 7) 마이페이지뷰

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/20b41bf5-63b3-43de-be45-bfd9daabb8cb/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/20b41bf5-63b3-43de-be45-bfd9daabb8cb/Untitled.png)

- imageView를 사용해 상단바, 프로필 추가
- imageButton을 이용해 수업추가, 버전정보, 개발자정보, 비밀번호 변경, 로그아웃, 서비스탈퇴 버튼 추가
- switch를 이용해 수업료 알림과 수업시작 전 알림 선택 추가
