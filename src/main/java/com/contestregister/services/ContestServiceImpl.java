package com.contestregister.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contestregister.commands.ContestForm;
import com.contestregister.converters.ContestFormToContest;
import com.contestregister.model.Contest;
import com.contestregister.repositories.ContestRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContestServiceImpl implements ContestService {

    private ContestRepository contestRepository;
    private ContestFormToContest contestFormToContest;

    @Autowired
    public ContestServiceImpl(ContestRepository contestRepository, ContestFormToContest contestFormToContest) {
        this.contestRepository = contestRepository;
        this.contestFormToContest = contestFormToContest;
    }


    @Override
    public List<Contest> listAll() {
        List<Contest> contests = new ArrayList<>();
        contestRepository.findAll().forEach(contests::add); //fun with Java 8
        return contests;
    }

    @Override
    public Contest getById(String id) {
        return contestRepository.findById(id).orElse(null);
    }

    @Override
    public Contest saveOrUpdate(Contest contest) {
        contestRepository.save(contest);
        return contest;
    }

    @Override
    public void delete(String id) {
        contestRepository.deleteById(id);
    }

    @Override
    public Contest saveOrUpdateContestForm(ContestForm contestForm ) {
        Contest savedProduct = saveOrUpdate(contestFormToContest.convert(contestForm));

        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }
}
