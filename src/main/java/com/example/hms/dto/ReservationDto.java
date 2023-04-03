package com.example.hms.dto;

import com.example.hms.entity.Room;
import com.example.hms.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationDto implements SuperDto {
    private String res_id;
    private Date date;
    private String status;
    @ToString.Exclude
    private StudentDto studentDto;
    @ToString.Exclude
    private RoomDto roomDto;
}
