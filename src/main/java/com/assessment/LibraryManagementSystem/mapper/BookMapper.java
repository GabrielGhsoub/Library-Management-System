package com.assessment.LibraryManagementSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.assessment.LibraryManagementSystem.model.Book;

@Mapper
public interface BookMapper {

	@Select("SELECT * FROM books")
	List<Book> findAllBooks();

	@Select("SELECT * FROM books WHERE id = #{id}")
	Book findBookById(@Param("id") int id);

	@Insert("INSERT INTO books(title, author, publicationYear, isbn) VALUES(#{title}, #{author}, #{publicationYear}, #{isbn})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertBook(Book book);

	@Update("UPDATE books SET title = #{title}, author = #{author}, publicationYear = #{publicationYear}, isbn = #{isbn} WHERE id = #{id}")
	void updateBook(Book book);

	@Delete("DELETE FROM books WHERE id = #{id}")
	void deleteBook(@Param("id") int id);

	@Select("SELECT * FROM books WHERE isbn = #{isbn}")
	Book findBookByIsbn(@Param("isbn") String isbn);
}
