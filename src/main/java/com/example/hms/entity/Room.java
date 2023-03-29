package com.example.hms.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Room implements SuperEntity {
    @OneToMany
    List<Reservation> list = new ArrayList<>();
    @Id
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;
}
