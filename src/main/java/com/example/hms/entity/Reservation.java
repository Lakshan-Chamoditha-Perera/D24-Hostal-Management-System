package com.example.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation implements SuperEntity {
    @Id
    private String res_id;
    private Date date;
    private String status;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Room room;

}
