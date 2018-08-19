package com.qchery.ltdco.pipeline;

import com.qchery.ltdco.LtdCoConstants;
import com.qchery.ltdco.entity.LtdCo;
import com.qchery.ltdco.mapper.LtdCoMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author Chery
 * @date 2018/8/19 14:40
 */
public class LtdCoPipeline implements Pipeline {

    private SqlSessionFactory sqlSessionFactory;

    public LtdCoPipeline(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            LtdCoMapper ltdCoMapper = sqlSession.getMapper(LtdCoMapper.class);
            List<LtdCo> ltdCos = resultItems.get(LtdCoConstants.LTD_COS);
            if (ltdCos != null) {
                for (LtdCo ltdCo : ltdCos) {
                    ltdCoMapper.insert(ltdCo);
                }
            }
        }
    }
}
