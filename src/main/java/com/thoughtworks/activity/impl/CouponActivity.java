package com.thoughtworks.activity.impl;

import java.math.BigDecimal;

import com.thoughtworks.activity.Activity;
import com.thoughtworks.vo.ItemVo;

public class CouponActivity implements Activity {
	
	private static final int MOD = 3;
	
	public void perform(ItemVo itemVo) {
		
		
		BigDecimal originalItemSumPrice = itemVo.getItemSumPrice(); //没有优惠的价格
		int qty = itemVo.getQty(); //没有优惠的数量
		
		//计算优惠的数量
		int saveQty = qty/MOD;
		itemVo.setSaveQty(saveQty);
		
		//计算节省的价钱
		BigDecimal saveItemSumPrice = itemVo.getItem().getUnitPrice().multiply(BigDecimal.valueOf(saveQty));
		itemVo.setSaveItemSumPrice(saveItemSumPrice);
		
		//计算实际支付的价钱
		itemVo.setItemSumPrice(originalItemSumPrice.subtract(saveItemSumPrice));
		
	}

}
