package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String prodNo=request.getParameter("prodNo");
		String menu = request.getParameter("menu");
		
		System.out.println(menu);//test
		System.out.println(prodNo);//test
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(Integer.parseInt(prodNo));
		
		request.setAttribute("product", product);

		if (menu.equals("manage")) {
			return "forward:/updateProductView.do" ;
		}else if (menu.equals("search")) {
			return "forward:/product/getProductSearch.jsp";
		}
		return "forward:/product/getProductView.jsp";
	}//e excute
}//e class
