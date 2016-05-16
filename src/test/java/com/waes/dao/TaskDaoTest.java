package com.waes.dao;

import com.waes.entity.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 2016/5/14.
 */

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TaskDaoTest {

    //注入DAO实现类依赖
    @Resource
    private TaskDao taskDao;

    @Test
    public void queryById() throws Exception {
        long taskId = 1000;
        Task task = taskDao.queryById(taskId);
        System.out.println(task);
    }

    @Test
    public void queryAll() throws Exception {
        List<Task> taskList = taskDao.queryAll(0,100);
        for (Task task : taskList){
            System.out.println(task);
        }
    }

    @Test
    public void reduceNum() throws Exception {
        long taskId = 1001;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date checkTime = fmt.parse("2017-1-1 00:00:00");
        int updateCount = taskDao.reduceNum(taskId,checkTime);
        System.out.println("updateCount = " + updateCount);
    }
}