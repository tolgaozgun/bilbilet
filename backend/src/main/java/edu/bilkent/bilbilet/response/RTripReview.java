package edu.bilkent.bilbilet.response;

import edu.bilkent.bilbilet.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RTripReview {
    private int ticketId;
    private Review review;
}

