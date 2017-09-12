# Oracle Certified Professional - Upgrade To SE 8 From SE 7
Подготовка к сертификации.

# What To Read
1. https://habrahabr.ru/sandbox/108078/
2. https://www.youtube.com/watch?v=3uIWoHlS51g
3. https://habrahabr.ru/company/luxoft/blog/270383/


# What To Remember
1. Remember that the parameter list part of a lambda expression declares new variables that are used in the body part of that lambda expression. However, a lambda expression does not create a new scope for variables. Therefore, you cannot reuse the local variable names that have already been used in the enclosing method to declare the variables in you lambda expression. It would be like declaring the same variable twice 

``
   public static void main(String[] args) { 
   Employee e = new Employee(); 
   System.out.println(validateEmployee(e, e->e.age<10000));}
 ``  
 
 
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