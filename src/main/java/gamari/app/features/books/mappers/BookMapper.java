package gamari.app.features.books.mappers;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import gamari.app.features.books.models.Book;

@Mapper
public interface BookMapper {

    @Select("SELECT * FROM books WHERE id = #{id}")
    Optional<Book> findBookById(@Param("id") String id);

    @Select("SELECT * FROM books WHERE isbn10 = #{isbn10} OR isbn13 = #{isbn13}")
    Optional<Book> findBookByIsbn(@Param("isbn10") String isbn10, @Param("isbn13") String isbn13);

    @Select("SELECT * FROM books WHERE title LIKE #{keyword}")
    List<Book> findByTitleContaining(@Param("keyword") String keyword);

    @Insert("INSERT INTO books (id, title, isbn10, isbn13, author, published_date, genre, summary, thumbnail) VALUES (#{id}, #{title}, #{isbn10}, #{isbn13}, #{author}, #{publishedDate}, #{genre}, #{summary}, #{thumbnail})")
    int save(Book book);
}
