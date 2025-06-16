package bookjava.GUI.Book;

import java.util.ArrayList;
import java.util.List;

public class BookManagement {
    private static List<Book> bookList = new ArrayList<>();
    private static List<ChangeListener> listeners = new ArrayList<>();

    public static void addBook(Book book) {
        bookList.add(book);
        notifyListeners();
    }

    public static List<Book> getBooks() {
        return new ArrayList<>(bookList);
    }

    public interface ChangeListener {
        void onChange();
    }

    public static void addChangeListener(ChangeListener l) {
        listeners.add(l);
    }

    static void notifyListeners() {
        for (ChangeListener l : listeners) {
            l.onChange();
        }
    }
    
    public static void clearBooks() {
        bookList.clear();
        notifyListeners();
    }
}
