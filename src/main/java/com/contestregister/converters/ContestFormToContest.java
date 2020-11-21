package com.contestregister.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.contestregister.commands.ContestForm;
import com.contestregister.model.Contest;


@Component
public class ContestFormToContest implements Converter<ContestForm, Contest> {

    @Override
    public Contest convert(ContestForm contestForm) {
        Contest contest = new Contest();
        if (contestForm.getId() != null  && !StringUtils.isEmpty(contestForm.getId())) {
            contest.setId(new ObjectId(contestForm.getId()));
        }
        contest.setName(contestForm.getName());
        contest.setContestName(contestForm.getContestName());
        contest.setTeamName(contestForm.getTeamName());
        contest.setTeamSize(contestForm.getTeamSize());
        return contest;
    }
}
