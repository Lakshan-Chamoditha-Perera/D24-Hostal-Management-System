package com.example.hms.dto;

import com.example.hms.entity.Reservation;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data

@AllArgsConstructor
@NoArgsConstructor
public class RoomDto implements SuperDto {
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;
    @ToString.Exclude
    private  List<Reservation> reservationList = new ArrayList<>();

}
