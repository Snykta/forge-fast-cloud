package com.fast.start.system;

import cn.hutool.setting.dialect.Props;
import com.fast.start.basic.utils.SpringContextUtil;
import com.fast.start.mybatis.config.gen.AutoConvertGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import sun.misc.Launcher;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

//	@Autowired
//	private AutoConvertGenerator autoGeneratorConvert;

	@Test
	void contextLoads() {
//		autoGeneratorConvert.doGeneration("sys_user");



	}

	public static void main(String[] args) {
//		Environment environment = new Environment() {
//		}
		AutoConvertGenerator autoConvertGenerator = new AutoConvertGenerator();
		autoConvertGenerator.doGeneration("sys_user");
//
//		Props props = new Props("application.properties", "UTF-8");
//
//		System.out.println(props.getStr("spring.datasource.url"));
	}




}
