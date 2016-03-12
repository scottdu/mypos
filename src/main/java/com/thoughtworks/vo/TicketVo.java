package com.thoughtworks.vo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import com.thoughtworks.domain.ItemEntity;

public class TicketVo {
	
	private List<ItemVo> itemVoList;
	
	private BigDecimal totalSumPrice = BigDecimal.ZERO;
	
	private BigDecimal totalSaveSumPrice = BigDecimal.ZERO;
	
	private boolean hasSaveQty = false;
	
	private static DecimalFormat DF = new DecimalFormat("0.00");
	
	public List<ItemVo> getItemVoList() {
		return itemVoList;
	}

	public void setItemVoList(List<ItemVo> itemVoList) {
		this.itemVoList = itemVoList;
	}

	public String format() {
		
		StringBuilder buf = new StringBuilder("***<没钱赚商店>购物清单***\n");
		StringBuilder buff = new StringBuilder("----------------------\n买二赠一商品：\n");
		for(ItemVo itemVo : itemVoList) {
			ItemEntity item = itemVo.getItem();
			buf.append("名称:").append(item.getName());
			buf.append(",数量:").append(itemVo.getQty()).append(item.getUnit());
			buf.append(",单价:").append(DF.format(item.getUnitPrice())).append("(元)");
			buf.append(",小计:").append(DF.format(itemVo.getItemSumPrice())).append("(元)");
			
			this.totalSumPrice = this.totalSumPrice.add(itemVo.getItemSumPrice());
			
			if(itemVo.getSaveItemSumPrice().compareTo(BigDecimal.ZERO) > 0) {
				if(itemVo.getSaveQty() <= 0) {
					buf.append(",节省").append(DF.format(itemVo.getSaveItemSumPrice())).append("(元)");
				}
				this.totalSaveSumPrice = this.totalSaveSumPrice.add(itemVo.getSaveItemSumPrice());
			}
			
			buf.append("\n");
			
			if(itemVo.getSaveQty() > 0) {
				this.hasSaveQty = true;
				buff.append("名称:").append(item.getName());
				buff.append(",数量:").append(itemVo.getSaveQty()).append(item.getUnit());
				buff.append("\n");
			}

		}
		
		if(this.hasSaveQty) {
			buf.append(buff);
		}
		
		buf.append("----------------------\n");
		buf.append("总计:").append(DF.format(this.totalSumPrice)).append("(元)\n");
		if(this.totalSaveSumPrice.compareTo(BigDecimal.ZERO) > 0) {
			buf.append("节省:").append(DF.format(this.totalSaveSumPrice)).append("(元)\n");
		}
		buf.append("**********************");
		return buf.toString();
	}
}
