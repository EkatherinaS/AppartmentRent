package com.rentappartment.server;

import com.rentappartment.server.controller.*;
import com.rentappartment.server.model.Address.Address;
import com.rentappartment.server.model.Address.AddressDao;
import com.rentappartment.server.model.Contact.Contact;
import com.rentappartment.server.model.Contact.ContactDao;
import com.rentappartment.server.model.Favorite.Favorite;
import com.rentappartment.server.model.Favorite.FavoriteDao;
import com.rentappartment.server.model.Favorite.FavoriteId;
import com.rentappartment.server.model.Filter.Filter;
import com.rentappartment.server.model.Filter.FilterDao;
import com.rentappartment.server.model.Image.Image;
import com.rentappartment.server.model.Image.ImageDao;
import com.rentappartment.server.model.Offer.Offer;
import com.rentappartment.server.model.Offer.OfferDao;
import com.rentappartment.server.model.User.User;
import com.rentappartment.server.model.User.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ServerApplicationTests {

	@Autowired
	AddressDao addressDao;
	@Autowired
	ContactDao contactDao;
	@Autowired
	FilterDao filterDao;
	@Autowired
	UserDao userDao;
	@Autowired
	OfferDao offerDao;
	@Autowired
	FavoriteDao favoriteDao;
	@Autowired
	ImageDao imageDao;
	
	@Autowired
	AddressController addressController;
	@Autowired
	ContactController contactController;
	@Autowired
	FilterController filterController;
	@Autowired
	UserController userController;
	@Autowired
	OfferController offerController;
	@Autowired
	FavoriteController favoriteController;
	@Autowired
	ImageController imageController;



	//Address tests
	@Test
	void addressCRUD() {
		addressDao.deleteAllAddresses();
		int listEmptySize = addressDao.getAllAddresses().size();

		Address object = createAddress();
		int listSavedSize = addressDao.getAllAddresses().size();

		Address addressFound = addressDao.findById(object.getAddress());

		addressDao.delete(object);
		int listDeletedSize = addressDao.getAllAddresses().size();

		assertThat(object.getAddress()).isEqualTo("addressText");
		assertThat(object.getFloorNumber()).isEqualTo(10);
		assertThat(object.getYear()).isEqualTo(2001);
		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(addressFound).isEqualTo(object);
		assertThat(listDeletedSize).isEqualTo(0);
	}
	@Test
	void addressController() {
		addressDao.deleteAllAddresses();
		int listEmptySize = addressController.getAllAddress().size();

		Address object = createAddress();
		int listSavedSize = addressController.getAllAddress().size();

		addressDao.delete(object);
		int listDeletedSize = addressController.getAllAddress().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(listDeletedSize).isEqualTo(0);
	}

	//Contact tests
	@Test
	void contactCRUD() {

		contactDao.deleteAllContacts();
		int listEmptySize = contactDao.getAllContacts().size();

		Contact object = createContact();
		int listSavedSize = contactDao.getAllContacts().size();

		Contact found = contactDao.findById(object.getPhoneNumber());

		contactDao.delete(object);
		int listDeletedSize = contactDao.getAllContacts().size();

		assertThat(object.getName()).isEqualTo("Name");
		assertThat(object.getPhoneNumber()).isEqualTo("+7 999 999 99 99");
		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(found).isEqualTo(object);
		assertThat(listDeletedSize).isEqualTo(0);
	}
	@Test
	void contactController() {
		contactDao.deleteAllContacts();
		int listEmptySize = contactController.getAllContacts().size();

		Contact object = createContact();
		int listSavedSize = contactController.getAllContacts().size();

		contactDao.delete(object);
		int listDeletedSize = contactController.getAllContacts().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(listDeletedSize).isEqualTo(0);
	}

	//Filter tests
	@Test
	void filterCRUD() {
		Filter object = createFilter();

		filterDao.deleteAllFilters();
		int listEmptySize = filterDao.getAllFilters().size();

		filterDao.save(object);
		int listSavedSize = filterDao.getAllFilters().size();

		Filter found = filterDao.findById(object.getName());

		filterDao.delete(object);
		int listDeletedSize = filterDao.getAllFilters().size();

		assertThat(object.getName()).isEqualTo("newFilter");
		assertThat(object.getPriority()).isEqualTo(1);
		assertThat(object.isSortAscending()).isEqualTo(true);
		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(found).isEqualTo(object);
		assertThat(listDeletedSize).isEqualTo(0);
	}
	@Test
	void filterController() {
		filterDao.deleteAllFilters();
		int listEmptySize = filterController.getFilters().size();

		Filter object = createFilter();
		int listSavedSize = filterController.getFilters().size();

		filterDao.delete(object);
		int listDeletedSize = filterController.getFilters().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(listDeletedSize).isEqualTo(0);
	}

	//User tests
	@Test
	void userCRUD() {
		userDao.deleteAllUsers();
		int listEmptySize = userDao.getAllUsers().size();

		User object = userDao.create();
		int listSavedSize = userDao.getAllUsers().size();

		User found = userDao.findById(object.getUserId());

		userDao.delete(object);
		int listDeletedSize = userDao.getAllUsers().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(found).isEqualTo(object);
		assertThat(listDeletedSize).isEqualTo(0);
	}
	@Test
	void userController() {
		userDao.deleteAllUsers();
		int listEmptySize = userController.getAllUsers().size();

		User object = userDao.create();
		int listSavedSize = userController.getAllUsers().size();

		User userCheck = userController.checkUser(object.getUserId());
		User userCreate = userController.createUser();

		userDao.delete(object);
		userDao.delete(userCreate);
		int listDeletedSize = userController.getAllUsers().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(listDeletedSize).isEqualTo(0);
		assertThat(userCheck).isNotNull();
		assertThat(userCreate).isNotNull();
	}

	//Offer tests
	@Test
	void offerCRUD() {
		offerDao.deleteOldOffers(new Date());
		int listEmptySize = offerDao.getAllOffers().size();

		Offer object = createOffer();
		int listSavedSize = offerDao.getAllOffers().size();

		Offer found = offerDao.findById(object.getId());

		offerDao.delete(object);
		int listDeletedSize = offerDao.getAllOffers().size();

		assertThat(object.getMainImage()).isEqualTo("MainImage");
		assertThat(object.getFullDescription()).isEqualTo("FullDescription");
		assertThat(object.getType()).isEqualTo("Type");
		assertThat(object.getArea()).isEqualTo(10.0);
		assertThat(object.getFloor()).isEqualTo(11);
		assertThat(object.getKitchenArea()).isEqualTo(12.0);
		assertThat(object.getPrice()).isEqualTo(13.0);
		assertThat(object.getRoomNumber()).isEqualTo(14);

		assertThat(object.getContact().getName()).isEqualTo("Name");
		assertThat(object.getContact().getPhoneNumber()).isEqualTo("+7 999 999 99 99");
		assertThat(object.getAddress().getAddress()).isEqualTo("addressText");
		assertThat(object.getAddress().getFloorNumber()).isEqualTo(10);
		assertThat(object.getAddress().getYear()).isEqualTo(2001);

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(found).isNotEqualTo(null);
		assertThat(listDeletedSize).isEqualTo(0);
	}
	@Test
	void offerController() {
		offerDao.deleteOldOffers(new Date());
		int listEmptySize = offerController.getAllOffers().size();

		Offer object = createOffer();
		int listSavedSize = offerController.getAllOffers().size();

		offerDao.delete(object);
		int listDeletedSize = offerController.getAllOffers().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(listDeletedSize).isEqualTo(0);

		List<Offer> offerList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Offer offer = new Offer();
			offer.setMainImage("MainImage" + i);
			offer.setFullDescription("FullDescription" + i);
			offer.setType("квартира");
			offer.setArea(10.0 + i);
			offer.setFloor(11 + i);
			offer.setKitchenArea(12.0 + i);
			offer.setPrice(13.0 + i);
			offer.setRoomNumber(14 + i);

			offer.setAddress(createAddress());
			offer.setContact(createContact());
			offerDao.save(offer);
			offerList.add(offer);
		}

		int filteredOfferListSize = offerController.getFilteredOffers(
				true, true,
				13, 15, true,
				14, 16, 10, 12,
				12, 15, 2000, 2002,
				11, 13, 9, 11).size();
		assertThat(filteredOfferListSize).isEqualTo(3);

		int sortedOfferListSize = offerController.getSortedOffers(new ArrayList<>()).size();
		assertThat(sortedOfferListSize).isEqualTo(5);

		int filteredSortedOfferListSize = offerController.getFilteredSortedOffers(
				true, true,
				13, 15, true,
				14, 16, 10, 12,
				12, 15, 2000, 2002,
				11, 13, 9, 11,
				new ArrayList<>()).size();
		assertThat(filteredSortedOfferListSize).isEqualTo(3);

		offerDao.deleteOldOffers(new Date());
	}

	//Favorite tests
	@Test
	void favoriteCRUD() {
		favoriteDao.deleteAllFavorites();
		int listEmptySize = favoriteDao.getAllFavorite().size();

		Favorite object = createFavorite();
		int listSavedSize = favoriteDao.getAllFavorite().size();

		Favorite found = favoriteDao.findById(new FavoriteId(object.getUser(), object.getOffer()));

		favoriteDao.delete(object);
		int listDeletedSize = favoriteDao.getAllFavorite().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(found).isNotEqualTo(null);
		assertThat(listDeletedSize).isEqualTo(0);
	}
	@Test
	void favoriteController() {
		favoriteDao.deleteAllFavorites();
		int listEmptySize = favoriteController.getAllFavorite().size();

		Favorite object = new Favorite();
		object.setOffer(createOffer());
		object.setUser(userDao.create());

		favoriteController.saveFavorite(object);
		int listSavedSize = favoriteController.getAllFavorite().size();

		favoriteController.deleteFavorite(object);
		int listDeletedSize = favoriteController.getAllFavorite().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(listDeletedSize).isEqualTo(0);

		User user = userDao.create();
		int userFavoriteSize = favoriteController.getFavoriteOffers(user.getUserId()).size();
		assertThat(userFavoriteSize).isEqualTo(0);

		createFavorite(user);
		userFavoriteSize = favoriteController.getFavoriteOffers(user.getUserId()).size();
		assertThat(userFavoriteSize).isEqualTo(1);

		favoriteDao.deleteAllFavorites();
		userDao.getAllUsers();
	}

	//Image tests
	@Test
	void imageCRUD() {
		imageDao.deleteAllImages();
		int listEmptySize = imageDao.getAllImages().size();

		Image object = createImage();
		int listSavedSize = imageDao.getAllImages().size();

		Image found = imageDao.findById(object.getId());

		imageDao.delete(object);
		int listDeletedSize = imageDao.getAllImages().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(found).isNotEqualTo(null);
		assertThat(listDeletedSize).isEqualTo(0);
	}
	@Test
	void imageController() {
		imageDao.deleteAllImages();
		int listEmptySize = imageController.getAllImages().size();

		Image object = createImage();
		int listSavedSize = imageController.getAllImages().size();

		imageDao.delete(object);
		int listDeletedSize = imageController.getAllImages().size();

		assertThat(listEmptySize).isEqualTo(0);
		assertThat(listSavedSize).isEqualTo(1);
		assertThat(listDeletedSize).isEqualTo(0);

		Offer offer = createOffer();
		int offerImagesSize = imageController.getOfferImages(offer.getId()).size();
		assertThat(offerImagesSize).isEqualTo(0);

		createImage(offer);
		offerImagesSize = imageController.getOfferImages(offer.getId()).size();
		assertThat(offerImagesSize).isEqualTo(1);

		imageDao.deleteAllImages();
	}




	private Image createImage() {
		Image object = new Image();
		object.setOffer(createOffer());
		object.setImageUrl("imageUrl");
		imageDao.save(object);
		return object;
	}
	private void createImage(Offer offer) {
		Image object = new Image();
		object.setOffer(offer);
		object.setImageUrl("imageUrl");
		imageDao.save(object);
	}

	private Favorite createFavorite() {
		Favorite object = new Favorite();
		object.setOffer(createOffer());
		object.setUser(userDao.create());
		favoriteDao.save(object);
		return object;
	}
	private void createFavorite(User user) {
		Favorite object = new Favorite();
		object.setOffer(createOffer());
		object.setUser(user);
		favoriteDao.save(object);
	}

	private Filter createFilter() {
		Filter object = new Filter();
		object.setName("newFilter");
		object.setPriority(1);
		object.setSortAscending(true);
		filterDao.save(object);
		return object;
	}

	private Address createAddress() {
		Address object = new Address();
		object.setYear(2001);
		object.setFloorNumber(10);
		object.setAddress("addressText");
		addressDao.save(object);
		return object;
	}

	private Contact createContact() {
		Contact object = new Contact();
		object.setName("Name");
		object.setPhoneNumber("+7 999 999 99 99");
		contactDao.save(object);
		return object;
	}

	private Offer createOffer() {
		Offer object = new Offer();
		object.setMainImage("MainImage");
		object.setFullDescription("FullDescription");
		object.setType("Type");
		object.setArea(10.0);
		object.setFloor(11);
		object.setKitchenArea(12.0);
		object.setPrice(13.0);
		object.setRoomNumber(14);

		object.setAddress(createAddress());
		object.setContact(createContact());
		offerDao.save(object);
		return object;
	}
}
