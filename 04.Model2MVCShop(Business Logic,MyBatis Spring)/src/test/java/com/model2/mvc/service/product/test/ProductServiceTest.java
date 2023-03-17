package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


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
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testInsertProduct() throws Exception {
		
		System.out.println("insertPro");
		
		Product product = new Product();
		product.setProdName("블루베리");
		product.setProdDetail("good");
		product.setManuDate("20200202");
		product.setPrice(30000);
		product.setFileName("testFile");
		
		//System.out.println("set 완료");
				
		productService.addProduct(product);
		
		//System.out.println("insert 완료");
		
		product = productService.getProduct(10024);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		/*
		Assert.assertEquals(10025, product.getProdNo());
		Assert.assertEquals("블루베리", product.getProdName());
		Assert.assertEquals("good", product.getProdDetail());
		Assert.assertEquals("20200202", product.getManuDate());
		Assert.assertEquals(30000, product.getPrice());
		Assert.assertEquals("testFile", product.getFileName());
		*/
	}
	
	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	  
	
	//@Test
	public void testGetProduct() throws Exception {

		System.out.println("getPro");
		Product product = new Product();
		/*
		product.setProdName("딸기");
		product.setProdDetail("good");
		product.setManuDate("20200202");
		product.setPrice(30000);
		product.setFileName("testFile");
		*/
		product = productService.getProduct(10001);

		//==> console 확인
		System.out.println(product);
		/*
		//==> API 확인
		Assert.assertEquals(10001, product.getProdNo());
		Assert.assertEquals("딸기", product.getProdName());
		Assert.assertEquals("맛있어요", product.getProdDetail());
		Assert.assertEquals("20120502", product.getManuDate());
		Assert.assertEquals(30000, product.getPrice());
		Assert.assertEquals(null, product.getFileName());

		Assert.assertNotNull(productService.getProduct(10002));
		Assert.assertNotNull(productService.getProduct(10003));
		*/
	}
			
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10010);
		Assert.assertNotNull(product);
		System.out.println(product);

		Assert.assertEquals("name", product.getProdName());
		Assert.assertEquals("detaile", product.getProdDetail());
		Assert.assertEquals("22113344", product.getManuDate());
		Assert.assertEquals("image", product.getFileName());

		product.setProdName("블루베리");
		product.setProdDetail("몸에좋아요");
		product.setManuDate("111111");
		product.setFileName("image");
		product.setPrice(113333);
		
		
		System.out.println(product);

		productService.updateProduct(product);
		
		product = productService.getProduct(10010);
		Assert.assertNotNull(product);
		
		//==> console 확인
		System.out.println(product);
			
		//==> API 확인
		Assert.assertEquals("블루베리", product.getProdName());
		Assert.assertEquals("몸에좋아요", product.getProdDetail());
		Assert.assertEquals("20200202", product.getManuDate());
		Assert.assertEquals("blue.jpg", product.getFileName());
	 }
	 
	
	 //@Test
	 public void testGetUserListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10001");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 
	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("맥북");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}