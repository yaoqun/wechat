package com.wh.wechat.dao.impl;

import com.wh.wechat.Command;
import com.wh.wechat.MyLogger;
import com.wh.wechat.dao.CommandDao;
import com.wh.wechat.pagination.Page;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Whiker on 2016/1/24.
 */
public class MybatisCommandDao implements CommandDao {

	public boolean addCommand(Command command) {
		SqlSession sess = null;
		try {
			sess = mSessFactory.openSession();
			// sess.insert("com.wh.wechat.dao.impl.IMybatisCommand.addCommand", command);
			IMybatisCommand icommand = sess.getMapper(IMybatisCommand.class);
			icommand.addCommand(command);
			sess.commit();
			return true;
		}
		catch (PersistenceException e) {
			MyLogger.logInfo(e.toString());
			return false;
		}
		finally {
			if (sess != null) {
				sess.close();
			}
		}
	}

	public Command queryCommand(String name) {
		SqlSession sess = mSessFactory.openSession();
		Command command = new Command();  // 用于传递多个参数
		command.setName(name);

		// command = sess.selectOne("com.wh.wechat.dao.impl.IMybatisCommand.queryCommand", command);
		IMybatisCommand dao = sess.getMapper(IMybatisCommand.class);
		command = dao.queryCommand(command);

		sess.close();
		return command;
	}

	public void deleteCommand(String name) {
		SqlSession sess = mSessFactory.openSession();

		// sess.delete("com.wh.wechat.dao.impl.IMybatisCommand.deleteCommand", name);
		IMybatisCommand dao = sess.getMapper(IMybatisCommand.class);
		dao.deleteCommand(name);

		sess.commit();
		sess.close();
	}

	public int countCommand() {
		SqlSession sess = mSessFactory.openSession();
		return sess.getMapper(IMybatisCommand.class).countCommand();
	}

	public List<Command> queryCommandList(Page page) {
		SqlSession sess = mSessFactory.openSession();
		return sess.getMapper(IMybatisCommand.class).queryCommandList(page);
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
