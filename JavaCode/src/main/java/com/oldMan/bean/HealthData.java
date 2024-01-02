package com.oldMan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/2 10:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthData {
    private int healthID;
    private int elderlyID;
    private int heartRate;
    private String bloodPressure;
    private int activityLevel;
    private int sleepQuality;
}
