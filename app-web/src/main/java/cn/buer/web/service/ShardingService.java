package cn.buer.web.service;

import java.util.List;

import cn.buer.web.bo.Table;

/**
 * 分表服务
 * @author WangBuEr
 *
 */
public interface ShardingService {
	/**
	 * 初始化数据
	 * @param pow
	 * @return 初始化的数据
	 */
	Table initData(Integer pow);
	/**
	 * 分表
	 * @param splitTableNum 分表数量
	 * @return 分表之后的数据
	 */
	List<Table> splitTable(Integer splitTableNum);
	/**
	 * 验证分表数据的正确性
	 * @param data 需要验证的数据
	 * @return 数据所在的分表及当前查询到的数据
	 */
	Table validationData(Object data);
}
