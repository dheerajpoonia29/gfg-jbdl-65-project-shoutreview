package com.gfg.shoutreview.service.response;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ReviewResponse implements Serializable {

    private String review;
    private Double rating;

}
