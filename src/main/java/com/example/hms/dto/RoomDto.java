package com.example.hms.dto;

import com.example.hms.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private List<ReservationDto> reservationList = new ArrayList<>();

}
