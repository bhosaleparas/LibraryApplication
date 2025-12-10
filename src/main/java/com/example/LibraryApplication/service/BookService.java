package com.example.LibraryApplication.service;




import com.example.LibraryApplication.dto.BookDTO;
import com.example.LibraryApplication.entity.Book;
import com.example.LibraryApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setAvailable(bookDTO.isAvailable());

        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }



    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public List<BookDTO> getAvailableBooks() {
        return bookRepository.findByAvailableTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDTO(book);
    }



    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setAvailable(bookDTO.isAvailable());

        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }



    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    public List<BookDTO> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public List<BookDTO> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable());
    }
}
