// package com.quickstartdev.librarymanagementsystem;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.junit.jupiter.api.MethodOrderer;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestMethodOrder;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;

// import com.knf.dev.librarymanagementsystem.Application;
// import com.knf.dev.librarymanagementsystem.entity.Author;
// import com.knf.dev.librarymanagementsystem.entity.Book;
// import com.knf.dev.librarymanagementsystem.entity.Category;
// import com.knf.dev.librarymanagementsystem.entity.Publisher;
// import com.knf.dev.librarymanagementsystem.service.impl.BookServiceImpl;

// @ContextConfiguration(classes = Application.class)
// @SpringBootTest
// @AutoConfigureMockMvc
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// class ApplicationTests {
	
// 	@Autowired
// 	BookServiceImpl bookService;
	
// 	@Autowired
// 	private MockMvc mockMvc;

// 	@Test
// 	void contextLoads() {
// 	}
	
// 	@Test
// 	@Order(1)
// 	public void showBookByIdTest()
// 	{
// 		Book book1= bookService.findBookById(Long.valueOf(1));
		
// 		assertEquals("AP1287", book1.getIsbn());
// 	}
	
// 	@Test
// 	@Order(2)
// 	public void searchBookByNameTest()
// 	{
		
// 		assertEquals("Spring in Action ", bookService.searchBooks("Spring in Action").get(0).getName());
// 	}
	
// 	@Test
// 	@Order(3)
// 	public void createBookTest()
// 	{
// 		Book book= new Book("BA8698", "JUnit Test Cases", "JHJG12389", "Book description");
// 		book.addAuthors(new Author("Swapnil", "dummy description"));
// 		book.addCategories(new Category("Dummy "));
// 		book.addPublishers(new Publisher("Dummy "));
// 		bookService.createBook(book);
		
// 		assertEquals(4, bookService.findAllBooks().size());
// 	}
	
// 	@Test
// 	@Order(4)
// 	public void loginPageTest()
// 	{
// 		try 
// 		{
// 			ResultActions response = mockMvc.perform(get("/login"));
// 			response.andExpect(status().isOk()).
// 			andDo(print());
// 			} 
// 		catch (Exception e) 
// 		{
// 			e.printStackTrace();
// 			}
// 		}

// }
