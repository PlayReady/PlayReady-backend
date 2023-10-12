package com.playready.PlayReadyBackend.model;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractTest {

    @Test
    public void testContractGettersAndSetters() {
        Contract contract = new Contract();

        contract.setId(1L);
        contract.setStart(new Date());
        contract.setMonths(12L);
        contract.setPrice(1000.0);
        User renter = new User();
        contract.setRenter(renter);
        byte[] fileData = new byte[]{1, 2, 3};
        contract.setFile(fileData);

        assertEquals(1L, contract.getId());
    }

    @Test
    public void testInvoiceAssociation() {
        Contract contract = new Contract();
        Invoice invoice = new Invoice();
        contract.setInvoices(List.of(invoice));

        assertEquals(1, contract.getInvoices().size());
        assertEquals(invoice, contract.getInvoices().get(0));
    }

    @Test
    public void testRenterAssociation() {
        Contract contract = new Contract();
        User renter = new User();
        contract.setRenter(renter);

        assertEquals(renter, contract.getRenter());
    }
}
