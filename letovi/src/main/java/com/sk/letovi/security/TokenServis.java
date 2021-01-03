package com.sk.letovi.security;

import io.jsonwebtoken.Claims;

public interface TokenServis {

    String generate(Claims claims);

    Claims parseToken(String jwt);
}
