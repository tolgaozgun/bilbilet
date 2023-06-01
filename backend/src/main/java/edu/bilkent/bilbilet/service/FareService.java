package edu.bilkent.bilbilet.service;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.model.Fare;
import edu.bilkent.bilbilet.repository.FareRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FareService {
    private final FareRepository fareRepository;

    // public Fare getFare(int fareId) {

    // }
}
