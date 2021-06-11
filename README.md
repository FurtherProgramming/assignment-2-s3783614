# Arub Booking System

This project is a booking system made for Arub employees and admins. This project is built by Ashwin Venkita Subharaman,
student number *s3783614*. This project was built using JAVA, and a graphical toolkit of JavaFX.

This project is a booking application with full functionality, for two different types of users Admins and Employees.

**The features for Employees include:**
- Managing details
- Registration 
- Make a booking

**The features for Admins include:**
- Managing Lockdown conditions for any date 
- Managing bookings made by employees
- Managing account details
- Managing Employee
- Graphical Visual of all tables
- Generating Reports
   
## In-Depth explanation
This section explains all the features in detail.

### Employee Features

- Registration
   - This feature allows employees to register any and all details such as **First Name**, **Last Name**, **Username**,
     **password**, **Secret Question**, and **Secret Answer**.
- Managing details
    - This feature allows the employee to manage and update their own details such as :
       - Personal details, First name and Last name
       - Secret Question and Secret Answer
       - Password
- Make a booking
   - This feature allows the employee to select where they want to sit on a desk today onwards. The only rules that the 
    employees have to comply by is that: 
        - if a table has been booked it will be shown in red and no-one else can sit on it.
        - if a table is in orange colour then no-one can sit there as the table has been blocked due to social distancing 
            rules.
        - if another employee has booked a table but is waiting for admin approval then the table will be highlighted in blue.
        - if a table is free then it will be displayed in the colour green.
    
### Admin Features

- Managing Lockdown conditions for any date
   - Gives admins the ability to alter the restrictions for any given day.
- Managing bookings made by employees
   - Gives admins the ability to **Approve** or **Reject** any bookings made by the employees
- Managing account details
   - Gives the admin the ability to manage and update their own personal details.
- Managing Employee
   - Gives the admin the ability to **Edit**, **Add**, **Delete**, **Activate**, and **Deactivate** Employee.
        - Adding an employee as it suggests is adding a new employee when the admin is logged in
        - Editing an employee is specifically for the admins, if they had to alter any details of an employee.
        - Deleting an employee, this feature could be used to get rid of any employees.
        - Deactivating and Activating employees is basically where the admin is given the option to deactivate the employee
            or reactivate them if they were previously deactivated. Once an employee has been deactivated they cannot log into
            their account as deactivating completely banns them from using their account. The only way for them to use their account again would be to get an admin to reactivate it.
- Graphical Visual of all tables
   - This is used to view all tables and booked tables. The admin can view all tables booked and what lockdown condition it currently is on a given date. The only limitation to this 
     is that the admin cannot check which employee is sitting on the graphical view, if the admin wanted to check what employee is sitting where they could very easily head over to 
     the manage booking tab and check which employee is sitting where.
- Generating Reports
   - This feature includes reports for all employees, all booking , and any lockdown conditions changes made.

## Testing
This application had rigorous manual testing done to it, i.e., after each feature was implemented there were several tests done
in order to check if the program would crash. a few of tests is giving wrong values or passwords and checking the error message outputted.
Similarly, some booking tests that were done was checked what would happen if two employees tried to book the same seat twice. Or what error
message would be thrown if the employee tried to book a seat that they previously sat on.

## Structure of this Project 
This project follows the MVC structure that is, all the model class fall under the package Model. ALl the model
class are what interact with the database and retrieve information. Similar to the Models all the controllers are in
the controller Package. The controllers are responsible for controlling the way the user interacts with the data from the 
database, they are what communicate to the models. And finally the FXML files are all located in the View package
as this project uses JavaFX as mentioned before. The view is what helps display all information to the user using a GUI.

## To Run this Project
This project was built using IntelliJ and SQLite, to run this program clone the project form the GitHub repository 
and open it using an IDE of your choosing. Run main like you would run your regular programs.
To have access to any of the admin features Log in using the following users
- **Username** : `test`  **Password** : `test`
- **Username** : `test2`  **Password** : `password`

All the other users are available in the database provided. The alternative is registering your own employee.