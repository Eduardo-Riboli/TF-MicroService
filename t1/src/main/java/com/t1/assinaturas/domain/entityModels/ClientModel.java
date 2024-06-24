package com.t1.assinaturas.domain.entityModels;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientModel {
    private long id;
    private String name;
    private String email;
}
