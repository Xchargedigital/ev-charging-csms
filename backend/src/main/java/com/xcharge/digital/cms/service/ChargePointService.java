package de.rwth.idsg.steve.service;

import de.rwth.idsg.steve.repository.ChargePointRepository;
import de.rwth.idsg.steve.repository.dto.ChargePoint;
import de.rwth.idsg.steve.repository.dto.ConnectorStatus;
import de.rwth.idsg.steve.utils.ConnectorStatusFilter;
import de.rwth.idsg.steve.web.dto.ChargePointForm;
import de.rwth.idsg.steve.web.dto.ConnectorStatusForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChargePointService {
    @Autowired
    protected ChargePointRepository chargePointRepository;

    /**
     * return chargePointPk
     * @param chargePointForm
     * @return
     */
    public int add(ChargePointForm chargePointForm){
        return chargePointRepository.addChargePoint(chargePointForm);
    }

    public ChargePoint.Details findBydId(Integer chargeBoxPk){
        return chargePointRepository.getDetails(chargeBoxPk);
    }

    public ChargePoint.Details getChargeBox(String chargeBoxId){
        return chargePointRepository.getChargeBoxId(chargeBoxId);
    }

    public void update(ChargePointForm chargePointForm){
        chargePointRepository.updateChargePoint(chargePointForm);
    }

    public void delete(int chargeBoxPk){
        chargePointRepository.deleteChargePoint(chargeBoxPk);
    }

    public void delete(String chargeBoxId){
        chargePointRepository.deleteChargePoint(chargeBoxId);
    }

    public List<String> list(){
        return chargePointRepository.getChargeBoxIds();
    }

    public List<Integer> getChargePointConnectorStatus(String chargeBoxId){
        return chargePointRepository.getNonZeroConnectorIds(chargeBoxId);
    }
}
