# JAVA
```
Banu Prakash C
Full Stack Architect, Corporate Trainer
Co-founder & CTO: Lucida Technologies Pvt Ltd.,
Email: banuprakashc@yahoo.co.in
banuprakash.cr@gmail.com

https://www.linkedin.com/in/banu-prakash-50416019/

https://github.com/BanuPrakash/NCG_JAVA

===================================

Softwares Required:

1) openJDK 21 https://jdk.java.net/java-se-ri/21
Option 1: install and add path and JAVA_HOME
vi ~/.zshrc export JAVA_HOME=/Users/banuprakash/Desktop/jdk-21
export PATH="/Users/banuprakash/Desktop/jdk-21/bin:"$PATH
Option 2: [better]
USE SDKMAN to manage java
curl -s "https://get.sdkman.io" | bash
sdk install java 21.0.6-tem
sdk default java 21.0.6-tem

https://mydeveloperplanet.com/2022/04/05/how-to-manage-your-jdks-with-sdkman/#:~:text=Some%20time%20ago%2C%20a%20colleague%20of%20mine,maintain%20different%20versions%20of%20JDKs%2C%20Maven%2C%20etc.

$ java --version

2) IntelliJ Ultimate or Community edition https://www.jetbrains.com/idea/download/?section=mac

3) MySQL [ Prefer on Docker]
Install Docker Desktop
Docker steps:
a) docker pull mysql
b) docker run --name local-mysql –p 3306:3306 -e MYSQL_ROOT_PASSWORD=Welcome123 -d mysql
container name given here is "local-mysql"
For Mac:
docker run -p 3306:3306 -d --name local-mysql -e MYSQL_ROOT_PASSWORD=Welcome123 mysql

c) CONNECT TO A MYSQL RUNNING CONTAINER:

$ docker exec -t -i local-mysql bash

d) Run MySQL client:

bash terminal> mysql -u "root" -p

mysql> exit

=======

```

Day 1:

OOP --> OBJECT ORIENTED PROGRAMMING

Object --> state and behaviour

Template to create objects.
* function
* class
* Type

---

SOLID Design Principles

S --> Single Responsibility
O --> Open Close Principle
L --> Liskov Substitution Principle
Generalization and Specialization
I --> Interface seggregation
D --> Dependency Injection [ Inversion Of Control]

Java ?
Technology, Platform to execute bytecode

Java DK --> bytecode
Kotlin DK --> bytecode
Groovy DK --> bytecode

==============

Compile Time Environment : Development Kit --> compiler

Account.java
```
    public class Account {
        private static int count = 0; // state of class
        private double balance; // instance var, state of object
        public Account() {
            count++;
        }
        public void deposit(double amt) {
           // public void deposit(Account this, double amt) {
            this.balance += amt;
        }
        public double getBalance() {
            return this.balance;
        }

        public static int getCount() {
            return count;
        }
    }
```
javac Account.java ---> Account.class

AccountClient.java
```
public class AccountClient {
    public static void main(String[] args) {
        Account rahulAcc = new Account();
        System.out.println(rahulAcc.getCount()); // 1
          System.out.println(Account.getCount()); // 1
        Account swethaAcc = new Account();
         System.out.println(swethaAcc.getCount()); // 2
           System.out.println(Account.getCount()); // 1
        rahulAcc.deposit(56000.00);
        // deposit(rahulAcc, 56000.00); 
        swethaAcc.deposit(90000);

        System.out.println(rahulAcc.getBalance());
    }
}
```

javac AccountClient.java ==> AccountClient.class

=======

State of class: member shared by all objects of a given class --> static

================

Logical grouping of class/objects in enterprise applications
1) entity
2) DAO --> Data Access Objects
3) Business classes
4) Service classes
5) Exception classes
6) Utility classes
7) Client classes / UI classes

Entity / Domain / Model classes

Uber: Customer, Driver, Vehicle, Trip, Payment, .. --> Business data
Long lived, survives application crash

entity classes --> state [ fileds], setters, getters, hashCode, equals

```
Rectangle r1 = new Rectangle(4,5);
Rectangle r2 = new Rectangle(4,5);

r1 == r2 [false]
r1.equals(r2); // should evalute to true
```

DAO: Data Access Object
contain CRUD operations CREATE READ UPDATE and DELETE 

DAO's are generally one per table 
CustomerDAO, ProductDao, OrderDAO ...

Service classes are facade over business and DAO classes.
Generally one per role.
AdminService, CustomerService ...

```
    public class BankingService {

        public void transferFunds(Account fromAcc, Account toAcc, double amt) {
            fromAcc.getBalance();
            business logic
            fromAcc.update(..)
            toAcc.update()
            transacton.update();
            send SMS
            send EMAIL
        }
    }

```

packages --> folders for logical grouping of classes

```
com
    adobe
        aem [project]
            entity
                Product.java
                Customer.java
                Order.java
            dao
                ProductDao.java
                CustomerDao.java
            service
                CustomerService.java
            utility
                DataUtil.java

```

Relationship between objects:
1) Generalization and Specialization
2) Realization
3) Association
4) Uses A


Maven / Gradle
Sonar --> Checkstyle, PMD, FindBugs
Jenkins


```
    public class Product {
       public double getPrice() {
        return 100;
       }
    }

    public class Mobile extends Product {
       public double getPrice() {
        return 999;
       }
        public String getConnectivity() {
            return "5G";
        }
    }

   Mobile m = new Mobile();
   m.getPrice(); //  999
   m.getConnectivity(); // 5G

   Product p = new Mobile(); // upcasting
   p.getPrice(); // 999
   p.getConnectivity(); // ??? ERROR
```

Product p = new Mobile();
p instanceof Product ===> true
p instanceof Mobile ===> true
p instanceof Object ===> true
p instanceof Tv ==> false


p.getClass ===> Mobile.class

=================

overriding rules:
1) method name has to be same
2) arguments has to be same
3) return type has to be same or subtype
4) visiblity has to be same or enhanced

```
    public class Account {
        public Account getAccount() {

        }

        protected boolean login() {

        }
    }

    public class SavingsAccount extends Account {
        // valid for override
        public SavingsAccount getAccount() {

        }
        // valid
        public boolean login() {

        }
    }

```

Realization Relationship: Program to contract, program to interface

a realization relationship signifies that one model element (the client) implements or fulfills the behavior specified by another element (the supplier)

```
interface EmployeeDao {
    void addEmployee(Employee e);
    Employee getEmployee(int id);
}

public class EmployeeDaoRdbmsImpl implements EmployeeDao {
    public void addEmployee(Employee e) {
        SQL insert
    }
    public Employee getEmployee(int id) {
        sql select
    }
}

public class EmployeeDaoMongoImpl implements EmployeeDao {
    public void addEmployee(Employee e) {
        db.employees.insert(e);
    }
    public Employee getEmployee(int id) {
        db.employee.findAll();
    }
}

```

Why should we program to interface?
1) DESIGN
2) IMPLEMENTATION
3) TESTING
4) INTEGRATION
5) LOOSE COUPLING

===========================

local variables --> Stack [method gets called, destroyed when method is poped out]
static variables --> loaded into metaspace as soon as class is loaded
instance variables --> heap area [ object created, destroyed when object is removed from heap area]

Garbage Collector: MSC, G1GC, ZPC, Epsilon, ...

GC is a low priority thread

System.gc(); request to start Short term GC

Product p = new Product();
...

p = null;

==================

Day 2

```
interface Comparable {
    int compareTo(Object obj);
}
```

A class can implement multiple interfaces. 
A class can extend from only one class.

an interface can extend another interface.

```
    public class Rectangle {
        int breadth;
        int height;
        // code..
    }

    public class DRectangle {
        double breadth;
        double height;
    }
```
Generics:

```
     public class Rectangle<T>{
        T breadth; // Object breadth;
        T height;
        // code..
    }

    Rectangle<Integer> r1 = new Rectangle<Integer>(4,5);
    Rectangle<Double> r2 = new Rectangle<Double>(4.1, 9.2);
    Rectangle<String> r3 = new Rectangle<String>("A", "B");
```

Type Wrapper classes: Integer, Short, Byte, Float, Double, ...

int x = 10;
Integer iX = x; // boxing
int y = iX; // unboxing



```
     public class Rectangle<T extends Number>{
        T breadth; // Number breadth;
        T height;
        // code..
    }

    interface Sample {
        int AGE = 100; // public static final int AGE = 100;
    }
```

Annotations:
Metadata

```
@interface AnnotationName {
    properties
}
```

1) Who uses it?
* COMPILER
* CLASSLOADER
* RUNTIME
2) Where can I use it?
METHOD
TYPE [ class, interface, annotation, enum]
FIELD
PARAMETER

@Override ==> COMPILER --> Checks base class method signature, if valid create a bytecode.
bytecode doesn't have annotation data

```
    public @interface Mobile {
        String name();
    }

    @Mobile(name="Samsung")
    public class CandyCrush extends Game {

    }

```

Example for Runtime Annotations:
1) @Table
2) @Column

```
    @Table(name="EMP")
    public class Employee {

        @Column(name="EMP_ID", type="NUMERIC(12)")
        id;
        @Column(name="EMP_NAME")
        name;

    }

    @Table(name="BOOKS")
    public class Book {
        ...
    }

```

Avoid :
```
String s = "Hello";

s += "World";

s += "123";
```

instead use StringBuffer / StringBuilder

=============================

Data Containers:
Array is a data container; size is fixed, adding and removing at arbitrary position is not efficient.

Java Collection Framework provides data containsers [java.util package]
* interfaces
* implementation
* algorithm / utilities

Comparable and Comparator are for comparing objects.
Comparable --> logic is part of object
Comparator --> logic is part of client code

Example: FileSystem of OS has one mechanism [comparable] to sort and list

Lambda Expressions can be used instead of Anonymous class if the interface is FunctionalInterface.

FunctionalInterface: interface which has only one method to implement

List:
    * supports duplicate elements
    * ordered
    * re-ordered
    * supports index based operation
    get(10); add("", 9);
Set:
    * unique collection
    * not ordered
    * can't sort / shuffle / reverse

List is an interface,
ArrayList, LinkedList are implementations of List interface provided by JCF.
Vector, Stack are depreicated.
3rd party implementations for this interface are also available like from vavr.io, apache collections


```
interface Flyable {
    void fly();
}

class Bird implements Flyable {
    name;
    colour;
    age;
    public void fly() {

    }
}


Flyable f = new Bird();

Flyable f = new Flyable() {
    public void fly() {

    }
}

```

List interface ; ArrayList and LinkedList


ArrayList list = new ArrayList(); // avoid this, program to interface

List list = new ArrayList();  // not typesafe
list.add("A");
list.add(new Date());

List<String> list = new ArrayList<>();
list.add("A"); // valid
list.add(new Date()); // error

TypeSafe: compile without warning and at runtime no exception

Arrays are Covariant
```
    Object[] data = new String[3]; // valid
    data[0] = "Test";
    data[1] = new Date(); // ArrayStoreException at runtime, not typesafe
```
Generic collections are not covariant
```
List<Object> list = new ArrayList<String>(); // error
```

Java 8 version --> Streams
A stream is a sequence of elements, not a data structure itself. 
It does not store data but rather provides a way to process it.

High Order Functions:
* function which can accept other function as argument
* function which returns a function

Commonly used HOF:
1) intermediary operations
    filter -> takes predicate function to get subset
    map --> takes transform function to get transformed data
    flatMap
    skip
    limit
2) terminal operations
    forEach --> takes action
    collect
    reduce --> takes aggregate function

======
Set -> unique collection, not ordered, can't re-order

HashSet implementation of Set uses
hashCode() and equals() to identify duplicate elements

TreeSet [Rarely used] uses comparable / Comparator to identify duplicated

HashCode:
* if 2 objects are similar then their hashcodes has to be same
* 2 objects which are not similar can also have same hashcode

```
    public class Rectangle {

        @override
        public int hashCode() {
            return w * b;
        }
    }

    Rectangle 4,5 ==> 20
    Rectangle 5,4 ==> 20
    Rectangle 10,2 ==> 20
    Rectangle 20, 1 ==> 20
```

Map stores data in key/value pair.
key has to be unique, values can be be duplicated.
Dictionary, Directory, object references, ...

Docker is a set of platform as a service (PaaS) products that use OS-level virtualization to deliver software in packages called containers.

```
Docker images:
docker run -p 3306:3306 -d --name local-mysql -e MYSQL_ROOT_PASSWORD=Welcome123 mysql
docker exec -it local-mysql bash
# mysql -u root -p
mysql> create database JAVA_NCG;
Query OK, 1 row affected (0.01 sec)

mysql> use JAVA_NCG;

mysql> create table products (id int PRIMARY KEY AUTO_INCREMENT, name varchar(100), price double);


mysql> insert into products values (0, 'iPhone 16', 98000.00);


mysql> insert into products values (0, 'Wacom', 4000.00);


mysql> select * from products;
+----+-----------+-------+
| id | name      | price |
+----+-----------+-------+
|  1 | iPhone 16 | 98000 |
|  2 | Wacom     |  4000 |
+----+-----------+-------+

```

JDBC: Java Database Connectivity --> Integration library to connect to RDBMS
JDBC is a set of interfaces, implementation classes are provided by database vendors

Steps:
1) load vendor provided classes
    Class.forName("oracle.jdbc.Driver");
    Class.forName("com.mysql.jdbc.Driver");

2) Establish database connection

Connection con = DriverManager.getConnection(URL, USER, PWD);
getConnection is a factory method, based on URL it creates
OracleConnection / MySQLConnection

URLS:
jdbc:oracle:thin:@127.14.14.11:1502:emp_db

jdbc:mysql://190.33.14.11:3306/emp_db

3) Send SQL
3.1) Statement
    use it if SQL is same for n requests like
    select * from products
3.2) PreparedStatement
    use this if SQL has IN parameters

    select * from users where username =? and password = ?
    select * from products where category = ?

    Avoid SQL concatination and Statement ==> SQL Injection
    https://owasp.org/Top10/A03_2021-Injection/

3.3) CallableStatement
    use this if we have stored procedures

4) 
int executeUpdate(SQL) ==> INSERT, DELETE and UPDATE SQL
ResultSet executeQuery(SQL) ==> SELECT

ResultSet is a cursor to fetched data

5) finally close the resources in finally block.

============================

Maven: Apache Maven is a build tool for Java projects. Using a project object model (POM),
 Maven can be used to manage dependencies.
 Maven manages a project's compilation, testing, and documentation.

 pom.xml
 ```
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.36</version>
</dependency>
 ```

 Day 3
 =====
 Recap:
 * Java Collection Framework: Comparable, Comparator, Arrays, Collections, ArrayList, HashSet and HashMap
 * JDBC, Maven tool
 * Docker desktop, mysql image and created local-mysql container
 * JAVA_NCG database, products and inserted 2 records

 Lombok: Project Lombok Java library into a build tool to automate some code, such as getter/setter methods and logging variables.

 ```
    public class ProductDaoJdbcImpl implements ProductDao {
        public void addProduct(Product product) {
            try {
                Connection
                Statement
                ...
            } catch(SQLException ex) {
                log the exception
            }
        }
    }

    public class ProductDaoMongoImpl implements ProductDao {
        public void addProduct(Product product) {
            try {
               db.products.insert(...)
            } catch(MongoException ex) {
                log the exception
            }
        }
    }

 public class ProductDaoJdbcImpl implements ProductDao {
        public void addProduct(Product product) throws PersistenceException {
                try {
                Connection
                Statement
                ...
            } catch(SQLException ex) {
                log the exception on server
                check exception error code and throw appropriate message
                to client
                if(ex.getErrorCode() == 6001) {
                    throw new PersistenceException("product with id : " + id + " already exists");
                }
            }
        }
    }
    public class ProductClient {
        main() {

            Product p = ...
            try {
                ProductDao productDao = new ProductDaoJdbcImpl();
                productDao.addProduct(p);
            } catch(PersistenceException ex) {
                log
            }
        }
    }

    public class PersistenceException extends Exception {
        constructors(...)
    }

    public interface ProductDao {
        void addProduct(Product product) 
                throws PersistenceException;
    }
 ```

 finally block is a compulsory execute block. gets called if exception occurs or not.

 ==========

Web Application Development using Java.

Servlet Technology : Servlet is a server side java program / application

Servlet engines / Servlet Container / Web Container --> Jetty / Tomcat / Netty

Servlets lifecycle, injecting dependencies are managed by Servlet engines/Container

Deployment Descriptor
XML / Annotation
Mapping URL to Servlet

HTTP Method of requests:
GET : to get resource[s] --> default method when request is sent from Address bar URL or Hyperlink
POST: create a new resource under resources
PUT/PATCH: modifying
DELETE: deleting

GET http://localhost:8080/products



request: encapsulates all info from client: FORM data, request parameters, BROWSER, OS, ..

response: used to write back to client

Deployment Descriptor: xml
web.xml --> one per web application
```
    <servlet>
        <servlet-name>First</servlet-name>
        <servlet-class>com.adobe.prj.web.LoginServlet</servlet-class>
    </servlet>
    // internally its a MAP type of data container
    <servlet-mapping>   
         <servlet-name>First</servlet-name>
         <url-pattern>/login</url-pattern>
    </servlet-mapping>
```
Deployment Descriptor: Annotation

``` 
    @WebServlet("/login")
    public class LoginServlet extends HttpServlet {

    }
```
jar --> java archive --> library
war --> web archive similar to jar but contains extra deployment descriptor adn folder structure which can be understood by servlet engines
ear --> Enterprise archive

WAR structure
``` 
    uberapp
        |
         index.html
         styles.css
         module.js
         WEB-INF
            |
            web.xml
            classes
                |
                LoginServlet.class
                RegisterServlet.class
                TripServlet.class

```
Maven Goals:
mvn compile
mvn package --> uses jar plugin or war plugin based on packaging config in pom.xml
mvn package --> triggers --> mvn compile

Embedded Jetty / Tomcat web server with servlet engine

mvn jetty:run
mvn jetty:run -Djetty.port=9999

    --> mvn compile
    --> mvn package --> war file
    --> starts jetty server / engine
    --> deploys war file on started jetty

http://localhost:8080/products

Jetty --> Servlet engine --> invoke ProductServlet
HTML --> static content
JSP --> Java Server Pages. static + dynamic content 

===================

MVC Architecture Pattern
Model View Controller

Model --> business data and logic [entity , Dao]
View --> Presentation logic [html , css , js, jsp]
Controller --> Application Logic [flow of application] --> servlet

Server Side Redirection: can be used as multi stage processing of data
SSR: one resource within the engine redirects to other resouce within the engine

========================================

Spring Boot Framework and JPA Frameworks

Spring Framework:
lightweight container manages life cycle of a bean and DI for beans.
 --> used for enterprise application.

Servlet container manages the life cycle of servlet and DI for servlet.

java version 1.2: Java Bean --> re-usable software component

Spring --> Bean: any object managed by spring container.

Spring depends on metadata to manage beans:
xml metadata or annotation.

```
    public interface EmployeeDao {
        void addEmployee(Employee e);
    }

    public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public addEmployee(Employee e) {
            ...
        }
    }
    public class EmployeeDaoMongoImpl implements EmployeeDao {
        public addEmployee(Employee e) {
            ...
        }
    }

    public class AppService {
        private EmployeeDao empDao;
        public void setEmployeeDao(EmployeeDao dao) {
            this.empDao = dao;
        }

        public void doTask(Employee e) {
            empDao.addEmployee(e);
        }
    }

beans.xml
<beans>
    <bean id="jdbc" class="pkg.EmployeeDaoJdbcImpl" />
    <bean id="mongo" class="pkg.EmployeeDaoMongoImpl" />
    <bean id="service" class="pkg.AppService" >
        <property name="employeeDao" ref="jdbc" />
    </bean>
</beans>

// to create Spring Container
ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

// get bean from container
AppService ser = ctx.getBean("service", AppService.class);
ser.doTask(new Employee()); // use the bean
```

Annotations can be used as metadata instead of xml:
1) @Component
2) @Repository
3) @Service
4) @Controller
5) @RestController
6) @Configuration
7) @ControllerAdvice

by placing any of the above annotations on class spring container instantiate it.

```
    public interface EmployeeDao {
        void addEmployee(Employee e);
    }

    @Repository
    public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public addEmployee(Employee e) {
            ...
        }
    }

    @Service
    public class AppService {
        @Autowired
        private EmployeeDao empDao;
        public void doTask(Employee e) {
            empDao.addEmployee(e);
        }
    }

ApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.scan("com.adobe");
ctx.refresh();

AppService service = ctx.getBean("appService", AppService.class);

```

https://github.com/spring-projects/spring-framework/blob/main/spring-jdbc/src/main/resources/org/springframework/jdbc/support/sql-error-codes.xml

```
 try {

 } catch(SQLException ex) {
    // Oracle
    if(ex.getErrorCode() == 1) }
     throw new PersistenceException("User already exists with email");
 }
```

Why Spring Boot?
Framework is built on top of Spring Framework.
Spring Boot 2.x is built on top of Spring Framework 5.x
Spring Boot 3.x is built on top of Spring Framework 6.x

* Highly opiniated framework
for example:
1) when web applications are built , tomcat is configured as embedded container
2) While building JDBC applications, Database connection pool is created out of box
3) many more features like MVC architecure Servlets ,,, are all configured.

DriverManager.getConnection() ---> Single connection, BAD for entriprise application. Latency in creating a connection and releasing

=========

 SpringApplication.run(SpringdemoApplication.class, args); ==>

@SpringBootApplication
--> @ComponentScan 
--> @Configuration
--> @EnableAutoConfiguration --> Opiniated objects like DB con pool

==========

Day 4:

JDBC --> Integration Library to connect to RDBMS
Maven -> build tool to manage dependencies, execute goals like compile, package, having embedded jetty web container plugin
Web application using Servlet Technology. Servlet and JSP [MVC]
packaging --> war

Spring Framework and Spring Boot framework.
* Light weight container to manage beans.
* Life cycle and dependency injection is managed by the Spring container.

Field employeeDao in com.adobe.springdemo.service.AppService required a single bean, but 2 were found:
	- employeeDaoJdbcImpl
	- employeeDaoMongoImpl

```

Solution 1:
Make one of the eligible bean as @Primary

@Repository
@Primary
public class EmployeeDaoJdbcImpl implements  EmployeeDao{
 
Solution 2:
Use Qualifier
@Repository
public class EmployeeDaoJdbcImpl implements  EmployeeDao{
@Repository
public class EmployeeDaoMongoImpl implements  EmployeeDao{

@Service
public class AppService {
    @Autowired
    @Qualifier("employeeDaoJdbcImpl")
    private EmployeeDao employeeDao;

Solution 3:
use @Profile
@Repository
@Profile("prod")
public class EmployeeDaoMongoImpl implements  EmployeeDao{

@Repository
@Profile("dev")
public class EmployeeDaoJdbcImpl implements  EmployeeDao{

@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao;

application.properties
spring.profiles.active=prod


CustomerService
    @Autowired
    @Qualifier("employeeDaoJdbcImpl")
    EmployeeDao employeeDao;


AdminService
    @Autowired
    @Qualifier("employeeDaoMongoImpl")
    EmployeeDao employeeDao;

```

Factory Methods:  required when programatically you need to instantiate objects and hand it over to Spring Container to manage.
Useful for 3rd party classes. Also useful if object creation is complex.
https://www.mchange.com/projects/c3p0/

DriverManger --> single connection to database
DataSource --> pool of database connections

All beans are singleton by default. 
Prototype bean scope --> different instances for each wiring.

==================================================

ORM --> Object Relational Mapping

Class <---> database table
fields <---> columns of table

ORM frameworks takes care of DDL and DML

```
Java Application <----> ORM <---> JDBC <---> RDBMS
```

ORM frameworks: Hibernate, TopLink, KODO, JDO, OpenJPA, ...

JPA: Java Persistence API is a specicifation for ORM
think JPA as interfaces, ORM as implementation.

```
Java Application <--> JPA <----> ORM <---> JDBC <---> RDBMS
```

Integrate Spring Framework with JPA.

ServletContext: env where servlets are managed.
ApplicationContext: Spring container where spring managed beans
PersistenceContext: container where entities are managed 

```
    @Bean
    public EntityManagerFactory emf(DataSource ds) {
        LocalContainerEntityManagerFactory emf = new LocalContainerEntityManagerFactory();

        emf.setDataSource(ds);
        emf.setJpaVendor(new HibernateJpaVendor());
        ...
        return emf;
    }

```

Spring Boot --> Spring Data JPA --> helps simplify using of JPA

With Spring Data JPA we just need to create interface extends JpaRepository.
Spring Data JPA generates implementation class for the interface, which has all the methods required for CRUD operations
It also gives options to add more methods.

```
    interface ProductDao extends JpaRepository<Product, Integer> {

    }
```

Spring using JPA project: spring boot application
dependecies:
1) lombok
2) mysql
3) jpa

Spring Data Jpa uses Hibernate as default ORM and hikariCP library for database connection pool.

https://docs.spring.io/spring-boot/appendix/application-properties/index.html

1) spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

ORM has to generate SQL suitable for MySQL

2) spring.jpa.hibernate.ddl-auto=update
--> DDL : create, alter, drop

a) spring.jpa.hibernate.ddl-auto=create
create tables when application starts. drop tables when application terminates
Good for testing environment only


b) spring.jpa.hibernate.ddl-auto=update
if table exists, map to existing tables. If required alter table
if table not exists, create table

c) spring.jpa.hibernate.ddl-auto=validate
map class to existing tables, no alter and create new ones.
Bottom to Top Approach. DBA has created tables, application developer just has to use it

```
JpaRepository<Customer, String>
manage Customer --> CRUD for Customer
PK is a String type

JpaRepository<Product, Integer>
manage Product, PK is a int type

```

update products set qty = 100 where 1 = 1;




