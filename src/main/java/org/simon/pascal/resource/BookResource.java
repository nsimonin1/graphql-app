/**
 *
 */
package org.simon.pascal.resource;

import java.util.List;

import org.simon.pascal.entities.BookEntity;
import org.simon.pascal.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class BookResource.
 *
 * @author nsimonin1
 */
@RestController
@RequestMapping("/api")
public class BookResource {

	/** The book service. */
	private final BookService bookService;

	/**
	 * Instantiates a new book resource.
	 *
	 * @param bookService the book service
	 */
	public BookResource(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@GetMapping("/books")
	public List<BookEntity> findAll(){
		return bookService.findAll();
	}


}
