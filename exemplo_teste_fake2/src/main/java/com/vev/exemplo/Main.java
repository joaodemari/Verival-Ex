package com.vev.exemplo;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.resetDatabase();
        IssuedInvoices issuedInvoices = new IssuedInvoicesImpl(databaseConnection);
        issuedInvoices.save(new Invoice("Mauricio", 20));
        issuedInvoices.save(new Invoice("Steve", 99));
        issuedInvoices.save(new Invoice("Frank", 100));
        InvoiceFilter invoiceFilter = new InvoiceFilter(issuedInvoices);
        var lowinvoices = invoiceFilter.lowValueInvoices();
        lowinvoices.forEach(System.out::println);
    }
}
