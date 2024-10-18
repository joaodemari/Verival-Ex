package com.vev.exemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Teste com mock para IssuedInvoices

@ExtendWith(MockitoExtension.class)
class InvoiceFilterMockTest {
    @Mock
    private IssuedInvoices issuedInvoices;
    private InvoiceFilter invoiceFilter;

    @BeforeEach
    void setup() {
        invoiceFilter = new InvoiceFilter(issuedInvoices);
    }

    @Test
    void lowValueInvoicesEmptyTest() {
        List<Invoice> invoices = Collections.emptyList();
        when(issuedInvoices.all()).thenReturn(invoices);
        assertThat(invoiceFilter.lowValueInvoices())
            .isEmpty();
        verify(issuedInvoices).all();
    }    

    @Test
    void lowValueInvoicesWith3And0LowTest() {
        Invoice mauricio = new Invoice("Mauricio", 200);
        Invoice steve = new Invoice("Steve", 990);
        Invoice frank = new Invoice("Frank", 100);
        List<Invoice> invoices = Arrays.asList(mauricio, steve, frank);
        when(issuedInvoices.all()).thenReturn(invoices);
        assertThat(invoiceFilter.lowValueInvoices())
            .isEmpty();
        verify(issuedInvoices).all();
    }

    @Test
    void lowValueInvoicesWith3And2LowTest() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice steve = new Invoice("Steve", 99);
        Invoice frank = new Invoice("Frank", 100);
        List<Invoice> invoices = Arrays.asList(mauricio, steve, frank);
        when(issuedInvoices.all()).thenReturn(invoices);
        assertThat(invoiceFilter.lowValueInvoices())
            .containsExactlyInAnyOrder(mauricio, steve);
        verify(issuedInvoices).all();
    }

}
