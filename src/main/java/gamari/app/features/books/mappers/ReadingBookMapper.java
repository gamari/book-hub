package gamari.app.features.books.mappers;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import gamari.app.features.books.models.ReadingBook;

@Mapper
public interface ReadingBookMapper {

    @Select("SELECT COUNT(*) FROM reading_books WHERE book_id = #{bookId} AND user_id = #{userId}")
    int countReadingBooksByBookIdAndUser(@Param("bookId") String bookId, @Param("userId") String userId);

    @Select("SELECT COUNT(*) FROM reading_books WHERE book_id = #{bookId}")
    int countReadingBooksByBookId(String bookId);

    @Select("SELECT * FROM reading_books where id = #{id}")
    Optional<ReadingBook> findById(String id);

    @Select("SELECT * FROM reading_books where user_id = #{userId}")
    List<ReadingBook> findByUserId(String id);

    @Insert("INSERT INTO reading_books (id, user_id, book_id, title, is_reading, start_date, end_date) VALUES (#{id}, #{userId}, #{bookId}, #{title}, #{isReading}, #{startDate}, #{endDate})")
    void insert(ReadingBook readingBook);

    @Select("SELECT COUNT(*) FROM reading_books WHERE book_id = #{bookId} AND user_id = #{userId}")
    int countByBookIdAndUserId(@Param("bookId") String bookId, @Param("userId") String userId);

    @Delete("DELETE FROM reading_books WHERE user_id = #{userId} AND book_id = #{bookId}")
    void unregisterBook(@Param("userId") String userId, @Param("bookId") String bookId);
}
