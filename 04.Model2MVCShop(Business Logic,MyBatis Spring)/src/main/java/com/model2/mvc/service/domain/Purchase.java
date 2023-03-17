package com.model2.mvc.service.domain;

import java.sql.Date;

public class Purchase {

	private User buyer;
	private String dlvyAddr;
	private String dlvyDate;
	private String dlyRequest;
	private Date orderDate;
	private String pruchaseOption;
	private Product purchaseProd;
	private	String receiverName;
	private String receiverPhone;
	private String tranCode;
	private int tranNo;
	
	private int pordNo;
	private String buyerID;
	
	public Purchase() {
	}

	public User getBuyer() {
		return buyer;
	}

	public String getDlvyAddr() {
		return dlvyAddr;
	}

	public String getDlvyDate() {
		return dlvyDate;
	}

	public String getDlyRequest() {
		return dlyRequest;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public String getPruchaseOption() {
		return pruchaseOption;
	}

	public Product getPurchaseProd() {
		return purchaseProd;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public String getTranCode() {
		return tranCode;
	}

	public int getTranNo() {
		return tranNo;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public void setDlvyAddr(String dlvyAddr) {
		this.dlvyAddr = dlvyAddr;
	}

	public void setDlvyDate(String dlvyDate) {
		this.dlvyDate = dlvyDate;
	}

	public void setDlyRequest(String dlyRequest) {
		this.dlyRequest = dlyRequest;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setPruchaseOption(String pruchaseOption) {
		this.pruchaseOption = pruchaseOption;
	}

	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	
	public int getPordNo() {
		return pordNo;
	}

	public String getbuyerID() {
		return buyerID;
	}

	public void setPordNo(int pordNo) {
		this.pordNo = pordNo;
	}

	public void setbuyerID(String buyerID) {
		this.buyerID = buyerID;
	}

	@Override
	public String toString() {
		return "Purchase [buyer=" + buyer + ", dlvyAddr=" + dlvyAddr + ", dlvyDate=" + dlvyDate + ", dlyRequest="
				+ dlyRequest + ", orderDate=" + orderDate + ", pruchaseOption=" + pruchaseOption + ", purchaseProd="
				+ purchaseProd + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone + ", tranCode="
				+ tranCode + ", tranNo=" + tranNo + "]";
	}
	
}
