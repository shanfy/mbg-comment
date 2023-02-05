package com.yang.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.kotlin.KotlinFunction;
import org.mybatis.generator.api.dom.kotlin.KotlinProperty;
import org.mybatis.generator.api.dom.kotlin.KotlinType;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * 扩展注释接口抽象类，中间缓冲，便于扩展额外功能
 * @author shanfy
 * @date 2023/2/4 23:25
 */
public abstract class AbstractCommentGenerator implements CommentGenerator {

    /**
     * 配置文件设置的各个属性值
     */
    protected Properties properties;

    /**
     * 是否忽略生成日期
     */
    protected boolean suppressDate;

    /**
     * 是否忽略生成所有的注释
     */
    protected boolean suppressAllComments;

    /**
     * 注释中是否增加数据库表，字段注释部分，如果suppressAllComments为true,此值无意义
     */
    protected boolean addRemarkComments;

    /**
     * 日期格式, 默认 yyyy-MM-dd
     */
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void addConfigurationProperties(Properties properties) {}

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {}

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {}

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {}

    @Override
    public void addModelClassComment(KotlinType modelClass, IntrospectedTable introspectedTable) {}

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {}

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {}

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {}

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {}

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {}

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {}

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {}

    @Override
    public void addComment(XmlElement xmlElement) {}

    @Override
    public void addRootComment(XmlElement rootElement) {}

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {}

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {}

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {}

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {}

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {}

    @Override
    public void addFileComment(KotlinFile kotlinFile) {}

    @Override
    public void addGeneralFunctionComment(KotlinFunction kf, IntrospectedTable introspectedTable, Set<String> imports) {}

    @Override
    public void addGeneralPropertyComment(KotlinProperty property, IntrospectedTable introspectedTable, Set<String> imports) {}

    /**
     * 获取时间字符串
     */
    protected String getDateString() {
        if (this.suppressDate) {
            return null;
        } else if (this.dateFormat != null) {
            return this.dateFormat.format(new Date());
        } else {
            return new Date().toString();
        }
    }
}
