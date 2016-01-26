package com.wh.wechat.pagination.intercept;

import com.wh.wechat.pagination.Page;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Whiker on 2016/1/26.
 */
@Intercepts( @Signature(
		type   = StatementHandler.class,  // 被拦截的类
		method = "prepare",               // 被拦截类的这个方法
		args   = {Connection.class}       // 被拦截方法的参数
))
public class PaginationInterceptor implements Interceptor {

	private String mOrder;

	/**
	 * 修改sql语句
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	public Object intercept(Invocation invocation) throws Throwable {

		// invocation.getTarget() 获取 plugin(Object target) 的 target
		StatementHandler statHandler = (StatementHandler) invocation.getTarget();

		MetaObject meta = SystemMetaObject.forObject(statHandler);
		MappedStatement mappedStat = (MappedStatement) meta.getValue("delegate.mappedStatement");

		// 配置文件中sql语句标签的id属性
		String id = mappedStat.getId();
		if ( ! id.matches(".+Intercept$") ) {  // id不匹配
			return invocation.proceed();
		}

		// 配置文件中的原始sql语句
		BoundSql boundSql = statHandler.getBoundSql();
		String sql = boundSql.getSql();

		// 新的sql语句
		Page page = (Page) boundSql.getParameterObject();
		String newSql = sql + " order by id " + mOrder +
				" limit " + page.getItemStart() + "," + page.ItemNumOfEachPage;
		meta.setValue("delegate.boundSql.sql", newSql);

		return invocation.proceed();
	}

	/**
	 * @param target 待判断是否要拦截
	 * @return 代理对象或target
	 */
	public Object plugin(Object target) {
		// wrap() 里会根据注解 @Signature 判断target的类型是否是被拦截类
		// 若是, 则包装; 否则直接返回target
		return Plugin.wrap(target, this);
	}

	/**
	 * 从配置文件 Configuration.xml 获取配置信息
	 * @param properties
	 */
	public void setProperties(Properties properties) {
		String order = properties.getProperty("order");
		if (order != null && order.equals("desc")) {
			mOrder = "desc";  // 降序
		}
		else {
			mOrder = "";
		}
	}
}
