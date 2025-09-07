package de.rwth.idsg.steve.web.api;

import com.neovisionaries.i18n.CountryCode;
import de.rwth.idsg.steve.repository.dto.ConnectorStatus;
import de.rwth.idsg.steve.service.ChargePointService;
import de.rwth.idsg.steve.utils.mapper.ChargePointDetailsMapper;
import de.rwth.idsg.steve.web.dto.Address;
import de.rwth.idsg.steve.web.dto.ChargePointForm;
import de.rwth.idsg.steve.web.dto.request.ChargePoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/chargePoint", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ChargePointController {

    @Autowired
    private ChargePointService chargePointService;
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @PostMapping(value = "")
    @ResponseBody
    public ChargePoint createChargePoint(@RequestBody ChargePoint chargePoint) {
        Map<String, Integer> result = new HashMap<>();
        ChargePointForm chargePointForm = new ChargePointForm();
        chargePointForm.setChargeBoxId(chargePoint.getChargeBoxId());
        chargePointForm.setDescription(chargePoint.getDescription());
        chargePointForm.setNote(chargePoint.getNote());
        chargePointForm.setLocationLatitude(chargePoint.getLatitude());
        chargePointForm.setLocationLongitude(chargePoint.getLongitude());
        chargePointForm.setRegistrationStatus(chargePoint.getRegistrationStatus());
        Address address = new Address();
        address.setCity(chargePoint.getCity());
        address.setCountry(chargePoint.getCountry());
        address.setStreet(chargePoint.getStreet());
        address.setZipCode(chargePoint.getZipCode());
        address.setHouseNumber(chargePoint.getHouseNumber());
        chargePointForm.setAddress(address);
        Integer id = chargePointService.add(chargePointForm);
        return chargePoint;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @PutMapping(value = "")
    public void updateChargePoint(@RequestBody ChargePoint chargePoint) {
        de.rwth.idsg.steve.repository.dto.ChargePoint.Details chargeBox = chargePointService.getChargeBox(chargePoint.getChargeBoxId());
        ChargePointForm chargePointForm = ChargePointDetailsMapper.mapToForm(chargeBox);
        if(chargePointForm == null){
            return;
        }
        String description = chargePoint.getDescription();
        if(description != null){
            chargePointForm.setDescription(description);
        }
        String note = chargePoint.getNote();
        if(description != null)
            chargePointForm.setNote(note);
        BigDecimal latitude = chargePoint.getLatitude();
        if(description != null)
            chargePointForm.setLocationLatitude(latitude);
        BigDecimal longitude = chargePoint.getLongitude();
        if(description != null)
            chargePointForm.setLocationLongitude(longitude);
        Address address = new Address();
        String city = chargePoint.getCity();
        if(description != null)
            address.setCity(city);
        CountryCode country = chargePoint.getCountry();
        if(description != null)
            address.setCountry(country);
        String street = chargePoint.getStreet();
        if(description != null)
            address.setStreet(street);
        String zipCode = chargePoint.getZipCode();
        if(description != null)
            address.setZipCode(zipCode);
        String houseNumber = chargePoint.getHouseNumber();
        if(description != null)
            address.setHouseNumber(houseNumber);
        chargePointForm.setAddress(address);
        chargePointService.update(chargePointForm);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @DeleteMapping(value="/{chargeBoxId}")
    public void deleteChargePoint(@PathVariable("chargeBoxId") String chargeBoxId) {
        chargePointService.delete(chargeBoxId);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @GetMapping(value="{chargeBoxId}")
    public ChargePoint getChargePoint(@PathVariable("chargeBoxId") String chargeBoxId) {
        de.rwth.idsg.steve.repository.dto.ChargePoint.Details chargeBox = chargePointService.getChargeBox(chargeBoxId);
        ChargePointForm chargePointForm = ChargePointDetailsMapper.mapToForm(chargeBox);
        ChargePoint chargePoint =  new ChargePoint();
        chargePoint.setChargeBoxId(chargePointForm.getChargeBoxId());
        chargePoint.setNote(chargePointForm.getNote());
        chargePoint.setDescription(chargePointForm.getDescription());
        chargePoint.setLatitude(chargePointForm.getLocationLatitude());
        chargePoint.setRegistrationStatus(chargePointForm.getRegistrationStatus());
        Address address = chargePointForm.getAddress();
        chargePoint.setCity(address.getCity());
        chargePoint.setCountry(address.getCountry());
        chargePoint.setZipCode(address.getZipCode());
        chargePoint.setHouseNumber(address.getHouseNumber());
        return chargePoint;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiControllerAdvice.ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiControllerAdvice.ApiErrorResponse.class)}
    )
    @GetMapping(value = "")
    public List<String> getAll(){
        return chargePointService.list();
    }

}
