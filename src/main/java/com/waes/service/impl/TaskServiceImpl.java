package com.waes.service.impl;

import com.waes.dao.SuccessCheckedDao;
import com.waes.dao.TaskDao;
import com.waes.dto.Exposer;
import com.waes.dto.TaskExecution;
import com.waes.entity.SuccessChecked;
import com.waes.entity.Task;
import com.waes.enums.TaskStateEnum;
import com.waes.exception.RepeatCheckException;
import com.waes.exception.TaskCloseException;
import com.waes.exception.TaskException;
import com.waes.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 2016/5/15.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入Service依赖
    @Autowired //@Resource,@Inject
    private TaskDao taskDao;
    @Autowired
    private SuccessCheckedDao successCheckedDao;

    //md5盐值字符串，用于混淆MD5
    private final String slat = "safaregfrhey37]htrqwegtyki67ikkyup0'[sad";

    public List<Task> getTaskList() {
        return taskDao.queryAll(0, 4);
    }

    public Task getById(long taskId) {
        return taskDao.queryById(taskId);
    }

    public Exposer exportTaskUrl(long taskId) {
        Task task = taskDao.queryById(taskId);
        if (task == null) {
            return new Exposer(false, taskId);
        }
        Date startTime = task.getStartTime();
        Date endTime = task.getEndTime();
        Date nowTime = new Date();//系统当前时间
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, taskId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(taskId);//转化特定字符串的过程，不可逆
        return new Exposer(true, md5, taskId);
    }

    private String getMD5(long taskId) {
        String base = taskId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    public TaskExecution executeTask(long taskId, long userPhone, String md5)
            throws TaskException, RepeatCheckException, TaskCloseException {
        if (md5 == null || !md5.equals(getMD5(taskId))) {
            throw new TaskException("task data rewrite");
        }
        //执行检测逻辑：减任务量+记录检测行为
        Date nowTime = new Date();

        try {
            //减任务量
            int updateCount = taskDao.reduceNum(taskId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录，检测结束
                throw new TaskCloseException("task is closed");
            } else {
                //记录检测行为
                int insertCount = successCheckedDao.insertSuccessChecked(taskId, userPhone);
                //唯一：taskId,userPhone
                if (insertCount <= 0) {
                    //重复检测
                    throw new RepeatCheckException("check repeated");
                } else {
                    //检测成功
                    SuccessChecked successChecked = successCheckedDao.queryByIdWithTask(taskId, userPhone);
                    return new TaskExecution(taskId, TaskStateEnum.SUCCESS, successChecked);
                }
            }
        } catch (TaskCloseException e) {
            throw e;
        } catch (RepeatCheckException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常，转化为运行期异常
            //一旦有错，spring会自动回滚
            throw new TaskException("task inner error:" + e.getMessage());
        }
    }
}
