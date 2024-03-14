package booksearch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import book.search.Book;
import book.search.Library;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{1,2}) ([A-Za-z]+) ([0-9]{4})")
    public Date iso8601Date(String day, String month, String year) throws ParseException, java.text.ParseException {
        String dateString = day + " " + month + " " + year;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        return sdf.parse(dateString);
    }

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addNewBook(final String title, final String author, final Date published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @Given("the following books:")
    public void addNewBooks(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        for (List<String> columns : rows) {
            String title = columns.get(0);
            String author = columns.get(1);
            Date published = null;
            try {
                published = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).parse(columns.get(2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Book book = new Book(title, author, published);
            library.addBook(book);
        }
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void setSearchParameters(final Date from, final Date to) {
        result = library.findBooks(from, to);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertThat(result.size(), equalTo(booksFound));
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }

    @And("another book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addAnotherBook(final String title, final String author, final Date published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books with the title {string}")
    public void setSearchParametersByTitle(final String title) {
        result = library.findBooksByTitle(title);
    }

    @When("the customer searches for books written by {string}")
    public void setSearchParametersByAuthor(final String author) {
        result = library.findBooksByAuthor(author);
    }
}