Feature: Bus Booking
    Scenario: Purchase a bus ticket and check reservation
        Given the user is on the main page
        When the user selects 'Lisboa' as the departure city
        And the user selects 'Porto' as the arrival city
        And the user selects '2024-05-05' as the departure date
        And the user clicks on the Search button
        Then the user is taken to the results page
        When the user selects the first available bus, with the id 'BUS007' and costing '20.0'
        And the user clicks on the Buy button
        Then the user is taken to the checkout page
        When the user fills in the required information with:
            | Name      | John Smith Carabina           |
            | Address   | minha casa                    |
            | City      | Aveiro                        |
            | State     | Portugal                      |
            | Zip Code  | 3800-000                      |
            | Email     | john.smith.carabina@gmail.com |
            | Phone     | 123456789                     |
            | Card Type | visa                          |
            | Card Nr   | 4111111111111111              |
            | Exp Month | 12                            |
            | Exp Year  | 2024                          |

        And the user clicks on the Pay Now button
        Then the user is taken to the purchase successfull page
        And the user is given a reservation code
        When the user clicks on the Check Reservation button
        Then the user is taken to the reservation page
        When the user paste the reservation code in the input field
        And the user clicks on the Check button
        Then the user sees the reservation details