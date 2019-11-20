DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS classrooms;

CREATE TABLE classrooms (
    classroom_id INT AUTO_INCREMENT PRIMARY KEY,
    classroom_name VARCHAR(255) NOT NULL
);

CREATE TABLE students (
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  grade FLOAT(2),
  birth_date DATE NOT NULL,
  classroom_id INT,
  FOREIGN KEY(classroom_id) REFERENCES classrooms(classroom_id) ON DELETE CASCADE
);
