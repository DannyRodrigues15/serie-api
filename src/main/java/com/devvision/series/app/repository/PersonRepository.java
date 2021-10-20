package com.devvision.series.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.devvision.series.app.model.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}
