package com.oldMan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/3 13:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicNotice {
    private int noticeId;
    private int noticeType;
    private String title;
    private String content;
    private LocalDateTime createTime;
}
