# kafka

```text
1. What is a Kafka topic? How does it work?
A Kafka topic is a logical category where messages are published and consumed.

Topics are similar to tables in a database, but instead of storing rows, they store streaming messages.
Topics are divided into partitions to distribute data across multiple brokers.
Producers write data to topics, and consumers subscribe to topics to receive messages.

2. What are Kafka partitions, and why are they important?
A partition is a subset of a Kafka topic that enables parallel processing and scalability.

Each topic is divided into multiple partitions, and each partition is stored on different brokers.
Partitions ensure Kafka can handle high throughput by distributing data.
Key Benefits:
✔ Scalability – Multiple partitions allow parallel processing.
✔ Fault Tolerance – Data can be replicated across brokers.
✔ Ordering – Kafka maintains order within a partition.
Example:
If a topic "orders" has 3 partitions:

Partition 0 -> [Order1, Order4, Order7]
Partition 1 -> [Order2, Order5, Order8]
Partition 2 -> [Order3, Order6, Order9]
Messages are distributed across partitions.
Consumers read in parallel to improve performance.
3. How does Kafka ensure message ordering?
Kafka ensures message ordering within a partition but not across partitions.

Each partition is ordered using an offset (a unique message ID).
If a producer sends messages with a key, Kafka ensures all messages with that key go to the same partition, preserving order.
Ordering is not guaranteed across multiple partitions since messages are processed in parallel.
✅ Solution for maintaining order: Use a single partition or key-based partitioning.

4. What happens if a Kafka topic has only one partition?
If a topic has only one partition:
✔ All messages go to a single broker (no parallel processing).
✔ Ordering is strictly maintained since there is only one sequence.
❌ Scalability is limited since only one consumer can process data at a time.

When to use a single partition?

When ordering is critical, such as financial transactions or event logs.
5. How does a producer decide which partition to send a message to?
A Kafka producer decides the partition in three ways:

Explicit Partitioning – If the producer specifies a partition, Kafka sends the message to that partition.
Key-Based Partitioning – If a message has a key, Kafka hashes the key and assigns the message to the same partition consistently.
Round-Robin (Default) – If no partition or key is specified, Kafka distributes messages evenly across partitions.
Example:

java
Copy
Edit
ProducerRecord<String, String> record = new ProducerRecord<>("orders", "customer123", "OrderID: 1001");
Here, "customer123" is the key, ensuring all messages from this customer go to the same partition.
6. How does Kafka handle partition replication?
Kafka ensures fault tolerance by replicating partitions across multiple brokers.

Replication Factor (RF): Defines how many copies of each partition exist.
One partition is the leader, and the others are followers.
If the leader fails, a follower is promoted as the new leader.
Example (Replication Factor = 2, 3 Brokers):

Partition 0: Leader → Broker 1, Follower → Broker 2
Partition 1: Leader → Broker 2, Follower → Broker 3
Partition 2: Leader → Broker 3, Follower → Broker 1
✔ Ensures high availability and prevents data loss.

7. What is a partition leader in Kafka?
A partition leader is the broker responsible for handling all read and write requests for a partition.

Each partition has only one leader and multiple followers.
Followers replicate data from the leader.
If a leader fails, Kafka automatically elects a new leader from the followers.
8. How does Kafka achieve scalability using partitions?
Kafka achieves scalability by using multiple partitions across brokers.

More partitions = Higher parallelism
Consumers can read from different partitions simultaneously, increasing throughput.
New brokers can be added dynamically, and partitions are reassigned automatically.
Example:
If a topic has 6 partitions and 3 consumers, Kafka will distribute them as:

Consumer 1 → Partition 0, Partition 3
Consumer 2 → Partition 1, Partition 4
Consumer 3 → Partition 2, Partition 5
✔ Allows horizontal scaling by adding more partitions and consumers.

Summary
Question	Answer
What is a Kafka topic?	A logical category where messages are stored and consumed.
What are Kafka partitions?	Subdivisions of a topic that enable parallel processing.
How does Kafka ensure message ordering?	Ordering is maintained within a partition, not across partitions.
What happens if a topic has one partition?	Strict ordering is maintained, but scalability is limited.
How does a producer decide the partition?	Based on explicit partition, key-based hashing, or round-robin.
How does Kafka handle replication?	Each partition has a leader and multiple followers for fault tolerance.
What is a partition leader?	The broker responsible for handling read and write requests for a partition.
How does Kafka scale?	By distributing partitions across multiple brokers and allowing parallel consumer processing.



1. What is the Role of ZooKeeper in Kafka?
ZooKeeper is a distributed coordination service that Kafka uses to manage metadata, brokers, partitions, and consumer groups.

Key Responsibilities of ZooKeeper in Kafka:
✔ Broker Management – Tracks all active Kafka brokers.
✔ Leader Election – Ensures that each partition has a leader broker.
✔ Consumer Group Coordination – Helps in balancing consumer workloads.
✔ Metadata Storage – Stores topic configurations, partitions, and ACLs.
✔ Fault Tolerance – Detects failures and helps in automatic recovery.

2. Why Does Kafka Use ZooKeeper?
Kafka relies on ZooKeeper for distributed system coordination.

Main reasons:
✔ Ensures High Availability – If a broker goes down, ZooKeeper helps elect a new leader.
✔ Manages Kafka Clusters – Keeps track of brokers, partitions, and consumers.
✔ Detects Failures Quickly – ZooKeeper watches nodes and notifies Kafka of failures.
✔ Maintains Consistency – Ensures that metadata (topics, offsets, etc.) is synchronized across the cluster.

3. How Does ZooKeeper Help in Broker Failure Recovery?
Detects broker failure – ZooKeeper continuously monitors broker health.
Removes the failed broker from the cluster – If a broker goes down, ZooKeeper marks it as dead.
Triggers leader election – ZooKeeper helps select a new leader for affected partitions.
Updates metadata – It informs consumers and producers about the new leader.
🔹 Example:

Broker 1 (Leader) fails.
ZooKeeper detects failure and elects Broker 2 as the new leader.
Consumers and producers start sending data to Broker 2.
4. What is Leader Election in Kafka, and How Does ZooKeeper Handle It?
Leader Election is the process of selecting a partition leader among brokers.

How ZooKeeper Handles Leader Election?
Each partition has a leader broker and multiple follower brokers.
ZooKeeper stores partition leader information.
If the leader fails, ZooKeeper elects a new leader from available followers.
The new leader takes over all read and write operations for that partition.
✔ Ensures high availability and fault tolerance.

5. How Does ZooKeeper Help Manage Consumer Groups in Kafka?
ZooKeeper helps in consumer group coordination by:
✔ Tracking active consumers and their assigned partitions.
✔ Managing consumer rebalancing when a consumer joins or leaves.
✔ Storing committed offsets (older Kafka versions; newer versions use Kafka brokers instead).

🔹 Example:

Consumer C1 leaves the group → ZooKeeper reassigns its partitions to other consumers.
Consumer C2 joins the group → ZooKeeper assigns it new partitions dynamically.
6. What Happens If ZooKeeper Fails in a Kafka Cluster?
Short-term failure → Kafka continues working because brokers can function independently.
Long-term failure → Kafka cannot elect new leaders or update metadata.
Solutions:
✔ Run multiple ZooKeeper nodes for high availability.
✔ Use Kafka KRaft mode (Kafka 3.0+) to eliminate ZooKeeper.

7. How Does Kafka Store Topic Metadata in ZooKeeper?
Kafka stores important metadata in ZooKeeper:

Broker Metadata – Information about active brokers.
Topic Metadata – List of topics, partitions, and their replication factors.
Partition Leader Information – Identifies the leader broker for each partition.
Consumer Group Information – Tracks consumers and their assigned partitions.
Access Control Lists (ACLs) – Stores security configurations.
📌 Stored as ZNodes (ZooKeeper Nodes) in a hierarchical format:


/kafka
  ├── /brokers       # List of active brokers
  ├── /topics        # Topic metadata
  ├── /consumers     # Consumer group info
  ├── /config        # Topic and broker configurations
✔ ZooKeeper notifies Kafka when any changes happen.

8. What is Kafka KRaft Mode? How Does It Eliminate ZooKeeper?
KRaft (Kafka Raft mode) is Kafka’s new metadata management system introduced in Kafka 3.0.

✔ KRaft removes the need for ZooKeeper by handling metadata inside Kafka itself.
✔ Uses the Raft consensus algorithm instead of ZooKeeper’s ZAB protocol.
✔ Metadata is stored in a dedicated Kafka controller quorum (a set of Kafka brokers).

🔹 How KRaft Works?

One Kafka broker becomes the controller leader.
Other brokers replicate metadata to ensure consistency.
If the leader fails, a new leader is elected from the Kafka cluster.
📌 No external dependency on ZooKeeper!

9. What Are the Advantages of Kafka KRaft Over ZooKeeper?
Feature	Kafka with ZooKeeper	Kafka with KRaft
Metadata Management	External (ZooKeeper)	Internal (Kafka)
Scalability	Limited (due to ZooKeeper bottlenecks)	Higher (Kafka manages metadata directly)
Failure Handling	Requires ZooKeeper recovery	Faster failover using Raft
Deployment Complexity	Needs separate ZooKeeper cluster	Simpler (only Kafka brokers needed)
Latency	Higher due to ZooKeeper communication	Lower since metadata is in Kafka
✔ KRaft improves Kafka’s performance, reliability, and scalability!

Summary Table
Question	Answer
What is the role of ZooKeeper in Kafka?	Manages metadata, brokers, partitions, and consumer groups.
Why does Kafka use ZooKeeper?	Provides distributed coordination, leader election, and failure detection.
How does ZooKeeper help in broker failure recovery?	Detects failures and helps elect a new partition leader.
What is leader election in Kafka?	ZooKeeper selects a new leader broker when the current leader fails.
How does ZooKeeper help manage consumer groups?	Tracks active consumers and balances partition assignments.
What happens if ZooKeeper fails?	Kafka continues running, but no new leader elections can happen.
How does Kafka store metadata in ZooKeeper?	Uses ZNodes to store broker, topic, partition, and consumer metadata.
What is Kafka KRaft mode?	Kafka’s built-in metadata system replacing ZooKeeper (from Kafka 3.0).
Advantages of KRaft over ZooKeeper?	Faster failover, better scalability, and no external dependencies.

---------------------------------------------------------------------------------------------------

Producer- servics that publish events
broker- kafka wegera ye sb broker hote hai
consumers- who consume the events

When we use kafka - jb hame Throughput bahut high chaheye.
Throughput in Kafka refers to how much data Kafka can process (produce and consume) in a given amount of time.
It is usually measured as:
Messages per second (msg/sec)
Bytes per second (MB/sec or GB/sec)


---------------------------------------------------------------------
Topic and events->

Excellent question! This is where many beginners get confused.

No. A Topic is not the same as an Event.

A Topic is a container (or category), and an Event is the actual message stored inside that container.

Let's understand it with an example.

Real-world analogy: WhatsApp Groups

Imagine you have three WhatsApp groups:

📱 Family Group
📱 Office Group
📱 Friends Group

These groups are like Kafka Topics.

Now suppose people send messages.

Family Group

Good Morning
Dinner at 8 PM
Happy Birthday

Each message is an Event.

So:

WhatsApp Group = Topic
Individual Message = Event
Kafka Example

Suppose you have an e-commerce application.

Whenever something happens, an event is generated.

Examples:

Customer registered
Order placed
Payment completed
Product shipped

Instead of putting everything into one place, Kafka organizes them into topics.

Topics

Customer-Topic
--------------------
Customer Registered
Customer Updated
Customer Deleted

Order-Topic
--------------------
Order Created
Order Cancelled
Order Delivered

Payment-Topic
--------------------
Payment Started
Payment Successful
Payment Failed

Here:

Customer-Topic is a Topic.
Customer Registered is an Event.
What exactly is an Event?

An event is simply something that happened in your system.

Example:

A customer places an order.

The application creates an event like:

{
  "orderId": 101,
  "customer": "Dhruv",
  "amount": 2500,
  "status": "CREATED"
}

This JSON is the event (also called a record or message).

Kafka stores it inside the Orders topic.
```
