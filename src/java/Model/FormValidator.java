/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Date;

/**
 *
 * @author minniemanZ
 * 
 * Class that is called on when forms are submitted to make sure the input is correct.
 */
public class FormValidator {
    
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    //Ensures that names match First name *space* Lastname
    private static String NAME_PATTERN = "[A-Z]{1}[a-z]+\\s[A-Z]{1}[a-z]+";
    //Ensures emails have a valid format of something @ something.anything
    private static String EMAIL_PATTERN = "([A-Z]|[a-z]|\\.|_)+@([a-z]|-)+\\.(([a-z]|-)+\\.?){1,}[^\\.]";
    //Makes sure the date is in the format dd/mm/yyyy
    private static String DATE_PATTERN = "\\d{1,2}/\\d{1,2}/\\d{4}";

    //This method is used for the enquiries form.
    //This string is built and then added to the url and determines what error messages to show the user if any
    //
    public static String getErrors(Enquiry enquiry) throws ParseException {
        String errors = "";
        errors += validateName(enquiry.getName());
        errors += validateEmail(enquiry.getEmail());
        errors += validateDates(enquiry.getStartDate(), enquiry.getEndDate());
        errors += validateMessage(enquiry.getMessage());
        return errors;
    }
    //Validates the name input. If the name is blank returns an error
    private static String validateName(String name) {
        if (name.equals("")) {
            return "&nameEmpty=";
        }
        //Also if the name does not match the pattern specified above it returns the error.
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(name).matches()) {
            return "&nameInvalid=";
        }
        return "";
    }
    //Validates the email address field, if the field is blank returns and error letting the user 
    //know they didn't enter an email address
    private static String validateEmail(String email) {
        if (email.equals("")) {
            return "&emailEmpty=";
        }
        //Parses what was entered into the regex pattern and makes sure that it is a valid email
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(email).matches()) {
            return "&emailInvalid=";
        }
        return "";
    }
    //Checks that there is at least some input in the date fields and errors if there is no input, lets the user know.
    private static String validateDates(String startDate, String endDate) throws ParseException {
        String dateErrors = "";
        boolean startDateValid = true;
        boolean endDateValid = true;
        if (startDate.equals("")) {
            dateErrors += "&startDateEmpty=";
            startDateValid = false;
        }
        if (endDate.equals("")) {
            dateErrors += "&endDateEmpty=";
            endDateValid = false;
        }
        //Checks that the date that was entered matches the correct pattern specified above. 
        //Lets the user know if the pattern is wrong if it does not match.
        //If both dates are valid it then calls checkDateRange to make sure the end date is after the start date.
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        if (!pattern.matcher(startDate).matches() && !startDate.equals("")) {
            dateErrors += "&startDateInvalid=";
            startDateValid = false;
        }
        if (!pattern.matcher(endDate).matches() && !endDate.equals("")) {
            dateErrors += "&endDateInvalid=";
            endDateValid = false;
        }
        if (startDateValid && endDateValid) {
            dateErrors += checkDateRange(startDate, endDate);
        }
        return dateErrors;
    }
    //Makes sure that the end date is after the start date.
    private static String checkDateRange(String startDate, String endDate) throws ParseException {
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        if (start.after(end)) {
            return "&dateRangeInvalid=";
        }
        return "";
    }
    //Throws an error back to the user if there is no message
    private static String validateMessage(String message) {
        if (message.equals("")) {
            return "&messageEmpty=";
        }
        return "";
    }
    //Same principle as the enquiries method but for the create new listing form
    public static String getListingErrors(Listing listing) {
        String errors = "";
        errors += validateListingType(listing.getType());
        errors += validateGuests(listing.getGuests());
        errors += validateDescription(listing.getDescription());
        errors += validateAddress(listing.getLocation());
        return errors;
    }
    //Makes sure that the listing type is not empty
    private static String validateListingType(String type) {
        if (type.equals("")) {
            return "&typeEmpty=";
        } else {
            return "";
        }
    }
    //Makes sure that the number of guests is at least one. 
    public static String validateGuests(int guests) {
        if (guests <= 0) {
            return "&guestsZero=";
        } else {
            return "";
        }
    }
    //Makes sure the description of the property is not empty
    public static String validateDescription(String description) {
        if (description.equals("")) {
            return "&descriptionEmpty=";
        } else {
            return "";
        }
    }
    //makes sure that there is a value in every String and a value that is >0 for the integer fields in the address. 
    //If there isn't it returns an error asking the user to use the auto complete address search.
    private static String validateAddress(Location location) {
        if (location.getNumber() <= 0) {
            return "&noAutoComplete=";
        } else if (location.getStreet().equals("")) {
            return "&noAutoComplete=";
        } else if (location.getSuburb().equals("")) {
            return "&noAutoComplete=";
        } else if (location.getState().equals("")) {
            return "&noAutoComplete=";
        } else if (location.getPostcode() <= 0) {
            return "&noAutoComplete=";
        } else {
            return "";
        }
    }
    //Used when creating new users
    //Same concept as other methods, appends error messages to the url to implement the error codes back to the user
    public static String getErrors(User user, String confirmPassword) {
        String errors = "";
        errors += validateUsername(user.getUsername());
        errors += validateName(user.getName());
        errors += validateEmail(user.getEmail());
        errors += validatePassword(user.getPassword(), confirmPassword);
        return errors;
    }
    //makes sure tha the username is not empty
    private static String validateUsername(String username) {
        if (username.equals("")) {
            return "&userNameEmpty=";
        }
        return "";
    }
    //makes sure that the password is the same and not empty. Throws different error messages if passwords are empty, compared to different.
    private static String validatePassword(String password, String confirmPassword) {
        String passwordErrors = "";
        if (password.equals("")) {
            passwordErrors += "&passwordEmpty=";
        }
        if (confirmPassword.equals("")) {
            passwordErrors += "&confirmPasswordEmpty=";
        }
        if (!password.equals(confirmPassword)) {
            passwordErrors += "&differentPasswords=";
        }
        return passwordErrors;
    }
    //When a person clicks login without entering any details they get an error letting them know they didn't have any input
    public static String getErrors(String email, String password) {
        String loginErrors = "";
        if(email.equals("")) {
            loginErrors += "&loginEmailEmpty=";
        }
        if(password.equals("")) {
            loginErrors += "&loginPasswordEmpty=";
        }
        return loginErrors;
    }
}
