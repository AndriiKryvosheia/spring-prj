package com.oched.booksprj.controllers;

import com.oched.booksprj.requests.AddBookRequest;
import com.oched.booksprj.requests.DeleteBookRequest;
import com.oched.booksprj.requests.UpdateBookRequest;
import com.oched.booksprj.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/add")
    private String getAddBookPage() {
        return "/books/addBook";
    }

    @PostMapping(value = "/add")
    public String addNewBook(final @ModelAttribute("request") AddBookRequest request) {
        this.bookService.addBook(request);

        return "redirect:/books/all";
    }

    @GetMapping(value = "/update")
    private String getUpdateBookPage() {
        return "/books/updateBook";
    }

    @PostMapping(value = "/update")
    public String updateBook(final @ModelAttribute("request") UpdateBookRequest request) {
        this.bookService.updateBook(request);

        return "redirect:/books/all";
    }

    @GetMapping(value = "/delete")
    private String getDeleteBookPage() {
        return "/books/deleteBook";
    }

    @PostMapping(value = "/delete")
    public String deleteBook(final @ModelAttribute("request") DeleteBookRequest request) {
        this.bookService.deleteBook(request);

        return "redirect:/books/all";
    }

    @GetMapping("/all")
    public ModelAndView getAllBooks(final ModelAndView modelAndView) {
        modelAndView.addObject("list", this.bookService.getAll());
        modelAndView.setViewName("/books/allBooks");

        return modelAndView;
    }
}
