package com.dev.godoy.almox.api.repositories;

import com.dev.godoy.almox.api.exceptions.ObjectNotFoundException;
import com.dev.godoy.almox.api.models.Invoice;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class InvoiceRepository {

    private MongoCollection<Invoice> invoiceCollection;

    public InvoiceRepository(MongoDatabase database) {
        this.invoiceCollection = database.getCollection("invoice", Invoice.class);
    }

    public List<Invoice> findAll() {
        List<Invoice> invoices = new ArrayList<>();
        for (Invoice invoice : invoiceCollection.find()) {
            invoices.add(invoice);
        }
        return invoices;
    }

    public Invoice findByNumber(long invoiceNumber) {
        Invoice invoice = invoiceCollection.find(eq("invoiceNumber", invoiceNumber)).first();
        if (invoice == null) {
            throw new ObjectNotFoundException("Nota número: " + invoiceNumber + " não encontrada!");
        }
        return invoice;
    }

    public Invoice save(Invoice invoice) {
        invoice.setId(new ObjectId().toString());
        invoiceCollection.insertOne(invoice);
        return invoice;
    }

    public void updateInvoice(long invoiceNumber, Invoice invoice) {
        Invoice updatedInvoice = invoiceCollection.findOneAndReplace(eq("invoiceNumber", invoiceNumber), invoice);
        if (updatedInvoice == null) {
            throw new ObjectNotFoundException("Nota número " + invoiceNumber + " não encontrada!");
        }
    }

    public void delete(long invoiceNumber) {
        Invoice invoice = invoiceCollection.findOneAndDelete(eq("invoiceNumber", invoiceNumber));
        if (invoice == null) {
            throw new ObjectNotFoundException("Nota número " + invoiceNumber + " não encontrada!");
        }
    }
}
