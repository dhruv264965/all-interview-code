# if we creating all the index for all column and that not a good practics it will take more memory . what is the best way to do

```text
ou’re 100% right 👍
Indexing every column is a bad practice — it wastes memory, slows DML, and hurts performance. Let’s talk about the right way professionals decide what to index.

❌ Why indexing all columns is BAD

Each index:

Uses extra storage

Slows down INSERT / UPDATE / DELETE

Increases redo & undo

Needs maintenance during data changes

So more indexes ≠ faster system.

✅ Best-practice strategy for indexing (REAL WORLD)
1️⃣ Index only columns used in WHERE, JOIN, ORDER BY, GROUP BY
Ask these questions:

Is this column frequently used in WHERE?

Is it used in JOIN conditions?

Is it used in ORDER BY / GROUP BY?

Is it used to enforce uniqueness?

If NO → don’t index.

2️⃣ Index columns with high selectivity
Selectivity = many unique values
Column	Index?	Why
employee_id	✅ YES	Primary key
mobile_no	✅ YES	Highly unique
department	❌ NO	Few repeated values

Indexing low-selectivity columns is often useless.

3️⃣ Use composite indexes (very important)

Instead of:

INDEX(department)
INDEX(salary)


Use:

CREATE INDEX idx_emp_dept_sal
ON employee (department, salary);


✔ Uses one index
✔ Faster queries
✔ Less memory

Column order matters!
WHERE department = 'CSE'
AND salary > 50000


Correct index order:

(department, salary)

4️⃣ Don’t index small tables

Rule of thumb:

< 10,000 rows → no index needed

Full table scan is faster

Oracle is very smart about this.

5️⃣ Avoid indexing frequently UPDATED columns

Bad candidates ❌:

last_login_time

status_flag

updated_at

Why?

Index must be updated on every change

6️⃣ Use function-based indexes ONLY when needed

Example:

WHERE UPPER(email) = 'ABC@XYZ.COM'


Then:

CREATE INDEX idx_emp_email_upper
ON employee (UPPER(email));


Otherwise → don’t.

7️⃣ Monitor before adding indexes
Find unused indexes
SELECT index_name
FROM user_indexes
WHERE index_name NOT IN (
    SELECT index_name FROM v$object_usage
);


(After monitoring is enabled)

8️⃣ Golden rule: PRIMARY KEY & UNIQUE already create indexes

❌ Don’t do this:

CREATE INDEX idx_emp_id ON employee(id);


✔ Oracle already did it for you.

🎯 Best indexing checklist (save this)

✅ PK / UNIQUE columns
✅ Frequent WHERE / JOIN columns
✅ High-selectivity columns
✅ Composite indexes over multiple single indexes
❌ Low-cardinality columns
❌ Rarely queried columns
❌ Frequently updated columns
❌ Small tables

Interview killer line 🔥

“Indexing is query-driven, not column-driven.”
```
