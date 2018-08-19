package com.qchery.ltdco;

import com.qchery.ltdco.pipeline.LtdCoPipeline;
import com.qchery.ltdco.processor.LtdcoPageProcessor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import us.codecraft.webmagic.Spider;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Chery
 * @date 2018/8/19 13:26
 */
public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Spider.create(new LtdcoPageProcessor())
                .thread(8)
                .addPipeline(new LtdCoPipeline(sqlSessionFactory))
                .addUrl(LtdCoConstants.CONTEXT_PATH + "/index.html")
                .run();
    }

}
