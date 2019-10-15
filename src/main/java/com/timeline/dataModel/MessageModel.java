package com.timeline.dataModel;

import java.time.Duration;
import java.time.LocalDateTime;

public class MessageModel {
    private Long messageId;
    private String content;
    private LocalDateTime time;
    private Integer userId;
    private Duration duration;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Duration getDuration() {
        LocalDateTime now = LocalDateTime.now();
        this.duration = Duration.between(now, this.time);
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
