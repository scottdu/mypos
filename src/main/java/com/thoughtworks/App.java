package com.thoughtworks;

import com.thoughtworks.constant.Constant;
import com.thoughtworks.service.BizService;
import com.thoughtworks.util.DBUtil;


public class App 
{
	
	/**
     * Case-1
     * 可口可乐买二赠一
     * 羽毛球买二赠一
     * 苹果没有优惠
     */
	public static void testcase_1(String inputFilePath)
	{
		//删除所有产品的优惠配置
        DBUtil.executeUpdate("DELETE FROM `item_activity_relation`;");
        //设置ITEM000005(可口可乐)为买二赠一
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000005','ACTIVITY_1','0',now(),now());");
        //设置ITEM000001(羽毛球)为买二赠一
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000001','ACTIVITY_1','0',now(),now())");
        
        String result = BizService.output(inputFilePath);
        System.out.println("可口可乐,羽毛球-买二赠一:\n");
        System.out.println(result);
     
	}
	
	/**
	 * Case-2
	 * 所有产品都没有优惠的情况
	 */
	public static void testcase_2(String inputFilePath) {
		//删除所有产品的优惠配置
        DBUtil.executeUpdate("DELETE FROM `item_activity_relation`;");
        String result = BizService.output(inputFilePath);
        System.out.println("所有产品都没有优惠的情况:\n");
        System.out.println(result);
     
	}
	
	/**
	 * Case-3
	 * 只有苹果95折的情况
	 */
	public static void testcase_3(String inputFilePath) {
		//删除所有产品的优惠配置
        DBUtil.executeUpdate("DELETE FROM `item_activity_relation`;");
        //设置ITEM000003(苹果)为95折
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000003','ACTIVITY_2','1',now(),now());");
        
        String result = BizService.output(inputFilePath);
        System.out.println("只有苹果95折:\n");
        System.out.println(result);

	}
	
	/**
	 * Case-4
	 * 可口可乐-羽毛球 都设置为买二赠一
	 * 可口可乐-羽毛球 同时都设置为95折
	 * 苹果 设置为95折
	 * @param args
	 */
	public static void testcase_4(String inputFilePath) {
		//删除所有产品的优惠配置
        DBUtil.executeUpdate("DELETE FROM `item_activity_relation`;");
        //设置ITEM000005(可口可乐)为买二赠一，优先级高=0
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000005','ACTIVITY_1','0',now(),now());");
        //设置ITEM000005(可口可乐)为95折，优先级低=1
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000005','ACTIVITY_2','1',now(),now());");

        //设置ITEM000001(羽毛球)为买二赠一，优先级高=0
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000001','ACTIVITY_1','0',now(),now())");
        //设置ITEM000001(羽毛球)为95折，优先级低=1
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000001','ACTIVITY_2','1',now(),now())");
        
        //设置ITEM000003(苹果)为95折
        DBUtil.executeUpdate("INSERT INTO `item_activity_relation`(`item_code`,`activity_code`,`seq_no`,`created_ts`,`updated_ts`) VALUES ('ITEM000003','ACTIVITY_2','1',now(),now());");
        
        
        String result = BizService.output(inputFilePath);
        System.out.println("可口可乐,羽毛球-同时设置为买二赠一(优先级高)和95折(优先级低),苹果为95折:\n");
        System.out.println(result);
		
	}
	
	
    public static void main( String[] args )
    {
    	//读取初始化参数
    	Constant.getParameters();
    	
    	//输入的文件位置在项目根目录下的input下的input1.txt文件中
    	String inputFilePath = "./input/input1.txt";
    	
    	/**
         * Case-1
         * 可口可乐买二赠一
         * 羽毛球买二赠一
         * 苹果没有优惠
         */
    	testcase_1(inputFilePath);
        
    	System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
        
    	/**
    	 * Case-2
    	 * 所有产品都没有优惠的情况
    	 */
    	testcase_2(inputFilePath);
    	
    	System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
    	
    	/**
    	 * Case-3
    	 * 只有苹果95折的情况
    	 */
    	testcase_3(inputFilePath);

    	System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
    	
    	
    	/**
    	 * Case-4
    	 * 可口可乐-羽毛球 都设置为买二赠一
    	 * 可口可乐-羽毛球 同时都设置为95折
    	 * 苹果 设置为95折
    	 * @param args
    	 */
    	inputFilePath = "./input/input2.txt";
    	testcase_4(inputFilePath);
    }
}
