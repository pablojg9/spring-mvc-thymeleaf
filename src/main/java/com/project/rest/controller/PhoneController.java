package com.project.rest.controller;

import com.project.rest.model.Account;
import com.project.rest.model.Phone;
import com.project.rest.repository.AccountRepository;
import com.project.rest.repository.PhoneRepository;
import com.project.rest.service.AccountService;
import com.project.rest.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping(value = "/phoneAccount/{idAccount}")
    public ModelAndView phoneAccount(@PathVariable("idAccount") Long idAccount) {

        Optional<Account> account = accountService.findById(idAccount);

        ModelAndView modelAndView = new ModelAndView("cadastro/phones");
        modelAndView.addObject("phones", phoneRepository.getPhones(idAccount));
        modelAndView.addObject("accountobj", account.get());

        return modelAndView;
    }

    @PostMapping("**/addPhone/{idAccountPhone}")
    public ModelAndView addPhoneAccount(Phone phoneNumber, @PathVariable("idAccountPhone") Long idAccountPhone) {

        ModelAndView modelAndView = new ModelAndView("cadastro/phones");

        Account account = accountService.findById(idAccountPhone).get();
        phoneNumber.setAccount(account);

        phoneService.save(phoneNumber);

        modelAndView.addObject("phones", phoneRepository.getPhones(idAccountPhone));

        modelAndView.addObject("accountobj", account);
        modelAndView.addObject("accountobj", new Account());

        return modelAndView;
    }

    @GetMapping(value = "**/editPhone/{idPhone}")
    public ModelAndView editPhone(@PathVariable("idPhone") Long idPhone) {
        Optional<Phone> phone = phoneService.findById(idPhone);

        ModelAndView modelAndView = new ModelAndView("cadastro/phones");

        modelAndView.addObject("accountobj", phone.get());

        return modelAndView;
    }

    @GetMapping(value = "**/phoneDelete/{idPhone}")
    public ModelAndView phoneDelete(@PathVariable("idPhone") Long idPhone) {
        ModelAndView modelAndView = new ModelAndView("cadastro/phones");

        Account account = phoneService.findById(idPhone).get().getAccount();

        phoneRepository.deleteById(idPhone);

        modelAndView.addObject("accountobj", account);
        modelAndView.addObject("phones", phoneRepository.getPhones(account.getId()));

        return modelAndView;
    }
}
