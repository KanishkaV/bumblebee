package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.models.dao.Brand;
import com.bumble.bee.app.models.dto.BrandDto;
import com.bumble.bee.app.rest.controller.BrandRestController;
import com.bumble.bee.app.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BrandRestController.class)
class BrandRestControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private IService<Brand, Long> serviceMock;

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

    @Test
    public void testUpdateBrand() {
        // Create the DTO to use in the test
        BrandDto brandDto = new BrandDto();
        brandDto.setId(1L);
        brandDto.setName("Test Brand");
        brandDto.setDescription("Test Description");

        // Create the expected result
        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Test Brand");
        expectedBrand.setDescription("Test Description");

        // Configure the service mock to return the expected result
        when(serviceMock.findById(anyLong())).thenReturn(Optional.of(expectedBrand));
        when(serviceMock.update(any())).thenReturn(expectedBrand);

        // Call the service method being tested
        BrandDto actualBrandDto = brandRestService.update(1L, brandDto);

        // Verify that the service method returned the expected result
        assertEquals(expectedBrand.getId(), actualBrandDto.getId());
        assertEquals(expectedBrand.getName(), actualBrandDto.getName());
        assertEquals(expectedBrand.getDescription(), actualBrandDto.getDescription());

        // Verify that the service method was called with the correct arguments
        verify(serviceMock, times(1)).findById(1L);
        verify(serviceMock, times(1)).update(any(Brand.class));
    }

    @Test
    public void testDeleteBrand() {
        // Call the service method being tested
        brandRestService.delete(1L);

        // Verify that the service method was called with the correct argument
        verify(serviceMock, times(1)).delete(1L);
    }

    @Test
    public void testFindById() {
        // Create a sample entity
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Brand 1");

        // Stub the service method to return the entity
        when(serviceMock.findById(1L)).thenReturn(Optional.<Brand>of(brand));

        // Call the method under test
        BrandDto dto = brandRestService.findById(1L);

        // Assert that the DTO matches the entity
        assertNotNull(dto);
        assertEquals(dto.getId(), brand.getId());
        assertEquals(dto.getName(), brand.getName());

        // Verify that the service method was called with the correct argument
        verify(serviceMock).findById(1L);
    }


}