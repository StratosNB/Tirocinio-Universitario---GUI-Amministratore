package db.hibernate.service;

import java.util.List;

import db.hibernate.dao.UserDao;
import models.User;

public class UserService {
	private static UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public void persist(User entity) {
		userDao.openCurrentSessionwithTransaction();
		userDao.persist(entity);
		userDao.closeCurrentSessionwithTransaction();
	}

	public void update(User entity) {
		userDao.openCurrentSessionwithTransaction();
		userDao.update(entity);
		userDao.closeCurrentSessionwithTransaction();
	}

	public User findById(String id) {
		userDao.openCurrentSession();
		User book = userDao.findById(id);
		userDao.closeCurrentSession();
		return book;
	}

	public void delete(String id) {
		userDao.openCurrentSessionwithTransaction();
		User book = userDao.findById(id);
		userDao.delete(book);
		userDao.closeCurrentSessionwithTransaction();
	}

	public List<User> findAll() {
		userDao.openCurrentSession();
		List<User> books = userDao.findAll();
		userDao.closeCurrentSession();
		return books;
	}

	public void deleteAll() {
		userDao.openCurrentSessionwithTransaction();
		userDao.deleteAll();
		userDao.closeCurrentSessionwithTransaction();
	}

	public UserDao userDao() {
		return userDao;
	}
}
