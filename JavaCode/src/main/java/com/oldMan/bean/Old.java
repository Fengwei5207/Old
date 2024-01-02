package com.oldMan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/2 10:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Old {
    private int elderlyID;
    private String name;
    private String gender;
    private String birthdate;
    private String address;
    private String healthCondition;
    private String medicationInfo;
}
