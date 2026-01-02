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