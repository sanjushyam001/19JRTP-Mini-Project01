package in.ashokit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Contact;
import in.ashokit.repository.ContactRepository;
import in.ashokit.service.IContactService;

@Service
public class ContactServiceImpl implements IContactService {

	// Injected ContactRepository dependency 
	@Autowired
	private ContactRepository repo;
	
	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("y");
		Contact c = repo.save(contact);
		
		if(c.getId()!=null) {
			return true;
		}
		return false;
		
	}

	@Override
	public boolean deleteContactById(Integer id) {
		Optional<Contact> optional = repo.findById(id);
		if(optional.isPresent()) {
			 Contact contact = optional.get();
			 contact.setActiveSw("n");
			 repo.save(contact);
			 return true;
			 
		}
		
		return false;
		
	}

	@Override
	public Contact getContactById(Integer id) {
		Optional<Contact> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Contact> getAllContacts() {
		Contact c=new Contact();
		c.setActiveSw("y");
		return repo.findAll(Example.of(c));
	}

	@Override
	public List<Contact> getContactsByUsingPagination(Integer page , Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Contact> contacts = repo.findAll(pageable);
		System.out.println("TOTAL PAGES : : "+contacts.getTotalPages());
		return contacts.getContent();
	}

	@Override
	public Integer totalPages(Integer page,Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Contact> contacts = repo.findAll(pageable);
		return contacts.getTotalPages();
	
	}

	@Override
	public List<Contact> searchContactsByName(String searchWord,Integer page,Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Contact> contacts = repo.search(searchWord, pageable);
		return contacts.getContent();
	}

	

}
