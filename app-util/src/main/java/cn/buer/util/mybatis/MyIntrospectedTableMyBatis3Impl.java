package cn.buer.util.mybatis;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
/**
 * 
 *@Title:  自定义mybatis代码生成器
 *@Description:  自定义mybatis代码生成器
 *@Author:BuEr  
 *@Since:2015年11月21日  
 *@Version:1.1.0
 */
public class MyIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {
	@Override
	protected void calculateJavaClientAttributes() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(calculateJavaClientImplementationPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("DAOImpl"); //$NON-NLS-1$
        setDAOImplementationType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("DAO"); //$NON-NLS-1$
        setDAOInterfaceType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("Dao"); //$NON-NLS-1$
        setMyBatis3JavaMapperType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("SqlProvider"); //$NON-NLS-1$
        setMyBatis3SqlProviderType(sb.toString());
    }
	/**
	 * 设置生成mapper文件的文件名字
	 */
	@Override
	protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("Mapper.xml"); //$NON-NLS-1$
        return sb.toString();
    }
}
