package com.oldMan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/2 10:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userID;
    private String userName;
    private String password;
    private String phoneNumber;
    private String userRole;
}
