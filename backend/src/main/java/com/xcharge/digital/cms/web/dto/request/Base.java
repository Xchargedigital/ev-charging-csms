package de.rwth.idsg.steve.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Base {

    private String chargeBoxId;

    private String idTag;

    @Min(value = 1, message = "Connector ID must be at least {value}")
    private Integer connectorId;
}
