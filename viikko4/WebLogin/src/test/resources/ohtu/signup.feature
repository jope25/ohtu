Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation successful with correct username and password
    Given new user is selected
    When valid username "asa" and valid matching passwords "password1" are given
    Then user is created

  Scenario: creation fails with too short username and valid password
    Given new user is selected
    When too short username "bf" and valid matching passwords "password1" are given
    Then user is not created and error "username should have at least 3 characters" is reported

  Scenario: creation fails with correct username and too short password
    Given new user is selected
    When valid username "asa" and too short matching passwords "short1" are given
    Then user is not created and error "password should have at least 8 characters" is reported

  Scenario: creation fails with correct username and password consisting of letters
    Given new user is selected
    When valid username "asa" and letter-only matching passwords "onlyletters" are given
    Then user is not created and error "password can not contain only letters" is reported

  Scenario: creation fails with already taken username and valid password
    Given new user is selected
    When taken username "jukka" and valid matching passwords "password1" are given
    Then user is not created and error "username is already taken" is reported

  Scenario: creation fails when password and password confirmation do not match
    Given new user is selected
    When valid username "asa" and valid nonmatching passwords "password1" and "password2" are given
    Then user is not created and error "password and password confirmation do not match" is reported
