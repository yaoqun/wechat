package com.wh.wechat.dao.impl;

import com.wh.wechat.Command;
import com.wh.wechat.pagination.Page;

import java.util.List;

/**
 * Created by Whiker on 2016/1/25.
 */
public interface IMybatisCommand {

	void addCommand(Command command);

	Command queryCommand(Command command);

	void deleteCommand(String commandName);

	int countCommand();

	List<Command> queryCommandList(Page page);

	List<Command> queryCommandListIntercept(Page page);

}
