package com.bcs.restendpoint;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ApplicationTests {

  @Test
  void contextLoads() {
    assertThat(2 + 2).isEqualTo(4);
  }

}
