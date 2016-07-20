package cn.buer.web.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.buer.util.snowflake.IdWorker;


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
	/**
	 * 分表数量
	 */
	private int splitTableNum = 0;
	/**
	 * 表集合
	 */
	private Map<String, Table> tableMap = new HashMap<String, Table>();;
	private DataBase() {}
	/**
	 * 获取数据库实例
	 * @param pow
	 * @return
	 */
	public static final DataBase getInstance(Integer pow){
		long dataSum =  new Double(Math.pow(2D,new Integer(pow).doubleValue())).longValue();
		DataBase dataBase = new DataBase();
		Table table = dataBase.createTable(Table.RAW_TALBE_NAME);
		IdWorker idWorker = new IdWorker(0, 0);
		for(int i = 0; i < dataSum; i++){
			//TODO 由Java的64位的long型传到页面再传到后台使用，结果达到上限，java的long不能用javascript的number表示出上限，需要用string代替来传值，否则会丧失精度
//			table.add(idWorker.nextId());
			table.add(String.valueOf(idWorker.nextId()));
		}
		return dataBase;
	}
	
	/**
	 * 分表
	 * @param splitTableNum
	 * @return
	 */
	public List<Table> split(int splitTableNum){
		this.splitTableNum = splitTableNum;
		List<Table> result = new ArrayList<>();
		for(int i = 0; i < splitTableNum; i++ ){
			Table table = this.createTable(Table.RAW_TALBE_NAME + "_" + i);
			result.add(table);
		}
		Table rawTalbe = this.getTable(Table.RAW_TALBE_NAME);
		List<Object> dataList = rawTalbe.getDataList();
		for(Object data : dataList){
			StringBuilder targetTableName = new StringBuilder(Table.RAW_TALBE_NAME);
			targetTableName.append("_");
			int tableIndex = (int) (Long.valueOf((String)data)%splitTableNum);
			targetTableName.append(tableIndex);
			Table targetTable = this.getTable(targetTableName.toString());
			targetTable.add(data);
		}
		return result;
	}
	
	/**
	 * 查询数据
	 * @param data
	 * @return
	 */
	public Table queryData(String data){
		StringBuilder targetTableName = new StringBuilder(Table.RAW_TALBE_NAME);
		targetTableName.append("_");
		int tableIndex;
		try {
			tableIndex = (int) (Long.valueOf(data)%splitTableNum);
		} catch (NumberFormatException e) {
			return null;
		}
		targetTableName.append(tableIndex);
		Table table = this.getTable(targetTableName.toString());
		if (table != null && table.getData(data) != null) {
			Table result = new Table(table.getTableName());
			result.add(data);
			return result;
		}else{
			return null;
		}
	}
	public Table createTable(String tableName){
		Table talbe = new Table(tableName);
		tableMap.put(tableName, talbe);
		return talbe;
	}
	
	public Table getTable(String tableName){
		return tableMap.get(tableName);
	}
	
	public Map<String, Table> getAllTable(){
		return tableMap;
	}
	public int getSplitTableNum() {
		return splitTableNum;
	}
	
}
