# Student Grade Management System

## Overview

A command-line Java application for managing student academic records. The system allows adding students, viewing all records, searching by ID, calculating and displaying results, deleting records, sorting students by percentage, generating class statistics, and persisting data to a file with automatic background saving.

## Features

- Add student (with duplicate ID prevention)
- View all students
- Search student by ID
- View individual student result (marks, total, percentage, grade)
- Delete student
- Sort students by percentage (descending)
- Class statistics (average, highest, lowest)
- File persistence (data survives restarts)
- Automatic background saving every 30 seconds

## Technologies

- Java 17+
- Collections Framework (ArrayList, Iterator, Comparator)
- Custom Exception Handling
- File I/O (BufferedReader / BufferedWriter)
- Multithreading (Runnable, Thread, Daemon Thread)
- Synchronization
- Git / GitHub

## Java Concepts Used

- Object-Oriented Programming (classes, objects, encapsulation, constructors)
- Collections Framework (ArrayList, Iterator, Comparator)
- Custom checked exception (`InvalidMarksException`)
- File I/O with BufferedReader / BufferedWriter
- Runnable, Thread, and daemon threads
- Synchronized blocks for thread-safe access to shared data
- Input validation

## Project Structure

```
Student-Grade-Management/
├── Main.java
├── Student.java
├── FileManager.java
├── AutoSave.java
├── InvalidMarksException.java
├── README.md
├── statement.md
├── .gitignore
└── data/
```

## Functional Modules

### 1. Student Management
- Add student
- View all students
- Delete student
- Prevent duplicate student IDs

### 2. Result Management
- Store subject marks (Java, Mathematics, Digital Logic)
- Calculate total
- Calculate percentage
- Assign grade
- Display student result

### 3. Search
- Search student using student ID

### 4. Sorting and Analytics
- Sort students by percentage
- Calculate class average
- Find highest percentage
- Find lowest percentage

### 5. File Persistence
- Save students to a text file
- Load students when the application starts

### 6. Automatic Saving
- Background auto-save every 30 seconds
- Uses Runnable and Thread
- Uses synchronization

## Grade Calculation Rules

Percentage is calculated as:

```
(Java Marks + Mathematics Marks + Digital Logic Marks) / 3
```

Total (out of 300):

```
Java + Mathematics + Digital Logic
```

Grade scale:

| Percentage    | Grade |
|---------------|-------|
| 90 and above  | A+    |
| 80 – 89.99    | A     |
| 70 – 79.99    | B     |
| 60 – 69.99    | C     |
| 50 – 59.99    | D     |
| Below 50      | F     |

## Installation / Compile

```bash
javac Main.java Student.java FileManager.java AutoSave.java InvalidMarksException.java
```

## Run

```bash
java Main
```

## Storage

Student records are stored in:

```
data/students.txt
```

Record format:

```
ID,Name,JavaMarks,MathsMarks,DigitalMarks
```

Example:

```
13,rahul,85.0,92.0,90.0
34,kirmada,75.0,78.0,78.0
```

The `data/` folder and `students.txt` file are created automatically by the program on first save. The data file is not committed to the repository (see `.gitignore`).

## Testing

The application has been manually tested for:

- Adding students
- Duplicate ID validation
- Invalid marks validation (must be between 0 and 100)
- Viewing all records
- Searching by ID
- Viewing individual results
- Deleting records
- Sorting by percentage
- Class statistics calculation
- File persistence across restarts
- Auto-save functionality

All test cases passed. See the project report for detailed test cases and results.

## Future Enhancements

- JDBC / relational database integration (MySQL or SQLite)
- GUI using JavaFX or Swing
- Web interface
- Authentication and role-based access
- Support for additional subjects
- Semester-wise results
- Attendance management
- PDF report generation
- Automated unit tests
- Backup and restore
- Search by name and filtering
- Advanced statistics and logging
