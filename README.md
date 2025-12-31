Client → Controller (DTO로 요청 전달)

Controller → Service (DTO 전달)

Service → Repository (Entity로 조회/저장 요청)

Repository → DB

DB → Repository → Service (Entity 반환)

Service → Controller (Entity를 다시 DTO로 변환하여 반환)

Controller → Client (최종 응답)



API 명세서

1.일정 관련

기능/Method/URL/Request Body/Response Body

일정 생성,POST,/api/schedules,"{""title"": ""string"", ""content"": ""string"", ""author"": ""string"", ""password"": ""string""}","{""id"": long, ""title"": ""string"", ""content"": ""string"", ""author"": ""string"", ""createdAt"": ""datetime"", ""modifiedAt"": ""datetime""}"

전체 조회,GET,/api/schedules?author=name,(Query Parameter: author - 선택사항),"[{""id"": long, ""title"": ""..."", ""author"": ""..."", ""modifiedAt"": ""...""}]"

단건 조회,GET,/api/schedules/{id},(None),"{""id"": long, ""title"": ""..."", ""content"": ""..."", ""author"": ""..."", ""comments"": [...], ""createdAt"": ""..."", ""modifiedAt"": ""...""}"

일정 수정,PUT,/api/schedules/{id},"{""title"": ""string"", ""author"": ""string"", ""password"": ""string""}","{""id"": long, ""title"": ""..."", ""author"": ""..."", ""modifiedAt"": ""...""}"

일정 삭제,DELETE,/api/schedules/{id},"{""password"": ""string""}","{""message"": ""삭제 완료""}"

2.댓글관련

기능/Method/URL/Request Body/Response Body

댓글 생성,POST,/api/schedules/{scheduleId}/comments,"{""content"": ""string"", ""author"": ""string"", ""password"": ""string""}","{""id"": long, ""content"": ""string"", ""author"": ""string"", ""createdAt"": ""..."", ""modifiedAt"": ""...""}"



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

author: Varchar(255), Not Null

password: Varchar(255), Not Null

created_at: Datetime

modified_at: Datetime