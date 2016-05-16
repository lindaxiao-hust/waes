package com.waes.dto;

import com.waes.entity.SuccessChecked;
import com.waes.enums.TaskStateEnum;

/**
 * 封装检测任务执行后的结果
 * Created by Dell on 2016/5/15.
 */
public class TaskExecution {

    private long taskId;
    private int state;//检测任务状态
    private String stateInfo;//状态表示
    private SuccessChecked successChecked;//检测成功对象

    public TaskExecution(long taskId, TaskStateEnum taskStateEnum, SuccessChecked successChecked) {
        this.taskId = taskId;
        this.state = taskStateEnum.getState();
        this.stateInfo = taskStateEnum.getStateInfo();
        this.successChecked = successChecked;
    }

    public TaskExecution(long taskId, TaskStateEnum taskStateEnum) {
        this.taskId = taskId;
        this.state = taskStateEnum.getState();
        this.stateInfo = taskStateEnum.getStateInfo();
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessChecked getSuccessChecked() {
        return successChecked;
    }

    public void setSuccessChecked(SuccessChecked successChecked) {
        this.successChecked = successChecked;
    }

    @Override
    public String toString() {
        return "TaskExecution{" +
                "taskId=" + taskId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successChecked=" + successChecked +
                '}';
    }
}
