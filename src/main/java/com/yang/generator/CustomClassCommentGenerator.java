package com.yang.generator;

import com.yang.generator.constant.PropertyConstant;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * 自定义注释普通类生成器
 * @see org.mybatis.generator.internal.DefaultCommentGenerator
 * @author shanfy
 * @date 2023/2/4 23:30
 */
public class CustomClassCommentGenerator extends AbstractCommentGenerator {

    /**
     * 初始化属性值
     */
    public CustomClassCommentGenerator() {
        this.properties = new Properties();
        this.suppressDate = false;
        this.suppressAllComments = false;
        // 默认生成数据库注释
        this.addRemarkComments = true;
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        // 赋值
        this.properties = properties;

        this.suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

        this.suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

        this.addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));

        String dateFormatString = properties.getProperty(PropertyConstant.DATE_FORMAT, PropertyConstant.DEFAULT_DATE_FORMAT);
        this.dateFormat = new SimpleDateFormat(dateFormatString);
    }

    /**
     * 添加类注释
     * @param topLevelClass 类对象
     * @param introspectedTable 数据库表信息
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        String author = properties.getProperty(PropertyConstant.AUTHOR, PropertyConstant.DEFAULT_AUTHOR);
        // 获取表注释
        String remarks = introspectedTable.getRemarks();

        topLevelClass.addJavaDocLine("/**");
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" *   " + remarkLine);
            }
        }
        topLevelClass.addJavaDocLine(" * @author " + author);
        String dateStr = getDateString();
        if(dateStr != null){
            topLevelClass.addJavaDocLine(" * @date " + dateStr);
        }
        topLevelClass.addJavaDocLine(" */");

    }

    /**
     * 添加属性注释
     * @param field 实体属性对象
     * @param introspectedTable 数据库表信息
     * @param introspectedColumn 数据库字段信息
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments || !addRemarkComments) {
            return;
        }
        field.addJavaDocLine("/**");
        // 获取列注释
        String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" *   " + remarkLine);
            }
        }
        field.addJavaDocLine(" */");

    }


}
