package com.waes.service;

import com.waes.dto.Exposer;
import com.waes.dto.TaskExecution;
import com.waes.entity.Task;
import com.waes.exception.RepeatCheckException;
import com.waes.exception.TaskCloseException;
import com.waes.exception.TaskException;

import java.util.List;

/**
 * 业务接口：站在‘使用者’的角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 * Created by Dell on 2016/5/15.
 */
public interface TaskService {

    /**
     * 查询所有检测任务
     * @return
     */
    List<Task> getTaskList();

    /**
     * 查询一条检测任务
     * @param taskId
     * @return
     */
    Task getById(long taskId);

    /**
     * 检测开始时输出检测接口地址，否则输出系统时间和检测时间
     * @param taskId
     */
    Exposer exportTaskUrl(long taskId);

    /**
     * 执行检测操作
     * @param taskId
     * @param userPhone
     * @param md5
     */
    TaskExecution executeTask(long taskId, long userPhone, String md5)
        throws TaskException,RepeatCheckException,TaskCloseException;
}
