package com.bimoku.dataplatform.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.AuthorDao;
import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.dao.PressDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.Author;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.Press;
import com.bimoku.dataplatform.entity.dto.AuthorDTO;
import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.entity.dto.BookDetailsDTO;
import com.bimoku.dataplatform.entity.dto.MessageDTO;
import com.bimoku.dataplatform.util.DTOAssembler;

@Transactional
@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private PressDao pressDao;
	
	@Autowired
	private UserDao userDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Creates a Book.
	 * @param book entity
	 * @return the new created book
	 */
	public Book create(BookDetailsDTO dto) {
		Book book = new Book(dto);
		
		//add authors
		for(AuthorDTO authorDTO : dto.getAuthors()) {
			Author author = null;
			if(authorDTO.getId() != 0) {
				author = authorDao.findOne(authorDTO.getId());
			} else {
				author = new Author(authorDTO);
				authorDao.save(author);
			}
			
			book.getAuthors().add(author);
		}
		//add translators
		
		//add press
		Press press = pressDao.findByName(dto.getPress());
		if(press != null) {
			book.setPress(press);
		}
		//add messages
		
		//add tags
		
		//TODO
		return bookDao.save(book);
	}

	/** 
	 * Gets a page of books. 
	 * 
	 * @param start the start page number
	 * @param size the size of page
	 * @return a page of books
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findAll(int start, int size, String direction, String... orders) {
		List<Book> books = null;
		
		if(orders == null || orders.length == 0) {
			books = bookDao.findAll(new PageRequest(start, size)).getContent();
		} else {
			Direction d = Direction.fromString(direction);
			books = bookDao.findAll(new PageRequest(start, size, new Sort(d, orders))).getContent();
		}
		
		return convertToBookDTOs(books);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findBooksByName(String name, int start, int size, String direction, String... orders) {
		List<Book> books = null;
		if(orders == null || orders.length == 0) {
			books = bookDao.findByName(name, (new PageRequest(start, size)));
		} else {
			Direction d = Direction.fromString(direction);
			books = bookDao.findByName(name, new PageRequest(start, size, new Sort(d, orders)));
		}
		return convertToBookDTOs(books);		
	}
	
	/**
	 * Gets the book by the ISBN.
	 * @param isbn ISBN
	 * @return the book entity
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public BookDetailsDTO findByIsbn(String isbn) {
		
		Book book = bookDao.findByIsbn(isbn);
		if(book == null) {
			return null;
		}
		
		BookDetailsDTO dto = DTOAssembler.assembleBookDetailDTO(book);
		
		//get comments
		//TODO should not fetch all comments (only 3 comments are required). 
		List<Message> messages = new ArrayList<Message>(book.getMessages());
		dto.setCommentCount(messages.size());
		
		if(dto.getCommentCount() > 3) {
			messages =  new ArrayList<Message>(book.getMessages()).subList(0, 3);
		} 
		List<MessageDTO> mDTOs = DTOAssembler.assembleMessageDTOs(messages);
		dto.setMessages(mDTOs);
		
		//get authors
		dto.setAuthors(DTOAssembler.assembleAuthorDTOs(book.getAuthors()));
		//get translators
		dto.setTranslators(DTOAssembler.assembleAuthorDTOs(book.getTranslators()));
		//get tags
		dto.setTags(DTOAssembler.assembleTagDTOs(book.getAssociatedTags()));
		//get recommendations
		if(dto.getTags().size() !=  0) {
			List<BookDTO> recommendations = DTOAssembler.assembleBookDTOs(bookDao.findByAssociatedTagsTagName(dto.getTags().get(0).getName(), new PageRequest(0, 10)));
			dto.setRecommendations(recommendations);
		}
		//get raw book information
		if(book.getBookRaws().size() != 0) {
			dto.setBookRaws(DTOAssembler.assembleBookRawDTOs(book.getBookRaws()));
		}
		return dto;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findByTagName(String tag, int start, int size, String direction, String... orders) {
		List<Book> books = null;
		if(orders == null || orders.length == 0) {
			books = bookDao.findByAssociatedTagsTagName(tag, new PageRequest(start, size));
		} else {
			Direction d = Direction.fromString(direction);
			books = bookDao.findByAssociatedTagsTagName(tag, new PageRequest(start, size, new Sort(d, orders)));
		}
		return convertToBookDTOs(books);		
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findSaleBooks(int start, int size, String direction, String... orders) {
		List<Book> books = null;
		if(orders == null || orders.length == 0) {
			books = bookDao.findByIsSaleRankTrue(new PageRequest(start, size));
		} else {
			Direction d = Direction.fromString(direction);
			books = bookDao.findByIsSaleRankTrue(new PageRequest(start, size, new Sort(d, orders)));
		}
		return convertToBookDTOs(books);		
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findPromotionBooks(int start, int size, String direction, String... orders) {
		List<Book> books = null;
		if(orders == null || orders.length == 0) {
			books = bookDao.findByIsPromotionRankTrue(new PageRequest(start, size));
		} else {
			Direction d = Direction.fromString(direction);
			books = bookDao.findByIsPromotionRankTrue(new PageRequest(start, size, new Sort(d, orders)));
		}
		return convertToBookDTOs(books);		
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findNewBooks(int start, int size, String direction, String... orders) {
		List<Book> books = null;
		if(orders == null || orders.length == 0) {
			books = bookDao.findByIsNewRankTrue(new PageRequest(start, size));
		} else {
			Direction d = Direction.fromString(direction);
			books = bookDao.findByIsNewRankTrue(new PageRequest(start, size, new Sort(d, orders)));
		}
		return convertToBookDTOs(books);		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findCollectedBooks(String userName, int start, int size) {
		StringBuilder sb = new StringBuilder(256);
		sb.append("select c.book from User u join u.collectedBooks c ")
			.append("where u.name = :name");
		Query q = entityManager.createQuery(sb.toString());
		q.setParameter("name", userName);
		q.setFirstResult(start * size);
		q.setMaxResults(size);
		return convertToBookDTOs(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findLikedBooks(String userName, int start, int size) {
		StringBuilder sb = new StringBuilder(256);
		sb.append("select l from User u join u.likeBooks l ")
			.append("where u.name = :name");
		Query q = entityManager.createQuery(sb.toString());
		q.setParameter("name", userName);
		q.setFirstResult(start * size);
		q.setMaxResults(size);
		return convertToBookDTOs(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BookDTO> findSearchedBooks(String userName, int start, int size) {
		StringBuilder sb = new StringBuilder(256);
		sb.append("select s from User u join u.searchBooks s ")
			.append("where u.name = :name");
		Query q = entityManager.createQuery(sb.toString());
		q.setParameter("name", userName);
		q.setFirstResult(start * size);
		q.setMaxResults(size);
		return convertToBookDTOs(q.getResultList());
	}
	
	/**
	 * Gets a page of books by the press name. 
	 * @param press press name
	 * @param pageable pagination information
	 * @return a page of books
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Book> findByPressName(String press, Pageable pageable) {
		return bookDao.findByPressName(press, pageable);
	}
	
	/**
	 * 
	 * @param tag
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Book> findByTagsName(String tag, Pageable pageable) {
		return bookDao.findByAssociatedTagsTagName(tag, pageable);
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<BookDTO> universalSearch(String input, int start, int size) {
		StringBuilder sb = new StringBuilder(256);
		sb.append("select b from Book b left join fetch b.press p ")
			.append("left join fetch b.authors a where b.name like '%")
			.append(input).append("%' ")
			.append("or a.name = :input or p.name = :input");
		Query q = entityManager.createQuery(sb.toString());
		q.setParameter("input", input);
		q.setFirstResult(start * size);
		q.setMaxResults(size);
		
		return convertToBookDTOs(q.getResultList());
	}
	
	private List<BookDTO> convertToBookDTOs(Collection<Book> books) {
		List<BookDTO> dtos = new ArrayList<BookDTO>();
		for(Book book : books) {
			BookDTO dto = DTOAssembler.assembleBookDTO(book);
			//TODO should not fetch all comments. 
			List<Message> messages = new ArrayList<Message>(book.getMessages());
			dto.setCommentCount(messages.size());
			
			if(dto.getCommentCount() > 3) {
				messages =  new ArrayList<Message>(book.getMessages()).subList(0, 3);
			} 

			List<MessageDTO> mDTOs = DTOAssembler.assembleMessageDTOs(messages);
			dto.setMessages(mDTOs);
			
			dtos.add(dto);
		}
		return dtos;
	}
	
}