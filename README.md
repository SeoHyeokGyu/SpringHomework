# SpringHomework
웹 애플리케이션은 WAR 로 패키징한 후 AWS EC2 인스턴스에 배포한다 사용자는 브라
우저에서 EC2 인스턴스에 접근한다.

서브릿과 jsp 를 활용하여 웹 서비스를 제공하려고 한다 . MVC 설계 패턴에 기초하여 웹 프로그램을 작성 

- 초기 페이지
> <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107319463-3c321380-6ae2-11eb-9b78-297922785e20.png">
- help 페이지 
> <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107319470-3dfbd700-6ae2-11eb-805a-41578fa756d5.png">
- Login 페이지
> <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107319478-40f6c780-6ae2-11eb-8ee4-2447799d075c.png">
```
public Customer findCustomer(String id, String password) {
		Customer customer = customerMap.get(id.toLowerCase());		
		if( id.equals(customer.getId())  && password.equals(customer.getPassword()) )
			return (customerMap.get(id.toLowerCase()));
		else
			return null; 
	} 
  ```
  > findCustomer 함수의 인자에 password를 추가하여 비밀번호까지 비교하여 리턴을 하게 합니다. 
  - 로그인 실패 페이지
> <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107319894-06d9f580-6ae3-11eb-8fae-ce00db67b748.png">
- 로그인 성공하면 Order 페이지로 이동
> <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107319903-093c4f80-6ae3-11eb-9c5f-b1ca62fccae6.png">
- 카드번호 입력이 일치하는지 판정 
> <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107319907-0b061300-6ae3-11eb-8cc6-0b6f22422920.png">
> <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107319912-0e010380-6ae3-11eb-8e72-b4b0259196d6.png">
```
<script type="text/javascript">
	function isSame(){
	var number = document.getElementById("creditcardnumber").value;
	var repeat = document.getElementById("repeat").value;
	var check = document.getElementById("check");
	var submit = document.getElementById("submit");
	if(number == "" ){	
		check.innerHTML="번호를 입력하세요. ";
	submit.disabled = true;
	}
	else if(number == repeat){
		check.innerHTML="번호가 일치합니다. ";
		submit.disabled = false;
		return true;
	}
	else{
		check.innerHTML="번호가 일치하지 않습니다.";
		submit.disabled = true;
	return false
		}
	}
</script>
```
카드 번호를 비교하기 위해 자바스크립트 사용. 카드번호에 id 속성을 작성하고 받아온다. 그리고 그 값을 비교하여, 빈 값 또는 번호가 일치하지 않을 경우 submit버튼을 비활성화 시킨다. 그리고 빈 태그에 innerhtml 로 상태를 알려준다. 
```
Credit Card Number : <input type="password" name="creditcardnumber" id="creditcardnumber" onchange="isSame()"> <br />	
	Repeat Credit Card Number : <input type="password" name="repeat" id="repeat"  onchange="isSame()" required>
  ```
상태가 변경되었을때 함수 호출하기위해 onchange() 사용, repeat 에는required 넣어서 무조건 입력 필요하게 만듬  
orderservice 에서 form 을 받을 order 를 해쉬맵으로 구현. addOrder 를 public 으로 구현 후 
```
public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderMap.put(order.getCreditcardnumber(), order);
	}
  ```
submit 하면 doform 서블릿으로 이동. form에서 작성한 데이터를 받고 addOrder 하여 해쉬맵에 추가해준다.
```
service.addOrder(new Order(itemnumber,desription,priceeach,firstname,lastname,middleinitial,shippingaddress,creditcard,creditcardnumber));
```
다음 입력한 값을 조회하기위한 
```
	public List<Order> getAllOrders(){
		// order 에 대한 arraylist 만들어준다. 여기있는 모든 벨류 값을 가지고와서 넣어준다.
		List<Order> customerList = new ArrayList<Order>(orderMap.values());
		return customerList;
	} 
```
  함수 작성해준다. 그 다음 doform 에서 attribute 값으로 저장하여 넘겨줌
```
		List<Order> orderList = service.getAllOrders();
		request.setAttribute("orders", orderList);
```
 > <img width="451" alt="image" src="https://user-images.githubusercontent.com/20594299/107320407-fc6c2b80-6ae3-11eb-94ad-50d74852dc82.png">
 폼 완성했을때의 상태
    
    
    
    
    
    
```
	<table border="1">
		<c:forEach var="order" items="${orders}">
		<tr>
		<th>Parameter Name</th>
		<th>Parameter Value</th>
		</tr>
			<tr>
				<td>Credit Card Number</td>
				<td>${order.creditcardnumber }</td>
			</tr><tr>
				<td>Credit Card</td>
				<td>${order.creditcard }</td>
			</tr><tr>
				<td>Price Each</td>
				<td>${order.priceeach }</td>	
			</tr><tr>
				<td>Middle Initial</td>
				<td>${order.middleinitial }</td>
			</tr><tr>
				<td>Item Number</td>
				<td>${order.itemnumber }</td>
			</tr><tr>
				<td>Shipping Address</td>
				<td>${order.shippingaddress }</td>
			</tr><tr>
				<td>First Name</td>
				<td>${order.firstname }</td>
			</tr><tr>
				<td>Desription</td>
				<td>${order.desription }</td>
			</tr><tr>
				<td>Last Name</td>
				<td>${order.lastname }</td>
			</tr>
		</c:forEach>
		</table>
```
    
