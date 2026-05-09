package com.otus.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class User {
  @Id @Column private Long id;
  @Column private String username;
  @Column private String firstName;
  @Column private String lastName;
  @Column private String email;
  @Column private String phone;
}
