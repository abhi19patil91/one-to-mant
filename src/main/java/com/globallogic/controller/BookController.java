package com.globallogic.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dto.BookDto;
import com.globallogic.dto.PageDto;
import com.globallogic.service.BookService;
import com.globallogic.utils.AppConstants;

@RestController
@RequestMapping("/api/v1")
public class BookController {

	@Autowired
	private BookService bookService;

	/*
	 * Gets All Books from Database
	 * */
	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> getAllBooks() {

		return new ResponseEntity<List<BookDto>>(bookService.getAllBooks(), HttpStatus.OK);
	}

	/*
	 * Adds a Book to Database
	 * */
	@PostMapping("/book")
	public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {

		return new ResponseEntity<BookDto>(bookService.addBook(bookDto), HttpStatus.CREATED);

	}

	/*
	 * Gets a Book from Database by using it's ID
	 * */
	@GetMapping("/books/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable(value = AppConstants.ID) long id) {

		return new ResponseEntity<BookDto>(bookService.getBookById(id), HttpStatus.OK);

	}

	/*
	 * Updates a Book by using it's ID
	 * */
	@PutMapping("/books/{id}")
	public ResponseEntity<BookDto> updateBookById(@PathVariable(value = AppConstants.ID) long id,
			@RequestBody BookDto bookDto) {

		return new ResponseEntity<BookDto>(bookService.updateBookById(id, bookDto), HttpStatus.OK);

	}

	/*
	 * Deletes a Book by using it's ID
	 * */
	@DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable(value = AppConstants.ID) long id) {

		bookService.deleteBookById(id);
		return new ResponseEntity<String>(AppConstants.DELETE_MESSAGE, HttpStatus.OK);
	}
	
	/*
	 * Get All Pages From A Book using it's ID
	 * */
	@GetMapping("/books/{id}/pages")
	public ResponseEntity<Set<PageDto>> getAllPagesFromBookById(@PathVariable(value = AppConstants.ID) long id) {

		return new ResponseEntity<Set<PageDto>>(bookService.getAllPagesFromBookById(id), HttpStatus.OK);
	}

}
