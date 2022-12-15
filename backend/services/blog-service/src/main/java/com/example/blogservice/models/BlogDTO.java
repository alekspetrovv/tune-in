package com.example.blogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO {
    private String title;
    private String body;
    private Date createdDate;
    private Date updatedDate;
}
