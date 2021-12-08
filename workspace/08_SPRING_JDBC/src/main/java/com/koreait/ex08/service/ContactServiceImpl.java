package com.koreait.ex08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex08.domain.Contact;
import com.koreait.ex08.repository.ContactRepository;

public class ContactServiceImpl implements ContactService {

	// 서브스가 필요한 객체
	// 서비스가 레파지토리를 불러서 쓰는 거임
	
		// field
		@Autowired
		private ContactRepository repository;
		
		@Override
		public List<Contact> findAllContact() {
			return repository.selectContactList();
		}
		
		@Override
		public void addContact(Contact contact) {
			repository.insertContact(contact);
		}
		
		/* 상세 보기 */
		@Override
		public Contact findContact(Contact contact) {
			return repository.selectContactByNo(contact);
		}
		
		/* 수정 */
		@Override
		public void updateContact(Contact contact) {
			repository.updateContact(contact);
		}
		
		/* 삭제  */
		@Override
		public void deleteContact(Contact contact) {
			repository.deleteContact(contact);
		}

}