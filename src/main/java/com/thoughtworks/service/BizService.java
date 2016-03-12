package com.thoughtworks.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.thoughtworks.activity.Activity;
import com.thoughtworks.constant.Constant;
import com.thoughtworks.factory.Factory;
import com.thoughtworks.vo.ItemVo;
import com.thoughtworks.vo.TicketVo;

public class BizService {
	
	/**
	 * 将文件中的商品进行mapReduce
	 * @return Map[key=>商品条码,value=>商品的数量]
	 * 输入：
	 * [
     *   'ITEM000001',
     *   'ITEM000001',
     *   'ITEM000001',
     *   'ITEM000001',
     *   'ITEM000001',
     *   'ITEM000003-2',
     *   'ITEM000005',
     *   'ITEM000005',
     *   'ITEM000005'
	 *  ]
	 * 输出：
	 * ITEM000001-5
	 * ITEM000003-2
	 * ITEM000005-3
	 */
	public static Map<String,Integer> mapReduce(String filePath) throws IOException {
		Map<String, Integer> resultMap = new LinkedHashMap<String, Integer>();
		String strInput = FileUtils.readFileToString(new File(filePath));
		strInput = StringUtils.remove(strInput, "[");
		strInput = StringUtils.remove(strInput, "]");
		strInput = StringUtils.remove(strInput, "'");
		strInput = StringUtils.remove(strInput, "\n");
		strInput = StringUtils.remove(strInput, " ");
		String[] array = strInput.split(",");
		for(String e : array) {
			if(StringUtils.contains(e, "-")) {
				String[] ee = e.split("-");
				Integer qty = resultMap.get(ee[0]) != null ? resultMap.get(ee[0]) : 0;
				Integer delta = 0;
				try { delta = Integer.parseInt(ee[1]);} catch(Exception ex) {}
				qty += delta;
				resultMap.put(ee[0], qty);
			}
			else {
				Integer qty = resultMap.get(e) != null ? resultMap.get(e) : 0;
				qty += 1;
				resultMap.put(e, qty);
			}
		}
		return resultMap;
	}
	
	/**
	 * 计算商品价格
	 */
	public static List<ItemVo> calculateItemVo(Map<String, Integer> map) {
		List<ItemVo> itemVoList = new ArrayList<ItemVo>();
		
		for(String itemCode : map.keySet()) {
			
			ItemVo itemVo = new ItemVo();
			itemVo.setItem(Constant.ITEM_CODE_ENTITY_MAP.get(itemCode));
			//放入购买的数量
			itemVo.setQty(map.get(itemCode));
			//计算没有优惠的价格
			itemVo.setItemSumPrice(itemVo.getItem().getUnitPrice().multiply(BigDecimal.valueOf(itemVo.getQty())));
			
			//加载当前的item的优惠活动
			Activity activity = Factory.getInstance().loadActivity(itemVo);
			
			if(activity != null) {
				//进行优惠计算
				activity.perform(itemVo);
			}
			
			itemVoList.add(itemVo);
		}
		
		return itemVoList;
	}
	
	/**
	 * 拼装Ticket
	 */
	public static TicketVo createTicet(List<ItemVo> itemVoList) {
		TicketVo ticketVo = new TicketVo();
		ticketVo.setItemVoList(itemVoList);
		return ticketVo;
	}
	
	/**
	 * 输出小票内容
	 * inputFilePath 为输入的文件
	 */
	public static String output(String inputFilePath) {
		Constant.loadCache();
		try {
			Map<String,Integer> si = mapReduce(inputFilePath);
			List<ItemVo> voList = calculateItemVo(si);
			TicketVo ticket = createTicet(voList);
			return ticket.format();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
