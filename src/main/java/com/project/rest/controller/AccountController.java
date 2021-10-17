package com.project.rest.controller;

import com.project.rest.model.Account;
import com.project.rest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastro")
    public String viewHandler(){
        return "cadastro/cadastropessoa";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public String save(Account account){
        accountRepository.save(account);
        return "cadastro/cadastropessoa";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/listSave")
    public String saveList(Account account) {
        accountRepository.save(account);
        return "list/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/listaAccounts")
    public ModelAndView accounts(){
        ModelAndView andView = new ModelAndView("list/list");

        Iterable<Account> accountIterable = accountRepository.findAll();

        andView.addObject("accounts", accountIterable);
        return andView;
    }
}
