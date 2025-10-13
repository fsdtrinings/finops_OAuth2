package com.mkj.app.repository.hrrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkj.app.entity.KYCDocuments;

@Repository
public interface DocumentRepository extends JpaRepository<KYCDocuments, Integer>
{

}
