package bookjava.GUI.Book;


public class Book {
    private String title, author, publisher;
    private String bookNum;

    public Book(int count, String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookNum = String.format("B%05d", count++);
    }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public String getBookNum() { return bookNum; }

    @Override
    public String toString() {
        return String.format("도서번호: %s | 제목: %s | 저자: %s | 출판사: %s", bookNum, title, author, publisher);
    }
}