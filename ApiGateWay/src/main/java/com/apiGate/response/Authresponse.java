package com.apiGate.response;

import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authresponse {

    private String userId;

    private  String accessToken;

    private String refreshToken;

    private long expireAt;

    private Collection<String> authorities;

}
