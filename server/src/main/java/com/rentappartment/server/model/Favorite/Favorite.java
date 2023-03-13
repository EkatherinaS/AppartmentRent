package com.rentappartment.server.model.Favorite;


import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Favorite")
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @Column(name = "offer", nullable = false)
  private Offer offer;


  public long getId() {
    return id;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User userId) {
    this.user = user;
  }


  public Offer getOffer() {
    return offer;
  }

  public void setOffer(Offer offer) {
    this.offer = offer;
  }

}
