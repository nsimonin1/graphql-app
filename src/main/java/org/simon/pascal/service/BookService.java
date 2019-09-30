/**
 *
 */
package org.simon.pascal.service;

import java.util.List;
import java.util.Map;

import org.simon.pascal.dto.BookInput;
import org.simon.pascal.entities.AuteurEntity;
import org.simon.pascal.entities.BookEntity;
import org.simon.pascal.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	private final ObjectMapper mapper = new ObjectMapper();

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

	public DataFetcher<List<BookEntity>> getAll() {
		return dataFetchingEnvironment->{
			return bookRepository.findAll();
		};
	}

	public DataFetcher<BookEntity> createBook() {
		return dataFetchingEnvironment->{
			final Map<String, Object> args=dataFetchingEnvironment.getArguments();
			final String json=mapper.writeValueAsString(args.get("data"));
			final BookInput input=mapper.readValue(json, BookInput.class);
			return new BookEntity();
		};
	}



}
