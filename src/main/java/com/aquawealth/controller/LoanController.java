package com.aquawealth.controller;

import com.aquawealth.model.Loan;
import com.aquawealth.model.LoanPayment;
import com.aquawealth.model.User;
import com.aquawealth.service.LoanPaymentService;
import com.aquawealth.service.LoanService;
import com.aquawealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import com.aquawealth.service.LoanPaymentService;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @GetMapping("/apply")
    public String showLoanForm(Model model) {
        model.addAttribute("loan", new Loan());
        return "apply-loan";
    }
    @PostMapping("/apply")
    public String applyForLoan(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String governmentId,
            @ModelAttribute Loan loan,
            Model model) {
        try {
            // Check if user exists, else create one
            User existingUser = userService.getUserByGovernmentId(governmentId);
            Optional<User> userOptional = Optional.ofNullable(existingUser);
            User user = userOptional.orElseGet(() -> {
                User newUser = new User();
                newUser.setName(name);
                newUser.setEmail(email);
                newUser.setGovernmentId(governmentId);
                return userService.createUser(newUser);
            });


            // Link user to loan (Fixed)
            loan.setUser(user);

            // Save loan
            Loan savedLoan = loanService.applyForLoan(loan);
            model.addAttribute("loan", savedLoan);

            return "loan-success"; // JSP page
        } catch (Exception e) {
            e.printStackTrace(); // Log error in console
            model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            return "error"; // Create error.jsp to display the error
        }
    }



//    @PostMapping("/apply")
//    public String applyForLoan(
//            @RequestParam String name,
//            @RequestParam String email,
//            @RequestParam String governmentId,
//            @ModelAttribute Loan loan,
//            Model model) {
//
//        // Check if the user exists; if not, create one
//        Optional<User> existingUser = userService.getUserByGovernmentId(governmentId);
//        User user;
//        if (existingUser.isPresent()) {
//            user = existingUser.get();
//        } else {
//            user = new User();
//            user.setName(name);
//            user.setEmail(email);
//            user.setGovernmentId(governmentId);
//            user = userService.createUser(user);
//        }
//
//        // Link loan to the user (Fixed: Now uses @ManyToOne correctly)
//        loan.setUser(user);
//
//        // Save the loan
//        Loan savedLoan = loanService.applyForLoan(loan);
//
//        model.addAttribute("loan", savedLoan);
//        return "loan-success"; // Return JSP view
//    }

    @Controller
    @RequestMapping("/payments")
    public static class LoanPaymentController {

        @Autowired
        private LoanPaymentService loanPaymentService;

        @GetMapping("/make")
        public String showPaymentForm(){
            return "make-payment";
        }
        @PostMapping("/process-payment")
        public String makePayment(
                @RequestParam Long loanId,
                @RequestParam BigDecimal amount,
                @RequestParam String paymentType,
                Model model
        ) {
            LoanPayment payment = loanPaymentService.makePayment(loanId, amount, paymentType);
            model.addAttribute("payment", payment);
            return "payment-success";    }
    }
}
