package com.springboot.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.api.book.dao.BookRepository;
import com.springboot.api.book.entities.Book;

@Component
public class BookService {
	@Autowired
	private BookRepository bookRepository;
//	static private List<Book> list = new ArrayList<>();
//	static {
//		list.add(new Book(12, "Life's Amazing Secrets", "Gaur Gopal Das"));
//		list.add(new Book(13, "Rich Dad Poor Dad", "Unknown"));
//		list.add(new Book(14, "Coffee Can Investing", "Sourabh"));
//	}

	public List<Book> getAllBooks() {
		List<Book> lst= (List<Book>) bookRepository.findAll();
		return lst;
	}

	public Book getBookById(int id) {
		Book book = null;
		try {
//			book = list.stream().filter(e -> e.getId() == id).findFirst().get();
//			;
			book=bookRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public Book addBook(Book b) {
		//list.add(b);
		Book result=bookRepository.save(b);
		return result;
	}

	public void deleteBook(int id) {
		//list = list.stream().filter(book -> book.getId() != id).collect(Collectors.toList());
		bookRepository.deleteById(id);
	}

	public void updateBook(Book book, int id) {
//		list = list.stream().map(b -> {
//			if (b.getId() == id) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(id);
		bookRepository.save(book);
	}
}
