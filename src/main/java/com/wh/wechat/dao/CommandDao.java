package com.wh.wechat.dao;

import com.wh.wechat.Command;

/**
 * Created by Whiker on 2016/1/23.
 */
public interface CommandDao {

	public boolean addCommand(Command command);

	public Command queryCommand(String name);

	public void deleteCommand(String name);

}
