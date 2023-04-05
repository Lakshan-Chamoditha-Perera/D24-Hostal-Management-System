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
public class StudentTm {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date dob;
    private String gender;

}
