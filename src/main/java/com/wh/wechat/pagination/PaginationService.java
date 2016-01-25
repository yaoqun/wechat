package com.wh.wechat.pagination;

import com.wh.wechat.Command;
import com.wh.wechat.dao.impl.MybatisCommandDao;

import java.util.List;

/**
 * Created by Whiker on 2016/1/25.
 */
public class PaginationService {

	public Page showPage(int pageIndex) {
		MybatisCommandDao dao = MybatisCommandDao.getInstance();
		int totalCommandNum = dao.countCommand();

		Page page = new Page();
		page.setTotalPageNum(totalCommandNum);
		if (page.getTotalPageNum() < pageIndex) {
			return null;
		}
		page.setCurrPage(pageIndex);

		List<Command> commands = dao.queryCommandList(page);
		page.setItemList(commands);
		return page;
	}

}
