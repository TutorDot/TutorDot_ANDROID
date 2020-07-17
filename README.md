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
![구조](https://user-images.githubusercontent.com/41908152/86928004-f8071b00-c16e-11ea-9cca-07a36a77ef21.JPG)


Tutor. 앱 실행 -> 온보딩 화면 -> 로그인/회원가입 화면   
로그인의 경우 튜터, 튜티 역할 선택 후 로그인(이메일, 비밀번호 입력) -> 총 4개의 네비게이션바에서 프래그먼트 형식으로 구성되어있다. 맨 처음 보이는 것은 캘린더뷰이다.   
1) 캘린더뷰: 캘린더뷰에서 상단의 토글버튼(전체/특정 과외)으로 전체의 일정을 볼 수도 있고 특정 과외의 정보만을 볼 수 있다. 오른쪽 하단의 플로팅 버튼을 누르면 일정 추가뷰로 이동한다. 괴외 종류에 따라 색상이 다르게 추가 가능. 일정 추가 후 캘린더뷰에서 각 일정을 클릭하면 해당 일정의 상세 정보를 보여주는 뷰(일정 정보뷰)로 이동한다. 캘린더에 일정이 등록되면 자동으로 갱신된다. 시간, 장소가 보여지고 일정 이름 옆의 편집 버튼을 누르면 일정 수정뷰로 이동한다.   
2) 수업 일지뷰: 전체 과목의 수업 일지 목록이 보인다. 토글 버튼으로 특정 과외 일지만 볼 수 있고 상단에는 과외 시간 달성률 막대그래프가 나타난다. 특정 일지를 누르면 진도, 숙제, 숙제 이행률을 입력할 수 있다.   
3) 알림뷰: 수업일지 변경, 과외 전 알림, 수업 정보 변경, 수업료 입금 확인 등의 알림이 뜬다.   
4) 마이뷰: 간편 프로필 밑에 자신의 과외 목록이 있으며 버튼 클릭 시 과외를 추가할 수 있다. 과외 추가뷰로 이동하여 과목 색깔, 학생이름, 시간당 금액, 계좌 정보, 정기 수업시간을 입력한다. 특정 과외를 누르면 해당 과외 정보를 볼 수 있다. 그 중에서 과외 초대부분이 있는데 과외 초대뷰에서 초대 코드를 받을 수 있다. 방삭제/연결해제로 과외를 삭제할 수 있다.   
수업료 입금 알림, 수업 전날 알림, 정보 숨김 알림을 받을지 받지 않을 지 설정할 수 있다.


## 6. 핵심 기능 구현 방법 정리 / 구현화면

### 1)Startpage
#### 1-1) 스플래시

- Handler().postDelayed를 이용해 스플래시 화면을 보여줄 시간 설정
#### 1-2) 온보딩

![image](https://user-images.githubusercontent.com/41908152/87784428-3d170580-c871-11ea-9df1-d3302138089a.png)

![image](https://user-images.githubusercontent.com/41908152/87784470-5029d580-c871-11ea-968d-480d7d0b7bac.png)

![image](https://user-images.githubusercontent.com/41908152/87784450-46a06d80-c871-11ea-81ce-42016218a812.png)

![image](https://user-images.githubusercontent.com/41908152/87784493-60da4b80-c871-11ea-8e95-28c5a46f6033.png)

### 2)로그인& 회원가입

![image](https://user-images.githubusercontent.com/41908152/87784535-751e4880-c871-11ea-8bc9-7fc2e61cf312.png)
- imageView를 사용해 로고 추가
- editText를 사용해 이름, 이메일, 비밀번호, 비밀번호 확인 입력창 추가
- imageButton를 이용해 튜터/튜티 선택, 동의, 회원가입 버튼 추가
- activity_signup.kt 은 서버 연동전이므로 모든 칸 입력 여부만 확인→비밀번호, 비밀번호 확인칸 같은지 확인→ 튜터/튜티 선택여부 확인→동의여부 확인하여 회원가입 후 loginactivity로 연결

![image](https://user-images.githubusercontent.com/41908152/87784582-8b2c0900-c871-11ea-85e5-25395fa5d041.png)
- imageView를 사용해 로고와 핸드폰 디자인 추가
- editText를 사용해 이메일과 비밀번호 입력창 추가
- imageButton를 이용해 로그인버튼 추가
- activity_login.kt 은 서버 연동전이므로 이메일과 비밀번호 입력 여부만 확인하고 로그인 후 calendaractivity로 연결


### 3)캘린더 뷰

![image](https://user-images.githubusercontent.com/41908152/87784618-98e18e80-c871-11ea-8475-71a5e2011f3c.png)

#### 3-1) 일정정보

![image](https://user-images.githubusercontent.com/41908152/87784645-a72faa80-c871-11ea-999d-6722d906d2ff.png)
- textView를 이용해 정보 띄우기
- imageView를 사용해 상단바 추가
- imageButton을 이용해 일정 삭제 버튼추가

#### 3-2) 일정수정

![image](https://user-images.githubusercontent.com/41908152/87784663-b0207c00-c871-11ea-96cc-46f424e06325.png)
- textView를 이용해 정보 띄우기
- imageView를 사용해 상단바 추가
- imageButton를 이용해 수정 버튼으로 내용 수정기능, 취소, 저장버튼추가

#### 3-3) 일정추가

![image](https://user-images.githubusercontent.com/41908152/87784695-be6e9800-c871-11ea-8599-9b2b70c98178.png)
- imageView를 사용해 상단바 추가
- editText를 사용해 위치정보 입력
- imageButton을 이용해 취소, 저장버튼추가
- timePicker을 이용해 수업 시작, 종료시간 입력
- datePicker을 이용해 날짜 입력
- Linearlayout을 사용하는 이유는 과외 색상, 과외 이름, 토글버튼을 가로 정렬을 하기 위해서이다.
- activity_login.kt 은 서버 연동전이므로 이메일과 비밀번호 입력 여부만 확인하고 로그인 후 calenderactivity로 연결






### 5) 일지뷰
![5번](https://user-images.githubusercontent.com/41908152/86928255-4b796900-c16f-11ea-893f-5fd41abd413b.JPG)


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
![7번](https://user-images.githubusercontent.com/41908152/86928287-5633fe00-c16f-11ea-927e-145c207e67a7.JPG)

- imageView를 사용해 상단바, 프로필 추가
- imageButton을 이용해 수업추가, 버전정보, 개발자정보, 비밀번호 변경, 로그아웃, 서비스탈퇴 버튼 추가
- switch를 이용해 수업료 알림과 수업시작 전 알림 선택 추가



-추가-
### **1. 구글 캘린더 api 적용**

#### **1. 라이브러리 추가**

- build.gradle에 추가
```kotlin
// 구글 캘린더 api
    implementation 'androidx.media:media:1.0.1'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.android.gms:play-services-auth:17.0.0'
    implementation 'pub.devrel:easypermissions:0.3.0'
    implementation('com.google.api-client:google-api-client-android:1.22.0') {
        exclude group: 'org.apache.httpcomponents'
    }
    implementation('com.google.apis:google-api-services-calendar:v3-rev235-1.22.0') {
        exclude group: 'org.apache.httpcomponents'
    }
```

- AndroidManifest.xml에 인터넷 권한 추가

### 2. GuideLine 사용

- 캘린더뷰 - 일정추가 activity_calendar_add.xml
```kotlin
<androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="20dp"/>
```

### 3. Chain 사용

- 캘린더뷰 - 일정추가 activity_calendar_add.xml
```kotlin
<TextView
            android:id="@+id/schedule_add_date_txt"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:hint="5월 23일"
            android:lineSpacingExtra="6sp"
            android:paddingLeft="16dp"
            android:paddingTop="14dp"
            android:paddingBottom="10dp"
            android:textColor="#707070"
            android:textSize="15sp"
            app:layout_constraintEnd_toStartOf="@+id/textView5"
            app:layout_constraintHorizontal_bias="0.5"
            app:layout_constraintHorizontal_chainStyle="spread_inside"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent">
```

- activity_schedule_add.xml
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white"
    tools:context="com.tutor.tutordot.ScheduleAddActivity">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/constarintlayout"
        android:layout_width="match_parent"
        android:layout_height="58dp"
        android:background="@color/colorPrimary"
        android:gravity="center"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="14dp"
            android:layout_marginBottom="14dp"
            android:text="일정 추가"
            android:fontFamily="@font/apple_sdgothic_neo_sb"
            android:textColor="#FFFFFF"
            android:textSize="18sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />




    </androidx.constraintlayout.widget.ConstraintLayout>

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="20dp" />

    <LinearLayout
        android:id="@+id/schedule_add_selection_linear"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="17dp"
        android:layout_marginRight="17dp"
        android:orientation="horizontal"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/guideline2"
        app:layout_constraintTop_toBottomOf="@+id/constarintlayout">

        <ImageView
            android:id="@+id/schedule_color"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="19dp"
            android:src="@drawable/notice_color_img_yellow">
        </ImageView>

        <TextView
            android:id="@+id/schedule_add_select_txt"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="19dp"
            android:layout_marginLeft="19dp"
            android:layout_marginTop="14dp"
            android:layout_marginBottom="13dp"
            android:lineSpacingExtra="7sp"
            android:text="김회진 학생 수학 수업"
            android:fontFamily="@font/apple_sdgothic_neo_sb"
            android:textColor="#161616"
            android:textSize="18sp">
        </TextView>


        <LinearLayout
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:gravity="right"
            android:orientation="horizontal">

            <ImageView
                android:id="@+id/schedule_add"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="25dp"
                android:src="@drawable/schedule_modification_subjectsection"></ImageView>
        </LinearLayout>
    </LinearLayout>

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginTop="25dp"
        android:layout_marginBottom="25dp"
        app:layout_constraintBottom_toTopOf="@+id/linearLayout"
        app:layout_constraintTop_toBottomOf="@+id/schedule_add_selection_linear">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            app:layout_constraintStart_toEndOf="@+id/guideline"
            app:layout_constraintTop_toBottomOf="@+id/schedule_add_selection_linear">

            <androidx.constraintlayout.widget.Guideline
                android:id="@+id/guideline"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                app:layout_constraintGuide_begin="20dp" />

            <TextView
                android:id="@+id/schedule_add_date"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:lineSpacingExtra="6sp"
                android:text="날짜"
                android:fontFamily="@font/apple_sdgothic_neo_m"
                android:textColor="#1c1c1c"
                android:textSize="15sp"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toTopOf="parent" />

            <androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/schedule_add_date_constraint"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_marginTop="13dp"
                android:layout_marginEnd="20dp"
                android:layout_marginRight="20dp"
                android:background="@drawable/stringbox_custom"
                android:orientation="horizontal"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/schedule_add_date">

                <TextView
                    android:id="@+id/schedule_add_date_txt"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:hint="5월 23일"
                    android:fontFamily="@font/apple_sdgothic_neo_m"
                    android:lineSpacingExtra="6sp"
                    android:paddingLeft="16dp"
                    android:paddingTop="12dp"
                    android:paddingBottom="10dp"
                    android:textColor="#707070"
                    android:textSize="15sp"
                    app:layout_constraintEnd_toStartOf="@+id/fix1"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintHorizontal_chainStyle="spread_inside"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent">

                </TextView>

                <TextView
                    android:id="@+id/fix1"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="16dp"
                    android:lineSpacingExtra="6sp"
                    android:paddingTop="12dp"
                    android:paddingBottom="10dp"
                    android:text="수정"
                    android:fontFamily="@font/apple_sdgothic_neo_m"
                    android:textColor="#6875dd"
                    android:textSize="15sp"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintStart_toEndOf="@+id/schedule_add_date_txt"
                    app:layout_constraintTop_toTopOf="parent">

                </TextView>

            </androidx.constraintlayout.widget.ConstraintLayout>

            <DatePicker
                android:id="@+id/date_picker"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:calendarViewShown="false"
                android:timePickerMode="spinner"
                android:visibility="gone"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/schedule_add_date_constraint" />

            <TextView
                android:id="@+id/schedule_add_start"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="25dp"
                android:lineSpacingExtra="6sp"
                android:text="수업시작"
                android:fontFamily="@font/apple_sdgothic_neo_m"
                android:textColor="#1c1c1c"
                android:textSize="15sp"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/date_picker" />

            <androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/schedule_add_start_constraint"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_marginTop="13dp"
                android:layout_marginEnd="20dp"
                android:layout_marginRight="20dp"
                android:background="@drawable/stringbox_custom"
                android:orientation="horizontal"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/schedule_add_start">

                <TextView
                    android:id="@+id/schedule_add_start_txt"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:hint="3:00 pm"
                    android:fontFamily="@font/apple_sdgothic_neo_m"
                    android:lineSpacingExtra="6sp"
                    android:paddingLeft="16dp"
                    android:paddingTop="12dp"
                    android:paddingBottom="10dp"
                    android:textColor="#707070"
                    android:textSize="15sp"
                    app:layout_constraintBottom_toBottomOf="parent"
                    app:layout_constraintEnd_toStartOf="@+id/fix2"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintHorizontal_chainStyle="spread_inside"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent">
                </TextView>

                <TextView
                    android:id="@+id/fix2"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="16dp"
                    android:lineSpacingExtra="6sp"
                    android:paddingTop="12dp"
                    android:paddingBottom="10dp"
                    android:text="수정"
                    android:fontFamily="@font/apple_sdgothic_neo_m"
                    android:textColor="#6875dd"
                    android:textSize="15sp"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintStart_toEndOf="@+id/schedule_add_start_txt"
                    app:layout_constraintTop_toTopOf="parent">
                </TextView>

            </androidx.constraintlayout.widget.ConstraintLayout>

            <TimePicker
                android:id="@+id/time_picker"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:timePickerMode="spinner"
                android:visibility="gone"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/schedule_add_start_constraint" />

            <TextView
                android:id="@+id/schedule_add_end"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="25dp"
                android:lineSpacingExtra="6sp"
                android:text="수업종료"
                android:fontFamily="@font/apple_sdgothic_neo_m"
                android:textColor="#1c1c1c"
                android:textSize="15sp"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/time_picker" />

            <androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/schedule_add_end_constraint"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_marginTop="13dp"
                android:layout_marginEnd="20dp"
                android:layout_marginRight="20dp"
                android:background="@drawable/stringbox_custom"
                android:orientation="horizontal"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/schedule_add_end">

                <TextView
                    android:id="@+id/schedule_add_end_txt"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:hint="8:00 pm"
                    android:fontFamily="@font/apple_sdgothic_neo_m"
                    android:lineSpacingExtra="6sp"
                    android:paddingLeft="16dp"
                    android:paddingTop="12dp"
                    android:paddingBottom="10dp"
                    android:textColor="#707070"
                    android:textSize="15sp"
                    app:layout_constraintEnd_toStartOf="@+id/fix3"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintHorizontal_chainStyle="spread_inside"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent">
                </TextView>

                <TextView
                    android:id="@+id/fix3"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="16dp"
                    android:lineSpacingExtra="6sp"
                    android:paddingTop="12dp"
                    android:paddingBottom="10dp"
                    android:text="수정"
                    android:fontFamily="@font/apple_sdgothic_neo_m"
                    android:textColor="#6875dd"
                    android:textSize="15sp"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintStart_toEndOf="@+id/schedule_add_end_txt"
                    app:layout_constraintTop_toTopOf="parent">
                </TextView>

            </androidx.constraintlayout.widget.ConstraintLayout>

            <TimePicker
                android:id="@+id/time_picker2"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:timePickerMode="spinner"
                android:visibility="gone"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/schedule_add_end_constraint" />

            <TextView
                android:id="@+id/schedule_add_location"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="25dp"
                android:lineSpacingExtra="6sp"
                android:text="위치"
                android:fontFamily="@font/apple_sdgothic_neo_m"
                android:textColor="#1c1c1c"
                android:textSize="15sp"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/time_picker2" />

            <androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/schedule_add_location_constraint"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="20dp"
                android:layout_marginTop="13dp"
                android:layout_marginRight="20dp"
                android:background="@drawable/stringbox_custom"
                android:orientation="horizontal"
                app:layout_constraintStart_toEndOf="@+id/guideline"
                app:layout_constraintTop_toBottomOf="@id/schedule_add_location">

                <EditText
                    android:id="@+id/schedule_add_location_txt"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@drawable/stringbox_custom"
                    android:lineSpacingExtra="6sp"
                    android:paddingLeft="16dp"
                    android:paddingTop="14dp"
                    android:paddingRight="16dp"
                    android:paddingBottom="10dp"
                    android:textColor="#707070"
                    android:textSize="15sp"
                    android:fontFamily="@font/apple_sdgothic_neo_m"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent">
                </EditText>


            </androidx.constraintlayout.widget.ConstraintLayout>

        </androidx.constraintlayout.widget.ConstraintLayout>
    </ScrollView>


    <LinearLayout
        android:id="@+id/linearLayout"
        android:layout_width="match_parent"
        android:layout_height="57dp"
        android:orientation="horizontal"
        app:layout_constraintBottom_toBottomOf="parent">

        <ImageButton
            android:id="@+id/schedule_add_btn_cancle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@drawable/schedule_add_btn_cancle" />

        <ImageButton
            android:id="@+id/schedule_add_btn_save"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@drawable/schedule_add_btn_save" />
    </LinearLayout>


</androidx.constraintlayout.widget.ConstraintLayout>
```
    

