package com.nyasha.NewApp.controller;

import com.nyasha.NewApp.model.Invoice;
import com.nyasha.NewApp.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Invoice>> getUserInvoices(@PathVariable Long userId, @RequestParam(required = false) String sortBy) {
        if ("amount".equals(sortBy)) {
            return ResponseEntity.ok(invoiceService.getUserInvoicesSortedByAmount(userId));
        }
        return ResponseEntity.ok(invoiceService.getUserInvoices(userId));
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.createInvoice(invoice));
    }

    @GetMapping("/{invoiceId}/pdf")
    public ResponseEntity<byte[]> getPdfInvoice(@PathVariable Long invoiceId) {
        byte[] pdfBytes = invoiceService.generatePdfInvoice(invoiceId);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf")
                .body(pdfBytes);
    }
}
