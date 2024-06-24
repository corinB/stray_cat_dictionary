package com.example.butler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @JsonProperty("user_idx")
    private Long userIdx;
    private String title;
    private String content;

    @JsonProperty("post_tag_list")
    @Builder.Default
    private List<String> postTagList = new ArrayList<>();

    @JsonProperty("post_img_list")
    @Builder.Default
    private List<String> postImgList = new ArrayList<>();

    @JsonProperty("like_count")
    @Builder.Default
    private int likeCount = 0;

    private String latitude;
    private String longitude;


}
