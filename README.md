# Oracle Certified Professional - Upgrade To SE 8 From SE 7
Подготовка к сертификации.

# What To Read
1. https://habrahabr.ru/sandbox/108078/
2. https://www.youtube.com/watch?v=3uIWoHlS51g
3. https://habrahabr.ru/company/luxoft/blog/270383/


# What To Remember
1. Remember that the parameter list part of a lambda expression declares new variables that are used in the body part of that lambda expression. However, a lambda expression does not create a new scope for variables. Therefore, you cannot reuse the local variable names that have already been used in the enclosing method to declare the variables in you lambda expression. It would be like declaring the same variable twice 

2. To answer this question, you need to know two things - distinction between "intermediate" and "terminal" operations and which operations of Stream are "intermediate" operations.
   
   A Stream supports several operations and these operations are divided into intermediate and terminal operations. The distinction between an intermediate operation and a termination operation is that an intermediate operation is lazy while a terminal operation is not. When you invoke an intermediate operation on a stream, the operation is not executed immediately. It is executed only when a terminal operation is invoked on that stream. In a way, an intermediate operation is memorized and is recalled as soon as a terminal operation is invoked. You can chain multiple intermediate operations and none of them will do anything until you invoke a terminal operation, at which time, all of the intermediate operations that you invoked earlier will be invoked along with the terminal operation.
   
   You should read more about this here: http://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#StreamOps
   
   It is easy to identify which operations are intermediate and which are terminal. All intermediate operations return Stream (that means, they can be chained), while terminal operations don't.
   
   filter, peek, and map are intermediate operations. Since the code does not invoke any terminal operation on the stream, the calls to these intermediate method do nothing. Therefore, no output is produced by the given code. 
   
   count, forEach, sum, allMatch, noneMatch, anyMatch, findFirst, and findAny are terminal operations.

```java
   public static void main(String[] args) { 
      Employee e = new Employee(); 
      System.out.println(validateEmployee(e, e->e.age<10000));
   }
 ```
 3. In case of classes, you cannot override a static method with a non-static method and vice-versa.
    However, in case of interfaces, it is possible for a sub interface to have a non-static (i.e. default) method with 
    the same signature as that of a static method of a super interface. This is because static methods are not inherited in any sense in the sub-interface and that is why it is allowed to declare a default method with the same signature. The reverse is not true though. That is, you cannot have a static method in a sub-interface with the same signature as that of a default method in a super interface.
    You cannot override a default method of an interface with a static method in sub-interface or even in a class that implements that interface.
    
 4. An interface method cannot be default and static at the same time because a default method is always an instance method.
 
 5. There is no problem with the code. It is perfectly valid for a subinterface to override a default method of the base interface. A class that implements an interface can also override a default method. It is valid for MyHouse to say that it implements Bungalow as well as House even though House is redundant because Bungalow is a House anyway.  It will actually print 101 Smart str.  

```java 
interface House{
    public default String getAddress(){      
        return "101 Main Str";   } }  interface Bungalow extends House{   public default String getAddress(){      return "101 Smart Str";   } }  class MyHouse implements Bungalow, House{  }  public class TestClass {    public static void main(String[] args) {     House ci = new MyHouse();  //1     System.out.println(ci.getAddress()); //2   } } 
``` 

 6. Interfaces are always abstract. You can but you don't have to mark them abstract. Methods of an interface that are not marked default or static are also always abstract. You don't have to mark them as abstract.
 
 7. 
 ```java
 public default String getId(){ super.getId(); }
 ```
 super.methodName(...) is a valid way to invoke a super class's method from anywhere within a subclass's method. But it works only for classes. You cannot invoke the interface's default method using this technique. To do so, you need to add interface name before super i.e. Account.super.getId();
 
8. 
```java
static void compute();
```
An interface can have a static method but the method must have a body in that case because a static method cannot be abstract.

9. 
```java
public class BookStore {    private static final int taxId = 300000;    private String name;    public String searchBook( final String criteria )    {       int count = 0;       int sum = 0;       sum++;       class Enumerator       {          String interate( int k)          {             //line 1             // lots of code             return "";          }          // lots of code.....       }       // lots of code.....       return "";    } }
```

taxId, name, criteria, count, k 

If the inner class is non static, all the static and non-static members of the outer class are accessible (otherwise only static are accessible) So option 1, 2 are valid.
Prior to java 8, only final local variables were accessible to the inner class but in Java 8, even effectively final local variables of the method are accessible to the inner defined in that method as well. So option 4 is correct.


10. Java Collections and Streams with Lambda
```html
As per https://docs.oracle.com/javase/tutorial/collections/streams/:

A pipeline contains the following components:
A source: This could be a collection, an array, a generator function, or an I/O channel. In this example, the source is the collection roster.
Zero or more intermediate operations: An intermediate operation, such as filter, produces a new stream.
A terminal operation.
```

```html
public static <T> Collector<T,?,Long> counting() Returns a Collector accepting elements of type T that counts the number of input elements. If no elements are present, the result is 0.
``'
 
# All Functional Interface in java.util.function
```  
BiConsumer
BiFunction
BinaryOperator
BiPredicate
BooleanSupplier
Consumer
DoubleBinaryOperator
DoubleConsumer
DoubleFunction
DoublePredicate
DoubleSupplier
DoubleToIntFunction
DoubleToLongFunction
DoubleUnaryOperator
Function
IntBinaryOperator
IntConsumer
IntFunction
IntPredicate
IntSupplier
IntToDoubleFunction
IntToLongFunction
IntUnaryOperator
LongBinaryOperator
LongConsumer
LongFunction
LongPredicate
LongSupplier
LongToDoubleFunction
LongToIntFunction
LongUnaryOperator
ObjDoubleConsumer
ObjIntConsumer
ObjLongConsumer
Predicate
Supplier
ToDoubleBiFunction
ToDoubleFunction
ToIntBiFunction
ToIntFunction
ToLongBiFunction
ToLongFunction
UnaryOperator
```
