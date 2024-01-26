package com.assessment.LibraryManagementSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.assessment.LibraryManagementSystem.model.Patron;

@Mapper
public interface PatronMapper {

	@Select("SELECT * FROM patrons")
	List<Patron> findAllPatrons();

	@Select("SELECT * FROM patrons WHERE id = #{id}")
	Patron findPatronById(@Param("id") int id);

	@Insert("INSERT INTO patrons(name, contactInformation) VALUES(#{name}, #{contactInformation})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertPatron(Patron patron);

	@Update("UPDATE patrons SET name = #{name}, contactInformation = #{contactInformation} WHERE id = #{id}")
	void updatePatron(Patron patron);

	@Delete("DELETE FROM patrons WHERE id = #{id}")
	void deletePatron(@Param("id") int id);
}
