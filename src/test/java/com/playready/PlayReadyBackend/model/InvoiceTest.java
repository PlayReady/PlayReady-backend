package com.playready.PlayReadyBackend.model;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    public void testInvoiceGettersAndSetters() {
        Invoice invoice = new Invoice();

        invoice.setId(1L);
        invoice.setYear(Year.of(2023));
        invoice.setMonth(Month.AUGUST);
        invoice.setPrice(1000L);
        invoice.setPaid(true);

        assertEquals(1L, invoice.getId());
        assertEquals(Year.of(2023), invoice.getYear());
        assertEquals(Month.AUGUST, invoice.getMonth());
        assertEquals(1000L, invoice.getPrice());
        assertTrue(invoice.isPaid());
    }

    @Test
    public void testContractAssociation() {
        Invoice invoice = new Invoice();
        Contract contract = new Contract();
        invoice.setContract(contract);

        assertEquals(contract, invoice.getContract());
    }
}
