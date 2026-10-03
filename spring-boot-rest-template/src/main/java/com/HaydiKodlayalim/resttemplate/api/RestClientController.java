package com.HaydiKodlayalim.resttemplate.api;

import com.HaydiKodlayalim.resttemplate.model.KisiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/kisiler")
@RequiredArgsConstructor
public class RestClientController {

    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    @GetMapping
    public ResponseEntity<List<KisiDto>> getAll() {
        ResponseEntity<List<KisiDto>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<KisiDto>>() {}
        );
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping
    public ResponseEntity<KisiDto> kaydet(@RequestBody KisiDto kisiDto) {
        ResponseEntity<KisiDto> response = restTemplate.postForEntity(apiUrl, kisiDto, KisiDto.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restTemplate.delete(apiUrl + "/" + id);
        return ResponseEntity.ok().build();
    }
}
