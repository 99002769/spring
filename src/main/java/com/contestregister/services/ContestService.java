package com.contestregister.services;

import java.util.List;

import com.contestregister.commands.ContestForm;
import com.contestregister.model.Contest;


public interface ContestService {

    List<Contest> listAll();

    Contest getById(String id);

    Contest saveOrUpdate(Contest product);

    void delete(String id);

    Contest saveOrUpdateContestForm(ContestForm contestForm);
}
