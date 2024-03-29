<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">거래번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">주문자</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">주문상품</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송지</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=${purchase.tranNo }">${purchase.tranNo }</a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${purchase.buyer.getUserName()}">${purchase.buyer.getUserName()}</a>
		</td>
		<td></td>
		<td align="left">
		<a href="/getProduct.do?prodNo=${purchase.purchaseProd.getProdNo()}&menu=search">${purchase.purchaseProd.getProdName()}</a>
		</td>
		<td></td>
		<td align="left">${purchase.buyer.getPhone() }</td>
		<td></td>
		<td align="left">
			<c:if test="${empty purchase.tranCode}">
				판매중
			</c:if>
			<c:if test="${not empty purchase.tranCode}">
				<c:if test="${purchase.tranCode.trim() eq '1'}">
					주문완료
				</c:if>
				<c:if test="${purchase.tranCode.trim() eq '2'}">
					배송중
				</c:if>
				<c:if test="${purchase.tranCode.trim() eq '3'}">
					구매완료
				</c:if>
			</c:if>
		
		</td>
		<td></td>
		<td>
		<c:if test="${purchase.tranCode.trim() eq '2'}">
					<a href="/updateTranCodedo?tranNo=${purchase.tranNo}&tranCode=3"> 물건도착 </a>
				</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
		   		<jsp:include page="../common/pageNavigatorPurchase.jsp"/>	
    	</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>
</div>
</body>
</html>