package com.wh.wechat.pagination.impl;

import com.wh.wechat.pagination.PaginationServiceTest;
import org.junit.Test;

/**
 * Created by Whiker on 2016/1/26.
 */
public class PaginationServiceInterceptorTest extends PaginationServiceTest {

	public PaginationServiceInterceptorTest() {
		super(new PaginationServiceInterceptor());
	}

	@Test
	public void test() {
		super.test();
	}

}
