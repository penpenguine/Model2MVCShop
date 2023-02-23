package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class PurchaseDAO {

	public PurchaseDAO() {
	}
	
	public void insertPurchase(Purchase purchase) throws Exception{
		
		Connection con = DBUtil.getConnection();
		String sql = 	"INSERT "+ 
				"INTO transaction "+ 
				"VALUES (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,SYSDATE,?) ";

		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, purchase.getTranNo());
		pStmt.setString(2, purchase.getBuyer().getUserId());
		pStmt.setString(3, purchase.getPruchaseOption());
		pStmt.setString(4, purchase.getReceiverName());
		pStmt.setString(5, purchase.getReceiverPhone());
		pStmt.setString(6, purchase.getDlvyAddr());
		pStmt.setString(7, purchase.getDlyRequest());
		pStmt.setString(8, purchase.getTranCode());
		pStmt.setString(9, purchase.getDlvyDate());
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
	}//e insertPurchase
	
	public Purchase findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
			
		String sql = 	"SELECT "+
								"tran_no, prod_no, buyer_id, payment_option, receiver_name, receiver_phone, demailaddr, dlvy_request, tran_status_code, order_data, dlvy_date" + 
								"FROM transaction WHERE tran_no = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, tranNo);

		ResultSet rs = pStmt.executeQuery();

		Purchase purchase = null;

		while (rs.next()) {
			purchase = new Purchase();
			
			UserService userservice = new UserServiceImpl();
			ProductService productservice = new ProductServiceImpl();
			
			purchase.setTranNo(rs.getInt("tran_no"));
			purchase.setBuyer(userservice.getUser(rs.getString("buyer_id")));
			purchase.setPurchaseProd(productservice.getProduct(rs.getInt("prod_no")));
			purchase.setPruchaseOption(rs.getString("payment_option"));
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setDlvyAddr(rs.getString("demailaddr"));
			purchase.setDlyRequest(rs.getString("dlvy_request"));
			purchase.setTranCode(rs.getString("tran_status_code"));
			purchase.setOrderDate(rs.getDate("order_data"));
			purchase.setDlvyDate(rs.getString("dlvy_date"));
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		return purchase;
	}//e findPurchase
	
public Map<String , Object> getPurchaseList(Search search) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		String sql = "SELECT tran_no, buyer_id, tran_status_code  FROM  transaction " +
						"WHERE buyer_id = ?" +
						"ORDER BY tran_no";
		
		System.out.println("PurchaseDAO::Original SQL :: " + sql);// test
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("PurchaseDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println("search : " +search);//test

		List<Purchase> list = new ArrayList<Purchase>();
		
		ProductService productservice = new ProductServiceImpl();

		while(rs.next()){
			Purchase purchase = new Purchase();
			purchase.setTranNo(rs.getInt("tran_no"));
			purchase.setPurchaseProd(productservice.getProduct(rs.getInt("prod_no")));
			purchase.setTranCode(rs.getString("tran_status_code"));
			list.add(purchase);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}//e getPurchaseList

public void updatePurchase(Purchase purchase) throws Exception {

	Connection con = DBUtil.getConnection();

	String sql = 	"UPDATE transaction "+
							"SET payment_option = ?, receiver_name = ?, receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ?"+ 
							"WHERE tran_no = ?";
	
	PreparedStatement pStmt = con.prepareStatement(sql);
	pStmt.setString(1, purchase.getPruchaseOption());
	pStmt.setString(2, purchase.getReceiverName());
	pStmt.setString(3, purchase.getReceiverPhone());
	pStmt.setString(4, purchase.getDlvyAddr());
	pStmt.setString(5, purchase.getDlyRequest());
	pStmt.setString(6, purchase.getDlvyDate());
	pStmt.executeUpdate();
	
	pStmt.close();
	con.close();
}

// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
private int getTotalCount(String sql) throws Exception {
	
	sql = "SELECT COUNT(*) "+
	          "FROM ( " +sql+ ") countTable";
	
	Connection con = DBUtil.getConnection();
	PreparedStatement pStmt = con.prepareStatement(sql);
	ResultSet rs = pStmt.executeQuery();
	
	int totalCount = 0;
	if( rs.next() ){
		totalCount = rs.getInt(1);
	}
	
	pStmt.close();
	con.close();
	rs.close();
	
	return totalCount;
}

// 게시판 currentPage Row 만  return 
private String makeCurrentPageSql(String sql , Search search){
	sql = 	"SELECT * "+ 
				"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
								" 	FROM (	"+sql+" ) inner_table "+
								"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
				"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
	
	System.out.println("PruchaseDAO :: make SQL :: "+ sql);	
	
	return sql;
}

}
