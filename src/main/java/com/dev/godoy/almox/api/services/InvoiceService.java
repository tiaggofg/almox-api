package com.dev.godoy.almox.api.services;

import com.dev.godoy.almox.api.models.Invoice;
import com.dev.godoy.almox.api.repositories.InvoiceRepository;

import java.util.List;

public class InvoiceService {

    private InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public List<Invoice> findAll() {
        return repository.findAll();
    }

    public Invoice findByNumber(int invoiceNumber) {
        return repository.findByNumber(invoiceNumber);
    }

    public Invoice save(Invoice invoice) {
        return repository.save(invoice);
    }

    public void updateInvoice(int invoiceNumber, Invoice invoice) {
        repository.updateInvoice(invoiceNumber, invoice);
    }

    public void delete(int invoiceNumber) {
        repository.delete(invoiceNumber);
    }
}
