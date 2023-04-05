package com.example.hms.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTm {
    private String res_id;
    private Date date;
    private String status;
    private String student_id;
    private String room_id;
}
