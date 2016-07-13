package cn.buer.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Object> dataList;

	public Table() {
		this.dataList = new ArrayList<>();
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
}
