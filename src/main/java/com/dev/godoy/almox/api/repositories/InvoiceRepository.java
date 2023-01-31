package com.dev.godoy.almox.api.repositories;

import com.dev.godoy.almox.api.dtos.ProductInvoiceDto;
import com.dev.godoy.almox.api.exceptions.ObjectNotFoundException;
import com.dev.godoy.almox.api.models.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class InvoiceRepository {

    private MongoCollection<Invoice> invoiceCollection;
    private MongoCollection<Document> documentMongoCollection;

    public InvoiceRepository(MongoDatabase database) {
        this.invoiceCollection = database.getCollection("invoice", Invoice.class);
        this.documentMongoCollection = database.getCollection("invoice", Document.class);
    }

    public List<Invoice> findAll() {
        List<Invoice> invoices = new ArrayList<>();
        for (Document doc : documentMongoCollection.find()) {
            Invoice invoice = bsonDocumentToInvoice(doc);
            invoices.add(invoice);
        }
        return invoices;
    }

    public Invoice findByNumber(long invoiceNumber) {
        Document doc = documentMongoCollection.find(eq("invoiceNumber", invoiceNumber)).first();
        if (doc == null) {
            throw new ObjectNotFoundException("Nota número: " + invoiceNumber + " não encontrada!");
        }
        return bsonDocumentToInvoice(doc);
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

    private Invoice bsonDocumentToInvoice(Document doc) {
        int number = doc.getInteger("invoiceNumber");
        String type = doc.getString("type");
        String origin = doc.getString("origin");

        Person issuer = PersonRepository.bsonDocumentToPerson((Document) doc.get("issuer"));
        Person receiver = PersonRepository.bsonDocumentToPerson((Document) doc.get("receiver"));
        Person carrier = PersonRepository.bsonDocumentToPerson((Document) doc.get("carrier"));

        List<ProductInvoiceDto> products = new ArrayList<>();
        for (Document d : (List<Document>) doc.get("products")) {
            ProductInvoiceDto product = ProductRepository.bsonDocumentToProduct(d);
            products.add(product);
        }

        return new Invoice(null, number, Type.valueOf(type), Origin.valueOf(origin), issuer, receiver, carrier, products);
    }
}
