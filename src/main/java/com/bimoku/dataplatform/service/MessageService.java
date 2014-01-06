package com.bimoku.dataplatform.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.dao.MessageDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.dao.WebsiteDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.Website;
import com.bimoku.dataplatform.entity.dto.MessageDTO;
import com.bimoku.dataplatform.util.DTOAssembler;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private WebsiteDao websiteDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public MessageDTO create(MessageDTO messageDTO) {
		Message message = new Message(messageDTO);
		message.setCreateAt(new Date());
		
		if(messageDTO.getUser() != null) {
			User user = userDao.findByName(messageDTO.getUser().getName());
			message.setUser(user);
		} 
		if(messageDTO.getBookName() != null) {
			Book book = bookDao.findByName(messageDTO.getBookName(), new PageRequest(0, 1)).get(0);
			message.setBook(book);
		}
		if(messageDTO.getSite() != null) {
			Website website = websiteDao.findByName(messageDTO.getSite());
			message.setSite(website);
		}
		return DTOAssembler.assembleMessageDTO(messageDao.save(message));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<MessageDTO> findByUserName(String userName, int start, int size) {
		List<Message> messages = messageDao.findByUserName(userName, new PageRequest(start, size, new Sort("createAt")));
		return DTOAssembler.assembleMessageDTOs(messages);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<MessageDTO> findByUserId(int userId, int start, int size) {
		List<Message> messages = messageDao.findByUserId(userId, new PageRequest(start, size, new Sort("createAt")));
		return DTOAssembler.assembleMessageDTOs(messages);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<MessageDTO> findByBookName(String bookName, int start, int size) {
		List<Message> messages = messageDao.findByBookName(bookName, new PageRequest(start, size, new Sort("createAt")));
		return DTOAssembler.assembleMessageDTOs(messages);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<MessageDTO> findByBookISBN(String isbn, int start, int size) {
		List<Message> messages = messageDao.findByBookIsbn(isbn, new PageRequest(start, size, new Sort("createAt")));
		return DTOAssembler.assembleMessageDTOs(messages);
	}
	
}
