package bookjava.GUI.BookJava;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import bookjava.GUI.DB.DAOManager;
import bookjava.GUI.DB.DTO.BookDTO;
import bookjava.GUI.DB.DTO.LoanInfoDTO;
import bookjava.GUI.DB.DTO.MemberDTO;

public class utils {

    public static String getExpirationStatus(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate inputDate = LocalDate.parse(dateStr, formatter);
        LocalDate targetDate = inputDate.plusDays(14);

        LocalDate today = LocalDate.now();
        long diff = Period.between(today, targetDate).getDays();

        if (diff == 0) {
            return "오늘 만료";
        } else if (diff > 0) {
            return diff + " 일 후 만료";
        } else {
            return Math.abs(diff) + " 일 전 만료";
        }
    }

    public static boolean isValidDateFormat(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Object[][] replaceDBDateFormat(Object[][] list)
    {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                Object cell = list[i][j];
                if ((cell instanceof String || cell instanceof LocalDate) && isValidDateFormat("" + cell, "yyyy-MM-dd")) {
                    list[i][j] = getExpirationStatus("" + cell);
                }
            }
        }

        return list;
    }
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Comparator<String> getDateStringComparator() {
        return (s1, s2) -> {
            LocalDate d1 = LocalDate.parse(s1, formatter);
            LocalDate d2 = LocalDate.parse(s2, formatter);
            return d1.compareTo(d2);
        };
    }

    public static Object[][] getDBLoanData()
    {
    	List<Object[]> loanlist = new ArrayList<>();
    	List<LoanInfoDTO> loaninfolist = DAOManager.getLoanInfoDAO().getLoanInfoWithBookTitle();
    	
    	for (LoanInfoDTO loan : loaninfolist) {
    		loanlist.add(new Object[] { 
    				loan.getLoanId(),
    				loan.getBookId(),
    				loan.getTitle(),
    				loan.getDueDate(),
    				loan.getMemberId()
    		});
    		
    	}
    	
    	return loanlist.toArray(new Object[0][]);

    }

    public static Object[][] getDBBookData()
    {
    	List<Object[]> list = new ArrayList<>();
    	List<BookDTO> booklist = DAOManager.getBookDAO().getBooks();
    	
    	for (BookDTO book : booklist) {
    		list.add(new Object[] { 
    				book.getId(),
    				book.getTitle(),
    				book.getAuthor(),
    				book.getPublisher()
    		});
    		
    	}
    	
    	return list.toArray(new Object[0][]);

    }

    public static Object[][] getDBMmberData()
    {
    	List<Object[]> list = new ArrayList<>();
    	List<MemberDTO> memberlist = DAOManager.getMemberDAO().getMembers();
    	
    	for (MemberDTO book : memberlist) {
    		list.add(new Object[] { 
    				book.getId(),
    				book.getName(),
    				book.getPhone(),
    				book.getEmail(),
    				book.getAddress()
    		});
    		
    	}
    	
    	return list.toArray(new Object[0][]);

    }
}

