package com.gfg.shoutreview.service;

import com.gfg.shoutreview.domain.Movie;
import com.gfg.shoutreview.domain.Review;
import com.gfg.shoutreview.repository.MovieRepository;
import com.gfg.shoutreview.repository.ReviewRepository;
import com.gfg.shoutreview.service.response.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    public void addReview(Review review) {
        Movie movie=movieRepository.findById(review.getMovie().getId()).orElse(null);
        reviewRepository.save(review);
    }

    @Scheduled(fixedRate = 20 * 1000) // 10 sec in milliseconds
    private void updateAvgOfMovie() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);

        List<Review> reviewList = reviewRepository.findByUpdatedToday(formattedDate);

        reviewList.stream().forEach(review -> {
            Movie movie = review.getMovie();
            // dheeraj: update the average review after each 4 hour, not every time this function call
            Double average = reviewRepository.getReviewAverage(movie.getId());
            movie.setRating(average);
            movieRepository.save(movie);
        });
    }

    public ReviewResponse getReviewById(Long reviewId) {

        Optional<Review> review= reviewRepository.findById(reviewId);
        return review.map(Review::toReviewResponse).orElse(null);

    }
}
