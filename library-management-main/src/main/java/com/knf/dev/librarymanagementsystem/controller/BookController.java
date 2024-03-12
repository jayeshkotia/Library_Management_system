package com.knf.dev.librarymanagementsystem.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knf.dev.librarymanagementsystem.entity.Author;
import com.knf.dev.librarymanagementsystem.entity.Book;
import com.knf.dev.librarymanagementsystem.entity.Category;
import com.knf.dev.librarymanagementsystem.entity.Publisher;
import com.knf.dev.librarymanagementsystem.service.AuthorService;
import com.knf.dev.librarymanagementsystem.service.BookService;
import com.knf.dev.librarymanagementsystem.service.CategoryService;
import com.knf.dev.librarymanagementsystem.service.PublisherService;

@Controller
public class BookController {

	final BookService bookService;
	final AuthorService authorService;
	final CategoryService categoryService;
	final PublisherService publisherService;

	

	public BookController(PublisherService publisherService, CategoryService categoryService, BookService bookService,
			AuthorService authorService) {
		this.authorService = authorService;
		this.bookService = bookService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@RequestMapping({ "/books", "/" })
	public String findAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);

		var bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("books", bookPage);

		var totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-books";
	}

	@RequestMapping("/searchBook")
	public String searchBook(@Param("keyword") String keyword, Model model) {

		model.addAttribute("books", bookService.searchBooks(keyword));
		model.addAttribute("keyword", keyword);
		return "list-books";
	}

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) {
		Book b=bookService.findBookById(id);
		if(b==null) {
			String a="Book with this id doesn't exists";
			model.addAttribute("msg", a);
			return "error";
		}
		model.addAttribute("book", b);
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Book book, Model model) {

		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "add-book";
	}
	
	@RequestMapping("/add-book")
	public String createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}
		
	
		if(book.getAuthors().isEmpty()== true) {
			Author a= new Author("Default Author", "NA");
			book.setAuthors(new HashSet<>(Arrays.asList(a)));
		}
		if(book.getCategories().isEmpty()== true) {
			Category c=new Category("Default Category");
			book.setCategories(new HashSet<>(Arrays.asList(c)));
		}
		if(book.getPublishers().isEmpty()== true) {
			Publisher p=new Publisher("Default Publisher");
			book.setPublishers(new HashSet<>(Arrays.asList(p)));
		}
		
		List<Book> a = bookService.findAllBooks();

		for (Book temp : a) {
			if (temp.getIsbn().equals(book.getIsbn())) {
				Long id = temp.getId();
				book.setId(id);
				bookService.updateBook(book);
				model.addAttribute("book", bookService.findAllBooks());
				return "redirect:/books";
			}
		}


		bookService.createBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("book", bookService.findBookById(id));
		return "update-book";
	}

	@RequestMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setId(id);
			return "update-book";
		}

		bookService.updateBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		
		Book b=bookService.findBookById(id);
		if(b==null) {
			String a="Book with this id doesn't exists";
			model.addAttribute("msg", a);
			return "error";
		}
		
		bookService.deleteBook(id);

		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

}