package com.rosoa0475.testingstudy.springmvc;

import com.rosoa0475.testingstudy.TestingStudyApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestingStudyApplication.class);
	}

}
