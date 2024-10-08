package com.udea.consulta;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class DataController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Faker faker = new Faker(new Locale("en-US"));

    @GetMapping("/")
    public String healthCheck() {
        return "HEALTH CHECK OK!";
    }

    @GetMapping("/version")
    public String version() {
        return "The actual version is 1.0.0";
    }

    @GetMapping("/nations")
    public JsonNode getRandomNations() {
        var nations = objectMapper.createArrayNode();
        for (var i = 0; i < 10; i++) {
            var nation = faker.nation();
            nations.add(objectMapper.createObjectNode()
                    .put("nationality", nation.nationality())
                    .put("capitalCity", nation.capitalCity())
                    .put("flag", nation.flag())  // Cambié "bandera" a "flag" para consistencia en inglés
                    .put("language", nation.language()));
        }
        return nations;
    }

    @GetMapping("/currencies")
    public JsonNode getRandomCurrencies() {
        var currencies = objectMapper.createArrayNode();
        for (var i = 0; i < 20; i++) {
            var currency = faker.currency();
            currencies.add(objectMapper.createObjectNode()
                    .put("name", currency.name())
                    .put("code", currency.code()));
        }
        return currencies;
    }

    @GetMapping("/aviation")
    public JsonNode getRandomAviation() {  // Corregido el nombre del método
        var aviations = objectMapper.createArrayNode();
        for (var i = 0; i < 20; i++) {
            var aviation = faker.aviation();
            aviations.add(objectMapper.createObjectNode()
                    .put("aircraft", aviation.aircraft())
                    .put("airport", aviation.airport())
                    .put("METAR", aviation.METAR()));
        }
        return aviations;
    }
}

