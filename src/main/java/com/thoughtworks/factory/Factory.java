package com.thoughtworks.factory;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.activity.Activity;
import com.thoughtworks.constant.Constant;
import com.thoughtworks.domain.ActivityEntity;
import com.thoughtworks.domain.ItemActivityEntity;
import com.thoughtworks.vo.ItemVo;

public class Factory {
	
	private static Factory instance = new Factory();
	
	public static Factory getInstance() {
		return instance;
	}
	
	public Activity loadActivity(ItemVo itemVo) {
		
		Activity activity = null;
		
		if(itemVo == null) {
			return activity;
		}
		
		String itemCode = itemVo.getItem() != null ? itemVo.getItem().getCode() : "";
		
		if(StringUtils.isEmpty(itemCode)) {
			return activity;
		}
		
		try {
			for(ItemActivityEntity itemActivity : Constant.ITEM_ACTIVITY_LIST) {
				if(StringUtils.equals(itemCode, itemActivity.getItemCode())) {
					ActivityEntity activityEntity = Constant.ACTIVITY_CODE_ENTITY_MAP.get(itemActivity.getActivityCode());
					if(activityEntity != null) {
						itemVo.setActivity(activityEntity);
						String className = activityEntity.getImplClassName();
						activity = (Activity) Class.forName(className).newInstance();
						break;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return activity;
	}
	
}
