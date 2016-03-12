package com.thoughtworks.util;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.constant.Constant;

public class TestDBUtil {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Constant.getParameters();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_001_getConnection() {
		Connection con = DBUtil.getConnection();
		Assert.assertNotNull(con);
	}

}
