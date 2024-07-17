package com.gfg.shoutreview.service.response;


import com.gfg.shoutreview.domain.Genre;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse implements Serializable {

    private String title;
    private Genre genre;
    private Double rating;
    private List<ReviewResponse> reviews;


}
