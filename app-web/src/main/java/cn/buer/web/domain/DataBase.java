package cn.buer.web.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 数据仓储
 * @author WangBuEr
 *
 */
public class DataBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Table> tableMap;
	public DataBase() {
		tableMap = new HashMap<String, Table>();
	}
	
	public Table createTable(String tableName){
		Table talbe = new Table();
		tableMap.put(tableName, talbe);
		return talbe;
	}
	
	public Table getTable(String tableName){
		return tableMap.get(tableName);
	}
	
	public Map<String, Table> getAllTable(){
		return tableMap;
	}
}
