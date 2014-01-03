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

import com.bimoku.dataplatform.dao.MessageDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.dto.MessageDTO;
import com.bimoku.dataplatform.util.DTOAssembler;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Message create(MessageDTO messageDTO) {
		Message message = new Message(messageDTO);
		message.setCreateAt(new Date());
		
		User user = userDao.findOne(messageDTO.getUser().getId());
		message.setUser(user);
		return messageDao.save(message);
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
