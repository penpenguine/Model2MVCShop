package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
//	public void testAddUser() throws Exception {
//		
//		Purchase purchase = new Purchase();
//		purchase.setBuyer("testUserId");
//		purchase.setDlvyAddr("testUserName");
//		purchase.setDlvyDate("testPasswd");
//		purchase.setDlyRequest("1111112222222");
//		
//		purchase.setPruchaseOption("경기도");
//		purchase.setPurchaseProd("test@test.com");
//		
//		purchaseService.addPurchase(purchase);;
//		
//		user = purchaseService.getUser("testUserId");
//
//		//==> console 확인
//		System.out.println(purchase);
//
//	}
	
	@Test
	public void testGetUser() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10010);
		System.out.println(purchase);
		purchase.setPruchaseOption("1");
		purchase.setDlvyDate("2023-02-03");
		purchaseService.addPurchase(purchase);

		//==> console 확인
		
		
		
	}
//	
	//@Test
	 public void testUpdateUser() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10010);
		
		
		

		purchase.setPruchaseOption("2");
		purchase.setReceiverName("777-7777-7777");
		purchase.setReceiverPhone("change");
		purchase.setDlvyAddr("서울시");
		purchase.setDlyRequest("qkRmnojsfsf");
		purchase.setDlvyDate("2023-02-03");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10001);
		System.out.println(purchase);
			
	 }

	 //@Test
		public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search,"user19");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 }
	 
	 //@Test
	 public void testGetSaleListByUserId() throws Exception{
		 Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword("");
		 	
		 	Map<String,Object> map = purchaseService.getSaleList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
	 }
	  
}