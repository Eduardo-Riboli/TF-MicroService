package com.t1.assinaturas.interfaceAdapter.persistency.JPARepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.t1.assinaturas.domain.entityModels.AppModel;
import com.t1.assinaturas.domain.repositories.IAppRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository.AppITFRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.App;

@Repository
public class AppJPARepository implements IAppRepository {

    private AppITFRepository app;

    @Autowired
    public AppJPARepository(AppITFRepository app) {
        this.app = app;
    }

    @Override
    public AppModel save(AppModel appModel) {
        App newApp = App.fromAppModel(appModel);
        app.save(newApp);

        return appModel;
    }

    @Override
    public List<AppModel> findAll() {
        List<App> apps = app.findAll();

        return apps.stream()
                .map(it -> App.toAppModel(it))
                .toList();
    }

    @Override
    public Optional<AppModel> findById(Long idAplicativo) {
        Optional<App> newApp = app.findById(idAplicativo);

        if (newApp.isPresent()) {
            return Optional.of(App.toAppModel(newApp.get()));
        } else {
            return Optional.empty();
        }
    }
}
