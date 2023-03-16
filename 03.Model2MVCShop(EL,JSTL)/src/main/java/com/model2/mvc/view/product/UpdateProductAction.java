package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prodNo=request.getParameter("prodNo");
		System.out.println(prodNo);

		Product prodVO = new Product();
		prodVO.setProdNo(Integer.parseInt(prodNo));
		prodVO.setProdName(request.getParameter("prodName"));
		prodVO.setProdDetail(request.getParameter("prodDetail"));
		prodVO.setManuDate(request.getParameter("manuDate"));
		prodVO.setPrice(Integer.parseInt(request.getParameter("price")));
		prodVO.setFileName(request.getParameter("fileName"));
		System.out.println(prodVO);
	
		ProductService service=new ProductServiceImpl();
		service.updateProduct(prodVO);
		/*
		HttpSession session=request.getSession();
		int sessionId=((ProductVO)session.getAttribute("product")).getProdNo();
	
		if(sessionId == Integer.parseInt(prodNo)){
			session.setAttribute("product", prodVO);
		}
		*/
		return "redirect:/getProduct.do?menu=search&prodNo="+prodNo;
	}
}