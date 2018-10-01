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









### Lambda CookBook
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

# Optional
```html
OptionalInt
OptionalDouble
OptionalLong
OptionalBoolean
Optional
```
Examples:
https://www.leveluplunch.com/java/examples/java-util-optionallong-example/
https://www.leveluplunch.com/java/examples/java-util-optionalint-example/
https://www.leveluplunch.com/java/examples/java-util-optionaldouble-example/

### Lambda Expressions

1. Не возможно вызвать строчку inner1.doA(). Нельзя получить доступ к методу, который создали в анонимном классе.  
```java
public class TopClass {
    public Inner inner1 = new Inner() {
        public void doA() {
            System.out.println("A");
        }
    };
    public void doA() {
        inner1.doA();
    }
}

class Inner {
    
}
```

2. Анонимный класс никогда не может быть статичным. Даже если он создан в статическом методе. 
Анонимные классы неявно final.

### Built-in Lambda Types

1. Требуется понимание основных функциональных интерфейсов Supplier, Consumer, Function, UnaryOperator, Predicate.
Читать: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

```java
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}


@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     *
     * @see #compose(Function)
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}


@FunctionalInterface
public interface Consumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}

@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @return a unary operator that always returns its input argument
     */
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}


@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * Returns a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}.
     *
     * @param <T> the type of arguments to the predicate
     * @param targetRef the object reference with which to compare for equality,
     *               which may be {@code null}
     * @return a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}
     */
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }
}
```

3. Чтобы избежать auto-boxing/unboxing используйте спецальные Stream (IntStream, LongStream, and DoubleStream),совместно с определенными функционаьными 
интерфейсами: IntFunction, IntConsumer, IntSupplier и т.д. 

4. Обращать внимание на типы, которые используюся в функциональных интерфейсах. Много вопросов с подвохом.
```java
    String val1 = "Hello";
    StringBuilder val2 = new StringBuilder("world");
    UnaryOperator<String> uo1 = s1 -> s1.concat(val1);
    UnaryOperator<String> uo2 = s -> s.toUpperCase();
    uo1.apply(uo2.apply(val2)); 
```

Запомните StringBuilder и StringBuffer не наследуются от класса String. 
UnaryOperaty uo2 тип класса String и нельзя его применить к объекту StringBuilder.


### Java Collections and Streams with Lambda

1. Метод forEach принимает интерфейс java.util.function.Consumer
```java
List<Integer> names = Arrays.asList(1,2,3);
names.forEach(x-> x = x + 1);
```
, где x - это временная переменная и данная функция не затронет изминения списка names.



### Collections Operations with Lambda

1. Помнить, что возвращают методы: findFirst, findAny, anyMatch, allMatch. Два возвращают boolean, два Optional<T>.
```java
boolean flag = values.stream().allMatch(str->str.equals("Alpha")); 
Optional<String> opt = values.stream().findFirst();
Optional<String> opt = values.stream().findAny();
boolean flag = values.stream().anyMatch(str->str.equals("Alpha"));
```

2. Операция average() для IntStream, DoubleStream, LongStream возвращают OptionalDouble. Если нет результата, то будет OptionalDouble.empty.

3. Операция sum() возвращает тип значения используемого Stream. Например: При использовании LongStream метода sum() будет возвращаться значение long.
Если Stream будет пустой, то sum() метод вернет 0 или 0.0. 

### Lambda CookBook

1. Использование метода flatMap(). Похож на map, но может создавать из одного элемента несколько, т.е элемент stream преобразуется в элементы из данного stream.
```java
List<String> l1 = Arrays.asList("a","b");
List<String> l2 = Arrays.asList("1","2");
Stream.of(l1, l2).flatMap((x)->x.stream()).forEach((x)->System.out.println(x));
```
В данном примере создается stream с элементами l1, l2. Далее, flatMap создает ОДИН stream с элементами, которые были в двух stream, т.е объединяет их. 
Оснавная мысль на английском: "flatMap() to flatten a Stream of Stream of values into just a Stream of values".

2. В Java 8 API, можно создать тремя способами stream.
a) Метод stream() добавлен в интерфейс Collection;
```java
private static List<Employee> empList = Arrays.asList(arrayOfEmps);
empList.stream();
```
б) Метод Stream.of(..):
```java
Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
```
в) Через Stream.builder():
```java
Stream.Builder<Employee> empStreamBuilder = Stream.builder();
empStreamBuilder.accept(arrayOfEmps[0]);
empStreamBuilder.accept(arrayOfEmps[1]);
empStreamBuilder.accept(arrayOfEmps[2]);
Stream<Employee> empStream = empStreamBuilder.build();
```

