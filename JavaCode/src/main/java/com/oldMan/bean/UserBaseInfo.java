package com.oldMan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/4 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseInfo {
    private int userId;
    private String gender;
    private String education;
    private String intro;
}
