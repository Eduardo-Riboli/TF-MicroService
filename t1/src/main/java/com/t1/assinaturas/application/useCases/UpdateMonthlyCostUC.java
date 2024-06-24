package com.t1.assinaturas.application.useCases;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.t1.assinaturas.application.dto.AppCostUpdateRequestDTO;
import com.t1.assinaturas.application.dto.AppResponseDTO;
import com.t1.assinaturas.domain.entityModels.AppModel;
import com.t1.assinaturas.domain.services.AppService;

@Component
public class UpdateMonthlyCostUC {
    private AppService appService;

    @Autowired
    public UpdateMonthlyCostUC(AppService appService) {
        this.appService = appService;
    }

    public ResponseEntity<AppResponseDTO> run(Long idAplicativo, AppCostUpdateRequestDTO appCostUpdateRequestDTO) {
        BigDecimal novoCusto = appCostUpdateRequestDTO.getNovoCusto();
        Optional<AppModel> appAtualizado = appService.updateCost(idAplicativo, novoCusto);

        AppModel novoApp = appAtualizado.get();

        AppResponseDTO appResponseDTO = new AppResponseDTO(
                novoApp.getId(),
                novoApp.getName(),
                novoApp.getMonthlyCost());
        return ResponseEntity.status(HttpStatus.CREATED).body(appResponseDTO);
    }
}
