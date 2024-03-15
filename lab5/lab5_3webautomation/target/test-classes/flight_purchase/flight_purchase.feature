Feature: Flight Purchase
    Scenario: Purchase a flight
        Given the user is on the Simple Travel Agency homepage
        When the user selects 'Boston' as the departure city
        And the user selects 'London' as the destination city
        And the user clicks on Find Flights
        Then the user is taken to the flight reservation page
        When the user selects a flight from 'Virgin America' number '43' with a price of '$472.56'
        And the user clicks on Choose This Flight
        Then the user is taken to the flight purchase page
        When the user fills out the flight purchase form with:
            | Name            | Jake                |
            | Address         | minha casa          |
            | City            | Aveiro              |
            | State           | Portugal            |
            | Zip Code        | 3800-180            |
            | Card Type       | Visa                |
            | Card Number     | 2939482842          |
            | Card Exp. Month | 11                  |
            | Card Exp. Year  | 2024                |
            | Name on Card    | JOHN SMITH CARABINA |
            | Remember Me     | Yes                 |
        And the user clicks on Purchase Flight
        Then the user is taken to the flight purchase confirmation page