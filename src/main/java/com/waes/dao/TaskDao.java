package com.waes.dao;

import java.util.Date;
import com.waes.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Dell on 2016/5/14.
 */
public interface TaskDao {

    /**
     * 减检测任务
     * @param taskId
     * @param checkTime
     * @return 如果影响行数>1, 表示更新的记录条数，返回0表示更新失败
     */
    int reduceNum(@Param("taskId") long taskId, @Param("checkTime") Date checkTime);

    /**
     * 根据任务id查询检测任务对象
     * @param taskId
     * @return
     */
    Task queryById(long taskId);

    /**
     * 根据偏移量查询检测任务列表
     * @param offset
     * queryAll(int offset, int limit) -> queryAll(arg0, arg1)
     * java无法记住形参的名称，所以mapper无法识别offset，这里用mybatis自带的@param解决
     * 其他多参情况皆采用此方法解决
     * @param limit 同offset
     * @return
     */
    List<Task> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
