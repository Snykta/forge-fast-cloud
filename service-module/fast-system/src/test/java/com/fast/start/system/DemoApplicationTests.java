package com.fast.start.system;

import com.fast.start.mybatis.config.gen.AutoConvertGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.Launcher;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private AutoConvertGenerator autoGeneratorConvert;

	@Test
	void contextLoads() {
		//autoGeneratorConvert.doGeneration("", "sys_user");



	}




}
