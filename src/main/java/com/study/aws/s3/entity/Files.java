package com.study.aws.s3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Files {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String filename;
    private String extension;

    public Files(String filename) {
        this.filename = filename;
        this.extension = getExtention(filename);
    }

    private String getExtention(String filename){
        int doxIdx = filename.lastIndexOf('.');
        return filename.substring(doxIdx+1);
    }

    public String getStorePath() {
        return id + "." + extension;
    }
}
