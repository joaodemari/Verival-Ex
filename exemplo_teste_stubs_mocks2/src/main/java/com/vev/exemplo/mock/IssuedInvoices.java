package com.vev.exemplo.mock;

import java.util.List;

public interface IssuedInvoices {
    List<Invoice> all();
    void save(Invoice inv);
}
