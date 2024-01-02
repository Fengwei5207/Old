package com.oldMan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/2 10:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private int announcementID;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}

