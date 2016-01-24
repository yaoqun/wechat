package com.wh.wechat.dao.impl;

import com.wh.wechat.dao.CommandDaoTest;
import org.junit.Test;

/**
 * Created by Whiker on 2016/1/23.
 */
public class JdbcCommandDaoTest extends CommandDaoTest {

	public JdbcCommandDaoTest() {
		super(JdbcCommandDao.getInstance());
	}

	@Test
	public void test() {
		super.test();
	}

}
