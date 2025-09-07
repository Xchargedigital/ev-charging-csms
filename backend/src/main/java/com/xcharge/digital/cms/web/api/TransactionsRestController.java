/*
 * SteVe - SteckdosenVerwaltung - https://github.com/steve-community/steve
 * Copyright (C) 2013-2023 SteVe Community Team
 * All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.rwth.idsg.steve.web.api;

import de.rwth.idsg.steve.SteveException;
import de.rwth.idsg.steve.ocpp.OcppTransport;
import de.rwth.idsg.steve.repository.TransactionRepository;
import de.rwth.idsg.steve.repository.dto.ChargePointSelect;
import de.rwth.idsg.steve.service.ChargePointHelperService;
import de.rwth.idsg.steve.service.ChargePointService16_Client;
import de.rwth.idsg.steve.web.api.exception.BadRequestException;
import de.rwth.idsg.steve.web.dto.TransactionQueryForm;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStartTransactionParams;
import de.rwth.idsg.steve.web.dto.ocpp.RemoteStopTransactionParams;
import de.rwth.idsg.steve.web.dto.request.Base;
import de.rwth.idsg.steve.web.dto.response.MeterValue;
import de.rwth.idsg.steve.web.dto.response.Transaction;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ocpp.cs._2015._10.RegistrationStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author Sevket Goekay <sevketgokay@gmail.com>
 * @since 13.09.2022
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionsRestController {

    private final TransactionRepository transactionRepository;

    private final ChargePointService16_Client chargePointService16Client;

    private final ChargePointHelperService chargePointHelperService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @GetMapping(value = "")
    @ResponseBody
    public List<de.rwth.idsg.steve.repository.dto.Transaction> get(@Valid TransactionQueryForm.ForApi params) {
        log.debug("Read request for query: {}", params);

        if (params.isReturnCSV()) {
            throw new BadRequestException("returnCSV=true is not supported for API calls");
        }

        var response = transactionRepository.getTransactions(params);
        log.debug("Read response for query: {}", response);
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @PostMapping(value = "/start")
    @ResponseBody
    public Transaction startTransaction(@RequestBody Base base){
        String chargeBoxId = base.getChargeBoxId();
        List<RegistrationStatus> inStatusFilter = Arrays.asList(RegistrationStatus.ACCEPTED, RegistrationStatus.PENDING);
        ChargePointSelect cps = chargePointHelperService.getChargePoint(inStatusFilter, chargeBoxId);
        if(cps == null) {
            throw new SteveException("'%s' is offline", chargeBoxId);
        }
        RemoteStartTransactionParams params = new RemoteStartTransactionParams();
        params.setConnectorId(base.getConnectorId());
        params.setIdTag(base.getIdTag());
        List<ChargePointSelect> chargePointSelectList = new LinkedList<>();
        ChargePointSelect chargePointSelect = new ChargePointSelect(OcppTransport.fromValue("J"), base.getChargeBoxId());
        chargePointSelectList.add(chargePointSelect);
        params.setChargePointSelectList(chargePointSelectList);
        Integer taskId = chargePointService16Client.remoteStartTransaction(params);
        Transaction result = new Transaction();
        result.setChargeBoxId(base.getChargeBoxId());
        if(taskId != null){
            result.setMessage("Success");
        }
        return result;
    }

    @PostMapping(value = "/stop")
    @ResponseBody
    public Transaction stopTransaction(@RequestBody Transaction transaction){
        String chargeBoxId = transaction.getChargeBoxId();
        List<Integer> transactionIds = transactionRepository.getActiveTransactionIds(chargeBoxId);
        if(transactionIds == null || transactionIds.size() < 1){
            throw new SteveException("'%s' is not charging", chargeBoxId);
        }
        RemoteStopTransactionParams params = new RemoteStopTransactionParams();
        params.setTransactionId(transactionIds.get(0));
        List<ChargePointSelect> chargePointSelectList = new LinkedList<>();
        ChargePointSelect chargePointSelect = new ChargePointSelect(OcppTransport.fromValue("J"), chargeBoxId);
        chargePointSelectList.add(chargePointSelect);
        params.setChargePointSelectList(chargePointSelectList);
        Integer taskId = chargePointService16Client.remoteStopTransaction(params);
        if(taskId != null){
            transaction.setMessage("Success");
        }
        return transaction;
    }

    @PostMapping(value = "/meter")
    @ResponseBody
    public List<de.rwth.idsg.steve.repository.dto.Transaction> getMeterValue(@RequestBody Base form){
       return transactionRepository.getTransactions(form);
    }
}
