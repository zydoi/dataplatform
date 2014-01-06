package com.bimoku.dataplatform.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.bimoku.dataplatform.entity.AssociatedTag;
import com.bimoku.dataplatform.entity.Author;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.dto.AuthorDTO;
import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.entity.dto.BookDetailsDTO;
import com.bimoku.dataplatform.entity.dto.MessageDTO;
import com.bimoku.dataplatform.entity.dto.TagDTO;
import com.bimoku.dataplatform.entity.dto.UserDTO;

public class DTOAssembler {
	
	private static Logger logger = Logger.getLogger(DTOAssembler.class);

	public static BookDTO assembleBookDTO(Book book) {
		BookDTO dto = new BookDTO();
		return (BookDTO) assemble(book, dto);
	}
	
	public static List<BookDTO> assembleBookDTOs(Collection<Book> books) {
		List<BookDTO> dtos = new ArrayList<BookDTO>();
		for (Book book : books) {
			assembleBookDTO(book);
		}
		return dtos;
	}
	
	public static BookDetailsDTO assembleBookDetailDTO(Book book) {
		BookDetailsDTO dto = new BookDetailsDTO();
		if(dto.getPress() != null) {
			dto.setPress(book.getPress().getName());
		}
		return (BookDetailsDTO) assemble(book, dto);
	}
	
	public static MessageDTO assembleMessageDTO(Message message) {
		MessageDTO dto = new MessageDTO();
		assemble(message, dto);
		if(message.getBook() != null) {
			dto.setBookName(message.getBook().getName());
		}
		if(message.getUser() != null) {
			dto.setUser((UserDTO) assemble(message.getUser(), new UserDTO()));
		}
		if(message.getSite() != null) {
			dto.setSite(message.getSite().getName());
		}
		return dto;
	}
	
	public static List<MessageDTO> assembleMessageDTOs(Collection<Message> messages) {
		List<MessageDTO> dtos = new ArrayList<MessageDTO>();
		for (Message message : messages) {
			dtos.add(assembleMessageDTO(message));
		}
		return dtos;
	}
	
	public static UserDTO assembleUserDTO(User user) {
		UserDTO dto = (UserDTO) assemble(user, new UserDTO());
		dto.setUserImage(user.getUserProfile().getUserImage());
		return dto;
	}
	
	public static List<UserDTO>	assembleUserDTOs(Collection<User> users) {
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (User user : users) {
			dtos.add((UserDTO) assemble(user, new UserDTO()));
		}
		return dtos;
	}
	
	public static AuthorDTO assembleAuthorDTO(Author author) {
		AuthorDTO dto = new AuthorDTO();
		assemble(author, dto);
		return dto;
	}
	
	public static List<AuthorDTO> assembleAuthorDTOs(Collection<Author> authors) {
		List<AuthorDTO> dtos = new ArrayList<AuthorDTO>();
		for (Author author : authors) {
			dtos.add(assembleAuthorDTO(author));
		}
		return dtos;
	}
	
	public static List<TagDTO> assembleTagDTOs(Collection<AssociatedTag> aTags) {
		List<TagDTO> dtos = new ArrayList<TagDTO>();
		for (AssociatedTag atag : aTags) {
			TagDTO dto = new TagDTO();
			dto.setCount(atag.getCount());
			dto.setName(atag.getTag().getName());
		}
		return dtos;
	}
	
	/**
	 * Converts entity object to DTO.
	 * @param obj
	 * @param dto
	 * @return
	 */
	public static Object assemble(Object obj, Object dto) {
		if(obj == null || dto == null) {
			return null;
		}
		
		Pattern setterPattern = Pattern.compile("set.*");

		Method[] methods = dto.getClass().getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			try {
				if (setterPattern.matcher(methodName).matches()) {
					String propertyName = methodName.substring(3);
					Method getter = obj.getClass().getMethod("get" + propertyName);
					
					if(getter.getReturnType().isPrimitive() || 
							getter.getReturnType().equals(String.class) ||
							getter.getReturnType().equals(Date.class)) {
						
						method.invoke(dto, getter.invoke(obj));
					}
				}
			} catch (NoSuchMethodException e) {
				logger.info(dto.getClass().getName() + "." + methodName + " is not supported!");
			} catch (Exception e) {
				System.out.println(dto.getClass().getName() + "." + methodName);
				e.printStackTrace();
			}

		}
		return dto;
	}
	
}
