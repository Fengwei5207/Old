package com.oldMan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/9 19:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalAppointment {
    private int medicalAppointmentId;
    private int oldId;
    private String title;
    private LocalDateTime startDateTime;
    private String context;
}
