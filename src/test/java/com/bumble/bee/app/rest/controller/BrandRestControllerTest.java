package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.models.dao.Brand;
import com.bumble.bee.app.models.dto.BrandDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BrandRestController.class)
class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbstractRestService<Long, BrandDto, Brand> restService;

    @Test
    public void testFindAll() throws Exception {
        List<BrandDto> brandDtoList = new ArrayList<>();
        brandDtoList.add(new BrandDto());
        when(restService.getAll()).thenReturn(brandDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/brands")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void testFindById() throws Exception {
        Long brandId = 1L;
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brandId);
        when(restService.findById(brandId)).thenReturn(brandDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/brands/{id}", brandId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(brandId.intValue())));
    }

    @Test
    public void testCreateEntity() throws Exception {
        BrandDto brandDto = new BrandDto();
        brandDto.setName("Test Brand");
        brandDto.setDescription("Test Description");

        when(restService.create(brandDto)).thenReturn(brandDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(brandDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(brandDto.getName())))
                .andExpect(jsonPath("$.description", is(brandDto.getDescription())));
    }

    @Test
    public void testUpdateEntity() throws Exception {

        Long brandId = 1L;

        BrandDto brandDto = new BrandDto();

        brandDto.setId(brandId);

        brandDto.setName("Test Brand");

        brandDto.setDescription("Test Description");

        when(restService.update(brandId, brandDto)).thenReturn(brandDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(brandDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(brandId.intValue())))
                .andExpect(jsonPath("$.name", is(brandDto.getName())))
                .andExpect(jsonPath("$.description", is(brandDto.getDescription())));
    }



@Test
public void testDeleteEntity() throws Exception {
    Long brandId = 1L;

    mockMvc.perform(MockMvcRequestBuilders.delete("/brands/{id}", brandId)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted());
}

// Utility method to convert object to JSON string
private static String asJsonString(Object obj) {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}