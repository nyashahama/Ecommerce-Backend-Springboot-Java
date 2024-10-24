package com.nyasha.NewApp.repos;

import com.nyasha.NewApp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUserIdOrderByPurchaseDateDesc(Long userId);
    List<Invoice> findByUserIdOrderByTotalAmountDesc(Long userId);
}
