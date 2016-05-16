package com.waes.enums;

/**
 * 使用枚举表述常量数据字典
 * Created by Dell on 2016/5/16.
 */
public enum TaskStateEnum {
    SUCCESS(1, "检测成功"),
    END(0, "检测结束"),
    REPEAT_CHECK(-1, "重复检测"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;
    private String stateInfo;

    TaskStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static TaskStateEnum stateOf(int index) {
        for (TaskStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
