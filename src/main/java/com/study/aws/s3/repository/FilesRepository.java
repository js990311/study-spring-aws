package com.study.aws.s3.repository;

import com.study.aws.s3.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilesRepository extends JpaRepository<Files, Long> {
}
