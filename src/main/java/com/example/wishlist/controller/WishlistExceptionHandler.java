package com.example.wishlist.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WishlistExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArguemnt(IllegalArgumentException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

}
