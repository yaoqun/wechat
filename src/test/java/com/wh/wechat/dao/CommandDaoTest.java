package com.wh.wechat.dao;

import com.wh.wechat.Command;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Whiker on 2016/1/23.
 */
public class CommandDaoTest {

	private CommandDao mDao;

	public CommandDaoTest(CommandDao dao) {
		mDao = dao;
	}

	protected void test() {
		Command command = randCommand();
		assertEquals(mDao.addCommand(command), true);
		assertEquals(mDao.addCommand(command), false);

		Command ret = mDao.queryCommand(command.getName());
		assertNotNull(ret);
		assertEquals(ret.getName(), command.getName());
		assertEquals(ret.getComment(), command.getComment());
		assertEquals(ret.getContent(), command.getContent());

		mDao.deleteCommand(command.getName());
		ret = mDao.queryCommand(command.getName());
		assertNull(ret);
	}

	protected Command addRandomCommand() {
		Command command = randCommand();
		mDao.addCommand(command);
		return command;
	}

	protected void deleteCommand(Command command) {
		mDao.deleteCommand(command.getName());
	}

	private Command randCommand() {
		Command command = new Command();
		command.setName(UUID.randomUUID().toString());
		command.setComment("注释");
		command.setContent("内容");
		return command;
	}

}
