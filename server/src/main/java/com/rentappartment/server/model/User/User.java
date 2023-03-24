package com.rentappartment.server.model.User;

import com.rentappartment.server.model.Filter.Filter;
import jakarta.persistence.*;

@Entity
@Table(name = "\"User\"")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private int userId;

  public int getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object object) {
    User a = (User) object;
    return this.userId == a.userId;
  }

}
