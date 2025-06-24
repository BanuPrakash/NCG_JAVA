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