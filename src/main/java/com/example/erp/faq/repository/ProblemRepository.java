package com.example.erp.faq.repository;


import com.example.erp.faq.entity.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemEntity,Long> {
}
