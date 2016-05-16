package com.waes.dao;

import com.waes.entity.SuccessChecked;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Dell on 2016/5/14.
 */
public interface SuccessCheckedDao {

    /**
     * 插入检测明细，可过滤重复
     * @param taskId
     * @param userPhone
     * @return 插入的行数，返回0表示插入失败
     */
    int insertSuccessChecked(@Param("taskId") long taskId, @Param("userPhone") long userPhone);

    /**
     * 根据task id查询SuccessChecked并携带检测任务对象实体
     * @param teskId
     * @return
     */
    SuccessChecked queryByIdWithTask(@Param("taskId") long taskId, @Param("userPhone") long userPhone);
}
