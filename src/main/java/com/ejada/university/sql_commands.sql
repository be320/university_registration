CREATE SCHEMA `university` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `university`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `date_of_birth` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`person_id`));


CREATE TABLE `university`.`student` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `gpa` DOUBLE NOT NULL,
  PRIMARY KEY (`student_id`),
  CONSTRAINT `student_person_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `university`.`person` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `university`.`department` (
  `department_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `manager_id` INT NOT NULL,
  PRIMARY KEY (`department_id`));

CREATE TABLE `university`.`instructor` (
  `instructor_id` INT NOT NULL AUTO_INCREMENT,
  `salary` DOUBLE NOT NULL,
  `years_of_experience` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`instructor_id`),
  INDEX `instructor_department_fk_idx` (`department_id` ASC) VISIBLE,
  CONSTRAINT `instructor_person_fk`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `university`.`person` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `instructor_department_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `university`.`department` (`department_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `university`.`department`
ADD INDEX `department_manager_fk_idx` (`manager_id` ASC) VISIBLE;
;
ALTER TABLE `university`.`department`
ADD CONSTRAINT `department_manager_fk`
  FOREIGN KEY (`manager_id`)
  REFERENCES `university`.`instructor` (`instructor_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

CREATE TABLE `university`.`course` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `credit_hours` INT NOT NULL,
  `instructor_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `course_instructor_fk_idx` (`instructor_id` ASC) VISIBLE,
  INDEX `course_department_fk_idx` (`department_id` ASC) VISIBLE,
  CONSTRAINT `course_instructor_fk`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `university`.`instructor` (`instructor_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course_department_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `university`.`department` (`department_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `university`.`register` (
  `registration_id` INT NOT NULL AUTO_INCREMENT,
  `marks` DOUBLE NOT NULL,
  `student_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  PRIMARY KEY (`registration_id`),
  INDEX `student_register_fk_idx` (`student_id` ASC) VISIBLE,
  INDEX `course_register_fk_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `student_register_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `university`.`student` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course_register_fk`
    FOREIGN KEY (`course_id`)
    REFERENCES `university`.`course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
