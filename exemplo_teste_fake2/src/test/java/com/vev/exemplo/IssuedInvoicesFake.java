package com.vev.exemplo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class IssuedInvoicesFake implements IssuedInvoices{
    private final ArrayList<Invoice> invoices;

    public IssuedInvoicesFake() {
        this.invoices = new ArrayList<>();
    }

    @Override
    public List<Invoice> all() {
        return Collections.unmodifiableList(invoices);
    }

    @Override
    public void save(Invoice inv) {
        invoices.add(inv);
    }

}
