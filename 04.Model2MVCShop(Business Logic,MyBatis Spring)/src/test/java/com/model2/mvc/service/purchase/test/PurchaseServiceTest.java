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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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
//		purchase.setPruchaseOption("��⵵");
//		purchase.setPurchaseProd("test@test.com");
//		
//		purchaseService.addPurchase(purchase);;
//		
//		user = purchaseService.getUser("testUserId");
//
//		//==> console Ȯ��
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

		//==> console Ȯ��
		
		
		
	}
//	
	//@Test
	 public void testUpdateUser() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10010);
		
		
		

		purchase.setPruchaseOption("2");
		purchase.setReceiverName("777-7777-7777");
		purchase.setReceiverPhone("change");
		purchase.setDlvyAddr("�����");
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
	 	
		//==> console Ȯ��
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
		 	
			//==> console Ȯ��
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
	 }
	  
}