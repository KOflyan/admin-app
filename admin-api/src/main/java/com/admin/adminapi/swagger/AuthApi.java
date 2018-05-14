package com.admin.adminapi.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Authentication API specification for Swagger documentation and Code Generation.
 * Implemented by Spring Security.
 */
@Api("Authentication")
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuthApi {
    /**
     * Implemented by Spring Security
     */
    @ApiOperation(value = "Login", notes = "Login with the given credentials.")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = Authentication.class)})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    default void login(@RequestParam("username") String username,
                       @RequestParam("password") String password) {}
}