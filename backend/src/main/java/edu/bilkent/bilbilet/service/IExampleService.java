package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.exception.ExampleException;

public interface IExampleService {
    boolean numExists(String num) throws ExampleException;
}
