package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.models.dto.BrandDto;
import com.bumble.bee.app.rest.controller.BrandRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BrandRestController.class)
class BrandRestControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BrandRestService brandRestService;

    @ParameterizedTest
    @MethodSource("bradCreateArguments")
    public void testBrandCreate(BrandDto dto, String jsonResponse) throws Exception {
        when(brandRestService.create(any(BrandDto.class))).thenReturn(dto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/brands")
                                                              .content(objectMapper.writeValueAsString(dto))
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .accept(MediaType.APPLICATION_JSON);
        var mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult.getResponse());
        assertEquals(200, mvcResult.getResponse().getStatus());

        var contentAsString = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(jsonResponse, contentAsString, false);

        var response = this.objectMapper.readValue(contentAsString, BrandDto.class);
        assertNotNull(contentAsString);
        assertEquals(dto.getName(), response.getName());
    }

    private static Stream<Arguments> bradCreateArguments() {
        return Stream.of(
                Arguments.of(
                        BrandDto.builder().name("Brand 1").description("brand 1 description").build(),
                        "{\"name\":\"Brand 1\",\"description\":\"brand 1 description\"}"
                ),
                Arguments.of(
                        BrandDto.builder().name("Brand 2").description("brand 2 description").build(),
                        "{\"name\":\"Brand 2\",\"description\":\"brand 2 description\"}"
                ),
                Arguments.of(
                        BrandDto.builder().name("Brand 3").description("brand 3 description").build(),
                        "{\"name\":\"Brand 3\",\"description\":\"brand 3 description\"}"
                )
        );

    }
}