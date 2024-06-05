package com.example.butler.dto;
import lombok.*;


@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCatDto{
    private String name;
    private String des;
}
