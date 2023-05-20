package edu.bilkent.bilbilet.service;

import org.springframework.stereotype.Service;
import edu.bilkent.bilbilet.exception.ExampleException;
import edu.bilkent.bilbilet.repository.ExampleRepository;
import edu.bilkent.bilbilet.utils.StringUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExampleService implements IExampleService {
    private final ExampleRepository exampleRepository; // Must be an interface if using JPA

    @Override
    public boolean numExists(String num) throws ExampleException {
        if (StringUtil.isNumeric(num)) {
            int i = Integer.parseInt(num);
            return exampleRepository.contains(i);
        }

        throw new ExampleException("Input is not a number!");
    }
}
