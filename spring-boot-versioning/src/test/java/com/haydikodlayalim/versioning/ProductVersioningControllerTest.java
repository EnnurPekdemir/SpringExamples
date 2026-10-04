package com.haydikodlayalim.versioning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductVersioningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUriPathVersioningV1() throws Exception {
        mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").doesNotExist());
    }

    @Test
    void testUriPathVersioningV2() throws Exception {
        mockMvc.perform(get("/api/v2/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(25000.00))
                .andExpect(jsonPath("$.currency").value("TRY"));
    }

    @Test
    void testParamVersioningV1() throws Exception {
        mockMvc.perform(get("/api/param/product").param("apiVersion", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.price").doesNotExist());
    }

    @Test
    void testParamVersioningV2() throws Exception {
        mockMvc.perform(get("/api/param/product").param("apiVersion", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.price").value(1250.00));
    }

    @Test
    void testHeaderVersioningV1() throws Exception {
        mockMvc.perform(get("/api/header/product").header("X-API-VERSION", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mouse"))
                .andExpect(jsonPath("$.price").doesNotExist());
    }

    @Test
    void testHeaderVersioningV2() throws Exception {
        mockMvc.perform(get("/api/header/product").header("X-API-VERSION", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mouse"))
                .andExpect(jsonPath("$.price").value(750.00));
    }

    @Test
    void testMediaTypeVersioningV1() throws Exception {
        mockMvc.perform(get("/api/media-type/product").accept(MediaType.valueOf("application/vnd.company.app-v1+json")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Monitor"))
                .andExpect(jsonPath("$.price").doesNotExist());
    }

    @Test
    void testMediaTypeVersioningV2() throws Exception {
        mockMvc.perform(get("/api/media-type/product").accept(MediaType.valueOf("application/vnd.company.app-v2+json")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Monitor"))
                .andExpect(jsonPath("$.price").value(4500.00));
    }
}
