package com.vev.exemplo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceFilterTest {

    @Test
    void lowValueInvoicesEmptyTest() {
        IssuedInvoices issuedInvoices = new IssuedInvoicesFake();
        InvoiceFilter invoiceFilter = new InvoiceFilter(issuedInvoices);
        assertThat(invoiceFilter.lowValueInvoices())
            .isEmpty();
    }    

    @Test
    void lowValueInvoicesWith3And0LowTest() {
        IssuedInvoices issuedInvoices = new IssuedInvoicesFake();
        Invoice mauricio = new Invoice("Mauricio", 200);
        issuedInvoices.save(mauricio);
        Invoice steve = new Invoice("Steve", 990);
        issuedInvoices.save(steve);
        Invoice frank = new Invoice("Frank", 100);
        issuedInvoices.save(frank);
        InvoiceFilter invoiceFilter = new InvoiceFilter(issuedInvoices);
        assertThat(invoiceFilter.lowValueInvoices())
            .isEmpty();
    }

    @Test
    void lowValueInvoicesWith3And2LowTest() {
        IssuedInvoices issuedInvoices = new IssuedInvoicesFake();
        Invoice mauricio = new Invoice("Mauricio", 20);
        issuedInvoices.save(mauricio);
        Invoice steve = new Invoice("Steve", 99);
        issuedInvoices.save(steve);
        Invoice frank = new Invoice("Frank", 100);
        issuedInvoices.save(frank);
        InvoiceFilter invoiceFilter = new InvoiceFilter(issuedInvoices);
        assertThat(invoiceFilter.lowValueInvoices())
            .containsExactlyInAnyOrder(mauricio, steve);
    }

}
