package com.wh.wechat.pagination;

import com.wh.wechat.Command;
import com.wh.wechat.dao.CommandDaoTest;
import com.wh.wechat.dao.impl.MybatisCommandDao;
import com.wh.wechat.pagination.impl.PaginationServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Whiker on 2016/1/25.
 */
public class PaginationServiceTest extends CommandDaoTest {

	private PaginationService mPagination;

	public PaginationServiceTest(PaginationService pagination) {
		super(MybatisCommandDao.getInstance());
		this.mPagination = pagination;
	}

	public void test() {
		final int CommandNum = 11;

		// 初始化
		List<Command> commands = new ArrayList<Command>(CommandNum);
		for (int i = 0; i < CommandNum; i++) {
			commands.add(randCommand());
		}
		MybatisCommandDao dao = MybatisCommandDao.getInstance();
		dao.addCommandList(commands);
		assertEquals(dao.countCommand(), CommandNum);

		// 打印各个页面
		StringBuilder str = new StringBuilder();
		Page page = null;
		for (int i = 1; (page = mPagination.getPage(i))!=null; i++) {
			str.append("------------------------------------------------------------\n");
			str.append(page.getCurrPage() + "/" + page.getTotalPageNum() + "\n");
			int j = page.getItemStart();
			for (Object item : page.getItemList()) {
				Command command = (Command) item;
				str.append(j + "\t" + command.getId() + "\t" + command.getName() + "\n");
				++j;
			}
		}

		// 清理
		dao.deleteCommandList(commands);
		assertEquals(dao.countCommand(), 0);

		System.out.println(str.toString());
	}

}
