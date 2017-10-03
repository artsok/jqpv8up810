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
```

11. 
```html
Remember that Stream has only two overloaded collect methods - one that takes a Collector as an argument and another one that takes a Supplier, BiConsumer, and BiConsumer. 
In this option, it is trying to pass two Collectors to the collect method. Therefore, it will not compile.  
1. public <R,A> R collect(Collector<? super T,A,R> collector) Performs a mutable reduction operation on the elements of this stream using a Collector. 
A Collector encapsulates the functions used as arguments to Stream.collect(Supplier, BiConsumer, BiConsumer), allowing for reuse of collection strategies 
and composition of collect operations such as multiple-level grouping or partitioning.  

2.public <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner) Performs a mutable reduction 
operation on the elements of this stream. A mutable reduction is one in which the reduced value is a mutable result container, 
such as an ArrayList, and elements are incorporated by updating the state of the result rather than by replacing the result. 
```


```html
public static <T> Collector<T,?,Long> counting() Returns a Collector accepting elements of type T that counts the number 
of input elements. If no elements are present, the result is 0.
```

```html
The Collector created by Collectors.toMap throws java.lang.IllegalStateException if an attempt is made to store a key that 
already exists in the Map. If you want to collect items in a Map and if you expect duplicate entries in the source, 
you should use Collectors.toMap(Function, Function, BinaryOperator) method. 
The third parameter is used to merge the duplicate entries to produce one entry. For example, in this case, you can do: 
Collectors.toMap(b->b.getTitle(), b->b.getPrice(), (v1, v2)->v1+v2) 
This Collector will sum the values of the entries that have the same key. Therefore, it will print :
```

```html
There are multiple flavors of Collectors.joining method and all of them are meant to join CharSequences and return the 
combined String. For example, if you have a List of Strings, you could join all the elements into one long String 
using the Collectors returned by these methods. You should check their JavaDoc API description for details.
```

11. Collections Operations with Lambda

```html
 Here are a few important things you need to know about Optional class: 
 1. Optional has a static method named of(T t) that returns an Optional object containing the value passed as argument. 
 It will throw NullPointerException if you pass null. If you want to avoid NullPointerException, 
 you should use Optional.ofNullable(T t) method. This will return Optional.empty if you pass null.  
 
 2. You cannot change the contents of Optional object after creation. Optional does not have a set method. 
 Therefore, grade.of, although technically correct, will not actually change the Optional object referred to by grade. 
 It will return a new Optional object containing the passed argument.  
 
 3. The orElse method returns the actual object contained inside the Optional or the argument passed to this method if 
 the Optional is empty. It does not return an Optional object. Therefore, print(grade1.orElse("UNKNOWN")) 
 will print UNKNOWN and not Optional[UNKNOWN].  
 
 4. isPresent() returns true if the Optional contains a value, false otherwise.  
 
 5. ifPresent(Consumer ) executes the Consumer object with the value if the Optional contains a value. 
 Not that it is the value contained in the Optional that is passed to the Consumer and not the Optional itself.
```

```java
boolean flag = values.stream().allMatch(str->str.equals("Alpha")); 
Optional<String> opt = values.stream().findFirst();
Optional<String> opt = values.stream().findAny();
boolean flag = values.stream().anyMatch(str->str.equals("Alpha"));
```

You need to know the details of the following methods of Stream interface: allMatch, noneMatch, anyMatch, findFirst, and findAny.
Please see the JavaDoc API description of these methods: https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

It is important to note that all of these are short-circuiting terminal operations. 
This means, the given predicate will not be executed for each element of the stream if the result can be determined by 
testing an element in the beginning itself. For example, if you invoke predicate on the given stream, 
the predicate will return false for the first element and therefore there is not need for it to be executed only rest of the element.









###Lambda CookBook
1. The JavaDoc API description explains exactly how the merge method works. 
You should go through it as it is important for the exam.  
public V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)  
If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value. 
Otherwise, replaces the associated value with the results of the given remapping function, or removes if the result is null. 
This method may be of use when combining multiple mapped values for a key. For example, to either create or append a String msg to a value mapping:   map.merge(key, msg, String::concat)  If the function returns null the mapping is removed. If the function itself throws an (unchecked) exception, the exception is rethrown, and the current mapping is left unchanged.  Parameters: key - key with which the resulting value is to be associated value - the non-null value to be merged with the existing value associated with the key or, if no existing value or a null value is associated with the key, to be associated with the key remappingFunction - the function to recompute a value if present  Returns: the new value associated with the specified key, or null if no value is associated with the key  Throws: UnsupportedOperationException - if the put operation is not supported by this map (optional) ClassCastException - if the class of the specified key or value prevents it from being stored in this map (optional) NullPointerException - if the specified key is null and this map does not support null keys or the value or remappingFunction is null 
 
2. Arrays class has several overloaded stream methods. You need to know them for the exam.  
To make it easy to understand, you can divide them into two kinds - one that takes all the elements of an array 
and puts them into the stream and another one that takes only those elements that fall between the start and end range arguments. 
The starting index is inclusive while the ending index is exclusive (indexing starts with 0). 

For example, Arrays.stream(cgpa, 1, 2) will return an IntStream with one element i.e. 3.  

Further, based on the type of the input array, there are 4 kinds of stream methods in Arrays class. 
One kind for each of int, double, long, and T (where T is generic). 
Thus, for example, you have Arrays.stream(doubleArray) that returns a DoubleStream, Arrays.stream(intArray) 
that returns an IntStream, and Arrays.stream(stringArray) that returns a Stream<String>.
```java
int[] cgpa =  { 2, 3, 2, 4, 3, 2 };
OptionalDouble od = Arrays.stream(cgpa, 0, 6).filter(x->x>=2&&x<3).average(); 
System.out.println(od.orElse(0.0));
``` 
### Date/Time Api
```html
The following is from JavaDoc API description of the toString methods of Duration and Period.  
Duration.toString:  It generates a string representation of the duration object using ISO-8601 seconds based representation, 
such as PT8H6M12.345S.  The format of the returned string will be PTnHnMnS, where n is the relevant hours, minutes or seconds part 
of the duration. Any fractional seconds are placed after a decimal point i the seconds section. 
If a section has a zero value, it is omitted. The hours, minutes and seconds will all have the same sign. 
Examples:  
   "20.345 seconds"                 -- "PT20.345S     
   "15 minutes" (15 * 60 seconds)   -- "PT15M"     
   "10 hours" (10 * 3600 seconds)   -- "PT10H"     
   "2 days" (2 * 86400 seconds)     -- "PT48H"  
   
Note that multiples of 24 hours are not output as days to avoid confusion with Period.  
Period.toString: Outputs this period as a String, such as P6Y3M1D. 
The output will be in the ISO-8601 period format. A zero period will be represented as zero days, 'P0D'.
```

 
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
