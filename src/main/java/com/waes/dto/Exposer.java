package com.waes.dto;

/**
 * 暴露检测地址DTO
 * Created by Dell on 2016/5/15.
 */
public class Exposer {

    private boolean esposed;//是否开始检测
    private String md5;//一种加密措施
    private long taskId;
    private long now;//系统当前时间（毫秒）
    private long start;//检测开始时间
    private long end;//检测结束时间

    public Exposer(boolean esposed, String md5, long taskId) {
        this.esposed = esposed;
        this.md5 = md5;
        this.taskId = taskId;
    }

    public Exposer(boolean esposed, long taskId, long now, long start, long end) {
        this.esposed = esposed;
        this.taskId = taskId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean esposed, long taskId) {
        this.esposed = esposed;
        this.taskId = taskId;
    }

    public boolean isEsposed() {
        return esposed;
    }

    public void setEsposed(boolean esposed) {
        this.esposed = esposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "esposed=" + esposed +
                ", md5='" + md5 + '\'' +
                ", taskId=" + taskId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
