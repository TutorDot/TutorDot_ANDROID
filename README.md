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


## A-2 코틀린으로 안드로이드 앱 개발

- 아래 항목 모두 포함되어야 하나로 인정
1. kotlin collection 의 확장함수 사용(filter, map...)   

1) customEnqueue   
-> extention 폴더에서 customEnqueue.kt   

2) moveActi   
-> extention 폴더에서 moveAci   

3) showToast   
-> 세미나 때 사용   
### 3. 패키지 분류 이미지

![image](https://user-images.githubusercontent.com/41908152/87796285-1adab300-c884-11ea-878d-5d2e6498f713.png)


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

### 5) 스플래시 애니메이션

→ 스플래시에 사용   

### 6) Retrofit 라이브러리

compile 'com.prolificinteractive:material-calendarview:1.4.3'

→ 서버 통신에 사용

### 7) Gson 라이브러리

→ 객체 시리얼라이즈를 위해 사용

### 8) crop 라이브러리


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

- fragment와 viewPager을 이용해 화면전환 기능 추가
- tablayout을 통해 화면 전환상태 표시
- imageButton과 intent를 통해 회원가입뷰, 로그인 뷰로 전환


### 2)로그인& 회원가입

![image](https://user-images.githubusercontent.com/41908152/87784535-751e4880-c871-11ea-8bc9-7fc2e61cf312.png)
- imageView를 사용해 로고 추가
- editText를 사용해 이름, 이메일, 비밀번호, 비밀번호 확인 입력창 추가
- imageButton를 이용해 튜터/튜티 선택, 동의, 회원가입 버튼 추가
- activity_signup.kt 은 클라이언트에서 모든 칸 입력 여부만 확인→비밀번호, 비밀번호 확인칸 같은지 확인→ 튜터/튜티 선택여부 확인→동의여부 확인완료 한 후서버에 연동해 회원가입 → loginactivity로 연결
- tutor와 tutee두개로 나누어진 role을 입력받아 이후 기능의 차별을 둠

![image](https://user-images.githubusercontent.com/41908152/87784582-8b2c0900-c871-11ea-85e5-25395fa5d041.png)
- imageView를 사용해 로고와 핸드폰 디자인 추가
- editText를 사용해 이메일과 비밀번호 입력창 추가
- imageButton를 이용해 로그인버튼 추가
- activity_login.kt 은 서버 연동전에 이메일과 비밀번호 입력 여부, 자동로그인 확인하고 서버에 데이터를 전송하여 로그인 후 calendaractivity로 연결
- sharedpreference를 이용해 자동로그인 기능 구현


### 3)캘린더 뷰

![image](https://user-images.githubusercontent.com/41908152/87788012-8ec28e80-c877-11ea-9e14-e760e92454de.png)

- Material Calendar 라이브러리를 사용하여 달력 틀 잡음   
<특정 날 일정 보여주기>
- recyclerView를 이용해 날짜에 따른 데이터 같은 형식으로 출력
- 날짜가 클릭되면 서버에 연결해 데이터를 받아오며 받아오는 도중에 해당 날짜에 해당하는 날의 데이터를 한곳에 모아 모든 데이터를 받아온 후에 리사이클러뷰에 띄움
- 우측하단 이미지 버튼을 누르면 일정추가 화면으로 이동
- 서버연결을 완료하였지만 로그인한 정보를 기준으로 동적 jwt지정 전이므로 임의로 정적 jwt를 이용해 header을 지정하고 내용을 보여줌   


→ CalendarFragment.kt

1. 캘린더 날짜 클릭할 때마다 서버와 GET방식으로 통신하여 모든 일정의 정보를 받아온다.
2. 달력 상에서 선택한 날짜와 서버에서 받은 날짜를 비교하여 같으면 리사이클러뷰에 띄워준다. (datas: 리사이클러뷰의 데이터)

```kotlin
// 서버 연결
            calendarLogAdapter= CalendarLogAdapter(view.context, datas)

            val calendarlogRequestToServer = CalendarLogRequestToServer
            // 서버 요청
            calendarlogRequestToServer.service.calendarlogRequest(
            ).enqueue(object : Callback<CalendarLogResponseData> {
                override fun onFailure(call: Call<CalendarLogResponseData>, t: Throwable) {
                    Log.d("통신 실패", "${t}")
                }

                override fun onResponse(
                    call: Call<CalendarLogResponseData>,
                    response: Response<CalendarLogResponseData>
                ) {
                    // 통신 성공
                        if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                        if (response.body()!!.success) {  // 참고 코드에서 없는 부분
                            Log.d(response.body()!!.data.toString(), response.body()!!.data.toString())

                            //데이터가 없을 경우 haveData를 false로 바꿔줌 (캘린더는 수정 필요)
                            if(response.body()!!.data.size == 0)
                                haveCalendarData = false
                            else
                                haveCalendarData = true
                            
                            val Year = date.year
                            var Month = (date.month + 1).toString()
                            var Day = (date.day).toString()
                            calendarlog_all_date.text = "$Day"
                            calendarlog_all_month.text = "$Month" + "월"

                            // 날짜 포맷 통일
                            if (Month.toInt() < 10) {
                                Month = "0$Month"
                            }
                            if (Day.toInt() < 10) {
                                Day = "0$Day"
                            }

                            val shot_Day = "$Year-$Month-$Day"
                            Log.i("shot_Day test", shot_Day + "")
                            materialCalendarView.clearSelection()

//                            Toast.makeText(
//                                requireContext(),
//                                shot_Day,
//                                Toast.LENGTH_SHORT
//                            ).show()

                            var i: Int = 0
                            for (i in 0 until response.body()!!.data.size) {
                                Log.d("인덱스", "${i}")

                                if (shot_Day == response.body()!!.data[i].classDate) {
                                    Log.d("test", "동일")
                                    Log.d("test", "${response.body()!!.data[i].classDate}")
                                    datas.apply {
                                        add(
                                            CalendarData(
                                                classId = "${response.body()!!.data[i].classId}".toInt(),
                                                startTime = "${response.body()!!.data[i].startTime}",
                                                endTime = "${response.body()!!.data[i].endTime}",
                                                color = "${response.body()!!.data[i].color}",
                                                times = "${response.body()!!.data[i].times}".toInt(),
                                                lectureName = "${response.body()!!.data[i].lectureName}",
                                                hour = "${response.body()!!.data[i].hour}".toInt(),
                                                location = "${response.body()!!.data[i].location}",
                                                classDate = "${response.body()!!.data[i].classDate}"
                                            )
                                        )
                                    }
                                    Log.i("test", "${response.body()!!.data[i].lectureName}")
                                    Log.i("test", "${response.body()!!.data[i].color}")
                                    calendarLogAdapter.notifyDataSetChanged()
                                } else {
                                    continue
                                }
                            }
                        }
                        calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), datas)
                        calendarLogAdapter.notifyDataSetChanged()
                        rv_calendarlog.adapter = calendarLogAdapter

                    } else {
                        Log.d("실패", "${response.message()}")
                    }
                }
            })
        }
    }
```

<점 찍기>   

→ EventDecorator.kt

EventDecorator를 이용하여 이벤트가 있는 날(일정이 있는 날)에 어떤 이벤트를 줄지 설정한다. 원하는 색상을 파싱하여 달력 상에 그 색상의 점을 찍을 수 있도록 하였다.

→ CustomMultipleDotSpan.kt

CustomMultipleDotSpan에서 한 날짜에 하나의 점이 아닌 여러 점을 찍을 수 있도록 하였다. 원래는 그 날에 있는 일정의 개수대로 찍으려 하였으나 아직 그렇게 하지는 못했다.

→ OnDayDecorator.kt, CurrentDayDecorator.kt

오늘 날짜에 이벤트를 준다. 오늘 날짜에 들어갈 원의 크기 등을 조절할 수 있다. 여기서는 네모로 적용하였다.

- 해당 날짜의 특정 정보를 누르면 상세 정보를 볼 수 있다. (일정 정보 뷰) GET방식과 서버와 통신(구현 중)
- FloatingButton을 이용해 일정을 추가할 수 있다. (일정 추가 뷰)
- 팝업메뉴를 이용해 상단에서 전체, 특정 수업을 선택할 수 있음

- 저장 버튼을 누르면 PUT방식으로 서버와 통신한다. (코드 구현 중)

#### 3-1) 일정정보

![image](https://user-images.githubusercontent.com/41908152/87784645-a72faa80-c871-11ea-999d-6722d906d2ff.png)
- textView를 이용해 정보 띄우기
- View를 사용해 상단바 추가
- imageButton을 이용해 일정 삭제 버튼추가
- 상단 좌측 이미지 버튼을 누르면 activity를 finish()하여 이전 화면으로 돌아감   
- 일정 정보 뷰에서 수업 날짜, 수업 시작, 수업 종료, 위치를 확인할 수 있다. 여기서 편집을 누르면 일정 수정 뷰로 이동.

#### 3-2) 일정수정

![image](https://user-images.githubusercontent.com/41908152/87784663-b0207c00-c871-11ea-96cc-46f424e06325.png)
- textView를 이용해 정보 띄우기
- imageView를 사용해 상단바 추가
- imageButton를 이용해 수정 버튼으로 내용 수정기능, 취소, 저장버튼추가   
- 날짜, 시간은 각각 피커뷰가 적용되었다. date picker, time picker. 피커뷰 상에서 돌려가며 날짜와 시간을 선택하면 텍스트가 바로바로 변하는 것을 확인 할 수 있다.
- 피커뷰는 visible속성을 이용해 gone으로 둔 뒤 각 입력창을 누르면 visible로 바뀌어 보이고 수정버튼이 완료버튼으로 바뀌어 보여진다. 완료버튼을 누르면 피커뷰가 다시 닫힌다.
- 일정 수정 뷰에 가면 수업날짜, 수업 시작, 수업 종료, 위치를 수정 가능   

#### 3-3) 일정추가

![image](https://user-images.githubusercontent.com/41908152/87784695-be6e9800-c871-11ea-8599-9b2b70c98178.png)
- imageView를 사용해 상단바 추가
- editText를 사용해 위치정보 입력
- imageButton을 이용해 취소, 저장버튼추가
- timePicker을 이용해 수업 시작, 종료시간 입력
- datePicker을 이용해 날짜 입력
- Linearlayout을 사용하는 이유는 과외 색상, 과외 이름, 토글버튼을 가로 정렬을 하기 위해서이다.   
- 저장 버튼을 누르면 서버와 POST방식으로 통신하며 입력한 데이터를 서버로 보낸다. (코드 구현은 했으나 서버 통신 오류로 수정 필요)


### 4) 일지 뷰

![image](https://user-images.githubusercontent.com/41908152/87785394-0c37d000-c873-11ea-8470-d5c215642065.png)

- ConstraintLayout으로 상단뷰, 진행률 레이아웃, 일지 아이템뷰, 숙제 이행률 구성
- 프로그레스바와 일지창 구성
- 전체 뷰에 맞는 폰트 적용
- 중첩 RecyclerView를 이용해 일지 날짜와 일지 아이템 구성

*하위 리사이클러뷰 - 일지 아이템

*상위 리사이클러뷰 - 날짜와 일지 아이템 리사이클러뷰

- 월 수업일지 양쪽 화살표 클릭 이벤트 추가
- 일지 아이템 버튼 클릭 이벤트 추가
- 커스텀 ProgressBar 이용
- 서버 통신을 통해 해당 일지 내용을 보여주고 토글에서 수업을 선택하면 해당 수업에 대한 정보를 불러오고 계산해 progress bar을 띄움

#### 4-1) 빈화면 일지

![image](https://user-images.githubusercontent.com/41908152/87785503-34273380-c873-11ea-97c7-cfde6f95a51f.png)

- 데이터가 없을 경우 이 화면을 송출함

#### 4-2) 일지수정

![image](https://user-images.githubusercontent.com/41908152/87785447-1fe33680-c873-11ea-8d4f-5406b86c82b5.png)

- 일지를 수정하면 서버에 그 내용을 PUT하고 Toast를 이용해 수정완료 메세지를 띄움



### 5) 알림 뷰/ 데이터 없는 화면

![image](https://user-images.githubusercontent.com/41908152/87785748-a8fa6d80-c873-11ea-847c-e3bd157befb1.png)
![image](https://user-images.githubusercontent.com/41908152/87785793-c16a8800-c873-11ea-9b36-a5599bee5e5c.png)

-popupmenu를 통해 토글 구현
-중접 recyclerview를 이용해 내용 띄움

### 6) 마이페이지 뷰/ 빈화면

![image](https://user-images.githubusercontent.com/41908152/87785847-e19a4700-c873-11ea-9feb-5cff0e59b790.png)6-2
![image](https://user-images.githubusercontent.com/41908152/87785893-f8409e00-c873-11ea-8be1-0809a89f3a44.png)

- imageView를 사용해 상단바, 프로필 추가
- imageButton을 이용해 수업추가, 버전정보, 개발자정보, 비밀번호 변경, 로그아웃, 서비스탈퇴 버튼 추가
- switch를 이용해 수업료 알림과 수업시작 전 알림 선택 추가
- recyclerview를 이용해 수업목록 형태를 구성하고 서버에서 받아와 수업목록과 선택색, 학생 프로필을 띄움
- 서버에서 userprofile을 받아와 내정보 상단의 프로필사진, role, 한줄 자기소개를 띄우고 role을 설정해 이후에 role에 따라 기능에 차이를 둘 수 있도록 함

#### 6-1) 한줄소개

![image](https://user-images.githubusercontent.com/41908152/87785967-15756c80-c874-11ea-96e9-f1f052450730.png)

-마이페이지 화면에서 프로필 사진을 클릭하면 회원정보를 서버에 연결해 받아온 후 한줄 소개페이지에 띄움
-Edittext창을 통해 소개를 수정할 수 있고 사진을 클릭하면 자신의 갤러리에서 사진을 선택해 업로드 할 수 있음
-취소버튼을 누르면 뒤로가기를 가능하고 저장 버튼을 누르면 바뀐 내용을 서버에 전송함

#### 6-2) 수업정보

![image](https://user-images.githubusercontent.com/41908152/87786004-2920d300-c874-11ea-819b-4b12d5e70e48.png)

- 마이페이지의 수업리스트 리사이클러뷰안의 항목을 누르면 자세한 수업 정보를 띄우기 위해 수업정보 activity로 이동한다.
- 복사 버튼을 누르면 계좌 정보가 자동으로 클립보드에 복사된다.
- 편집 버튼을 누르면 수업 정보를 수정할 수 있는 수업 수정 activity로 이동한다
- 과외초대 버튼을 누르면 수업초대 코드를 볼 수 있는 페이지로 이동한다.
- 연결해제 버튼을 누르면 연결을 해제할지 물어보는 팝업을 띄운다.

#### 6-3) 수업수정

![image](https://user-images.githubusercontent.com/41908152/87786033-3ccc3980-c874-11ea-8fe4-a875331b6a5f.png)

#### 6-4) 수업추가

![image](https://user-images.githubusercontent.com/41908152/87786122-5f5e5280-c874-11ea-90b6-40cbb20c11da.png)

#### 6-5) 수업초대/ 초대코드
![image](https://user-images.githubusercontent.com/41908152/87786175-71d88c00-c874-11ea-8556-fc249d3125d1.png)
![image](https://user-images.githubusercontent.com/41908152/87786196-7dc44e00-c874-11ea-9574-0f875edcec21.png)

- 서버에서 주는 각 수업에 대한 랜덤코드가 나타난다. 옆의 복사 버튼을 누르면 코드가 한번에 자동으로 복사된다.
- 튜티는 튜터에게 받은 초대코드를 입력하면 해당 수업에 들어갈 수 있다.

#### 6-6) 개발자 정보

![image](https://user-images.githubusercontent.com/41908152/87786277-a0eefd80-c874-11ea-8254-321d47a19a0c.png)

- 개발자들의 정보가 나타난다.

### 7) 팝업

#### 7-1) 로그아웃

![image](https://user-images.githubusercontent.com/41908152/87787479-ab11fb80-c876-11ea-9a6c-de5ecac1a440.png)

-로그아웃 버튼을 누르면 자동 로그인이 해제되고  로그인 화면으로 이동한다. 
- "아니오, 취소합니다"를 누르면 팝업창이 사라진다. 

#### 7-2) 연결해제

![image](https://user-images.githubusercontent.com/41908152/87787559-ce3cab00-c876-11ea-9efa-ecb4454402bd.png)
- 수업 연결을 해제하게 되면 수업을 추가하기 위한 수업 추가 뷰가 등장한다. 
- 취소하게 되면 팝업창이 사라진다.


#### 7-3) 탈퇴

![image](https://user-images.githubusercontent.com/41908152/87787661-fa582c00-c876-11ea-9c3a-826c807df3a7.png)

-탈퇴합니다를 누르면 회원가입 페이지로 이동하고, 취소하게 되면 팝업창이 사라진다.










-추가-
### 1. GuideLine 사용

- 캘린더뷰 - 일정추가 activity_calendar_add.xml
```kotlin
<androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="20dp"/>
```

### 2. Chain 사용

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

### 3. match_constraint   

    

