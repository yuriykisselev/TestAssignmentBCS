package com.bcs.restendpoint.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

@Api
@RequestMapping("/api/${spring.application.name}")
abstract class BaseController {

}
