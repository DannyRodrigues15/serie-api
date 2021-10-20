package com.devvision.series.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.devvision.series.app.model.Group;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {

}
