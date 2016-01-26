package com.wh.wechat.pagination.impl;

import com.wh.wechat.Command;
import com.wh.wechat.dao.impl.MybatisCommandDao;
import com.wh.wechat.pagination.Page;
import com.wh.wechat.pagination.PaginationService;

import java.util.List;

/**
 * Created by Whiker on 2016/1/26.
 */
public class PaginationServiceInterceptor implements PaginationService {

	public Page getPage(int pageIndex) {
		MybatisCommandDao dao = MybatisCommandDao.getInstance();
		int totalCommandNum = dao.countCommand();

		Page page = new Page();
		page.setTotalPageNum(totalCommandNum);
		if (page.getTotalPageNum() < pageIndex) {
			return null;
		}
		page.setCurrPage(pageIndex);

		List<Command> commands = dao.queryCommandListIntercept(page);
		page.setItemList(commands);
		return page;
	}

}
