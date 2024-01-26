package com.assessment.LibraryManagementSystem.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.assessment.LibraryManagementSystem.model.BorrowingRecord;

@Mapper
public interface BorrowingRecordMapper {

	@Select("SELECT * FROM borrowing_records WHERE book_id = #{bookId} AND patron_id = #{patronId} AND return_date IS NULL")
	BorrowingRecord findActiveBorrowingRecord(@Param("bookId") int bookId, @Param("patronId") int patronId);

	@Select("SELECT * FROM borrowing_records WHERE id = #{id}")
	Optional<BorrowingRecord> findBorrowingRecordById(@Param("id") int id);

	@Select("SELECT * FROM borrowing_records")
	List<BorrowingRecord> findAllBorrowingRecords();

	@Insert("INSERT INTO borrowing_records(book_id, patron_id, borrowed_date) VALUES(#{bookId}, #{patronId}, #{borrowedDate})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertBorrowingRecord(BorrowingRecord record);

	@Update("UPDATE borrowing_records SET return_date = #{returnDate} WHERE id = #{id}")
	void updateBorrowingRecord(BorrowingRecord record);
}
