package com.t1.assinaturas.domain.entityModels;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class UserLoginModel {
    private long id;
    private String username;
    private String password;
}
