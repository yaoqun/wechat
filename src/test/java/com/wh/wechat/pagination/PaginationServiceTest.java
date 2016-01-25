package com.wh.wechat.pagination;

import com.wh.wechat.Command;
import com.wh.wechat.dao.CommandDaoTest;
import com.wh.wechat.dao.impl.MybatisCommandDao;
import org.junit.Test;

import java.util.List;

/**
 * Created by Whiker on 2016/1/25.
 */
public class PaginationServiceTest extends CommandDaoTest {

	public PaginationServiceTest() {
		super(MybatisCommandDao.getInstance());
	}

	@Test
	public void test() {
		final int CommandNum = 11;

		// 初始化
		Command[] commands = new Command[CommandNum];
		for (int i = 0; i < CommandNum; i++) {
			commands[i] = addRandomCommand();
		}

		// 打印各个页面
		PaginationService service = new PaginationService();
		Page page = null;
		for (int i = 1; (page = service.showPage(i))!=null; i++) {
			System.out.println("------------------------------------------------------------");
			System.out.println(page.getCurrPage() + "/" + page.getTotalPageNum());
			int j = page.getItemStart();
			for (Object item : page.getItemList()) {
				Command command = (Command) item;
				System.out.println(j + "\t" + command.getId() + "\t" + command.getName());
				++j;
			}
			System.out.println("------------------------------------------------------------");
		}

		// 清理
		for (Command command : commands) {
			deleteCommand(command);
		}
	}

}
