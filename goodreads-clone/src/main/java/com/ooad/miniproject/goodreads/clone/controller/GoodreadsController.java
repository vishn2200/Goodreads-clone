package com.ooad.miniproject.goodreads.clone.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.ooad.miniproject.goodreads.clone.entity.User;
import com.ooad.miniproject.goodreads.clone.service.UserService;
import com.ooad.miniproject.goodreads.clone.entity.BookClub;
import com.ooad.miniproject.goodreads.clone.service.BookClubService;
import com.ooad.miniproject.goodreads.clone.entity.Book;
import com.ooad.miniproject.goodreads.clone.service.BookService;
import com.ooad.miniproject.goodreads.clone.entity.ReadingList;
import com.ooad.miniproject.goodreads.clone.entity.ReadingStatus;
import com.ooad.miniproject.goodreads.clone.entity.ReadingListBook;
import com.ooad.miniproject.goodreads.clone.service.ReadingListService;

import java.util.*;

@Controller
@RequestMapping(value = "/api/goodreads", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class GoodreadsController {

    private final UserService userService;
    private final BookClubService bookClubService;
    private final BookService bookService;
    private final ReadingListService readingListService;
    private boolean authenticated = false; // Initialize as false

    public GoodreadsController(UserService userService, BookClubService bookClubService, BookService bookService, ReadingListService readingListService) {
        this.userService = userService;
        this.bookClubService = bookClubService;
        this.bookService = bookService;
        this.readingListService = readingListService;

    }

    @GetMapping("/")
    public String apiHome() {
        return "home";
    }

    @GetMapping("/signup") // Handle GET requests for /signup
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User()); // Add an empty user object to the model
        return "signup"; // Return the name of the HTML template for the signup page
    }

    @PostMapping("/register") // Handle POST requests for /signup
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/api/goodreads/login"; // Redirect to the login page after signing up
    }

    @GetMapping("/error")
    public String error(@RequestParam String param) {
        return "error";
    }

    @GetMapping("/login") // Handle GET requests for /signup
    public String showLoginForm(Model model) {
        //model.addAttribute("user", new User()); // Add an empty user object to the model
        return "login"; // Return the name of the HTML template for the signup page
    }

    @PostMapping("/loginSuccess")
    public String checkUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.checkUser(username, password)) {
            authenticated = true; // Set authenticated to true upon successful login
            return "redirect:/api/goodreads/dashboard";
        } else {
            return "login"; // Return to login page if user credentials are invalid
        }
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model) {
        return "forgot-password"; // Return the HTML template for the forgot password page
    }

    @PostMapping("/reset-password") // Handle POST requests for /signup
    public String updateUser(@RequestParam String username, @RequestParam(value="password", required=false) String password) {
        User updatedUser = new User();
        updatedUser.setUsername(username);
        updatedUser.setPassword(password);
        //updatedUser.setPassword(password1);
        userService.updateUser(updatedUser);
        return "redirect:/api/goodreads/login"; // Redirect to the login page after signing up
    }

    @PostMapping("/delete-account")
    public String deleteAccount(@RequestParam String username) {
        userService.deleteUser(username);
        return "redirect:/api/goodreads/"; // Redirect to the logout page after deleting the account
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Pass the authenticated variable to the Thymeleaf template
        model.addAttribute("authenticated", authenticated);
        return "dashboard"; // Return the name of the HTML template for the dashboard page
    }

    //////////////////////////////////////////////

    @GetMapping("/viewBookClub") // Handle GET requests for viewing all book clubs
    public String viewBookClubs(Model model) {
        // Assuming you have a method in UserService to get all book clubs
        List<BookClub> bookClubs = bookClubService.viewBookClub();
        model.addAttribute("bookClubs", bookClubs);
        return "viewBookClub"; // Return the name of the HTML template for viewing book clubs
    }

    @GetMapping("/createBookClub") // Handle GET requests for showing the form to create a new book club
    public String showCreateBookClubForm(Model model) {
        model.addAttribute("bookClub", new BookClub()); // Add an empty book club object to the model
        return "createBookClub"; // Return the name of the HTML template for the book club creation form
    }

    @PostMapping("/createBookClubSuccess") // Handle POST requests for creating a new book club
    public String createBookClub(@ModelAttribute BookClub bookClub) {
        bookClubService.createBookClub(bookClub);
        return "redirect:/api/goodreads/viewBookClub"; // Redirect to the dashboard after creating the book club
    }

    @GetMapping("/joinBookClub") // Handle GET requests for showing the form to join a book club
    public String showJoinBookClubForm(Model model) {
        model.addAttribute("bookClub", new BookClub()); // Add an empty book club object to the model
        return "joinBookClub"; // Return the name of the HTML template for the book club joining form
    }

    @PostMapping("/joinBookClubSuccess") // Handle POST requests for joining a book club
    public String joinBookClub(@RequestParam("clubId") Long clubId, @RequestParam("userId") Long userId) {
        // Assuming you have a method in the bookClubService to join a book club by clubId
        bookClubService.joinBookClub(userId, clubId);
        return "redirect:/api/goodreads/dashboard"; // Redirect to the dashboard after joining the book club
    }

    /////////////////////////////////////////////////

    @GetMapping("/search")
    public String searchBooks(@RequestParam String query, Model model) {
        List<Book> searchResults = bookService.searchBooks(query);
        model.addAttribute("searchResults", searchResults);
        return "searchResults"; // Return the HTML template for displaying search results
    }

    //////////////////////////////////////////////////////

    @GetMapping("/reading-list")
    public String showReadingListForm() {
        return "readingListForm"; // Return the HTML template for the reading list form
    }

    @PostMapping("/viewReadingList")
    public String viewReadingList(Model model, @RequestParam("readingListId") Long readingListId) {
        List<ReadingListBook> readingList = readingListService.getReadingListBooks(readingListId);
        model.addAttribute("readingList", readingList);
        model.addAttribute("readingListId", readingListId);
        return "viewReadingList"; // Return the name of the HTML template for viewing the reading list
    }

    @GetMapping("/addBook/{readingListId}")
    public String showAddBookForm(Model model, @PathVariable("readingListId") Long readingListId) {
        model.addAttribute("readingListId", readingListId);
        return "addToReadingList"; // Return the HTML template for adding a book
    }

    @PostMapping("/addBookSuccess/{readingListId}")
    public String addBookToReadingList(@PathVariable("readingListId") Long readingListId,
                                       @RequestParam("book_id") Long bookId,
                                       @RequestParam("title") String title,
                                       @RequestParam("status") ReadingStatus status,
                                       @RequestParam("rating") Double rating) {
        readingListService.addToReadingList(readingListId, bookId, title, status, rating);
        //return "redirect:/api/goodreads/viewReadingList?userId=" + readingListId; // Redirect back to the reading list page
        return "redirect:/api/goodreads/dashboard";
    }

    @GetMapping("/deleteBook/{readingListId}")
    public String showDeleteBookForm(Model model, @PathVariable("readingListId") Long readingListId) {
        model.addAttribute("readingListId", readingListId);
        return "removeBookFromReadingList"; // Return the HTML template for adding a book
    }

    @PostMapping("/deleteBookSuccess/{readingListId}")
    public String removeBookFromReadingList(@PathVariable("readingListId") Long readingListId, @RequestParam("title") String title) {
        readingListService.deleteFromReadingList(readingListId, title);
        return "redirect:/api/goodreads/dashboard"; // Redirect back to the reading list page
    }
/*
    @PostMapping("/update-book-status")
    public String updateBookStatus(@RequestParam Long bookId, @RequestParam ReadingStatus status, Principal principal) {
        // Assuming you have a method in ReadingListService to update the status of a book in the reading list
        String username = principal.getName();
        Long readingListId = readingListService.getCurrentUserReadingListId(username);
        readingListService.setBookStatusAndRating(readingListId, bookId, status, null); // Pass null for rating as it is not being updated
        return "redirect:/api/goodreads/reading-list"; // Redirect back to the reading list page after updating the status
    }

    @PostMapping("/update-book-rating")
    public String updateBookRating(@RequestParam Long bookId, @RequestParam Double rating, Principal principal) {
        // Assuming you have a method in ReadingListService to update the rating of a book in the reading list
        String username = principal.getName();
        Long readingListId = readingListService.getCurrentUserReadingListId(username);
        readingListService.setBookStatusAndRating(readingListId, bookId, null, rating); // Pass null for status as it is not being updated
        return "redirect:/api/goodreads/reading-list"; // Redirect back to the reading list page after updating the rating
    }
*/
    


}

