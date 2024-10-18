package com.vev.exemplo;

import java.util.ArrayList;
import java.util.List;

public class IssuedInvoices {

    private DatabaseConnection connection;

    public IssuedInvoices(DatabaseConnection connection) {
        this.connection = connection;
    }

    public List<Invoice> all() {
        return connection.withSql(() -> {
            try (var ps = connection.getConnection().prepareStatement("select name, value from invoice")) {
                final var rs = ps.executeQuery();
                List<Invoice> allInvoices = new ArrayList<>();
                while (rs.next()) {
                    allInvoices.add(new Invoice(rs.getString("name"), rs.getInt("value")));
                }
                return allInvoices;
            }
        });
    }

    public void save(Invoice inv) {
        connection.withSql(() -> {
            try (var ps = connection.getConnection()
                    .prepareStatement("insert into invoice (name, value) values (?,?)")) {
                ps.setString(1, inv.getCustomer());
                ps.setInt(2, inv.getValue());
                ps.execute();
                connection.getConnection().commit();
            }
            return null;
        });
    }

}