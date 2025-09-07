package de.rwth.idsg.steve.web.api;

import de.rwth.idsg.steve.repository.dto.ConnectorStatus;
import de.rwth.idsg.steve.service.ChargePointService;
import de.rwth.idsg.steve.web.dto.ConnectorStatusForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/connectorStatus", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ConnectorStatusController {
    @Autowired
    private ChargePointService chargePointService;
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @GetMapping(value = "/{chargeBoxId}")
    @ResponseBody
    public Map<String, Object> getChargePointConnectorStatus(@PathVariable("chargeBoxId") String chargeBoxId){
        Map<String, Object> result = new HashMap<>();
        List<Integer> connectIds = chargePointService.getChargePointConnectorStatus(chargeBoxId);
        result.put("connectIds", connectIds);
        return result;
    }
}
