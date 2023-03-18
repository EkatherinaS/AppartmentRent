package com.rentappartment.server.model.Favorite;

import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "\"Favorite\"")
@IdClass(FavoriteId.class)
public class Favorite {

  @Id
  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "user_entity", nullable = false)
  private User user;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "offer_entity", nullable = false)
  private Offer offer;


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }


  public Offer getOffer() {
    return offer;
  }

  public void setOffer(Offer offer) {
    this.offer = offer;
  }

}
