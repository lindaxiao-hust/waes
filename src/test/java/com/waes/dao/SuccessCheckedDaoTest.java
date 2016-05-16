package com.waes.dao;

import com.waes.entity.SuccessChecked;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Dell on 2016/5/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessCheckedDaoTest {

    @Resource
    private SuccessCheckedDao successCheckedDao;

    @Test
    public void insertSuccessChecked() throws Exception {
        int insertCount = successCheckedDao.insertSuccessChecked(1000L, 13377889900L);
        System.out.println("insertCount = " + insertCount);
    }

    @Test
    public void queryByIdWithTask() throws Exception {
        SuccessChecked successChecked = successCheckedDao.queryByIdWithTask(1000L, 13377889900L);
        System.out.println(successChecked);
        System.out.println(successChecked.getTask());
    }

}