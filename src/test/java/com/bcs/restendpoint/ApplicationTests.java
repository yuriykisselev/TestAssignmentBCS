package com.bcs.restendpoint;

import com.bcs.restendpoint.controllers.CalculatorController;
import com.bcs.restendpoint.model.Allocation;
import com.bcs.restendpoint.model.InputData;
import com.bcs.restendpoint.model.OutputData;
import com.bcs.restendpoint.model.Stock;
import com.bcs.restendpoint.service.CalculatorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplicationTests {
    @Autowired
    private CalculatorController controller;

    @Autowired
    private CalculatorServiceImpl calculatorImpl;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getRequestNoBodyShouldReturn4xxStatusCode() throws Exception {
        this.mockMvc.perform(get("/calculate")).andExpect(status().is4xxClientError());
    }

    @Test
    public void postRequestNoBodyShouldReturn4xxStatusCode() throws Exception {
        this.mockMvc.perform(post("/calculate")).andExpect(status().is4xxClientError());
    }

    @Test
    public void getRequestEmptyBodyShouldReturn4xxStatusCode() throws Exception {
        this.mockMvc.perform(get("/calculate").content("{}")).andExpect(status().is4xxClientError());
    }

    @Test
    public void postRequestEmptyBodyShouldReturn4xxStatusCode() throws Exception {
        this.mockMvc.perform(post("/calculate").content("{}")).andExpect(status().is4xxClientError());
    }

    @Test
    public void postRequestShouldReturn200StatusCode() throws Exception {
        // жирный тест чтобы не делать миллион запросов
        // проверяет статус 200, не пустые значения, тип возвращаемого объекта,
        // суммарную стоимость портфеля, количество секторов, значения в секторах, сумму пропорции (~1)
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/input.json")));
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        InputData temp = calculatorImpl.getTemp();
        OutputData output = calculatorImpl.getOutput();
        assertThat(output)
                .isNotNull()
                .isExactlyInstanceOf(OutputData.class);
        assertThat(output.getValue()).isNotNull();
        assertThat(output.getAllocations()).isNotNull();
        assertEquals(temp.getValue(), output.getValue());
        assertEquals(calculatorImpl.calculateData(temp), output);
        double proportionSum = calculatorImpl.getOutput().getAllocations().stream().mapToDouble(Allocation::getProportion).sum();
        assertTrue((Math.abs(1-proportionSum) <= 0.01));
        Set<String> uniqSectors = new HashSet<>();
        for (Stock stock : calculatorImpl.getTemp().getStocks()) {
            uniqSectors.add(stock.getSector());
        }
        assertEquals(uniqSectors.size(), calculatorImpl.getOutput().getAllocations().size());
    }
}
