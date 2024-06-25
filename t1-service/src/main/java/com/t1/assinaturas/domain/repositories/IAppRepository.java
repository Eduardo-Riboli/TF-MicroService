package com.t1.assinaturas.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.t1.assinaturas.domain.entityModels.AppModel;

public interface IAppRepository {
    AppModel save(AppModel appModel);

    List<AppModel> findAll();

    Optional<AppModel> findById(Long idAplicativo);
}
