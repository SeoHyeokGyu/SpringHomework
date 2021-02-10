#  (Spring MVC, WebForm, Security 활용하여 웹 프로그램 작성
> 1) 본인이 지금까지 수강한 교과목을 MySQL DB 에 저장한다
> 2) 웹페이지 접속시 인증 유무를 체크한다 . 인증된 경우 , 아래 3) 번 작업을 수행하고 미인증시 인증을 위한 로그인 폼 (username,password) 을 출력한다
> 3) ① 학기별 이수 학점 조회 ② 수강 신청 하기 ③ 수강 신청 조회 메뉴가 나온다
>> ① 학년 학기별 이수 총 학점을 보여준다. 링크선택시 교과목 리스트가 출력된다.  
>> ② 수강 신청 하기 메뉴를 만든 후 2020 년 1 학기에 신청할 예상 교과목 을 웹 폼을 통해 입력받아서 DB 에 저장한다.  
>> ③ 수강 신청 조회 메뉴를 통해 2020 년 1 학기 수강 신청 내역을 조회 한다.  

> 웹 애플리케이션은 WAR 로 패키징한 후 AWS EC2 인스턴스에 배포한다 사용자는 브라우저에서 EC2 인스턴스에 접근한다. 

-  권한 설정하여 권한이 없으면 접속을 막아놓음. 로그인, 로그아웃만 허용해 들어가면 바로 로그인 가능하다.
> 실행화면 : <img width="225" alt="image" src="https://user-images.githubusercontent.com/20594299/107466775-fccdfa80-6ba7-11eb-9689-e24e540d0d3a.png">
> <img width="216" alt="image" src="https://user-images.githubusercontent.com/20594299/107466783-00fa1800-6ba8-11eb-93a8-a2f2d5a743e0.png">   
> 코드 : <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107466790-035c7200-6ba8-11eb-9803-04799117c92a.png">

1. show current lecture 

<img width="236" alt="image" src="https://user-images.githubusercontent.com/20594299/107467619-c2fdf380-6ba9-11eb-931c-7f0960081fd4.png">
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467625-c5f8e400-6ba9-11eb-9f3d-acc792b410d3.png">
-  Dao 파일에서 년도와 학기 기준으로 학점의 총합을 구하도록 SQL 문을 작성해준다.
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467629-c7c2a780-6ba9-11eb-892d-b10a74bf82f1.png">
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467630-ca250180-6ba9-11eb-82b4-71182479d43f.png">
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467633-cb562e80-6ba9-11eb-8d80-bd5e8b327769.png">
-  JSP 에서 받아 출력해준다. 상세보기 누르면 year, semester 가 쿼리스트링으로 넘어간다. 
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467638-cdb88880-6ba9-11eb-8680-bd1d53f30b04.png">
-  컨트롤러에서 year, semester 를 받아서 서비스에 넘겨준다.
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467641-cf824c00-6ba9-11eb-9da3-f15e213f5227.png">
-  Dao 를 호출하여 처리해서 결과값 넘겨준다.
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467644-d1e4a600-6ba9-11eb-89e2-a727959016ee.png">

2. 상세보기 눌렀을때 
