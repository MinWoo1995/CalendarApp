Client → Controller (DTO로 요청 전달)

Controller → Service (DTO 전달)

Service → Repository (Entity로 조회/저장 요청)

Repository → DB

DB → Repository → Service (Entity 반환)

Service → Controller (Entity를 다시 DTO로 변환하여 반환)

Controller → Client (최종 응답)



API 명세서

1.일정 관련

기능/Method/URL/설명

일정 생성,POST,/api/schedules,제목 30자 내용 200자 제한 및 필수값 검증

전체 조회,GET,/api/schedules||GET,/api/schedules?username=전민우,수정일 기준 내림차순 정렬,작성자명 기준 일정 조회

단건 조회,GET,/api/schedules/{scheduleId}, 입력된 스케줄 일정 조회

일정 수정,PUT,/api/schedules/{scheduleId}, 수정할 내용 + 비밀번호를 Body로 받아서 업데이트 실행

일정 삭제,DELETE,/api/schedules/{scheduleId},비밀번호 Body로 받아서 삭제 실행

2.댓글관련

기능/Method/URL/설명

댓글 생성,POST,/api/comments,일정 존재 여부 확인후 일정당 최대 10개 댓글만 작성가능


ERD

1. Schedule Table (일정)

   id (PK): BigInt, Auto Increment (고유 식별자)

title: Varchar(30), Not Null (최대 30자 제한)

content: Varchar(200), Not Null (최대 200자 제한)

author: Varchar(255), Not Null

password: Varchar(255), Not Null (수정/삭제 검증용)

created_at: Datetime (JPA Auditing)

modified_at: Datetime (JPA Auditing)

2. Comment Table (댓글 - Lv 5 도전 과제)

   id (PK): BigInt, Auto Increment

schedule_id (FK): BigInt, Not Null (어떤 일정의 댓글인지 연결)

content: Varchar(100), Not Null (최대 100자 제한)

password: Varchar(255), Not Null

created_at: Datetime

modified_at: Datetime

1.3 Layer Architecture(Controller, Service, Repository)를 적절히 적용했는지 확인해 보고, 왜 이러한 구조가 필요한지 작성해 주세요.

1.역할분담-> 클래스를 분리하여 유지보수성을 높인다

2.테스트의 용이성 -> 에러가 발생시 해당 계층만 분리하여 테스트를 할수있다.

3.중복 코드방지 -> 일정 존재 여부와 같은 로직을 여기저기 사용하다 보니, 서비스 계층에 만들어두면 호출하여 재사용성이 높아진다



2.@RequestParam, @PathVariable, @RequestBody가 각각 어떤 어노테이션인지, 어떤 특징을 갖고 있는지 작성해 주세요.

@RequestParam -> URL 끝에 ?뒤에 key=value 형태로 데이터를 붙여 보내는 방식, 검색/필터링/정렬에 사용

특징 : 데이터가 URL에 다보인다, 필수가 아닌 값(required = false)이나 기본값(defaultValue) 설정이 자유롭다

@PathVariable -> URL의 일부를 변수값으로 사용하는 방식 / Id 와 같이

특징 : URL 경로에 데이터가 직접 노출, 주로 ID 값을 넘길 때 가장 많이 사용

@RequestBody -> HTTP 요청의 Body에 데이터를 담아 보내는 방식,  생성(POST)이나 수정(PUT) 사용

특징 : URL에 데이터가 보이지 않아 보안상 유리, 객체(DTO)로 바로 변환

