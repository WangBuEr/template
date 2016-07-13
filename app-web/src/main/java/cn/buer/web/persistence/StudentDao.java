package cn.buer.web.persistence;

import cn.buer.web.domain.Student;

public interface StudentDao {
    /**
     * 此方法是由MyBatis Generator自动生成不要修改。
     * 这个方法对应于数据库表。 student
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 此方法是由MyBatis Generator自动生成不要修改。
     * 这个方法对应于数据库表。 student
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    int insert(Student record);

    /**
     * 此方法是由MyBatis Generator自动生成不要修改。
     * 这个方法对应于数据库表。 student
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    int insertSelective(Student record);

    /**
     * 此方法是由MyBatis Generator自动生成不要修改。
     * 这个方法对应于数据库表。 student
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    Student selectByPrimaryKey(Long id);

    /**
     * 此方法是由MyBatis Generator自动生成不要修改。
     * 这个方法对应于数据库表。 student
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     * 此方法是由MyBatis Generator自动生成不要修改。
     * 这个方法对应于数据库表。 student
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    int updateByPrimaryKey(Student record);
}