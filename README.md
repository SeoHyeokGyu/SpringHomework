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
```
	<authentication-manager>
		<authentication-provider>
			<jdbc-user-service data-source-ref="dataSource"
				users-by-username-query="select username, password, enabled from users where username=?"
				authorities-by-username-query="select username, authority from authorities where username=?" />
		</authentication-provider>
	</authentication-manager>

	<http auto-config="true" use-expressions="true">
		<intercept-url pattern="/" access="isAuthenticated()" />

		<intercept-url pattern="/login" access="permitAll" />
		<intercept-url pattern="/logout" access="permitAll" />
		<intercept-url pattern="/lectures" access="isAuthenticated()" />
		<intercept-url pattern="/specifier" access="isAuthenticated()" />


		<intercept-url pattern="/createlecture" access="isAuthenticated()" />
		<intercept-url pattern="/docreate" access="isAuthenticated()" />
		<intercept-url pattern="/resources/**" access="permitAll" />
		<intercept-url pattern="/**" access="denyAll" />

		<form-login login-page="/login"
			authentication-failure-url="/login?error" />
		<logout />
	</http>
  ```

1. show current lecture 

<img width="236" alt="image" src="https://user-images.githubusercontent.com/20594299/107467619-c2fdf380-6ba9-11eb-931c-7f0960081fd4.png">

```
// 여러개의 객체를 조회한다. 
	public List<Lecture> getOffers() {
		String sqlstatement = "select year, semester, sum(point) from lecture group by year,semester ";
		return jdbcTemplate.query(sqlstatement, new RowMapper<Lecture>() {
			// 레코드를 자바 객체로 매핑시켜준다.rowmapper : 인터페이스를 구현 / 익명클래스 작성
			@Override
			public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Lecture offer = new Lecture();

				offer.setYear(rs.getInt("year"));
				offer.setSemester(rs.getInt("semester"));
				offer.setPoint(rs.getInt("sum(point)"));

				return offer;
			}

		});
	}
  ```
  
-  Dao 파일에서 년도와 학기 기준으로 학점의 총합을 구하도록 SQL 문을 작성해준다.

```
<body>
	<table>
	<tr>
	<td colspan="4">
	2020년 1학기 수강신청 
	 </td>
	</tr>
		<tr>
			<td>코드</td>
			<td>이름</td>
			<td>구분</td>
			<td>학점</td>
		</tr>
		<c:forEach var="lecture" items="${specifier}">
			<tr>
				<td><c:out value="${lecture.code}">
					</c:out></td>
				<td><c:out value="${lecture.name}">
					</c:out></td>
				<td><c:out value="${lecture.division}">
					</c:out></td>
				<td><c:out value="${lecture.point}">
					</c:out></td>

			</tr>

		</c:forEach>
		<tr>
			<td colspan="4"><input type="button" class="button"
				onclick="location.href = '${pageContext.request.contextPath }/' "
				value="home"></td>
		</tr>
	</table>
</body>
```

-  JSP 에서 받아 출력해준다. 상세보기 누르면 year, semester 가 쿼리스트링으로 넘어간다. 

```
	@RequestMapping("/specifier")
	public String specifier(Model model, @RequestParam("year") int year,@RequestParam("semester") int semester) {
		List<Lecture> specifier = offerService.getOffer(year, semester);
		model.addAttribute("specifier",specifier);
		
		return "specifier";
	}
	
	@RequestMapping("/lectures")
	public String showOffers(Model model) {
		List<Lecture> lectures = offerService.getCurrent();
		model.addAttribute("lectures", lectures);

		return "lectures";
	}
```

-  컨트롤러에서 year, semester 를 받아서 서비스에 넘겨준다.

```
@Service
public class LectureService {

	@Autowired
	private LectureDao lectureDao;
	
	public List<Lecture> getOffer(int year, int semester) {
		List<Lecture> specifier = lectureDao.getOffer(year, semester);
		return specifier;
	}
	public List<Lecture> getCurrent(){
		return lectureDao.getOffers();
	}

	public void insert(Lecture lecture) {
		// TODO Auto-generated method stub
		lectureDao.insert(lecture);
	}
}
```

-  Dao 를 호출하여 처리해서 결과값 넘겨준다.

```
	// 쿼리 하나의 객체를 조회한다.
	public List<Lecture> getOffer(int year, int semester) {
	
//		int year = offer.getYear();
//		int semester = offer.getSemester();
		String sqlStatement = "select * from lecture where year=? and semester=? ;";
		return jdbcTemplate.query(sqlStatement,new Object[] {year, semester}, new RowMapper<Lecture>() {
			// 레코드를 자바 객체로 매핑시켜준다.rowmapper : 인터페이스를 구현 / 익명클래스 작성
			@Override
			public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Lecture offer = new Lecture();

				offer.setYear(rs.getInt("year"));
				offer.setSemester(rs.getInt("semester"));
				offer.setCode(rs.getString("code")); 
				offer.setName(rs.getString("name"));
				offer.setDivision(rs.getString("division"));
				offer.setPoint(rs.getInt("point"));

				return offer;
			}

		});
	}

```

2. 상세보기 눌렀을때 

<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467826-39025a80-6baa-11eb-89b0-f54ede942723.png">
<img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107467828-3b64b480-6baa-11eb-9f4a-4d0ad425d672.png">

```
<body>
	<table>
		<tr>
			<td>년도</td>
			<td>학기</td>
			<td>코드</td>
			<td>이름</td>
			<td>구분</td>
			<td>학점</td>
		</tr>
		<c:forEach var="lecture" items="${specifier}">
			<tr>
				<td><c:out value="${lecture.year}">
					</c:out></td>
				<td><c:out value="${lecture.semester}">
					</c:out></td>
				<td><c:out value="${lecture.code}">
					</c:out></td>
				<td><c:out value="${lecture.name}">
					</c:out></td>
				<td><c:out value="${lecture.division}">
					</c:out></td>
				<td><c:out value="${lecture.point}">
					</c:out></td>

			</tr>

		</c:forEach>
		<tr>
			<td colspan="6"><input type="button" class="button"
				onclick="location.href = '${pageContext.request.contextPath }/' "
				value="home"></td>
		</tr>
	</table>
</body>
```

3. add a new lecture 

<img width="235" alt="image" src="https://user-images.githubusercontent.com/20594299/107467866-4ae3fd80-6baa-11eb-82b9-32504bd0a6a4.png">

```
<body>
	<!-- contextroot 와 매칭 
 -->
	<sf:form method="post"
		action="${pageContext.request.contextPath }/docreate"
		modelAttribute="lecture">
		<table class="formtable">
			<tr>
				<td>수강년도 :</td>
				<td><sf:input class="control" type="text" path="year" /> <br />
					<sf:errors path="year" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>학기 :</td>
				<td><sf:input class="control" type="text" path="semester" /><br />
					<sf:errors path="semester" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>교과코드 :</td>
				<td><sf:input class="control" type="text" path="code" /><br />
					<sf:errors path="code" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>교과목명 :</td>
				<td><sf:input class="control" type="text" path="name" /><br />
					<sf:errors path="name" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>구분 :</td>
				<td><sf:input class="control" type="text" path="division" /><br />
					<sf:errors path="division" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>학점 :</td>
				<td><sf:input class="control" type="text" path="point" /><br />
					<sf:errors path="point" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="reset" class="button" value="Reset" />
					<input type="submit" class="button" value="수강 신청하기" />
					<input type="button" class="button" onclick="location.href = '${pageContext.request.contextPath }/' "value="home">
					</td>
			</tr>
		</table>
	</sf:form>

</body>
```

```
@RequestMapping("/createlecture")
	public String createOffer(Model model) {
//		임의적으로 객체 생성하여 view 에서 사용할수 있게함 .
		model.addAttribute("lecture", new Lecture());

		return "createlecture";
	}

	@RequestMapping("/docreate")
//	데이터 바인딩. 자동으로 offer 들어간다.	
	public String docreate(Model model, @Valid Lecture lecture, BindingResult result) {

		if(result.hasErrors()) {
			System.out.println("=== Web Form data doen not validated ===");
			List<ObjectError> errors = result.getAllErrors();
//			메세지를 지정해줘야한다. 	
			for(ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "createlecture";
		}
//		컨트롤러 - 서비스 - dao
		offerService.insert(lecture);

		return "lecturecreated";
	}
```

<img width="220" alt="image" src="https://user-images.githubusercontent.com/20594299/107467881-52a3a200-6baa-11eb-904f-4ea7383e75f8.png">

4. add a new lecture list

<img width="333" alt="image" src="https://user-images.githubusercontent.com/20594299/107467903-5cc5a080-6baa-11eb-8695-869042364181.png">
