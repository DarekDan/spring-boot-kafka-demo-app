package com.example.demoapp.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AccountTest {


  @Test
  void testEquals() {
    Account a1 = new Account("Bob");
    Account a2 = new Account("Bob");
    assertThat(a1).isNotEqualTo(a2);
    assertThat(a2).isNotEqualTo(a1);
  }

}
