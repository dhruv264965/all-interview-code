# MongoDB Complete Notes + Interview Guide

Run all examples in mongosh (MongoDB Shell). Sample DB used: `company`

## Table of Contents

- 1.  Introduction: MongoDB, Database, Collection, Document
- 2.  BSON
- 3.  Create / Use / Delete Database
- 4.  Datatypes
- 5.  Insert (insertOne, insertMany, ordered option)
- 6.  Find vs findOne, Projection
- 7.  Update vs updateOne (updateMany, replaceOne)
- 8.  Delete vs deleteOne (deleteMany)
- 9.  Nested (embedded) documents: create, search, update
- 10.  Adding new fields/documents to existing documents
- 11.  Comparison operators
- 12.  Logical operators
- 13.  $exists and $type
- 14.  Evaluation operators ($regex, $expr, $mod, $text, $where, $jsonSchema)
- 15.  Array operators: $size, $all, $elemMatch
- 16.  Sort, limit, skip
- 17.  $min, $max, $rename
- 18.  Array update: $pop, $pull, $push, $addToSet
- 19.  Schema Validation
- 20.  Atomicity
- 21.  Indexing
- 22.  Aggregation ($match, $group, $project, $bucket, $lookup ...)
- 23.  Join collections ($lookup)
- 24.  Capped collection
- 25.  Replication
- 26.  Sharding
- 27.  Transactions
- 28.  Advanced data queries
- 29.  Interview Questions & Answers
- 30.  Code to write in the interview (Java + shell)
- 31.  How to explain in an interview (cheat sheet)

## 1. INTRODUCTION

```text
MongoDB = open source, document-oriented NoSQL database. Stores data as JSON-like
documents (BSON) with a flexible schema.

SQL vs MongoDB terms:
   Database      -> Database
   Table         -> Collection
   Row           -> Document
   Column        -> Field
   Join          -> $lookup / embedding
   Primary key   -> _id (auto-added, unique, ObjectId by default)
   Index         -> Index
   GROUP BY      -> aggregation $group

DATABASE   : container of collections.
COLLECTION : group of documents (like a table, but NO fixed schema).
DOCUMENT   : one record, set of key:value pairs. Max size 16 MB.

Example collection "employees" with documents:
   {
     _id: ObjectId("652f1c..."),
     name: "Dhruv",
     age: 28,
     skills: ["Java", "Spring", "MongoDB"],
     address: { city: "Mumbai", pin: 400001 }
   }
   {
     name: "Amit", age: 31, dept: "HR"       // different fields -> allowed (flexible schema)
   }

Why use MongoDB: flexible schema, horizontal scaling (sharding), high availability
(replica set), fast reads/writes for JSON-like data, rich query + aggregation.

Interview explanation:
 "MongoDB stores data as documents in collections. Documents in the same collection
  need not have the same structure, so it suits rapidly changing requirements. It
  scales horizontally through sharding and gives high availability using replica sets."


```

## 2. BSON

```text
BSON = Binary JSON. The format MongoDB uses to store documents and for network transfer.

Why BSON and not JSON?
  - Binary -> faster to parse/traverse
  - Supports more datatypes than JSON: Date, ObjectId, Int32, Int64, Decimal128,
    BinData, Timestamp
  - Stores length prefixes so the server can skip fields quickly
  - Slightly bigger than JSON sometimes, but faster to scan

JSON:  {"age": 28}  (number only)       BSON: Int32 / Int64 / Double / Decimal128
You write JSON-like syntax in the shell; the driver converts it to BSON.

Interview explanation:
 "BSON is a binary-encoded serialization of JSON that adds types like Date and
  ObjectId and is designed for speed and efficient traversal."


```

## 3. CREATE / USE / DELETE DATABASE

```text
   show dbs                         // list databases (only non-empty ones appear)
   use company                      // switch to db; CREATES it lazily on first insert
   db                               // show current db
   db.createCollection("employees") // explicit collection creation
   show collections

   db.employees.insertOne({name:"Dhruv"})   // now "company" really exists

NOTE: "use newdb" alone does NOT create the db on disk. It is created when you
store the first document/collection.

Collection options:
   db.createCollection("logs", { capped: true, size: 1048576, max: 1000 })

DELETE DATABASE:
   use company
   db.dropDatabase()                // drops the CURRENT database

DELETE COLLECTION:
   db.employees.drop()

Interview: "MongoDB creates databases and collections implicitly on first write."


```

## 4. DATATYPES IN MONGODB

```text
   String        "hello"
   Int32         NumberInt(10)
   Int64 (Long)  NumberLong(100000000000)
   Double        10.5          (default for numbers in shell)
   Decimal128    NumberDecimal("10.25")   // money, exact precision
   Boolean       true / false
   Date          new Date()  or ISODate("2024-01-15T10:00:00Z")
   ObjectId      ObjectId()   // 12 bytes: 4 timestamp + 5 random + 3 counter
   Array         ["a","b"]
   Object/Embedded document   { city: "Pune" }
   Null          null
   Timestamp     Timestamp()  // internal use (oplog)
   Binary data   BinData(0, "...")
   Regex         /^abc/i
   MinKey/MaxKey special comparison types
   UUID          UUID("...")

Example:
   db.types.insertOne({
     s:"text", i:NumberInt(5), l:NumberLong(9999999999), d:10.5,
     dec:NumberDecimal("99.99"), b:true, dt:new Date(), arr:[1,2,3],
     obj:{a:1}, n:null
   })

Check type:  typeof / db.types.find({ s: {$type:"string"} })

Interview: "_id is ObjectId by default - includes creation timestamp, so it's roughly
 sortable by creation time. Use Decimal128 for currency, not double."


```

## 5. INSERT

```text
insertOne:
   db.employees.insertOne({ name:"Dhruv", age:28, dept:"IT" })
   // returns { acknowledged:true, insertedId: ObjectId(...) }

insertMany:
   db.employees.insertMany([
     { name:"Amit",  age:31, dept:"HR",  salary:50000 },
     { name:"Riya",  age:25, dept:"IT",  salary:60000 },
     { name:"Karan", age:35, dept:"Fin", salary:80000 }
   ])

Custom _id:
   db.employees.insertOne({ _id: 101, name:"Sam" })   // duplicate _id -> E11000 error

Legacy: db.employees.insert({...})  // deprecated, use insertOne/insertMany

ORDERED OPTION (insertMany)
   ordered:true  (DEFAULT) -> inserts in order; STOPS at first error; remaining docs
                              are NOT inserted.
   ordered:false           -> continues after an error; inserts all valid docs;
                              reports errors at end; can be faster (parallel).

   db.employees.insertMany([
      {_id:1, name:"A"},
      {_id:1, name:"B"},     // duplicate -> error
      {_id:2, name:"C"}
   ], { ordered:true })     // inserts only A; B fails; C NOT inserted

   db.employees.insertMany([...same...], { ordered:false })  // inserts A and C; B fails

Interview: "Ordered=false is useful for bulk loads where one bad record shouldn't
 block the rest."


```

## 6. FIND vs findOne, PROJECTION

```text
   db.employees.find()                      // ALL docs -> returns a CURSOR
   db.employees.find({ dept:"IT" })         // filtered, all matches (cursor)
   db.employees.findOne({ dept:"IT" })      // FIRST matching doc only -> a single DOCUMENT
                                            // (or null if none)
   db.employees.find().pretty()             // formatted output
   db.employees.find().limit(2)
   db.employees.countDocuments({dept:"IT"})
   db.employees.distinct("dept")

Difference:
   find()    -> returns cursor (many docs), shell shows 20 at a time ("it" for more)
   findOne() -> returns one document (natural order first match), null if none

PROJECTION = choose which fields to return (2nd argument of find)
   1 = include, 0 = exclude. Cannot mix 1 and 0 (except _id).
   db.employees.find({}, { name:1, age:1 })            // _id also returned by default
   db.employees.find({}, { name:1, age:1, _id:0 })     // hide _id
   db.employees.find({}, { salary:0 })                 // everything except salary
   db.employees.find({}, { "address.city":1, _id:0 }) // nested field projection
   db.students.find({}, { scores: { $slice: 2 } })     // first 2 array items
   db.students.find({}, { scores: { $elemMatch: { $gt: 80 } } })  // first matching element

Interview: "Projection reduces network and memory usage - return only needed fields.
 Combined with a covered index it can be answered from the index alone."


```

## 7. UPDATE vs updateOne (updateMany, replaceOne)

```text
   updateOne(filter, update, options)  -> updates FIRST matching document
   updateMany(filter, update)          -> updates ALL matching documents
   replaceOne(filter, newDoc)          -> replaces entire doc (except _id)
   update()  (legacy) -> updates one doc by default; needs {multi:true} for many.
             Deprecated - use updateOne/updateMany.

   db.employees.updateOne({ name:"Dhruv" }, { $set: { age:29 } })
   db.employees.updateMany({ dept:"IT" }, { $set: { bonus:5000 } })
   db.employees.updateOne({ name:"Dhruv" }, { $inc: { salary: 5000 } })
   db.employees.updateOne({ name:"Dhruv" }, { $unset: { bonus: "" } })      // remove field
   db.employees.updateOne({ name:"New" }, { $set:{age:20} }, { upsert:true }) // insert if not found
   db.employees.replaceOne({ name:"Amit" }, { name:"Amit", age:32 })

Common update operators: $set $unset $inc $mul $rename $min $max $currentDate
                         $push $pull $pop $addToSet

WARNING: update without an operator (e.g. updateOne(f, {age:30})) is an error in
updateOne; with replaceOne the full doc is replaced.

findOneAndUpdate returns the document:
   db.employees.findOneAndUpdate({name:"Riya"}, {$inc:{age:1}}, {returnDocument:"after"})


```

## 8. DELETE vs deleteOne (deleteMany)

```text
   db.employees.deleteOne({ dept:"IT" })    // deletes FIRST match only
   db.employees.deleteMany({ dept:"IT" })   // deletes ALL matches
   db.employees.deleteMany({})              // deletes all docs, collection + indexes remain
   db.employees.drop()                      // drops collection entirely
   db.employees.remove({...})               // legacy, deprecated (removes all matches by default)
   db.employees.findOneAndDelete({name:"X"})// delete and return the doc

Interview: "deleteMany({}) removes docs one by one (logged); drop() is faster for
 removing everything because it drops the collection."


```

## 9. NESTED (EMBEDDED) DOCUMENTS

```text
CREATE:
   db.employees.insertOne({
     name: "Dhruv",
     address: { city:"Mumbai", state:"MH", geo:{ lat:19.07, lng:72.87 } },
     projects: [ { title:"Alpha", hours:100 }, { title:"Beta", hours:50 } ]
   })

SEARCH (dot notation - field path MUST be in quotes):
   db.employees.find({ "address.city": "Mumbai" })
   db.employees.find({ "address.geo.lat": { $gt: 19 } })
   db.employees.find({ "projects.title": "Alpha" })           // array of sub-docs
   db.employees.find({ projects: { $elemMatch:{ title:"Alpha", hours:{$gt:80} } } })

   Exact sub-document match (order of fields matters!):
   db.employees.find({ address: { city:"Mumbai", state:"MH", geo:{lat:19.07,lng:72.87} } })

UPDATE nested value:
   db.employees.updateOne({ name:"Dhruv" }, { $set: { "address.city": "Pune" } })
   db.employees.updateOne({ name:"Dhruv" }, { $set: { "address.pin": 411001 } })    // add new nested field
   db.employees.updateOne({ name:"Dhruv" }, { $unset: { "address.state": "" } })
   db.employees.updateOne({ name:"Dhruv" }, { $inc: { "address.geo.lat": 1 } })

Update array element inside nested array:
   // positional operator $ : first matching array element
   db.employees.updateOne(
      { name:"Dhruv", "projects.title":"Alpha" },
      { $set: { "projects.$.hours": 120 } })
   // by index
   db.employees.updateOne({ name:"Dhruv" }, { $set: { "projects.1.hours": 75 } })
   // all elements
   db.employees.updateOne({ name:"Dhruv" }, { $inc: { "projects.$[].hours": 10 } })
   // filtered positional
   db.employees.updateOne({ name:"Dhruv" }, { $set: { "projects.$[p].hours": 0 } },
                          { arrayFilters: [ { "p.hours": { $lt: 80 } } ] })

Interview: "Embed when data is read together and has 1:few relationship; reference
 (store ids and $lookup) when data is large, unbounded, or shared (1:many/many:many)."


```

## 10. ADDING NEW DOCUMENTS / FIELDS TO EXISTING DOCUMENTS

```text
Add a new field to an existing doc:
   db.employees.updateOne({ name:"Dhruv" }, { $set: { email:"d@x.com" } })
Add a field to all docs:
   db.employees.updateMany({}, { $set: { active:true } })
Add a new embedded document:
   db.employees.updateOne({ name:"Dhruv" }, { $set: { contact: { phone:"999", alt:"888" } } })
Add a new sub-document into an array of documents:
   db.employees.updateOne({ name:"Dhruv" }, { $push: { projects: { title:"Gamma", hours:20 } } })
Add several:
   db.employees.updateOne({ name:"Dhruv" }, { $push: { projects: { $each:[{title:"X"},{title:"Y"}] } } })
Add new doc to collection: insertOne / insertMany (section 5).


```

## 11. COMPARISON OPERATORS

```text
   $eq   equal                 { age: { $eq: 30 } }  same as { age: 30 }
   $ne   not equal             { dept: { $ne: "IT" } }
   $gt   greater than          { age: { $gt: 25 } }
   $gte  greater or equal      { age: { $gte: 25 } }
   $lt   less than             { age: { $lt: 30 } }
   $lte  less or equal         { age: { $lte: 30 } }
   $in   matches any in array  { dept: { $in: ["IT","HR"] } }
   $nin  matches none          { dept: { $nin: ["IT","HR"] } }

Range:  db.employees.find({ age: { $gte: 25, $lte: 35 } })
Note: $ne / $nin also match docs where the field does not exist.


```

## 12. LOGICAL OPERATORS

```text
   $and   all conditions true
   $or    at least one true
   $nor   none true
   $not   inverts an operator expression

   db.employees.find({ $and: [ { age:{$gt:25} }, { dept:"IT" } ] })
   db.employees.find({ age:{$gt:25}, dept:"IT" })      // implicit AND (comma)
   db.employees.find({ $or:  [ { dept:"IT" }, { salary:{$gt:70000} } ] })
   db.employees.find({ $nor: [ { dept:"IT" }, { dept:"HR" } ] })
   db.employees.find({ age: { $not: { $gt: 30 } } })   // age <= 30 OR age missing

Use explicit $and when the same field/operator appears twice:
   { $and:[ {age:{$gt:20}}, {age:{$lt:40}} ] }  (or just {age:{$gt:20,$lt:40}})


```

## 13. $exists AND $type

```text
   db.employees.find({ salary: { $exists: true } })      // field present (even if null)
   db.employees.find({ salary: { $exists: false } })     // field absent
   db.employees.find({ salary: { $exists:true, $ne:null } })  // present and not null

   db.employees.find({ age: { $type: "int" } })
   db.employees.find({ age: { $type: "number" } })       // alias: int, long, double, decimal
   db.employees.find({ name: { $type: 2 } })             // 2 = string (numeric codes work)
   db.employees.find({ age: { $type: ["int","double"] } })
   db.employees.find({ x: null })                        // null OR missing
   db.employees.find({ x: { $type: "null" } })           // only explicit null

Type names: double, string, object, array, binData, objectId, bool, date, null,
            regex, int, timestamp, long, decimal.

Use: data cleaning, finding bad data with wrong types, optional fields.


```

## 14. EVALUATION OPERATORS

```text
   $regex       pattern match
        db.employees.find({ name: { $regex: "^Dh", $options: "i" } })
        db.employees.find({ name: /^Dh/i })
   $expr        compare fields within same document / use aggregation expressions
        db.employees.find({ $expr: { $gt: ["$salary", "$expectedSalary"] } })
   $mod         modulo           db.employees.find({ age: { $mod: [2, 0] } })   // even age
   $text        text search (needs a text index)
        db.articles.createIndex({ body: "text" })
        db.articles.find({ $text: { $search: "mongo database" } })
   $where       JS expression (SLOW, avoid, security risk)
        db.employees.find({ $where: "this.salary > this.age * 1000" })
   $jsonSchema  validate/match against JSON schema
        db.employees.find({ $jsonSchema: { required:["name"] } })


```

## 15. ARRAY OPERATORS: $size, $all, $elemMatch

```text
Sample:  db.students.insertMany([
   { name:"A", skills:["Java","SQL","Mongo"], scores:[80,90,70] },
   { name:"B", skills:["Java","Python"],      scores:[60,95] }
])

 Match array containing a value:       db.students.find({ skills: "Java" })
 Exact array (order+items):            db.students.find({ skills: ["Java","Python"] })
 $size  - array has exact length:      db.students.find({ skills: { $size: 3 } })
          (cannot use ranges with $size; store a count field or use $expr + $size)
          db.students.find({ $expr: { $gt: [ { $size:"$skills" }, 2 ] } })
 $all   - contains ALL listed (any order):
          db.students.find({ skills: { $all: ["Java","Mongo"] } })
 $elemMatch - ONE element satisfies ALL conditions:
          db.students.find({ scores: { $elemMatch: { $gt: 85, $lt: 95 } } })
   Without $elemMatch { scores:{$gt:85,$lt:95} } can match different elements
   (one >85, another <95) -> a common interview trap.
 Index position:  db.students.find({ "scores.0": { $gt: 75 } })


```

## 16. SORT, LIMIT, SKIP

```text
   db.employees.find().sort({ age: 1 })             // 1 ascending, -1 descending
   db.employees.find().sort({ dept: 1, salary: -1 })
   db.employees.find().sort({ salary: -1 }).limit(3)       // top 3 salaries
   db.employees.find().skip(10).limit(10)                  // pagination (page 2)
   Order of execution is always: sort -> skip -> limit (regardless of writing order)
   Sort on an indexed field is fast; sort without index has a 100MB memory limit
   (use allowDiskUse in aggregation).
   Pagination tip: for big data use range query on _id (id > lastSeen) instead of skip.


```

## 17. $min, $max, $rename

```text
   $min / $max (UPDATE operators): update field only if given value is lower / higher.
     db.employees.updateOne({name:"Dhruv"}, { $min: { salary: 40000 } })  // set only if 40000 < current
     db.employees.updateOne({name:"Dhruv"}, { $max: { salary: 90000 } })  // set only if 90000 > current

   $min / $max also exist as AGGREGATION accumulators in $group (see section 22).
   Shell cursor: db.employees.find().min({age:25}).hint({age:1})  // index bounds (rare)

   $rename: rename a field
     db.employees.updateMany({}, { $rename: { "dept": "department" } })
     db.employees.updateOne({}, { $rename: { "address.city": "address.town" } })

   Rename a collection:  db.employees.renameCollection("staff")


```

## 18. ARRAY UPDATES: $push, $pop, $pull, $addToSet

```text
   $push  - append element (duplicates allowed)
     db.students.updateOne({name:"A"}, { $push: { skills: "Docker" } })
     db.students.updateOne({name:"A"}, { $push: { skills: { $each:["K8s","AWS"] } } })
     // with sort/slice - keep last 5 scores:
     db.students.updateOne({name:"A"}, { $push: { scores: { $each:[88], $slice:-5 } } })
     // insert at position:
     db.students.updateOne({name:"A"}, { $push: { skills: { $each:["X"], $position:0 } } })

   $addToSet - add only if NOT already present (no duplicates)
     db.students.updateOne({name:"A"}, { $addToSet: { skills: "Java" } })   // no change
     db.students.updateOne({name:"A"}, { $addToSet: { skills: { $each:["Go","Java"] } } })

   $pop   - remove first (-1) or last (1) element
     db.students.updateOne({name:"A"}, { $pop: { skills: 1 } })    // remove last
     db.students.updateOne({name:"A"}, { $pop: { skills: -1 } })   // remove first

   $pull  - remove ALL elements matching a value/condition
     db.students.updateOne({name:"A"}, { $pull: { skills: "SQL" } })
     db.students.updateOne({name:"A"}, { $pull: { scores: { $lt: 75 } } })
     db.employees.updateOne({name:"Dhruv"}, { $pull: { projects: { title:"Alpha" } } })

   $pullAll - remove list of values: { $pullAll: { skills: ["Java","SQL"] } }

   $push vs $addToSet: push allows duplicates, addToSet is set semantics.
   $pop vs $pull: pop by position, pull by value/condition.


```

## 19. SCHEMA VALIDATION

```text
MongoDB is schema-flexible but you can ENFORCE rules per collection with $jsonSchema.

   db.createCollection("users", {
     validator: {
       $jsonSchema: {
         bsonType: "object",
         required: ["name", "email", "age"],
         properties: {
           name:  { bsonType: "string", description: "must be a string" },
           email: { bsonType: "string", pattern: "^.+@.+$" },
           age:   { bsonType: "int", minimum: 18, maximum: 100 },
           role:  { enum: ["admin","user"] },
           address: {
              bsonType:"object",
              properties:{ city:{ bsonType:"string" } }
           }
         }
       }
     },
     validationLevel:  "strict",   // strict (default): all inserts/updates validated
                                   // moderate: only validate existing valid docs & inserts
     validationAction: "error"     // error (default): reject; warn: allow + log warning
   })

   db.users.insertOne({ name:"A", email:"a@x.com", age:15 })   // FAILS: Document failed validation

Add validator to an existing collection:
   db.runCommand({ collMod:"users", validator:{ $jsonSchema:{ ... } }, validationLevel:"moderate" })

Notes: _id is exempt; validation runs on insert and update; bypass with
bypassDocumentValidation:true (needs privilege).

Interview: "Schema validation gives data integrity while keeping flexibility; use
 validationAction 'warn' to roll out new rules gradually."


```

## 20. ATOMICITY

```text
 - Write operations are ATOMIC at the SINGLE DOCUMENT level (even if they modify
   multiple fields or embedded docs). Either all changes apply or none.
 - Operations across multiple documents (updateMany, insertMany) are NOT atomic as a
   whole -> use multi-document transactions (section 27).
 - Embedding related data in one document is the main design technique to get atomicity
   without transactions.
 - Atomic read-modify-write using operators: $inc, $set, findOneAndUpdate:
     db.accounts.updateOne({ _id:1, balance:{ $gte:100 } }, { $inc:{ balance:-100 } })
   (condition check + update is one atomic step - prevents race conditions)
 - ACID: single doc always ACID; multi-doc ACID through transactions (4.0+ replica sets,
   4.2+ sharded clusters).

Interview: "Atomicity is guaranteed per document. Design the schema so data that must
 change together lives in one document; otherwise use a transaction."


```

## 21. INDEXING

```text
Index = data structure (B-tree) that speeds up queries. Without index -> COLLSCAN
(full collection scan). With index -> IXSCAN. Default index on _id.

   db.employees.createIndex({ name: 1 })                      // single field
   db.employees.createIndex({ dept:1, salary:-1 })            // compound (order matters! ESR rule)
   db.employees.createIndex({ email:1 }, { unique:true })     // unique
   db.employees.createIndex({ skills:1 })                     // multikey (array field)
   db.articles.createIndex({ body:"text" })                   // text
   db.places.createIndex({ loc:"2dsphere" })                  // geospatial
   db.employees.createIndex({ city:"hashed" })                // hashed (for hash sharding)
   db.sessions.createIndex({ createdAt:1 }, { expireAfterSeconds: 3600 })  // TTL index
   db.employees.createIndex({ phone:1 }, { sparse:true })     // only docs having the field
   db.employees.createIndex({ age:1 }, { partialFilterExpression:{ age:{$gt:18} } })  // partial

   db.employees.getIndexes()
   db.employees.dropIndex("name_1")
   db.employees.dropIndexes()

EXPLAIN:
   db.employees.find({ name:"Dhruv" }).explain("executionStats")
   Look at: winningPlan.stage (IXSCAN vs COLLSCAN), totalDocsExamined vs nReturned,
            executionTimeMillis.

Compound index prefix rule: index {a:1,b:1,c:1} supports queries on a; a,b; a,b,c.
Not on b alone.
ESR rule: order compound fields as Equality, Sort, Range.
Covered query: all fields in filter + projection are in the index (and _id:0) ->
no document fetch.

Cons: indexes use memory/disk and slow writes. Don't over-index.


```

## 22. AGGREGATION

```text
Pipeline: documents pass through stages; each stage transforms them.

   db.collection.aggregate([ {stage1}, {stage2}, ... ])

Common stages: $match $group $project $sort $limit $skip $unwind $lookup $addFields
               $set $unset $count $bucket $bucketAuto $facet $out $merge $sample
               $replaceRoot $sortByCount

Sample data:
   db.orders.insertMany([
     { _id:1, cust:"A", amount:100, status:"done",    items:["x","y"], date:ISODate("2024-01-10") },
     { _id:2, cust:"B", amount:250, status:"done",    items:["x"],     date:ISODate("2024-02-11") },
     { _id:3, cust:"A", amount:300, status:"pending", items:["z","y"], date:ISODate("2024-02-15") },
     { _id:4, cust:"C", amount:50,  status:"done",    items:[],        date:ISODate("2024-03-01") }
   ])

$match + $group (total per customer):
   db.orders.aggregate([
     { $match: { status:"done" } },
     { $group: { _id:"$cust", total:{ $sum:"$amount" }, avg:{ $avg:"$amount" },
                 max:{ $max:"$amount" }, min:{ $min:"$amount" }, cnt:{ $sum:1 } } },
     { $sort: { total:-1 } }
   ])
   Accumulators: $sum $avg $min $max $first $last $push $addToSet $count

Group everything: { $group:{ _id:null, total:{$sum:"$amount"} } }

$project (shape output, compute fields):
   db.orders.aggregate([
     { $project: { _id:0, cust:1, amount:1, withTax:{ $multiply:["$amount",1.18] },
                   year:{ $year:"$date" } } }
   ])

$unwind (one doc per array element):
   db.orders.aggregate([ { $unwind:"$items" }, { $group:{ _id:"$items", n:{$sum:1} } } ])
   // preserveNullAndEmptyArrays:true keeps docs with empty arrays

$addFields / $set:  { $addFields: { gst: { $multiply:["$amount", 0.18] } } }
$count:             { $count: "totalOrders" }

$bucket (group into ranges):
   db.orders.aggregate([
     { $bucket: {
         groupBy: "$amount",
         boundaries: [0, 100, 200, 500],       // [0,100) [100,200) [200,500)
         default: "Other",                     // values outside boundaries
         output: { count:{ $sum:1 }, total:{ $sum:"$amount" } }
     } }
   ])
   $bucketAuto: { $bucketAuto:{ groupBy:"$amount", buckets:3 } }  // auto ranges

$facet (multiple pipelines in one pass), $out / $merge (write results to a collection).

Performance: put $match and $limit EARLY (so indexes are used and fewer docs flow on).
{ allowDiskUse:true } for big sorts/groups.


```

## 23. JOIN COLLECTIONS ($lookup)

```text
   db.customers.insertMany([ {_id:"A", name:"Alice"}, {_id:"B", name:"Bob"} ])

   db.orders.aggregate([
     { $lookup: {
         from: "customers",          // collection to join
         localField: "cust",         // field in orders
         foreignField: "_id",        // field in customers
         as: "customerInfo"          // output array
     } },
     { $unwind: "$customerInfo" },   // array -> object
     { $project: { amount:1, "customerInfo.name":1 } }
   ])
   This is a LEFT OUTER JOIN. Unmatched -> empty array.

Pipeline form (conditions / multiple fields):
   { $lookup: { from:"customers", let:{ c:"$cust" },
       pipeline:[ { $match:{ $expr:{ $eq:["$_id","$$c"] } } } ], as:"customerInfo" } }

Limitation: $lookup on sharded "from" collection was restricted in older versions;
joins are costlier than in SQL -> prefer embedding for frequently-read-together data.
Index the foreignField.

Also: $graphLookup (recursive, hierarchy), $unionWith (UNION ALL).


```

## 24. CAPPED COLLECTION

```text
Fixed-size collection; acts like a circular buffer: when full, oldest docs are overwritten.

   db.createCollection("logs", { capped:true, size:1048576, max:5000 })
        // size in bytes (required), max docs (optional)
   db.logs.isCapped()
   db.runCommand({ convertToCapped:"logs", size:1048576 })

Properties:
 - Preserves insertion order; natural order retrieval is fast
 - High insert throughput
 - Cannot delete individual docs; cannot update if it increases doc size; cannot shard
 - Tailable cursors (like "tail -f")
Use: logs, caches, recent activity feeds, oplog (internal).
Alternative: TTL index (auto-delete by time).


```

## 25. REPLICATION

```text
Replica set = group of mongod instances holding the same data -> HIGH AVAILABILITY,
redundancy, read scaling.

 - PRIMARY: receives all writes. Records them in the oplog.
 - SECONDARY: replicate the oplog asynchronously and apply it.
 - ARBITER: votes only, holds no data.
 - If primary fails -> ELECTION (majority vote) picks a new primary (typically ~10-12 s).
 - Needs odd number of voting members (3, 5, 7) to get a majority. Max 50 members, 7 voting.

Setup (shell):
   mongod --replSet rs0 --port 27017 --dbpath /data/db1
   mongod --replSet rs0 --port 27018 --dbpath /data/db2
   mongod --replSet rs0 --port 27019 --dbpath /data/db3
   rs.initiate({ _id:"rs0", members:[
        {_id:0, host:"localhost:27017"},
        {_id:1, host:"localhost:27018"},
        {_id:2, host:"localhost:27019"} ] })
   rs.status()      rs.conf()      rs.add("host:port")

Write concern: how many nodes must ack. w:1, w:"majority", j:true.
   db.orders.insertOne({...}, { writeConcern:{ w:"majority", wtimeout:5000 } })
Read preference: primary (default), primaryPreferred, secondary, secondaryPreferred, nearest.
Read concern: local, majority, snapshot, linearizable.
Replication lag: secondary reads may be stale.

Interview: "Replication gives HA and failover; it does NOT give write scaling - use
 sharding for that."


```

## 26. SHARDING

```text
Sharding = horizontal partitioning of data across multiple machines (shards) to scale
storage and write throughput.

Components:
 - Shard: replica set that holds a subset of data
 - mongos: query router; apps connect to it
 - Config servers (replica set): store cluster metadata / chunk ranges

Shard key: field(s) that decide how docs are distributed. Choose carefully:
   high cardinality, even distribution, matches query pattern, not monotonically
   increasing (hotspot).
Strategies:
   Ranged sharding  - { age: 1 }       range queries efficient; risk of hotspots
   Hashed sharding  - { _id: "hashed" } even distribution; range queries scatter-gather
   Zoned sharding   - pin ranges to specific shards (geo / tier)

Commands:
   sh.enableSharding("company")
   sh.shardCollection("company.orders", { custId: "hashed" })
   sh.status()
Data is split into CHUNKS (default 128 MB, 64MB in older) and a BALANCER moves chunks
between shards to keep even.
Targeted query: includes shard key -> goes to one shard. Without it -> scatter-gather
to all shards.

Replication vs Sharding:
   Replication: copies of SAME data, availability.
   Sharding   : DIFFERENT data on each shard, scalability.
   Production: each shard is itself a replica set.


```

## 27. TRANSACTIONS

```text
Multi-document ACID transactions: require replica set (4.0+) or sharded cluster (4.2+).

Shell example (money transfer):
   const session = db.getMongo().startSession();
   const accounts = session.getDatabase("bank").accounts;
   session.startTransaction({ readConcern:{level:"snapshot"}, writeConcern:{w:"majority"} });
   try {
       accounts.updateOne({ _id:"A" }, { $inc:{ balance:-100 } });
       accounts.updateOne({ _id:"B" }, { $inc:{ balance: 100 } });
       session.commitTransaction();
   } catch (e) {
       session.abortTransaction();
       throw e;
   } finally {
       session.endSession();
   }

Better (callback API auto-retries transient errors) - Node style:
   await session.withTransaction(async () => { ... });

Notes:
 - Default max runtime 60 s (transactionLifetimeLimitSeconds)
 - Collections must already exist (older versions)
 - Transactions have a performance cost; prefer embedding for single-doc atomicity
 - Java code is in section 30.


```

## 28. ADVANCED DATA QUERIES

```text
 // 1. Second highest salary
 db.employees.find().sort({salary:-1}).skip(1).limit(1)

 // 2. Highest salary per department
 db.employees.aggregate([ { $group:{ _id:"$dept", maxSal:{ $max:"$salary" } } } ])

 // 3. Employee with highest salary in each dept (whole doc)
 db.employees.aggregate([
   { $sort:{ salary:-1 } },
   { $group:{ _id:"$dept", top:{ $first:"$$ROOT" } } }
 ])

 // 4. Find duplicates by email
 db.users.aggregate([
   { $group:{ _id:"$email", count:{$sum:1}, ids:{$push:"$_id"} } },
   { $match:{ count:{ $gt:1 } } }
 ])

 // 5. Count by status
 db.orders.aggregate([ { $sortByCount:"$status" } ])

 // 6. Top 3 customers by spend
 db.orders.aggregate([
   { $group:{ _id:"$cust", total:{$sum:"$amount"} } },
   { $sort:{ total:-1 } }, { $limit:3 }
 ])

 // 7. Monthly sales
 db.orders.aggregate([
   { $group:{ _id:{ y:{$year:"$date"}, m:{$month:"$date"} }, sales:{$sum:"$amount"} } },
   { $sort:{ "_id.y":1, "_id.m":1 } }
 ])

 // 8. Employees whose salary > department average
 db.employees.aggregate([
   { $setWindowFields:{ partitionBy:"$dept", output:{ avgSal:{ $avg:"$salary", window:{documents:["unbounded","unbounded"]} } } } },
   { $match:{ $expr:{ $gt:["$salary","$avgSal"] } } }
 ])

 // 9. Running total (window function, 5.0+)
 db.orders.aggregate([
   { $setWindowFields:{ sortBy:{date:1}, output:{ running:{ $sum:"$amount", window:{ documents:["unbounded","current"] } } } } }
 ])

 // 10. Text search + sort by relevance
 db.articles.find({ $text:{ $search:"mongo" } }, { score:{ $meta:"textScore" } }).sort({ score:{ $meta:"textScore" } })

 // 11. Geo near
 db.places.find({ loc:{ $near:{ $geometry:{ type:"Point", coordinates:[72.87,19.07] }, $maxDistance:5000 } } })

 // 12. Array: total of all items in array field
 db.students.aggregate([ { $project:{ name:1, total:{ $sum:"$scores" } } } ])

 // 13. $filter and $map on arrays
 db.students.aggregate([ { $project:{ high:{ $filter:{ input:"$scores", as:"s", cond:{ $gte:["$$s",80] } } } } } ])

 // 14. Conditional: $cond / $switch
 db.employees.aggregate([ { $project:{ name:1, grade:{ $cond:[ {$gte:["$salary",60000]}, "High","Low" ] } } } ])

 // 15. Distinct count: group then $count
 db.orders.aggregate([ { $group:{ _id:"$cust" } }, { $count:"uniqueCustomers" } ])

 // 16. Change streams (watch for changes, needs replica set)
 db.orders.watch()


```

## 29. INTERVIEW QUESTIONS AND ANSWERS

```text
Q1. What is MongoDB? Why use it?
A: Document NoSQL DB storing BSON. Flexible schema, horizontal scaling, high availability,
   rich query/aggregation. Good for catalogs, content, IoT, real-time analytics.

Q2. SQL vs NoSQL / MongoDB vs RDBMS?
A: RDBMS: fixed schema, tables, joins, strong ACID, vertical scaling mainly.
   MongoDB: flexible schema, documents, embedding, built-in horizontal scaling,
   ACID per document (multi-doc via transactions).

Q3. What is BSON?
A: Binary JSON with extra types (Date, ObjectId, Decimal128...), fast to traverse.

Q4. What is _id / ObjectId?
A: Mandatory unique primary key. ObjectId = 12 bytes: 4-byte timestamp, 5-byte random,
   3-byte counter. Auto-indexed.

Q5. find vs findOne?
A: find returns a cursor of all matches; findOne returns the first matching document or null.

Q6. update vs updateOne vs updateMany?
A: updateOne -> first match; updateMany -> all matches; legacy update -> one unless multi:true;
   replaceOne -> replace whole doc.

Q7. delete vs deleteOne?
A: deleteOne removes first match; deleteMany removes all matches; remove is deprecated.

Q8. How is a database created in MongoDB?
A: use <name> and insert first document; created lazily.

Q9. What is a projection?
A: Choosing returned fields: find(filter, {name:1,_id:0}).

Q10. Embedded vs referenced documents - when to use which?
A: Embed for 1:1, 1:few, read together, need atomicity. Reference for 1:many large /
   unbounded, many:many, data used independently. Watch 16MB document limit.

Q11. Does MongoDB support joins?
A: Yes, via $lookup in aggregation (left outer join), plus $graphLookup. But schema
   design should minimize joins.

Q12. What is an index? Types?
A: Structure to speed reads. Single, compound, multikey, text, geospatial, hashed, TTL,
   unique, sparse, partial, wildcard. Trade-off: slower writes + storage.

Q13. How do you check if a query uses an index?
A: explain("executionStats"); look for IXSCAN vs COLLSCAN and docsExamined vs nReturned.

Q14. What is the aggregation framework?
A: Pipeline of stages ($match, $group, $project, ...) for data processing/analytics.
   Alternative to map-reduce (deprecated).

Q15. Difference between $match placed early vs late?
A: Early $match reduces documents and can use indexes -> much faster.

Q16. What is replication? What is a replica set?
A: Copies of data across nodes: 1 primary + secondaries; automatic failover through election.

Q17. What is sharding? Components?
A: Horizontal partitioning across shards via shard key; shards, mongos, config servers.

Q18. Replication vs sharding?
A: Replication = availability/redundancy (same data). Sharding = scalability (split data).

Q19. How to choose a shard key?
A: High cardinality, even distribution, query isolation (appears in most queries), avoid
   monotonic keys (use hashed).

Q20. Does MongoDB support ACID transactions?
A: Yes. Single document always atomic. Multi-document transactions since 4.0 (replica set)
   and 4.2 (sharded).

Q21. What is a capped collection?
A: Fixed-size, insertion-ordered circular collection. Used for logs.

Q22. What is TTL index?
A: Index with expireAfterSeconds on a date field; background thread deletes expired docs.

Q23. $push vs $addToSet?
A: push appends (dupes allowed); addToSet appends only if not present.

Q24. $pop vs $pull?
A: pop removes first/last by position; pull removes elements matching a condition.

Q25. What is $elemMatch and why needed?
A: Ensures a single array element meets all given conditions. Without it conditions can
   be satisfied by different elements.

Q26. What are write concern and read concern/preference?
A: Write concern = ack level for writes (w:1, majority). Read concern = isolation level
   of reads. Read preference = which node to read from.

Q27. What is the oplog?
A: Capped collection (local.oplog.rs) on the primary recording all writes; secondaries
   replay it.

Q28. What happens when the primary goes down?
A: Secondaries detect missed heartbeats, hold election, majority-voted secondary becomes
   primary. Needs majority of voting members alive.

Q29. What is WiredTiger?
A: Default storage engine (since 3.2): document-level concurrency, compression
   (snappy/zlib/zstd), checkpoints + journaling.

Q30. How do you handle schema changes / validation?
A: $jsonSchema validator with validationLevel/Action; collMod to modify.

Q31. How to improve MongoDB performance?
A: Right indexes (ESR), covered queries, projection, avoid $where & large skip, proper
   schema (embed), pagination via range, explain(), sharding, limit unbounded arrays,
   keep working set in RAM, connection pooling.

Q32. How to do pagination?
A: skip/limit for small offsets; for large data use find({_id:{$gt:lastId}}).sort({_id:1}).limit(n).

Q33. What is the difference between $lookup and embedding?
A: Embedding = single read, atomic, denormalized/duplicated data. $lookup = normalized,
   extra work at read time.

Q34. What is a covered query?
A: All queried and returned fields are in an index -> no docs read.

Q35. What is the maximum document size?
A: 16 MB (use GridFS for bigger files).

Q36. What is GridFS?
A: Spec for storing files > 16MB by splitting into 255KB chunks (fs.files, fs.chunks).

Q37. How to find slow queries?
A: Database profiler: db.setProfilingLevel(1, {slowms:100}); db.system.profile.find();
   or mongotop/mongostat, Atlas Performance Advisor.

Q38. What does upsert do?
A: Update if match else insert new doc.

Q39. $set vs replaceOne (or update without operator)?
A: $set changes only the given fields; replace overwrites the whole doc.

Q40. What is the difference between null and $exists:false?
A: {x:null} matches null OR missing; {x:{$exists:false}} only missing.

Q41. How do you secure MongoDB?
A: Enable authentication (SCRAM), RBAC roles, TLS, network binding/firewall, encryption
   at rest, auditing, avoid $where, never expose to internet.

Q42. Backup/restore?
A: mongodump / mongorestore, mongoexport / mongoimport (JSON/CSV), filesystem snapshots,
   Atlas backups.

Q43. CAP theorem and MongoDB?
A: MongoDB is CP by default: during a partition the minority side can't accept writes
   (no primary) to keep consistency.

Q44. Change streams?
A: Real-time notification of data changes using collection.watch() (built on oplog,
   requires replica set).

Q45. Spring Data MongoDB: how to connect?
A: spring.data.mongodb.uri=mongodb://localhost:27017/company ; use @Document,
   MongoRepository, MongoTemplate (see section 30).


```

## 30. CODE TO WRITE IN THE INTERVIEW

```text
--- 30.1 Quick shell CRUD (write this from memory) ---
   use company
   db.employees.insertOne({ name:"Dhruv", age:28, dept:"IT", salary:60000 })
   db.employees.find({ dept:"IT" }, { name:1, _id:0 }).sort({ salary:-1 }).limit(5)
   db.employees.updateOne({ name:"Dhruv" }, { $set:{ salary:70000 } })
   db.employees.deleteOne({ name:"Dhruv" })
   db.employees.createIndex({ dept:1, salary:-1 })

--- 30.2 Aggregation you will be asked: average salary per dept ---
   db.employees.aggregate([
     { $match:{ age:{ $gt:20 } } },
     { $group:{ _id:"$dept", avgSalary:{ $avg:"$salary" }, count:{ $sum:1 } } },
     { $sort:{ avgSalary:-1 } }
   ])

--- 30.3 Plain Java driver (mongodb-driver-sync) ---
   // Maven: org.mongodb:mongodb-driver-sync
   import com.mongodb.client.*;
   import com.mongodb.client.model.*;
   import org.bson.Document;
   import static com.mongodb.client.model.Filters.*;
   import static com.mongodb.client.model.Updates.*;
   import java.util.*;

   public class MongoDemo {
       public static void main(String[] args) {
           try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
               MongoDatabase db = client.getDatabase("company");
               MongoCollection<Document> col = db.getCollection("employees");

               // CREATE
               col.insertOne(new Document("name", "Dhruv").append("age", 28)
                       .append("dept", "IT")
                       .append("address", new Document("city", "Mumbai")));
               col.insertMany(Arrays.asList(
                       new Document("name", "Amit").append("age", 31).append("dept", "HR"),
                       new Document("name", "Riya").append("age", 25).append("dept", "IT")));

               // READ
               Document one = col.find(eq("name", "Dhruv")).first();
               for (Document d : col.find(and(gt("age", 20), eq("dept", "IT")))
                                    .projection(Projections.fields(Projections.include("name"),
                                                                   Projections.excludeId()))
                                    .sort(Sorts.descending("age")).limit(10)) {
                   System.out.println(d.toJson());
               }
               // nested
               col.find(eq("address.city", "Mumbai"));

               // UPDATE
               col.updateOne(eq("name", "Dhruv"), set("age", 29));
               col.updateMany(eq("dept", "IT"), inc("age", 1));
               col.updateOne(eq("name", "Dhruv"), set("address.city", "Pune"));
               col.updateOne(eq("name", "Dhruv"), push("skills", "Java"));

               // DELETE
               col.deleteOne(eq("name", "Amit"));
               col.deleteMany(eq("dept", "HR"));

               // INDEX
               col.createIndex(Indexes.ascending("name"));

               // AGGREGATION
               col.aggregate(Arrays.asList(
                   Aggregates.match(gt("age", 20)),
                   Aggregates.group("$dept", Accumulators.sum("count", 1),
                                              Accumulators.avg("avgAge", "$age")),
                   Aggregates.sort(Sorts.descending("count"))
               )).forEach(d -> System.out.println(d.toJson()));
           }
       }
   }

--- 30.4 Java transaction ---
   try (ClientSession session = client.startSession()) {
       session.withTransaction(() -> {
           accounts.updateOne(session, eq("_id", "A"), inc("balance", -100));
           accounts.updateOne(session, eq("_id", "B"), inc("balance",  100));
           return "done";
       });
   }

--- 30.5 Spring Boot / Spring Data MongoDB ---
   // application.properties
   spring.data.mongodb.uri=mongodb://localhost:27017/company

   @Document(collection = "employees")
   public class Employee {
       @Id private String id;
       private String name;
       private int age;
       private String dept;
       // getters / setters
   }

   public interface EmployeeRepository extends MongoRepository<Employee, String> {
       List<Employee> findByDept(String dept);
       List<Employee> findByAgeGreaterThan(int age);
       @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
       List<Employee> searchByName(String regex);
   }

   @RestController
   @RequestMapping("/employees")
   public class EmployeeController {
       @Autowired private EmployeeRepository repo;
       @PostMapping  public Employee add(@RequestBody Employee e) { return repo.save(e); }
       @GetMapping   public List<Employee> all() { return repo.findAll(); }
       @GetMapping("/{id}") public Employee get(@PathVariable String id) {
           return repo.findById(id).orElseThrow(); }
       @DeleteMapping("/{id}") public void del(@PathVariable String id) { repo.deleteById(id); }
   }

   // MongoTemplate custom query
   Query q = new Query(Criteria.where("dept").is("IT").and("age").gt(25));
   q.with(Sort.by(Sort.Direction.DESC, "age")).limit(5);
   List<Employee> list = mongoTemplate.find(q, Employee.class);
   mongoTemplate.updateFirst(new Query(Criteria.where("name").is("Dhruv")),
                             new Update().set("age", 30), Employee.class);

--- 30.6 Schema design example to draw on the whiteboard ---
   Blog: post { _id, title, author:{name,id}, tags:[..], comments:[{user,text,date}] }
   Embed first N comments in post; keep the rest in a "comments" collection (bucket pattern).


```

## 31. HOW TO EXPLAIN IN AN INTERVIEW (CHEAT SHEET)

```text
Structure every answer: DEFINITION -> HOW IT WORKS -> SMALL EXAMPLE -> WHEN/WHY (trade-off)

 * "Tell me about MongoDB":
   "It is a document-oriented NoSQL database. Data is stored as BSON documents in
   collections, with a flexible schema. I've used it for [project] because ... It
   scales horizontally via sharding and stays available via replica sets. Queries use
   a rich query language and the aggregation pipeline."

 * Embedding vs referencing: "I model by access pattern. Data read together and bounded
   goes embedded; unbounded or shared data is referenced."

 * Indexing: "Without an index MongoDB does a collection scan. I check explain(); I build
   compound indexes following Equality-Sort-Range and prefer covered queries. Trade-off
   is write cost and memory."

 * Aggregation: "A pipeline: $match early to cut data, $group for accumulations,
   $lookup for joins, $project to shape output. I put $match/$sort first to use indexes."

 * Replication vs sharding: "Replication = same data on many nodes for availability;
   sharding = split data across nodes for scale. In production each shard is a replica set."

 * Transactions: "Single-doc ops are atomic. For multi-doc I use sessions with
   startTransaction/commit, but I prefer a schema that avoids needing them."

 * Performance tuning story: "Found slow query via profiler -> explain showed COLLSCAN
   -> added compound index -> docsExamined dropped from 1M to 20."

Common traps to mention proactively:
   - $elemMatch vs plain array conditions
   - {x:null} matches missing fields
   - updateOne needs operators ($set), replaceOne overwrites
   - skip() is slow for large offsets
   - ordered:true stops at first insert error
   - Dates stored in UTC
   - 16 MB document limit, avoid unbounded arrays
   - Use Decimal128 for money

Quick revision one-liners:
   find -> cursor | findOne -> doc | updateOne -> first | updateMany -> all
   deleteOne -> first | deleteMany -> all | $set change | $unset remove field
   $push append | $addToSet unique append | $pop first/last | $pull by condition
   $in any-of | $all contains-all | $size exact length | $elemMatch same element
   Replication = HA | Sharding = scale | Index = speed | Transaction = multi-doc ACID

```
