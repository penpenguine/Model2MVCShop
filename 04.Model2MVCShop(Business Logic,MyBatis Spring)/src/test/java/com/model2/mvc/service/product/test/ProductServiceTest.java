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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testInsertProduct() throws Exception {
		
		System.out.println("insertPro");
		
		Product product = new Product();
		product.setProdName("��纣��");
		product.setProdDetail("good");
		product.setManuDate("20200202");
		product.setPrice(30000);
		product.setFileName("testFile");
		
		//System.out.println("set �Ϸ�");
				
		productService.addProduct(product);
		
		//System.out.println("insert �Ϸ�");
		
		product = productService.getProduct(10024);

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		/*
		Assert.assertEquals(10025, product.getProdNo());
		Assert.assertEquals("��纣��", product.getProdName());
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
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	  
	
	//@Test
	public void testGetProduct() throws Exception {

		System.out.println("getPro");
		Product product = new Product();
		/*
		product.setProdName("����");
		product.setProdDetail("good");
		product.setManuDate("20200202");
		product.setPrice(30000);
		product.setFileName("testFile");
		*/
		product = productService.getProduct(10001);

		//==> console Ȯ��
		System.out.println(product);
		/*
		//==> API Ȯ��
		Assert.assertEquals(10001, product.getProdNo());
		Assert.assertEquals("����", product.getProdName());
		Assert.assertEquals("���־��", product.getProdDetail());
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

		product.setProdName("��纣��");
		product.setProdDetail("�������ƿ�");
		product.setManuDate("111111");
		product.setFileName("image");
		product.setPrice(113333);
		
		
		System.out.println(product);

		productService.updateProduct(product);
		
		product = productService.getProduct(10010);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		System.out.println(product);
			
		//==> API Ȯ��
		Assert.assertEquals("��纣��", product.getProdName());
		Assert.assertEquals("�������ƿ�", product.getProdDetail());
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
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
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
	 	search.setSearchKeyword("�ƺ�");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}