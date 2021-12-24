package com.learn.meme.controller;

import com.learn.meme.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping("/test")
    double testProcedure(){
        return invoiceRepository.do_bonusSalary_Month(1,0.1d);
    }

    @GetMapping("/troidu")
    int troidu(){
        return invoiceRepository.troidu();
    }
}
