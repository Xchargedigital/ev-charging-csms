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
public class Reservation extends Base{

    //2024-08-12T12:00:00
    private String to;
}
