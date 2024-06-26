package com.example.butler.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {
    private String name;
    private String des;
    private Long userId;
    @Builder.Default
    private List<String> catImgList = new ArrayList<>();
}
