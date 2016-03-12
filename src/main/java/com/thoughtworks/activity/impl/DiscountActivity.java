package com.thoughtworks.activity.impl;

import java.math.BigDecimal;

import com.thoughtworks.activity.Activity;
import com.thoughtworks.vo.ItemVo;

public class DiscountActivity implements Activity {

	private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0.95);
	
	@Override
	public void perform(ItemVo itemVo) {

		BigDecimal originalItemSumPrice = itemVo.getItemSumPrice(); //没有优惠的价格
		BigDecimal itemSumPrice = originalItemSumPrice.multiply(DISCOUNT); //优惠后的价格
		
		itemVo.setItemSumPrice(itemSumPrice);
		itemVo.setSaveItemSumPrice(originalItemSumPrice.subtract(itemSumPrice));
		
	}

}
