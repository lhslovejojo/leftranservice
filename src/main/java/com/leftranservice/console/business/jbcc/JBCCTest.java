package com.leftranservice.console.business.jbcc;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leftranservice.console.util.DateUtil;
import com.tiande.jbcc.clien.JBCCClien;

import cn.tiandechain.jbcc.bean.DataBuilder;
import cn.tiandechain.jbcc.message.JBCCResult;
import cn.tiandechain.jbcc.message.RegulationType;
import cn.tiandechain.jbcc.message.Transaction;

public class JBCCTest {

	// 用户签名私钥
	static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANFSChgpECk6HMroQ5JPBUMDXme18qzxS21ThryPoHIlJf2zGipOx6f9dpiihsKtuIXMph3OrPt1ThHgoE2FEK6jp7xZz+L0fs9XaftU70RpNmC5iEMC/8TdGZaiiCKPsJ+Y/d3EsNJEinNDyCYhyMWGJostdKSfGwpixAa1LphHAgMBAAECgYEAookqr3ILS4gFXYWXGp/jfRK7lpqQ8rt3D1Bhej+onZm880/EjH+ZxxcopQLm03pjovmzL8gRSDzhPz6GG0utFBX631cYJDwuhrcAdJsNWQKKgKGujSr2iEfIybRPSbDFomCueAz+dd3t8YdhI/HIJjNh6Msjtfvwuy5Xa7no+oECQQD6pOT+bQ91bCkbDsjxSeeRga4MN0He7A18i68lyDSHLZDlTxmhv1qH1S4GsIcQlq61upyxbG+gREpBoIHlWLvBAkEA1csXS6cvStd+3S1gHpYx+lBByleBM4MkVRgoFY2MfY5n1jQAdeonbB6ZE4bFFqiLMYLDryzZVd+8Bi6XONb2BwJAIr7LE84Qj4mP5TVWe8Rv+obMltruIbX9ZJ+EhytKxNZ3OKKPfhGlviC6QaklABKzY3PcFuheTQxKcvCKMT3swQJALoFvH9XOQ8l8n0AvmHezh2/N3+YJBNuSG7CVh5qy5N0FWWMWdo/o42wEgGrglH01Sh/X0VNAM53nmLCp1INuIQJAc9r9nwF0yX3F2bE5q+zw+XbA2HXT5oTUSW1tVm0kIULjpWNCChSWpTnZSJLeVOGRl9wGJTBHhy7asCJvWsGh+g==";
    static String abc_name="third_pay_abc";
    static String abc_user_table="userinfo";
    static String abc_account_table="fund_account_info";
	public static void main(String[] args) throws Exception {

		JBCCResult r = null;
		// 创建用户表
//		r = createUserTable();
		//创建账户表
//		r =createAccountTable();
		
		// 插入会员和账户信息
		r =addUserAddCount();

		// 账户发送交易
		// sendMsg();

		// 创建唯一索引
	    //r = createUniqueIndex();
		

		// 创建索引
//	    r =  createIndex();
	    

		if (r.getStatus() == 0) {// 0成功，1失败，2超时
			System.out.println("success r.getMsg():" + r.getMsg());
		} else if (r.getStatus() == 1) {
			System.out.println("fail r.getMsg():" + r.getMsg());
		} else if (r.getStatus() == 2) {
			System.out.println("timeout r.getMsg():" + r.getMsg());
		}

	}

	/**
	 * 创建新链测试
	 * 
	 * @author xiaoming
	 */
	private static JBCCResult createUserTable() {
		JBCCClien jbccClient = getJBCCClien();

		JBCCResult result = null;

		/**
		 * ***********************************************************创建TBC 开始
		 *******************************************************/
		/** ------------------------------------------------创建userinfo表--start------------------------------------------------------------ */
		DataBuilder workBuilder = new DataBuilder(abc_name, "userinfo", "y", new Date());
		workBuilder.addParam("exchange_id","varchar(20)"); //交易所编码
		workBuilder.addParam("serial_no","varchar(20)");//序列号;
		workBuilder.addParam("mem_code","varchar(20)");//会员编码;
		workBuilder.addParam("exchange_member_status","varchar(20)");//会员状态
		workBuilder.addParam("full_name","varchar(100)"); //名字全程
		workBuilder.addParam("short_name","varchar(100)"); //名字简称
		workBuilder.addParam("en_full_name","varchar(100)"); //英文名字全程
		workBuilder.addParam("en_short_name","varchar(100)"); //英文名字简称
		workBuilder.addParam("gender","varchar(2)"); //性别  0为 男 1为女
		workBuilder.addParam("nationality","varchar(20)"); //国籍
		workBuilder.addParam("id_kind","varchar(20)"); //
		workBuilder.addParam("id_no","varchar(20)");
		workBuilder.addParam("tel","varchar(20)");
		workBuilder.addParam("up_mem_code","varchar(20)");
		workBuilder.addParam("broker_code","varchar(20)");
		workBuilder.addParam("busi_date","varchar(20)");
		workBuilder.addParam("busi_time","timestamp null");  //注册时间
		workBuilder.addParam("last_busi_time","timestamp null"); //最后修改时间
//		
//		workBuilder.addParam("exchangeId", "int(10)");
//		workBuilder.addParam("banquanjia_evidence_code", "tinytext");
//		workBuilder.addParam("evidence_code", "char(11)");
//		workBuilder.addParam("account", "int(11)");
//		workBuilder.addParam("work_id", "varchar(64)");
//		workBuilder.addParam("work_name", "varchar(80)");
//		workBuilder.addParam("upload_username", "varchar(64)");
//		workBuilder.addParam("account", "double(6,2)");

		try {
			result = jbccClient.createChain(workBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jbccClient.close();
		}
		
		return result;

		/** ------------------------------------------------创建works表--end------------------------------------------------------------ */
	}
	/**
	 * 创建新链测试
	 * 
	 * @author xiaoming
	 */
	private static JBCCResult createAccountTable() {
		JBCCClien jbccClient = getJBCCClien();

		JBCCResult result = null;

		/**
		 * ***********************************************************创建TBC 开始
		 *******************************************************/
		/** ------------------------------------------------创建资金账号 fund_account_info表--start------------------------------------------------------------ */
		DataBuilder workBuilder = new DataBuilder(abc_name, "fund_account_info", "y", new Date());
		workBuilder.addParam("exchange_id","varchar(20)"); //交易所编码
		workBuilder.addParam("serial_no","varchar(20)");//序列号;
		workBuilder.addParam("mem_code","varchar(20)");//会员编码;
		workBuilder.addParam("fund_account_code","varchar(20)");//账户编码
		workBuilder.addParam("fund_account_status","varchar(20)");//账户状态
		workBuilder.addParam("total_bal","decimal(20,2) NULL"); //资金总余额
		workBuilder.addParam("able_bal","decimal(20,2) NULL"); //可用金额
		workBuilder.addParam("frozen_bal","decimal(20,2) NULL"); //冻结金额
		workBuilder.addParam("advance_bal","decimal(20,2) NULL"); //可提金额
		workBuilder.addParam("busi_time","timestamp null");  //业务创建时间
		workBuilder.addParam("last_busi_time","timestamp null"); //最后修改时间

		try {
			result = jbccClient.createChain(workBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jbccClient.close();
		}
		
		return result;

		/** ------------------------------------------------创建works表--end------------------------------------------------------------ */
	}
	/**
	 * 创建用户和账户
	 * 
	 * @author xiaoming
	 * @throws Exception
	 */
	private static JBCCResult addUserAddCount() {
		JBCCClien jbccClient = getJBCCClien();
		JBCCResult result = null;

		List<DataBuilder> dataList = new ArrayList<DataBuilder>();

		DataBuilder workBuilder = new DataBuilder(abc_name, "userinfo", "y", new Date());
		workBuilder.addParam("exchange_id","10001"); //交易所编码
		workBuilder.addParam("serial_no","10001");//序列号;
		workBuilder.addParam("mem_code","0001");//会员编码;
		workBuilder.addParam("exchange_member_status","1");//会员状态
		workBuilder.addParam("full_name","李红松"); //名字全程
		workBuilder.addParam("short_name","松"); //名字简称
		workBuilder.addParam("en_full_name","hongsong.li"); //英文名字全程
		workBuilder.addParam("en_short_name","hs.l"); //英文名字简称
		workBuilder.addParam("gender","0"); //性别  0为 男 1为女
		workBuilder.addParam("nationality","CHN"); //国籍
		workBuilder.addParam("id_kind","1"); //
		workBuilder.addParam("id_no","3203221989112312345");
		workBuilder.addParam("tel","15801403022");
		workBuilder.addParam("up_mem_code","1002");
		workBuilder.addParam("broker_code","1003");
		workBuilder.addParam("busi_date","20170330");
		String dateStr=DateUtil.DateToString(new Date(),DateUtil.YYYYMMDDHHMMSSSSS);
		workBuilder.addParam("busi_time",dateStr);  //注册时间
		workBuilder.addParam("last_busi_time",dateStr); //最后修改时间
		dataList.add(workBuilder);
		
		DataBuilder accountBuilder = new DataBuilder(abc_name, "fund_account_info", "y", new Date());
		accountBuilder.addParam("exchange_id","varchar(20)"); //交易所编码
		accountBuilder.addParam("serial_no","varchar(20)");//序列号;
		accountBuilder.addParam("mem_code","0001");//会员编码;
		accountBuilder.addParam("fund_account_code","F_0001");//账户编码
		accountBuilder.addParam("fund_account_status","1");//账户状态
		accountBuilder.addParam("total_bal","10000.00"); //资金总余额
		accountBuilder.addParam("able_bal","10000.00"); //可用金额
		accountBuilder.addParam("frozen_bal","0.00"); //冻结金额
		accountBuilder.addParam("advance_bal","10000.00"); //可提金额
		accountBuilder.addParam("busi_time",dateStr);  //业务创建时间
		accountBuilder.addParam("last_busi_time",dateStr); //最后修改时间
		dataList.add(accountBuilder);
		try {
			result = jbccClient.initTran(dataList);// 初始化一个新账户
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jbccClient.close();
		}

		return result;
	}

	/**
	 * 创建唯一索引
	 * 
	 * @author liwh
	 */
	public static JBCCResult createUniqueIndex() throws Exception {
		JBCCClien jbccClient = getJBCCClien();
		List<String> columnList = new ArrayList<String>();
		columnList.add("copyright_id");
		columnList.add("work_id");
		JBCCResult r = jbccClient.createUniqueIndex("third_pay_abc", "works", columnList);
		jbccClient.close();
		return r;
	}

	/**
	 * 创建索引
	 * 
	 * @author liwh
	 */
	public static JBCCResult createIndex() throws Exception {
		JBCCClien jbccClient = getJBCCClien();
		List<String> columnList = new ArrayList<String>();
		columnList.add("copyright_id");
		columnList.add("work_id");
		JBCCResult r = jbccClient.createIndex(abc_name, "works", columnList);
		jbccClient.close();
		return r;
	}

	/**
	 * 成交数据拆分
	 */
	private static void transactionSplit() {
		// 初始化jbcc clien
		JBCCClien jbccClient = getJBCCClien();

		// 交易数据
		List<DataBuilder> dataList = new ArrayList<DataBuilder>();
		DataBuilder data = new DataBuilder(abc_name, abc_account_table, "y", new Date());
		Transaction tran = new Transaction();

		tran.setRegulationType(RegulationType.REGULATION_ADD);// 增减类型

		Map<String, Double> regulationMap = new HashMap<String, Double>();
		
		regulationMap.put("account", 1d);
		regulationMap.put("account_1", -1);
		tran.setRegulationMap();// 增减字段 值

		Map<String, String> filterMap = new HashMap<String, String>();
		filterMap.put("upload_username", "lmd");
		tran.setFilterMap(filterMap);// 过滤条件
		//
		data.setTransaction(tran);

		dataList.add(data);

		// 发送交易
		try {
			jbccClient.sendAndReturn(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		jbccClient.close();
	}

	private static JBCCClien getJBCCClien() {
		JBCCClien jbccClient = new JBCCClien();
		String[] nodeMqTcps = new String[] { "tcp://192.168.0.96:61616?" };

		// 节点公钥
		String[] nodePublicKeys = new String[] {
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkEi8q9lnY+uiIVYIRQjFwGnk7INOgK6q8vUeXWyhlbDrnWS6Cd9mvV63AAPBPu6MIzwiYU0zRVaGBxPHHKJJv+1rL5WpEvjJ4vlSSyv2NOTrSMNcADwMU3iWGZJjaQ78qnuk7seRVpNpIW3JzI9dxz504t6hlwJQfovgGHRV9dQIDAQAB"};

		jbccClient.startClien(privateKey, "admin_1", nodeMqTcps, "admin", "123", nodePublicKeys);
		return jbccClient;
	}
}
