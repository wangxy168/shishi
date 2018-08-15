package org.rising.dashboard.generate;

import org.rising.dashboard.generate.service.IGenerate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class MyMain {
    public static void main(String[] args) {

        ApplicationContext app = new ClassPathXmlApplicationContext("classpath*:application-*.xml");
        Map<String, IGenerate> map = app.getBeansOfType(IGenerate.class);
        for (Map.Entry<String, IGenerate> entry : map.entrySet()) {
            entry.getValue().start();
        }

    }
}
