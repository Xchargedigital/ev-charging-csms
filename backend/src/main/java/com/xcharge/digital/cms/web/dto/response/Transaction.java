package de.rwth.idsg.steve.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transaction {
    private String chargeBoxId;

    private String message;
}
