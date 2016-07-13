package cn.buer.util.mybatis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.DefaultCommentGenerator;
/**
 * 
    * @ClassName: MyCommentGenerator
    * @Description: 自定义mybatis generator 生成格式
    * @author WangJianMin
    * @date 2015年11月15日
    *
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
	@Override
	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * @Description:");
		innerClass.addJavaDocLine(" * @author ${user}");
		innerClass.addJavaDocLine(" * @version 1.0 " + sdf.format(new Date()));
		addJavadocTag(innerClass, false);
		innerClass.addJavaDocLine(" */");

	}

	@Override
	public void addFieldComment(Field field,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		field.addJavaDocLine("/**");
		field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		field.addJavaDocLine(" * 此字段是由MyBatis Generator自动生成，不要修改。");
		addJavadocTag(field, false);
		field.addJavaDocLine(" */");
	}

	@Override
	public void addGetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addSetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
	}
	@Override
	public void addGeneralMethodComment(Method method,
			IntrospectedTable introspectedTable) {
		StringBuilder sb = new StringBuilder();

        method.addJavaDocLine("/**"); 
        method
                .addJavaDocLine(" * 此方法是由MyBatis Generator自动生成不要修改。"); 

        sb.append(" * 这个方法对应于数据库表。 "); 
        sb.append(introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());

        addJavadocTag(method, false);

        method.addJavaDocLine(" */"); 
	}
	@Override
	public void addComment(XmlElement xmlElement) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		xmlElement.addElement(new TextElement("<!--")); 
		StringBuilder sb = new StringBuilder();
		sb.append("  WARNING - "); 
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		xmlElement.addElement(new TextElement(sb.toString()));
		xmlElement.addElement(new TextElement(
						"       这是MyBatis Generator自动生成, 不要修改。")); 

		sb.setLength(0);
		sb.append("        生成于 "); 
		sb.append(sdf.format(new Date()));
		sb.append('。');
		xmlElement.addElement(new TextElement(sb.toString()));

		xmlElement.addElement(new TextElement("-->"));
	}
}
