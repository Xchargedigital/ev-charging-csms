package de.rwth.idsg.steve.web.api;

import de.rwth.idsg.steve.SteveException;
import de.rwth.idsg.steve.ocpp.OcppTransport;
import de.rwth.idsg.steve.ocpp.OcppVersion;
import de.rwth.idsg.steve.repository.dto.ChargePointSelect;
import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.service.ChargePointService16_Client;
import de.rwth.idsg.steve.web.dto.ocpp.CancelReservationParams;
import de.rwth.idsg.steve.web.dto.ocpp.ReserveNowParams;
import de.rwth.idsg.steve.web.dto.request.Reservation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ocpp.cs._2015._10.RegistrationStatus;
import org.joda.time.LocalDateTime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/reserve", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReservationRestController {

    private final ChargePointService16_Client chargePointService16Client;
    private final ChargePointHelperService chargePointHelperService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @PostMapping(value = "")
    @ResponseBody
    public Map<String, Object> reserveNow(@RequestBody Reservation reservation) {
        log.info("Read request for query: {}", reservation);
        String chargeBoxId = reservation.getChargeBoxId();
        List<RegistrationStatus> inStatusFilter = Arrays.asList(RegistrationStatus.ACCEPTED, RegistrationStatus.PENDING);
        ChargePointSelect cps = chargePointHelperService.getChargePoint(inStatusFilter, chargeBoxId);
        if(cps == null) {
            throw new SteveException("'%s' is offline", chargeBoxId);
        }
        ReserveNowParams params = new ReserveNowParams();
        List<ChargePointSelect> chargePointSelectList = new LinkedList<>();
        ChargePointSelect chargePointSelect = new ChargePointSelect(OcppTransport.fromValue("J"), reservation.getChargeBoxId());
        chargePointSelectList.add(chargePointSelect);
        params.setChargePointSelectList(chargePointSelectList);
        params.setExpiry(LocalDateTime.parse(reservation.getTo()));
        params.setIdTag(reservation.getIdTag());
        params.setConnectorId(reservation.getConnectorId());
        Integer reservationId = chargePointService16Client.reserveNow(params);
        Map<String, Object> result = new HashMap<>();
        result.put("chargeBoxId", reservation.getChargeBoxId());
        result.put("idTag", reservation.getIdTag());
        result.put("to", reservation.getTo());
        result.put("connectorId", reservation.getConnectorId());
        result.put("reservationId", reservationId);
        log.debug("Read response for query: {}", result);
        return result;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @PostMapping(value = "/cancel")
    @ResponseBody
    public Map<String, Object> cancelReservation(@RequestBody Map<String, String> request) {
        log.info("Read request for query: {}", request);
        CancelReservationParams params = new CancelReservationParams();
        List<ChargePointSelect> chargePointSelectList = new LinkedList<>();
        ChargePointSelect chargePointSelect = new ChargePointSelect(OcppTransport.fromValue("J"), request.get("chargeBoxId"));
        chargePointSelectList.add(chargePointSelect);
        params.setChargePointSelectList(chargePointSelectList);
        params.setReservationId(Integer.parseInt(request.get("reservationId")));
        Integer taskId = chargePointService16Client.cancelReservation(params);
        Map<String, Object> result = new HashMap<>();
        result.put("chargeBoxId", request.get("chargeBoxId"));
        result.put("idTag", request.get("reservationId"));
        result.put("taskId", taskId);
        log.debug("Read response for query: {}", result);
        return result;
    }
}
