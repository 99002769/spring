package com.contestregister.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contestregister.commands.ContestForm;
import com.contestregister.converters.ContestToContestForm;
import com.contestregister.model.Contest;
import com.contestregister.services.ContestService;

import javax.validation.Valid;


@Controller
public class ContestController {
    private ContestService contestService;

    private ContestToContestForm contestToContestForm;

    @Autowired
    public void setProductToProductForm(ContestToContestForm productToProductForm) {
        this.contestToContestForm = productToProductForm;
    }

    @Autowired
    public void setProductService(ContestService productService) {
        this.contestService = productService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/contest/list";
    }

    @RequestMapping({"/contest/list", "/contest"})
    public String listContests(Model model){
        model.addAttribute("contests", contestService.listAll());
        return "contest/list";
    }

    @RequestMapping("/contest/show/{id}")
    public String getContest(@PathVariable String id, Model model){
        model.addAttribute("contest", contestService.getById(id));
        return "contest/show";
    }

    @RequestMapping("contest/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Contest contest = contestService.getById(id);
        ContestForm contestForm = contestToContestForm.convert(contest);

        model.addAttribute("contestForm", contestForm);
        return "contest/contestform";
    }

    @RequestMapping("/contest/new")
    public String newContest(Model model){
        model.addAttribute("contestForm", new ContestForm());
        return "contest/contestform";
    }

    @RequestMapping(value = "/contest", method = RequestMethod.POST)
    public String saveOrUpdateContest(@Valid ContestForm contestForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "contest/contestForm";
        }

        Contest savedContest = contestService.saveOrUpdateContestForm(contestForm);

        return "redirect:/contest/show/" + savedContest.getId();
    }

    @RequestMapping("/contest/delete/{id}")
    public String delete(@PathVariable String id){
    	
        contestService.delete(id);
        return "redirect:/contest/list";
    }
}
