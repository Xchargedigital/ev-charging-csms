package de.rwth.idsg.steve.web.dto.request;

import com.neovisionaries.i18n.CountryCode;
import de.rwth.idsg.steve.web.validation.ChargeBoxId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChargePoint {

    @NotBlank(message = "ChargeBox ID is required")
    @ChargeBoxId
    private String chargeBoxId;

    @NotBlank(message = "Registration status is required")
    private String registrationStatus = "Accepted";

    @Range(min = -90, max = 90, message = "Latitude must be between {min} and {max}")
    private BigDecimal latitude;

    @Range(min = -180, max = 180, message = "Longitude must be between {min} and {max}")
    private BigDecimal longitude;

    private String description;
    private String note;

    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private CountryCode country = CountryCode.US;
}
