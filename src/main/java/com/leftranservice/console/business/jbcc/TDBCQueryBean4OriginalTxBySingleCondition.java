package com.leftranservice.console.business.jbcc;

/**
 * 通过IACT查询原始交易信息
 * @author Bob
 *
 */
public class TDBCQueryBean4OriginalTxBySingleCondition {

	private String iactTableName;
	private String tbcTableName;	
	private String accountID;
	private long startTS;                   //开始时间
	private long endTS;                    //结束时间
	private String qualifier;
	private String value;
	
	
	public String getIactTableName() {
		return iactTableName;
	}
	public void setIactTableName(String iactTableName) {
		this.iactTableName = iactTableName;
	}
	public String getTbcTableName() {
		return tbcTableName;
	}
	public void setTbcTableName(String tbcTableName) {
		this.tbcTableName = tbcTableName;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public long getStartTS() {
		return startTS;
	}
	public void setStartTS(long startTS) {
		this.startTS = startTS;
	}
	public long getEndTS() {
		return endTS;
	}
	public void setEndTS(long endTS) {
		this.endTS = endTS;
	}
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
