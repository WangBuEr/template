package cn.buer.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.buer.web.bo.DataBase;
import cn.buer.web.bo.Table;
import cn.buer.web.service.ShardingService;
@Service
public class ShardingServiceImpl implements ShardingService {
	private static DataBase dataBase = null;
	@Override
	public Table initData(Integer pow) {
		dataBase =  DataBase.getInstance(pow);
		return dataBase.getTable(Table.RAW_TALBE_NAME);
	}

	@Override
	public List<Table> splitTable(Integer splitTableNum) {
		return dataBase.split(splitTableNum);
	}

	@Override
	public Table validationData(Object data) {
		return dataBase.queryData(data);
	}

}
