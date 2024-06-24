package com.t1.assinaturas.interfaceAdapter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t1.assinaturas.application.dto.AppCostUpdateRequestDTO;
import com.t1.assinaturas.application.dto.AppResponseDTO;
import com.t1.assinaturas.application.useCases.FindAllAppUC;
import com.t1.assinaturas.application.useCases.UpdateMonthlyCostUC;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/servcad")
@RequiredArgsConstructor
public class AppController {

    @Autowired
    private FindAllAppUC appUC;
    @Autowired
    private UpdateMonthlyCostUC updateUC;


    @GetMapping("/aplicativos")
    public ResponseEntity<List<AppResponseDTO>> getAllApps() {
        return appUC.run();
    }

    @PostMapping("/aplicativos/atualizacusto/{idAplicativo}")
    public ResponseEntity<AppResponseDTO> updateMonthlyCostApp(@PathVariable Long idAplicativo,
            @RequestBody AppCostUpdateRequestDTO appCostUpdateRequestDTO) {
        return updateUC.run(idAplicativo, appCostUpdateRequestDTO);
    }
}
