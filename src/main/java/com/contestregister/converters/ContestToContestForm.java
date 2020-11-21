package com.contestregister.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.contestregister.commands.ContestForm;
import com.contestregister.model.Contest;


@Component
public class ContestToContestForm implements Converter<Contest, ContestForm> {
    @Override
    public ContestForm convert(Contest contest) {
        ContestForm contestForm = new ContestForm();
        contestForm.setId(contest.getId().toHexString());
        contestForm.setName(contest.getName());
        contestForm.setContestName(contest.getContestName());
        contestForm.setTeamName(contest.getTeamName());
        contestForm.setTeamSize(contest.getTeamSize());
        return contestForm;
    }
}
