# Senario

```text
there are one senarion where first we storing mobile number and datatype is number 10 and now i want for all number
we should add +91 and store data .. what i cant to do . i need to create backup table and put new data type for mobile number
as varchar(20) and insert all the data into that class with update the value +91 .

Answer->
Problem recap (in simple words)

Old table: mobile_number stored as NUMBER(10)

You now want:

Country code +91 added to all existing numbers

New datatype VARCHAR2(20) (because +91 is text)

Best approach:
✅ Create a backup/new table
✅ Convert + transform data during insert

Step 1️⃣ Old table (example)
employee
--------------------------
id            NUMBER
name          VARCHAR2(50)
mobile_no     NUMBER(10)

Step 2️⃣ Create backup / new table with VARCHAR2
CREATE TABLE employee_bkp (
    id        NUMBER,
    name      VARCHAR2(50),
    mobile_no VARCHAR2(20)
);

Step 3️⃣ Insert data with +91 added

👉 Convert NUMBER → VARCHAR2 and concatenate +91

INSERT INTO employee_bkp (id, name, mobile_no)
SELECT id,
       name,
       '+91' || TO_CHAR(mobile_no)
FROM employee;


✔ TO_CHAR converts number to text
✔ || concatenates strings
✔ +91 is safely stored

Step 4️⃣ (Optional) Use PL/SQL block
BEGIN
    INSERT INTO employee_bkp (id, name, mobile_no)
    SELECT id,
           name,
           '+91' || TO_CHAR(mobile_no)
    FROM employee;

    COMMIT;
END;
/

Step 5️⃣ Verify result
SELECT mobile_no FROM employee_bkp;


Result:

+919876543210
+919123456789

🔥 Important Notes (very important in interviews)

❌ Phone numbers should NOT be NUMBER

Leading zeros lost

Country codes impossible

✅ Always use VARCHAR2

Migration via INSERT…SELECT is safest

Optional: Replace old table (after validation)
RENAME employee TO employee_old;
RENAME employee_bkp TO employee;

Summary

✔ Create new table
✔ Change datatype
✔ Transform data during insert
✔ No data loss

---------------------------------------------------------------------------------------------
How to update in same table (if allowed)
 How to add CHECK constraint for mobile format
  How to handle NULL or invalid numbers

  answer->

  1️⃣ Update in the SAME table (if allowed)
  Step 1: Change datatype NUMBER → VARCHAR2

  You must modify the column first.

  ALTER TABLE employee
  MODIFY mobile_no VARCHAR2(20);

  Step 2: Update existing values with +91
  UPDATE employee
  SET mobile_no = '+91' || mobile_no
  WHERE mobile_no IS NOT NULL;

  COMMIT;


  ✔ Works because column is now VARCHAR
  ✔ NULLs are untouched

  ⚠️ Important safety check before update
  SELECT mobile_no
  FROM employee
  WHERE LENGTH(TO_CHAR(mobile_no)) <> 10;


  This helps find invalid mobile numbers before updating.

  2️⃣ Add CHECK constraint for mobile number format
  Indian mobile format

  Starts with +91

  Followed by 10 digits

  Total length = 13

  Add CHECK constraint
  ALTER TABLE employee
  ADD CONSTRAINT chk_mobile_format
  CHECK (
      REGEXP_LIKE(mobile_no, '^\+91[0-9]{10}$')
  );


  ✔ Ensures format like: +919876543210
  ✔ Blocks invalid inserts/updates

  3️⃣ Handle NULL or invalid numbers
  ✅ Case A: Allow NULL mobile numbers

  Just modify constraint:

  ALTER TABLE employee
  ADD CONSTRAINT chk_mobile_format
  CHECK (
      mobile_no IS NULL
      OR REGEXP_LIKE(mobile_no, '^\+91[0-9]{10}$')
  );


  ✔ NULL allowed
  ✔ Non-NULL must be valid

  ❌ Case B: Find invalid data before adding constraint
  SELECT id, mobile_no
  FROM employee
  WHERE mobile_no IS NOT NULL
  AND NOT REGEXP_LIKE(mobile_no, '^\+91[0-9]{10}$');


  Fix these rows before adding the constraint.

  🔧 Case C: Auto-fix invalid numbers (example logic)
  UPDATE employee
  SET mobile_no = NULL
  WHERE mobile_no IS NOT NULL
  AND NOT REGEXP_LIKE(mobile_no, '^\+91[0-9]{10}$');

  COMMIT;

  🚫 Case D: Prevent future bad data (trigger – optional)
  CREATE OR REPLACE TRIGGER trg_mobile_check
  BEFORE INSERT OR UPDATE ON employee
  FOR EACH ROW
  BEGIN
      IF :NEW.mobile_no IS NOT NULL
         AND NOT REGEXP_LIKE(:NEW.mobile_no, '^\+91[0-9]{10}$') THEN
          RAISE_APPLICATION_ERROR(-20001, 'Invalid mobile number format');
      END IF;
  END;
  /

  ✅ Final Best Practice Summary
  Task	Best Solution
  Change datatype	ALTER TABLE MODIFY
  Add +91	UPDATE
  Validate format	CHECK + REGEXP_LIKE
  Allow NULLs	mobile_no IS NULL OR …
  Prevent bad data	Constraint > Trigger
  Interview gold line ✨

  “Phone numbers should always be stored as VARCHAR, not NUMBER.”


  -----------------------------------------------------------------------------------------------------
  Formatting for multiple country codes

  Masking mobile numbers

  Performance impact of REGEXP constraints

  Real-world validation strategy used in banks


  Answer->
  1️⃣ Support MULTIPLE country codes (not just +91)
  Rule

  Starts with +

  Country code: 1–3 digits

  Mobile number: 8–12 digits (varies by country)

  CHECK constraint (generic & flexible)
  ALTER TABLE employee
  ADD CONSTRAINT chk_mobile_international
  CHECK (
      mobile_no IS NULL
      OR REGEXP_LIKE(mobile_no, '^\+[0-9]{1,3}[0-9]{8,12}$')
  );


  ✔ Examples allowed:

  +919876543210
  +14155552671
  +447911123456

  2️⃣ Mask mobile numbers (security / privacy)
  Show only last 4 digits
  SELECT
      RPAD('*', LENGTH(mobile_no) - 4, '*') || SUBSTR(mobile_no, -4) AS masked_mobile
  FROM employee;


  Example output:

  **********3210

  3️⃣ Performance: REGEXP vs LENGTH (important!)
  REGEXP (slower but flexible)

  ✔ Best for validation
  ❌ Slightly slower on huge tables

  Faster alternative (when only +91)
  CHECK (
      mobile_no IS NULL
      OR (SUBSTR(mobile_no, 1, 3) = '+91'
          AND LENGTH(mobile_no) = 13
          AND TRANSLATE(mobile_no, '+0123456789', '+') IS NULL)
  );


  🔥 This avoids REGEXP and is faster.

  4️⃣ Real-world validation strategy (bank / telecom style)
  What professionals actually do:

  Store as VARCHAR2

  CHECK constraint → basic format

  Trigger or application logic → deeper validation

  No COMMIT in triggers

  Never store phone as NUMBER

  Recommended combo
  ALTER TABLE employee
  ADD CONSTRAINT chk_mobile_basic
  CHECK (
      mobile_no IS NULL
      OR REGEXP_LIKE(mobile_no, '^\+[0-9]{10,15}$')
  );


  Application layer handles:

  Country-specific rules

  OTP verification

  Duplicates

  🎯 Final Architecture (Interview-ready)
  TABLE
   └── VARCHAR2 mobile_no
         ├── CHECK constraint (format)
         ├── Indexed (optional)
         └── Validated at app level

  One-liner interview answer 💡

  “Mobile numbers are stored as VARCHAR2 with international format, validated using CHECK constraints and application-level logic.”

  ----------------------------------------------------------------
  How to index mobile numbers

  How to prevent duplicates

  How to normalize phone numbers

  How to rollback safely if update fails

  Answer->
  1️⃣ How to INDEX mobile numbers
  Why index mobile numbers?

  Fast lookup during:

  Login / OTP

  Customer search

  Dedup checks

  Basic index
  CREATE INDEX idx_employee_mobile
  ON employee (mobile_no);


  ✔ Speeds up WHERE mobile_no = '+919876543210'

  Function-based index (normalized search)

  If you normalize by removing spaces/dashes:

  CREATE INDEX idx_emp_mobile_norm
  ON employee (
      REGEXP_REPLACE(mobile_no, '[^0-9]', '')
  );


  Now this query uses the index:

  SELECT *
  FROM employee
  WHERE REGEXP_REPLACE(mobile_no, '[^0-9]', '') =
        '919876543210';

  2️⃣ How to PREVENT duplicates
  Option A: UNIQUE constraint (best)
  ALTER TABLE employee
  ADD CONSTRAINT uq_employee_mobile UNIQUE (mobile_no);


  ✔ Database-level guarantee
  ✔ Fast & safe
  ✔ Handles concurrency

  Option B: Unique on normalized value (real-world best)

  Prevents:

  +91 9876543210
  +91-9876543210
  +919876543210

  CREATE UNIQUE INDEX uq_emp_mobile_norm
  ON employee (
      REGEXP_REPLACE(mobile_no, '[^0-9]', '')
  );


  🔥 This is production-grade.

  3️⃣ How to NORMALIZE phone numbers
  What normalization means

  Store phone numbers in one consistent format:

  +<countrycode><number>

  Normalize existing data
  UPDATE employee
  SET mobile_no =
      '+' || REGEXP_REPLACE(mobile_no, '[^0-9]', '')
  WHERE mobile_no IS NOT NULL;


  Example:

  (987) 654-3210  →  +9876543210

  Normalize future inserts (trigger)
  CREATE OR REPLACE TRIGGER trg_mobile_normalize
  BEFORE INSERT OR UPDATE ON employee
  FOR EACH ROW
  BEGIN
      IF :NEW.mobile_no IS NOT NULL THEN
          :NEW.mobile_no :=
              '+' || REGEXP_REPLACE(:NEW.mobile_no, '[^0-9]', '');
      END IF;
  END;
  /


  ✔ Keeps data clean automatically
  ✔ No application dependency

  4️⃣ How to ROLLBACK safely if update fails

  This is critical in production.

  A️⃣ Use SAVEPOINT (best practice)
  BEGIN
      SAVEPOINT before_mobile_update;

      UPDATE employee
      SET mobile_no = '+91' || mobile_no
      WHERE mobile_no IS NOT NULL;

      COMMIT;
  EXCEPTION
      WHEN OTHERS THEN
          ROLLBACK TO before_mobile_update;
          DBMS_OUTPUT.PUT_LINE('Update failed: ' || SQLERRM);
  END;
  /


  ✔ Partial rollback
  ✔ Table remains consistent

  B️⃣ Dry-run validation before update
  SELECT COUNT(*)
  FROM employee
  WHERE mobile_no IS NOT NULL
  AND LENGTH(mobile_no) <> 10;


  Fix errors before touching data.

  C️⃣ Always BACKUP before mass update
  CREATE TABLE employee_backup AS
  SELECT * FROM employee;


  🔥 This is non-negotiable in real systems.

  D️⃣ Never COMMIT inside loops

  Bad ❌

  FOR r IN (SELECT * FROM employee) LOOP
      UPDATE employee SET ...
      COMMIT;
  END LOOP;


  Good ✅

  UPDATE employee SET ...
  COMMIT;

  🧠 Final Professional Strategy
  1. Backup table
  2. Normalize existing data
  3. Add CHECK constraint
  4. Add UNIQUE index
  5. Add search index
  6. Use SAVEPOINT for safety

  Interview Power Answer 🚀

  “We normalize mobile numbers, enforce uniqueness with a function-based unique index, validate format via CHECK
  constraints, index for fast lookup, and use savepoints for safe rollback during migrations.”
```
