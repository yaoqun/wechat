package com.wh.wechat.dao.impl;

import com.wh.wechat.Command;

/**
 * Created by Whiker on 2016/1/25.
 */
public interface IMybatisCommand {

	public void addCommand(Command command);

	public Command queryCommand(Command command);

	public void deleteCommand(String commandName);

}
