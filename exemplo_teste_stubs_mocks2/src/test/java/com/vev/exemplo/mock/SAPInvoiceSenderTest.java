package com.vev.exemplo.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vev.exemplo.mock.Invoice;
import com.vev.exemplo.mock.InvoiceFilter;
import com.vev.exemplo.mock.IssuedInvoices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SAPInvoiceSenderTest {
    @Mock
    private InvoiceFilter invoiceFilter;
    @Mock
    private SAP sap;
    private SAPInvoiceSender invoiceSender;

    @BeforeEach
    void setup() {
        invoiceSender = new SAPInvoiceSender(invoiceFilter, sap);
    }

    @Test
    void noLowValueInvoices() {
        List<Invoice> invoices = Collections.emptyList();
        when(invoiceFilter.lowValueInvoices()).thenReturn(invoices);
        assertThat(invoiceSender.sendLowValuedInvoices())
            .isEmpty();
        verify(sap, never()).send(any(SapInvoice.class));
    }    

    @Test
    void returnNoFailedInvoices() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 25);
        Invoice steve = new Invoice("Steve", 48);
        List<Invoice> invoices = Arrays.asList(mauricio, frank, steve);
        when(invoiceFilter.lowValueInvoices()).thenReturn(invoices);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));

        List<Invoice> failedInvoices = invoiceSender.sendLowValuedInvoices();

        SapInvoice mauriciosInvoice = new SapInvoice("Mauricio", 20, date + "Ma");
        verify(sap).send(mauriciosInvoice);

        SapInvoice franksInvoice = new SapInvoice("Frank", 25, date + "Fr");
        verify(sap).send(franksInvoice);
        
        SapInvoice stevesInvoice = new SapInvoice("Steve", 48, date + "St");
        verify(sap).send(stevesInvoice);
    }

    @Test
    void return1FailedInvoices() {
        Invoice frank = new Invoice("Frank", 25);
        List<Invoice> invoices = Arrays.asList(frank);
        when(invoiceFilter.lowValueInvoices()).thenReturn(invoices);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        SapInvoice franksInvoice = new SapInvoice("Frank", 25, date + "Fr");
        doThrow(new SAPException()).when(sap).send(franksInvoice);

        List<Invoice> failedInvoices = invoiceSender.sendLowValuedInvoices();
        assertThat(failedInvoices).containsExactly(frank);
    }
}
