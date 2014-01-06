package com.bimoku.dataplatform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.AssociatedTagDao;
import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.dao.TagDao;
import com.bimoku.dataplatform.entity.AssociatedTag;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Tag;
import com.bimoku.dataplatform.entity.dto.TagDTO;
import com.bimoku.dataplatform.util.DTOAssembler;

@Transactional
@Service
public class TagService {

	@Autowired
	private TagDao dao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private AssociatedTagDao aTagDao;
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TagDTO> getPopularTags() {
		List<TagDTO> dtos = new ArrayList<TagDTO>();
		List<Tag> tags = dao.findPopularTags();
		for (Tag tag : tags) {
			dtos.add((TagDTO) DTOAssembler.assemble(tag, new TagDTO()));
		}
		return dtos;
	}
	
	/**
	 * Add a tag with tagName for the book with isbn.
	 * @param isbn
	 * @param tagName
	 */
	public void addTag(String isbn, String tagName) {
		Book book = bookDao.findByIsbn(isbn);
		if(book == null) {
			//TODO Exception handling.
			return;
		}
		AssociatedTag aTag = aTagDao.findByTagNameAndBookIsbn(tagName, isbn);
		if(aTag != null) {
			aTag.setCount(aTag.getCount() + 1);
		} else {
			Tag tag = dao.findByName(tagName);
			if(tag == null) {
				tag = new Tag(tagName);
				dao.save(tag);
			} 
			aTag = new AssociatedTag(book, tag);
			book.getAssociatedTags().add(aTag);
			aTagDao.save(aTag);
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TagDTO> findTagsByBook(String isbn) {
		return DTOAssembler.assembleTagDTOs(aTagDao.findByBookIsbnOrderByCountDesc(isbn));
	}
}
