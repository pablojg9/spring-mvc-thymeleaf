package com.project.rest.controller;

import com.project.rest.model.Account;
import com.project.rest.repository.AccountRepository;
import com.project.rest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView viewHandler(){

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Account> accountIterable = accountService.findAll();

        modelAndView.addObject("accounts", accountIterable);
        modelAndView.addObject("accountobj", new Account());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ModelAndView save(Account account){
        accountService.save(account);

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");

        Iterable<Account> accountIterable = accountService.findAll();
        andView.addObject("accounts", accountIterable);
        andView.addObject("accountobj", new Account());

        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listAccounts")
    public ModelAndView accounts() {
        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");

        Iterable<Account> accountIterable = accountService.findAll();

        andView.addObject("accounts", accountIterable);
        andView.addObject("accountobj", new Account());
        return andView;
    }

    @GetMapping(value = "/editAccount/{idAccount}")
    public ModelAndView accountEdit(@PathVariable("idAccount") Long idAccount) {

        Optional<Account> account = accountService.findById(idAccount);

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("accountobj", account.get());

        return modelAndView;
    }

    @GetMapping(value = "/removeAccount/{idAccount}")
    public ModelAndView accountDelete(@PathVariable("idAccount") Long idAccount) {

        accountService.deleteById(idAccount);

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("accounts", accountService.findAll());
        modelAndView.addObject("accountobj", new Account());

        return modelAndView;
    }

    @PostMapping("**/searchAccount")
    public ModelAndView search(@RequestParam("nameAccount") String nameAccount) {
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");

        modelAndView.addObject("accounts", accountRepository.findAccountByUsername(nameAccount));
        modelAndView.addObject("accountobj", new Account());

        return modelAndView;
    }
}
