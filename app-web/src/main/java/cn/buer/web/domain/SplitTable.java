package cn.buer.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.buer.util.snowflake.IdWorker;

public class SplitTable implements Serializable {
	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String RAW_TALBE_NAME = "USER";
	private static final String UNLINE = "_";
	/**
	 * 分表数量
	 */
	private int splitTableNum = 0;
	/**
	 * 数据库
	 */
	private DataBase dataBase;
	/**
	 * 
	 * @param pow 幂 2^pow
	 */
	public SplitTable(int pow) {
		long dataSum =  new Double(Math.pow(2D,new Integer(pow).doubleValue())).longValue();
		dataBase = new DataBase();
		Table table = dataBase.createTable(RAW_TALBE_NAME);
		IdWorker idWorker = new IdWorker(0, 0);
		for(int i = 0; i < dataSum; i++){
			table.add(idWorker.nextId());
		}
	}
	/**
	 * 分表
	 * @param splitTableNum
	 * @return
	 */
	public List<Table> split(int splitTableNum){
		this.splitTableNum = splitTableNum;
		List<Table> result = new ArrayList<>();
		for(int i=0; i<splitTableNum; i++ ){
			Table table = dataBase.createTable(RAW_TALBE_NAME + UNLINE + i);
			result.add(table);
		}
		Table rawTalbe = dataBase.getTable(RAW_TALBE_NAME);
		List<Object> dataList = rawTalbe.getAllData();
		for(Object data : dataList){
			StringBuilder targetTableName = new StringBuilder(RAW_TALBE_NAME);
			targetTableName.append(UNLINE);
			int tableIndex = (int) ((long)data%splitTableNum);
			targetTableName.append(tableIndex);
			Table targetTable = dataBase.getTable(targetTableName.toString());
			targetTable.add(data);
		}
		return result;
	}
	/**
	 * 查询数据
	 * @param data 
	 * @return
	 */
	public String queryData(Object data){
		StringBuilder targetTableName = new StringBuilder(RAW_TALBE_NAME);
		targetTableName.append(UNLINE);
		int tableIndex = (int) ((long)data%splitTableNum);
		targetTableName.append(tableIndex);
		Table table = dataBase.getTable(targetTableName.toString());
		if (table != null && table.getData(data) != null) {
			return targetTableName.toString();
		}else{
			return null;
		}
	}
	public DataBase getDataBase() {
		return dataBase;
	}
	public static void main(String[] args) {
		SplitTable splitTable = new SplitTable(16);
		splitTable.split(10);
		Map<String, Table> tableMap = splitTable.getDataBase().getAllTable();
		for(Map.Entry<String, Table> entry : tableMap.entrySet()){
			System.out.println(entry.getKey() + "-->" + entry.getValue().getAllData().size());
		}
		
		Table rawTable = splitTable.getDataBase().getTable(RAW_TALBE_NAME);
		for(int i=0; i<100; i++){
			Object data = rawTable.getData(new Random().nextInt(rawTable.getAllData().size()));
			System.out.println(data + "-->" + splitTable.queryData(data));
		}
		
	}
}
