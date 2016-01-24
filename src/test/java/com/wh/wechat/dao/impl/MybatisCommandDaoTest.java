package com.wh.wechat.dao.impl;

import com.wh.wechat.dao.CommandDaoTest;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by Whiker on 2016/1/24.
 */
public class MybatisCommandDaoTest extends CommandDaoTest {

	public MybatisCommandDaoTest() {
		super(MybatisCommandDao.getInstance());
	}

	@Test
	public void test() {
		super.test();
	}

}
