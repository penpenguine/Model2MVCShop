package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class AddPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request);//test
		Purchase purchase = new Purchase();
		UserService userService = new UserServiceImpl();
		ProductService productService = new ProductServiceImpl();
		PurchaseService purchaseService = new PurchaseServiceImpl();
		
		purchase.setBuyer(userService.getUser(request.getParameter("buyerId")));
		purchase.setPurchaseProd(productService.getProduct(Integer.parseInt(request.getParameter("prodNo"))));
		purchase.setPruchaseOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDlvyAddr(request.getParameter("receiverAddr"));
		purchase.setDlyRequest(request.getParameter("receiverRequest"));
		purchase.setTranCode("1");
		purchase.setDlvyDate(request.getParameter("receiverDate").replaceAll("-",""));
		
		System.out.println(purchase);//test
		purchaseService.addPurchase(purchase);
		
		request.setAttribute("purchaseVO", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
