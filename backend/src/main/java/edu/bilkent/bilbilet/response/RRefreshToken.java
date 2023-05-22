package edu.bilkent.bilbilet.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RRefreshToken {
    private String accessToken;
}
