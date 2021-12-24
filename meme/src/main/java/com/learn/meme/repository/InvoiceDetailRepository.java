package com.learn.meme.repository;

import com.learn.meme.model.InvoiceDetail;
import com.learn.meme.model.InvoiceDetailPK;
import com.learn.meme.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, InvoiceDetailPK> {
//    List<InvoiceDetail> findByProductId(Integer product_id);
}
