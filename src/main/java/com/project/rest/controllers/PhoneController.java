package com.project.rest.controllers;

import com.project.rest.entities.Account;
import com.project.rest.entities.Phone;
import com.project.rest.repositories.PhoneRepository;
import com.project.rest.services.AccountService;
import com.project.rest.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        Account account = accountService.findById(idAccountPhone).get();

        if (phoneNumber != null && (phoneNumber.getPhoneNumber() != null && phoneNumber.getPhoneNumber().isEmpty())
                || Objects.requireNonNull(phoneNumber).getPhoneNumber() == null) {

            ModelAndView modelAndView = new ModelAndView("cadastro/phones");

            modelAndView.addObject("accountobj", account);
            modelAndView.addObject("phones", phoneRepository.getPhones(idAccountPhone));

            List<String> msg = new ArrayList<>();
            msg.add("Número deve ser informado!");
            modelAndView.addObject("message", msg);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("cadastro/phones");

        phoneNumber.setAccount(account);

        phoneService.save(phoneNumber);

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
