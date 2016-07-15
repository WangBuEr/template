package cn.buer.web.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String RAW_TALBE_NAME = "USER";
	private String tableName;
	private List<Object> dataList;

	public Table(String tableName) {
		this.dataList = new ArrayList<>();
		this.tableName = tableName;
	}
	public void add(Object data){
		dataList.add(data);
	}
	
	public List<Object> getAllData(){
		return dataList;
	}
	public Object getData(Object data){
		if(dataList.contains(data)){
			return dataList.get(dataList.indexOf(data));
		}else{
			return null;
		}
	}
	public Object getData(int index){
		return dataList.get(index);
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
