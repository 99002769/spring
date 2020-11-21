package com.contestregister.repositories;

import org.springframework.data.repository.CrudRepository;

import com.contestregister.model.Contest;


public interface ContestRepository extends CrudRepository<Contest, String> {
}
