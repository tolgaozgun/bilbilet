package edu.bilkent.bilbilet.response;

import edu.bilkent.bilbilet.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RReviewAvg {
    private double avgPunct;
    private double avgClean;
    private double avgCustSer;
}

