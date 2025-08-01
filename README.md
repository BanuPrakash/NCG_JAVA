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

CommandLineRunner is an interface in Spring Boot that indicates a bean should execute its run() method as soon as the Spring container is created and initialized.

Settings --> build, execution, ..
Compiler --> Annotation Processor --> select "orderapp" --> obtain processor path from classpath.

mvn compile.


Task:

Complete ProductClient to 
1) list all products,
2) get product by id.
3) add a product [each time pass different product to avoid duplicates]

Mapping associations
* one to many
* many to one
* one to one
* many to many

https://martinfowler.com/bliki/DomainDrivenDesign.html
https://martinfowler.com/bliki/BoundedContext.html


@JoinColumn with @ManyToOne introduces FK in owning table
@JoinColumn with @OneToMany introduces FK in child table

====

Without Cascade:
```
    one order has many line items;
    @OneToMany
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();

    1 order has 4 items
    orderDao.save(order);
    itemDao.save(i1);
    itemDao.save(i2);
    itemDao.save(i3);
    itemDao.save(i4);

    To delete
    orderDao.delete(order);
    itemDao.delete(i1);
    itemDao.delete(i2);
    itemDao.delete(i3);
    itemDao.delete(i4);
```


With Cascade:
```
    one order has many line items;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();

    1 order has 4 items

    orderDao.save(order); // takes care of saving all items in order
    orderDao.delete(order); // takes care of deleting items of order
```

LAZY and EAGER fetching:

1) LAZY fetching --> default for OneToMany
    ```
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();

     orderDao.findById(1);
        select * from orders where oid = 1;
    line items are not fetched
    ```
2) EAGER fetching
```
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();

orderDao.findById(1);
        select * from orders where oid = 1;
        select * from line_items where order_fk = 1;

```
   
ManyToOne by default is EAGER fetching

With Cascade and EAGER fetching we don't need ItemDao
====


 @Transactional ensures that the method is atomic operation, all operations commit or rollback if any exception occurs.

 ==========
 New Spring Boot with different database.
 Vehicle Rental application
 1) entities required
    Vehicle
    Customer
    Rental

```
    vehicles
    REG_NO.         | type      | fuel_type | cost_per_day
    UP 10 A 1234        SEDAN       ELECTIC     3500.00
    KA 9 NC 5112        HATCHBACK   PETROL      2600.00


    customer similar to already done in order app

    rentals

    id | vehicle_fk  | customer_fk | rent_from | rent_till

```
After Mapping, insert customers, vehicles data in the backend MySQL prompt

Use case 1: Rent a Vehicle

```
id | vehicle_fk     | customer_fk           | rent_from         | return_date
1      KA 9 NC 5112   linda@adobe.com           24 JUNE 2025        NULL
```

Use case 2: Return a Vehicle --> DIRTY CHECKING

```
id | vehicle_fk     | customer_fk           | rent_from         | return_date
1      KA 9 NC 5112   linda@adobe.com           24 JUNE 2025        25 JUNE 2025
```

select c.fname, c.email, o.order_date, o.total  from orders o inner join customers c on c.email = o.customer_fk

==========

Day 4 Recap:
* @Entity, @Id --> compulsory
* @Column, @Table --> Optional
* @OneToMany, @ManyToOne, @JoinColumn
* Cascade, LAZY vs EAGER fetching

Ward --> Booths --> Voters 

* @Transactional boundary generally applied to a method, can be applied at a class level if every method is transactional

* @Transactional ensures that the method is atomic, if no exception is propagated from method --> it commits, else if exception is propagated from a method marked as transactional --> it rollsback.

* Within transactional boundary if an entity becomes dirty --> JPA flushes the state to database --> DIRTY CHECKING --> Update SQL

* SQL Projection, @Query for SQL or JP-QL

======

Day 5:

Order Date: in database 2025-06-26 16:39:33.817000

Client sends : 2025-06-26

Spring MVC Module
* Traditional web applications like how servlet and JSP works --> Server Side Rendering --> JSP and ThymeLeaf 

* Client Side Rendering: server sends representation in various formats like XML/ JSON of resource state
    * Less Payload [only xml or json and sent and received]
    * Heterogenous clients like Mobile / TV and Web clients like Browser.

RESTful Web Services: Roy Fielding
* Resource 
* Representation [ state of resource]
* Various Formats : Content Negotiation

Uniform Identifier to refer to a Resource
* URLs --> plural nouns refers to a resource
http://server.com/api/products
http://server.com/api/customers

* HTTP methods for CRUD operations
GET --> to fetch resource[s]
POST --> to create a subresource 
PUT/PATCH --> to update resource
DELETE --> to delete a resource // AVOID this, not recommended

1) To fetch all products
GET http://localhost:8080/api/products

2) To fetch products by range --> sub set --> query parameter [?]
GET http://localhost:8080/api/products?low=500&high=2000
Pagination
GET http://localhost:8080/api/products?page=1&size=20

3) To fetch based on path parameter [/]
get product whose id is 3
GET http://localhost:8080/api/products/3

get all orders of banu@gmail.com
GET http://localhost:8080/api/customers/banu@gmail.com/orders

HTTP header
accept:application/json
accept:text/xml

====

4) POST http://localhost:8080/api/products
  ```
   accept: application/json
   content-type: application/json

   {
    name : "A",
    price: 4535.22
   }

```

5) PUT http://localhost:8080/api/products/3

    ```
   accept: application/json
   content-type: application/json

   {
    name : "A2",
    price: 9000.22
   }

```

GET and DELETE no payload --> Safe methods
PUT and POST --> contains payload --> Not Safe methods

GET is IDEMPOTENT
PUT and POST are not IDEMPOTENT

===========================

Spring Boot MVC Module provides:
1) Embedded Tomcat Servlet Container
2) DispatcherServlet --> Works like a FrontController
3) HandlerMapping --> mapping URLs to @Controller and @RestController
4) By default it provies Jackson library for Java <--> JSON ContentNegotiation handler

Instead of Jackson --> jettison , GSON, Moxy if you want to use, explicitly it has to be configured

```
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("api/products")
    public class ProductController {
        private final OrderService service;

        @GetMapping()
        public List<Product> getProducts() {
            return service.getProducts();
        }

        @PostMapping()
        public Product addProduct(@RequestBody Product p) {
        //  public @ResponseBody Product addProduct(@RequestBody Product p) {
            return service.addProduct(p);
        }
    }
```

Spring has HttpMessageConverters:
String --> int, double, long, short, byte

PUT vs PATCH
PUT and PATCH are both used for updating resources, but they differ in their approach. PUT replaces the entire resource with the data provided in the request body, while PATCH only updates specific fields of the resource.

====

Task:
Complete Rental application for renting and returning a vehicle with RESTful.

* @RestController
* @RequestMapping
* @Getmapping(), @PostMapping(), @Putmapping(), @PatchMapping() and @DeleteMapping

* @RequestParam --> Query Parameters
* @PathVariable --> Path Parameter
* @RequestBody --> Payload to Java Object
* @ResponseBody [optional] --> Java Object to JSON / XML payload
* @DateTimeFormat

=================

Exception Handling and Validation.

@ControllerAdvice

Validation:
https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary

```
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    Validation Constraints are added to Product.java

    public Product addProduct(@RequestBody @Valid Product product) {
        return  service.addProduct(product);
    }

```


```
MethodArgumentNotValidException: Validation failed for argument [0] in public com.adobe.orderapp.entity.Product com.adobe.orderapp.api.ProductController.addProduct(com.adobe.orderapp.entity.Product) with 3 errors: 

[Field error in object 'product' on field 'name': rejected value []; codes [NotBlank.product.name,NotBlank.name,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [product.name,name]; arguments []; default message [name]]; default message [Name is required!!!]] 

    [Field error in object 'product' on field 'quantity': rejected value [0]; codes [Min.product.quantity,Min.quantity,Min.int,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [product.quantity,quantity]; arguments []; default message [quantity],1]; default message [Quantity 0 has to more than 1]] 

[Field error in object 'product' on field 'price': rejected value [2.0]; codes [Min.product.price,Min.price,Min.double,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [product.price,price]; arguments []; default message [price],10]; 
default message [Price 2.0 has to more than 10]]

```



==========


Unit Testing:
--> done by developers
--> Unit : A smallest compilable thing like a class
* JUnit and TestNG are unit testing frameworks
Spring boot bundles 
* JUnit out of the box
* Mockito for mocking API so that we can test objects in isolation

Controller --> Service --> Repository --> database

To test service we need to mock Repository
To test controllers we need to mock service.

* Hamcrest --> Matchers for asserting collections
https://hamcrest.org/JavaHamcrest/tutorial
* JsonPath
https://jsonpath.com/

WebMvcTest --> focuses on Spring MVC tests. doesn't create entire Spring Container, instead creates a contaniner with just DispatcherServletTest, HandlerMapping
@WebMvcTest(ProductController.class) --> ProductController is instantiated.


mock OrderService is created:
 @MockitoBean
 private OrderService service;

 MockMvc --> used to perform HTTP CRUD operations like GET, POST, 

RESTful API documentation:
* RAML
* OpenAPI --> Swagger implementation

```
 <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>

```

http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/

================

Task 2:
Employee Project assign: can be completed only with ManyToOne association

```
    Employee
    emp_id | email | fname | lname | hire_date | end_date

    client
    client_id | name

    project
    project_id | name | start_date | end_date | client_fk


    public class EmployeeProject {
        id
        @ManyToOne
        @Joincolumn(name="project_fk")
        Project project; // 
        @ManyToOne
        @Joincolumn(name="empoyee_fk")
        Employee employee; //
        String role;
        Date startDate;
        Date endDate;
    }

    employee_project
    id | project_fk | employee_fk | role | start_date | end_date


```

https://www.database-answers.com/data_models/


==================

Employee <---> Project looks like  many to many
Movie <---> Actor looks like many to many
``` 
    Many to Many
     Employee * <---> * Project
     Employee
    emp_id | email | fname | lname | hire_date | end_date 

     project
    project_id | name | start_date | end_date | client_fk

    Join table
    employees_project
    emp_id | project_id

    many times we need to store extra attributes like role of employee in project,
    employee working duration in project

     Join table has extra fields, so we need a link class to map
    employees_project
    emp_id | project_id | start_date | end_date | role

    Employee 1 -> *  EmployeeProject * --> 1 Project
```

One To One Mapping Employee 1 <----> 1 Laptop

```
    Employee
        empid | name | laptop_fk

    Laptop
        serail_no | description | make | HDD 

    OR
    class Employee {

    }

    class Laptop {

        @OneToOne
        @JoinColumn("employee_fk")
        Employee emp;
    }

    Employee
        empid | name 

    Laptop
        serial_no | description | make | HDD | employee_fk

If we need to track complete history of laptop assigned [start_date, end_date, to which employee]
```

Dirty Checking:
Dirty checking is a mechanism, most notably used by ORMs like Hibernate, that automatically detects changes made to managed entities within a session and synchronizes them with the database during the transaction's flush phase, eliminating the need for manual UPDATE or SAVE statements for every change.

Any changes in Transactional boundary, entity will be synchronized with database
by issuing UPDATE SQL --> Entity becomes dirty 
```
    // Programatic tx
      public void updateQty(int id, int qty) {
        Session session = sessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Product p = productDao.findById(3).get(); // get from database
        p.setQuantity(qty); // product become dirty
        tx.commit();
        session.close();
        // no need for explcit UPDATE SQL / JP-QL, ORM issues them for you.
    }

    // declarative tx
    @Transactional
    public void updateQty(int id, int qty) {
        Product p = productDao.findById(3).get(); // get from database
        p.setQuantity(qty); // product become dirty
        // no need for explcit UPDATE SQL / JP-QL, ORM issues them for you.
    }

```

Further learning:
Async, HATEOAS, Caching, Scheduling, Observable, MicroMeter, ..

==========================

Spring Security

Authentication and Authorization.

```
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

```

By including above security module our project is secured.
1) All our resources are protected by default.
2) creates one user with username ="user" and generated password
Using generated security password: 92fa46e0-2b4f-445d-b0b3-4a6326138fee
3) login page
4) logout page
http://localhost:8080/logout

Filters are similar to Servlets which execute within Servlet Container.

====

* Having In memory users
* Role based access --> Authorization
https://docs.spring.io/spring-security/reference/servlet/appendix/database-schema.html

https://bcrypt-generator.com/

RESTful Services ---> Stateless [one of the charactersticis]

RESTful APIs are stateless, meaning that each request made by the client to the server contains all the information necessary for the server to fulfill the request, without relying on any previous requests or server-side storage. 
No Converstational state of client, each request should be treated as a fresh request.


* Problem with Statefulness
1) Clients are heterogenous
2) Session stickiness in Cluster environment, scability is an issue

Solution: use tokens --> JWT token
https://jwt.io/

```
// HEADER
{
  "alg": "HS256",
  "typ": "JWT"
}
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
// PAYLOAD
{
  "sub": "banu@gmail.com",
  "iat": 1516239022,
  "exp": 1899999212,
  "iss": "https://security.adobe.com",
  "roles/authorities": "ROLE_ADMIN, ROLE_CUSTOMER"
}
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.
// SIGNATURE
HMACSHA256(
      base64UrlEncode(header) + "." +
      base64UrlEncode(payload),
      a-string-secret-at-least-256-bits-long)
KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30

```

Authorization Server: Private keys to generate tokens
Resource Server: public keys can be used to validate token

Custom UserDetailsService with JPA instead of default JdbcAuthentication Manager.

```

mysql> desc users;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| email    | varchar(255) | NO   | PRI | NULL    |       |
| password | varchar(255) | YES  |     | NULL    |       |
| username | varchar(255) | YES  |     | NULL    |       |
+----------+--------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

mysql> desc roles;
+-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| name        | varchar(255) | NO   | PRI | NULL    |       |
| description | varchar(255) | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+
2 rows in set (0.01 sec)

mysql> desc users_roles;
+---------+--------------+------+-----+---------+-------+
| Field   | Type         | Null | Key | Default | Extra |
+---------+--------------+------+-----+---------+-------+
| user_id | varchar(255) | NO   | PRI | NULL    |       |
| role_id | varchar(255) | NO   | PRI | NULL    |       |
+---------+--------------+------+-----+---------+-------+

```

* Repository
* JWTFilter
* SecurityConfig
* JWTConfig
* UserDetailsService [ custom]

==============

Security JWT dependencies

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
        </dependency>
```

* User and Role ManyToMany association
* UserDao
* For registration -- SignUpRequest DTO
* for Login -- SignInRequest DTO
* On success -- JwtTokenResponse DTO
* Services:
1) UserDetailsServiceImpl
Spring Security --> loadUserByUsername() --> UserDao loadByEmail()

Login:
Generate Token
JwtService --> generateToken() 

 http://localhost:8080/auth/login [email/password] AuthController --> login() 
 AuthenticationService --> signIn() --> authenticationManager.authenticate()
 --> jwtService.generateToken(user);


Access Resources: Validate token --> JwtAuthenticationFilter


Registration:
1) 
```

POST http://localhost:8080/auth/register
Content-Type: application/json
Accept: application/json

{
  "email": "peter@adobe.com",
  "password": "secret",
  "username": "Peter",
  "roles": [ {
    "name": "ROLE_USER",
    "description": "Has basic rights"
  }]
}
```
request.requestMatchers("/auth/**").permitAll()

2) AuthController register()
3) AuthenticationService --> signup() -->  userDao.save(user); 

========================

Login Flow:

1) 
```
### @name ="Admin Login"

POST http://localhost:8080/auth/login
Content-Type: application/json
Accept: application/json

{
  "email": "anna@adobe.com",
  "password": "secret"
}
```
request.requestMatchers("/auth/**").permitAll()

2) AuthController --> login() --> AuthenticationService --> signIn() -->  authenticationManager.authenticate() --> UserDetailsService --> loadByEmail() -->  jwtService.generateToken(user);

{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm5hQGFkb2JlLmNvbSIsImlhdCI6MTc1MTI3NTQ1MSwiZXhwIjoxNzUxMjc2ODkxLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaXNzIjoiaHR0cHM6Ly9hdXRoc2VydmVyLmFkb2JlLmNvbSJ9.pQjxb-X4FVR77NqoKx6StwY3RnTLuShQCtORfMp2zQA"
}

====

Access Protected resources using JWT token

1) 
```


### @name="access Protected Resource"

GET http://localhost:8080/api/products
Accept: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm5hQGFkb2JlLmNvbSIsImlhdCI6MTc1MTI3NTQ1MSwiZXhwIjoxNzUxMjc2ODkxLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaXNzIjoiaHR0cHM6Ly9hdXRoc2VydmVyLmFkb2JlLmNvbSJ9.pQjxb-X4FVR77NqoKx6StwY3RnTLuShQCtORfMp2zQA

```

2) JwtAuthenticationFilter - doFilterInternal()

No Authorization Header  filterChain.doFilter(request, response); --> UsernamePasswordAuthenticationFilter

3) jwtService.extractUserName(jwt token); get completed details from database 
--> jwtService.isTokenValid(jwt, userDetails) --> temporarily store data in SecurityContext [not associated with JSESSIONID] --> check while permiting access --> once response is sent back delete SecurityContext.


{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm5hQGFkb2JlLmNvbSIsImlhdCI6MTc1MTI3NjQyMiwiZXhwIjoxNzUxMjc3ODYyLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaXNzIjoiaHR0cHM6Ly9hdXRoc2VydmVyLmFkb2JlLmNvbSJ9.kfTZwP4S8EiYgsraJHsbeJ-nDCyiJgD0AjPqcqPhdbM"
}

==============

