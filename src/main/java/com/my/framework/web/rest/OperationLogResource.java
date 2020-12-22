package com.my.framework.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.my.framework.domain.OperationLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OperationLogResource {

    private final Logger log = LoggerFactory.getLogger(OperationLogResource.class);

}
