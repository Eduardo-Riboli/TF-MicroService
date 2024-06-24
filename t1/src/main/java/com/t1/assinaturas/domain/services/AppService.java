package com.t1.assinaturas.domain.services;

import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t1.assinaturas.domain.entityModels.AppModel;
import com.t1.assinaturas.domain.repositories.IAppRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppService {
    private IAppRepository appRepository;
    
    @Autowired
    public AppService(IAppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public List<AppModel> findAll() {
        return appRepository.findAll();
    }

    public AppModel registerApp(AppModel appModel) {
        return appRepository.save(appModel);
    }

    public Optional<AppModel> updateCost(Long idAplicativo, BigDecimal newCost) {
        Optional<AppModel> optionalApp = appRepository.findById(idAplicativo);
        if (optionalApp.isPresent()) {
            AppModel app = optionalApp.get();
            app.setMonthlyCost(newCost);
            return Optional.of(appRepository.save(app));
        }
        return Optional.empty();
    }

    public Optional<AppModel> editApp(Long idAplicativo, AppModel appDetails) {
        Optional<AppModel> optionalApp = appRepository.findById(idAplicativo);
        if (optionalApp.isPresent()) {
            AppModel app = optionalApp.get();
            app.setName(appDetails.getName());
            app.setMonthlyCost(appDetails.getMonthlyCost());
            return Optional.of(appRepository.save(app));
        }
        return Optional.empty();
    }
}
