package com.shuyu.service.interceptor;


import com.shuyu.service.constant.MybatisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
/**
 * @package:com.ganinfo.common.interceptor
 * @className:MybatisInterceptor
 * @description:拦截打印完整sql语句
 * @author:Shuyu.Wang
 * @date:2018-11-30 18:13
 * @version:V1.0
**/
@Slf4j
@Intercepts({ @Signature(type = Executor.class, method = MybatisConstant.UPDATE_METHOD_STRING, args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = MybatisConstant.QUERY_METHOD_STRING, args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class MybatisInterceptor implements Interceptor {


	@SuppressWarnings("unused")
	private Properties properties;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		try {
			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			Object parameter = null;
			if (invocation.getArgs().length > 1) {
				parameter = invocation.getArgs()[1];
			}
			String sqlId = mappedStatement.getId();
			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			Configuration configuration = mappedStatement.getConfiguration();
			String sql = getSql(configuration, boundSql, sqlId, 0);
			log.info(">>>>>>>>>>>>>");
			log.info(">>>"+sql);
			log.info(">>>>>>>>>>>>>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invocation.proceed();
	}

	public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
		String sql = showSql(configuration, boundSql);
		StringBuilder str = new StringBuilder(100);
		str.append(sqlId);
		str.append(MybatisConstant.APPEND_STRING);
		str.append(sql);
		return str.toString();
	}

	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = MybatisConstant.SPLIT_STRING + obj.toString() + MybatisConstant.SPLIT_STRING;
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value =MybatisConstant.SPLIT_STRING + formatter.format(new Date()) + MybatisConstant.SPLIT_STRING;
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	/**
	 * @param configuration
	 * @param boundSql
	 * @return
	 */
	public static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (CollectionUtils.isNotEmpty(parameterMappings) && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst(MybatisConstant.REPLACE_FIRST_STRING, Matcher.quoteReplacement(getParameterValue(parameterObject)));

			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst(MybatisConstant.REPLACE_FIRST_STRING, Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst(MybatisConstant.REPLACE_FIRST_STRING, Matcher.quoteReplacement(getParameterValue(obj)));
					} else {
						sql = sql.replaceFirst(MybatisConstant.REPLACE_FIRST_STRING, MybatisConstant.NO_VALUE_STRING);
					} // 打印出缺失，提醒该参数缺失并防止错位
				}
			}
		}
		return sql;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}
}
