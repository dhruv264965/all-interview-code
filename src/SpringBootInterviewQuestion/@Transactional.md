# @Transactional

`@Transactional` is an annotation provided by Spring to manage transactions declaratively —
it ensures that a series of operations either complete successfully (commit) or fail together (rollback).

---

## 1. What it does

> `@Transactional` is used to manage database transactions in Spring. It ensures that all operations
> inside a method are executed in a single transaction context. If any exception occurs, the transaction
> is rolled back automatically.

---

## 2. How it works under the hood

> Spring uses **AOP (Aspect-Oriented Programming) proxies** to wrap the method annotated with
> `@Transactional`. Before the method executes, Spring opens a transaction. If the method completes
> successfully, it **commits**. If a runtime exception occurs, it **rolls back** the transaction.

---

## 3. Where and how to use

- Can be applied at **class** or **method** level.
- Typically used in the **Service layer** (not recommended in DAO layer directly).

**Real project example:**
> In my project, we used `@Transactional` in the service layer to wrap complex operations like placing
> an order. The method saved the order, updated inventory, and sent notifications. If any step failed,
> Spring would roll back the DB changes automatically, ensuring data consistency. We also used
> `rollbackFor = Exception.class` when handling checked exceptions.

---

## 4. Key Attributes

```java
@Transactional(
    rollbackFor = Exception.class,
    propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED,
    timeout = 30,
    readOnly = true
)
```

| Attribute       | Description                                      |
|-----------------|--------------------------------------------------|
| `rollbackFor`   | Rollback for specific exception types            |
| `propagation`   | How the transaction propagates (REQUIRED, etc.)  |
| `isolation`     | Isolation level (READ_COMMITTED, etc.)           |
| `timeout`       | Max seconds before transaction times out         |
| `readOnly`      | Hint to optimize read-only transactions          |

---

## 5. Programmatic Transaction API

| Method             | Description                                       |
|--------------------|---------------------------------------------------|
| `getTransaction()` | Begins a new transaction or joins an existing one |
| `commit()`         | Commits the transaction                           |
| `rollback()`       | Rolls back the transaction                        |

> I primarily use the declarative `@Transactional` annotation, but I understand that under the hood,
> Spring uses `PlatformTransactionManager` to manage transactions. It internally calls methods like
> `getTransaction()`, `commit()`, and `rollback()`. For fine-grained control, we can also use
> `TransactionTemplate` for programmatic transactions.
