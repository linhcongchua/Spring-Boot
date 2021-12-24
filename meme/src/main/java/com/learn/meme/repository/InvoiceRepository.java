package com.learn.meme.repository;

import com.learn.meme.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Transactional(readOnly = true)
    @Query(value = "call sp_meme_bonusSalary(:i_staff_id, :i_percent_bonus)", nativeQuery = true)
    double do_bonusSalary_Month(@Param("i_staff_id") int i_staff_id, @Param("i_percent_bonus") double i_percent_bonus);

    @Query(value = "call troidu()", nativeQuery = true)
    int troidu();
}
