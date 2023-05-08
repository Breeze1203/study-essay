自定义异常的处理

Spring 支持使用控制器建议 (@RestControllerAdvice)的全局异常处理程序 ( @ExceptionHandler ) 进行异常处理。

```java
@RestControllerAdvice
public class GlobalExceptionHandle {
   @ExceptionHandler(SQLException.class)
    public StatusUtils SQLException(SQLException e){
       StatusUtils statusUtils = new StatusUtils();
       if(e instanceof SQLIntegrityConstraintViolationException){
           statusUtils.setMessage("该数据有关联数据，无法删除");
       }else {
           statusUtils.setMessage("数据库异常，操作失败");
       }
       return statusUtils;
   }
}
```

注释@RestControllerAdvice是@Component`注释的特化，因此可以通过类路径扫描自动检测到。它是一种拦截器，围绕着我们控制器中的逻辑，并允许我们对它们应用一些通用逻辑。

Rest Controller Advice 的方法（用 注释）在多个组件@ExceptionHandler之间全局共享，以捕获异常并将它们转换为 HTTP 响应。@Controller注释@ExceptionHandler指示我们要处理哪种类型的异常。实例exception`和`request将通过方法参数注入。

通过同时使用两个注解，我们可以：

- 控制响应的主体以及状态代码
- 在同一个方法中处理多个异常

怎么样`@ResponseStatus`？

`@RestControllerAdvice`注解告诉控制器返回的对象自动序列化为 JSON 并将其传递给对象`HttpResponse`。您只需要返回 Java 主体对象而不是`ResponseEntity`对象。但状态可能始终为 OK (200)，尽管数据对应于异常信号（例如 404 – Not Found）。`@ResponseStatus`可以帮助设置响应的 HTTP 状态代码：

```java
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
  // ...    
  return message;
}
```

## @RestControllerAdvice 和@ResponseEntity

@RestControllerAdvice如果不使用@ResponseBodyand @ResponseStatus，则可以返回ResponseEntity`对象。

```java
@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(...);
    
    return message;
  }
}
```

## @ControllerAdvice 与 @RestControllerAdvice

@RestControllerAdvice是@ControllerAdvice和@ResponseBody的组合

你可以理解为：

- `@RestControler`= `@Controller`+`@ResponseBody`
- `@RestControllerAdvice`= `@ControllerAdvice`+`@ResponseBody`

让我们用`@ControllerAdvice`and来做这件事`@ResponseBody`：

```java
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(...);
    return message;
  }
}
```

`@ControllerAdvice`如果不使用`@ResponseBody`and `@ResponseStatus`，则可以返回`ResponseEntity`对象。

```java
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage...);
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
  }
}
```