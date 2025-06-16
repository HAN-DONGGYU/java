package bookjava.GUI.DB;

import bookjava.GUI.DB.DAO.BookDAO;
import bookjava.GUI.DB.DAO.LoanDAO;
import bookjava.GUI.DB.DAO.LoanInfoDAO;
import bookjava.GUI.DB.DAO.MemberDAO;

public class DAOManager {
    private static final DAOManager instance = new DAOManager();

    private final static BookDAO bookDAO = new BookDAO();
    private final static LoanDAO loanDAO = new LoanDAO();
    private final static MemberDAO memberDAO = new MemberDAO();
    
    private final static LoanInfoDAO loanInfoDAO = new LoanInfoDAO();

    private DAOManager() {}  // 외부에서 생성 불가

    public static DAOManager getInstance() {
        return instance;
    }

    public static BookDAO getBookDAO() { return bookDAO; }
    public static LoanDAO getLoanDAO() { return loanDAO; }
    public static MemberDAO getMemberDAO() { return memberDAO; }
    
    public static LoanInfoDAO getLoanInfoDAO() { return loanInfoDAO; }
}


