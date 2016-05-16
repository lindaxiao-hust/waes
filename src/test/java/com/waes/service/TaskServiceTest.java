package com.waes.service;

import com.waes.dto.Exposer;
import com.waes.dto.TaskExecution;
import com.waes.entity.Task;
import com.waes.exception.RepeatCheckException;
import com.waes.exception.TaskCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dell on 2016/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class TaskServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    @Test
    public void getTaskList() throws Exception {
        List<Task> taskList = taskService.getTaskList();
        logger.info("list={}", taskList);
    }

    @Test
    public void getById() throws Exception {
        long taskId = 1000;
        Task task = taskService.getById(taskId);
        logger.info("task={}", task);
    }

//    @Test
//    public void exportTaskUrl() throws Exception {
//        long taskId = 1000;
//        Exposer exposer = taskService.exportTaskUrl(taskId);
//        logger.info("exposer={}"+exposer);
//    }
//
//    @Test
//    public void executeTask() throws Exception {
//        long taskId = 1000;
//        long userPhone = 13377779900L;
//        String md5 = "1e8cc4ca9ba45f7b7c50505954b786e1";
//        try {
//            TaskExecution taskExecution = taskService.executeTask(taskId,userPhone,md5);
//            logger.info("taskExecution={}", taskExecution);
//        } catch (RepeatCheckException e) {
//            logger.error(e.getMessage());
//        } catch (TaskCloseException e) {
//            logger.error(e.getMessage());
//        }
//    }

    //注意可重复执行
    //注意集成测试业务覆盖的完整性
    @Test
    public void testTaskLogic() throws Exception {
        long taskId = 1002;
        Exposer exposer = taskService.exportTaskUrl(taskId);
        if (exposer.isEsposed()) {
            logger.info("exposer={}"+exposer);
            long userPhone = 13377779900L;
            String md5 = exposer.getMd5();
            try {
                TaskExecution taskExecution = taskService.executeTask(taskId,userPhone,md5);
                logger.info("taskExecution={}", taskExecution);
            } catch (RepeatCheckException e) {
                logger.error(e.getMessage());
            } catch (TaskCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            //检测未开启
            logger.warn("exposer={}"+exposer);
        }
    }

}