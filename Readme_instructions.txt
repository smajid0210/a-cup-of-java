               ============ Instructions for Running the Project ================

1. I have used Spring Boot as Backend and Thymeleaf as Frontend. So, any Standard JAVA IDE (Eclipse, Intellij) should be fine
   to run the project.

2. Separate users for PostgreSQL and MySQL Database needs to be created with following credentials-

            PostGres-    Username: postgres   Password: 1234
            MySQL-       Username: mysql      Password: 1234

3. In both Postgres and MySQL, a new database named "Blogproject" needs to be created. Also, a new Table named "blogs" has to be 
   created.

4. The "blogs" table will have the following columns

            blogid INT Primary Key, Auto Increment
            title VARCHAR (500)
            description VARCHAR(3000)
            authorid INT
            CREATED_AT Timestamp, Current_Timestamp
            UPDATED_AT TImestamp, Current_Timestamp

5. For switching between Databases, go to the "com.example.entity" package, Setglobals.Java file. From there, update the dbName
   variable as "postgres" or "mysql".

6. A CSV File Template named "Book1.csv" is also added for checking the Bulk Blog Upload Feature.

6. For any queries regarding running the project, feel free to contact at: shadmanai068@gmail.com. Happy Reviewing!!! :D :D 