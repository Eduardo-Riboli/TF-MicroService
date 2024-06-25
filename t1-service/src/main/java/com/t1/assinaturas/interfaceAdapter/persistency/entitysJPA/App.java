package com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA;

import java.math.BigDecimal;

import com.t1.assinaturas.domain.entityModels.AppModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal monthlyCost;

    public App() {}
    
    public App(long id, String name, BigDecimal monthlyCost) {
        this.id = id;
        this.name = name;
        this.monthlyCost = monthlyCost;
    }

    public static App fromAppModel(AppModel appModel) {
        return new App(appModel.getId(), appModel.getName(), appModel.getMonthlyCost());
    }

    public static AppModel toAppModel(App app) {
        return new AppModel(app.getId(), app.getName(), app.getMonthlyCost());
    }
}
