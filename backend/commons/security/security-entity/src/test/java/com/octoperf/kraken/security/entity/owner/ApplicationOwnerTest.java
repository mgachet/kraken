package com.octoperf.kraken.security.entity.owner;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class ApplicationOwnerTest {

  public static final ApplicationOwner APPLICATION_OWNER = ApplicationOwner.builder()
      .applicationId("applicationId")
      .build();

  @Test
  public void shouldPassEquals() {
    TestUtils.shouldPassEquals(APPLICATION_OWNER.getClass());
  }

  @Test
  public void shouldPassNPE() {
    TestUtils.shouldPassNPE(APPLICATION_OWNER.getClass());
  }

  @Test
  public void shouldPassToString() {
    TestUtils.shouldPassToString(APPLICATION_OWNER);
  }

}
