package edu.bilkent.bilbilet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("example_repo")
@Repository
public class ExampleRepository {
    private static List<Integer> DB = new ArrayList<>();

    public boolean contains(int num) {
        return DB.contains(num);
    }
}
