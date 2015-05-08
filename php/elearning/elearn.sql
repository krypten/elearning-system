-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2015 at 05:19 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `elearn`
--
CREATE DATABASE IF NOT EXISTS `elearn` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `elearn`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `loginid` varchar(20) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`loginid`, `pass`) VALUES
('iiita', 'iiita');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `que_id` int(5) NOT NULL AUTO_INCREMENT,
  `test_id` int(5) DEFAULT NULL,
  `que_desc` varchar(150) DEFAULT NULL,
  `ans1` varchar(75) DEFAULT NULL,
  `ans2` varchar(75) DEFAULT NULL,
  `ans3` varchar(75) DEFAULT NULL,
  `ans4` varchar(75) DEFAULT NULL,
  `true_ans` int(1) DEFAULT NULL,
  `ques_type` int(1) DEFAULT NULL,
  PRIMARY KEY (`que_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=95 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`que_id`, `test_id`, `que_desc`, `ans1`, `ans2`, `ans3`, `ans4`, `true_ans`, `ques_type`) VALUES
(1, 13, 'The DBMS acts as an interface between what two components of an enterprise-class database system?', 'Database application and the database', 'Data and the database', 'The user and the database application', 'Database application and SQL', 1, 0),
(2, 13, 'The following are components of a database except ________ .', 'user data', 'metadata', 'reports', 'indexes', 3, 0),
(3, 13, 'An application where only one user accesses the database at a given time is an example of a(n) ________ .', 'single-user database application', 'multiuser database application', 'e-commerce database application', 'data mining database application', 1, 0),
(4, 13, 'SQL stands for ________ .', 'Sequential Query Language', 'Structured Question Language', 'Sequential Question Language', 'Structured Query Language', 4, 0),
(5, 13, 'Because it contains a description of its own structure, a database is considered to be ________ .', 'described', 'metadata compatible', 'self-describing', 'an application program', 3, 0),
(6, 13, 'The following are functions of a DBMS except ________ .', 'creating and processing forms', 'creating databases', 'processing data', 'administrating databases', 1, 0),
(7, 14, 'Which is a not characteristic of java programming language?', 'Robust', 'Procedural', 'Distributed', 'Multithreaded', 2, 0),
(8, 14, 'Which tool is required to compile java code', 'java', 'javac', 'jar', 'none', 2, 0),
(11, 14, 'Which of the following is not an exception in Java?', 'Nullpointer Exception', 'Arithmetic Exception', 'ArrayoutofBounds Exception', 'Logical Exception', 4, 2),
(12, 13, 'When two transactions are being processed against the database at the same time they are termed concurrent transactions.', 'True', 'False', '', '', 1, 0),
(13, 13, 'With XML, document structure, content and format are all defined within one document.', 'True', 'False', '', '', 2, 3),
(14, 13, 'The result of every SQL query is a table.', 'True', 'False', '', '', 1, 2),
(15, 13, 'A transaction is a series of actions to be taken on the database so that either all of them are performed successfully or none of them are performed a', 'True ', 'False', '', '', 1, 0),
(16, 13, 'Which of the following products was an early implementation of the relational model developed by E.F. Codd of IBM?', 'IDMS', 'DB2', 'dBase-II', 'R:base', 2, 1),
(17, 13, 'Helping people keep track of things is the purpose of a(n) ________ .', 'database', 'table', 'instance', 'relationship', 1, 1),
(18, 13, 'Which of the following products implemented the CODASYL DBTG model?', 'IDMS', 'DB2', 'dBase-II', 'R:base', 1, 3),
(19, 13, 'An Enterprise Resource Planning application is an example of a(n) ________ .', 'single-user database application', 'multiuser database application', 'e-commerce database application', 'data mining database application', 2, 2),
(20, 13, 'A DBMS that combines a DBMS and an application generator is ________ .', 'Microsoft SQL Server', 'Microsoft Access', 'IBM DB2', 'Oracle Corporation Oracle', 2, 2),
(21, 13, 'You have run an SQL statement that asked the DBMS to display data in a table named USER_TABLES. The results include columns of data labeled TableName,', 'user data', 'metadata', 'report', 'indexes', 2, 1),
(22, 13, 'Which of the following is not considered to be a basic element of an enterprise-class database system?', 'Users', 'Database applications', 'DBMS', 'COBOL programs', 4, 3),
(23, 13, 'If a denormalization situation exists with a many-to-many or associative binary relationship, which of the following is true?', 'All fields are stored in 1 relation.', 'All fields are stored in 2 relations. ', 'All fields are stored in 3 relations. ', 'All fields are stored in 4 relations. ', 2, 3),
(24, 13, 'In an object diagram, an object is represented as a rectangle with two compartments.', 'True', 'False', '', '', 1, 2),
(25, 13, 'The data model produced from the reverse engineering process does not include intersection table data.', 'True', 'False', '', '', 2, 2),
(26, 15, 'An oval represents which of the following in an EER?', 'Attribute', 'Entity', 'Optional One', 'Relationship', 1, 1),
(27, 15, 'An action assertion must include which of the following?', 'Anchor object', 'Action', 'Corresponding object', 'All of the above.', 4, 3),
(28, 15, 'Inheritance is which of the following?', 'supertype entity inherit values of the subtype attribute', 'subtype entity inherit values of the supertype attribute', 'supertype entity inhert value of other supertype attribute', 'subtype entity inherit values of another subtype attribute', 2, 2),
(29, 15, 'When an entity instance must be a member of only one subtype, it is which of the following?', 'Disjoint with total specialization', 'Disjoint with partial specialization', 'Overlap with total specialization', 'Overlap with partial specialization', 1, 3),
(30, 15, 'A supertype subtype hierarchy is which of the following?', 'Each subtype has only one attribute.', 'Each supertype has only one attribute.', 'Each subtype has only one supertype.', 'Each supertype has only one subtype.', 3, 1),
(31, 15, 'A rectangle represents which of the following in an EER?', 'Attribute', 'Entity', 'Optional One', 'Relationship', 2, 1),
(32, 15, 'Which of the following statements concerning business rules is true?', 'It should be complex.', 'It should not be convertible to computer code.', 'It may include restrictions.', 'All of the above.', 3, 2),
(33, 15, 'Which of the following indicates the maximum number of entities that can be involved in a relationship?', 'Minimum cardinality', 'Maximum cardinality', 'ERD', 'Greater Entity Count (GEC)', 2, 1),
(34, 15, 'In a one-to-many relationship, the entity that is on the one side of the relationship is called a(n) ________ entity.', 'parent', 'child', 'instance', 'subtype', 1, 2),
(35, 15, 'Which type of entity represents an actual occurrence of an associated generalized entity?', 'Supertype entity', 'Subtype entity', 'Archetype entity', 'Instance entity', 4, 3),
(36, 15, 'A recursive relationship is a relationship between an entity and ________ .', 'itself', 'a subtype entity', 'an archetype entity', 'an instance entity', 1, 1),
(37, 15, 'Which of the following indicates the minimum number of entities that must be involved in a relationship?', 'Minimum cardinality', 'Maximum cardinality', 'ERD', 'Greater Entity Count (GEC)', 1, 2),
(38, 15, 'Which of the following refers to an entity in which the identifier of one entity includes the identifier of another entity?', 'Weak entity', 'Strong entity', 'ID-dependent entity', 'ID-independent entity', 3, 3),
(39, 16, 'The different classes of relations created by the technique for preventing modification anomalies are called:', 'normal forms', 'referential integrity constraints', 'functional dependencies', 'None of the above is correct', 1, 2),
(40, 16, 'A relation is in this form if it is in BCNF and has no multivalued dependencies:', 'second normal form', 'third normal form', 'fourth normal form', 'domain/key normal form', 3, 3),
(41, 16, 'Row is synonymous with the term:', 'record', 'relation', 'column', 'field', 1, 1),
(42, 16, 'The primary key is selected from the', 'composite keys', 'determinants', 'candidate keys', 'foreign keys', 3, 1),
(43, 16, 'Which of the following is a group of one or more attributes that uniquely identifies a row?', 'Key', 'Determinant', 'Tuple', 'Relation', 1, 1),
(44, 16, 'When the values in one or more attributes being used as a foreign key must exist in another set of one or more attributes in another table, we have cr', 'transitive dependency.', 'insertion anomaly.', 'referential integrity constraint.', 'normal form.', 3, 3),
(45, 16, 'In the relational model, relationships between relations or tables are created by using:', 'composite keys.', 'determinants.', 'candidate keys.', 'foreign keys.', 4, 2),
(46, 16, 'A functional dependency is a relationship between or among:', 'tables', 'rows', 'relations', 'attributes', 4, 1),
(47, 16, 'Which of the following is not a restriction for a table to be a relation?', 'The cells of the table must contain a single value.', 'All of the entries in any column must be of the same kind.', 'The columns must be ordered.', 'No two rows in a table may be identical.', 3, 3),
(48, 16, 'For some relations, changing the data can have undesirable consequences called:', 'referential integrity constraints.', 'modification anomalies.', 'normal forms.', 'transitive dependencies.', 2, 2),
(49, 16, 'A relation in this form is free of all modification anomalies.', 'First normal form', 'Second normal form', 'Third normal form', 'Domain/key normal form', 4, 1),
(50, 16, 'If attributes A and B determine attribute C, then it is also true that:', 'A &#8594; C.', 'B &#8594; C.', '(A,B) is a composite determinant.', 'C is a determinant.', 3, 2),
(51, 16, 'One solution to the multivalued dependency constraint problem is to:', 'split the relation into two, each with a single theme', 'change the theme', 'create a new theme', 'add a composite key', 1, 3),
(52, 17, 'An extent is which of the following?', 'keyword that indicates: subclass inherits from superclass', 'keyword that indicates: superclass inherits from subclass', 'The set of all instances of a class within a database', 'Only one instance of a class within a database', 3, 2),
(53, 17, 'ODL supports which of the following types of association relationships?', 'Unary', 'Unary and Binary', 'Unary and Binary and Ternary', 'Unary and Binary and Ternary and higher', 2, 3),
(54, 17, 'Using ODL, you can define which of the following?', 'Attribute', 'Structure', 'Operation', 'All of the above', 4, 1),
(55, 13, 'An on-line commercial site such as Amazon.com is an example of a(n) ________ .', 'single-user database application', 'multiuser database application', 'e-commerce database application', 'data mining database application', 3, 1),
(56, 17, 'Identify the class name for the following code: ABC123 course();', 'ABC123', 'course', 'course()', 'All of the above.', 1, 1),
(57, 17, 'The keyword "inverse" is used in which of the following?', 'Class', 'Attribute', 'Relationship', 'All of the above.', 3, 2),
(58, 17, 'The object definition language (ODL) is which of the following?', 'Used to develop logical schemas', 'A data definition language for OODB', 'A method to implement a logical schema', 'All of the above.', 4, 1),
(59, 17, 'An atomic literal is which of the following?', 'Strings', 'Boolean', 'Long', 'All of the above.', 4, 2),
(60, 17, 'The reserved word enum is used for which of the following?', 'To define a range for an attribute.', 'To define a range for a class.', 'To define a range for a relationship.', 'All of the above.', 1, 3),
(61, 17, 'Which of the following is an unordered collection of elements that may contain duplicates?', 'Set', 'Bag', 'List', 'Dictionary', 2, 1),
(62, 17, 'Which of the following is true concerning the following statement: class Manager extends Employee', 'Manager is a concrete class and a superclass.', 'Manager is a concrete class and a subclass.', 'Manager is an abstract class and a superclass.', 'Manager is an abstract class and a subclass.', 2, 2),
(63, 17, 'A relationship should be specified how in the ODL?', 'One direction starting with the first class', 'One direction starting with the second class', 'Neither direction.', 'Both directions.', 4, 3),
(64, 18, 'You can add a row using SQL in a database with which of the following?', 'ADD', 'CREATE', 'INSERT', 'MAKE', 3, 1),
(65, 18, 'The command to remove rows from a table CUSTOMER is:', 'REMOVE FROM CUSTOMER ...', 'DROP FROM CUSTOMER ...', 'DELETE FROM CUSTOMER WHERE ...', 'UPDATE FROM CUSTOMER ...', 3, 1),
(66, 18, 'The SQL WHERE clause:', 'limits the column data that are returned.', 'limits the row data are returned.', 'Both A and B are correct.', 'Neither A nor B are correct.', 2, 2),
(67, 18, 'Which of the following is the original purpose of SQL?', 'Specify syntax and semantics of SQLDDL', 'Specify syntax and semantics of SQL manipulation language', 'To define the data structures', 'All of the above.', 4, 1),
(68, 18, 'The wildcard in a WHERE clause is useful when?', 'An exact match is necessary in a SELECT statement.', 'An exact match is not possible in a SELECT statement.', 'An exact match is necessary in a CREATE statement.', 'An exact match is not possible in a CREATE statement.', 2, 3),
(69, 18, 'Needing to using more complicated SQL in database applications is a(n) ________ of normalization.', 'advantage', 'disadvantage', 'either an advantage or disadvantage', 'neither an advantage nor disadvantage', 2, 2),
(70, 18, 'Eliminating modification anomalies is a(n) ________ of normalization.', 'advantage', 'disadvantage', 'either an advantage or disadvantage', 'neither an advantage nor disadvantage', 1, 1),
(71, 18, 'Multivalued dependencies should ________ be eliminated.', 'always', 'commonly', 'seldom', 'never', 1, 2),
(72, 18, 'When assessing the table structure of an acquired set of tables with data, accessing the validity of possible referential integrity constraints on for', 'first step.', 'second step.', 'third step.', 'fourth step.', 3, 3),
(73, 18, 'Using the SQL GROUP BY phrase with a SELECT statement can help detect which of the following problems?', 'The multivalue, multicolumn problem', 'The inconsistent values problem', 'The missing values problem', 'The general-purpose remarks column problem', 2, 2),
(74, 18, 'In a 1:N relationship, the foreign key is placed in:', 'either table without specifying parent and child tables.', 'the parent table.', 'the child table.', 'either the parent table or the child table.', 3, 3),
(75, 19, 'Locks placed by command are called ________ .', 'implicit locks', 'explicit locks', 'exclusive locks', 'shared locks', 2, 2),
(76, 19, 'Which of the following locks the item from change but not from read?', 'Implicit lock', 'Explicit lock', 'Exclusive lock', 'Shared lock', 4, 1),
(77, 19, 'Which of the following occurs when a transaction rereads data and finds new rows that were inserted by a command transaction since the prior read?', 'Nonrepeatable read', 'Phantom read', 'Dirty read', 'Consistent read', 2, 3),
(78, 19, 'A transaction for which all committed changes are permanent is called:', 'atomic.', 'consistent.', 'isolated.', 'durable.', 4, 2),
(79, 19, 'In this instance, dirty reads are disallowed, while nonrepeatable reads and phantom reads are allowed.', 'Read committed', 'Read uncommitted', 'Repeatable read', 'Serializable', 1, 3),
(80, 19, 'Which of the following occurs when a transaction rereads data it has previously read and finds modification or deletions caused by a committed transac', 'Nonrepeatable read', 'Phantom read', 'Dirty read', 'Consistent read', 1, 2),
(81, 19, 'The advantage of optimistic locking is that:', 'the lock is obtained only after the transaction has proces', 'the lock is obtained before the transaction has processed.', 'the lock never needs to be obtained.', 'transactions that are best suited having a lot of activity', 1, 1),
(82, 19, 'Which of the following refers to a cursor type where changes of any type and from any source are visible?', 'Forward only', 'Static', 'Keyset', 'Dynamic', 4, 3),
(83, 19, 'What type of failure occurs when Oracle fails due to an operating system or computer hardware failure?', 'Application failure', 'Instance Failure', 'Media Failure', 'Rollback failure', 2, 1),
(84, 19, 'Which prefixes are available to Oracle triggers?', ':new only', ':old only', 'Both :new and :old', 'Neither :new nor :old', 3, 3),
(85, 19, 'Which of the following items are not necessary for client/server?', 'Assure that tools will connect with middleware.', 'Understand the requirements.', 'Determine network bandwidth capabilities.', 'Include the use of a file server.', 4, 1),
(86, 20, 'What type of join is needed when you wish to include rows that do not have matching values?', 'Equi-join', 'Natural join', 'Outer join', 'All of the above.', 3, 1),
(87, 20, 'Which of the following is true concerning a procedure?', 'You do not create them with SQL.', 'They do not need to have a unique name.', 'They include procedural and SQL statements.', 'They are the same thing as a function', 3, 1),
(88, 20, 'A CASE SQL statement is which of the following?', 'A way to establish an IF-THEN-ELSE in SQL.', 'A way to establish a loop in SQL.', 'A way to establish a data definition in SQL.', 'All of the above.', 1, 2),
(89, 20, 'Which of the following statements is true concerning routines and triggers?', 'Both consist of procedural code.', 'Both have to be called to operate.', 'Both run automatically.', 'Both are stored in the database.', 1, 2),
(90, 20, 'The @active data warehouse architecture includes which of the following?', 'At least one data mart', 'Data that can extracted from internal and external source', 'Near real-time updates', 'All of the above.', 4, 3),
(91, 20, 'A data warehouse is which of the following?', 'Can be updated by end users.', 'Contains numerous naming conventions and formats.', 'Organized around important subject areas.', 'Contains only current data.', 3, 1),
(92, 20, 'A goal of data mining includes which of the following?', 'To explain some observed event or condition', 'To confirm that data exists', 'To analyze data for expected relationships', 'To create a new data warehouse', 1, 1),
(93, 20, 'A distributed database has which of the following advantages over a centralized database?', 'Software cost', 'Software complexity', 'Slow Response', 'Modular growth', 4, 2),
(94, 20, 'Location transparency allows for which of the following?', 'Users to treat the data as if it is at one location', 'Programmers to treat the data as if it is at one location', 'Managers to treat the data as if it is at one location', 'All of the above.', 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `login` varchar(20) DEFAULT NULL,
  `test_id` int(5) DEFAULT NULL,
  `test_date` timestamp NULL DEFAULT NULL,
  `score` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`login`, `test_id`, `test_date`, `score`) VALUES
('shubham', 14, '2015-05-03 10:21:46', 1),
('shubham', 13, '2015-05-03 10:22:24', 3),
('shubham', 14, '2015-05-03 10:27:41', 1),
('shubham', 14, '2015-05-03 10:29:36', 1),
('shubham', 13, '2015-05-03 10:29:55', 1),
('shubham', 14, '2015-05-03 10:32:58', 1),
('shubham', 14, '2015-05-03 10:33:39', 2),
('shubham', 14, '2015-05-03 10:36:00', 1),
('shubham', 14, '2015-05-03 10:36:56', 1),
('shubham', 14, '2015-05-03 10:37:19', 1),
('shubham', 14, '2015-05-03 10:38:23', 1),
('shubham', 13, '2015-05-03 10:42:12', 3),
('shubham', 13, '2015-05-03 10:42:58', 4),
('shubham', 13, '2015-05-03 10:43:31', 4),
('shubham', 13, '2015-05-03 10:45:53', 3),
('shubham', 14, '2015-05-03 10:47:50', 1),
('shubham', 13, '2015-05-03 10:48:23', 2),
('shubham', 14, '2015-05-03 10:49:10', 1),
('shubham', 13, '2015-05-03 10:49:33', 3),
('shubham', 14, '2015-05-03 10:51:20', 1),
('shubham', 14, '2015-05-04 04:13:32', 1),
('shubham', 14, '2015-05-04 04:13:50', 0),
('shubh', 14, '2015-05-04 04:15:28', 2),
('shubh', 13, '2015-05-04 04:16:12', 2),
('shubh', 14, '2015-05-04 04:16:49', 1),
('shubh', 14, '2015-05-04 04:20:16', 1),
('shubham', 14, '2015-05-04 05:27:38', 1),
('shubham', 14, '2015-05-04 05:27:51', 1),
('shubham', 14, '2015-05-04 05:40:26', 2),
('shubh', 14, '2015-05-04 05:48:16', 2),
('shubh', 14, '2015-05-04 06:33:55', 2),
('shubh', 14, '2015-05-04 06:42:12', 2),
('shubh', 14, '2015-05-04 06:44:53', 2),
('shubh', 14, '2015-05-04 06:54:01', 1),
('shubh', 14, '2015-05-04 07:15:14', 2),
('shubh', 14, '2015-05-04 07:17:25', 2),
('shubh', 14, '2015-05-04 07:19:21', 2),
('shubh', 14, '2015-05-04 07:20:28', 2),
('shubh', 14, '2015-05-04 07:54:01', 2),
('shubh', 14, '2015-05-04 07:55:02', 2),
('shubh', 14, '2015-05-04 07:55:29', 2),
('shubh', 14, '2015-05-04 08:02:12', 2),
('shubh', 14, '2015-05-04 08:05:05', 2),
('shubh', 14, '2015-05-04 08:07:42', 2),
('shubh', 14, '2015-05-04 08:22:45', 2),
('shubh', 14, '2015-05-04 08:24:07', 2),
('shubh', 13, '2015-05-04 08:24:30', 1),
('shubh', 14, '2015-05-04 08:31:29', 2),
('shubh', 14, '2015-05-04 08:38:19', 2),
('shubh', 14, '2015-05-04 08:42:37', 1),
('shubh', 14, '2015-05-04 08:43:43', 1),
('shubh', 14, '2015-05-04 08:43:48', 1),
('shubh', 14, '2015-05-04 08:44:05', 1),
('shubh', 14, '2015-05-04 12:16:39', 2);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `sub_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(25) DEFAULT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`sub_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`sub_id`, `sub_name`, `description`) VALUES
(1, 'DBMS', 'A database-management system (DBMS) is a collection of interrelated data and a\r\nset of programs to access those data. The collection of data, usually referred to as the\r\ndatabase, contains information relevant to an enterprise. The primary goal of a DBMS\r\nis to provide a way to store and retrieve database information that is both convenient\r\nand effcient.\r\nDatabase systems are designed to manage large bodies of information. Management of data involves both defining structures for storage of information and providing mechanisms for the manipulation of information. In addition, the database\r\nsystem must ensure the safety of the information stored, despite system crashes or\r\nattempts at unauthorized access. If data are to be shared among several users, the\r\nsystem must avoid possible anomalous results.\r\nBecause information is so important in most organizations, computer scientists\r\nhave developed a large body of concepts and techniques for managing data. These\r\nconcepts and technique form the focus of this course.'),
(2, 'JAVA', 'This course covers all aspects of the Java programming language.\r\nPart 1 presents an\r\nin-depth tutorial of the Java language. It begins with the basics, including such things\r\nas data types, control statements, and classes. Part 1 also discusses Java’s\r\nexception-handling mechanism, multithreading subsystem, packages, and interfaces.\r\nPart 2 examines the standard Java library. As you will learn, much of Java’s power\r\nis found in its library. Topics include strings, I/O, networking, the standard utilities,\r\nthe Collections Framework, applets, GUI-based controls, and imaging.\r\nPart 3 looks at some issues relating to the Java development environment, including\r\nan overview of Java Beans, Servlets, and Swing.\r\nPart 4 presents a number of high-powered Java applets that serve as extended\r\nexamples of the way Java can be applied. The final applet, called Scrabblet, is a complete,\r\nmultiuser networked game. It shows how to handle some of the toughest issues involved\r\nin Web-based programming.');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `test_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_id` int(5) DEFAULT NULL,
  `test_name` varchar(30) DEFAULT NULL,
  `total_que` varchar(15) DEFAULT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`test_id`, `sub_id`, `test_name`, `total_que`, `description`) VALUES
(13, 1, 'Introduction to DBMS', '10', ''),
(14, 2, 'Java basics', '5', ''),
(15, 1, 'Database Design Theory', '10', '<b>Introduction</b><br>\n1. Data Models<br>\n2. Entity-Relationship Model<br>\n3. Relational Model<br>'),
(16, 1, 'Relational Data Modeling', '10', '<b>Relational Databases</b><br>\n1. SQL<br>\n2. Other Relational Languages<br>\n3. Integrity and Security<br>\n4. Relational-Database Design<br>\n'),
(17, 1, 'Object based Databases', '10', '<b>Object based Databases and XML<b><br>\n1. Object?Oriented Databases<br>\n2. Object-Relational Databases<br>\n3. XML<br>'),
(18, 1, 'Data Storage, Indexing, Query', '10', '<b>Data Storage and Querying</b><br>\n1. Storage and File Structure<br>\n2. Indexing and Hashing<br>\n3. Query Processing<br>\n4. Query Optimization<br>'),
(19, 1, 'Transaction Management', '10', ''),
(20, 1, 'Advance Database Topics', '10', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(5) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `login`, `pass`, `username`, `email`) VALUES
(5, 'shubham', '12345', 'shubham kela', 'shubhamkela@gmail.com'),
(6, 'shubh', '123', 'shubham kela', 'shubhamkela@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `useranswer`
--

CREATE TABLE IF NOT EXISTS `useranswer` (
  `sess_id` varchar(80) DEFAULT NULL,
  `test_id` int(11) DEFAULT NULL,
  `que_des` varchar(200) DEFAULT NULL,
  `ans1` varchar(50) DEFAULT NULL,
  `ans2` varchar(50) DEFAULT NULL,
  `ans3` varchar(50) DEFAULT NULL,
  `ans4` varchar(50) DEFAULT NULL,
  `true_ans` int(11) DEFAULT NULL,
  `your_ans` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useranswer`
--

INSERT INTO `useranswer` (`sess_id`, `test_id`, `que_des`, `ans1`, `ans2`, `ans3`, `ans4`, `true_ans`, `your_ans`) VALUES
('g2jfi0ar6laq4d0bciq8qvk304', 13, 'The DBMS acts as an interface between what two components of an enterprise-class database system?', 'Database application and the database', 'Data and the database', 'The user and the database application', 'Database application and SQL', 1, 1),
('g2jfi0ar6laq4d0bciq8qvk304', 13, 'The following are components of a database except ________ .', 'user data', 'metadata', 'reports', 'indexes', 3, 2),
('g2jfi0ar6laq4d0bciq8qvk304', 13, 'An application where only one user accesses the database at a given time is an example of a(n) ________ .', 'single-user database application', 'multiuser database application', 'e-commerce database application', 'data mining database application', 1, 1),
('g2jfi0ar6laq4d0bciq8qvk304', 13, 'SQL stands for ________ .', 'Sequential Query Language', 'Structured Question Language', 'Sequential Question Language', 'Structured Query Language', 4, 4),
('g2jfi0ar6laq4d0bciq8qvk304', 13, 'With XML, document structure, content and format are all defined within one document.', 'True', 'False', '', '', 2, 1),
('g2jfi0ar6laq4d0bciq8qvk304', 13, 'Which of the following products implemented the CODASYL DBTG model?', 'IDMS', 'DB2', 'dBase-II', 'R:base', 1, 2),
('g2jfi0ar6laq4d0bciq8qvk304', 13, 'Which of the following is not considered to be a basic element of an enterprise-class database system?', 'Users', 'Database applications', 'DBMS', 'COBOL programs', 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `usertest`
--

CREATE TABLE IF NOT EXISTS `usertest` (
  `usertest_id` int(5) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `test_id` int(5) NOT NULL,
  `available` int(5) NOT NULL DEFAULT '0',
  `pretest_given` int(5) NOT NULL DEFAULT '0',
  `pretest_score` float NOT NULL,
  `test_given` int(5) NOT NULL DEFAULT '0',
  `test_score` float NOT NULL,
  `pretest_time` datetime NOT NULL,
  PRIMARY KEY (`usertest_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `usertest`
--

INSERT INTO `usertest` (`usertest_id`, `login`, `test_id`, `available`, `pretest_given`, `pretest_score`, `test_given`, `test_score`, `pretest_time`) VALUES
(1, 'shubham', 13, 1, 1, 37.5, 0, 0, '2015-05-07 14:39:50'),
(2, 'shubham', 14, 1, 0, 0, 0, 0, '0000-00-00 00:00:00'),
(3, 'shubh', 13, 1, 0, 0, 0, 0, '0000-00-00 00:00:00'),
(4, 'shubh', 14, 1, 0, 0, 0, 0, '0000-00-00 00:00:00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
