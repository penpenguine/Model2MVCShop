package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class UpdateProductViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prodNo=request.getParameter("prodNo");
		
		ProductService service=new ProductServiceImpl();
		Product prodVO=service.getProduct(Integer.parseInt(prodNo));
	
		request.setAttribute("prodVO", prodVO);
		
		System.out.println(prodVO);//updateProdutView µð¹ö±ë
		
		return "forward:/product/updateProduct.jsp";
	}
}
