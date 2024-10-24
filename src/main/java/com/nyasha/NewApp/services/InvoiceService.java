package com.nyasha.NewApp.services;

import com.nyasha.NewApp.model.Invoice;
import com.nyasha.NewApp.model.InvoiceItem;
import com.nyasha.NewApp.repos.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getUserInvoices(Long userId) {
        return invoiceRepository.findByUserIdOrderByPurchaseDateDesc(userId);
    }

    public List<Invoice> getUserInvoicesSortedByAmount(Long userId) {
        return invoiceRepository.findByUserIdOrderByTotalAmountDesc(userId);
    }

    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        for (InvoiceItem item : invoice.getItems()) {
            item.setInvoice(invoice);
        }
        return invoiceRepository.save(invoice);
    }

    public byte[] generatePdfInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // Here you would implement the actual PDF generation
        // For now, we'll just return a placeholder
        String placeholderContent = "Invoice #" + invoiceId + " PDF Content";
        return placeholderContent.getBytes();
    }
}