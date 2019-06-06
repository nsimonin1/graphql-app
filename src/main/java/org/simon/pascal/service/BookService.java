/**
 *
 */
package org.simon.pascal.service;

import java.util.List;

import org.simon.pascal.entities.AuteurEntity;
import org.simon.pascal.entities.BookEntity;
import org.simon.pascal.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graphql.schema.DataFetcher;

/**
 * The Class BookService.
 *
 * @author nsimonin1
 */
@Service
@Transactional(readOnly = true)
public class BookService {

	/** The book repository. */
	public final BookRepository bookRepository;

	/**
	 * Instantiates a new book service.
	 *
	 * @param bookRepository the book repository
	 */
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<BookEntity> findAll() {
		return bookRepository.findAll();
	}

	/**
	 * Gets the book by id data fetcher.
	 *
	 * @return the book by id data fetcher
	 */
	public DataFetcher<BookEntity> getBookByIdDataFetcher() {
		return dataFetchingEnvironment -> {
			final String code=dataFetchingEnvironment.getArgument("code");
			return bookRepository.findByCode(code).orElse(null);
		};
	}

	public DataFetcher<AuteurEntity> getAuthorDataFetcher() {
		return dataFetchingEnvironment->{
			final BookEntity data=dataFetchingEnvironment.getSource();
			return data.getAuthor();
		};
	}



}
