package com.example.hms.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTm {
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;

}
