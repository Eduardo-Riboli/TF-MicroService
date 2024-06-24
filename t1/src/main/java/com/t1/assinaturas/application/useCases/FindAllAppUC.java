package com.t1.assinaturas.application.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.t1.assinaturas.application.dto.AppResponseDTO;
import com.t1.assinaturas.domain.entityModels.AppModel;
import com.t1.assinaturas.domain.services.AppService;

@Component
public class FindAllAppUC {
    private AppService appService;

    @Autowired
    public FindAllAppUC(AppService appService) {
        this.appService = appService;
    }

    public ResponseEntity<List<AppResponseDTO>> run() {
        List<AppModel> listSubscriptions = appService.findAll();
        List<AppResponseDTO> listDTO = listSubscriptions.stream()
                .map(app -> new AppResponseDTO(app.getId(), app.getName(), app.getMonthlyCost()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }
}
