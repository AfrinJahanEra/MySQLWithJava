# Book Buddy: Personalized Book Recommendation System

## Description
**Book Buddy** is a Java-based Book Recommendation System integrated with MySQL. It allows users to find book recommendations tailored to their interests and reading habits. The system stores user profiles, tracks reading history, and provides intelligent suggestions based on genre preferences, ratings, and popularity. It's designed to be user-friendly and scalable for handling large datasets.

---

## Features
- **User Profiles**: Register and manage user accounts.
- **Book Management**: Add, view, and search for books by genre, author, or title.
- **Recommendations**:
  - Genre-based suggestions.
  - Top-rated books.
  - "You May Also Like" feature based on similar readers.
- **Rating and Feedback**: Rate books and provide feedback for better recommendations.
- **Advanced Search**: Search by title, author, or keywords.
- **Analytics**:
  - Most popular books.
  - Trends based on reading history.

---

## Project Structure
```plaintext
BookBuddy/
├── src/
│   ├── Main.java
│   ├── BookManager.java
│   ├── UserManager.java
│   └── RecommendationEngine.java
├── resources/
│   └── db_schema.sql
├── config/
│   └── database.properties
└── README.md
```

---

## Installation and Setup

### Prerequisites
- Java Development Kit (JDK) 8 or later.
- MySQL Server.
- JDBC Driver for MySQL.

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/AfrinJahanEra/BookBuddy.git
   cd BookBuddy
   ```

2. **Setup the database**:
   - Import `resources/db_schema.sql` into your MySQL server.
   - Configure `config/database.properties` with your MySQL credentials.

3. **Compile and run**:
   ```bash
   javac -d bin src/*.java
   java -cp bin:lib/mysql-connector-java.jar Main
   ```

---

## Database Schema
### Tables
1. **Users**:
   - `id`: Primary Key
   - `name`, `email`, `password`, `preferences`

2. **Books**:
   - `id`: Primary Key
   - `title`, `author`, `genre`, `rating`, `popularity`

3. **Ratings**:
   - `id`: Primary Key
   - `user_id`, `book_id`, `rating`

4. **Recommendations**:
   - `user_id`, `book_id`, `reason`

---

## Usage
- **Register a User**: Create a new user profile.
- **Add Books**: Admin can add or update the book database.
- **Get Recommendations**: Login to view personalized book suggestions.
- **Rate a Book**: Improve the system by providing feedback on books you've read.

---

## Contributing
We welcome contributions! Please fork the repository and submit a pull request. Ensure your code adheres to the project's coding standards and includes tests.

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.

