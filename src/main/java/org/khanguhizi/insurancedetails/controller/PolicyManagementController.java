package org.khanguhizi.insurancedetails.controller;

import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
public class PolicyManagementController {
    @Autowired
    private PolicyManagementService policyManagementService;


}
