package com.example.demoapp.models;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Mail {

    private int id;
    private String item;
    private String address;
    private String sendBy;
    private LocalDate sendDate;
    private String email;
}
