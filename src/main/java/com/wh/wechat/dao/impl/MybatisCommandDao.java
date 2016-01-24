package com.wh.wechat.dao.impl;

import com.wh.wechat.Command;
import com.wh.wechat.MyLogger;
import com.wh.wechat.dao.CommandDao;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Whiker on 2016/1/24.
 */
public class MybatisCommandDao implements CommandDao {

	public boolean addCommand(Command command) {
		int ret = 0;
		SqlSession sess = null;
		try {
			sess = mSessFactory.openSession();
			ret = sess.insert("Command.addCommand", command);
			sess.commit();
		}
		catch (PersistenceException e) {
			MyLogger.logInfo(e.toString());
		}
		finally {
			if (sess != null) {
				sess.close();
			}
		}
		return ret > 0;
	}

	public Command queryCommand(String name) {
		SqlSession sess = mSessFactory.openSession();
		Command command = new Command();  // 用于传递多个参数
		command.setName(name);
		command = sess.selectOne("Command.queryCommand", command);
		sess.close();
		return command;
	}

	public void deleteCommand(String name) {
		SqlSession sess = mSessFactory.openSession();
		sess.delete("Command.deleteCommand", name);
		sess.commit();
		sess.close();
	}

	private static MybatisCommandDao mybatisCommandDao = new MybatisCommandDao();

	public static MybatisCommandDao getInstance() { return mybatisCommandDao; }

	private MybatisCommandDao() {
		try {
			// 需要把 resources目录 设成Source Root
			Reader reader = Resources.getResourceAsReader("mybatis/Configuration.xml");
			mSessFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		catch (IOException e) {
			MyLogger.logError(e.toString());
		}
	}

	private SqlSessionFactory mSessFactory;

}
