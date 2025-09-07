package de.rwth.idsg.steve.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/health")
public class HealthCheckController {
    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}
