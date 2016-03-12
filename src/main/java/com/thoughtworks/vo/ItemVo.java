package com.thoughtworks.vo;

import java.math.BigDecimal;

import com.thoughtworks.domain.ActivityEntity;
import com.thoughtworks.domain.ItemEntity;

public class ItemVo {
	
	private ItemEntity item;
	private ActivityEntity activity;
	
	//实际数量
	private int qty;
	
	//优惠数量
	private int saveQty;
	
	//节省价格
	private BigDecimal saveItemSumPrice = BigDecimal.ZERO;
	
	//小计
	private BigDecimal itemSumPrice = BigDecimal.ZERO;
	
	public ItemEntity getItem() {
		return item;
	}
	public void setItem(ItemEntity item) {
		this.item = item;
	}
	public ActivityEntity getActivity() {
		return activity;
	}
	public void setActivity(ActivityEntity activity) {
		this.activity = activity;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getItemSumPrice() {
		return itemSumPrice;
	}
	public void setItemSumPrice(BigDecimal itemSumPrice) {
		this.itemSumPrice = itemSumPrice;
	}
	public int getSaveQty() {
		return saveQty;
	}
	public void setSaveQty(int saveQty) {
		this.saveQty = saveQty;
	}
	public BigDecimal getSaveItemSumPrice() {
		return saveItemSumPrice;
	}
	public void setSaveItemSumPrice(BigDecimal saveItemSumPrice) {
		this.saveItemSumPrice = saveItemSumPrice;
	}
}
