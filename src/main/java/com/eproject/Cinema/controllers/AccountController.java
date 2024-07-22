package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController {
      @Autowired
      AccountService _accountService;

      @Autowired
      HttpResponse _httpResponse;
}
